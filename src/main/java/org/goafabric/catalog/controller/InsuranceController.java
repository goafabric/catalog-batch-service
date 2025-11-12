package org.goafabric.catalog.controller;

import org.goafabric.catalog.job.insurance.InsuranceEo;
import org.goafabric.catalog.job.insurance.InsuranceRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "insurances", produces = MediaType.APPLICATION_JSON_VALUE)
public class InsuranceController {
    private final InsuranceRepository repository;

    public InsuranceController(InsuranceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public InsuranceEo getById(@PathVariable String id) {
        return repository.findById(id).orElseThrow();
    }

    @GetMapping("/findByCode")
    public List<InsuranceEo> findByCode(@RequestParam("code") String code) {
        return repository.findByCode(code);
    }

    @GetMapping("/findByDisplay")
    public List<InsuranceEo> findByDisplay(@RequestParam("display") String display) {
        return repository.findByDisplayStartsWith(display);
    }
}
