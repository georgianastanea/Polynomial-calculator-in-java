package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial {

    private ArrayList<Monomial> polynomial = new ArrayList<>();

    public Polynomial() {
        this.polynomial = polynomial;
    }

    public ArrayList<Monomial> getPolynomial() {
        return polynomial;
    }

    public void addMonomial(Monomial mon){
        polynomial.add(mon);
    }

    public Polynomial copyPolynomial(Polynomial pol)
    {
        Polynomial copy = new Polynomial();

        for(Monomial mon: pol.getPolynomial()){
            int power = mon.getPower();
            int coeffiecient = mon.getCoefficient();
            Monomial monom = new Monomial(coeffiecient,power);
            copy.addMonomial(monom);
        }
        return copy;

    }

    public String printPolynomial(){
        String out = "";
        for(Monomial mon: this.getPolynomial())
            out = out + mon;
        return out;
    }

    public void minimise(){
        for(int i=0;i<polynomial.size();i++){
            int power = polynomial.get(i).getPower();
            for(int j= i + 1; j< polynomial.size();j++){
                int auxPower = polynomial.get(j).getPower();
                if(power == auxPower){
                    if(polynomial.get(i).getDoubleCoefficient() != 0.0 && polynomial.get(i).getCoefficient() == 0)
                    { polynomial.get(i).setDoubleCoefficient(polynomial.get(i).getCoefficient() + polynomial.get(j).getCoefficient() + polynomial.get(i).getDoubleCoefficient() + polynomial.get(j).getDoubleCoefficient());
                    this.polynomial.remove(j);}
                    else if(polynomial.get(i).getDoubleCoefficient() == 0.0 && polynomial.get(i).getCoefficient() != 0){
                        polynomial.get(i).setCoefficient(polynomial.get(i).getCoefficient() + polynomial.get(j).getCoefficient());
                        this.polynomial.remove(j);
                    }

                }
            }
        }
        Collections.sort(this.polynomial, Monomial.compareByPower());
    }


    public void sort(){
        Collections.sort(polynomial, new Comparator<Monomial>() {
            @Override
            public int compare(Monomial o1, Monomial o2) {
                if(o1.getPower() > o2.getPower()) return  -1;
                else if(o1.getPower() == o2.getPower()) return 0;
                else return 1;
            }
        });
    }
    //Addition

    public Polynomial add( Polynomial pol2) {
        Polynomial sumPolynomial = new Polynomial();
       int i=0,j=0;
       while(i < this.getPolynomial().size() && j< pol2.getPolynomial().size()){

            Monomial mon1 = this.getPolynomial().get(i);
            Monomial mon2 = pol2.getPolynomial().get(j);

            if(mon1.getPower() > mon2.getPower()){
                sumPolynomial.addMonomial(mon1);
                i++;
            }
            else if(mon1.getPower() < mon2.getPower()){
                sumPolynomial.addMonomial(mon2);
                j++;
            }
            else{
                Monomial sumMon;
                if (mon1.getCoefficient()!=0 && mon2.getCoefficient()!=0)
                   sumMon = new Monomial(mon1.getCoefficient()+ mon2.getCoefficient(),mon1.getPower());
                else if (mon2.getCoefficient()==0 && mon1.getCoefficient()!=0)
                    sumMon = new Monomial(mon1.getCoefficient()+ mon2.getDoubleCoefficient(),mon1.getPower());
                else if (mon1.getCoefficient()==0 && mon2.getCoefficient()!=0)
                    sumMon = new Monomial(mon2.getCoefficient()+ mon1.getDoubleCoefficient(),mon1.getPower());
                else
                    sumMon = new Monomial(mon1.getDoubleCoefficient()+ mon2.getDoubleCoefficient(),mon1.getPower());
                if (sumMon.getCoefficient()!=0 || sumMon.getDoubleCoefficient()!=0.0)
                    sumPolynomial.addMonomial(sumMon);
                i++; j++;
            }

       }

       if(i < this.getPolynomial().size()) {
           while (i < this.getPolynomial().size()) {
               Monomial auxMon = this.getPolynomial().get(i);
               sumPolynomial.addMonomial(auxMon);
               i++;
           }
       }
           if (j < pol2.getPolynomial().size()) {
               while (j < pol2.getPolynomial().size()) {
                   Monomial auxMon1 = pol2.getPolynomial().get(j);
                   sumPolynomial.addMonomial(auxMon1);
                   j++;
               }

           }

    return sumPolynomial;

    }
    //Subtraction

    public Polynomial subtract( Polynomial pol2){
        for(Monomial mon: pol2.getPolynomial())
        {
            if (mon.getCoefficient()!=0)
                mon.setCoefficient(-(mon.getCoefficient()));
            else
                mon.setDoubleCoefficient(-(mon.getDoubleCoefficient()));
        }
        Polynomial subtractPolynomial = new Polynomial();
        subtractPolynomial = this.add(pol2);
        return subtractPolynomial;
    }

    //Multiplication

    public Polynomial multiply(Polynomial pol2){
        Polynomial multipliedPolynomial = new Polynomial();

        for(Monomial mon1 : this.getPolynomial())
        {
            for(Monomial mon2: pol2.getPolynomial()){
                int coef=0;
                double doublecoef=0.0;
                if (mon2.getDoubleCoefficient()!=0.0 && mon1.getDoubleCoefficient()!=0.0)
                    doublecoef=mon1.getDoubleCoefficient()* mon2.getDoubleCoefficient();
                else if (mon2.getDoubleCoefficient()!=0.0)
                    doublecoef=mon2.getDoubleCoefficient()* mon1.getCoefficient();
                else if (mon1.getDoubleCoefficient()!=0.0)
                    doublecoef=mon1.getDoubleCoefficient()*mon2.getCoefficient();
                else
                    coef = mon1.getCoefficient() * mon2.getCoefficient();
                int power = mon1.getPower() + mon2.getPower();
                Monomial auxMon = null;
                if (coef!=0 && doublecoef == 0.0)
                   auxMon = new Monomial(coef,power);
                else if(coef == 0 && doublecoef != 0.0)
                    auxMon = new Monomial (doublecoef,power);
                multipliedPolynomial.addMonomial(auxMon);
            }
        }
        multipliedPolynomial.minimise();
        multipliedPolynomial.sort();
        return multipliedPolynomial;

    }
    //Differentiation

    public Polynomial differentiate(){
        Polynomial differentiatedPolynomial = new Polynomial();
        for(Monomial mon: this.getPolynomial()){
            int coef,power;
            if(mon.getPower() == 0) {coef =0; power=0;}
            else{
             coef = mon.getCoefficient() * mon.getPower();
             power = mon.getPower() - 1;}
            Monomial auxMon = new Monomial(coef,power);
            differentiatedPolynomial.addMonomial(auxMon);
        }
        return differentiatedPolynomial;
    }

    //Integration

    public Polynomial integrate(){
        Polynomial integratedPolynomial = new Polynomial();
        for(Monomial mon: this.getPolynomial()){
            int power;
            double coefficient;
            coefficient = (double) mon.getCoefficient()/(mon.getPower() +1);
            power = mon.getPower() + 1;
            Monomial auxMon = new Monomial(coefficient,power);
            integratedPolynomial.addMonomial(auxMon);
        }
        integratedPolynomial.sort();
        return integratedPolynomial;
    }

    // Division

    public ArrayList<Polynomial> divide(Polynomial pol2){
        this.sort();
        pol2.sort();
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();
        ArrayList<Polynomial> result = new ArrayList<>();
        remainder = this.copyPolynomial(this);
        Monomial monFromPol1 = remainder.getPolynomial().get(0);
        Monomial monFromPol2 = pol2.getPolynomial().get(0);

        while((monFromPol1.getPower() >= monFromPol2.getPower()) && !(remainder.getPolynomial().isEmpty())){
            int power  = monFromPol1.getPower() - monFromPol2.getPower();
            double doublecoef;
            if (monFromPol1.getDoubleCoefficient()!=0.0 && monFromPol2.getDoubleCoefficient()!=0.0)
                doublecoef=monFromPol1.getDoubleCoefficient()/ monFromPol2.getDoubleCoefficient();
            else if (monFromPol2.getDoubleCoefficient()!=0.0)
                doublecoef=monFromPol1.getCoefficient()/monFromPol2.getDoubleCoefficient();
            else if (monFromPol1.getDoubleCoefficient()!=0.0)
                doublecoef=monFromPol1.getDoubleCoefficient()/monFromPol2.getCoefficient();
            else
                doublecoef = monFromPol1.getCoefficient() / monFromPol2.getCoefficient();

            Monomial mon = new Monomial(doublecoef,power);
           quotient.getPolynomial().add(mon);
            Polynomial auxPol = new Polynomial();
            auxPol.addMonomial(mon);
            remainder = remainder.subtract((pol2.multiply(auxPol)));
            Collections.sort(remainder.getPolynomial());
            if(!remainder.getPolynomial().isEmpty()) {
                monFromPol1 = remainder.getPolynomial().get(0);
            }
        }
        quotient.minimise();
        quotient.sort();
        result.add(quotient);
        remainder.minimise();
        remainder.sort();
        result.add(remainder);
        return result;
    }







}
