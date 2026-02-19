package com.amigos.amigosprofsdevcourse.customer;

import com.amigos.AbstractTestcontainers;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerJDBCDataAccessServiceTest extends AbstractTestcontainers {

    private static final String EMAIL = FAKER.internet().emailAddress() + "-" + UUID.randomUUID();

    private static final Customer CUSTOMER = Customer.builder()
            .name(FAKER.name().fullName())
            .email(EMAIL)
            .age(20)
            .country("UA")
            .build();

    static {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        String query = """
                CREATE TABLE customer (
                    id BIGSERIAL PRIMARY KEY,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    age INT NOT NULL,
                    country TEXT NOT NULL
                );
                
                ALTER TABLE customer
                ADD COLUMN profile_image_id VARCHAR(36);
                
                ALTER TABLE customer
                ADD CONSTRAINT profile_image_id_unique UNIQUE (profile_image_id);
                
                """;

        jdbcTemplate.update(query);
    }


    private CustomerJDBCDataAccessService customerJDBCDataAccessService;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    @BeforeEach
    void setUp() {
        customerJDBCDataAccessService = new CustomerJDBCDataAccessService(
               new JdbcTemplate(getDataSource()),
               customerRowMapper
        );
    }

    @Test
    @Order(110)
    void insertCustomer() {
//        List<Customer> actual = customerJDBCDataAccessService.getAllCustomers();
//        assertThat(actual).isEmpty();
//
//        customerJDBCDataAccessService.insertCustomer(CUSTOMER);
//
//        actual = customerJDBCDataAccessService.getAllCustomers();
//        assertThat(actual).isNotEmpty();
    }

    @Test
    @Order(9)
    void getAllCustomers() {
        customerJDBCDataAccessService.insertCustomer(CUSTOMER);
        List<Customer> actual = customerJDBCDataAccessService.getAllCustomers();

        System.out.println(actual);

        assertThat(actual).isNotEmpty();
        assertThat(actual.size()).isEqualTo(2);
    }

    @Test
    @Order(8)
    void getCustomerById() {
        customerJDBCDataAccessService.insertCustomer(CUSTOMER);

        List<Customer> actual = customerJDBCDataAccessService.getAllCustomers();

        System.out.println(actual);

        int id = actual
                        .stream()
                        .filter(c -> c.getEmail().equals(EMAIL))
                        .map(Customer::getId)
                        .findFirst()
                        .orElseThrow();


        Optional<Customer> actualCustomer = customerJDBCDataAccessService.getCustomerById(id);
        assertThat(actualCustomer).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getName()).isEqualTo(CUSTOMER.getName());
            assertThat(c.getEmail()).isEqualTo(EMAIL);
            assertThat(c.getAge()).isEqualTo(CUSTOMER.getAge());
            assertThat(c.getCountry()).isEqualTo(CUSTOMER.getCountry());
        });
    }

    @Test
    @Order(7)
    void willReturnEmptyWhenSelectCustomerById() {
        int id = -1;

        Optional<Customer> customer = customerJDBCDataAccessService.getCustomerById(id);

        assertThat(customer).isEmpty();
    }

    @Test
    @Order(6)
    void getCustomerByEmail() {
        Optional<Customer> actual = customerJDBCDataAccessService.getCustomerByEmail(EMAIL);

//        assertThat(actual).isPresent().hasValueSatisfying(c -> {
//            assertThat(c.getName()).isEqualTo(CUSTOMER.getName());
//            assertThat(c.getEmail()).isEqualTo(EMAIL);
//            assertThat(c.getAge()).isEqualTo(CUSTOMER.getAge());
//            assertThat(c.getCountry()).isEqualTo(CUSTOMER.getCountry());
//        });
    }

    @Test
    @Order(5)
    void willReturnEmptyWhenSelectCustomerByEmail() {
        String email = "email";

        Optional<Customer> customer = customerJDBCDataAccessService.getCustomerByEmail(email);

        assertThat(customer).isEmpty();
    }

    @Test
    void removeCustomer() {
    }

    @Test
    void updateCustomer() {
    }
}