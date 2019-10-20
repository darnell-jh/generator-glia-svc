package <%=packageName%>.services

import <%=packageName%>.controllers.requests.<%=serviceName.capitalize()%>Request
import <%=packageName%>.controllers.responses.<%=serviceName.capitalize()%>Response
import <%=packageName%>.domain.aggregates.<%=serviceName.capitalize()%>Aggregate
import com.dhenry.glia.data.DataDomainEventsOperations
import com.dhenry.glia.utils.AggregateProvider
import org.springframework.stereotype.Service

@Service
class <%=serviceName.capitalize()%>Service(
    private val domainEventsService: DomainEventsService,
    private val aggregateProvider: AggregateProvider
) {

    fun update<%=serviceName.capitalize()%>(<%=serviceName.toLocaleLowerCase()%>Request: <%=serviceName.capitalize()%>Request): <%=serviceName.capitalize()%>Response {
        val aggregateId = <%=serviceName.capitalize()%>Aggregate.generateId(<%=serviceName.toLocaleLowerCase()%>Request.name)
        val <%=serviceName.toLocaleLowerCase()%>Aggregate: <%=serviceName.capitalize()%>Aggregate = aggregateProvider.createOrLoadAggregate(
            aggregateId,
            { <%=serviceName.capitalize()%>Aggregate(<%=serviceName.toLocaleLowerCase()%>Request.name) },
            <%=serviceName.capitalize()%>Aggregate::create<%=serviceName.capitalize()%>
        )
        // <%=serviceName.toLocaleLowerCase()%>Aggregate.updateProject(<%=serviceName.toLocaleLowerCase()%>Request.name)
        domainEventsService.save(<%=serviceName.toLocaleLowerCase()%>Aggregate)
        return <%=serviceName.capitalize()%>Response(<%=serviceName.toLocaleLowerCase()%>Request)
    }

    fun delete<%=serviceName.capitalize()%>(name: String) {
        val aggregateId = <%=serviceName.capitalize()%>Aggregate.generateId(name)
        val <%=serviceName.toLocaleLowerCase()%>Aggregate = aggregateProvider.loadAggregate<<%=serviceName.capitalize()%>Aggregate>(aggregateId, false)
            .orElseThrow { throw NullPointerException() }
        <%=serviceName.toLocaleLowerCase()%>Aggregate.delete<%=serviceName.capitalize()%>()
        domainEventsService.markDeleted(<%=serviceName.toLocaleLowerCase()%>Aggregate)
    }

}