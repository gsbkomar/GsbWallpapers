package gsbkomar.gsbwallpapers.screens.fullscreen_photo

import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import gsbkomar.data.dto.db.PhotoDbDto
import gsbkomar.gsbwallpapers.R
import gsbkomar.gsbwallpapers.State
import gsbkomar.gsbwallpapers.databinding.FragmentFullsreenPhotoBinding
import gsbkomar.gsbwallpapers.extensions.setBackPressed
import gsbkomar.gsbwallpapers.factory.FullscreenPhotoViewModelFactory
import gsbkomar.gsbwallpapers.screens.Keys
import gsbkomar.gsbwallpapers.screens.fullscreen_photo.dialog.SetWallpaperDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject


@AndroidEntryPoint
class FullscreenPhotoFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentFullsreenPhotoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: FullscreenPhotoViewModelFactory
    private val viewModel: FullscreenPhotoViewModel by viewModels { viewModelFactory }

    private var photoUrl = ""
    private var isSaveToFavorite = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoUrl = it.getString(Keys.KEY_PHOTO_URL)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullsreenPhotoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackPressed(R.id.action_fullscreenPhotoFragment_to_navFragment)

        checkStateInfo()

        lifecycleScope.launch {
            if (viewModel.getAllFavoritePhoto().contains(PhotoDbDto(photoUrl))) {
                isSaveToFavorite = false
                checkIsFavorite()
            } else {
                checkIsFavorite()
            }
        }
        initListeners()
    }

    private fun checkStateInfo() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Loading -> {
                        lottieLoading.setAnimation(R.raw.loading)
                        lottieLoading.isVisible = true
                        lottieLoading.playAnimation()
                        photo.isVisible = false
                        cvBottomMenu.isVisible = false
                    }

                    State.Success -> {
                        lottieLoading.isVisible = false
                        lottieLoading.pauseAnimation()
                        photo.isVisible = true
                        cvBottomMenu.isVisible = true
                    }

                    is State.Error -> {
                        // Log
                    }
                }
            }
        }
    }

    private fun checkIsFavorite() = with(binding.btnAddFavorite) {
        if (isSaveToFavorite) {
            setImageDrawable(resources.getDrawable(R.drawable.baseline_favorite_border))
        } else setImageDrawable(resources.getDrawable(R.drawable.baseline_favorite))
    }

    private fun initListeners() = with(binding) {

        Glide.with(this@FullscreenPhotoFragment)
            .load(photoUrl)
            .into(photo)

        btnSetWallpaper.setOnClickListener {
            showSetWallpaperDialog()
        }
        btnAddFavorite.setOnClickListener {
            lifecycleScope.launch {
                if (isSaveToFavorite) {
                    if (!viewModel.getAllFavoritePhoto().contains(PhotoDbDto(photoUrl))) {
                        viewModel.upsertFavoritePhoto(PhotoDbDto(photoUrl))
                        isSaveToFavorite = false
                        checkIsFavorite()
                    }
                } else {
                    viewModel.deleteFavoritePhoto(photoUrl)
                    isSaveToFavorite = true
                    checkIsFavorite()
                }
            }
        }
        btnDownload.setOnClickListener {
            downloadUrlPhotoToGallery()
        }
    }

    private fun downloadUrlPhotoToGallery() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val url = URL(photoUrl)
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                val values = ContentValues()
                values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis())
                values.put(Images.Media.MIME_TYPE, "image/jpeg")
                values.put(MediaStore.MediaColumns.DATA, WALLPAPER)
                Images.Media.insertImage(
                    requireContext().contentResolver,
                    image,
                    null,
                    null
                )
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Photo download", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Download error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showSetWallpaperDialog() {
        val dialogFragment = SetWallpaperDialog(this)
        dialogFragment.show(parentFragmentManager, null)
    }

    fun setWallpaper(lockOrMainScreen: Boolean) {
        lifecycleScope.launch(Dispatchers.IO) {
            val inputStream = URL(photoUrl).openStream()
            if (lockOrMainScreen) {
                WallpaperManager.getInstance(requireContext().applicationContext).setStream(
                    inputStream,
                    null,
                    true,
                    WallpaperManager.FLAG_LOCK
                )
            } else {
                WallpaperManager.getInstance(requireContext().applicationContext)
                    .setStream(inputStream)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val WALLPAPER = "wallpaper"
    }
}