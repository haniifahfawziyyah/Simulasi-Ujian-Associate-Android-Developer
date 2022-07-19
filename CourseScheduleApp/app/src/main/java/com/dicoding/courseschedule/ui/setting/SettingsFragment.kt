package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        val pref = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        pref?.setOnPreferenceChangeListener { _, newValue ->
            when(newValue){
                "off" -> updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                "on" -> updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                else -> updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        val prefNotification = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        val dailyReminder = DailyReminder()
        prefNotification?.setOnPreferenceChangeListener { _, newValue ->

            if (newValue == true){
                dailyReminder.setDailyReminder(requireContext())
            }else{
                dailyReminder.cancelAlarm(requireContext())
            }

            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}