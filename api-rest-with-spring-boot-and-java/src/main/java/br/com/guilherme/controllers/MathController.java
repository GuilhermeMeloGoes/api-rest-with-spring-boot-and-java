package br.com.guilherme.controllers;

import br.com.guilherme.exceptions.UnsupportedMathOperationException;
import br.com.guilherme.math.SimpleMath;
import br.com.guilherme.resquest.converters.NumberConverterValidate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    private final SimpleMath simpleMath = new SimpleMath();

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) {
        NumberConverterValidate.validateNumbers(numberOne, numberTwo);
        return simpleMath.sum(NumberConverterValidate.convertToDouble(numberOne), NumberConverterValidate.convertToDouble(numberTwo));
    }

    @GetMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable("numberOne") String numberOne,
                              @PathVariable("numberTwo") String numberTwo) {
        NumberConverterValidate.validateNumbers(numberOne, numberTwo);
        return simpleMath.subtraction(NumberConverterValidate.convertToDouble(numberOne), NumberConverterValidate.convertToDouble(numberTwo));
    }

    @GetMapping("/multiply/{numberOne}/{numberTwo}")
    public Double multiply(@PathVariable("numberOne") String numberOne,
                           @PathVariable("numberTwo") String numberTwo) {
        NumberConverterValidate.validateNumbers(numberOne, numberTwo);
        return simpleMath.multiply(NumberConverterValidate.convertToDouble(numberOne), NumberConverterValidate.convertToDouble(numberTwo));
    }

    @GetMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne,
                           @PathVariable("numberTwo") String numberTwo) {
        NumberConverterValidate.validateNumbers(numberOne, numberTwo);

        if (NumberConverterValidate.convertToDouble(numberTwo) == 0) {
            throw new UnsupportedMathOperationException("Please enter a dividend value orther than 0!");
        }
        return simpleMath.division(NumberConverterValidate.convertToDouble(numberOne), NumberConverterValidate.convertToDouble(numberTwo));
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo) {
        NumberConverterValidate.validateNumbers(numberOne, numberTwo);
        return simpleMath.mean(NumberConverterValidate.convertToDouble(numberOne), NumberConverterValidate.convertToDouble(numberTwo));
    }

    @GetMapping("/square-root/{number}")
    public Double squareRoot(@PathVariable("number") String number) {
        if (!NumberConverterValidate.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        double value = NumberConverterValidate.convertToDouble(number);

        if (value < 0) {
            throw new UnsupportedMathOperationException(
                    "Square root of a negative number is not supported!"
            );
        }

        return simpleMath.squareRoot(NumberConverterValidate.convertToDouble(number));
    }
}
