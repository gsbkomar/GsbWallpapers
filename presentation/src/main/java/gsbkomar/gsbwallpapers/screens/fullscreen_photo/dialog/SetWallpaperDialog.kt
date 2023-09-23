package gsbkomar.gsbwallpapers.screens.fullscreen_photo.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import gsbkomar.gsbwallpapers.databinding.SetWallpaperDialogBinding
import gsbkomar.gsbwallpapers.screens.fullscreen_photo.FullscreenPhotoFragment
import javax.inject.Inject

class SetWallpaperDialog @Inject constructor(private val fullscreenPhotoFragment: FullscreenPhotoFragment) :
    DialogFragment() {

    private var _binding: SetWallpaperDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            _binding = SetWallpaperDialogBinding.inflate(layoutInflater)
            AlertDialog.Builder(it)
                .setView(binding.root)
                .create()
        } ?: throw IllegalStateException("Alert dialog don't create")
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() = with(binding) {
        btnSetLockScreen.setOnClickListener {
            fullscreenPhotoFragment.setWallpaper(false)
            Toast.makeText(context, "Wallpaper set", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        btnSetMainScreen.setOnClickListener {
            fullscreenPhotoFragment.setWallpaper(true)
            Toast.makeText(context, "Wallpaper set", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}