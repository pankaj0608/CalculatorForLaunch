package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends AppCompatActivity {

    /*
    Steps for commit
git add --all
git commit -m "code changed"
git push https://pakr0608@bitbucket.org/pakr0608/calculatorpro.git
CCQXJDyGXX5RQKCnE2w9

del .git\index
git reset


---------
echo "# CalculatorPro" >> README.md
git init
git add README.md
git commit -m "first commit"
git remote add origin https://github.com/pankaj0608/CalculatorPro.git
git push https://github.com/pankaj0608/CalculatorPro.git

git push -u origin master
---------

https://code.tutsplus.com/tutorials/android-user-interface-design-creating-a-numeric-keypad-with-gridlayout--mobile-8677
 https://github.com/intuit/sdp/blob/master/sdp-android/src/main/res/layout/sdp_example.xml

     Sliding Menu

     https://developer.android.com/training/implementing-navigation/nav-drawer.html


https://guides.codepath.com/android/developing-custom-themes

http://www.vogella.com/tutorials/AndroidStylesThemes/article.html

https://android.jlelse.eu/android-developers-we-ve-been-using-themes-all-wrong-eed7755da586

https://gist.github.com/ishitcno1/9408188 - dialog box postion

     */

    public static boolean recreateMe = false;
    private DrawerLayout mDrawerLayout;
    AppCompatTextView editTextResult;
    EditText editTextEquation;
    EditText editTextMemory;
    //    final Typeface sansSeifNormal = Typeface.create("sans-serif-light", Typeface.NORMAL);
//    Typeface face = Typeface.createFromAsset(getAssets(),"fonts/opansans.ttfe");

    final Typeface sansSeifCondensed = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    final Typeface sansSeifMonospace = Typeface.create("arial", Typeface.NORMAL);
    final Typeface sansSeifTest = Typeface.create("sans-serif-thin", Typeface.BOLD);

//    Typeface sansSeifNormal_Normal = Typeface.createFromAsset(getAssets(), "fonts/droid_sans.ttf");

    final Typeface sansSeifNormal_Thin = Typeface.create("sans-serif-thin", Typeface.NORMAL);
    final Typeface sansSeifNormal_Normal = Typeface.create("sans-serif-light", Typeface.NORMAL);
    final Typeface sansSeifNormal_Bold = Typeface.create("sans-serif-light", Typeface.BOLD);


    String result;
    String operand1;
    String operand2;
    String operator;
    boolean evaluationDone = false;
//    boolean clearDone = false;

    int mySelectionValueStart = 0;
    int mySelectionValueEnd = 0;
    int mySelectionAdjustment = 0;

    private CalculatorResources calculatorResources;

    int[] resourcesButton =
            {R.id.buttonSignChange,
                    R.id.buttonDecimal,
                    R.id.button0,
                    R.id.button00,
                    R.id.button1,
                    R.id.button2,
                    R.id.button3,
                    R.id.button4,
                    R.id.button5,
                    R.id.button6,
                    R.id.button7,
                    R.id.button8,
                    R.id.button9,
                    R.id.buttonMemoryClear,
                    R.id.buttonMemoryPlus,
                    R.id.buttonMemoryMinus,
                    R.id.buttonMemoryRead,
                    R.id.buttonInverse,
                    R.id.buttonPercentage,
                    R.id.buttonToPowerOf,
                    R.id.buttonAllClear,
                    R.id.buttonDivide,
                    R.id.buttonMultiply,
                    R.id.buttonSubtract,
                    R.id.buttonAdd,
                    R.id.buttonEquals,
                    R.id.buttonSettings};

    int[] operatorButtons =
            {
//                    R.id.buttonBack,
                    R.id.buttonMemoryClear,
                    R.id.buttonMemoryPlus,
                    R.id.buttonMemoryMinus,
                    R.id.buttonMemoryRead,
                    R.id.buttonInverse,
                    R.id.buttonPercentage,
                    R.id.buttonAllClear,
                    R.id.buttonDivide,
                    R.id.buttonMultiply,
                    R.id.buttonSubtract,
                    R.id.buttonAdd,
                    R.id.buttonToPowerOf,
                    R.id.buttonEquals
            };

    int[] operatorButtonsNoFill =
            {
                    R.id.buttonBracketStart,
                    R.id.buttonBracketEnd
            };

    int[] imageButtons =
            {
//                    R.id.buttonUndolastEval,
                    R.id.buttonBack
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
//        super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.calculator_menu, menu);
//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.Material_Orange:
//                System.out.println("clicked " + item.getTitle());
//                return true;
//            case R.id.Material_Blue:
//                System.out.println("clicked " + item.getTitle());
//                return true;
//            case R.id.Material_Green:
//                System.out.println("clicked " + item.getTitle());
//                return true;
//            case R.id.Material_Pink:
//                System.out.println("clicked " + item.getTitle());
//                return true;
//            case R.id.Material_Red:
//                System.out.println("clicked " + item.getTitle());
//                return true;
//            default:
//                System.out.println("MyTheme clicked" + item.toString());
//                return super.onOptionsItemSelected(item);
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        recreateMe = true;

        //set the context and vibrator in the Utils
        Utils.setBaseEssentials(getApplicationContext(),
                getApplicationContext().getSharedPreferences(
                        Utils.MY_SHARED_PREFERENCE, Context.MODE_PRIVATE),
                (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE));

        //set the default theme
        if (Utils.EMPTY_STRING.equals(
                Utils.getValueFromSharedPreference(Utils.THEME_ITEM_SELECTED_FROM_DIALOG, Utils.EMPTY_STRING))) {
            Utils.putStringInSharedPreference(Utils.THEME_ITEM_SELECTED_FROM_DIALOG,
                    Utils.DEFAULT_THEME_FROM_DIALOG);
        }

        //set the default font
        if (Utils.EMPTY_STRING.equals(
                Utils.getValueFromSharedPreference(Utils.FONT_ITEM_SELECTED_FROM_DIALOG, Utils.EMPTY_STRING))) {
            Utils.putStringInSharedPreference(Utils.FONT_ITEM_SELECTED_FROM_DIALOG,
                    Utils.DEFAULT_FONT_FROM_DIALOG);
        }


//        if(Utils.EMPTY_STRING.equals(
//                Utils.getValueFromSharedPreference(Utils.SETTINGS_COLOR_THEME, Utils.EMPTY_STRING))) {
//            Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                    Integer.toString(Utils.DEFAULT_THEME));
//
//            Utils.putStringInSharedPreference(Utils.THEME_ITEM_SELECTED,
//                    Integer.toString(Utils.DEFAULT_THEME_ITEM));
//        }

//        this.setTheme(R.style.Theme_GOLDEN_COLOR);

//        setPinkTheme();

        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        Utils.putStringInSharedPreference(Utils.LAST_EQUATION_FOR_UNDO, Utils.EMPTY_STRING);

        setContentView(R.layout.mylayout_phone_standard);

        resetOperators();

        editTextResult = (AppCompatTextView) findViewById(R.id.editTextResult);
        editTextEquation = (EditText) findViewById(R.id.editTextEquation);
        editTextMemory = (EditText) findViewById(R.id.editTextMemory);


//        String preferenceColour = Utils.getValueFromSharedPreference(Utils.SETTINGS_COLOR_THEME, Utils.EMPTY_STRING);

//        if(Utils.EMPTY_STRING.equals(preferenceColour)) {
//            editTextMemory.setText("V : empty :");
//        }
//        else {
//
//            try {
//                editTextMemory.setText("V : "
//                        +  getResources().getResourceName(Integer.parseInt(preferenceColour)).
//                        substring(getResources().getResourceName(Integer.parseInt(preferenceColour)).lastIndexOf("/"))
//                        + " :");
//            }
//            catch (Exception e) {
//                editTextMemory.setText("V : " + e.getMessage());
//            }
//
//        }

//        if (Utils.EMPTY_STRING.equals(preferenceColour.equals(Utils.SETTINGS_COLOR_THEME))) {
//            Utils.putStringInSharedPreference(Utils.SETTINGS_COLOR_THEME,
//                    Integer.toString(R.color.amaya_favourite_color_golden));
//        }

        resetThemeColoursAndFonts();

//         setContentView(R.layout.mylayout_phone_with_drawer);


        //For sliding menu starts

        //For sliding menu ends
        //Pankaj Code Starts

        editTextResult.setTextIsSelectable(true);

        //TODO remove this option for API15
        editTextEquation.setShowSoftInputOnFocus(false);

        TextViewCompat.setAutoSizeTextTypeWithDefaults(editTextResult, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);


        editTextEquation.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        //Add listener so that we can chek the validity of the equation
        editTextEquation.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mySelectionValueStart = editTextEquation.getSelectionStart();
                mySelectionValueEnd = editTextEquation.getSelectionEnd();
                mySelectionAdjustment = 0;
                if (after > count) {
                    mySelectionAdjustment = 1;
                } else {
                    mySelectionAdjustment = -1;
                }
                System.out.println("beforeTextChanged " +
                        s.toString()
                        + " : " + start
                        + " : " + count
                        + " : " + after
                        + " : " + editTextEquation.getSelectionStart()
                        + " : " + editTextEquation.getSelectionEnd());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                System.out.println("onTextChanged " +
                        s.toString()
                        + " : " + start
                        + " : " + before
                        + " : " + count
                        + " : " + editTextEquation.getSelectionStart()
                        + " : " + editTextEquation.getSelectionEnd());

            }

            @Override
            public void afterTextChanged(Editable s) {

                System.out.println("afterTextChanged " +
                        s.toString()
                        + " : " + editTextEquation.getSelectionStart()
                        + " : " + editTextEquation.getSelectionEnd());

                String result = "";

//                if (!clearDone) {

                result = Utils.evalMe(s.toString());

                //colour only if evaluation has been done and not on All Clear
                if (evaluationDone && !editTextEquation.getText().toString().equals("0")) {
                    editTextResult.setText(Utils.colourMyText(result, Utils.getPreferenceColor()));
//                    editTextResult.setText(Utils.colourMyText(result, Utils.getPreferenceColorForRandomLayout()));
                } else {
                    editTextResult.setText(result);

                }
//                    clearDone = false;
//                }
                //editTextEquation.setSelection(editTextEquation.getText().length());

                //minimum valid value of selctionStart is 0
                if ((mySelectionValueStart + mySelectionAdjustment) <= s.toString().length()
                        && (mySelectionValueStart + mySelectionAdjustment >= 0)) {
                    editTextEquation.setSelection(mySelectionValueStart + mySelectionAdjustment);
                } else {
                    if (editTextEquation.isFocused()) {
                        editTextEquation.setSelection(mySelectionValueStart);
                    } else {
                        editTextEquation.setSelection(0);
                    }
                }
            }
        });

        if (Utils.getValueFromSharedPreference(
                Utils.LAST_EQUATION_FOR_THEME_CHANGE, Utils.EMPTY_STRING).length() > 0) {
            editTextEquation.setText(Utils.getValueFromSharedPreference(
                    Utils.LAST_EQUATION_FOR_THEME_CHANGE, Utils.EMPTY_STRING));

            Utils.putStringInSharedPreference(Utils.LAST_EQUATION_FOR_THEME_CHANGE, Utils.EMPTY_STRING);
        } else {
            editTextEquation.setText("0");
        }


        //get the memory from the Share Preference
        String storedMemory = Utils.getValueFromSharedPreference(Utils.MEMORY_SAVED_VALE, Utils.EMPTY_STRING);

        if (storedMemory.length() > 0) {
            editTextMemory.setText(Utils.MEMORY_PREFIX + storedMemory);
        }


        //Set the font type for the fields
        editTextResult.setTypeface(sansSeifTest);
        editTextEquation.setTypeface(sansSeifMonospace);
        editTextMemory.setTypeface(sansSeifCondensed);

        for (int i = 0; i < resourcesButton.length; i++) {
            try {
                System.out.println("resourcesButton[i] " + i + " : " + resourcesButton[i]);
                Button button = ((Button) findViewById(resourcesButton[i]));
                button.setTypeface(sansSeifNormal_Normal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
//
//    public void openMymenu(View v) {
////        toast.setText("To be Implemented");
////        (Toast.makeText(this, "To be Implemented", Toast.LENGTH_SHORT)).show();
////        toast.show();
//        PopupMenu popup = new PopupMenu(this, v);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.calculator_menu, popup.getMenu());
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.Material_Orange:
//                        System.out.println("clicked popup " + item.getTitle());
//                        return true;
//                    case R.id.Material_Blue:
//                        System.out.println("clicked popup " + item.getTitle());
//                        return true;
//                    case R.id.Material_Green:
//                        System.out.println("clicked popup " + item.getTitle());
//                        return true;
//                    case R.id.Material_Pink:
//                        System.out.println("clicked popup " + item.getTitle());
//                        return true;
//                    case R.id.Material_Red:
//                        System.out.println("clicked popup " + item.getTitle());
//                        return true;
//                    default:
//                        System.out.println("Unknown clicked popup" + item.toString());
//                        return true;
//                }
//            }
//        });
//        popup.show();
//    }


    private void setPinkTheme() {
        if (Utils.TRUE.equals(
                Utils.getValueFromSharedPreference(Utils.PINK_THEME, Utils.EMPTY_STRING))
                ||
                Utils.EMPTY_STRING.equals(
                        Utils.getValueFromSharedPreference(Utils.PINK_THEME, Utils.EMPTY_STRING))) {
            setTheme(R.style.Theme_PINK_COLOR);
        } else {
            setTheme(R.style.Theme_LIGHT_BLUE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Utils.setBaseEssentials(getApplicationContext(),
                getApplicationContext().getSharedPreferences(
                        Utils.MY_SHARED_PREFERENCE, Context.MODE_PRIVATE),
                (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE));

//        setPinkTheme();

        System.out.println("getTheme " + getTheme());


        resetThemeColoursAndFonts();

        editTextEquation.setText(Utils.correctEquation(editTextEquation.getText().toString()));

        if (recreateMe) {
            recreateMe = false;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    private void resetThemeColoursAndFonts() {

        int preferenceColour = Utils.getPreferenceColor();

        int preferenceFont = Utils.getPreferenceFont();

        Utils.putStringInSharedPreference(
                Utils.THEME_COLOUR_FOR_LAYOUT_FROM_RANDOM_EFFECT,
                Integer.toString(preferenceColour));

//        if (preferenceFont != null && preferenceFont.length() > 0) {
        for (int i = 0; i < resourcesButton.length; i++) {
            try {
                System.out.println("resourcesButton[i] " + i + " : " + resourcesButton[i]);
                Button button = ((Button) findViewById(resourcesButton[i]));
                if (preferenceFont == R.id.Font_Thin) {
                    button.setTypeface(sansSeifNormal_Thin);
                } else if (preferenceFont == R.id.Font_Normal) {
                    button.setTypeface(sansSeifNormal_Normal);

                } else if (preferenceFont == R.id.Font_Bold) {
                    button.setTypeface(sansSeifNormal_Bold);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        }

//        if (preferenceColour != null && preferenceColour.length() > 0) {
        for (int i = 0; i < operatorButtons.length; i++) {
            try {
                Button button = findViewById(operatorButtons[i]);
                button.setBackgroundColor(getResources().getColor(preferenceColour));
//                    button.setTextColor(getResources().getColor(Integer.parseInt(preferenceColour)));
                button.setTextColor(getResources().getColor(R.color.pankaj_pad_button_text_color_white));

                button.setTypeface(sansSeifNormal_Normal);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        }

        if (!Utils.EMPTY_STRING.equals(preferenceColour)) {
            for (int i = 0; i < operatorButtonsNoFill.length; i++) {
                try {
                    Button button = findViewById(operatorButtonsNoFill[i]);
//                    button.setBackgroundColor(getResources().getColor(Integer.parseInt(preferenceColour)));
                    button.setTextColor(getResources().getColor(preferenceColour));
//                    button.setTextColor(getResources().getColor(R.color.pankaj_pad_button_text_color_white));

                    button.setTypeface(sansSeifNormal_Normal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (!Utils.EMPTY_STRING.equals(preferenceColour)) {
            for (int i = 0; i < imageButtons.length; i++) {
                try {
                    ImageButton button = findViewById(imageButtons[i]);
                    button.setBackgroundColor(getResources().getColor(preferenceColour));
//                    button.setColorFilter(getResources().getColor(Integer.parseInt(preferenceColour)));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        ((TextView) findViewById(R.id.slidingMenuButton)).setTextColor(getResources().getColor(preferenceColour));

    }

    public void saveInMemory(View view) {

        Utils.vibrateMe();

        String memoryCurrent = editTextMemory.getText().toString();
        String resultCurrent = Utils.evalMe(editTextResult.getText().toString());

        if (memoryCurrent.startsWith(Utils.MEMORY_PREFIX)) {
            memoryCurrent = memoryCurrent.substring(Utils.MEMORY_PREFIX.length());
        }

        if (memoryCurrent == null || memoryCurrent.equals("")) {
            memoryCurrent = "";
        }


        if (view.getTag().equals(Utils.MEMORY_ADD)) {
            memoryCurrent = Utils.evalMe(
                    memoryCurrent
                            + "+"
                            + resultCurrent);
            editTextMemory.setText(Utils.MEMORY_PREFIX + memoryCurrent);

        } else if (view.getTag().equals(Utils.MEMORY_SUBTRACT)) {
            memoryCurrent = Utils.evalMe(
                    memoryCurrent
                            + "-"
                            + resultCurrent);
            editTextMemory.setText(Utils.MEMORY_PREFIX + memoryCurrent);
        } else if (view.getTag().equals(Utils.MEMORY_CLEAR)) {
            memoryCurrent = "";
            editTextMemory.setText(memoryCurrent);
        } else if (view.getTag().equals(Utils.MEMORY_READ) && Utils.isNotNullString(memoryCurrent)) {
            editTextEquation.setText(memoryCurrent);
        }

        //save the memotry in preferences
        Utils.putStringInSharedPreference(Utils.MEMORY_SAVED_VALE, memoryCurrent);

    }


    public void showHistory(View view) {
        //Try this link
//        http://www.edumobile.org/android/custom-listview-in-a-dialog-in-android/

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
//        builderSingle.setIcon(R.drawable.ic_launcher);
//        builderSingle.setTitle("Select from history:-");

        final ArrayAdapter<HistoryTasks> arrayAdapter = new ArrayAdapter<HistoryTasks>(this,
                android.R.layout.select_dialog_item);

        arrayAdapter.addAll(getHistoryData());

//        arrayAdapter.add("Hardik");
//        arrayAdapter.add("Archit");
//        arrayAdapter.add("Jignesh");
//        arrayAdapter.add("Umang");
//        arrayAdapter.add("Gatti");

        builderSingle.setNegativeButton(R.string.ClearHistory, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Utils.putStringInSharedPreference(Utils.HISTORY_TASKS, Utils.EMPTY_STRING);
            }
        });

        builderSingle.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });

//        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String strName = arrayAdapter.getItem(which).getEquation();
//                editTextEquation.setText(Utils.correctEquation(strName));
////                AlertDialog.Builder builderInner = new AlertDialog.Builder(MainActivity.this);
////                builderInner.setMessage(strName);
////                builderInner.setTitle("Your Selected Item is ");
////                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog,int which) {
////                        dialog.dismiss();
////                    }
////                });
////
////                builderInner.show();
//            }
//        });


        //AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);

//        final EditText etUsername = alertLayout.findViewById(R.id.et_username);
//        final EditText etEmail = alertLayout.findViewById(R.id.et_email);
//        final CheckBox cbToggle = alertLayout.findViewById(R.id.cb_show_pass);
//
        ListView listView = (ListView) alertLayout.findViewById(R.id.listView1);
        TextView listViewFillerText = (TextView) alertLayout.findViewById(R.id.listViewFillerText);


        CalculatorCustomAdapter calculatorCustomAdapter = new CalculatorCustomAdapter(this, getHistoryData());
//        listView.setAdapter(adapter);

        //lv.setAdapter(arrayAdapter);
        ArrayAdapter<HistoryTasks> adapter =
                new ArrayAdapter<HistoryTasks>(this, android.R.layout.simple_list_item_1, getHistoryData());
        listView.setAdapter(calculatorCustomAdapter);

        if (getHistoryData() == null || getHistoryData().size() == 0) {
            listViewFillerText.setText(R.string.no_history_data);
            listViewFillerText.setTextColor(getResources().getColor(Utils.getPreferenceColor()));
        }


        builderSingle.setView(alertLayout);

        final AlertDialog alertDialogObject = builderSingle.create();//DialogBuilder.create();

        alertDialogObject.setView(alertLayout);

        alertDialogObject.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                alertDialogObject.getButton(AlertDialog.BUTTON_NEGATIVE).
                        setTextColor(getResources().getColor(Utils.getPreferenceColor()));


                LinearLayout.LayoutParams layoutParams =
                        (LinearLayout.LayoutParams)
                                alertDialogObject.getButton(AlertDialog.BUTTON_NEGATIVE).getLayoutParams();

                layoutParams.weight = 1;
                alertDialogObject.getButton(AlertDialog.BUTTON_NEGATIVE).setLayoutParams(layoutParams);

                alertDialogObject.getButton(
                        AlertDialog.BUTTON_NEGATIVE).setGravity(ViewGroup.TEXT_ALIGNMENT_CENTER);
                layoutParams =
                        (LinearLayout.LayoutParams)
                                alertDialogObject.getButton(AlertDialog.BUTTON_POSITIVE).getLayoutParams();

                layoutParams.weight = 1;

                alertDialogObject.getButton(AlertDialog.BUTTON_POSITIVE).setLayoutParams(layoutParams);
                alertDialogObject.getButton(AlertDialog.BUTTON_POSITIVE).
                        setTextColor(getResources().getColor(Utils.getPreferenceColor()));

                alertDialogObject.getButton(
                        AlertDialog.BUTTON_POSITIVE).setGravity(ViewGroup.TEXT_ALIGNMENT_CENTER);

                alertDialogObject.getButton(
                        AlertDialog.BUTTON_POSITIVE).setTextSize(getResources().getDimension(R.dimen._8sdp));
                alertDialogObject.getButton(
                        AlertDialog.BUTTON_NEGATIVE).setTextSize(getResources().getDimension(R.dimen._8sdp));

                alertDialogObject.getButton(
                        AlertDialog.BUTTON_POSITIVE).setTransformationMethod(null);
                alertDialogObject.getButton(
                        AlertDialog.BUTTON_NEGATIVE).setTransformationMethod(null);

                if (getHistoryData() == null || getHistoryData().size() == 0) {
                    alertDialogObject.getButton(AlertDialog.BUTTON_NEGATIVE).setVisibility(View.GONE);
                }

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strName = arrayAdapter.getItem(position).getEquation();
                editTextEquation.setText(Utils.correctEquation(strName));
                alertDialogObject.dismiss();
            }
        });
        alertDialogObject.show();
        // set color listView.setDividerHeight(2);
        // set height alertDialogObject.show();

        //builderSingle.show();

    }

    private int dpToPx(int dp) {
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    public void changeMySettings(View view) {

        Utils.vibrateMe();

        Utils.putStringInSharedPreference(Utils.LAST_EQUATION_FOR_THEME_CHANGE,
                editTextEquation.getText().toString());

        Intent intent = new Intent(this, CalculatorSettingsActivity.class);

        startActivity(intent);
    }


    public void clearAll(View view) {
        Utils.vibrateMe();

        evaluationDone = true;
//        clearDone = true;
        mySelectionAdjustment = 0;
        mySelectionValueEnd = 0;
        mySelectionValueStart = 0;
        editTextEquation.clearFocus();
//        editTextResult.setText("0");
//        editTextEquation.setText("0");

//        ((ImageButton) findViewById(R.id.buttonUndolastEval)).setVisibility(View.INVISIBLE);

        if (Utils.TRUE.equals(Utils.getValueFromSharedPreference(Utils.SETTINGS_ANIMATION, Utils.FALSE))) {
            editTextResult.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
            editTextEquation.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
        }
        //        editTextResult.clearAnimation();
        editTextEquation.setText(Utils.evalMe("0"));

    }

    public void calculateMe(View view) {

        Utils.vibrateMe();

//        ((ImageButton) findViewById(R.id.buttonUndolastEval)).setVisibility(View.INVISIBLE);

        String tag = view.getTag().toString();
        String currentEquation = editTextEquation.getText().toString();
        String currentEquationCopy = new String(editTextEquation.getText().toString());


        if (Utils.INVERSE.equals(tag)) {

            //for changesign find the last index of number and change it's sign
            return;
        } else if (Utils.BRACKET_START.equals(tag)) {

            if (evaluationDone) {
                evaluationDone = false;
                currentEquation = Utils.BRACKET_START;
            } else {
                currentEquation = currentEquation + Utils.BRACKET_START;
            }

            editTextEquation.setText(currentEquation);

            return;
        } else if (Utils.BRACKET_END.equals(tag)) {

            //we can't start with BRACKET_END

            //if no start bracket then no end brackets !!!
            if (Utils.containsCountOfAny(currentEquation, Utils.BRACKET_START) == 0) {
                return;
            }

            if (evaluationDone) {
                return;
            } else {
                currentEquation = currentEquation + Utils.BRACKET_END;
            }

            editTextEquation.setText(currentEquation);

            return;

        } else if (Utils.UNDO_LAST_EVALUATE.equals(tag)) {

            evaluationDone = false;

            if (Utils.TRUE.equals(Utils.getValueFromSharedPreference(Utils.SETTINGS_ANIMATION, Utils.FALSE))) {
                editTextResult.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
                editTextEquation.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
            }

            //for changesign find the last index of number and change it's sign
            editTextEquation.setText(Utils.getValueFromSharedPreference(
                    Utils.LAST_EQUATION_FOR_UNDO, Utils.EMPTY_STRING));

//            ((ImageButton) findViewById(R.id.buttonUndolastEval)).setVisibility(View.INVISIBLE);
            return;
        } else if (Utils.CHANGESIGN.equals(tag)) {

            if (currentEquation == null || currentEquation.trim().length() == 0) {
                return;
            }

            if (currentEquation.length() == 1 && "0".equals(currentEquation)) {
                return;
            }

            int lastOperatorIndex = -1;
            int currentEquationLength = currentEquation.length();
            int lastIndexOfDivide = currentEquation.lastIndexOf("÷");
            int lastIndexOfMultiply = currentEquation.lastIndexOf("x");
            int lastIndexOfAdd = currentEquation.lastIndexOf("+");
            int lastIndexOfSubtract = currentEquation.lastIndexOf("-");

            lastOperatorIndex = lastIndexOfDivide > lastOperatorIndex ? lastIndexOfDivide : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfMultiply > lastOperatorIndex ? lastIndexOfMultiply : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfAdd > lastOperatorIndex ? lastIndexOfAdd : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfSubtract > lastOperatorIndex ? lastIndexOfSubtract : lastOperatorIndex;

            //It means no Operators hence put a - infront of the equation
            if (lastOperatorIndex == -1) {
                currentEquation = "-" + currentEquation;
                if (currentEquation.startsWith("--")) {
                    currentEquation = currentEquation.substring(2);
                }
            } else {
                //replace the last operand with sign change 9x123 , lastOperatorIndex = 1
                String inital = currentEquation.substring(0, lastOperatorIndex + 1);
                String end = currentEquation.substring(lastOperatorIndex + 1);
                currentEquation = inital
                        + "-"
                        + end;
            }
            //for changesign find the last index of number and change it's sign


            editTextEquation.setText(Utils.correctEquation(currentEquation));

            return;

        } else if (Utils.DECIMAL.equals(tag) && !editTextEquation.isFocused()) {

            evaluationDone = false;

            if (currentEquation == null || currentEquation.trim().length() == 0) {
                editTextEquation.setText(Utils.correctEquation("0" + tag));
                return;
            }

            int lastOperatorIndex = -1;
            int currentEquationLength = currentEquation.length();
            int lastIndexOfDivide = currentEquation.lastIndexOf("÷");
            int lastIndexOfMultiply = currentEquation.lastIndexOf("x");
            int lastIndexOfAdd = currentEquation.lastIndexOf("+");
            int lastIndexOfSubtract = currentEquation.lastIndexOf("-");
            int lastIndexOfToPowerOf = currentEquation.lastIndexOf("^");
            int lastIndexOfPercentage = currentEquation.lastIndexOf("%");

            lastOperatorIndex = lastIndexOfDivide > lastOperatorIndex ? lastIndexOfDivide : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfMultiply > lastOperatorIndex ? lastIndexOfMultiply : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfAdd > lastOperatorIndex ? lastIndexOfAdd : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfSubtract > lastOperatorIndex ? lastIndexOfSubtract : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfToPowerOf > lastOperatorIndex ? lastIndexOfToPowerOf : lastOperatorIndex;

            //It means no Operators hence put a - infront of the equation
            if (lastOperatorIndex == -1) {
                try {
                    //if exception in arsing then it means that decimal is illegal
                    Double.parseDouble(currentEquation + tag);
                    currentEquation = currentEquation + tag;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //replace the last operand with sign change 9x123 , lastOperatorIndex = 1
                String inital = currentEquation.substring(0, lastOperatorIndex + 1);
                String end = currentEquation.substring(lastOperatorIndex + 1);

                try {
                    //if exception in arsing then it means that decimal is illegal
                    if (Utils.EMPTY_STRING.equals(end)) {
                        end = "0";
                        Double.parseDouble(end + tag);
                        currentEquation = currentEquation + end + tag;
                    } else {
                        currentEquation = currentEquation + tag;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            editTextEquation.setText(Utils.correctEquation(currentEquation));

            return;

        } else if (Utils.PERCENTAGE.equals(tag)) {
            //there should be atleas one numeric number to left of % symbol
            if (currentEquation.length() == 0 ||
                    !Utils.isNumeric(currentEquation.substring(currentEquation.length() - 1))) {
                return;
            }
        }

        if (Utils.BACK.equals(tag)) {
            if (currentEquation.length() > 1) {

                System.out.println("Back pressed " + editTextEquation.getSelectionEnd());
                //To change 1.1234E78 -> 1.234
                if (!editTextEquation.isFocused()) {//editTextEquation.getSelectionEnd() <= 0

                    currentEquation = currentEquation.substring(0, currentEquation.length() - 1);
                    if (currentEquation.endsWith("E")) {
                        currentEquation = currentEquation.substring(0, currentEquation.length() - 1);
                    }

                    if (currentEquation.length() == 0) {
                        currentEquation = "0";
                    }

                    editTextEquation.setText(Utils.correctEquation(currentEquation));

                } else if (mySelectionValueStart >= 0) {

                    int mySelectionValueStart = editTextEquation.getSelectionStart();
                    int mySelectionValueEnd = editTextEquation.getSelectionEnd();


                    System.out.println("Back seeMeTextChanged " + mySelectionValueStart
                            + " : " + mySelectionValueEnd);

                    if (mySelectionValueStart > 0) {
                        String inital = currentEquation.substring(0, mySelectionValueStart - 1);
                        String end = currentEquation.substring(mySelectionValueStart);
                        currentEquation = inital + end;
                    }

                    editTextEquation.setText(Utils.correctEquation(currentEquation));
                }

            } else {
                editTextEquation.setText("0");
                editTextEquation.clearFocus();

            }
        } else if (Utils.EVALUATE.equals(tag)) {
            //editTextEquation.setText(Utils.evalMe(editTextEquation.getText().toString()));

            //if evaluation already done then no need to redo it
            if (evaluationDone) {
                return;
            }

            evaluationDone = true;
            mySelectionAdjustment = 0;
            mySelectionValueEnd = 0;
            mySelectionValueStart = 0;
            editTextEquation.clearFocus();
            if (Utils.TRUE.equals(Utils.getValueFromSharedPreference(Utils.SETTINGS_ANIMATION, Utils.FALSE))) {
                editTextResult.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
                editTextEquation.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
            }
            Utils.putStringInSharedPreference(Utils.LAST_EQUATION_FOR_UNDO, editTextEquation.getText().toString());

            saveHistoryData();
            //        editTextResult.clearAnimation();
            editTextEquation.setText(Utils.evalMe(editTextEquation.getText().toString()));

//            ((ImageButton) findViewById(R.id.buttonUndolastEval)).setVisibility(View.VISIBLE);

            //editTextResult.setText(editTextEquation.getText().toString());
        } else {
            if (evaluationDone && Utils.isNumeric(tag)) {
                evaluationDone = false;
                editTextEquation.setText(tag);
            } else {
                evaluationDone = false;

                int mySelectionValueStart = editTextEquation.getSelectionStart();
                int mySelectionValueEnd = editTextEquation.getSelectionEnd();


                System.out.println("seeMeTextChanged " + mySelectionValueStart
                        + " : " + mySelectionValueEnd);

                if (!editTextEquation.isFocused()) {
                    currentEquation = currentEquation + tag;
                } else {
                    if (mySelectionValueStart > 0) {
                        String inital = currentEquation.substring(0, mySelectionValueStart);
                        String end = currentEquation.substring(mySelectionValueStart);
                        currentEquation = inital
                                + tag
                                + end;
                    } else if (mySelectionValueStart == 0) {
                        currentEquation = tag + currentEquation;
                    }

                }

                //If there is an issue with the Decimal then keep the old String

                try {
                    String testResult = Utils.evalMe(currentEquation);
                } catch (Exception e) {
                    currentEquation = new String(currentEquationCopy);
                }

                editTextEquation.setText(Utils.correctEquation(currentEquation));
            }

        }

    }


    private ArrayList<HistoryTasks> getHistoryData() {
        Gson gson = new Gson();

        Type type = new TypeToken<List<HistoryTasks>>() {
        }.getType();

        String historyJson = Utils.getValueFromSharedPreference(Utils.HISTORY_TASKS, Utils.EMPTY_STRING);

        ArrayList<HistoryTasks> historyTasks = new ArrayList<>();

        if (Utils.isNotNullString(historyJson)) {
            historyTasks = gson.fromJson(historyJson, type);
        }

        Collections.sort(historyTasks, new Comparator<HistoryTasks>() {
            @Override
            public int compare(HistoryTasks lhs, HistoryTasks rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getDate().getTime() > rhs.getDate().getTime() ?
                        -1 : (lhs.getDate().getTime() < rhs.getDate().getTime()) ? 1 : 0;
            }
        });

        return historyTasks;
    }


    private void saveHistoryData() {
        String historyEquation = editTextEquation.getText().toString();
        String historyResult = editTextResult.getText().toString();

        Gson gson = new Gson();

        Type type = new TypeToken<List<HistoryTasks>>() {
        }.getType();

        String historyJson = Utils.getValueFromSharedPreference(Utils.HISTORY_TASKS, Utils.EMPTY_STRING);

        List<HistoryTasks> historyTasks = new ArrayList<>();

        if (Utils.isNotNullString(historyJson)) {
            historyTasks = gson.fromJson(historyJson, type);
        }

        historyTasks.add(new HistoryTasks(new Date(), historyEquation, historyResult));

        if (historyTasks.size() > Utils.MAX_RECORDS_IN_HISTORY) {
            historyTasks.remove(0);
        }

        String json = gson.toJson(historyTasks);
        Utils.putStringInSharedPreference(Utils.HISTORY_TASKS, json);


        System.out.println(historyTasks);

        Collections.sort(historyTasks, new Comparator<HistoryTasks>() {
            @Override
            public int compare(HistoryTasks lhs, HistoryTasks rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getDate().getTime() > rhs.getDate().getTime() ?
                        -1 : (lhs.getDate().getTime() < rhs.getDate().getTime()) ? 1 : 0;
            }
        });

        System.out.println(historyTasks);

    }

    private void resetOperators() {
        operand1 = null;
        operand1 = null;
        operator = "";
    }


}


//        ListView listView = alertDialogObject.getListView();
//        listView.setDivider(new ColorDrawable(getResources().getColor(R.color.pankaj_very_light_grey)));
//        listView.setDividerHeight(1);
//
//        Window window = alertDialogObject.getWindow();
//        window.setGravity(Gravity.TOP | Gravity.LEFT);
//
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.y = dpToPx(250);
//        params.height = 300;
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
////        params.y = editTextResult.getBottom();
//
//        window.setAttributes(params);
//