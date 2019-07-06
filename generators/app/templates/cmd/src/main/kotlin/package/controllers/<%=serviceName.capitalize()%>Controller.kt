package com.dhenry.controllers

import com.dhenry.controllers.requests.<%=serviceName.capitalize()%>Request
import com.dhenry.controllers.responses.<%=serviceName.capitalize()%>Response
import <%=packageName%>.services.<%=serviceName.capitalize()%>Service
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Pattern

const val BASE_MAPPING = "/v1/<%=serviceName.toLocaleLowerCase()%>s"
const val DELETE_ENDPOINT = "/{name}"

@RestController
@RequestMapping(BASE_MAPPING, consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
@Validated
class <%=serviceName.capitalize()%>Controller(private val <%=serviceName.toLocaleLowerCase()%>Service: <%=serviceName.capitalize()%>Service) {

    @PutMapping
    fun update<%=serviceName.capitalize()%>(@RequestBody <%=serviceName.toLocaleLowerCase()%>Request: <%=serviceName.capitalize()%>Request): ResponseEntity<<%=serviceName.capitalize()%>Response> {
        return ResponseEntity.ok(<%=serviceName.toLocaleLowerCase()%>Service.update<%=serviceName.capitalize()%>(<%=serviceName.toLocaleLowerCase()%>Request))
    }

    @DeleteMapping(DELETE_ENDPOINT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remove<%=serviceName.capitalize()%>(@PathVariable("name") name: String) {
        <%=serviceName.toLocaleLowerCase()%>Service.delete<%=serviceName.capitalize()%>(name)
    }
}