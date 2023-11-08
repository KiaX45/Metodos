package Metodos.vol.api.Domain.TercerPunto.Biseccion;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Biseccion3PResponse {
    private List<IterBisec3P> iteraciones;
}
