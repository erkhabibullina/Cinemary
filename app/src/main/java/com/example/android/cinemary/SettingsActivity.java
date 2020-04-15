package com.example.android.cinemary;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(getString(R.string.settings));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Fragment which handles preferences.
     */
    public static class SettingsFragment extends PreferenceFragmentCompat implements
            SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.settings);

            SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
            PreferenceScreen prefScreen = getPreferenceScreen();

            for (int i = 0; i < prefScreen.getPreferenceCount(); i++) {
                Preference pref = prefScreen.getPreference(i);
                if (!(pref instanceof CheckBoxPreference)) {
                    setPreferenceSummary(pref, sharedPreferences.getString(pref.getKey(), ""));
                }
            }
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Preference pref = findPreference(key);
            if (pref != null) {
                if (!(pref instanceof CheckBoxPreference)) {
                    setPreferenceSummary(pref, sharedPreferences.getString(pref.getKey(), ""));
                }
            }
        }

        /**
         * Sets summary to current value of ListPreference and EditTextPreference.
         *
         * @param preference The preference intended to change summary.
         * @param summary    Summary String to use.
         */
        private void setPreferenceSummary(Preference preference, String summary) {
            if (preference instanceof ListPreference) {
                ListPreference pref = (ListPreference) preference;
                int prefIndex = pref.findIndexOfValue(summary);
                if (prefIndex >= 0) {
                    pref.setSummary(pref.getEntries()[prefIndex]);
                }
            } else if (preference instanceof EditTextPreference) {
                EditTextPreference pref = (EditTextPreference) preference;
                pref.setSummary(summary);
            }
        }
    }
}
