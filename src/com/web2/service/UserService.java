package com.web2.service;

import java.sql.SQLException;

import com.web2.dao.UserDao;
import com.web2.domain.User;

public class UserService {

	public static User getUserByUserNameAndPassword(String userName, String password) throws SQLException {
		
		return UserDao.getUser(userName, password);
	}

}
