package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.controller.vo.Condition;
import org.goafabric.catalogservice.service.logic.ConditionLogic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "diagnosis", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConditionController {
    private final ConditionLogic logic;

    public ConditionController(ConditionLogic logic) {
        this.logic = logic;
    }

    @GetMapping("/{id}")
    public Condition getById(@PathVariable String id) {
        return logic.getById(id);
    }

    @GetMapping("/findByDisplay")
    public List<Condition> findByDisplay(@RequestParam("display") String display) {
        return logic.search(display);
    }
}
