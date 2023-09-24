package org.goafabric.catalogservice.controller;

import org.goafabric.catalogservice.repository.entity.InsuranceEo;
import org.goafabric.catalogservice.repository.InsuranceRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "insurances", produces = MediaType.APPLICATION_JSON_VALUE)
public class InsuranceController {
    private final InsuranceRepository logic;

    public InsuranceController(InsuranceRepository logic) {
        this.logic = logic;
    }

    @GetMapping("/{id}")
    public InsuranceEo getById(@PathVariable String id) {
        return logic.findById(id).get();
    }

    @GetMapping("/findByDisplay")
    public List<InsuranceEo> findByDisplay(@RequestParam("display") String display) {
        return logic.findByDisplayStartsWithIgnoreCase(display);
    }
}
