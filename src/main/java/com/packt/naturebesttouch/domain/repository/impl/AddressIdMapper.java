package com.packt.naturebesttouch.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AddressIdMapper implements RowMapper<Long>{

	@Override
	public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("ID");
		return id;
	}

}
