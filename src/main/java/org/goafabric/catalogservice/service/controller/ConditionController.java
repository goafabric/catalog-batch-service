package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.repository.ConditionRepository;
import org.goafabric.catalogservice.service.repository.entity.ConditionEo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConditionController {
    private final ConditionRepository logic;

    public ConditionController(ConditionRepository logic) {
        this.logic = logic;
    }

    @GetMapping("/{id}")
    public ConditionEo getById(@PathVariable String id) {
        return logic.findById(id).get();
    }

    @GetMapping("/findByDisplay")
    public List<ConditionEo> findByDisplay(@RequestParam("display") String display) {
        return logic.findByDisplayStartsWithIgnoreCase(display);
    }
}
