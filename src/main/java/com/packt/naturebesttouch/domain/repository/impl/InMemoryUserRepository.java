package com.packt.naturebesttouch.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.naturebesttouch.domain.User;
import com.packt.naturebesttouch.domain.repository.UserRepository;
import com.packt.naturebesttouch.exception.UserNotFoundException;

@Repository
public class InMemoryUserRepository implements UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<User> getAllUsers() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<User> result = jdbcTemplate.query("SELECT * FROM users", params, new UserMapper());
		return result;
	}

	private static final class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getString("ID"));
			user.setFirstName(rs.getString("FIRST_NAME"));
			user.setLastName(rs.getString("LAST_NAME"));
			user.setEmail(rs.getString("EMAIL"));
			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PASSWORD"));
			user.setIs_admin(rs.getBoolean("IS_ADMIN"));
			return user;
		}
	}

	@Override
	public void addUser(User user) {
		String SQL = "INSERT INTO USERS (FIRST_NAME," + "LAST_NAME," + "EMAIL," + "USERNAME," + "PASSWORD," + "IS_ADMIN) "
				+ "VALUES (:firstName, :lastName, :email, :username, :password, :isAdmin)";
		Map<String, Object> params = new HashMap<>();
		params.put("firstName", user.getFirstName());
		params.put("lastName", user.getLastName());
		params.put("email", user.getEmail());
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("isAdmin", user.isIs_admin());
		jdbcTemplate.update(SQL, params);
	}

	@Override
	public User getUserByUsername(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		String SQL = "SELECT * FROM users WHERE USERNAME =:username";
		params.put("username", username);
			
		try {
		 return jdbcTemplate.queryForObject(SQL, params, new UserMapper());
		 } catch (DataAccessException e) {
		 throw new UserNotFoundException(username);
		 }
		
	}

}
