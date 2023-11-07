package Metodos.vol.api.Domain.falsaPosicion;


import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class CalculoFp {
    //atributos
    double ncs;
    double es;
    double xi;
    double xs;
    double xr;
    double fxi;
    double fxr;
    double e;

    //Este solo funciona para la funcion que es de x ^ 10 −1
    //Metodos propios de la clase

    //Metodo principal de la clase
    public List<IteracionFp> calcularBiseccion(){
        List<IteracionFp> resultados = new ArrayList<IteracionFp>();
        //llamamos al metodo para comprobar si es correcto el intervalo dado
        if(calcularF(this.xi) * calcularF(this.xs) >0){
            //No se cumple la condición por lo que no se puede solucionar por este medio
            return null;
        }
        //Creamos un atributo para poder contar las iteraciones
        int numeroIteracion = 1;
        //Calculamos la condición de salida
        cacularEs();

        //Guardmaos los resultados de las funciones evaluadas
        this.fxi = calcularF(this.xi);
        double fxs = calcularF(this.xs);
        //calculamos xr inicial
        this.xr = calcularXr(xi, xs, fxs, this.fxi);
        this.fxr = calcularF(this.xr);
        // crear una iteración y guardamos
        IteracionFp iteracion = new IteracionFp(numeroIteracion, this.xi, this.xs, this.xr, this.fxi, this.fxr, this.e);
        resultados.add(iteracion);
        numeroIteracion++;

        //Empezamos el metodo
        //por el momento vamos a poner la condicion de salido con un for para comprobar que los valores son correctos
        while(this.e > this.es) {
            //Con este ciclo se planea sacar las iteraciones de la tabla de resultados a partir de la segunda iteración
            //Primero calculamos el nuevo xi o xs
            if (calcularF(this.xi) * calcularF(xr) < 0){
                //si el resultado es menor nos indica que la raiz esta en el subintervalo izquierdo
                this.xs = xr;
            }else {
                // de lo contrario la raiz se encuentra en el intervalo derecho
                this.xi = xr;
            }
            //repetimos el proceso

            this.fxi = calcularF(this.xi);
            fxs = calcularF(this.xs);

            //calculamos la nueva xr
            //antes de actualizar el valor de xr guardamos su valor en una variable temporal para el calculo del error
            double anteriorXr = this.xr;
            this.xr = calcularXr(this.xi, this.xs, fxs, this.fxi);

            //calculamos las funciones

            this.fxr = calcularF(this.xr);

            //calculamos el error
            this.e = calcularE(anteriorXr, xr);

            //creamos una nueva iteración y guardamos
            IteracionFp iteracion2 = new IteracionFp(numeroIteracion, this.xi, this.xs, this.xr, this.fxi, this.fxr, this.e);
            resultados.add(iteracion2);

            numeroIteracion++;

            if(numeroIteracion > 200){
                System.out.println("Algo malo paso");
                break;
            }

        }

        return resultados;

    }

    //Metodo para calcular la F(xi) o para calcular la F(xr)
    private double calcularF(double xi) {
        return Math.pow(xi, 10) - 1;
    }

    //Metodo para calcular la condición de salida
    private void cacularEs(){
        double elevado = 2 - this.ncs;
        this.es = 0.5 * Math.pow(10.0, elevado);
    }

    private double calcularXr(double xi, double xs, double fxs, double fxi){
        return xs - ((fxs * (xi - xs)) / (fxi - fxs));
    }

    private double calcularE(double anterior, double actual){
        return obtenerValorAbsoluto(((actual - anterior) / actual) *100);
    }

    private static double obtenerValorAbsoluto(double numero) {
        return Math.abs(numero);
    }
}
