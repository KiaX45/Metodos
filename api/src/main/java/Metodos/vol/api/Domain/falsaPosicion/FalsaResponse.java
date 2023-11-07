package Metodos.vol.api.Domain.falsaPosicion;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class FalsaResponse {
    private List<IteracionFp> iteraciones;
}
