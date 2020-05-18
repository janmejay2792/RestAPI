package com.qa.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Getting Connect to JDBC and returning the JSON format
 *
 * @developer Janmejay.kumar
 */

public class JDBC_Connector {
	public static ServiceTest serviceTest = new ServiceTest();

	public Connection jdbcConnector(String url, String userName, String password) {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, userName, password);
			if (con != null) {
				System.out.println("Connected");
			} else {
				System.out.println("Failed to make connection!");
			}

		} catch (SQLException e) {
			System.out.println("MySQL Connection Failed!");
		}
		// Load mysql jdbc driver
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public String queryExecutorWithReturnJSON(Connection con, String query) {
		Statement stmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			// System.out.println(rs);
			result = convertResultSetToJson(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// closing DB Connection
				rs.close();
				con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public static String convertResultSetToJson(ResultSet resultSet) throws SQLException {
		if (resultSet == null)
			return null;

		JSONArray json = new JSONArray();
		ResultSetMetaData metadata = resultSet.getMetaData();
		int numColumns = metadata.getColumnCount();

		while (resultSet.next()) // iterate rows
		{
			JSONObject obj = new JSONObject(); // extends HashMap
			for (int i = 1; i <= numColumns; ++i) // iterate columns
			{
				String column_name = metadata.getColumnName(i);
				obj.put(column_name, resultSet.getObject(column_name));
			}
			json.put(obj);
		}
		return json.toString();
	}

	public static String convertToJSON(ResultSet resultSet) throws Exception {
		JSONArray jsonArray = new JSONArray();
		while (resultSet.next()) {
			int total_rows = resultSet.getMetaData().getColumnCount();
			for (int i = 0; i < total_rows; i++) {
				JSONObject obj = new JSONObject();
				obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
				jsonArray.put(obj);
			}
		}
		return jsonArray.toString();
	}

}
