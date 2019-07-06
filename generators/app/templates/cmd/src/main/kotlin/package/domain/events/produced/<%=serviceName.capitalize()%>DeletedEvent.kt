package <%=packageName%>.domain.events.produced

import <%=packageName%>.glia.annotations.Event

@Event(routingKey = "<%=serviceName.toLocaleLowerCase()%>.deleted")
data class <%=serviceName.capitalize()%>DeletedEvent(
    val id: String = ""
)