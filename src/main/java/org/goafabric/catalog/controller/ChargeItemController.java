package org.goafabric.catalog.controller;

import org.goafabric.catalog.job.chargeitem.ChargeItemRepository;
import org.goafabric.catalog.job.chargeitem.ChargeItemEo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "chargeitems", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeItemController {
    private final ChargeItemRepository repository;

    public ChargeItemController(ChargeItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ChargeItemEo getById(@PathVariable String id) {
        return repository.findById(id).get();
    }

    @GetMapping("/findByCode")
    public ChargeItemEo findByCode(@RequestParam("code") String code) {
        return repository.findByCode(code);
    }

    @GetMapping("/findByDisplay")
    public List<ChargeItemEo> findByDisplay(@RequestParam("display") String display) {
        return repository.findByDisplayStartsWithIgnoreCase(display);
    }
}
