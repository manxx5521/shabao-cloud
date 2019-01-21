package com.xiaoshabao.gateAuth.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class MyUserDetailDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserInfo getUser(String userName) {
		String sql = "select user_name,password from sys_user WHERE user_name=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {userName},new int[]{Types.VARCHAR}, new RowMapper<UserInfo>(){
			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfo user=new UserInfo();
				user.setUserName(rs.getString(1));
				user.setPassword(rs.getString(2));
				return user;
			}
		});
	}

}

class UserInfo{
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
