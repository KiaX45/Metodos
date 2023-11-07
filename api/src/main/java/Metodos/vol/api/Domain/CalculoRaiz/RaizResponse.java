package Metodos.vol.api.Domain.CalculoRaiz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RaizResponse {
    private List<IteracionR> resultados;
}
