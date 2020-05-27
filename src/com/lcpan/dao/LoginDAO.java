package com.lcpan.dao;


import com.lcpan.bean.UserBean;

public interface LoginDAO {
	public UserBean getUserId(String userId, String password);
}
