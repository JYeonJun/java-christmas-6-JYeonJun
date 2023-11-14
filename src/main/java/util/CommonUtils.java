package util;

import static util.ErrorMessageVO.*;

import java.text.DecimalFormat;

public class CommonUtils {

    public static int stringToInt(String str) {

        int result = 0;

        try {
            result = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT_EXCEPTION);
        }

        return result;
    }

    public static String addCommaToPrice(int value) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(value);
    }
}
