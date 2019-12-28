package <%=packageName%>.controllers

import com.dhenry.config.X_PROJECT
import <%=packageName%>.domain.entities.<%=serviceName.capitalize()%>
import <%=packageName%>.domain.repositories.<%=serviceName.capitalize()%>Repository
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
import org.springframework.web.context.request.ServletWebRequest

const val BASE_MAPPING = "/v1/<%=serviceName.toLocaleLowerCase()%>"

@RestController
@RequestMapping(BASE_MAPPING, produces = [MediaType.APPLICATION_JSON_VALUE])
class <%=serviceName.capitalize()%>Controller(val <%=serviceName.toLocaleLowerCase()%>Repository: <%=serviceName.capitalize()%>Repository) {

    @GetMapping
    fun getAll<%=serviceName.capitalize()%>s(@PageableDefault pageable: Pageable,
                                             assembler: PagedResourcesAssembler<<%=serviceName.capitalize()%>>,
                                             request: ServletWebRequest): ResponseEntity<*> {
        val slice = <%=serviceName.toLocaleLowerCase()%>Repository.findAll(pageable)
        val total = <%=serviceName.toLocaleLowerCase()%>Repository.count()
        val page = PageImpl(slice.content, pageable, total)
        return ResponseEntity.ok(assembler.toModel(page))
    }

    @GetMapping("/{name}")
    fun get<%=serviceName.capitalize()%>(@PathVariable("name") name: String,
                                         request: ServletWebRequest): ResponseEntity<<%=serviceName.capitalize()%>> {
        val projectId = request.getHeader(X_PROJECT) ?: throw IllegalArgumentException("projectId is null!")
        val mapId = BasicMapId.id("projectId", projectId).with("name", name)
        val data = <%=serviceName.toLocaleLowerCase()%>Repository.findById(mapId)
        return ResponseEntity.of(data)
    }
}