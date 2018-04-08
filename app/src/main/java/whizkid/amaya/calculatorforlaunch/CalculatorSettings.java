package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class CalculatorSettings extends AppCompatActivity {

    private CheckBox settingsVibrateOnTouch;
    private CheckBox settingsPrecisionTwoDigits;
//    private CheckBox settingsKeyPadLayout;
//    private CheckBox settingsTheme;
//    private CheckBox settingsDisplayFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_settings);

        settingsVibrateOnTouch = (CheckBox) findViewById(R.id.settingsVibrateOnTouch);
        settingsPrecisionTwoDigits = (CheckBox) findViewById(R.id.settingsPrecisionTwoDigits);

        settingsPrecisionTwoDigits.setChecked(
                Boolean.valueOf(
                    Utils.getValueFromSharedPreference(Utils.SETTINGS_PRECISSION_TWO_DIGIT)));

        settingsVibrateOnTouch.setChecked(
                Boolean.valueOf(
                        Utils.getValueFromSharedPreference(Utils.SETTINGS_VIBRATE_ON_TOUCH)));


//        private CheckBox settingsKeyPadLayout;
//        private CheckBox settingsTheme;
//        private CheckBox settingsDisplayFormat;


    }


    public void setCalculatorSettings(View view) {
        
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_VIBRATE_ON_TOUCH,
                Boolean.toString(settingsVibrateOnTouch.isChecked()), sharedPreferences);

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_PRECISSION_TWO_DIGIT,
                Boolean.toString(settingsPrecisionTwoDigits.isChecked()), sharedPreferences);

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_COLOR_THEME, "SETTINGS_THEME", sharedPreferences);

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_KEYPAD_LAYOUT, "SETTINGS_KEYPAD_LAYOUT", sharedPreferences);

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_DISPLAY_FORMAT, "SETTINGS_DISPLAY_FORMAT", sharedPreferences);
    }
}
