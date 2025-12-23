package org.goafabric.catalog.controller

import org.goafabric.catalog.job.chargeitem.ChargeItemEo
import org.goafabric.catalog.job.chargeitem.ChargeItemRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["chargeitems"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ChargeItemController(private val repository: ChargeItemRepository) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ChargeItemEo {
        return repository.findById(id).orElseThrow()!!
    }

    @GetMapping("/findByCode")
    fun findByCode(@RequestParam("code") code: String): ChargeItemEo? {
        return repository.findByCode(code)
    }

    @GetMapping("/findByDisplay")
    fun findByDisplay(@RequestParam("display") display: String): MutableList<ChargeItemEo> {
        return repository.findByDisplayStartsWith(display)
    }
}
