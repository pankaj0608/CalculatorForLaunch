package whizkid.amaya.calculatorforlaunch;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class CalculatorSettingsActivity extends AppCompatActivity {

    private CheckBox settingsVibrateOnTouch;
    private CheckBox settingsPrecisionTwoDigits;
    private CheckBox settingsCommaAfterThousand;
    private CheckBox settingsAnimation;
    private TextView settingsThemeTextView;

    final Typeface sansSeifNormal_Thin = Typeface.create("sans-serif-thin", Typeface.NORMAL);
    final Typeface sansSeifNormal_Normal = Typeface.create("sans-serif-light", Typeface.NORMAL);
    final Typeface sansSeifNormal_Bold = Typeface.create("sans-serif-light", Typeface.BOLD);

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
        settingsAnimation =  (CheckBox) findViewById(R.id.settingsAnimation);

        settingsThemeTextView = (TextView) findViewById(R.id.settingsThemeTextView);

        settingsPrecisionTwoDigits.setChecked(
                Boolean.valueOf(
                        Utils.getValueFromSharedPreference(Utils.SETTINGS_PRECISSION_TWO_DIGIT, Utils.FALSE)));

        settingsVibrateOnTouch.setChecked(
                Boolean.valueOf(
                        Utils.getValueFromSharedPreference(Utils.SETTINGS_VIBRATE_ON_TOUCH, Utils.FALSE)));

        settingsCommaAfterThousand.setChecked(
                Boolean.valueOf(
                        Utils.getValueFromSharedPreference(Utils.SETTINGS_COMMA_AFTER_THOUSAND, Utils.FALSE)));

        settingsAnimation.setChecked(
                Boolean.valueOf(
                        Utils.getValueFromSharedPreference(Utils.SETTINGS_ANIMATION, Utils.FALSE)));


        setTextvaluesColourful();

//        private CheckBox settingsKeyPadLayout;
//        private CheckBox settingsTheme;
//        private CheckBox settingsDisplayFormat;


    }

    private void setTextvaluesColourful() {
        try {
            String strText = "Theme\n";
            String colour =
                    (getResources().getResourceName(
                            Integer.parseInt(
                                    Utils.getValueFromSharedPreference(Utils.SETTINGS_COLOR_THEME, Utils.EMPTY_STRING))));

            colour = colour.substring(colour.lastIndexOf("_") + 1);
            createDifferentFonts((TextView) findViewById(R.id.settingsThemeTextView), strText + colour);


            strText = "Font Type\n";
            colour =
                    (getResources().getResourceName(
                            Integer.parseInt(
                                    Utils.getValueFromSharedPreference(Utils.SETTINGS_FONT_STYLE, Utils.EMPTY_STRING))));

            colour = colour.substring(colour.lastIndexOf("/") + 1).toLowerCase();
            createDifferentFonts((TextView) findViewById(R.id.settingsFontTypeTextView), strText + colour);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                Boolean.toString(settingsCommaAfterThousand.isChecked()));

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_ANIMATION,
                Boolean.toString(settingsAnimation.isChecked()));

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_COLOR_THEME, "SETTINGS_THEME");

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_KEYPAD_LAYOUT, "SETTINGS_KEYPAD_LAYOUT");

        Utils.putStringInSharedPreference(
                Utils.SETTINGS_DISPLAY_FORMAT, "SETTINGS_DISPLAY_FORMAT");
    }

    private void createDifferentFonts(TextView textView, String strText) {

//        TextView txt = (TextView) findViewById(R.id.custom_fonts);
//        txt.setTextSize(30);
//        Typeface font = Typeface.createFromAsset(getAssets(), "Akshar.ttf");
//        Typeface font2 = Typeface.createFromAsset(getAssets(), "bangla.ttf");
//        SpannableStringBuilder SS = new SpannableStringBuilder("আমারநல்வரவு");

        SpannableStringBuilder SS = new SpannableStringBuilder(strText);

        SS.setSpan(new CustomTypefaceSpan("", sansSeifNormal_Normal),
                0, strText.indexOf("\n"),
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        SS.setSpan(new CustomTypefaceSpan("", sansSeifNormal_Thin),
                strText.indexOf("\n"), strText.length(),
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(SS);
    }


    public void openThemeSettings(View v) {
//        toast.setText("To be Implemented");
//        (Toast.makeText(this, "To be Implemented", Toast.LENGTH_SHORT)).show();
//        toast.show();
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();

        if (v.getId() == R.id.settingsThemeTextView) {
            inflater.inflate(R.menu.calculator_theme, popup.getMenu());
        } else if (v.getId() == R.id.settingsFontTypeTextView) {
            inflater.inflate(R.menu.calculator_font_style, popup.getMenu());
        } else if (v.getId() == R.id.settingsKeypadLayout) {
            inflater.inflate(R.menu.calculator_keypad_style, popup.getMenu());
        } else {
            return;
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item. getItemId()) {
                    case R.id.Material_Golden:
                        System.out.println("clicked popup " + item.getTitle());
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.amaya_favourite_color_golden));
                        break;
//                        return true;

                    case R.id.Material_Orange:
                        System.out.println("clicked popup " + item.getTitle());
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_orange));
                        break;
//                        return true;

                    case R.id.Material_Blue:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_blue));
                        System.out.println("clicked popup " + item.getTitle());
                        break;
//                        return true;
                    case R.id.Material_Green:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_green));
                        System.out.println("clicked popup " + item.getTitle());
                        break;
//                        return true;
                    case R.id.Material_Pink:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_pink));
                        System.out.println("clicked popup " + item.getTitle());
                        break;
//                        return true;
                    case R.id.Material_Red:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
                                Integer.toString(R.color.pankaj_dark_red));
                        System.out.println("clicked popup " + item.getTitle());
                        break;
//                        return true;
                    case R.id.Font_Thin:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
                                Integer.toString(R.id.Font_Thin));
                        System.out.println("clicked popup " + item.getTitle());
                        break;
//                        return true;
                    case R.id.Font_Normal:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
                                Integer.toString(R.id.Font_Normal));
                        System.out.println("clicked popup " + item.getTitle());
                        break;
//                        return true;
                    case R.id.Font_Bold:
                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
                                Integer.toString(R.id.Font_Bold));
                        System.out.println("clicked popup " + item.getTitle());
                        break;
//                        return true;
                    default:
                        System.out.println("Unknown clicked popup" + item.toString());
                        break;
//                        return true;
                }

                setTextvaluesColourful();
                return true;
            }
        });

        popup.show();
    }

}
