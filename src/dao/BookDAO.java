package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import item.Book;

public class BookDAO extends BaseDAO {

	// ��ѯͼ��
	public List<Book> getAll(String term, Book book) // term��ʾ����һ�������鼮��book�����洢�û�����Ĳ�ѯ����
	{
		List<Book> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement psst = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = openConnection();
			switch (term) {
			case "id":// �����鼮id�����鼮
				sql = "select * from booktable where id=?";
				psst = conn.prepareStatement(sql);
				psst.setInt(1, book.getId());
				break;

			case "all":// ��ѯ�����鼮
				sql = "select * from booktable";
				psst = conn.prepareStatement(sql);
				break;
			case "type":// �����鼮�������Ͳ����鼮
				sql = "select * from booktable where type=?";
				psst = conn.prepareStatement(sql);
				psst.setString(1, book.getType());
				break;
			case "name":// �����鼮���ֲ����鼮
				sql = "select * from booktable where name=?";
				psst = conn.prepareStatement(sql);
				psst.setString(1, book.getName());
				break;
			case "press":// �����鼮����������鼮
				sql = "select * from booktable where press=?";
				psst = conn.prepareStatement(sql);
				psst.setString(1, book.getPress());
				break;
			case "author":// �����鼮���߲����鼮
				sql = "select * from booktable where author=?";
				psst = conn.prepareStatement(sql);
				psst.setString(1, book.getAuthor());
				break;
			case "price":// �����鼮�۸�����鼮
				sql = "select * from booktable where price=?";
				psst = conn.prepareStatement(sql);
				psst.setFloat(1, book.getPrice());
				break;
			default:
				break;
			}
			rs = psst.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getInt("id"));
				book.setType(rs.getString("type"));
				book.setName(rs.getString("name"));
				book.setPress(rs.getString("press"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getFloat("price"));
				book.setCount(rs.getInt("count"));
				list.add(book);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			closeAll(rs, psst, conn);
		}
		return list;
	}

	// ����鼮
	public boolean add(Book book) {
		boolean flag = false;

		Connection conn = null;
		PreparedStatement psst = null;

		try {
			conn = openConnection();
			String sql = "insert into booktable(type,name,press,author,price,count) values(?,?,?,?,?,?)";
			psst = conn.prepareStatement(sql);
			psst.setString(1, book.getType());
			psst.setString(2, book.getName());
			psst.setString(3, book.getPress());
			psst.setString(4, book.getAuthor());
			psst.setFloat(5, book.getPrice());
			psst.setInt(6, book.getCount());
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

	// ɾ���鼮
	public boolean delete(Book book) {
		// TODO Auto-generated method stub
		boolean isDelete = false;
		Connection conn = null;
		String sql = null;
		PreparedStatement psst = null;
		ResultSet rs = null;
		try {
			conn = openConnection();
			sql = "delete from booktable where id = ?";
			psst = conn.prepareStatement(sql);
			psst.setInt(1, book.getId());
			isDelete = psst.execute();
			isDelete = true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			closeAll(rs, psst, conn);
		}
		return isDelete;
	}

	// �޸��鼮
	public boolean change(Book book) {
		boolean changeSuccess = false;
		Connection conn = null;
		String sql = null;
		PreparedStatement psst = null;
		ResultSet rs = null;
		try {
			conn = openConnection();
			sql = "update booktable set type =?,name = ?,press =?,author = ?,price =?,count = ? where id=?";
			psst = conn.prepareStatement(sql);

			psst.setString(1, book.getType());
			psst.setString(2, book.getName());
			psst.setString(3, book.getPress());
			psst.setString(4, book.getAuthor());
			psst.setFloat(5, book.getPrice());
			psst.setInt(6, book.getCount());
			psst.setInt(7, book.getId());

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

	// �洢�����ڱ������ʾ������
	public Object[][] queryDataBook(String term, Book book) // term��ʾ����һ�������鼮��book�����洢�û�����Ĳ�ѯ����
	{
		// term��ʾ����һ�������鼮��book�����洢�û�����Ĳ�ѯ����
		List<Book> list = new BookDAO().getAll(term, book);// list�Ƕ������飬ֻ�������
		Object[][] bookData = new Object[list.size()][7];// dataBook��Ÿ������ò鵽���鼮����
		for (int i = 0; i < list.size(); i++) {
			bookData[i][0] = list.get(i).getId();
			bookData[i][1] = list.get(i).getType();
			bookData[i][2] = list.get(i).getName();
			bookData[i][3] = list.get(i).getPress();
			bookData[i][4] = list.get(i).getAuthor();
			bookData[i][5] = list.get(i).getPrice();
			bookData[i][6] = list.get(i).getCount();
		}
		return bookData;
	}

	// ���ر����������ˢ��,term��ʾ����һ�������鼮��book�����洢�û�����Ĳ�ѯ����
	public DefaultTableModel selectBook(String term, Book book) {
		Object[][] bookData = queryDataBook(term, book);
		String[] columnNames = { "ID", "����", "����", "������", "����", "�۸�Ԫ��", "����������" };
		DefaultTableModel dtwBook = new DefaultTableModel(bookData, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;// ����true��ʾ�ܱ༭��false��ʾ���ܱ༭
			}
		};
		return dtwBook;
	}
}