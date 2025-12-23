package org.goafabric.catalog.controller

import org.goafabric.catalog.job.condition.ConditionEo
import org.goafabric.catalog.job.condition.ConditionRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["conditions"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ConditionController(private val repository: ConditionRepository) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ConditionEo {
        return repository.findById(id).orElseThrow()!!
    }


    @GetMapping("/findByCode")
    fun findByCode(@RequestParam("code") code: String): ConditionEo {
        return repository.findByCode(code)
    }

    @GetMapping("/findByDisplay")
    fun findByDisplay(@RequestParam("display") display: String): MutableList<ConditionEo> {
        return repository.findByDisplayStartsWith(display)
    }
}
