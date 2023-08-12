package id.izazdhiya.disasterapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import id.izazdhiya.disasterapp.R
import id.izazdhiya.disasterapp.databinding.ActivitySettingsBinding
import id.izazdhiya.disasterapp.data.datastore.SettingsDataStore
import id.izazdhiya.disasterapp.ui.viewmodel.SettingsViewModel

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private val viewModel by viewModels<SettingsViewModel> {
        SettingsViewModel.Factory(SettingsDataStore(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.pengaturan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.deskripsiMode.text = getString(R.string.disable_dark_mode)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.deskripsiMode.text = getString(R.string.enable_dark_mode)
            }
            binding.switchMode.isChecked = it
        }

        binding.switchMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveTheme(isChecked)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}