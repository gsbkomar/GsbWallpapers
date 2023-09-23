package gsbkomar.gsbwallpapers.screens.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import gsbkomar.gsbwallpapers.R
import gsbkomar.gsbwallpapers.databinding.FragmentSettingsBinding
import gsbkomar.gsbwallpapers.extensions.setBackPressed

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs = requireActivity().getSharedPreferences(KEY_THEME_COLOR, Context.MODE_PRIVATE)
        initListeners()
        setBackPressed(null)
    }

    private fun initListeners() = with(binding) {
        val isSharedChecked = sharedPrefs.getBoolean(KEY_THEME_COLOR, false)
        switchThemeColor.isChecked = isSharedChecked
        setColorTheme(isSharedChecked)
        switchThemeColor.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean(KEY_THEME_COLOR, isChecked).apply()
            setColorTheme(isChecked)
        }
        btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_navFragment)
        }
    }

    private fun setColorTheme(isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.tvColorTheme.text = getString(R.string.disable_dark_mode)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.tvColorTheme.text = getString(R.string.enable_dark_mode)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val KEY_THEME_COLOR = "KEY_THEME_COLOR"
    }
}