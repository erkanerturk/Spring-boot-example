package com.erkanerturk.petclinic.dao.jdbc;

import com.erkanerturk.petclinic.dao.OwnerRepository;
import com.erkanerturk.petclinic.model.Owner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class OwnerRepositoryJdbcImpl implements OwnerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Owner> rowMapper = new RowMapper<Owner>() {

        @Override
        public Owner mapRow(ResultSet rs, int rowNum) throws SQLException {
            Owner owner = new Owner();
            owner.setId(rs.getLong("id"));
            owner.setFirstName(rs.getString("first_name"));
            owner.setLastName(rs.getString("last_name"));
            return owner;
        }
    };

    @Override
    public List<Owner> findAll() {
        String sql = "select id,first_name,last_name from t_owner";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Owner findById(Long id) {
        String sql = "select id,first_name,last_name from t_owner where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        String sql = "select id,first_name,last_name from t_owner where last_name like ?";
        return jdbcTemplate.query(sql, rowMapper, "%" + lastName + "%");
    }

    @Override
    public void add(Owner owner) {
    }

    @Override
    public Owner update(Owner owner) {
        return null;
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from t_owner where id = ?";
        jdbcTemplate.update(sql, id);
    }

}
