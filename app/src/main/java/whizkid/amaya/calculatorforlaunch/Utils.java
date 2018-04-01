package whizkid.amaya.calculatorforlaunch;

public class Utils {

    public static final String ADD = "+";
    public static final String SUBTRACT = "-";
    public static final String MULTIPLY = "*";
    public static final String DIVIDE = "/";
    public static final String ALL_CLEAR = "ALLCLEAR";
    public static final String EVALUATE = "=";
    public static final String MEMORY_SAVED_VALE = "MEMORY_SAVED_VALE";
    public static final String MEMORY_PREFIX ="M ";
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

    /**
     * Computes the file
     * @param equation
     * @return
     */
    static String evalMe(String equation) {

        if(equation == null || equation.trim().length() == 0) {
            return "";
        }

        System.out.println("equation " + equation);

        double result = 0.0;
        String noMinus = equation.replace("-", "+-");
        String[] byPluses = noMinus.split("\\+");

        for (String multipl : byPluses) {
            String[] byMultipl = multipl.split("\\*");
            double multiplResult = 1.0;
            for (String operand : byMultipl) {
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

        System.out.println("result " + result);
        return Double.toString(result);
    }

}
