package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class CalculatorSettingsActivity extends AppCompatActivity {

    private CheckBox settingsVibrateOnTouch;
    private CheckBox settingsPrecisionTwoDigits;
    private CheckBox settingsCommaAfterThousand;
//    private CheckBox settingsKeyPadLayout;
//    private CheckBox settingsTheme;
//    private CheckBox settingsDisplayFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_settings);

        settingsVibrateOnTouch = (CheckBox) findViewById(R.id.settingsVibrateOnTouch);
        settingsPrecisionTwoDigits = (CheckBox) findViewById(R.id.settingsPrecisionTwoDigits);
        settingsCommaAfterThousand = (CheckBox) findViewById(R.id.settingsCommaAfterThousand);

        settingsPrecisionTwoDigits.setChecked(
                Boolean.valueOf(
                        Utils.getValueFromSharedPreference(Utils.SETTINGS_PRECISSION_TWO_DIGIT)));

        settingsVibrateOnTouch.setChecked(
                Boolean.valueOf(
                        Utils.getValueFromSharedPreference(Utils.SETTINGS_VIBRATE_ON_TOUCH)));

        settingsCommaAfterThousand.setChecked(
                Boolean.valueOf(
                        Utils.getValueFromSharedPreference(Utils.SETTINGS_COMMA_AFTER_THOUSAND)));


//        private CheckBox settingsKeyPadLayout;
//        private CheckBox settingsTheme;
//        private CheckBox settingsDisplayFormat;


    }


    public void setCalculatorSettings(View view) {

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_VIBRATE_ON_TOUCH,
                Boolean.toString(settingsVibrateOnTouch.isChecked()));

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_PRECISSION_TWO_DIGIT,
                Boolean.toString(settingsPrecisionTwoDigits.isChecked()));

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_COMMA_AFTER_THOUSAND,
                Boolean.toString(settingsPrecisionTwoDigits.isChecked()));




        Utils.putStringInSharedPreference(
                Utils.SETTINGS_COLOR_THEME, "SETTINGS_THEME");

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_KEYPAD_LAYOUT, "SETTINGS_KEYPAD_LAYOUT");

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_DISPLAY_FORMAT, "SETTINGS_DISPLAY_FORMAT");
    }


    public void openThemeSettings(View v) {
//        toast.setText("To be Implemented");
//        (Toast.makeText(this, "To be Implemented", Toast.LENGTH_SHORT)).show();
//        toast.show();
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();

        if (v.getId() == R.id.settingsTheme) {
            inflater.inflate(R.menu.calculator_theme, popup.getMenu());
        } else if (v.getId() == R.id.settingsFontType) {
            inflater.inflate(R.menu.calculator_font_style, popup.getMenu());
        } else if (v.getId() == R.id.settingsKeypadLayout) {
            inflater.inflate(R.menu.calculator_keypad_style, popup.getMenu());
        } else if (v.getId() == R.id.settingsDisplayFormat) {
            inflater.inflate(R.menu.calculator_display_format, popup.getMenu());
        } else {
            return;
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Material_Golden:
                        System.out.println("clicked popup " + item.getTitle());
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.amaya_favourite_color));
                        return true;
                    case R.id.Material_Orange:
                        System.out.println("clicked popup " + item.getTitle());
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_orange));
                        return true;
                    case R.id.Material_Blue:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_blue));
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Material_Green:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_green));
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Material_Pink:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_pink));
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Material_Red:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_red));
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Font_Thin:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
                                Integer.toString(R.id.Font_Thin));
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Font_Normal:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
                                Integer.toString(R.id.Font_Normal));
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Font_Bold:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
                                Integer.toString(R.id.Font_Bold));
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    default:
                        System.out.println("Unknown clicked popup" + item.toString());
                        return true;
                }
            }
        });
        popup.show();
    }

}
