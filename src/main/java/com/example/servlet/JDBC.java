package com.example.servlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JDBC {
	private static final String URL = "jdbc:postgresql://localhost:5432/MedTest";
	private static final String USER = "postgres";
	private static final String PASSWORD = "1109";

	public JDBC() {
		String URL = "jdbc:postgresql://localhost:5432/MedTest";
		String PASSWORD = "1109";
	}

	public static int execute(String sql) {
		int i = 0; // return 0 when failed
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st = conn.createStatement();
			i = st.executeUpdate(sql);
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("failed");
		}
		return i;
	}

	public static ArrayList<HashMap<String, String>> result(String sql) {
		ResultSet rs = null; // return null when failed

		ArrayList<HashMap<String, String>> a = new ArrayList<HashMap<String, String>>();

		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);

			ResultSetMetaData mt = rs.getMetaData();
			while (rs.next()) {
				HashMap<String, String> h = new HashMap<String, String>();
				for (int i = 1; i <= mt.getColumnCount(); i++) {
					String cName = mt.getColumnName(i);
					h.put(cName, rs.getString(cName));
				}
				a.add(h);
			}

			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public static boolean exist(String sql) {
		boolean f = false;
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st = conn.createStatement();
			f = st.execute(sql);
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("failed");
		}
		return f;
	}

	public static void main(String[] args) {
		JDBC A = new JDBC();
		System.out.println(JDBC.class.getPackage().getName());
		System.out.println(JDBC.class.getPackage().getName());
		System.out.println(JDBC.class.getPackage().getName());
	}
}
