package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
import org.javia.arity.Util;

import java.util.ArrayList;

public class Utils {

    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String DECIMAL = ".";
    public static final String INVERSE = "1/x";
    public static final String PERCENTAGE = "%";
    public static final String CHANGESIGN = "+/-";

    public static final String ALL_CLEAR = "ALLCLEAR";
    public static final String BACK = "BACK";
    public static final String EVALUATE = "=";
    public static final String MEMORY_SAVED_VALE = "MEMORY_SAVED_VALE";
    public static final String MEMORY_PREFIX = "M ";
    public static final String MEMORY_ADD = "m+";
    public static final String MEMORY_SUBTRACT = "m-";
    public static final String MEMORY_READ = "mr";
    public static final String MEMORY_CLEAR = "mc";

    /**
     * The maximum number of significant digits to display.
     */
    private static final int MAX_DIGITS = 12;

    /**
     * A {@link Double} has at least 17 significant digits, we show the first {@link #MAX_DIGITS}
     * and use the remaining digits as guard digits to hide floating point precision errors.
     */
    private static final int ROUNDING_DIGITS = Math.max(17 - MAX_DIGITS, 0);


    public static boolean isTagOperator(String tag) {
        return ADD.equals(tag) || SUBTRACT.equals(tag)
                || MULTIPLY.equals(tag) || DIVIDE.equals(tag);
    }


    /**
     *
     * @param str
     * @param searchChars
     * @return
     */
    public static boolean containsAnyTagAlready(String str, String searchChars) {
        if (searchChars == null) {
            return false;
        }
        return containsAny(str, searchChars.toCharArray());
    }

    /**
     *
     * @param str
     * @param searchChars
     * @return
     */
    public static boolean containsAny(String str, char[] searchChars) {
        if (str == null || str.length() == 0 || searchChars == null || searchChars.length == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < searchChars.length; j++) {
                if (searchChars[j] == ch) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param equation
     * @return
     */
    static String correctEquation(String equation) {
        equation = equation.replace("xx", "x");
        equation = equation.replace("x÷", "÷");
        equation = equation.replace("÷x", "x");
        equation = equation.replace("x+", "x");
        equation = equation.replace("÷+", "÷");
        equation = equation.replace("++", "+");
        equation = equation.replace("+÷", "+");
        equation = equation.replace("+x", "+");
        equation = equation.replace("---", "-");
        // equation = equation.replace("--", "+");
        equation = equation.replace("-÷", "-");
        equation = equation.replace("-x", "-");
        equation = equation.replace("+-", "-");
        equation = equation.replace("-+", "-");

        equation = equation.replace("..", ".");


        //to remove the first +,  +1 -> 1
        if (equation != null && equation.length() > 1 &&
                (equation.startsWith("÷") || equation.startsWith("x")
                        || equation.startsWith("+") || equation.startsWith(PERCENTAGE))) {
            equation = equation.substring(1, equation.length());
        }

        //to remove the first --,  --1 -> 1
        if (equation != null && equation.length() > 2 &&
                equation.startsWith("--")) {
            equation = equation.substring(2, equation.length());
        }

        //remove the initil 0s, 09 -> 9
        if (equation != null && equation.length() == 2 &&
                equation.startsWith("0")
                && (Utils.isNumeric(equation.substring(1, 2)))) {
            equation = equation.substring(1, equation.length());
        }

        //remove 2 decimals
        return equation;
    }


    /**
     * @param str
     * @return
     */
    public static String evalMe(String str) {

        str = str.replace("÷", "/");
        str = str.replace("x", "*");

        if (str == null || str.trim().length() == 0) {
            return "0";
        }

        if (str.endsWith(DIVIDE) || str.endsWith(MULTIPLY)
                || str.endsWith(ADD) || str.endsWith(SUBTRACT)
                || str.endsWith(DECIMAL)) {

            return evalMe(str.substring(0, str.length() - 1));
        }

        double resultDouble = evalMeUsingSymbols(str);

        String resultString = Util.doubleToString(resultDouble, MAX_DIGITS, ROUNDING_DIGITS);

        return resultString;

    }

    /**
     * @param equation
     * @return
     */
    private static double evalMeUsingSymbols(String equation) {

        try {
            Symbols symbols = new Symbols();
            return symbols.eval(equation);
        } catch (SyntaxException s) {
            return Double.valueOf(null);
        }

    }


    /**
     * @param key
     * @param sharedPreferences
     * @return
     */
    public static String getValueFromSharedPreference(
            final String key, SharedPreferences sharedPreferences) {

        return sharedPreferences.getString(key, "");

    }


    /**
     * @param key
     * @param value
     * @param sharedPreferences
     */
    public static void putStringInSharedPreference(final String key, final String value,
                                                   SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    /**
     * @param val
     * @return
     */
    static boolean isNotNullString(String val) {

        return val != null && val.trim().length() > 0;
    }

    /**
     * @param editText
     * @return
     */
    static boolean isEditTextEmpty(EditText editText) {

        return false;
    }

    /**
     * @param val
     * @return
     */
    static boolean isNumeric(String val) {

        try {
            Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}