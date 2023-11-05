package Metodos.vol.api.Domain.falsaPosicion;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class IteracionFp {
    //atributos
    public static int numeroTotal;
    int numeroIteracion;
    double xi;
    double xs;
    double xr;
    double fxi;
    double fxr;
    double e;

    //Constructor de la clase
    public IteracionFp(int iteracion, double xi, double xs, double xr, double fxi, double fxr, double e) {
        this.numeroIteracion = iteracion;
        this.xi = xi;
        this.xs = xs;
        this.xr = xr;
        this.fxi = fxi;
        this.fxr = fxr;
        this.e = e;
        numeroTotal++; // Incrementa el contador cada vez que se crea una instancia
    }

    // Sobrescribir el método toString()
    @Override
    public String toString() {
        return String.format("Iteración %d: xi = %.5f, xs = %.5f, xr = %.5f, f(xi) = %.5f, f(xr) = %.5f, e = %.5f%%",
                numeroIteracion, xi, xs, xr, fxi, fxr, e);
    }

}

