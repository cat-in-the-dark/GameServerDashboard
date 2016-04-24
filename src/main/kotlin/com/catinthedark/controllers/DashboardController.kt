package com.catinthedark.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
open class DashboardController() {
    
    @RequestMapping(value = "/",method = arrayOf(RequestMethod.GET))
    fun index(): String {
        return "index"
    }
}