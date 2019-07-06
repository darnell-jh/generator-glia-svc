package <%=packageName%>.domain.events.consumed

import <%=packageName%>.glia.annotations.Event

@Event(routingKey = "<%=serviceName.toLocaleLowerCase()%>.updated")
data class <%=serviceName.capitalize()%>UpdatedEvent(
    val id: String = "",
    val name: String = ""
)