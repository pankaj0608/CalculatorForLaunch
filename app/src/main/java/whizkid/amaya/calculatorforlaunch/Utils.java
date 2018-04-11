package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.widget.EditText;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
import org.javia.arity.Util;

import java.text.DecimalFormat;

public class Utils {

    public static final String EMPTY_STRING = "";
    public static final String FALSE = "false";
    public static final String TRUE = "true";

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

    public static final String SETTINGS_VIBRATE_ON_TOUCH = "SETTINGS_VIBRATE_ON_TOUCH";
    public static final String SETTINGS_PRECISSION_TWO_DIGIT = "SETTINGS_PRECISSION_TWO_DIGIT";
    public static final String SETTINGS_COLOR_THEME = "SETTINGS_COLOR_THEME";
    public static final String SETTINGS_KEYPAD_LAYOUT = "SETTINGS_KEYPAD_LAYOUT";
    public static final String SETTINGS_DISPLAY_FORMAT = "SETTINGS_DISPLAY_FORMAT";
    public static final String SETTINGS_FONT_STYLE = "SETTINGS_FONT_STYLE";
    public static final String SETTINGS_COMMA_AFTER_THOUSAND = "SETTINGS_COMMA_AFTER_THOUSAND";

    //    private static final DecimalFormat decimalFormat = new DecimalFormat("#0.###"); //
    private static final DecimalFormat decimalFormatWithCommaOnly = new DecimalFormat("#,###.#########");
    private static final DecimalFormat decimalFormatWithPrecisionOnly = new DecimalFormat("#0.##");
    private static final DecimalFormat decimalFormatCommaAndFormat = new DecimalFormat("##,###.##");
    private static final double MAX_VALUE_FOR_START_OF_EXPONENT = 99999999998d;

//    private static final DecimalFormat decimalFormatWithComma = new DecimalFormat("#,###.###");


    public static final long VIBRATION_DURATION = 25;
    private static Vibrator vibrator;
    public static Context contextOfApplication;
    private static final String MY_SHARED_PREFERENCE = "MY_SHARED_PREFERENCE";


    public static void setBaseEssentials(Context context, Vibrator aVibrator) {
        contextOfApplication = context;
        vibrator = aVibrator;
    }


    /**
     * The maximum number of significant digits to display.
     */
    private static final int MAX_DIGITS = 12;

    private static final int MAX_DIGITS_TWO_PRECISSION = 5;


    /**
     * A {@link Double} has at least 17 significant digits, we show the first {@link #MAX_DIGITS}
     * and use the remaining digits as guard digits to hide floating point precision errors.
     */
    private static final int ROUNDING_DIGITS = Math.max(17 - MAX_DIGITS, 0);
    private static final int ROUNDING_DIGITS_TWO_PRECISSION = Math.max(17 - MAX_DIGITS_TWO_PRECISSION, 0);


    public static boolean isTagOperator(String tag) {
        return ADD.equals(tag) || SUBTRACT.equals(tag)
                || MULTIPLY.equals(tag) || DIVIDE.equals(tag);
    }


    /**
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
        equation = equation.replace("÷%", "÷");
        equation = equation.replace("x%", "x");
        equation = equation.replace("+%", "+");
        equation = equation.replace("-%", "-");

        equation = equation.replace("%%", "%");

        equation = equation.replace("xx", "x");
        equation = equation.replace("xx", "x");
        equation = equation.replace("x÷", "÷");
        equation = equation.replace("÷x", "x");
        equation = equation.replace("x+", "x");
        equation = equation.replace("÷+", "÷");
        equation = equation.replace("÷÷", "÷");
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
        str = str.replace(",", "");

        if (str == null || str.trim().length() == 0) {
            return "0";
        }

        if (str.endsWith(DIVIDE) || str.endsWith(MULTIPLY)
                || str.endsWith(ADD) || str.endsWith(SUBTRACT)
                || str.endsWith(DECIMAL)) {

            return evalMe(str.substring(0, str.length() - 1));
        }

        double resultDouble = evalMeUsingSymbols(str);

        String twoDigitPrecission =
                getValueFromSharedPreference(Utils.SETTINGS_PRECISSION_TWO_DIGIT, FALSE );
        String commaAfterThousand =
                getValueFromSharedPreference(Utils.SETTINGS_COMMA_AFTER_THOUSAND, FALSE);

        String resultString = "";

//        decimalFormatWithCommaOnly
//        decimalFormatWithPrecisionOnly
//        decimalFormatWithCommaAndPrecision
//

        resultString = Util.doubleToString(resultDouble, MAX_DIGITS, ROUNDING_DIGITS);

        if (TRUE.equals(twoDigitPrecission) && TRUE.equals(commaAfterThousand)) {
            resultString = decimalFormatCommaAndFormat.format(resultDouble);
        } else if (TRUE.equals(twoDigitPrecission) && !TRUE.equals(commaAfterThousand)) {
            resultString = decimalFormatWithPrecisionOnly.format(resultDouble);
        } else if (!TRUE.equals(twoDigitPrecission) && TRUE.equals(commaAfterThousand)) {
            resultString = decimalFormatWithCommaOnly.format(resultDouble);
        }

        //if the result is too long then change it into Exponential form
        if (resultDouble > MAX_VALUE_FOR_START_OF_EXPONENT) {
            resultString = Util.doubleToString(resultDouble, MAX_DIGITS, ROUNDING_DIGITS);
        }

        return resultString;

    }


    private static SharedPreferences getSharedPreference() {
//        return PreferenceManager.getDefaultSharedPreferences(contextOfApplication);

        return contextOfApplication.getSharedPreferences(
                MY_SHARED_PREFERENCE, Context.MODE_PRIVATE);

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
     * @return
     */
    public static String getValueFromSharedPreference(
            final String key, String defaultValue) {

        return getSharedPreference().getString(key, defaultValue);

    }


    /**
     * @param key
     * @param value
     */
    public static void putStringInSharedPreference(final String key, final String value) {

        SharedPreferences.Editor editor = getSharedPreference().edit();
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


    static void vibrateMe() {
        //vibrate if vibation set in the settings
        if (TRUE.equals(getValueFromSharedPreference(Utils.SETTINGS_VIBRATE_ON_TOUCH, FALSE))) {
            vibrator.vibrate(Utils.VIBRATION_DURATION);
        }
    }

}