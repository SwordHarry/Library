package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.BookDAO;
import dao.UserDAO;
import item.Book;
import item.User;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;

public class UserUI extends JFrame {

	private static User user;
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneUser;
	private JPanel contentPane;
	private static UserUI userUI;
	private int xOld, yOld;
	private int searchFlag;
	
	private static int tableindex;
	private static int tablecolumn;
	public static JTable bookTable;
	private static JTable userTable;
	private DefaultTableModel userDtw;
	private DefaultTableModel bookDtw;
	private JComboBox<String> comboBox;
	private JLabel lblSearchType;
	private JLabel lblSearch;
	private static JPopupMenu popupMenu;
	private JTextField textSearch;
	private JTextField textRead;
	private JScrollPane scrollPane_2;

	private ImageIcon bgp;
	private JLabel background;

	private String searchText;

	private ImageIcon image;
	private JLabel imageLabel;

	private static BookDAO bookDao = new BookDAO();
	private static UserDAO userDao = new UserDAO();

	private JButton buttonRead;
	private JButton btnExit;
	private JButton buttonMini;
	private JButton buttonOff;

	private ExplainUI explainUI;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userUI = new UserUI(user);
					userUI.setVisible(true);
					reSize(userUI);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserUI(User user) {

		// 得到当前登录的用户信息
		// List<User> list = new UserDAO().getAll("username", user);
		// this.user = list.get(0);
		// user = list.get(0);

		this.user = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 956, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnExit = new JButton("");
		btnExit.setBounds(905, 4, 41, 27);
		btnExit.addActionListener(new btnExit());
		contentPane.setLayout(null);
		btnExit.setIcon(new ImageIcon("picture//button//exit//退出.png"));
		btnExit.setRolloverIcon(new ImageIcon("picture//button//exit//退出2.png"));
		// 隐藏按钮的方法
		btnExit.setBorder(null);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		// 去掉按钮文字周围的焦点框
		btnExit.setFocusPainted(false);
		contentPane.add(btnExit);

		buttonMini = new JButton("");
		buttonMini.setBounds(850, 4, 41, 27);
		buttonMini.addActionListener(new buttonMini());
		buttonMini.setIcon(new ImageIcon("picture//button//mini//最小化.png"));
		buttonMini.setRolloverIcon(
				new ImageIcon("picture//button//mini//最小化2.png"));
		// 隐藏按钮的方法
		buttonMini.setBorder(null);
		buttonMini.setOpaque(false);
		buttonMini.setContentAreaFilled(false);
		// 去掉按钮文字周围的焦点框
		buttonMini.setFocusPainted(false);
		contentPane.add(buttonMini);

		setTitle("haha ");
		// 去除界面边框
		setUndecorated(true);
		// 窗口不可调
		setResizable(false);
		// 可拖动窗口
		dragUI();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 53, 878, 269);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);

		bookTable = new JTable();

		// 书籍表格双击事件
		bookTable.addMouseListener(new BookTableClicked());

		bookTable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		bookTable.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 18));
		scrollPane.setViewportView(bookTable);
		bookTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		bookTable.setBounds(53, 63, 686, 272);
		// 设置行高
		bookTable.setRowHeight(20);

		bookDtw = new BookDAO().selectBook("all", null);
		bookTable.setModel(bookDtw);
		
		 popupMenu = new JPopupMenu();
		addPopup(bookTable, popupMenu);
		
		// 通过右键菜单进入说明界面
		JMenuItem menuItem = new JMenuItem("\u67E5\u770B\u8BE6\u7EC6\u8D44\u6599");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User ui = new User();
				explainUI = new ExplainUI(ui);
				explainUI.showUI(explainUI);
//				userUI.setVisible(false);
			}
		});
		popupMenu.add(menuItem);

		contentPane.add(scrollPane);

		lblSearchType = new JLabel("\u8BF7\u9009\u62E9\u4F60\u8981\u67E5\u8BE2\u7684\u7C7B\u578B\uFF1A");
		lblSearchType.setBounds(500, 373, 198, 18);
		lblSearchType.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(lblSearchType);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(712, 370, 175, 24);
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		comboBox.addItem("查询所有图书");
		comboBox.addItem("按书名查询");
		comboBox.addItem("按类型查询");
		comboBox.addItem("按作者查询");
		comboBox.addActionListener(new comboBox());// 将下拉框与查询按钮相关联
		contentPane.add(comboBox);

		lblSearch = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u67E5\u8BE2\u7684\u5185\u5BB9\uFF1A");
		lblSearch.setBounds(500, 425, 198, 18);
		lblSearch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(lblSearch);

		textSearch = new JTextField();
		textSearch.setBounds(712, 422, 175, 24);
		textSearch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(textSearch);
		textSearch.setColumns(10);

		JButton btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.setBounds(774, 459, 113, 27);
		btnSearch.addActionListener(new btnSearch());
		btnSearch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(btnSearch);

		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u501F\u9605\u4E66\u7C4D\u7684\u4E66\u540D:");
		lblNewLabel.setBounds(467, 540, 231, 18);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);

		textRead = new JTextField();
		textRead.setBounds(712, 539, 175, 24);
		textRead.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(textRead);
		textRead.setColumns(10);

		buttonRead = new JButton("\u501F\u9605");
		buttonRead.setBounds(774, 576, 113, 27);
		buttonRead.addActionListener(new buttonRead());
		buttonRead.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		contentPane.add(buttonRead);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(423, 348, 493, 279);
		contentPane.add(scrollPane_2);

		buttonOff = new JButton("");
		buttonOff.setBounds(758, 4, 73, 27);
		buttonOff.addActionListener(new buttonOff());
		buttonOff.setIcon(new ImageIcon("picture//button//off//注销.png"));
		buttonOff.setPressedIcon(new ImageIcon("picture//button//off//注销2.png"));
		// 隐藏按钮的方法
		buttonOff.setBorder(null);
		buttonOff.setOpaque(false);
		buttonOff.setContentAreaFilled(false);
		// 去掉按钮文字周围的焦点框
		buttonOff.setFocusPainted(false);
		contentPane.add(buttonOff);

		// ——————————————————用户借书表——————————————————————
		scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(38, 348, 361, 95);
		contentPane.add(scrollPaneUser);

		userTable = new JTable();
		// 表格双击响应事件
		userTable.addMouseListener(new UserTableClicked());
		userTable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		userTable.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 18));
		scrollPaneUser.setViewportView(userTable);
		userTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		userTable.setBounds(53, 63, 686, 272);
		// 设置行高
		userTable.setRowHeight(20);
		showUser();

		contentPane.add(scrollPaneUser);
		// 更新已借书籍和借书日期

		// ——————————————————用户借书表——————————————————————

		JLabel labelWelcome = new JLabel("");
		labelWelcome.setBounds(14, 4, 245, 27);
		labelWelcome.setForeground(Color.WHITE);
		labelWelcome.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		labelWelcome.setText("欢迎你：" + user.getName() + "!");
		contentPane.add(labelWelcome);

		// 背景图
		bgp = new ImageIcon("picture//UI//普通用户界面2.jpg");// 这是背景图片
		
		JLabel label = new JLabel("\u6E29\u99A8\u63D0\u793A\uFF1A\u53CC\u51FB\u4E0A\u8868\u884C\u53EF\u8FD8\u4E66\uFF0C\u53F3\u952E\u60A8\u60F3\u67E5\u9605\u7684\u56FE\u4E66");
		label.setBounds(48, 456, 351, 18);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u4EE5\u67E5\u9605\u8BE6\u7EC6\u8D44\u6599");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		label_1.setBounds(48, 486, 351, 18);
		contentPane.add(label_1);
		background = new JLabel(bgp);
		background.setBounds(0, 0, 956, 667);
		contentPane.add(background);
	}

	// 拖动界面
	public void dragUI() {

		this.addMouseListener(new MouseAdapter() {
			// 记录点击的位置
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		// 重新设置舞台位置
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();// 记录点抬起时的位置？
				int yOnScreen = e.getYOnScreen();
				{
					int xx = xOnScreen - xOld;
					int yy = yOnScreen - yOld;
					UserUI.this.setLocation(xx, yy);
				}
			}
		});
	}

	// 退出按钮
	class btnExit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

	}

	// 最小化按钮
	class buttonMini implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			userUI.setExtendedState(JFrame.ICONIFIED);
		}

	}

	// 显示图书
	public void showBook(int searchFlag, String searchText) {
		Book book = new Book();

		switch (searchFlag) {
		case 0:
			bookDtw = new BookDAO().selectBook("all", null);
			bookTable.setModel(bookDtw);
			break;
		case 1:
			book.setName(searchText);
			bookDtw = new BookDAO().selectBook("name", book);
			bookTable.setModel(bookDtw);
			break;
		case 2:
			book.setType(searchText);
			bookDtw = new BookDAO().selectBook("type", book);
			bookTable.setModel(bookDtw);
			break;
		case 3:
			book.setAuthor(searchText);
			bookDtw = new BookDAO().selectBook("author", book);
			bookTable.setModel(bookDtw);
			break;
		}
	}

	// 显示窗口
	public void showUI(UserUI userUI) {
		this.userUI = userUI;
		this.userUI.setVisible(true);
		reSize(userUI);
	}

	// 圆角化窗口
	public static void reSize(UserUI userUI) {
		// 下面圆角边框代码需要放在实例化以后不然会空指针
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, userUI.getWidth(), userUI.getHeight(), 30, 30);// 空你妈逼的指针？

		// 圆角化
		userUI.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				userUI.setShape(shape);
			}
		});
	}

	// 下拉框监听事件
	class comboBox implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String str = (String) comboBox.getSelectedItem();

			switch (str) {
			case "查询所有图书":
				searchFlag = 0;
				bookDtw = new BookDAO().selectBook("all", null);
				bookTable.setModel(bookDtw);
				break;
			case "按书名查询":
				searchFlag = 1;
				break;
			case "按类型查询":
				searchFlag = 2;
				break;
			case "按作者查询":
				searchFlag = 3;
				break;
			}
		}
	}

	// 查询按钮
	class btnSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			searchText = textSearch.getText();

			showBook(searchFlag,searchText);

		}

	}

	// 借阅按钮
	class buttonRead implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (textRead.getText().length() == 0)// 未输入值提示
			{
				JOptionPane.showConfirmDialog(null, "输入不能为空", "提示", JOptionPane.YES_NO_OPTION);
			} else {
				// 接收输入的书名
				Book book = new Book();
				book.setName(textRead.getText());

				// 借书操作封装
				borrow(book);

				// 刷新表格
				showBook(searchFlag,searchText);

				// 更新已借书籍和借书日期
				showUser();

			}

		}
	}

	// 注销按钮
	class buttonOff implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = JOptionPane.showConfirmDialog(null, "确定要注销吗？", "提示", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_NO_OPTION) {
				LoginUI login = new LoginUI();
				login.showUI(login);
				userUI.setVisible(false);
				if(explainUI != null){
					explainUI.setVisible(false);
				}
			}
		}
	}

	// 显示用户数据
	public static  void showUser() {
		// 更新已借书籍和借书日期
		Object[][] userData = { { user.getBook1(), user.getDate1() }, { user.getBook2(), user.getDate2() },
				{ user.getBook3(), user.getDate3() } };

		String[] columnNames = { "已借书籍", "借书日期" };
		DefaultTableModel userDtw = new DefaultTableModel(userData, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;// 返回true表示能编辑，false表示不能编辑
			}
		};
		userTable.setModel(userDtw);
	}

	// 返回间隔天数
	public int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	// 借书操作，修改数据库数据
	public static Book borrow(Book book) {
		// 根据书名加载该书所有信息，存放在book中
		List<Book> list = bookDao.getAll("name", book);

		if (list.size() != 0) {// 当该书存在时执行
			book = list.get(0);
			// 只有书籍数量大于0，并且用户借书数量少于3时才可以执行借书操作，否则显示相应提示
			if (book.getCount() <= 0) {
				JOptionPane.showConfirmDialog(null, "对不起，书籍库存不足", "提示", JOptionPane.YES_NO_OPTION);
			} else if (user.getCount() >= 3) {
				JOptionPane.showConfirmDialog(null, "对不起，您只能借阅三本书", "提示", JOptionPane.YES_NO_OPTION);
			} else {
				// 书籍数量减一
				book.setCount(book.getCount() - 1);
				// 在数据库中修改
				bookDao.change(book);

				// 将当前日期转换为String
				SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				// String times = time.toString();
				String times = timeFormat.format(time);

				// 根据用户借书数量选择存储位置
				switch (user.getCount()) {
				case 0:
					user.setBook1(book.getName());// 在用户信息中存放借阅的书籍名字
					user.setDate1(times);// 在用户信息中存放借阅的时间
					user.setCount(user.getCount() + 1);// 用户借书数量加一
					break;
				case 1:
					user.setBook2(book.getName());
					user.setDate2(times);
					user.setCount(user.getCount() + 1);
					break;
				case 2:
					user.setBook3(book.getName());
					user.setDate3(times);
					user.setCount(user.getCount() + 1);
					break;
				default:
					break;
				}// switch
					// 在数据库中修改用户信息
				userDao.change(user);
				JOptionPane.showConfirmDialog(null, "借书成功", "提示", JOptionPane.YES_NO_OPTION);
			}
		} else {
			JOptionPane.showConfirmDialog(null, "对不起，该书不存在", "提示", JOptionPane.YES_NO_OPTION);
		}
		return book;
	}

	// 还书操作，修改数据库数据
	public void returnBook(Book book) {
		List<Book> list = bookDao.getAll("name", book);
		int sequenceNumber = 0;// 记录还第几本书
		if (user.getBook1().equals(book.getName())) {// ==================需要添加此书被管理员删除的情况=================
			sequenceNumber = 1;
		} else if (user.getBook2().equals(book.getName())) {
			sequenceNumber = 2;
		} else if (user.getBook3().equals(book.getName())) {
			sequenceNumber = 3;
		}
		if (sequenceNumber != 0) {
			if (list.size() != 0) {
				book = list.get(0);
				// 书籍数量加一
				book.setCount(book.getCount() + 1);
				// 在数据库中修改
				bookDao.change(book);
			}
			// 提示借书天数
			String s = null;
			Date date2 = null;
			Date date1 = null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			switch (sequenceNumber) {
			case 1:
				s = user.getDate1();
				break;
			case 2:
				s = user.getDate2();
				break;
			case 3:
				s = user.getDate3();
				break;
			default:
				break;
			}
			try {
				date2 = new Date();
				date1 = formatter.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 用以提示借书天数
			int n = differentDaysByMillisecond(date1, date2);

			// 根据用户还第几本书决定操作
			switch (user.getCount()) {
			case 1:
				user.setBook1(null);
				user.setDate1(null);
				user.setCount(user.getCount() - 1);// 用户借书数量加一
				break;
			case 2:
				if (sequenceNumber == 1) {
					user.setBook1(user.getBook2());
					user.setDate1(user.getDate2());
					user.setBook2(null);
					user.setDate2(null);
				} else if (sequenceNumber == 2) {
					user.setBook2(null);
					user.setDate2(null);
				}
				user.setCount(user.getCount() - 1);
				break;
			case 3:
				if (sequenceNumber == 1) {
					user.setBook1(user.getBook2());
					user.setDate1(user.getDate2());
					user.setBook2(user.getBook3());
					user.setDate2(user.getDate3());
					user.setBook3(null);
					user.setDate3(null);
				} else if (sequenceNumber == 2) {
					user.setBook2(user.getBook3());
					user.setDate2(user.getDate3());
					user.setBook3(null);
					user.setDate3(null);
				} else if (sequenceNumber == 3) {
					user.setBook3(null);
					user.setDate3(null);
				}
				user.setCount(user.getCount() - 1);
				break;
			default:
				break;
			}// switch
				// 在数据库中修改用户信息
			userDao.change(user);
			JOptionPane.showConfirmDialog(null, "还书成功" + "你已借该书" + n + "天", "提示", JOptionPane.YES_NO_OPTION);
		} else {
			JOptionPane.showConfirmDialog(null, "你未借该书", "提示", JOptionPane.YES_NO_OPTION);
		}
	}

	// 表格双击还书
	class UserTableClicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (userTable.getSelectedRow() != -1 && userTable.getSelectedRow() < user.getCount())

			// 选中有图书的行才会有提示
			{
				if (e.getClickCount() == 2) {// 点击几次
					int i = JOptionPane.showConfirmDialog(null, "要还这本书吗？", "提示", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						// 接收书名
						Book book = new Book();
						book.setName(String.valueOf(userTable.getValueAt(userTable.getSelectedRow(), 0)));// 表格从0开始计，书名在第1列，所以为0
						// 还书操作封装
						returnBook(book);

						// 刷新表格
						showBook(searchFlag,searchText);

						// 更新已借书籍和借书日期
						showUser();
					}
				}

			}
		}
	}

	// 表格双击借阅
	class BookTableClicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (bookTable.getSelectedRow() != -1 && bookTable.getSelectedRow() < bookTable.getRowCount())
			// 选中有图书的行才会有提示
			{
				if (e.getClickCount() == 2) {// 点击几次
					int i = JOptionPane.showConfirmDialog(null, "要借阅这本书吗？", "提示", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						// 接收输入的书名
						Book book = new Book();
						book.setName(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 2)));// 表格从0开始计，书名在第三列，所以为2
						// 借书操作封装
						book = borrow(book);

						// 更改表格数据
						bookTable.setValueAt(book.getCount(), bookTable.getSelectedRow(), 6);
						//刷新用户数据
						showUser();
					}
				}
			}
		}
	}
	
	// 表格右键借阅
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3){
					
				 	tableindex=bookTable.rowAtPoint(e.getPoint());
				 	tablecolumn=bookTable.columnAtPoint(e.getPoint());
				 	bookTable.setRowSelectionInterval(tableindex, tableindex);
				 	popupMenu.show(bookTable, e.getX(), e.getY());
				}
			}
		});
	}
	public static String  passname(){
		return user.getName();
	}
	public static Book  passbookname(){
		 Book book = new Book();
		book.setName(String.valueOf(bookTable.getValueAt(tableindex, 2)));
		book.setType((String) bookTable.getValueAt(tableindex, 1));
		book.setAuthor((String) bookTable.getValueAt(tableindex, 4));
		book.setPress((String) bookTable.getValueAt(tableindex, 3));
		return  book;
	}
	public static UserUI  passframe(){
		return userUI;
	}
}
