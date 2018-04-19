package whizkid.amaya.calculatorforlaunch;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CalculatorSettingsActivity extends AppCompatActivity {

    private MenuItem menuItem = null;
    private String menuGettingCreated = null;
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
        settingsAnimation = (CheckBox) findViewById(R.id.settingsAnimation);

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

            String[] themes = getResources().getStringArray(R.array.pankaj_themes);
            String[] fonts = getResources().getStringArray(R.array.pankaj_font_type);

            //names to be displayed for themes
            String strText = getResources().getString(R.string.ColorTheme) + "\n";

            //name of the color selected setting
            String colour =
                    themes[Integer.parseInt(Utils.getValueFromSharedPreference(Utils.THEME_ITEM_SELECTED_FROM_DIALOG,
                            Utils.DEFAULT_THEME_FROM_DIALOG))];

            createDifferentFonts((TextView) findViewById(R.id.settingsThemeTextView), strText + colour);

            //names to be displayed for fonts
            strText = getResources().getString(R.string.FontType) + "\n";

            //name of the current font selected setting
            colour =
                    fonts[Integer.parseInt(Utils.getValueFromSharedPreference(Utils.FONT_ITEM_SELECTED_FROM_DIALOG,
                            Utils.DEFAULT_FONT_FROM_DIALOG))];

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

//        Utils.putStringInSharedPreference(
//                Utils.SETTINGS_COLOR_THEME,
//                Utils.getValueFromSharedPreference(Utils.SETTINGS_COLOR_THEME, Utils.EMPTY_STRING));
//
//        Utils.putStringInSharedPreference(
//                Utils.SETTINGS_KEYPAD_LAYOUT,
//                Utils.getValueFromSharedPreference(Utils.SETTINGS_KEYPAD_LAYOUT, Utils.EMPTY_STRING));
//
//        Utils.putStringInSharedPreference(
//                Utils.SETTINGS_DISPLAY_FORMAT,
//                Utils.getValueFromSharedPreference(Utils.SETTINGS_DISPLAY_FORMAT, Utils.EMPTY_STRING));
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


    public void openThemeSettings_Dialog(View v, final int title, final int arrayResource,  final String preferenceString) {

        final ArrayList mSelectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle(title)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setSingleChoiceItems(arrayResource, Integer.parseInt(Utils.getValueFromSharedPreference(
                        preferenceString, "-1")),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("which " + which);

                                //store the value selected in the prefrence
                                Utils.putStringInSharedPreference(
                                        preferenceString,
                                        Integer.toString(which));

                                //set the colourful values
                                setTextvaluesColourful();
                                
                                dialog.cancel();
                            }
                        })
                // Set the action buttons
//                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked OK, so save the mSelectedItems results somewhere
//                        // or return them to the component that opened the dialog
//                    }
//                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        builder.show();
        //.create();
    }


    public void openThemeSettings(View v) {


        if (v.getId() == R.id.settingsThemeTextView) {

            openThemeSettings_Dialog(v, R.string.pick_theme,
                    R.array.pankaj_themes, Utils.THEME_ITEM_SELECTED_FROM_DIALOG);

        } else if (v.getId() == R.id.settingsFontTypeTextView) {

            openThemeSettings_Dialog(v, R.string.pick_font,
                    R.array.pankaj_font_type, Utils.FONT_ITEM_SELECTED_FROM_DIALOG);

        } else if (v.getId() == R.id.settingsKeypadLayout) {
            openThemeSettings_Dialog(v, R.string.pick_keypad,
                    R.array.pankaj_keypad_type, Utils.KEYPAD_ITEM_SELECTED_FROM_DIALOG);

        } else {
            return;
        }

        if (true) {
            return;
        }


//        toast.setText("To be Implemented");
//        (Toast.makeText(this, "To be Implemented", Toast.LENGTH_SHORT)).show();
//        toast.show();
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.vibrationTextView), Gravity.CENTER_HORIZONTAL);
        MenuInflater inflater = popupMenu.getMenuInflater();
//
//        if (v.getId() == R.id.settingsThemeTextView) {
//
//            inflater.inflate(R.menu.calculator_theme, popupMenu.getMenu());
//            menuGettingCreated = Utils.THEME_ITEM_SELECTED;
//
//        } else if (v.getId() == R.id.settingsFontTypeTextView) {
//
//            popupMenu = new PopupMenu(this, findViewById(R.id.settingsThemeTextView), Gravity.LEFT);
//            inflater = popupMenu.getMenuInflater();
//
//            inflater.inflate(R.menu.calculator_font_style, popupMenu.getMenu());
//            menuGettingCreated = Utils.FONT_ITEM_SELECTED;
//
//        } else if (v.getId() == R.id.settingsKeypadLayout) {
//            inflater.inflate(R.menu.calculator_keypad_style, popupMenu.getMenu());
//            menuGettingCreated = Utils.KEYPAD_ITEM_SELECTED;
//
//        } else {
//            return;
//        }
//
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                menuItem = item;
//
//                switch (item.getItemId()) {
//                    case R.id.amaya_favourite_color_golden:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.amaya_favourite_color_golden));
//                        break;
//                    case R.id.pankaj_theme_dark_black:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_black));
//                        break;
//                    case R.id.pankaj_theme_dark_blue:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_blue));
//                        break;
//                    case R.id.pankaj_theme_dark_light_blue:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_light_blue));
//                        break;
//                    case R.id.pankaj_theme_dark_blue_grey:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_blue_grey));
//                        break;
//                    case R.id.pankaj_theme_dark_cyan:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_cyan));
//                        break;
//                    case R.id.pankaj_theme_dark_deep_brown:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_deep_brown));
//                        break;
//                    case R.id.pankaj_theme_dark_green:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_green));
//                        break;
//                    case R.id.pankaj_theme_dark_grey:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_grey));
//                        break;
//                    case R.id.pankaj_theme_dark_indigo:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_indigo));
//                        break;
//                    case R.id.pankaj_theme_dark_lime:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_lime));
//                        break;
//                    case R.id.pankaj_theme_dark_orange:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_orange));
//                        break;
//                    case R.id.pankaj_theme_dark_deep_orange:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_deep_brown));
//                        break;
//                    case R.id.pankaj_theme_dark_pink:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_pink));
//                        break;
//                    case R.id.pankaj_theme_dark_purple:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_purple));
//                        break;
//                    case R.id.pankaj_theme_dark__dark_purple:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_dark_purple));
//                        break;
//                    case R.id.pankaj_theme_dark_teal:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_teal));
//                        break;
//                    case R.id.pankaj_theme_dark_yellow:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                                Integer.toString(R.color.pankaj_theme_dark_yellow));
//                        break;
//
//                    case R.id.Font_Thin:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
//                                Integer.toString(R.id.Font_Thin));
//                        System.out.println("clicked popupMenu " + item.getTitle());
//                        break;
////                        return true;
//                    case R.id.Font_Normal:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
//                                Integer.toString(R.id.Font_Normal));
//                        System.out.println("clicked popupMenu " + item.getTitle());
//                        break;
////                        return true;
//                    case R.id.Font_Bold:
//                        Utils.putStringInSharedPreference(Utils.SETTINGS_FONT_STYLE,
//                                Integer.toString(R.id.Font_Bold));
//                        System.out.println("clicked popupMenu " + item.getTitle());
//                        break;
////                        return true;
//                    default:
//                        System.out.println("Unknown clicked popupMenu" + item.toString());
//                        break;
////                        return true;
//                }
//
//                if (Utils.THEME_ITEM_SELECTED.equals(menuGettingCreated)) {
//                    Utils.putStringInSharedPreference(Utils.THEME_ITEM_SELECTED,
//                            Integer.toString(menuItem.getItemId()));
//                }
//
//                if (Utils.FONT_ITEM_SELECTED.equals(menuGettingCreated)) {
//                    Utils.putStringInSharedPreference(Utils.FONT_ITEM_SELECTED,
//                            Integer.toString(menuItem.getItemId()));
//                }
//
//                if (Utils.KEYPAD_ITEM_SELECTED.equals(menuGettingCreated)) {
//                    Utils.putStringInSharedPreference(Utils.KEYPAD_ITEM_SELECTED,
//                            Integer.toString(menuItem.getItemId()));
//                }
////
////                To change Themes dynamically
////                if(!Utils.TRUE.equals(
////                        Utils.getValueFromSharedPreference(Utils.PINK_THEME, Utils.EMPTY_STRING))) {
////                    setTheme(R.style.Theme_PINK_COLOR);
////                    Utils.putStringInSharedPreference(Utils.PINK_THEME,Utils.TRUE);
////                }
////                else {
////                    setTheme(R.style.Theme_LIGHT_BLUE);
////                    Utils.putStringInSharedPreference(Utils.PINK_THEME,Utils.FALSE);
////                }
////
////                MainActivity.recreateMe = true;
//
////                setContentView(R.layout.activity_calculator_settings);
//
//                System.out.println("before " + item.isChecked());
//
////                if (item.isCheckable()) {
////                    item.setChecked(true);
////                }
////                System.out.println("after " + item.isChecked());
//
//                setTextvaluesColourful();
//
////                if (menuItem != null) {
////                    menuItem.setChecked(true);
////                    menuItem.setTitle("Hello Me");
////                }
//
//                return false;
//            }
//        });
//
////        if (!Utils.EMPTY_STRING.equals(
////                Utils.getValueFromSharedPreference(Utils.SETTINGS_COLOR_THEME,""))) {
////            (popupMenu.getMenu().findItem(
////                    Integer.parseInt(
////                    Utils.getValueFromSharedPreference(Utils.SETTINGS_COLOR_THEME,"")))).setChecked(true);
////        }
//
//        if (Utils.THEME_ITEM_SELECTED.equals(menuGettingCreated)) {
//            if (!Utils.EMPTY_STRING.equals(
//                    Utils.getValueFromSharedPreference(
//                            Utils.THEME_ITEM_SELECTED, Utils.EMPTY_STRING))) {
//                (popupMenu.getMenu().findItem(Integer.parseInt(Utils.getValueFromSharedPreference(
//                        Utils.THEME_ITEM_SELECTED, Utils.EMPTY_STRING)))).setChecked(true);
//            }
//        }
//
//        if (Utils.FONT_ITEM_SELECTED.equals(menuGettingCreated)) {
//            if (!Utils.EMPTY_STRING.equals(
//                    Utils.getValueFromSharedPreference(
//                            Utils.FONT_ITEM_SELECTED, Utils.EMPTY_STRING))) {
//                (popupMenu.getMenu().findItem(Integer.parseInt(Utils.getValueFromSharedPreference(
//                        Utils.FONT_ITEM_SELECTED, Utils.EMPTY_STRING)))).setChecked(true);
//            }
//        }
//
//
//        if (Utils.KEYPAD_ITEM_SELECTED.equals(menuGettingCreated)) {
//            if (!Utils.EMPTY_STRING.equals(
//                    Utils.getValueFromSharedPreference(
//                            Utils.KEYPAD_ITEM_SELECTED, Utils.EMPTY_STRING))) {
//                (popupMenu.getMenu().findItem(Integer.parseInt(Utils.getValueFromSharedPreference(
//                        Utils.KEYPAD_ITEM_SELECTED, Utils.EMPTY_STRING)))).setChecked(true);
//            }
//        }
//
//
//        popupMenu.show();
//
////        if (menuItem != null) {
////            menuItem.setChecked(true);
////        }

    }

}
