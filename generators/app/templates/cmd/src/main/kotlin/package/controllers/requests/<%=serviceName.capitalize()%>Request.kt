package <%=packageName%>.controllers.requests

import javax.validation.constraints.NotEmpty

data class <%=serviceName.capitalize()%>Request(
    @NotEmpty
    val name: String = ""
)