package <%=projectName%>.test.medium

import <%=projectName%>.Application
import com.dhenry.glia.rabbit.service.RabbitService
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean
import org.springframework.data.cassandra.core.CassandraAdminTemplate
import org.springframework.data.cassandra.core.cql.CqlIdentifier
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import kotlin.test.BeforeTest

@ActiveProfiles("medium-test")
@RunWith(SpringRunner::class)
@SpringBootTest(
    classes = [Application::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
abstract class BaseMediumTest {

    @Autowired
    protected lateinit var rabbitService: RabbitService

    @Autowired
    private lateinit var cassandraAdminTemplate: CassandraAdminTemplate

    @Autowired
    private lateinit var cassandraSessionFactoryBean: CassandraSessionFactoryBean

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @BeforeTest
    fun setupBase() {
        cassandraAdminTemplate.keyspaceMetadata.tables.onEach {
            cassandraAdminTemplate.dropTable(CqlIdentifier.of(it.name))
        }

        cassandraSessionFactoryBean.afterPropertiesSet()
    }
}