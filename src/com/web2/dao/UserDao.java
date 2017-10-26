package com.web2.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.web2.DataSourceUtils.DataSourcePool;
import com.web2.domain.User;

public class UserDao {

	public static User getUser(String userName, String password) throws SQLException {
		
		QueryRunner qr = new QueryRunner(DataSourcePool.getDataSource());
		
		String sql = "select * from user where username = ? and password = ?";
		
		User user = qr.query(sql, new BeanHandler<User>(User.class), userName, password);
		
		return user;
	}

}
