package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.controller.vo.Condition;
import org.goafabric.catalogservice.service.logic.ConditioLogic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConditionController {
    private final ConditioLogic conditioLogic;

    public ConditionController(ConditioLogic ConditioLogic) {
        this.conditioLogic = ConditioLogic;
    }

    @GetMapping("/{id}")
    public Condition getById(@PathVariable String id) {
        return conditioLogic.getById(id);
    }

    @GetMapping("/findByDisplay")
    List<Condition> findByDisplay(@RequestParam("display") String display) {
        return conditioLogic.search(display);
    }
}
