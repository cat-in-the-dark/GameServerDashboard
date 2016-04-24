package com.catinthedark.services

import com.catinthedark.models.RoomModel
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.sql2o.Sql2o

@Repository
open class RoomRepository
@Autowired constructor(
        val sql2o: Sql2o,
        val mapper: ObjectMapper
) {
    val LOG = LoggerFactory.getLogger(RoomRepository::class.java)

    open fun findAll(): List<RoomModel> {
        val conn = sql2o.open()
        return try {
            conn.createQuery("SELECT id, meta, created_at FROM game ORDER BY created_at DESC")
                    .executeAndFetchTable()
                    .rows().map {
                        val json = it.getString("meta")
                        mapper.readValue(json, RoomModel::class.java)
                    }.filterNotNull()
        } catch(e: Exception) {
            LOG.error("Can't findAll rooms ${e.message}", e)
            emptyList()
        } finally {
            conn.close()
        }
    }
}
