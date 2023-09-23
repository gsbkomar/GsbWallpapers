package gsbkomar.gsbwallpapers.screens.photo_list_category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gsbkomar.data.dto.CategoriesDto
import gsbkomar.gsbwallpapers.R
import gsbkomar.gsbwallpapers.State
import gsbkomar.gsbwallpapers.databinding.FragmentPhotoListBinding
import gsbkomar.gsbwallpapers.extensions.setBackPressed
import gsbkomar.gsbwallpapers.factory.PhotoListViewModelFactory
import gsbkomar.gsbwallpapers.screens.Keys
import gsbkomar.gsbwallpapers.screens.photo_list_category.adapter.PhotoListAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PhotoListFragment @Inject constructor(): Fragment() {

    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: PhotoListViewModelFactory
    private val viewModel: PhotoListViewModel by viewModels { viewModelFactory }

    private var photoListAdapter = PhotoListAdapter(this) {
        findNavController().navigate(
            R.id.action_photoListFragment_to_fullscreenPhotoFragment,
            Bundle().apply { putString(Keys.KEY_PHOTO_URL, it) })
    }

    private var categoryDto: CategoriesDto? = null

    private var category = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(Keys.KEY_CATEGORY)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkStateInfo()
        setBackPressed(R.id.action_photoListFragment_to_navFragment)

        lifecycleScope.launch {
            categoryDto = viewModel.getPhotoListByCategory(category) as CategoriesDto
            initAdapter()
        }
    }

    private fun checkStateInfo() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Loading -> {
                        lottieLoading.setAnimation(R.raw.loading)
                        lottieLoading.isVisible = true
                        lottieLoading.playAnimation()
                        svPhoto.isVisible = false
                    }

                    State.Success -> {
                        lottieLoading.isVisible = false
                        lottieLoading.pauseAnimation()
                        svPhoto.isVisible = true
                    }
                    is State.Error -> {
                        // Log
                    }
                }
            }
        }
    }

    private fun initAdapter() = with(binding.rcPhoto) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = photoListAdapter

        lifecycleScope.launch {
            val list = categoryDto?.results?.first()?.tags?.filter { tagsDto ->  tagsDto.source != null}
            photoListAdapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}