package <%=packageName%>.controllers.responses

import <%=packageName%>.controllers.requests.<%=serviceName.capitalize()%>Request

data class <%=serviceName.capitalize()%>Response(
    val name: String
) {
    constructor(<%=serviceName.toLocaleLowerCase()%>Response: <%=serviceName.capitalize()%>Request): this(
        <%=serviceName.toLocaleLowerCase()%>Response.name
    )
}
