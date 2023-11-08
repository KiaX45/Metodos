package Metodos.vol.api.Domain.TercerPunto.Secante;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IterSecante3p {
    //atributos
    int iteracion;
    double xi0;
    double xi;
    double fxi0;
    double fxi;
    double xi1;
    double e;

    //metodo to String

    @Override
    public String toString() {
        return String.format("Iteracion %d: xi-1 = %.8f xi = %.8f f(xi-1) = %.8f f(xi) = %.8f xi+1 = %.8f e = %.8f%%",
                iteracion, xi0, xi, fxi0, fxi, xi1, e);
    }
}
