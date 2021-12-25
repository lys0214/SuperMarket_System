package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.*;

public abstract class BaseDAO {
	// ��ͨ����ɾ�ķ���
		public int update(String sql,Object[] arr) {
			Connection conn = ConnDB.getConnection();
			PreparedStatement pst = null;
			try {
				pst = conn.prepareStatement(sql);
				//��ռλ����ֵ
				for (int i = 0; i < arr.length; i++) {
					pst.setObject(i+1, arr[i]);
				}
				int row = pst.executeUpdate();
				return row;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnDB.closeDB(null, pst, conn);
			}
			return 0;
		}
		// ��ͨ��ѯ
		public <T>T select(String sql,Object[] arr){
			Connection conn = ConnDB.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			T t = null;
			try {
				pst= conn.prepareStatement(sql);
				for (int i = 0; i < arr.length; i++) {
					pst.setObject(i+1, arr[i]);
				}
				rs = pst.executeQuery();
				// �Խ�������ռ�
				if(rs.next()) {
					// �ռ����������
					t = this.rowMapper(rs);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnDB.closeDB(null, pst, conn);
			}
			return t;
		}
		// ��ѯ�������
		public <T> List<T> selectAll(String sql,Object[] arr){
			List<T> list = new ArrayList<>();
			Connection conn = ConnDB.getConnection();
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			try {
				pst= conn.prepareStatement(sql);
				for (int i = 0; i < arr.length; i++) {
					pst.setObject(i+1, arr[i]);
				}
				rs = pst.executeQuery();
				T t = null;
				// �Խ�������ռ�
				while(rs.next()) {
					// �ռ����������
					t = this.rowMapper(rs);
					// ��ӵ�����
					list.add(t);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnDB.closeDB(null, pst, conn);
			}
			return list;
		}
		// ���巽��  ÿ����ѯ�Ľ������ȡ��Ӧ������
		public  abstract <T> T rowMapper(ResultSet rs) ;
}
