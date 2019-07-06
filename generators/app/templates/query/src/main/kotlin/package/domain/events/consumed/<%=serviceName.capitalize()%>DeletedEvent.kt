package <%=packageName%>.domain.events.consumed

import <%=packageName%>.glia.annotations.Event

@Event(routingKey = "<%=serviceName.toLocaleLowerCase()%>.deleted")
data class <%=serviceName.capitalize()%>DeletedEvent(
    val projectId: String = "",
    val name: String = ""
)