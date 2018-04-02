package whizkid.amaya.calculatorforlaunch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
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


    EditText textViewResult;
    EditText textViewEquation;
    EditText textViewMemory;
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

        textViewResult = (EditText) findViewById(R.id.textViewResult);
        textViewEquation = (EditText) findViewById(R.id.textViewEquation);
        textViewMemory = (EditText) findViewById(R.id.textViewMemory);

        //TODO remove this option for API15
        textViewEquation.setShowSoftInputOnFocus(false);
//        textViewEquation.setLongClickable(false);
//        textViewEquation.setTextIsSelectable(false);

        //Add listener so that we can chek the validity of the equation
        textViewEquation.addTextChangedListener(new TextWatcher() {
            int mySelectionValue = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mySelectionValue = textViewEquation.getSelectionEnd() - 1;
//                System.out.println("text after change -1 " +
//                        textViewEquation.getSelectionStart() + " : " + textViewEquation.getSelectionEnd());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                System.out.println("text after change 0 " +
//                        textViewEquation.getSelectionStart() + " : " + textViewEquation.getSelectionEnd());
                System.out.println("corrected string 1 " + Utils.correctEquation(s.toString()));
                String correctedString = Utils.correctEquation(s.toString());

                if (!correctedString.equals(s.toString())) {
                    textViewEquation.setText(correctedString);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//                System.out.println("text after change 1 " +
//                        textViewEquation.getSelectionStart() + " : " + textViewEquation.getSelectionEnd());
//                System.out.println("text for the equation changed " + s.toString());
                System.out.println("corrected string 2 " + Utils.correctEquation(s.toString()));

                String result = Utils.evalMe(s.toString());
                textViewResult.setText(result);

//                System.out.println("text after change 2 " +
//                        textViewEquation.getSelectionStart() + " : " + textViewEquation.getSelectionEnd()
//                        + " : " + mySelectionValue);

                //if mySelectionValue is less than 0 set it to 0
                mySelectionValue = mySelectionValue < 0 ? 0 : mySelectionValue;

                textViewEquation.setSelection(mySelectionValue, mySelectionValue);
            }
        });

        textViewEquation.setText("0");

        //get the memory from the Share Preference
        String storedMemory = Utils.getValueFromSharedPreference(Utils.MEMORY_SAVED_VALE,
                this.getPreferences(Context.MODE_PRIVATE));

        if (Utils.isNotNullString(storedMemory)) {
            textViewMemory.setText(Utils.MEMORY_PREFIX + storedMemory);
        }

        //Set the font type for the fields
        textViewResult.setTypeface(sansSeifNormal);
        textViewResult.setTypeface(sansSeifNormal);
        textViewMemory.setTypeface(sansSeifCondensed);

        for (int i = 0; i < resourcesButton.length; i++) {
            ((Button) findViewById(resourcesButton[i])).setTypeface(sansSeifNormal);
        }


    }

    public void saveInMemory(View view) {

        String memoryCurrent = textViewMemory.getText().toString();
        String resultCurrent = Utils.evalMe(textViewResult.getText().toString());

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
            textViewMemory.setText(Utils.MEMORY_PREFIX + memoryCurrent);

        } else if (view.getTag().equals(Utils.MEMORY_SUBTRACT)) {
            memoryCurrent = Utils.evalMe(
                    memoryCurrent
                            + "-"
                            + resultCurrent);
            textViewMemory.setText(Utils.MEMORY_PREFIX + memoryCurrent);
        } else if (view.getTag().equals(Utils.MEMORY_CLEAR)) {
            memoryCurrent = "";
            textViewMemory.setText(memoryCurrent);
        } else if (view.getTag().equals(Utils.MEMORY_READ) && Utils.isNotNullString(memoryCurrent)) {
            textViewEquation.setText(memoryCurrent);
            textViewResult.setText(memoryCurrent);
        }

        //save the memotry in preferences
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        Utils.putStringInSharedPreference(Utils.MEMORY_SAVED_VALE, memoryCurrent, sharedPreferences);

    }

    public void clearAll(View view) {
        //textViewResult.setText("0");
        textViewEquation.setText("0");

    }

    public void calculateMe(View view) {
        String tag = view.getTag().toString();
        String currentEquation = textViewEquation.getText().toString();

        if (Utils.INVERSE.equals(tag)
                || Utils.PERCENTAGE.equals(tag)
                || Utils.CHANGESIGN.equals(tag)) {
            return;
        }
        if (Utils.BACK.equals(tag)) {
            if (currentEquation.length() > 1) {

                System.out.println("Back pressed " + textViewEquation.getSelectionEnd());
                if (!textViewEquation.isFocused()) {//textViewEquation.getSelectionEnd() <= 0
                    textViewEquation.setText(currentEquation.substring(0, currentEquation.length() - 1));

                } else if (textViewEquation.getSelectionEnd() > 0) {
                    textViewEquation.setText(
                            currentEquation.substring(0, textViewEquation.getSelectionEnd() - 1)
                                    +
                                    currentEquation.substring(textViewEquation.getSelectionEnd(), currentEquation.length()
                                    ));
                }

            } else {
                textViewEquation.setText("");
            }
        } else if (Utils.EVALUATE.equals(tag)) {
            textViewEquation.setText(Utils.evalMe(textViewEquation.getText().toString()));
            textViewEquation.clearFocus();
            evaluationDone = true;
        } else {

            if (evaluationDone && Utils.isNumeric(tag)) {
                textViewEquation.setText(tag);
            } else {
                textViewEquation.setText(textViewEquation.getText().toString() + tag);
            }

            evaluationDone = false;

            textViewEquation.clearFocus();
        }

        // textViewResult.setText(Utils.evalMe(textViewEquation.getText().toString()));
//
//        result = null;
//        String tag = view.getTag().toString();
//        String resultCurrentValue = textViewResult.getText().toString();
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
//            textViewEquation.setText(resultCurrentValue);
//        } else {
//            //if evaluation has already been done then start from scratch
//            //unless it's a operator
//            if (evaluationDone && !isItOperatorTag) {
//                setValueToTextResult(tag);
//                textViewEquation.setText("");
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

        textViewResult.setText(result);
    }

    private void resetOperators() {
        operand1 = null;
        operand1 = null;
        operator = "";
    }


}
