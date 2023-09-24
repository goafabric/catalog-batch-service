package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.repository.ChargeItemRepository;
import org.goafabric.catalogservice.service.repository.entity.ChargeItemEo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "chargeitems", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeItemController {
    private final ChargeItemRepository logic;

    public ChargeItemController(ChargeItemRepository logic) {
        this.logic = logic;
    }

    @GetMapping("/{id}")
    public ChargeItemEo getById(@PathVariable String id) {
        return logic.findById(id).get();
    }

    @GetMapping("/findByDisplay")
    public List<ChargeItemEo> findByDisplay(@RequestParam("display") String display) {
        return logic.findByDisplayStartsWithIgnoreCase(display);
    }
}
