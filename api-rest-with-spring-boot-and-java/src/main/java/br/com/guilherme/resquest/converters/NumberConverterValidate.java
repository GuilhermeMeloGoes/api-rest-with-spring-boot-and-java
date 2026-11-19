package br.com.guilherme.resquest.converters;

import br.com.guilherme.exceptions.UnsupportedMathOperationException;

public class NumberConverterValidate {

    public static double convertToDouble(String strNumber) {
        if (isNullOrEmpty(strNumber)) {
            throw new UnsupportedMathOperationException("Please provide a non-null and non-empty value");
        }

        String number = replace(strNumber);
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber) {
        if (isNullOrEmpty(strNumber)) {
            return false;
        }

        String number = replace(strNumber);

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static void validateNumbers(String numberOne, String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
    }

    private static boolean isNullOrEmpty(String strNumber) {
        return strNumber == null || strNumber.isEmpty();
    }

    private static String replace(String strNumber) {
        return strNumber.replace(",", ".");
    }

}
