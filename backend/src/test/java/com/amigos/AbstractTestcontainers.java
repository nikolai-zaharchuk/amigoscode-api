package com.amigos;

import com.github.javafaker.Faker;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
public class AbstractTestcontainers {
    @Container
    protected static final PostgreSQLContainer POSTGRE_SQL_CONTAINER =
            new PostgreSQLContainer("postgres:latest")
                    .withDatabaseName("amigoscode-dao-unit-test")
                    .withUsername("admin")
                    .withPassword("admin");

    protected static final Faker FAKER = new Faker();

    @DynamicPropertySource
    private static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.datasource.url",
                POSTGRE_SQL_CONTAINER::getJdbcUrl
        );
        registry.add(
                "spring.datasource.username",
                POSTGRE_SQL_CONTAINER::getUsername
        );
        registry.add(
                "spring.datasource.password",
                POSTGRE_SQL_CONTAINER::getPassword
        );
    }

    protected static DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName(POSTGRE_SQL_CONTAINER.getDriverClassName())
                .url(POSTGRE_SQL_CONTAINER.getJdbcUrl())
                .username(POSTGRE_SQL_CONTAINER.getUsername())
                .password(POSTGRE_SQL_CONTAINER.getPassword());

        return dataSourceBuilder.build();
    }

    protected static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }


}
