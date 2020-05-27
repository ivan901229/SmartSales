package com.lcpan.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JSONTools {

	public static JSON resultSetToJSON(ResultSet resultSet) {
		JSONArray jsonArray = new JSONArray();
		JSONObject rowObj = null;
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			while (resultSet.next()) {
//          每讀一列，新建物件
				rowObj = new JSONObject();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
//                獲取欄位名稱
					String columnName = rsmd.getColumnName(i);
//                取數據
					String value = resultSet.getString(columnName);
//                添加到rowOb中
					rowObj.put(columnName, value);
				}
//                添加到數據集Json
				jsonArray.add(rowObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}
