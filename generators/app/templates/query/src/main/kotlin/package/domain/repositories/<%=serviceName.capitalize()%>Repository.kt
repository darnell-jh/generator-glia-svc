package <%=packageName%>.domain.repositories

import <%=packageName%>.domain.entities.<%=serviceName.capitalize()%>
import <%=packageName%>.glia.cassandra.domain.repositories.OutOfOrderRepository
import org.springframework.data.cassandra.repository.MapIdCassandraRepository

interface <%=serviceName.capitalize()%>Repository: MapIdCassandraRepository<<%=serviceName.capitalize()%>>, OutOfOrderRepository<<%=serviceName.capitalize()%>>
