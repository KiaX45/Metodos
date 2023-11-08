package Metodos.vol.api.Domain.TercerPunto.Newton;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Newton3P {

    //Atributos
    int iteracion;
    double xi;
    double ncs;
    double es;
    double e;
    double fxi;
    double dfxi;
    double xi1;
    //Constructor

    public Newton3P(double xi, double ncs) {
        this.xi = xi;
        this.ncs = ncs;
        this.iteracion = 1;
        this.e = 100;
    }
    //metodos

    //Metodo principal de la clase
    public List<IterNew3P> calcular(){
        List<IterNew3P> resultados = new ArrayList<>();
        //Calculamos condición de salida
        cacularEs();
        //calculamos F(xi) y tambien df(xi)
        this.fxi = calcularF(this.xi);
        this.dfxi = calculatDf(this.xi);
        //Calculamos x+1
        this.xi1 = calcularX1(this.xi, this.fxi, this.dfxi);
        //Creamos un objeto de tipo IteracionR y guardamos
        IterNew3P iteracionR = new IterNew3P(iteracion, this.xi, this.fxi, this.dfxi, this.xi1, this.e);
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
            iteracionR = new IterNew3P(iteracion, this.xi, this.fxi, this.dfxi, this.xi1, this.e);
            resultados.add(iteracionR);
            this.iteracion++;
        }
        return  resultados;
    }

    //La función que se va a utilizar en este caso sera: tan(x) - 0.5x donde a es cualquier numero del que dueramos sacar la raiz

    //Metodo para calcular la F(xi)
    private double calcularF(double x){
        return Math.tan(xi) - 0.5 * (xi);
    }

    //la derivada de la función en este caso seria sec(x)^2 - 0.5
    //Metodo para poder calcular la df(Xi)
    private double calculatDf(double x){
        double base = 1.0/Math.cos(x);
        return 3 * Math.pow(base, 2) - 0.5;
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
