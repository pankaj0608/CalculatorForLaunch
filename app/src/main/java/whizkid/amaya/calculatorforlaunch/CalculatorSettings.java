package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class CalculatorSettings extends AppCompatActivity {

    private CheckBox settingsVibrateKeys;
    private CheckBox settingsPrecisionTwoDigits;
//    private CheckBox settingsKeyPadLayout;
//    private CheckBox settingsTheme;
//    private CheckBox settingsDisplayFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_settings);

        settingsVibrateKeys = (CheckBox) findViewById(R.id.settingsVibrateKeys);
        settingsPrecisionTwoDigits = (CheckBox) findViewById(R.id.settingsPrecisionTwoDigits);
//        private CheckBox settingsKeyPadLayout;
//        private CheckBox settingsTheme;
//        private CheckBox settingsDisplayFormat;


    }

    public void setCalculatorSettings(View view) {

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_VIBRATE_ON_TOUCH,
                Boolean.toString(settingsVibrateKeys.isChecked()),
                this.getPreferences(Context.MODE_PRIVATE));

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_PRECISSION_TWO_DIGIT,
                Boolean.toString(settingsPrecisionTwoDigits.isChecked()),
                this.getPreferences(Context.MODE_PRIVATE));

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_COLOR_THEME, "SETTINGS_THEME",
                this.getPreferences(Context.MODE_PRIVATE));

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_KEYPAD_LAYOUT, "SETTINGS_KEYPAD_LAYOUT",
                this.getPreferences(Context.MODE_PRIVATE));

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_DISPLAY_FORMAT, "SETTINGS_DISPLAY_FORMAT",
                this.getPreferences(Context.MODE_PRIVATE));
    }
}
