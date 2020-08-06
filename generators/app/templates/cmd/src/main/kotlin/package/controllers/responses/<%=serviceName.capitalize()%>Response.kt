package <%=packageName%>.controllers.responses

import <%=packageName%>.controllers.requests.<%=serviceName.capitalize()%>Request

data class <%=serviceName.capitalize()%>Response(
    val name: String
) {
    constructor(<%=serviceName.toLocaleLowerCase()%>Response: <%=serviceName.capitalize()%>Response): this(
        <%=serviceName.toLocaleLowerCase()%>Response.name
    )
}
