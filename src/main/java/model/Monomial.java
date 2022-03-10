package model;

import java.util.Comparator;

public class Monomial implements Comparable<Monomial> {

    private int coefficient;
    private int power;
    private double doubleCoefficient;


    public Monomial(int coefficient, int power) {
        this.power = power;
        this.coefficient = coefficient;
    }
    public Monomial(double doubleCoefficient, int power) {
        this.power = power;
        this.doubleCoefficient = doubleCoefficient;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public double getDoubleCoefficient() {
        return doubleCoefficient;
    }

    public void setDoubleCoefficient(double doubleCoefficient) {
        this.doubleCoefficient = doubleCoefficient;
    }

    public int getPower() {
        return power;
    }


    public static Comparator<Monomial> compareByPower(){
        Comparator<Monomial> comparator = new Comparator<Monomial>() {
            @Override
            public int compare(Monomial o1, Monomial o2) {
                return Integer.compare(o1.power, o2.power);
            }
        };
        return comparator;
    }


    @Override
    public String toString() {
        String text = "";

        if (coefficient != 0) {
            if (coefficient < 0) {
                if (power == 0) text = text + coefficient;
                else if (power == 1) text = text + coefficient + "x ";
                else text = text + coefficient + "x^" + power + " ";

            } else if(coefficient == 1)
            {
                if (power == 0) text = text + "+" + coefficient;
                else if (power == 1) text = text + "+" + "x ";
                else text = text +"+" + "x^" + power + " ";
            }

            else {
                if (power == 0) text = text + "+" + coefficient;
                else if (power == 1) text = text + "+" + coefficient + "x ";
                else text = text + "+" + coefficient + "x^" + power + " ";

            }

        }

         else if (doubleCoefficient != 0.0) {
            if (doubleCoefficient < 0.0) {
                if (power == 0) text = text + doubleCoefficient;
                else if (power == 1) text = text + doubleCoefficient + "x ";
                else text = text + doubleCoefficient + "x^" + power + " ";

            } else if(doubleCoefficient == 1.0)
            {
                if (power == 0) text = text + "+" + doubleCoefficient;
                else if (power == 1) text = text + "+" + "x ";
                else text = text  + "x^" + power + " ";
            }

            else {
                if (power == 0) text = text + "+" + doubleCoefficient;
                else if (power == 1) text = text + "+" + doubleCoefficient + "x ";
                else text = text + "+" + doubleCoefficient + "x^" + power + " ";

            }

        }

        return text;

    }

    public String toStringDouble() {
        String text = "";

        if (doubleCoefficient != 0.0) {
            if (doubleCoefficient < 0.0) {
                if (power == 0) text = text + doubleCoefficient;
                else if (power == 1) text = text + doubleCoefficient + "x ";
                else text = text + doubleCoefficient + "x^" + power + " ";

            } else if(doubleCoefficient == 1.0)
            {
                if (power == 0) text = text + "+" + doubleCoefficient;
                else if (power == 1) text = text + "+" + "x ";
                else text = text  + "x^" + power + " ";
            }

            else {
                if (power == 0) text = text + "+" + doubleCoefficient;
                else if (power == 1) text = text + "+" + doubleCoefficient + "x ";
                else text = text + "+" + doubleCoefficient + "x^" + power + " ";

            }

        }

        return text;

    }

    public int compareTo(Monomial o) {
        return o.getPower()-this.getPower();
    }


}
