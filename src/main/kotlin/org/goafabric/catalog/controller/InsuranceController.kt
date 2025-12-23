package org.goafabric.catalog.controller

import org.goafabric.catalog.job.insurance.InsuranceEo
import org.goafabric.catalog.job.insurance.InsuranceRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["insurances"], produces = [MediaType.APPLICATION_JSON_VALUE])
class InsuranceController(private val repository: InsuranceRepository) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): InsuranceEo {
        return repository.findById(id).orElseThrow()!!
    }

    @GetMapping("/findByCode")
    fun findByCode(@RequestParam("code") code: String): MutableList<InsuranceEo> {
        return repository.findByCode(code)
    }

    @GetMapping("/findByDisplay")
    fun findByDisplay(@RequestParam("display") display: String): MutableList<InsuranceEo> {
        return repository.findByDisplayStartsWith(display)
    }
}
