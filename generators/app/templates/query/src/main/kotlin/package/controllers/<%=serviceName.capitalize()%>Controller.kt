package com.dhenry.controllers

import com.dhenry.domain.entities.<%=serviceName.capitalize()%>
import com.dhenry.domain.repositories.<%=serviceName.capitalize()%>Repository
import com.dhenry.helpers.projectId
import org.springframework.data.cassandra.core.mapping.BasicMapId
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val BASE_MAPPING = "/v1/<%=serviceName.toLocaleLowerCase()%>"

@RestController
@RequestMapping(BASE_MAPPING, produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class <%=serviceName.capitalize()%>Controller(val <%=serviceName.toLocaleLowerCase()%>Repository: <%=serviceName.capitalize()%>Repository) {

    @GetMapping
    fun getAll<%=serviceName.capitalize()%>s(@PageableDefault pageable: Pageable, assembler: PagedResourcesAssembler<<%=serviceName.capitalize()%>>)
            : ResponseEntity<*> {
        val slice = <%=serviceName.toLocaleLowerCase()%>Repository.findAll(pageable)
        val total = <%=serviceName.toLocaleLowerCase()%>Repository.count()
        val page = PageImpl(slice.content, pageable, total)
        return ResponseEntity.ok(assembler.toResource(page))
    }

    @GetMapping("/{name}")
    fun get<%=serviceName.capitalize()%>(@PathVariable("name") name: String): ResponseEntity<<%=serviceName.capitalize()%>> {
        val mapId = BasicMapId.id("projectId", projectId!!).with("name", name)
        return ResponseEntity.of(<%=serviceName.toLocaleLowerCase()%>Repository.findById(mapId))
    }
}