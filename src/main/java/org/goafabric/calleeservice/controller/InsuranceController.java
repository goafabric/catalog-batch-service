package org.goafabric.calleeservice.controller;

import org.goafabric.calleeservice.controller.dto.Insurance;
import org.goafabric.calleeservice.logic.InsuranceCatalogLogic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "insurances", produces = MediaType.APPLICATION_JSON_VALUE)
public class InsuranceController {
    private final InsuranceCatalogLogic insuranceCatalogLogic;

    public InsuranceController(InsuranceCatalogLogic insuranceCatalogLogic) {
        this.insuranceCatalogLogic = insuranceCatalogLogic;
    }

    @GetMapping("/{id}")
    public Insurance getById(@PathVariable String id) {
        return insuranceCatalogLogic.getById(id);
    }

    @GetMapping
    List<Insurance> findByDisplay(@RequestParam("display") String display) {
        return insuranceCatalogLogic.search(display);
    }
}
