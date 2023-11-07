package Metodos.vol.api.Domain.CalculoRaiz;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CalculoR {

    //Atributos
    int iteracion;
    double xi;
    double ncs;
    double es;
    double a; //este a es cualquier numero que se quiera calcular la raiz cubica
    double e;
    double fxi;
    double dfxi;
    double xi1;
    //Constructor

    public CalculoR(double xi, double ncs, double a) {
        this.xi = xi;
        this.ncs = ncs;
        this.a = a;
        this.iteracion = 1;
        this.e = 100;
    }
    //metodos

    //Metodo principal de la clase
    public List<IteracionR> calcular(){
        List<IteracionR> resultados = new ArrayList<>();
        //Calculamos condición de salida
        cacularEs();
        //calculamos F(xi) y tambien df(xi)
        this.fxi = calcularF(this.xi);
        this.dfxi = calculatDf(this.xi);
        //Calculamos x+1
        this.xi1 = calcularX1(this.xi, this.fxi, this.dfxi);
        //Creamos un objeto de tipo IteracionR y guardamos
        IteracionR iteracionR = new IteracionR(iteracion, this.xi, this.fxi, this.dfxi, this.xi1, this.e);
        resultados.add(iteracionR);
        this.iteracion++;
        //Planteamos primeramente el ciclo
        while(this.e > this.es){
            //cambiamos el valor de xi por el de xi + 1
            this.xi = this.xi1;
            //calculamos F(xi) y tambien df(xi)
            this.fxi = calcularF(this.xi);
            this.dfxi = calculatDf(this.xi);
            //Calculamos x+1 pero guadamos su valor actual en una variable temporal
            double xi1Ant = this.xi1;
            this.xi1 = calcularX1(this.xi, this.fxi, this.dfxi);
            //calculamos el nuevo error
            this.e = calcularE(xi1Ant, this.xi1);
            //Creamos un objeto de tipo IteracionR y guardamos
            iteracionR = new IteracionR(iteracion, this.xi, this.fxi, this.dfxi, this.xi1, this.e);
            resultados.add(iteracionR);
            this.iteracion++;
        }
        return  resultados;
    }

    //La función que se va a utilizar en este caso sera: x^3 - a donde a es cualquier numero del que dueramos sacar la raiz

    //Metodo para calcular la F(xi)
    private double calcularF(double x){
        return Math.pow(x, 3) - this.a;
    }

    //la derivada de la función en este caso seria 3x^2
    //Metodo para poder calcular la df(Xi)
    private double calculatDf(double x){
        return 3 * Math.pow(x, 2);
    }

    //Metodo para poder calcular la x+1
    private double calcularX1(double xi, double fxi, double dfxi){
        return xi-(fxi / dfxi);
    }


    //Metodo para calcular la condición de salida
    private void cacularEs(){
        double elevado = 2 - this.ncs;
        this.es = 0.5 * Math.pow(10.0, elevado);
    }


    //Metodo para calcular el error
    private double calcularE(double anterior, double actual){
        return obtenerValorAbsoluto(((actual - anterior) / actual) *100);
    }

    //Metodo para poder calcular el valor absoluto de un numero double
    private static double obtenerValorAbsoluto(double numero) {
        return Math.abs(numero);
    }



}
