package br.com.guilherme.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @PostMapping("/sum/{number1}/{number2}")
    public Double sum(
            @PathVariable("number1") String number1,
            @PathVariable("number2") String number2) {

        if (!isNumeric(number1) || !isNumeric(number2)) {
            throw new UnsupportedOperationException("Please set a numeric value!");
        }
        return convertToDouble(number1) + convertToDouble(number2);
    }

    private double convertToDouble(String strNumber) {
        if (isNUllOrEmpty(strNumber)) {
           throw new UnsupportedOperationException("Please set a diferente value null or empty!");
        }

        String number = replace(strNumber);

        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if (isNUllOrEmpty(strNumber)) {
            return false;
        }

        String number = replace(strNumber);

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private boolean isNUllOrEmpty(String strNumber) {
        return strNumber == null || strNumber.isEmpty();
    }

    private String replace (String strNumber) {
        return strNumber.replace(",", ".");
    }


}
