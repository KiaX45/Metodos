package Metodos.vol.api.Domain.TercerPunto.Newton;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Newton3PResponse {
    private List<IterNew3P> iteraciones;
}
