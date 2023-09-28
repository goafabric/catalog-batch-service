package org.goafabric.catalogservice.controller;

import org.goafabric.catalogservice.repository.ConditionRepository;
import org.goafabric.catalogservice.repository.entity.ConditionEo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConditionController {
    private final ConditionRepository repository;

    public ConditionController(ConditionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ConditionEo getById(@PathVariable String id) {
        return repository.findById(id).get();
    }

    @GetMapping("/findByDisplay")
    public List<ConditionEo> findByDisplay(@RequestParam("display") String display) {
        return repository.findByDisplayStartsWithIgnoreCase(display);
    }
}