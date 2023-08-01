package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.controller.vo.Insurance;
import org.goafabric.catalogservice.service.logic.InsuranceLogic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "insurances", produces = MediaType.APPLICATION_JSON_VALUE)
public class InsuranceController {
    private final InsuranceLogic insuranceLogic;

    public InsuranceController(InsuranceLogic insuranceLogic) {
        this.insuranceLogic = insuranceLogic;
    }

    @GetMapping("/{id}")
    public Insurance getById(@PathVariable String id) {
        return insuranceLogic.getById(id);
    }

    @GetMapping("/findByDisplay")
    List<Insurance> findByDisplay(@RequestParam("display") String display) {
        return insuranceLogic.search(display);
    }
}
