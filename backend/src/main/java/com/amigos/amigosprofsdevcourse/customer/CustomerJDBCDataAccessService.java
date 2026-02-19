package com.amigos.amigosprofsdevcourse.customer;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("jdbc")
@AllArgsConstructor
public class CustomerJDBCDataAccessService implements CustomerDao {

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    @Override
    public List<Customer> getAllCustomers() {
        var sql = """
                SELECT id, name, email, age, country FROM customer
                """;

        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer customerId)
    {
        var sql = """
                SELECT id, name, email, age, country FROM customer
                WHERE id = ?
                """;

        return jdbcTemplate
                .query(sql, customerRowMapper, customerId)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        var sql = """
                SELECT id, name, email, age, country FROM customer
                WHERE email = ?
                """;

        return jdbcTemplate
                .query(sql, customerRowMapper, email)
                .stream()
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        var sql = """
                INSERT INTO customer(name, email, age, country)
                VALUES(?, ? ,?, ?)
                """;

        int update = jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAge(),
                customer.getCountry()
        );
    }

    @Override
    public void removeCustomer(Integer id) {
        var sql = """
                DELETE FROM customer
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, id);

    }

    @Override
    public void updateCustomer(Customer customer) {
        var sql = """
                UPDATE customer
                SET name = ?,
                    email = ?,
                    age = ?,
                    country = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getEmail(),
                customer.getAge(),
                customer.getCountry(),
                customer.getId()
        );
    }
}
