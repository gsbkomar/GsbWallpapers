package gsbkomar.gsbwallpapers.screens.favorite

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
import gsbkomar.data.dto.db.PhotoDbDto
import gsbkomar.gsbwallpapers.R
import gsbkomar.gsbwallpapers.State
import gsbkomar.gsbwallpapers.databinding.FragmentFavoriteBinding
import gsbkomar.gsbwallpapers.factory.FavoriteViewModelFactory
import gsbkomar.gsbwallpapers.screens.Keys
import gsbkomar.gsbwallpapers.screens.favorite.adapter.FavoriteListAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteListAdapter = FavoriteListAdapter(this) {
        findNavController().navigate(R.id.action_navFragment_to_fullscreenPhotoFragment,
            Bundle().apply { putString(Keys.KEY_PHOTO_URL, it) })
    }

    private var favoriteList: List<PhotoDbDto>? = null

    @Inject
    lateinit var viewModelFactory: FavoriteViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            checkStateInfo()
            favoriteList = viewModel.getAllFavorite()
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
        adapter = favoriteListAdapter

        lifecycleScope.launch {
            favoriteListAdapter.submitList(favoriteList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}