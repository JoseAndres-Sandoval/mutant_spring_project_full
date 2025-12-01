package com.marea.mutant.controller;

import com.marea.mutant.dto.DnaRequest;
import com.marea.mutant.dto.StatsResponse;
import com.marea.mutant.service.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MutantController {

    private final MutantService service;

    public MutantController(MutantService service) {
        this.service = service;
    }

    @PostMapping(path = "/mutant/", consumes = "application/json")
    public ResponseEntity<Void> mutant(@Valid @RequestBody DnaRequest req) {
        // La validación @Valid ocurre antes de entrar aquí.
        // Si el ADN es inválido, el GlobalExceptionHandler devolverá 400 Bad Request.

        boolean isMutant = service.verifyAndSave(req.getDna());

        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping(path = "/stats", produces = "application/json")
    public ResponseEntity<StatsResponse> stats() {
        long m = service.countMutants();
        long h = service.countHumans();
        StatsResponse resp = new StatsResponse(m, h);
        return ResponseEntity.ok(resp);
    }
}