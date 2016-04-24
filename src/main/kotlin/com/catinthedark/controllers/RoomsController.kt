package com.catinthedark.controllers

import com.catinthedark.models.RoomModel
import com.catinthedark.services.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
open class RoomsController
@Autowired constructor(
        val roomRepository: RoomRepository
) {
    @RequestMapping(value = "/api/games.json", method = arrayOf(RequestMethod.GET))
    fun getAll(): List<RoomModel> {
        return roomRepository.findAll()
    }
}