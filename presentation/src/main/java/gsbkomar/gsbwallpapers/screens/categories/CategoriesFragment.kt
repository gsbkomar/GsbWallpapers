package gsbkomar.gsbwallpapers.screens.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gsbkomar.gsbwallpapers.R
import gsbkomar.gsbwallpapers.State
import gsbkomar.gsbwallpapers.databinding.FragmentCategoriesBinding
import gsbkomar.gsbwallpapers.extensions.setBackPressed
import gsbkomar.gsbwallpapers.factory.CategoriesViewModelFactory
import gsbkomar.gsbwallpapers.screens.Keys
import gsbkomar.gsbwallpapers.screens.categories.adapter.CategoryCardListAdapter
import gsbkomar.gsbwallpapers.screens.categories.models.CategoryCard
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class CategoriesFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: CategoriesViewModelFactory
    private val viewModel: CategoriesViewModel by viewModels { viewModelFactory }

    private var categoryCardListAdapter = CategoryCardListAdapter(this) {
        findNavController().navigate(
            R.id.action_navFragment_to_photoListFragment2,
            Bundle().apply { putString(Keys.KEY_CATEGORY, it) })
    }

    private var categoryCardList: MutableList<CategoryCard> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackPressed(null)

        checkStateInfo()
        categoryCardList = viewModel.getCategoryList()

        initListeners()
        initAdapter()
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
                        cvTopPanel.isVisible = false
                    }

                    State.Success -> {
                        lottieLoading.isVisible = false
                        lottieLoading.pauseAnimation()
                        svPhoto.isVisible = true
                        cvTopPanel.isVisible = true
                    }
                    is State.Error -> {
                        // Log
                    }
                }
            }
        }
    }

    private fun initListeners() = with(binding) {
        btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_navFragment_to_settingsFragment)
        }
        btnSearch.setOnClickListener {
            val text = etSearch.text.toString()
            if (text.isNotBlank()) {
                val list = categoryCardList.filter { it.name.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT)) }
                categoryCardListAdapter.submitList(list)
            } else {
                categoryCardListAdapter.submitList(categoryCardList)
            }
        }
    }

    private fun initAdapter() = with(binding.rcPhoto) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = categoryCardListAdapter

        lifecycleScope.launch {
            categoryCardListAdapter.submitList(categoryCardList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}