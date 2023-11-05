package Metodos.vol.api.Domain.Biseccion;

import lombok.Data;

@Data
public class BiseccionRequest {
    private double ncs;
    private double es;
    private double xi;
    private double xs;
    // Los campos xr, fxi, fxr, e no son necesarios en el request ya que son calculados
}


