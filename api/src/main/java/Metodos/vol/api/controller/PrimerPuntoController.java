package Metodos.vol.api.controller;

import Metodos.vol.api.Domain.Biseccion.BiseccionRequest;
import Metodos.vol.api.Domain.Biseccion.BiseccionResponse;
import Metodos.vol.api.Domain.Biseccion.Calculo;
import Metodos.vol.api.Domain.Biseccion.Iteracion;
import Metodos.vol.api.Domain.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/primer")
public class PrimerPuntoController {
    @PostMapping
    public ResponseEntity<ApiResponse> calcularBiseccion(@RequestBody BiseccionRequest request) {
        Calculo calculo = new Calculo(
                request.getNcs(),
                request.getEs(),
                request.getXi(),
                request.getXs(),
                0, // xr inicializado en 0
                0, // fxi inicializado en 0
                0, // fxr inicializado en 0
                100 // e inicializado en 100
        );
        List<Iteracion> resultados = calculo.calcularBiseccion();

        if (resultados == null) {
            // Si no se puede calcular, devuelve un estado HTTP 400 Bad Request
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse(null, "No se puede calcular la bisección con los datos proporcionados."));
        } else {
            // Si el cálculo es exitoso, devuelve un estado HTTP 200 OK con los resultados
            return ResponseEntity
                    .ok()
                    .body(new ApiResponse(new BiseccionResponse(resultados), "Cálculo realizado con éxito."));
        }
    }
}
