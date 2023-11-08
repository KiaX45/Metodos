package Metodos.vol.api.controller;

import Metodos.vol.api.Domain.TercerPunto.Biseccion.Biseccion3P;
import Metodos.vol.api.Domain.TercerPunto.Biseccion.Biseccion3PRequest;
import Metodos.vol.api.Domain.TercerPunto.Biseccion.Biseccion3PResponse;
import Metodos.vol.api.Domain.TercerPunto.Biseccion.IterBisec3P;
import Metodos.vol.api.Domain.TercerPunto.Falsa.Falsa3P;
import Metodos.vol.api.Domain.TercerPunto.Falsa.Falsa3PRequest;
import Metodos.vol.api.Domain.TercerPunto.Falsa.Falsa3PResponse;
import Metodos.vol.api.Domain.TercerPunto.Falsa.IterFalsaP;
import Metodos.vol.api.Domain.TercerPunto.Newton.IterNew3P;
import Metodos.vol.api.Domain.TercerPunto.Newton.Newton3P;
import Metodos.vol.api.Domain.TercerPunto.Newton.Newton3PRequest;
import Metodos.vol.api.Domain.TercerPunto.Newton.Newton3PResponse;
import Metodos.vol.api.Domain.TercerPunto.Secante.IterSecante3p;
import Metodos.vol.api.Domain.TercerPunto.Secante.Secante3P;
import Metodos.vol.api.Domain.TercerPunto.Secante.Secante3PRequest;
import Metodos.vol.api.Domain.TercerPunto.Secante.Secante3PResponse;
import Metodos.vol.api.Domain.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TercerPuntoController {

    @PostMapping("/Bisec3P")
    public ResponseEntity<ApiResponse> bisec3P (@RequestBody Biseccion3PRequest request){
        Biseccion3P calculo = new Biseccion3P(
                request.getNcs(),
                request.getEs(),
                request.getXi(),
                request.getXs(),
                0,
                0,
                0,
                100
        );
        List<IterBisec3P> iteraciones = calculo.calcularBiseccion();
        if(iteraciones == null){
            return ResponseEntity.badRequest().body(new ApiResponse(null, "No se puede calcular con los datos proporcionados"));
        }else{
            return  ResponseEntity.ok().body(new ApiResponse(new Biseccion3PResponse(iteraciones), "Todo salio bien"));
        }
    }

    @PostMapping("/Falsa3P")
    public ResponseEntity<ApiResponse> calcularFalsa3P(@RequestBody Falsa3PRequest request){
        Falsa3P calculo = new Falsa3P(
                request.getNcs(),
                0,
                request.getXi(),
                request.getXs(),
                0,
                0,
                0,
                100
        );
        List<IterFalsaP> resultados = calculo.calcularFalsa3P();

        if(resultados == null){
            return ResponseEntity.badRequest().body(new ApiResponse(null, "Los datos proporcionados puedes ser incorrecetos"));
        } else{
            return ResponseEntity.ok().body(new ApiResponse(new Falsa3PResponse(resultados), " Todos salio bien"));
        }
    }

    @PostMapping("/Newton3P")
    private ResponseEntity<ApiResponse> calcularNewton3P(@RequestBody Newton3PRequest request){
        Newton3P calculo = new Newton3P(
                request.getXi(),
                request.getNcs()
        );
        List<IterNew3P> resultados = calculo.calcular();
        return ResponseEntity.ok().body(new ApiResponse(new Newton3PResponse(resultados), "Todo salio bien"));
    }

    @PostMapping("/Secante3P")
    private ResponseEntity<ApiResponse> calcularSecante3P(@RequestBody Secante3PRequest request){
        Secante3P calculo = new Secante3P(
                request.getNcs(),
                request.getXi(),
                request.getXi0()
        );
        List<IterSecante3p> resultados = calculo.calcular();
        return ResponseEntity.ok().body(new ApiResponse(new Secante3PResponse(resultados), "Todos salio de acuerdo a lo planeado"));
    }
}
