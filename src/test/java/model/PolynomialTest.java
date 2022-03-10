package model;

import logic.PatternMatching;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    void add() {
        PatternMatching patternMatching1 = new PatternMatching("4x^3-2x+5");
        Polynomial firstPolynomial = patternMatching1.createPolynomial();

        PatternMatching patternMatching2 = new PatternMatching("2x^2+2x");
        Polynomial secondPolynomial = patternMatching2.createPolynomial();

        Polynomial result = firstPolynomial.add(secondPolynomial);
        assertEquals("+4x^3 +2x^2 +5", result.printPolynomial());

        System.out.println("The test was successful");


    }

    @Test
    void subtract() {
        PatternMatching patternMatching1 = new PatternMatching("x^2-x");
        Polynomial firstPolynomial = patternMatching1.createPolynomial();

        PatternMatching patternMatching2 = new PatternMatching("x^3-x+1");
        Polynomial secondPolynomial = patternMatching2.createPolynomial();

        Polynomial result = firstPolynomial.subtract(secondPolynomial);
        assertEquals("-1x^3 +x^2 -1", result.printPolynomial());

        System.out.println("The test was successful");
    }

    @Test
    void multiply() {
        PatternMatching patternMatching1 = new PatternMatching("x+1");
        Polynomial firstPolynomial = patternMatching1.createPolynomial();

        PatternMatching patternMatching2 = new PatternMatching("x+2");
        Polynomial secondPolynomial = patternMatching2.createPolynomial();

        Polynomial result = firstPolynomial.multiply(secondPolynomial);
        assertEquals("+x^2 +3x +2", result.printPolynomial());

        System.out.println("The test was successful");
    }

    @Test
    void differentiate() {
        PatternMatching patternMatching1 = new PatternMatching("8x^4");
        Polynomial firstPolynomial = patternMatching1.createPolynomial();

        Polynomial result = firstPolynomial.differentiate();
        assertEquals("+32x^3 ", result.printPolynomial());

        System.out.println("The test was successful");
    }

    @Test
    void integrate() {
        PatternMatching patternMatching1 = new PatternMatching("-x+2");
        Polynomial firstPolynomial = patternMatching1.createPolynomial();


        Polynomial result = firstPolynomial.integrate();
        assertEquals("-0.5x^2 + 2x ", result.printPolynomial());

        System.out.println("The test was successful");
    }

    @Test
    void divide() {
        PatternMatching patternMatching1 = new PatternMatching("x^2+2x+2");
        Polynomial firstPolynomial = patternMatching1.createPolynomial();

        PatternMatching patternMatching2 = new PatternMatching("x+1");
        Polynomial secondPolynomial = patternMatching2.createPolynomial();

        ArrayList<Polynomial> result = firstPolynomial.divide(secondPolynomial);

        Polynomial Q = result.get(0);
        String textResult = "";
        textResult=textResult + "Q: ";
        for (Monomial mon : Q.getPolynomial())
            textResult = textResult + mon.toString();
        Polynomial R = result.get(1);
        textResult=textResult + " R: ";
        for (Monomial mon : R.getPolynomial())
            textResult = textResult + mon.toString();

        assertEquals("Q: +x +1.0 R: +1.0", textResult);
        System.out.println("The test was successful");
    }
}