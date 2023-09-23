package gsbkomar.gsbwallpapers.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gsbkomar.gsbwallpapers.R
import gsbkomar.gsbwallpapers.databinding.FragmentNavBinding
import gsbkomar.gsbwallpapers.screens.categories.CategoriesFragment
import gsbkomar.gsbwallpapers.screens.favorite.FavoriteFragment

class NavFragment : Fragment() {

private var _binding: FragmentNavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFragment(CategoriesFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.category -> {
                    loadFragment(CategoriesFragment())
                    true
                }

                R.id.favorite -> {
                    loadFragment(FavoriteFragment())
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}
