
package com.marea.mutant.controller;

import com.marea.mutant.dto.DnaRequest;
import com.marea.mutant.dto.StatsResponse;
import com.marea.mutant.service.MutantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MutantController {

    private final MutantService service;

    public MutantController(MutantService service) { this.service = service; }

    @PostMapping(path = "/mutant/", consumes = "application/json")
    public ResponseEntity<?> mutant(@RequestBody DnaRequest req) {
        try {
            boolean isMutant = service.verifyAndSave(req.getDna());
            if (isMutant) return ResponseEntity.ok().build();
            else return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
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
