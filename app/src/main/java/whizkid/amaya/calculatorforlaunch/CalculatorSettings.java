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



    public void openThemeSettings(View v) {
//        toast.setText("To be Implemented");
//        (Toast.makeText(this, "To be Implemented", Toast.LENGTH_SHORT)).show();
//        toast.show();
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();

        if(v.getId() == R.id.settingsTheme) {
            inflater.inflate(R.menu.calculator_theme, popup.getMenu());
        }
        else if(v.getId() == R.id.settingsFontType) {
            inflater.inflate(R.menu.calculator_font_style, popup.getMenu());
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Material_Orange:
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Material_Blue:
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Material_Green:
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Material_Pink:
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Material_Red:
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Font_Thin:
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Font_Normal:
                        System.out.println("clicked popup " + item.getTitle());
                        return true;
                    case R.id.Font_Bold:
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
