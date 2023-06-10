package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.controller.dto.Diagnosis;
import org.goafabric.catalogservice.service.logic.DiagnosisCatalogLogic;
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

    @GetMapping("/findByDisplay")
    List<Diagnosis> findByDisplay(@RequestParam("display") String display) {
        return diagnosisCatalogLogic.search(display);
    }
}
