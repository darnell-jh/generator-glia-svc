package <%=packageName%>.domain.listeners.external

import <%=packageName%>.domain.entities.<%=serviceName.capitalize()%>
import <%=packageName%>.domain.events.consumed.<%=serviceName.capitalize()%>DeletedEvent
import <%=packageName%>.domain.events.consumed.<%=serviceName.capitalize()%>CreatedEvent
import <%=packageName%>.domain.repositories.<%=serviceName.capitalize()%>Repository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.data.cassandra.core.mapping.BasicMapId
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import java.util.*

@Component
@RabbitListener(queues = ["\${spring.application.name}"])
class <%=serviceName.capitalize()%>EventListener(private val <%=serviceName.toLocaleLowerCase()%>Repository: <%=serviceName.capitalize()%>Repository) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(<%=serviceName.capitalize()%>EventListener::class.java)
    }

    @RabbitHandler
    fun on(<%=serviceName.toLocaleLowerCase()%>CreatedEvent: <%=serviceName.capitalize()%>CreatedEvent, @Header("timestamp") timestamp: Date) {
        logger.info("<%=serviceName.capitalize()%> Added Event: {}", <%=serviceName.toLocaleLowerCase()%>CreatedEvent)
        val <%=serviceName.toLocaleLowerCase()%> = with(<%=serviceName.toLocaleLowerCase()%>CreatedEvent) {
            <%=serviceName.capitalize()%>(projectId, name)
        }
        <%=serviceName.toLocaleLowerCase()%>Repository.saveLatest(<%=serviceName.toLocaleLowerCase()%>, timestamp.toInstant())
    }

    @RabbitHandler
    fun on(<%=serviceName.toLocaleLowerCase()%>DeletedEvent: <%=serviceName.capitalize()%>DeletedEvent) {
        logger.info("<%=serviceName.capitalize()%> Deleted Event: {}", <%=serviceName.toLocaleLowerCase()%>DeletedEvent)
        val mapId = BasicMapId.id("projectId", <%=serviceName.toLocaleLowerCase()%>DeletedEvent.projectId)
            .with("name", <%=serviceName.toLocaleLowerCase()%>DeletedEvent.name)
        <%=serviceName.toLocaleLowerCase()%>Repository.deleteById(mapId)
    }

}