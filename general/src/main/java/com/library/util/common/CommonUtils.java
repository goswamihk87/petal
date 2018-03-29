package com.library.util.common;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by agile-01 on 6/7/2017.
 * put all common utility methods here
 * Avoid context related/asynchronous/long running methods here
 */
public class CommonUtils {

    private CommonUtils() {
        //no direct instances allowed.
    }

    /**
     * finds all digits from string (excluding fractional parts - only 0-9)
     *
     * @param str string to parse
     * @return all digits [0-9] string
     */
    public static String getDigits(final String str) {
        return str.replaceAll("\\D+", "");
    }

    /**
     * finds all digits from string (including fractional digits)
     *
     * @param str string to parse
     * @return numerical (including fractional point(.)) string
     */
    public static String getDigitsIncludingFractions(String str) {
        return str.replaceAll("[^\\d.]", "");
    }

    /**
     * <p>Checks whether the given String is a parsable number.</p>
     * <p>
     * <p>Parsable numbers include those Strings understood by {@link Integer#parseInt(String)},
     * {@link Long#parseLong(String)}, {@link Float#parseFloat(String)} or
     * {@link Double#parseDouble(String)}. This method can be used instead of catching {@link java.text.ParseException}
     * when calling one of those methods.</p>
     * <p>
     * <p>Hexadecimal and scientific notations are <strong>not</strong> considered parsable.
     * <p>
     * <p>{@code Null} and empty String will return <code>false</code>.</p>
     *
     * @param str the String to check.
     * @return {@code true} if the string is a parsable number.
     */
    public static boolean isParsable(final String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.charAt(str.length() - 1) == '.') {
            return false;
        }
        if (str.charAt(0) == '-') {
            return str.length() != 1 && parseDecimal(str, 1);
        }
        return parseDecimal(str, 0);
    }

    /**
     * returns divisor only if +ve value, otherwise 1
     */
    public static long getSafeDivisor(long divisor) {
        return divisor > 0 ? divisor : 1;
    }

    /**
     * clears focus from all edit texts
     *
     * @param viewGroup root viewgroup to remove focus from its child views
     */
    public static void clearFocusRecursive(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                clearFocusRecursive((ViewGroup) view);
            } else {
                if (view instanceof EditText) {
                    view.clearFocus();
                }
            }
        }
    }

    //region private internal methods
    private static boolean parseDecimal(final String str, final int beginIdx) {
        int decimalPoints = 0;
        for (int i = beginIdx; i < str.length(); i++) {
            final boolean isDecimalPoint = str.charAt(i) == '.';
            if (isDecimalPoint) {
                decimalPoints++;
            }
            if (decimalPoints > 1) {
                return false;
            }
            if (!isDecimalPoint && !Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    //endregion

}
