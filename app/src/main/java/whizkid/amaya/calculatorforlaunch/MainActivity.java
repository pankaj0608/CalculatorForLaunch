package whizkid.amaya.calculatorforlaunch;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    String result;
    String operand1;
    String operand2;
    String operator;
    boolean evaluationDone = false;
    int[] resources =
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

    public void changeMyLayout(View view) {
        if (true) {
            return;
        }
        setContentView(R.layout.mylayout_phone_changelayout);

        Button buttonMemoryClear = (Button) findViewById(R.id.buttonMemoryClear);
        buttonMemoryClear.setVisibility(View.INVISIBLE);

        Button buttonMemoryMinus = (Button) findViewById(R.id.buttonMemoryMinus);
        buttonMemoryMinus.setVisibility(View.INVISIBLE);

        Button buttonMemoryPlus = (Button) findViewById(R.id.buttonMemoryPlus);
        buttonMemoryPlus.setVisibility(View.INVISIBLE);

        Button buttonMemoryRead = (Button) findViewById(R.id.buttonMemoryRead);
        buttonMemoryRead.setVisibility(View.INVISIBLE);
    }

    public void hideMyMemory(View view) {
        if(true) {
            return;
        }

        Button buttonMemoryClear = (Button) findViewById(R.id.buttonMemoryClear);
        buttonMemoryClear.setVisibility(View.INVISIBLE);

        Button buttonMemoryMinus = (Button) findViewById(R.id.buttonMemoryMinus);
        buttonMemoryMinus.setVisibility(View.INVISIBLE);

        Button buttonMemoryPlus = (Button) findViewById(R.id.buttonMemoryPlus);
        buttonMemoryPlus.setVisibility(View.INVISIBLE);

        Button buttonMemoryRead = (Button) findViewById(R.id.buttonMemoryRead);
        buttonMemoryRead.setVisibility(View.INVISIBLE);
    }

    public void showMyMemory(View view) {

        if(true) {
            return;
        }
        Button buttonMemoryClear = (Button) findViewById(R.id.buttonMemoryClear);
        buttonMemoryClear.setVisibility(View.VISIBLE);

        Button buttonMemoryMinus = (Button) findViewById(R.id.buttonMemoryMinus);
        buttonMemoryMinus.setVisibility(View.VISIBLE);

        Button buttonMemoryPlus = (Button) findViewById(R.id.buttonMemoryPlus);
        buttonMemoryPlus.setVisibility(View.VISIBLE);

        Button buttonMemoryRead = (Button) findViewById(R.id.buttonMemoryRead);
        buttonMemoryRead.setVisibility(View.VISIBLE);
    }

    public void changeMyLayoutToOriginal(View view) {
        setContentView(R.layout.mylayout_phone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_calculator_port);
        setContentView(R.layout.mylayout_phone);

        resetOperators();
//        textViewResult = (EditText) findViewById(R.id.textViewResult);
//        Typeface typeface = ResourcesCompat.getFont(this, R.font.seymour_one);
//        textViewResult.setTypeface(typeface);
//
//        Button btn = (Button) findViewById(resources[0]);
//        btn.setTypeface(typeface);
        Typeface sansSeifNormal = Typeface.create("sans-serif-light", Typeface.NORMAL);
        Typeface sansSeifCondensed = Typeface.create("sans-serif-condensed", Typeface.NORMAL);

        textViewResult = (EditText) findViewById(R.id.textViewResult);
        textViewEquation = (EditText) findViewById(R.id.textViewEquation);

        textViewResult.setTypeface(sansSeifNormal);

        for (int i = 0; i < resources.length; i++) {
            ((Button) findViewById(resources[i])).setTypeface(sansSeifNormal);
        }

        ((TextView) findViewById(R.id.textViewResult)).setTypeface(sansSeifNormal);
        ((TextView) findViewById(R.id.textViewEquation)).setTypeface(sansSeifNormal);
        ((TextView) findViewById(R.id.textViewMemory)).setTypeface(sansSeifCondensed);


        //btn1.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));

        //this.openOrCreateDatabase("CalculatorPro", MODE_PRIVATE, null);
        SharedPreferences sharedPreferences =
                this.getSharedPreferences(
                        "com.amaya.whizkid.calculatorpro",
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AUTHOR", "Amaya Kumar");
        editor.commit();
        editor.apply();

        String author = sharedPreferences.getString("AUTHOR", "NOT SET");
        System.out.println("Shared Preferences Author onCreate " + author);

    }

    public void clearAll(View view) {
        textViewResult.setText("");
    }

    public void calculateMe(View view) {

//        if (true) {
//            return;
//        }

        SharedPreferences sharedPreferences =
                this.getSharedPreferences(
                        "com.amaya.whizkid.calculatorpro",
                        MODE_PRIVATE);
        String author = sharedPreferences.getString("AUTHOR", "NOT SET");
        System.out.println("Shared Preferences Author " + author);

        result = null;
        String tag = view.getTag().toString();
        String resultCurrentValue = textViewResult.getText().toString();

        boolean isItOperatorTag = Utils.isTagOperator(tag);
        boolean isItEvaluateTag = Utils.isTagEvaluateOperator(tag);
        boolean isItClearTag = Utils.isItClearTagOperator(tag);

        if (isItClearTag) {
            setValueToTextResult("0");
            return;
        }

        if (isItEvaluateTag) {
            result = evaluate(resultCurrentValue);
        }

        //if it was evaluate tag then set the result
        if (isItEvaluateTag) {
            setValueToTextResult(result);
            textViewEquation.setText(resultCurrentValue);
        } else {
            //if evaluation has already been done then start from scratch
            //unless it's a operator
            if (evaluationDone && !isItOperatorTag) {
                setValueToTextResult(tag);
                textViewEquation.setText("");
            } else {
                setValueToTextResult(resultCurrentValue + tag);
            }
            evaluationDone = false;
        }

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
