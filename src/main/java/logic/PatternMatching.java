package logic;

import model.Monomial;
import model.Polynomial;
import java.util.regex.*;
import java.util.regex.Matcher;


public class PatternMatching {

   private String polynomial;

    public PatternMatching(String polynomial) {
        this.polynomial = polynomial;
    }

    Polynomial pol = new Polynomial();

    public Polynomial createPolynomial() {

        String polynomialPattern = "([+-]?[^-+]+)";
        Pattern pattern = Pattern.compile(polynomialPattern);
        Matcher matcher = pattern.matcher(polynomial);

        while (matcher.find()) {

            String mon = matcher.group(1);
            int coefficient;
            int power;

            if (mon.contains("x")) {
                String parts[] = mon.split("x", 2);
                String part1 = parts[0];
                String part2 = parts[1];
                if (part1.length() == 0 || (part1.length()==1 && part1.charAt(0) == '+')) coefficient = 1;
                else if (part1.charAt(0) == '-' && part1.length() == 1) coefficient = -1;
                else coefficient = Integer.parseInt(part1);

                if (part2.length()==0) power=1;
                else
                    if(part2.contains("^"))
                    power = Integer.parseInt(part2.substring(1));
                    else power=1;

                Monomial monomial = new Monomial(coefficient,power);
                pol.addMonomial(monomial);

            }
            else {coefficient = Integer.parseInt(mon);
                power = 0;
            Monomial monomial =new Monomial(coefficient,power);
            pol.addMonomial(monomial);}
        }

        return pol;

    }
}
