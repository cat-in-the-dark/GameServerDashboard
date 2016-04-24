package com.catinthedark.models

import java.sql.Timestamp
import java.util.*

data class RoomModelWrapper(
        val id: Long = 0L,
        val createdAt: Timestamp = Timestamp(Date().time),
        val meta: RoomModel = RoomModel()
)

data class RoomModel(
        val name: String = "",
        val played: Boolean = false,
        val startedAt: Timestamp? = Timestamp(Date().time),
        val maxPlayers: Long = 0,
        val players: List<PlayerModel> = emptyList()
)

data class PlayerModel(
        val ip: String = "127.0.0.1",
        val uuid: String = "",
        val status: String = "",
        val connectedAt: Timestamp? = Timestamp(Date().time),
        val disconnectedAt: Timestamp? = Timestamp(Date().time),
        val geo: GeoModel? = GeoModel()
)

data class GeoModel(
        val lat: Double = 0.0,
        val lon: Double = 0.0,
        val country: String = "",
        val countryCode: String = "",
        val timezone: String = "",
        val regionName: String = "",
        val city: String = ""
)