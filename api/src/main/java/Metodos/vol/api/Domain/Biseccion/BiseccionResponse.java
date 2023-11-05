package Metodos.vol.api.Domain.Biseccion;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class BiseccionResponse {
    private List<Iteracion> iteraciones;
}

