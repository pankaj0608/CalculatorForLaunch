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
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

    String result;
    String operand1;
    String operand2;
    String operator;
    boolean evaluationDone = false;
    int[] resourcesButton =
            {R.id.buttonSignChange,
                    R.id.buttonDecimal,
                    R.id.button0,
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
                    R.id.buttonInvert,
                    R.id.buttonBack,
                    R.id.buttonAllClear,
                    R.id.buttonDivide,
                    R.id.buttonMultiply,
                    R.id.buttonSubtract,
                    R.id.buttonAdd,
                    R.id.buttonEquals};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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

        //TODO remove this option for API15
        editTextEquation.setShowSoftInputOnFocus(false);

        TextViewCompat.setAutoSizeTextTypeWithDefaults(editTextResult, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);

//        editTextEquation.setLongClickable(false);
//        editTextEquation.setTextIsSelectable(false);

        //Add listener so that we can chek the validity of the equation
        editTextEquation.addTextChangedListener(new TextWatcher() {
            int mySelectionValue = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mySelectionValue = editTextEquation.getSelectionEnd() - 1;
//                System.out.println("text after change -1 " +
//                        editTextEquation.getSelectionStart() + " : " + editTextEquation.getSelectionEnd());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                System.out.println("text after change 0 " +
//                        editTextEquation.getSelectionStart() + " : " + editTextEquation.getSelectionEnd());
                System.out.println("corrected string 1 " + Utils.correctEquation(s.toString()));
                String correctedString = Utils.correctEquation(s.toString());

                if (!correctedString.equals(s.toString())) {
                    editTextEquation.setText(correctedString);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                System.out.println("corrected string 2 " + Utils.correctEquation(s.toString()));

                String result = Utils.evalMe(s.toString());
                editTextResult.setText(result);

                mySelectionValue = mySelectionValue < 0 ? 0 : mySelectionValue;

                editTextEquation.setSelection(mySelectionValue, mySelectionValue);
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
        editTextResult.setTypeface(sansSeifNormal);
        editTextResult.setTypeface(sansSeifNormal);
        editTextMemory.setTypeface(sansSeifCondensed);

        for (int i = 0; i < resourcesButton.length; i++) {
            ((Button) findViewById(resourcesButton[i])).setTypeface(sansSeifNormal);
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
            editTextResult.setText(memoryCurrent);
        }

        //save the memotry in preferences
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        Utils.putStringInSharedPreference(Utils.MEMORY_SAVED_VALE, memoryCurrent, sharedPreferences);

    }

    public void clearAll(View view) {
        //editTextResult.setText("0");
        editTextEquation.setText("0");

    }

    public void calculateMe(View view) {
        String tag = view.getTag().toString();
        String currentEquation = editTextEquation.getText().toString();

        if (Utils.INVERSE.equals(tag)
                || Utils.PERCENTAGE.equals(tag)
                || Utils.CHANGESIGN.equals(tag)) {
            return;
        }
        if (Utils.BACK.equals(tag)) {
            if (currentEquation.length() > 1) {

                System.out.println("Back pressed " + editTextEquation.getSelectionEnd());
                if (!editTextEquation.isFocused()) {//editTextEquation.getSelectionEnd() <= 0
                    editTextEquation.setText(currentEquation.substring(0, currentEquation.length() - 1));

                } else if (editTextEquation.getSelectionEnd() > 0) {
                    editTextEquation.setText(
                            currentEquation.substring(0, editTextEquation.getSelectionEnd() - 1)
                                    +
                                    currentEquation.substring(editTextEquation.getSelectionEnd(), currentEquation.length()
                                    ));
                }

            } else {
                editTextEquation.setText("");
            }
        } else if (Utils.EVALUATE.equals(tag)) {
            editTextEquation.setText(Utils.evalMe(editTextEquation.getText().toString()));
            editTextEquation.clearFocus();
            evaluationDone = true;
        } else {

            if (evaluationDone && Utils.isNumeric(tag)) {
                editTextEquation.setText(tag);
            } else {
                editTextEquation.setText(editTextEquation.getText().toString() + tag);
            }

            evaluationDone = false;

            editTextEquation.clearFocus();
        }

        // editTextResult.setText(Utils.evalMe(editTextEquation.getText().toString()));
//
//        result = null;
//        String tag = view.getTag().toString();
//        String resultCurrentValue = editTextResult.getText().toString();
//
//        boolean isItOperatorTag = Utils.isTagOperator(tag);
//        boolean isItEvaluateTag = Utils.isTagEvaluateOperator(tag);
//        boolean isItClearTag = Utils.isItClearTagOperator(tag);
//
//        if (isItClearTag) {
//            setValueToTextResult("0");
//            return;
//        }
//
//        if (isItEvaluateTag) {
//            result = evaluate(resultCurrentValue);
//        }
//
//        //if it was evaluate tag then set the result
//        if (isItEvaluateTag) {
//            setValueToTextResult(result);
//            editTextEquation.setText(resultCurrentValue);
//        } else {
//            //if evaluation has already been done then start from scratch
//            //unless it's a operator
//            if (evaluationDone && !isItOperatorTag) {
//                setValueToTextResult(tag);
//                editTextEquation.setText("");
//            } else {
//                setValueToTextResult(resultCurrentValue + tag);
//            }
//            evaluationDone = false;
//        }

    }

    private String evaluate(String problem) {

        String result = "";
        result = Utils.evalMe(problem);
        evaluationDone = true;
        resetOperators();
        return result;
    }


    private void setValueToTextResult(String result) {

        //remove starting 0. 07 -> 7
        if (result != null && result.length() > 1 && result.startsWith("0")) {
            result = result.substring(1);
        }

        //remove trailing operators 78++ -> 78+
        if (result != null && result.length() > 2
                && Utils.containsAnyTagAlready(result.substring(0, result.length() - 1), "+-*/")
                && Utils.isTagOperator(Character.toString(result.charAt(result.length() - 1)))) {
            result = result.substring(0, result.length() - 1);
        }

        editTextResult.setText(result);
    }

    private void resetOperators() {
        operand1 = null;
        operand1 = null;
        operator = "";
    }


}
