package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnDB {
	// ��ȡ����ͨ��
	// Connection 
	public static Connection getConnection() {
		Connection conn = null;
		// 1.����������
		try {
			// Mysql����
			Class.forName("com.mysql.cj.jdbc.Driver");
			// ��ȡ����ͨ��
			String url ="jdbc:mysql://localhost:3306/supermarket?useSSL=false&serverTimezone=Asia/Shanghai";
			String user = "root";
			String password = "root";
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException  e) {
			// 1.�������� 2.�ⲿjarû������
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	// �ر���Դ
	public  static void closeDB(ResultSet rs,Statement pst,Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pst!=null) {
				pst.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println(ConnDB.getConnection());
	}
}
