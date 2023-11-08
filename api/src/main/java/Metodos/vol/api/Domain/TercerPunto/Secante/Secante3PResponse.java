package Metodos.vol.api.Domain.TercerPunto.Secante;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Secante3PResponse {
    private List<IterSecante3p> resultados;
}
