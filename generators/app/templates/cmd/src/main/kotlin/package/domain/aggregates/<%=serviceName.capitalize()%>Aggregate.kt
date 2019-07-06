package <%=packageName%>.domain.aggregates

import <%=packageName%>.domain.events.produced.<%=serviceName.capitalize()%>CreatedEvent
import <%=packageName%>.domain.events.produced.<%=serviceName.capitalize()%>DeletedEvent
import <%=packageName%>.domain.events.produced.<%=serviceName.capitalize()%>UpdatedEvent
import <%=packageName%>.glia.annotations.AggregateId
import <%=packageName%>.glia.annotations.EventSourceHandler
import <%=packageName%>.glia.cassandra.domain.aggregate.AbstractAggregateRoot
import <%=packageName%>.glia.cassandra.domain.models.AggregatePrimaryKey
import java.time.LocalDate
import java.util.*

class <%=serviceName.capitalize()%>Aggregate(
    name: String? = null
) : AbstractAggregateRoot<<%=serviceName.capitalize()%>Aggregate>(AggregatePrimaryKey()) {

    companion object {

        private val ID_SANITIZED_PATTERN = """[^A-Za-z]""".toRegex()

        fun generateId(name: String): String {
            return name.replace(ID_SANITIZED_PATTERN, "").toUpperCase(Locale.US)
        }
    }

    lateinit var name: String
        private set

    init {
        if (name != null) this.name = name
    }

    fun create<%=serviceName.capitalize()%>() {
        val event = <%=serviceName.capitalize()%>CreatedEvent(aggregatePrimaryKey.aggregateId, name)
        registerEvent(event)
    }

    fun update<%=serviceName.capitalize()%>() {
        val event = <%=serviceName.capitalize()%>UpdatedEvent(aggregatePrimaryKey.aggregateId, name)
        registerEvent(event)
    }

    fun delete<%=serviceName.capitalize()%>() {
        val <%=serviceName.toLocaleLowerCase()%>DeletedEvent = <%=serviceName.capitalize()%>DeletedEvent(aggregatePrimaryKey.aggregateId)
        registerEvent(<%=serviceName.toLocaleLowerCase()%>DeletedEvent)
    }

    @EventSourceHandler
    fun on(<%=serviceName.toLocaleLowerCase()%>CreatedEvent: <%=serviceName.capitalize()%>CreatedEvent, @AggregateId aggregateId: String) {
        aggregatePrimaryKey.aggregateId = aggregateId
        name = <%=serviceName.toLocaleLowerCase()%>CreatedEvent.name
    }

    @EventSourceHandler
    fun on(<%=serviceName.toLocaleLowerCase()%>UpdatedEvent: <%=serviceName.capitalize()%>UpdatedEvent) {
        name = <%=serviceName.toLocaleLowerCase()%>UpdatedEvent.name
    }

    fun copy(
        name: String? = null
    ): <%=serviceName.capitalize()%>Aggregate {
        return <%=serviceName.capitalize()%>Aggregate(
            name ?: this.name
        )
    }
}