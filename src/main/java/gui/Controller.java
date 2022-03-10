package gui;

import logic.PatternMatching;
import model.Monomial;
import model.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Controller implements ActionListener {

    private View view;

    public Controller(View v){
        this.view = v;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        PatternMatching patternMatching1 = new PatternMatching(view.getFirstPolynomialTextField().getText().toLowerCase());
        Polynomial firstPolynomial = patternMatching1.createPolynomial();

        PatternMatching patternMatching2 = new PatternMatching(view.getSecondPolynomialTextField().getText().toLowerCase());
        Polynomial secondPolynomial = patternMatching2.createPolynomial();

        Polynomial result = new Polynomial();
        ArrayList<Polynomial> divisionResult = new ArrayList<>();
        boolean isADoubleResult = false;
        boolean isDivision = false;



        switch (command){
            case "ADD" : result = firstPolynomial.add(secondPolynomial);
            break;
            case "SUBTRACT" : result = firstPolynomial.subtract(secondPolynomial);
            break;
            case "MULTIPLY" : result = firstPolynomial.multiply(secondPolynomial);
            break;
            case "DIFFERENTIATE" : result = firstPolynomial.differentiate();
            break;
            case "INTEGRATE" : {
                result = firstPolynomial.integrate();
                isADoubleResult = true;
            }
            break;
            case "DIVIDE" : {
               divisionResult = firstPolynomial.divide(secondPolynomial);
                isDivision = true;

            }
        }

        result.minimise();
        result.sort();

        String textResult ="";


        if(isADoubleResult == false && isDivision == false) {
            for (Monomial mon : result.getPolynomial())
                textResult = textResult + mon.toString();
            if (textResult.length() == 0) textResult = "0";
        }
        else if(isADoubleResult == true && isDivision == false){
            for (Monomial mon : result.getPolynomial())
                textResult = textResult + mon.toStringDouble();
            if (textResult.length() == 0) textResult = "0";
        }
        else{
            Polynomial Q = divisionResult.get(0);
            textResult=textResult + "Q: ";
                for (Monomial mon : Q.getPolynomial())
                    textResult = textResult + mon.toString();
            Polynomial R = divisionResult.get(1);
            textResult=textResult + " R: ";
            for (Monomial mon : R.getPolynomial())
                textResult = textResult + mon.toString();
            if (textResult.length() == 0) textResult = "0";


        }

        view.getResultValueLabel().setText(textResult);


    }

}
