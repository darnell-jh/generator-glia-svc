package <%=packageName%>.tests.medium

import <%=packageName%>.config.X_PROJECT
import <%=packageName%>.controllers.BASE_MAPPING
import <%=packageName%>.controllers.requests.<%=serviceName.capitalize()%>Request
import <%=packageName%>.domain.aggregates.<%=serviceName.capitalize()%>Aggregate
import <%=packageName%>.domain.events.produced.<%=serviceName.capitalize()%>CreatedEvent
import <%=packageName%>.services.<%=serviceName.capitalize()%>Service
import org.assertj.core.api.Assertions
import org.awaitility.kotlin.await
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.Test

const val PROJECT_1 = "PROJECT_1"

class <%=serviceName.capitalize()%>ControllerComponentTest: BaseMediumTest() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(<%=serviceName.capitalize()%>ControllerComponentTest::class.java)
    }

    @Autowired
    lateinit var <%=serviceName.toLocaleLowerCase()%>Service: <%=serviceName.capitalize()%>Service

    @Test
    fun addShouldSaveToRepo() {
        // Arrange
        val <%=serviceName.toLocaleLowerCase()%>Request = <%=serviceName.capitalize()%>Request(PROJECT_1)
        val expectedEvent = with (<%=serviceName.toLocaleLowerCase()%>Request) {
            <%=serviceName.capitalize()%>CreatedEvent(<%=serviceName.capitalize()%>Aggregate.generateId(PROJECT_1), name)
        }

        // Act
        enter(<%=serviceName.toLocaleLowerCase()%>Request)

        // Assert
        await.untilAsserted {
            val messages = eventListener.getAllPayloads()
            Assertions.assertThat(messages).isNotEmpty
            messages
                .also { Assertions.assertThat(it).hasSize(1) }
                .filter { it is <%=serviceName.capitalize()%>CreatedEvent }
                .also { Assertions.assertThat(it).hasSize(1) }
                .onEach { Assertions.assertThat(it).isEqualTo(expectedEvent) }
        }
    }

    private fun enter(<%=serviceName.toLocaleLowerCase()%>Request: <%=serviceName.capitalize()%>Request): ResultActions {
        return mockMvc
            .perform(
                MockMvcRequestBuilders.request(HttpMethod.PUT, BASE_MAPPING)
                    .header(X_PROJECT, PROJECT_1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(<%=serviceName.toLocaleLowerCase()%>Request))
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

}