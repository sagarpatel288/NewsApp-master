package com.example.android.newsapp.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.android.newsapp.R;
import com.example.android.newsapp.base.BaseActivity;
import com.example.android.newsapp.databinding.ActivitySettingsBinding;

import androidx.annotation.Nullable;

public class SettingsActivity extends BaseActivity {

    /**
     * We should refresh and reload news list only if current preference has been changed
     */
    public static boolean hasPrefChanged;
    ActivitySettingsBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void onViewStubInflated(View inflatedView, Bundle savedInstanceState) {
        binding = ActivitySettingsBinding.bind(inflatedView);
    }

    @Override
    public void initControllers() {

    }

    @Override
    public void handleViews() {
        setToolbar();
    }

    private void setToolbar() {
        binding.commonToolbar.toolbar.setTitle(getString(R.string.settings).toUpperCase());
        setSupportActionBar(binding.commonToolbar.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void restoreValues(Bundle savedInstanceState) {

    }

    @Override
    public void onGetConnectionState(boolean isConnected) {

    }

    @Override
    public void onBackPressed() {
        if (hasPrefChanged) {
            setResult(Activity.RESULT_OK);
        } else {
            setResult(Activity.RESULT_CANCELED);
        }
        super.onBackPressed();
    }

    public static class SettingsFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener {

        private int i;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences_settings);
            // Note: 11/20/2018 by sagar  Find preference through key
            Preference prefSearchFor = findPreference(getString(R.string.pref_key_search_for));
            // Note: 11/20/2018 by sagar  Helper method
            bindPreference(prefSearchFor);
        }

        private void bindPreference(Preference prefSearchFor) {
            // Note: 11/19/2018 by sagar  set onPreferenceChangeListener for our preference object
            prefSearchFor.setOnPreferenceChangeListener(this);
            // Note: 11/20/2018 by sagar  Get default preference to store and retrieve value
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(prefSearchFor.getContext());
            // Note: 11/20/2018 by sagar  Get preference key value
            String prefValue = sharedPreferences.getString(prefSearchFor.getKey(), "");
            // Note: 11/20/2018 by sagar  Store value in preferences
            onPreferenceChange(prefSearchFor, prefValue);
        }

        /**
         * Listener to track preference change
         * <p>
         * Callback whenever the preference having this callback gets changed
         * <p>
         * Being used in {@link #bindPreference(Preference)} {@link com.example.android.newsapp.newslist.ui.MainActivity#getPreference(SharedPreferences, String, String)}
         *
         * @param preference Preference on which this listener has been applied and has been changed
         * @param newValue   New value of preference after change
         * @return true if the preference change has been handled successfully
         * @see <a href="https://developer.android.com/reference/android/preference/PreferenceFragment">Preference Fragment</a>
         * @since 1.0
         * See <a href="https://developer.android.com/reference/android/preference/Preference.OnPreferenceChangeListener">OnPreferenceChange</a>
         */
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            i++;
            String prefs = String.valueOf(newValue);
            preference.setSummary(prefs);
            // Note: 11/20/2018 by sagar  To prevent toast message before preference changes
            if (i > 1) {
                hasPrefChanged = true;
                Toast.makeText(preference.getContext(), getString(R.string.msg_your_preference_has_been_saved), Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }
}
