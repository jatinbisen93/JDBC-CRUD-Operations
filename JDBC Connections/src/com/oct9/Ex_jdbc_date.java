package com.oct9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.Data;
import com.mysql.cj.xdevapi.Statement;

public class Ex_jdbc_date {

	private static String path = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3307/";
	private static String uname = "root";
	private static String pwd = "root";
	private static String dtname = "jdbcdb";
	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;

	private int id;
	private String nm;
	private String sal;
	private String cd;

	public Ex_jdbc_date(int id, String nm, String sal, String cd) {
		super();
		this.id = id;
		this.nm = nm;
		this.sal = sal;
		this.cd = cd;
	}

	@Override
	public String toString() {
		return "Ex_jdbc_date [id=" + id + ", nm=" + nm + "]";
	}

	public static void createDatabase() {

		Scanner s = new Scanner(System.in);
		System.out.println("Enter");
		String query = "CREATE SCHEMA " + dtname;
		try {
			Class.forName(path);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jdbcdb", uname, pwd);
			conn.prepareStatement(query).execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTable() {
		String tname = "example";
		String query = "CREATE table " + tname
				+ "(id INT PRIMARY KEY AUTO_INCREMENT,Name VARCHAR(40),Salary INT,CreatedAt DATETIME DEFAULT now())  ";
		try {
			Class.forName(path);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jdbcdb", uname, pwd);
			conn.prepareStatement(query).execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertData() {
		String tname = "example";
		String query = "insert into example (Name, Salary) values ('Jatin', 90000);";

		try {
			Class.forName(path);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jdbcdb", uname, pwd);
			conn.prepareStatement(query).execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteData() {
		String tname = "Registration";
		String query = "delete from Registration where id= 14 ;";

		try {
			Class.forName(path);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/newDatabase", uname, pwd);
			conn.prepareStatement(query).execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Ex_jdbc_date> getallData() {
		List<Ex_jdbc_date> l1 = new ArrayList<>();
		String query1 = "Select * from example";

		try {
			Class.forName(path);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jdbcdb", uname, pwd);
			conn.prepareStatement(query1).execute();
			rs = conn.prepareStatement(query1).executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String nm = rs.getString("Name");
				String sal = rs.getString("Salary");
				String cd = rs.getString("CreatedAt");

				Ex_jdbc_date a1 = new Ex_jdbc_date(id, nm, sal, cd);
				l1.add(a1);

				System.out.println(id + " " + nm + " " + sal + " " + cd);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l1;
	}

	public static void main(String[] args) {

		List<Ex_jdbc_date> getallData = getallData();
		System.out.println(" Showing All Data");
		for (Ex_jdbc_date jd : getallData) {
			System.out.printf("%-20s%-7d \n", jd.getName());

			System.out.println(jd);
		}
	}

	private Object getName() {
		return null;
	}
}
