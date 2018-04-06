package whizkid.amaya.calculatorforlaunch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {

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
     */


    AppCompatTextView editTextResult;
    EditText editTextEquation;
    EditText editTextMemory;
    final Typeface sansSeifNormal = Typeface.create("sans-serif-light", Typeface.NORMAL);
    final Typeface sansSeifCondensed = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    final Typeface sansSeifMonospace = Typeface.create("arial", Typeface.NORMAL);
    final Typeface sansSeifTest = Typeface.create("sans-serif-thin", Typeface.BOLD);


    String result;
    String operand1;
    String operand2;
    String operator;
    boolean evaluationDone = false;
    int[] resourcesImageButton = {R.id.buttonBack};
    int mySelectionValueStart = 0;
    int mySelectionValueEnd = 0;
    int mySelectionAdjustment = 0;

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
                    R.id.buttonAllClear,
                    R.id.buttonDivide,
                    R.id.buttonMultiply,
                    R.id.buttonSubtract,
                    R.id.buttonAdd,
                    R.id.buttonEquals,
                    R.id.buttonSettings};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calculator_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.MyTheme:
                System.out.println("clicked " + item.getTitle());
                return true;
            case R.id.MyHelp:
                System.out.println("clicked " + item.getTitle());
                return true;
            default:
                System.out.println("MyTheme clicked" + item.toString());
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.mylayout_phone);

        //Pankaj Code Starts
        resetOperators();

        editTextResult = (AppCompatTextView) findViewById(R.id.editTextResult);
        editTextEquation = (EditText) findViewById(R.id.editTextEquation);
        editTextMemory = (EditText) findViewById(R.id.editTextMemory);

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

                result = Utils.evalMe(s.toString());
                editTextResult.setText(result);
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

        editTextEquation.setText("0");

        //get the memory from the Share Preference
        String storedMemory = Utils.getValueFromSharedPreference(Utils.MEMORY_SAVED_VALE,
                this.getPreferences(Context.MODE_PRIVATE));

        if (Utils.isNotNullString(storedMemory)) {
            editTextMemory.setText(Utils.MEMORY_PREFIX + storedMemory);
        }

        //Set the font type for the fields
        editTextResult.setTypeface(sansSeifTest);
        editTextEquation.setTypeface(sansSeifMonospace);
        editTextMemory.setTypeface(sansSeifCondensed);

        for (int i = 0; i < resourcesButton.length; i++) {
            try {
                System.out.println("resourcesButton[i] " + i + " : " + resourcesButton[i]);
                ((Button) findViewById(resourcesButton[i])).setTypeface(sansSeifNormal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void saveInMemory(View view) {

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
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        Utils.putStringInSharedPreference(Utils.MEMORY_SAVED_VALE, memoryCurrent, sharedPreferences);

    }

    public void clearAll(View view) {
        evaluationDone = true;
        mySelectionAdjustment = 0;
        mySelectionValueEnd = 0;
        mySelectionValueStart = 0;
        editTextEquation.clearFocus();
        editTextResult.setText("0");
        editTextEquation.setText("0");
    }

    public void calculateMe(View view) {
        String tag = view.getTag().toString();
        String currentEquation = editTextEquation.getText().toString();
        String currentEquationCopy = new String(editTextEquation.getText().toString());

        if (Utils.INVERSE.equals(tag)) {

            //for changesign find the last index of number and change it's sign
            return;
        } else if (Utils.CHANGESIGN.equals(tag)) {

            if (currentEquation == null || currentEquation.trim().length() == 0) {
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

            if (currentEquation == null || currentEquation.trim().length() == 0) {
                return;
            }

            int lastOperatorIndex = -1;
            int currentEquationLength = currentEquation.length();
            int lastIndexOfDivide = currentEquation.lastIndexOf("÷");
            int lastIndexOfMultiply = currentEquation.lastIndexOf("x");
            int lastIndexOfAdd = currentEquation.lastIndexOf("+");
            int lastIndexOfSubtract = currentEquation.lastIndexOf("-");
            int lastIndexOfPercentage = currentEquation.lastIndexOf("%");

            lastOperatorIndex = lastIndexOfDivide > lastOperatorIndex ? lastIndexOfDivide : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfMultiply > lastOperatorIndex ? lastIndexOfMultiply : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfAdd > lastOperatorIndex ? lastIndexOfAdd : lastOperatorIndex;
            lastOperatorIndex = lastIndexOfSubtract > lastOperatorIndex ? lastIndexOfSubtract : lastOperatorIndex;

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
                    Double.parseDouble(end + tag);
                    currentEquation = currentEquation + tag;
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
                    editTextEquation.setText(currentEquation);

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

                    editTextEquation.setText(currentEquation);
                }

            } else {
                editTextEquation.setText("0");
                editTextEquation.clearFocus();

            }
        } else if (Utils.EVALUATE.equals(tag)) {
            //editTextEquation.setText(Utils.evalMe(editTextEquation.getText().toString()));
            evaluationDone = true;
            mySelectionAdjustment = 0;
            mySelectionValueEnd = 0;
            mySelectionValueStart = 0;
            editTextEquation.clearFocus();
            editTextEquation.setText(Utils.evalMe(editTextEquation.getText().toString()));
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


    private void resetOperators() {
        operand1 = null;
        operand1 = null;
        operator = "";
    }


}
