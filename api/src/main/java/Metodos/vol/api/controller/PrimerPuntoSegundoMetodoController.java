package Metodos.vol.api.controller;

import Metodos.vol.api.Domain.Biseccion.BiseccionRequest;
import Metodos.vol.api.Domain.Biseccion.BiseccionResponse;
import Metodos.vol.api.Domain.Biseccion.Calculo;
import Metodos.vol.api.Domain.Biseccion.Iteracion;
import Metodos.vol.api.Domain.falsaPosicion.CalculoFp;
import Metodos.vol.api.Domain.falsaPosicion.FalsaRequest;
import Metodos.vol.api.Domain.falsaPosicion.FalsaResponse;
import Metodos.vol.api.Domain.falsaPosicion.IteracionFp;
import Metodos.vol.api.Domain.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrimerPuntoSegundoMetodoController {
    @PostMapping("/falsa1P")
    public ResponseEntity<ApiResponse> calcularBiseccion(@RequestBody FalsaRequest request) {
        CalculoFp calculo = new CalculoFp(
                request.getNcs(),
                0,    // Tambien se lo inicializa en cero puesto que luego se va a calcular
                request.getXi(),
                request.getXs(),
                0, // xr inicializado en 0
                0, // fxi inicializado en 0
                0, // fxr inicializado en 0
                100 // e inicializado en 100
        );
        List<IteracionFp> resultados = calculo.calcularFP();

        if (resultados == null) {
            // Si no se puede calcular, devuelve un estado HTTP 400 Bad Request
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse(null, "No se puede calcular la bisección con los datos proporcionados."));
        } else {
            // Si el cálculo es exitoso, devuelve un estado HTTP 200 OK con los resultados
            return ResponseEntity
                    .ok()
                    .body(new ApiResponse(new FalsaResponse(resultados), "Cálculo realizado con éxito."));
        }
    }
}
