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
//          �CŪ�@�C�A�s�ت���
				rowObj = new JSONObject();
				int columnCount = rsmd.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
//                ������W��
					String columnName = rsmd.getColumnName(i);
//                ���ƾ�
					String value = resultSet.getString(columnName);
//                �K�[��rowOb��
					rowObj.put(columnName, value);
				}
//                �K�[��ƾڶ�Json
				jsonArray.add(rowObj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}
