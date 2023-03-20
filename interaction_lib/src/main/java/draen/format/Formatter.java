package draen.format;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Formatter {
    private final static DecimalFormat decimalFormat = new DecimalFormat("0.###");
    private static final DecimalFormat withPrecisionDecimalFormat = new DecimalFormat("0.##");
    private static final DecimalFormat exactDecimalFormat = new DecimalFormat("0.###E0");

    public static String formatDefault(double num) {
        return decimalFormat.format(num);
    }
    public static String formatWithPrecision(double num) {
        return withPrecisionDecimalFormat.format(num);
    }
    public static String formatExact(double num) {
        return exactDecimalFormat.format(num);
    }


    public static void setPrecision(double precision) {
        if (precision <= 1e-20) throw new IllegalArgumentException("Cannot work with precision like this!");
        if (precision >= 1) {
            withPrecisionDecimalFormat.applyPattern("0");
        } else {
            withPrecisionDecimalFormat.applyPattern("0." + "#".repeat(getDigitsNum(precision)));
        }

    }

    private static int getDigitsNum(double num) {
        BigDecimal decimal = BigDecimal.valueOf(num);
        String text = decimal.toPlainString();
        String[] split = text.split("\\.");
        if (split.length > 1) {
            int i = 1;
            for (char c : split[1].toCharArray()) {
                if (c != '0') break;
                i++;
            }
            return i;
        }
        return 0;
    }
}
