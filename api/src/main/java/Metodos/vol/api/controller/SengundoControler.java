package Metodos.vol.api.controller;


import Metodos.vol.api.Domain.CalculoRaiz.CalculoR;
import Metodos.vol.api.Domain.CalculoRaiz.IteracionR;
import Metodos.vol.api.Domain.CalculoRaiz.RaizRequest;
import Metodos.vol.api.Domain.CalculoRaiz.RaizResponse;
import Metodos.vol.api.Domain.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SengundoControler {
    @PostMapping("/raiz")
    public ResponseEntity<ApiResponse> calcularRaiz(@RequestBody RaizRequest request) {
        CalculoR calculoR = new CalculoR(
                request.getXi(),
                request.getNcs(),
                request.getA()
        );
        List<IteracionR> resultados = calculoR.calcular();
        return ResponseEntity.ok().body(new ApiResponse(new RaizResponse(resultados), "Todo salio de acuerdo a lo planeado"));
    }
}
