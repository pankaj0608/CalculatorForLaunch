package whizkid.amaya.calculatorforlaunch;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import java.util.ArrayList;

public class Utils {

    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
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


    public static boolean isTagOperator(String tag) {
        return ADD.equals(tag) || SUBTRACT.equals(tag)
                || MULTIPLY.equals(tag) || DIVIDE.equals(tag);
    }

    public static boolean isTagEvaluateOperator(String tag) {
        return EVALUATE.equals(tag);
    }

    public static boolean isItClearTagOperator(String tag) {
        return ALL_CLEAR.equals(tag);
    }


    public static String[] splitMyString(String problem) {
        String[] tokens = problem.split("\\+|\\-|\\*|\\/");

        return tokens;
    }


    public static boolean containsAnyTagAlready(String str, String searchChars) {
        if (searchChars == null) {
            return false;
        }
        return containsAny(str, searchChars.toCharArray());
    }

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

    static String correctEquation(String equation) {
        //equation = equation.replace("-", "+-");;

        //remove the initil 0s
        if (equation != null && equation.length() == 2 &&
                equation.startsWith("0")
                && (Utils.isNumeric(equation.substring(1, 2)))) {
            equation = equation.substring(1, equation.length());
        }

        equation = equation.replace("**", "*");
        equation = equation.replace("*/", "/");
        equation = equation.replace("/*", "*");
        equation = equation.replace("++", "+");

        return equation;
    }


    static String correctResult(String result) {
        //equation = equation.replace("-", "+-");;

        //remove the initil 0s
        if (result != null && result.trim().length() == 0 ) {
            return "0";
        }


        if(result.endsWith(".0") && result.length() > ".0".length()) {
            result = result.substring(0,result.length()-2);
        }
        return result;
    }


    static String inverselMe(String equation) {
        return "";
    }

    public static String evalMe(String str) {

        if (str == null || str.trim().length() == 0) {
            return "0";
        }

        if (str.endsWith(Utils.DIVIDE) || str.endsWith(Utils.MULTIPLY)
                || str.endsWith(Utils.ADD) || str.endsWith(Utils.SUBTRACT)) {
            return evalMe(str.substring(0, str.length() - 1));
        }

        double result = evalMeDouble(str);
        return correctResult(Double.toString(result));
    }

//    I've written this eval method for arithmetic expressions to answer this question.
//    It does addition, subtraction, multiplication, division, exponentiation (using the ^ symbol), a
//    nd a few basic functions like sqrt. It supports grouping using (...), and it gets the operator
//    precedence and associativity rules correct.
//
//    https://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form

    public static double evalMeDouble(final String str) {

        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }


    static String evalMe_Old_With_Issues(String equation) {

        System.out.println("equation " + equation);

        if (equation == null || equation.trim().length() == 0) {
            return "";
        }

        if (equation.endsWith(Utils.DIVIDE) || equation.endsWith(Utils.MULTIPLY)
                || equation.endsWith(Utils.ADD) || equation.endsWith(Utils.SUBTRACT)) {

            return evalMe(equation.substring(0, equation.length() - 1));
        }

        double result = 0.0;

        //pankaj added
        equation = equation.replace("--", "+");

        String noMinus = equation.replace("-", "+-");
//        String[] byPluses = noMinus.split("\\+");

        //pankaj added remove ""
        String[] byPluses = removeBlankEnteries(noMinus.split("\\+"));

        System.out.println("byPluses.length " + byPluses.length);

        for (String multipl : byPluses) {
            String[] byMultipl = multipl.split("\\*");
            double multiplResult = 1.0;
            for (String operand : byMultipl) {
                //pankaj added
                if (operand == null || operand.trim().equals("")) {
                    continue;
                }
                if (operand.contains("/")) {
                    String[] division = operand.split("\\/");
                    double divident = Double.parseDouble(division[0]);
                    for (int i = 1; i < division.length; i++) {
                        divident /= Double.parseDouble(division[i]);
                    }
                    multiplResult *= divident;
                } else {
                    multiplResult *= Double.parseDouble(operand);
                }
            }
            result += multiplResult;
        }

        return Double.toString(result);
    }

    /**
     * @param received
     * @return
     */
    static String[] removeBlankEnteries(String[] received) {
        ArrayList<String> validList = new ArrayList<>();

        for (int i = 0; i < received.length; i++) {
            if (received[i] != null && !received[i].trim().equals("")) {
                validList.add(received[i]);
            }
        }
        String result[] = new String[validList.size()];
        return validList.toArray(result);
    }


    public static String getValueFromSharedPreference(final String key,
                                                      SharedPreferences sharedPreferences) {

        return sharedPreferences.getString(key, "");

    }


    public static void putStringInSharedPreference(final String key, final String value,
                                                   SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    static boolean isNotNullString(String val) {

        return val != null && val.trim().length() > 0;
    }

    static boolean isEditTextEmpty(EditText editText) {

        return false;
    }

    static boolean isNumeric(String val) {

        try {
            Integer.parseInt(val);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Computes the file
     * @param equation
     * @return
     */
//    static String evalMeOriginal(String equation) {
//
//        if(equation == null || equation.trim().length() == 0) {
//            return "";
//        }
//
//        System.out.println("equation " + equation);
//
//        double result = 0.0;
//        String noMinus = equation.replace("-", "+-");
//        String[] byPluses = noMinus.split("\\+");
//
//        for (String multipl : byPluses) {
//            String[] byMultipl = multipl.split("\\*");
//            double multiplResult = 1.0;
//            for (String operand : byMultipl) {
//                if (operand.contains("/")) {
//                    String[] division = operand.split("\\/");
//                    double divident = Double.parseDouble(division[0]);
//                    for (int i = 1; i < division.length; i++) {
//                        divident /= Double.parseDouble(division[i]);
//                    }
//                    multiplResult *= divident;
//                } else {
//                    multiplResult *= Double.parseDouble(operand);
//                }
//            }
//            result += multiplResult;
//        }
//
//        System.out.println("result " + result);
//        return Double.toString(result);
//    }

}
