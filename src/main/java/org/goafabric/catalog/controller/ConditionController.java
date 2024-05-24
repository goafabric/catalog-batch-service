package org.goafabric.catalog.controller;

import org.goafabric.catalog.job.condition.ConditionRepository;
import org.goafabric.catalog.job.condition.ConditionEo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "conditions", produces = MediaType.APPLICATION_JSON_VALUE)
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
