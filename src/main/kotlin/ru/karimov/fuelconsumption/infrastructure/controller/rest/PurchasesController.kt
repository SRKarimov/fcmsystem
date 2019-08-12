package ru.karimov.fuelconsumption.infrastructure.controller.rest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class PurchasesController {
    @RequestMapping("/purchases")
    @ResponseBody
    fun get(): String {
        return "Hello World"
    }
}
