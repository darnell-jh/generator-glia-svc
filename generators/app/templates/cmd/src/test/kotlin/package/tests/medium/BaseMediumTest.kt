package <%=packageName%>.tests.medium

import <%=packageName%>.Application
import com.dhenry.glia.cassandra.config.CassandraPostConfig
import com.dhenry.glia.test.ProducerEventListener
import com.dhenry.glia.test.autoconfigure.amqp.rabbit.AutoConfigureRabbitProducerListener
import com.fasterxml.jackson.databind.ObjectMapper
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
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@ActiveProfiles("medium-test")
@RunWith(SpringRunner::class)
@SpringBootTest(
    classes = [IncomeCmdApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureRabbitProducerListener(producerEventPackages = ["<%=packageName%>.domain.events.produced"])
@AutoConfigureMockMvc
abstract class BaseMediumTest {

    @Autowired
    private lateinit var cassandraAdminTemplate: CassandraAdminTemplate

    @Autowired
    protected lateinit var eventListener: ProducerEventListener

    @Autowired
    private lateinit var cassandraSessionFactoryBean: CassandraSessionFactoryBean

    @Autowired
    private lateinit var cassandraConfig: CassandraPostConfig

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @BeforeTest
    fun setupBase() {
        cassandraAdminTemplate.keyspaceMetadata.tables.onEach {
            cassandraAdminTemplate.dropTable(CqlIdentifier.of(it.name))
        }

        eventListener.clearMessages()
        cassandraSessionFactoryBean.afterPropertiesSet()
        cassandraConfig.setupStaticActiveColumn()
    }

    @AfterTest
    fun teardownBase() {

    }

}