package <%=packageName%>.domain.entities

import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table

const val <%=serviceName.toLocaleUpperCase()%>_TABLE_NAME = "<%=serviceName.toLocaleLowerCase()%>"
@Table(<%=serviceName.toLocaleUpperCase()%>_TABLE_NAME)
data class <%=serviceName.capitalize()%>(
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED) val projectId: String,
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED) val name: String
)