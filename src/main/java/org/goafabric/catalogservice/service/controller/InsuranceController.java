package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.controller.vo.Insurance;
import org.goafabric.catalogservice.service.logic.InsuranceLogic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "insurances", produces = MediaType.APPLICATION_JSON_VALUE)
public class InsuranceController {
    private final InsuranceLogic logic;

    public InsuranceController(InsuranceLogic logic) {
        this.logic = logic;
    }

    @GetMapping("/{id}")
    public Insurance getById(@PathVariable String id) {
        return logic.getById(id);
    }

    @GetMapping("/findByDisplay")
    public List<Insurance> findByDisplay(@RequestParam("display") String display) {
        return logic.search(display);
    }
}
