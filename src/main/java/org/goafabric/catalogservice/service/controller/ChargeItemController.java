package org.goafabric.catalogservice.service.controller;

import org.goafabric.catalogservice.service.controller.dto.ChargeItem;
import org.goafabric.catalogservice.service.logic.ChargeItemCatalogLogic;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "chargeitems", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChargeItemController {
    private final ChargeItemCatalogLogic chargeItemCatalogLogic;

    public ChargeItemController(ChargeItemCatalogLogic ChargeItemCatalogLogic) {
        this.chargeItemCatalogLogic = ChargeItemCatalogLogic;
    }

    @GetMapping("/{id}")
    public ChargeItem getById(@PathVariable String id) {
        return chargeItemCatalogLogic.getById(id);
    }

    @GetMapping("/findByDisplay")
    List<ChargeItem> findByDisplay(@RequestParam("display") String display) {
        return chargeItemCatalogLogic.search(display);
    }
}
