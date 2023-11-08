package Metodos.vol.api.Domain.TercerPunto.Secante;

import java.util.ArrayList;
import java.util.List;

public class Secante3P {
    //Atributos
    double ncs;
    double es;
    double xi;
    double xi0;
    double xi1;
    double fxi0;
    double fxi;
    double e;
    int iteracion;

    //constructor de la clase

    public Secante3P(double ncs, double xi, double xi0) {
        this.ncs = ncs;
        this.xi = xi;
        this.xi0 = xi0;
        this.e= 100;
        this.iteracion = 1;
    }

    //Metodos de la clase

    //Metodo principal de la clase
    public List<IterSecante3p> calcular(){
        List<IterSecante3p> resultados = new ArrayList<>();

        //En este caso no necesitamos comprobar si el intervalo cumple con la condición
        //calculamos la condición de salida
        cacularEs();
        System.out.println(this.es);
        //calculamos f(xi) y f(xi-1)
        this.fxi = calcularF(this.xi);
        this.fxi0 = calcularF(this.xi0);
        //calculamos la x+1
        this.xi1 = calcularxi1(this.xi, this.xi0, this.fxi, this.fxi0);
        //creamos un objeto y guardamos
        IterSecante3p iteracionR = new IterSecante3p(this.iteracion, this.xi0, this.xi, this.fxi0, this.fxi, this.xi1, this.e);
        resultados.add(iteracionR);
        this.iteracion++;

        //Empezamos el ciclo
        while(this.e > this.es){
            //cambiamos los numeros de xi y xi-1
            this.xi0 = this.xi;
            this.xi = this.xi1;
            //calculamos f(xi) y f(xi-1)
            this.fxi = calcularF(this.xi);
            this.fxi0 = calcularF(this.xi0);
            //calculamos la x+1 y guardamos su valor para el calculo del error
            double ant = this.xi1;
            this.xi1 = calcularxi1(this.xi, this.xi0, this.fxi, this.fxi0);
            //calculamos el e
            this.e = calcularE(ant, xi1);
            //creamos un objeto y guardamos
            iteracionR = new IterSecante3p(this.iteracion, this.xi0, this.xi, this.fxi0, this.fxi, this.xi1, this.e);
            resultados.add(iteracionR);
            this.iteracion++;
            if(this.iteracion > 200){
                System.out.println("algo salio mal");
                break;
            }
        }

        return resultados;
    }

    //Metodo para calcular la condición de salida
    private void cacularEs(){
        double elevado = 2 - this.ncs;
        this.es = 0.5 * Math.pow(10.0, elevado);
    }

    //Metodo para calcular f(xi) o f(xi-1)
    //Vamos a utilizar la funcion tang(x) - 0.5x
    private double calcularF(double xi){
        return Math.tan(xi) -0.5*(xi);
    }

    //Metodo para calcular la x+1
    private double calcularxi1(double xi, double xi0, double fxi, double fxi0){
        return xi - ((fxi*(xi0-xi))/(fxi0- fxi));
    }

    //Metodo para poder calcular el error
    private double calcularE(double anterior, double actual){
        return obtenerValorAbsoluto(((actual - anterior) / actual) *100);
    }

    private static double obtenerValorAbsoluto(double numero) {
        return Math.abs(numero);
    }

}
