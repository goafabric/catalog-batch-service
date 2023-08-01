package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.controller.vo.ChargeItem;
import org.goafabric.catalogservice.service.logic.ChargeItemLogic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "chargeitems", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeItemController {
    private final ChargeItemLogic logic;

    public ChargeItemController(ChargeItemLogic logic) {
        this.logic = logic;
    }

    @GetMapping("/{id}")
    public ChargeItem getById(@PathVariable String id) {
        return logic.getById(id);
    }

    @GetMapping("/findByDisplay")
    public List<ChargeItem> findByDisplay(@RequestParam("display") String display) {
        return logic.search(display);
    }
}
