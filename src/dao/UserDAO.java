package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import item.User;

public class UserDAO extends BaseDAO {
	public boolean login(User ui, boolean checkFlag) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement psst = null;
		ResultSet rs = null;
		try {
			String sql;
			conn = openConnection();

			// ��Ϊֻ����û����ͼ���û���������
			if (checkFlag) {
				sql = "select * from usertable where username=? and userpass=? ";
				psst = conn.prepareStatement(sql);
				psst.setString(1, ui.getName());
				psst.setString(2, ui.getPass().toString());
				rs = psst.executeQuery();
			}

			else {
				sql = "select * from usertable where username=?";
				psst = conn.prepareStatement(sql);
				psst.setString(1, ui.getName());
				rs = psst.executeQuery();
			}

			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			closeAll(rs, psst, conn);
		}
		return flag;
	}

	// ��ѯ�û�
	public List<User> getAll(String term, User user) {
		List<User> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement psst = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = openConnection();
			switch (term) {
			case "id":// �����û�id�����û�
				sql = "select * from usertable where id=?";
				psst = conn.prepareStatement(sql);
				psst.setInt(1, user.getId());
				break;
			case "all":// ��ѯ�����û�
				sql = "select * from usertable";
				psst = conn.prepareStatement(sql);
				break;
			case "username":// �����û����ֲ����û�
				sql = "select * from usertable where username=?";
				psst = conn.prepareStatement(sql);
				psst.setString(1, user.getName());
				break;
			default:
				break;
			}
			rs = psst.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("username"));
				user.setPass(rs.getString("userpass"));
				user.setIsAdmin(rs.getInt("isadmin"));
				user.setCount(rs.getInt("count"));
				user.setBook1(rs.getString("book1"));
				user.setBook2(rs.getString("book2"));
				user.setBook3(rs.getString("book3"));
				user.setDate1(rs.getString("date1"));
				user.setDate2(rs.getString("date2"));
				user.setDate3(rs.getString("date3"));
				list.add(user);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			closeAll(rs, psst, conn);
		}
		return list;
	}

	// ע��
	public boolean add(String name, String pass) {
		// TODO Auto-generated method stub
		boolean flag = false;

		Connection conn = null;
		PreparedStatement psst = null;

		try {
			conn = openConnection();
			String sql = "insert into usertable(username,userpass) values(?,?)";
			psst = conn.prepareStatement(sql);
			psst.setString(1, name);
			psst.setString(2, pass);
			if (psst.executeUpdate() != 0) {
				flag = true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				psst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return flag;
	}

	// �û�
	// �洢�������û��������ʾ������
	public Object[][] queryDataUser(String term, User user) // term��ʾ����һ�������鼮��book�����洢�û�����Ĳ�ѯ����
	{
		// term��ʾ����һ�������û� ��user�����洢����Ĳ�ѯ����
		List<User> list = new UserDAO().getAll(term, user);
		Object[][] userData = new Object[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			userData[i][0] = list.get(i).getId();
			userData[i][1] = list.get(i).getName();
			userData[i][2] = list.get(i).getPass();
			userData[i][3] = list.get(i).getIsAdmin();
			userData[i][4] = list.get(i).getCount();
			userData[i][5] = list.get(i).getBook1();
			userData[i][6] = list.get(i).getDate1();
			userData[i][7] = list.get(i).getBook2();
			userData[i][8] = list.get(i).getDate2();
			userData[i][9] = list.get(i).getBook3();
			userData[i][10] = list.get(i).getDate3();
		}
		return userData;
	}

	// �޸��鼮
	public boolean change(User user) {
		boolean changeSuccess = false;// �����ж��Ƿ��޸ĳɹ�=================��Ҫ�޸�=======================
		Connection conn = null;
		String sql = null;
		PreparedStatement psst = null;
		ResultSet rs = null;
		try {
			conn = openConnection();
			sql = "update usertable set username =?,userpass = ?,isadmin =?,count = ?,book1 =?,date1 = ?,book2 =?,date2 = ?,book3 =?,date3 = ? where id=?";
			psst = conn.prepareStatement(sql);

			// �ô��������û��޸����ݿ��е�����
			psst.setString(1, user.getName());
			psst.setString(2, user.getPass());
			psst.setInt(3, user.getIsAdmin());
			psst.setInt(4, user.getCount());
			psst.setString(5, user.getBook1());
			psst.setString(6, user.getDate1());
			psst.setString(7, user.getBook2());
			psst.setString(8, user.getDate2());
			psst.setString(9, user.getBook3());
			psst.setString(10, user.getDate3());
			psst.setInt(11, user.getId());

			int i = psst.executeUpdate();

			if (i == 1) {
				changeSuccess = true;
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			closeAll(rs, psst, conn);
		}
		return changeSuccess;
	}

	// ���ر����������ˢ��,term��ʾ����һ�������û���user�����洢����Ĳ�ѯ����
	public DefaultTableModel selectUser(String term, User user) {
		Object[][] userData = queryDataUser(term, user);
		String[] columnNames = { "ID", "�û���","����","���","������Ŀ","�ѽ��鼮1","��������1","�ѽ��鼮2","��������2" ,"�ѽ��鼮3","��������3"};
		DefaultTableModel userDtw = new DefaultTableModel(userData, columnNames);
		return userDtw;
	}
}
