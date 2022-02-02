package com.example.todo.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SwaggerConfigurationTest {

    private SwaggerConfig swaggerConfiguration = new SwaggerConfig();

    @Test
    void testApi() {
        try {
            Docket docket = swaggerConfiguration.swaggerConfigurations();
            assertNotNull(docket);
            assertThat(docket.getDocumentationType().getName()).isEqualTo("swagger");
        } catch (Exception e) {
            fail("Unexpected error " + e);
        }
    }
}
