package Metodos.vol.api.Domain.TercerPunto.Newton;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IterNew3P {
    //atributos
    int numeroIteracion;
    double xi;
    double fxi;
    double dfxi;
    double xi1;
    double e;

    //Metodo To String


    @Override
    public String toString() {
        return String.format("Iteracion %d: xi = %.8f f(xi) = %.8f df(xi) = %.8f xi+1 = %.8f e = %.8f%%",
                numeroIteracion, xi, fxi, dfxi, xi1, e);
    }
}
