package com.itranswarp.common;

import com.itranswarp.warpdb.WarpDb;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarpDbFactory implements FactoryBean<WarpDb> {

	@Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public WarpDb getObject() throws Exception {
		WarpDb db = new WarpDb();
		db.setBasePackages(List.of("com.itranswarp.model"));
		db.setJdbcTemplate(jdbcTemplate);
		db.init();
		return db;
	}

	@Override
	public Class<?> getObjectType() {
		return WarpDb.class;
	}

}
