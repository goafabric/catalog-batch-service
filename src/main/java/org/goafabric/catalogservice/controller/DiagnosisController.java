package org.goafabric.catalogservice.controller;

import org.goafabric.catalogservice.controller.dto.Diagnosis;
import org.goafabric.catalogservice.logic.DiagnosisCatalogLogic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiagnosisController {
    private final DiagnosisCatalogLogic diagnosisCatalogLogic;

    public DiagnosisController(DiagnosisCatalogLogic DiagnosisCatalogLogic) {
        this.diagnosisCatalogLogic = DiagnosisCatalogLogic;
    }

    @GetMapping("/{id}")
    public Diagnosis getById(@PathVariable String id) {
        return diagnosisCatalogLogic.getById(id);
    }

    @GetMapping
    List<Diagnosis> findByDisplay(@RequestParam("display") String display) {
        return diagnosisCatalogLogic.search(display);
    }
}
