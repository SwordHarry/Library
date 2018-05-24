package item;

public class User {
	private int id;	
	private int count;//记录已经借的书的数量
	private String userName;
	private String userPass;
	private int isAdmin;//是管理员为1，普通用户为0
	private String book1;
	private String date1;
	private String book2;
	private String date2;
	private String book3;
	private String date3;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return userName;
	}
	public void setName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return userPass;
	}
	public void setPass(String userPass) {
		this.userPass = userPass;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBook1() {
		return book1;
	}
	public void setBook1(String book1) {
		this.book1 = book1;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getBook2() {
		return book2;
	}
	public void setBook2(String book2) {
		this.book2 = book2;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getBook3() {
		return book3;
	}
	public void setBook3(String book3) {
		this.book3 = book3;
	}
	public String getDate3() {
		return date3;
	}
	public void setDate3(String date3) {
		this.date3 = date3;
	}
}
