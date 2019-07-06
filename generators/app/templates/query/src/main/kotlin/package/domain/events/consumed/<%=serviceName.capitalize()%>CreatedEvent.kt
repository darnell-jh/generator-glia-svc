package <%=packageName%>.domain.events.consumed

import <%=packageName%>.glia.annotations.Event

@Event(routingKey = "<%=serviceName.toLocaleLowerCase()%>.created")
data class <%=serviceName.capitalize()%>CreatedEvent(
    val id: String = "",
    val name: String = ""
)