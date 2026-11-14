package com.example.erecepta.controllers;

import com.example.erecepta.dto.Requests.LekarzDaneRequest;
import com.example.erecepta.dto.Requests.ReceptaRequest;
import com.example.erecepta.dto.Responses.*;
import com.example.erecepta.services.LekarzService;
import com.example.erecepta.services.PacjentService;
import com.example.erecepta.services.ReceptaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lekarz")
public class LekarzController {

    private final LekarzService lekarzService;
    private final ReceptaService receptaService;
    private final PacjentService pacjentService;

    public LekarzController(LekarzService lekarzService, ReceptaService receptaService, PacjentService pacjentService) {
        this.lekarzService = lekarzService;
        this.receptaService = receptaService;
        this.pacjentService = pacjentService;
    }

    @GetMapping("/{dane}/daneLekarza")
    public ResponseEntity<LekarzResponse> getDaneLekarza(@PathVariable String dane) {
        String[] czesci = dane.split("_", 2);
        if (czesci.length < 2) {
            return ResponseEntity.badRequest().build();
        }
        return lekarzService.getDaneLekarza(czesci[0], czesci[1])
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{pesel}/dane")
    public ResponseEntity<LekarzDaneResponse> getDaneLekarzaByPesel(@PathVariable String pesel) {
        return lekarzService.getDaneLekarzaByPesel(pesel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{idLekarza}/recepty")
    public ResponseEntity<List<ReceptaLekarzResponse>> getReceptyLekarza(@PathVariable Integer idLekarza) {
        return receptaService.getReceptyLekarza(idLekarza)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{idLekarza}/historia")
    public ResponseEntity<List<HistoriaLekarzaResponse>> getHistoriaLekarza(@PathVariable Integer idLekarza) {
        return lekarzService.getHistoriaLekarza(idLekarza)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateDaneLekarza(@RequestBody LekarzDaneRequest request) {
        if (lekarzService.updateDaneLekarza(request)) {
            return ResponseEntity.ok(Map.of("status", "success"));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{pesel}/id")
    public ResponseEntity<Map<String, String>> getIdLekarzaByPesel(@PathVariable String pesel) {
        return lekarzService.getIdLekarzaByPesel(pesel)
                .map(id -> ResponseEntity.ok(Map.of("idLekarza", id)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{pesel}/danePacjenta")
    public ResponseEntity<PacjentDaneResponse> getDanePacjenta(@PathVariable String pesel) {
        return pacjentService.getDanePacjentaByPesel(pesel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{pesel}/aktualizuj")
    public ResponseEntity<Map<String, String>> aktualizacjaDanych(@PathVariable String pesel, @RequestBody LekarzDaneRequest request) {
        if (lekarzService.aktualizujDaneLekarza(pesel, request)) {
            return ResponseEntity.ok(Map.of("status", "success"));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{pesel}/dodajRecepte")
    public ResponseEntity<Map<String, String>> dodanieRecepty(@PathVariable String pesel, @RequestBody ReceptaRequest request) {
        if (receptaService.dodajRecepte(pesel, request)) {
            return ResponseEntity.ok(Map.of("status", "success"));
        }
        return ResponseEntity.notFound().build();
    }
}
