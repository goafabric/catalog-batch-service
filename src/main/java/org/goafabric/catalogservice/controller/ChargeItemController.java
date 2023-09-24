package org.goafabric.catalogservice.controller;

import org.goafabric.catalogservice.repository.ChargeItemRepository;
import org.goafabric.catalogservice.repository.entity.ChargeItemEo;
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

    @GetMapping("/findByDisplay")
    public List<ChargeItemEo> findByDisplay(@RequestParam("display") String display) {
        return repository.findByDisplayStartsWithIgnoreCase(display);
    }
}
