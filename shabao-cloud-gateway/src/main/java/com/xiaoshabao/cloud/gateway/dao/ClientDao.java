package com.xiaoshabao.cloud.gateway.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<ClientEntity> getClientList() {
    String sql = "select client_id,filter_path from sys_cloud_client_details";
    return jdbcTemplate.query(sql, new RowMapper<ClientEntity>() {
      @Override
      public ClientEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClientEntity entity = new ClientEntity();
        entity.setClientId(rs.getString(1));
        entity.setFilterPath(rs.getString(2));
        return entity;
      }
    });
  }
}
