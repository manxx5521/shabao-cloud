package com.xiaoshabao.gateAuth.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class MyUserDetailDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserInfo getUser(String userName) {
		String sql = "select user_id,user_name,password from upms_user WHERE user_name=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {userName},new int[]{Types.VARCHAR}, new RowMapper<UserInfo>(){
			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfo user=new UserInfo();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				return user;
			}
		});
	}
	
	public List<Role> getRole(Integer userId) {
		String sql = "select b.role_id,b.role_value from upms_user_role a,upms_role b WHERE a.role_id=b.role_id and a.user_id=?";
		return jdbcTemplate.query(sql,new Object[] {userId},new int[]{Types.INTEGER}, new RowMapper<Role>(){
			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
				Role role=new Role();
				role.setRoleId(rs.getInt(1));
				role.setRoleValue(rs.getString(2));
				return role;
			}
		});
	}
	
	public List<Permission> getPermission(Integer roleId) {
		String sql = "select b.permission_id,b.permission_value from upms_role_permission a,upms_permission b "
					+"WHERE a.permission_id=b.permission_id and a.role_id=?";
		return jdbcTemplate.query(sql,new Object[] {roleId},new int[]{Types.INTEGER}, new RowMapper<Permission>(){
			@Override
			public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
				Permission permission=new Permission();
				permission.setPermissionId(rs.getInt(1));
				permission.setPermissionValue(rs.getString(2));
				return permission;
			}
		});
	}

}

class UserInfo{
	private Integer userId;
	private String userName;
	private String password;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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

class Role{
	private Integer roleId;
	private String roleValue;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleValue() {
		return roleValue;
	}
	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}
}

class Permission{
	private Integer permissionId;
	private String permissionValue;
	public Integer getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionValue() {
		return permissionValue;
	}
	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}
	
}
