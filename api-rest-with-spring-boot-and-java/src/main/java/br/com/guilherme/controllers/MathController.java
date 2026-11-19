package br.com.guilherme.controllers;

import br.com.guilherme.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) {
        validateNumbers(numberOne, numberTwo);
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @GetMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable("numberOne") String numberOne,
                              @PathVariable("numberTwo") String numberTwo) {
        validateNumbers(numberOne, numberTwo);
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @GetMapping("/multiply/{numberOne}/{numberTwo}")
    public Double multiply(@PathVariable("numberOne") String numberOne,
                           @PathVariable("numberTwo") String numberTwo) {
        validateNumbers(numberOne, numberTwo);
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne,
                           @PathVariable("numberTwo") String numberTwo) {
        validateNumbers(numberOne, numberTwo);

        if (convertToDouble(numberTwo) == 0) {
            throw new UnsupportedMathOperationException("Please enter a dividend value orther than 0!");
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo) {
        validateNumbers(numberOne, numberTwo);
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2.0;
    }

    @GetMapping("/square-root/{number}")
    public Double squareRoot(@PathVariable("number") String number) {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return Math.sqrt(convertToDouble(number));
    }

    private double convertToDouble(String strNumber) {
        if (isNullOrEmpty(strNumber)) {
            throw new UnsupportedMathOperationException("Please provide a non-null and non-empty value");
        }

        String number = replace(strNumber);
        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if (isNullOrEmpty(strNumber)) {
            return false;
        }

        String number = replace(strNumber);

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private boolean isNullOrEmpty(String strNumber) {
        return strNumber == null || strNumber.isEmpty();
    }

    private String replace(String strNumber) {
        return strNumber.replace(",", ".");
    }

    private void validateNumbers(String numberOne, String numberTwo) {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
    }
}
