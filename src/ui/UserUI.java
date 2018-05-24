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

		// �õ���ǰ��¼���û���Ϣ
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
		btnExit.setIcon(new ImageIcon("picture//button//exit//�˳�.png"));
		btnExit.setRolloverIcon(new ImageIcon("picture//button//exit//�˳�2.png"));
		// ���ذ�ť�ķ���
		btnExit.setBorder(null);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		// ȥ����ť������Χ�Ľ����
		btnExit.setFocusPainted(false);
		contentPane.add(btnExit);

		buttonMini = new JButton("");
		buttonMini.setBounds(850, 4, 41, 27);
		buttonMini.addActionListener(new buttonMini());
		buttonMini.setIcon(new ImageIcon("picture//button//mini//��С��.png"));
		buttonMini.setRolloverIcon(
				new ImageIcon("picture//button//mini//��С��2.png"));
		// ���ذ�ť�ķ���
		buttonMini.setBorder(null);
		buttonMini.setOpaque(false);
		buttonMini.setContentAreaFilled(false);
		// ȥ����ť������Χ�Ľ����
		buttonMini.setFocusPainted(false);
		contentPane.add(buttonMini);

		setTitle("haha ");
		// ȥ������߿�
		setUndecorated(true);
		// ���ڲ��ɵ�
		setResizable(false);
		// ���϶�����
		dragUI();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 53, 878, 269);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);

		bookTable = new JTable();

		// �鼮���˫���¼�
		bookTable.addMouseListener(new BookTableClicked());

		bookTable.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		bookTable.getTableHeader().setFont(new Font("΢���ź�", Font.PLAIN, 18));
		scrollPane.setViewportView(bookTable);
		bookTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		bookTable.setBounds(53, 63, 686, 272);
		// �����и�
		bookTable.setRowHeight(20);

		bookDtw = new BookDAO().selectBook("all", null);
		bookTable.setModel(bookDtw);
		
		 popupMenu = new JPopupMenu();
		addPopup(bookTable, popupMenu);
		
		// ͨ���Ҽ��˵�����˵������
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
		lblSearchType.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		contentPane.add(lblSearchType);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(712, 370, 175, 24);
		comboBox.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		comboBox.addItem("��ѯ����ͼ��");
		comboBox.addItem("��������ѯ");
		comboBox.addItem("�����Ͳ�ѯ");
		comboBox.addItem("�����߲�ѯ");
		comboBox.addActionListener(new comboBox());// �����������ѯ��ť�����
		contentPane.add(comboBox);

		lblSearch = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u67E5\u8BE2\u7684\u5185\u5BB9\uFF1A");
		lblSearch.setBounds(500, 425, 198, 18);
		lblSearch.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		contentPane.add(lblSearch);

		textSearch = new JTextField();
		textSearch.setBounds(712, 422, 175, 24);
		textSearch.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		contentPane.add(textSearch);
		textSearch.setColumns(10);

		JButton btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.setBounds(774, 459, 113, 27);
		btnSearch.addActionListener(new btnSearch());
		btnSearch.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		contentPane.add(btnSearch);

		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u501F\u9605\u4E66\u7C4D\u7684\u4E66\u540D:");
		lblNewLabel.setBounds(467, 540, 231, 18);
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);

		textRead = new JTextField();
		textRead.setBounds(712, 539, 175, 24);
		textRead.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		contentPane.add(textRead);
		textRead.setColumns(10);

		buttonRead = new JButton("\u501F\u9605");
		buttonRead.setBounds(774, 576, 113, 27);
		buttonRead.addActionListener(new buttonRead());
		buttonRead.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		contentPane.add(buttonRead);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(423, 348, 493, 279);
		contentPane.add(scrollPane_2);

		buttonOff = new JButton("");
		buttonOff.setBounds(758, 4, 73, 27);
		buttonOff.addActionListener(new buttonOff());
		buttonOff.setIcon(new ImageIcon("picture//button//off//ע��.png"));
		buttonOff.setPressedIcon(new ImageIcon("picture//button//off//ע��2.png"));
		// ���ذ�ť�ķ���
		buttonOff.setBorder(null);
		buttonOff.setOpaque(false);
		buttonOff.setContentAreaFilled(false);
		// ȥ����ť������Χ�Ľ����
		buttonOff.setFocusPainted(false);
		contentPane.add(buttonOff);

		// �������������������������������������û������������������������������������������������
		scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(38, 348, 361, 95);
		contentPane.add(scrollPaneUser);

		userTable = new JTable();
		// ���˫����Ӧ�¼�
		userTable.addMouseListener(new UserTableClicked());
		userTable.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		userTable.getTableHeader().setFont(new Font("΢���ź�", Font.PLAIN, 18));
		scrollPaneUser.setViewportView(userTable);
		userTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		userTable.setBounds(53, 63, 686, 272);
		// �����и�
		userTable.setRowHeight(20);
		showUser();

		contentPane.add(scrollPaneUser);
		// �����ѽ��鼮�ͽ�������

		// �������������������������������������û������������������������������������������������

		JLabel labelWelcome = new JLabel("");
		labelWelcome.setBounds(14, 4, 245, 27);
		labelWelcome.setForeground(Color.WHITE);
		labelWelcome.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		labelWelcome.setText("��ӭ�㣺" + user.getName() + "!");
		contentPane.add(labelWelcome);

		// ����ͼ
		bgp = new ImageIcon("picture//UI//��ͨ�û�����2.jpg");// ���Ǳ���ͼƬ
		
		JLabel label = new JLabel("\u6E29\u99A8\u63D0\u793A\uFF1A\u53CC\u51FB\u4E0A\u8868\u884C\u53EF\u8FD8\u4E66\uFF0C\u53F3\u952E\u60A8\u60F3\u67E5\u9605\u7684\u56FE\u4E66");
		label.setBounds(48, 456, 351, 18);
		label.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u4EE5\u67E5\u9605\u8BE6\u7EC6\u8D44\u6599");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 15));
		label_1.setBounds(48, 486, 351, 18);
		contentPane.add(label_1);
		background = new JLabel(bgp);
		background.setBounds(0, 0, 956, 667);
		contentPane.add(background);
	}

	// �϶�����
	public void dragUI() {

		this.addMouseListener(new MouseAdapter() {
			// ��¼�����λ��
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		// ����������̨λ��
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();// ��¼��̧��ʱ��λ�ã�
				int yOnScreen = e.getYOnScreen();
				{
					int xx = xOnScreen - xOld;
					int yy = yOnScreen - yOld;
					UserUI.this.setLocation(xx, yy);
				}
			}
		});
	}

	// �˳���ť
	class btnExit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

	}

	// ��С����ť
	class buttonMini implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			userUI.setExtendedState(JFrame.ICONIFIED);
		}

	}

	// ��ʾͼ��
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

	// ��ʾ����
	public void showUI(UserUI userUI) {
		this.userUI = userUI;
		this.userUI.setVisible(true);
		reSize(userUI);
	}

	// Բ�ǻ�����
	public static void reSize(UserUI userUI) {
		// ����Բ�Ǳ߿������Ҫ����ʵ�����Ժ�Ȼ���ָ��
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, userUI.getWidth(), userUI.getHeight(), 30, 30);// ������Ƶ�ָ�룿

		// Բ�ǻ�
		userUI.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				userUI.setShape(shape);
			}
		});
	}

	// ����������¼�
	class comboBox implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String str = (String) comboBox.getSelectedItem();

			switch (str) {
			case "��ѯ����ͼ��":
				searchFlag = 0;
				bookDtw = new BookDAO().selectBook("all", null);
				bookTable.setModel(bookDtw);
				break;
			case "��������ѯ":
				searchFlag = 1;
				break;
			case "�����Ͳ�ѯ":
				searchFlag = 2;
				break;
			case "�����߲�ѯ":
				searchFlag = 3;
				break;
			}
		}
	}

	// ��ѯ��ť
	class btnSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			searchText = textSearch.getText();

			showBook(searchFlag,searchText);

		}

	}

	// ���İ�ť
	class buttonRead implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (textRead.getText().length() == 0)// δ����ֵ��ʾ
			{
				JOptionPane.showConfirmDialog(null, "���벻��Ϊ��", "��ʾ", JOptionPane.YES_NO_OPTION);
			} else {
				// �������������
				Book book = new Book();
				book.setName(textRead.getText());

				// ���������װ
				borrow(book);

				// ˢ�±��
				showBook(searchFlag,searchText);

				// �����ѽ��鼮�ͽ�������
				showUser();

			}

		}
	}

	// ע����ť
	class buttonOff implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫע����", "��ʾ", JOptionPane.YES_NO_OPTION);
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

	// ��ʾ�û�����
	public static  void showUser() {
		// �����ѽ��鼮�ͽ�������
		Object[][] userData = { { user.getBook1(), user.getDate1() }, { user.getBook2(), user.getDate2() },
				{ user.getBook3(), user.getDate3() } };

		String[] columnNames = { "�ѽ��鼮", "��������" };
		DefaultTableModel userDtw = new DefaultTableModel(userData, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;// ����true��ʾ�ܱ༭��false��ʾ���ܱ༭
			}
		};
		userTable.setModel(userDtw);
	}

	// ���ؼ������
	public int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	// ����������޸����ݿ�����
	public static Book borrow(Book book) {
		// �����������ظ���������Ϣ�������book��
		List<Book> list = bookDao.getAll("name", book);

		if (list.size() != 0) {// ���������ʱִ��
			book = list.get(0);
			// ֻ���鼮��������0�������û�������������3ʱ�ſ���ִ�н��������������ʾ��Ӧ��ʾ
			if (book.getCount() <= 0) {
				JOptionPane.showConfirmDialog(null, "�Բ����鼮��治��", "��ʾ", JOptionPane.YES_NO_OPTION);
			} else if (user.getCount() >= 3) {
				JOptionPane.showConfirmDialog(null, "�Բ�����ֻ�ܽ���������", "��ʾ", JOptionPane.YES_NO_OPTION);
			} else {
				// �鼮������һ
				book.setCount(book.getCount() - 1);
				// �����ݿ����޸�
				bookDao.change(book);

				// ����ǰ����ת��ΪString
				SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				// String times = time.toString();
				String times = timeFormat.format(time);

				// �����û���������ѡ��洢λ��
				switch (user.getCount()) {
				case 0:
					user.setBook1(book.getName());// ���û���Ϣ�д�Ž��ĵ��鼮����
					user.setDate1(times);// ���û���Ϣ�д�Ž��ĵ�ʱ��
					user.setCount(user.getCount() + 1);// �û�����������һ
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
					// �����ݿ����޸��û���Ϣ
				userDao.change(user);
				JOptionPane.showConfirmDialog(null, "����ɹ�", "��ʾ", JOptionPane.YES_NO_OPTION);
			}
		} else {
			JOptionPane.showConfirmDialog(null, "�Բ��𣬸��鲻����", "��ʾ", JOptionPane.YES_NO_OPTION);
		}
		return book;
	}

	// ����������޸����ݿ�����
	public void returnBook(Book book) {
		List<Book> list = bookDao.getAll("name", book);
		int sequenceNumber = 0;// ��¼���ڼ�����
		if (user.getBook1().equals(book.getName())) {// ==================��Ҫ��Ӵ��鱻����Աɾ�������=================
			sequenceNumber = 1;
		} else if (user.getBook2().equals(book.getName())) {
			sequenceNumber = 2;
		} else if (user.getBook3().equals(book.getName())) {
			sequenceNumber = 3;
		}
		if (sequenceNumber != 0) {
			if (list.size() != 0) {
				book = list.get(0);
				// �鼮������һ
				book.setCount(book.getCount() + 1);
				// �����ݿ����޸�
				bookDao.change(book);
			}
			// ��ʾ��������
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
			// ������ʾ��������
			int n = differentDaysByMillisecond(date1, date2);

			// �����û����ڼ������������
			switch (user.getCount()) {
			case 1:
				user.setBook1(null);
				user.setDate1(null);
				user.setCount(user.getCount() - 1);// �û�����������һ
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
				// �����ݿ����޸��û���Ϣ
			userDao.change(user);
			JOptionPane.showConfirmDialog(null, "����ɹ�" + "���ѽ����" + n + "��", "��ʾ", JOptionPane.YES_NO_OPTION);
		} else {
			JOptionPane.showConfirmDialog(null, "��δ�����", "��ʾ", JOptionPane.YES_NO_OPTION);
		}
	}

	// ���˫������
	class UserTableClicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (userTable.getSelectedRow() != -1 && userTable.getSelectedRow() < user.getCount())

			// ѡ����ͼ����вŻ�����ʾ
			{
				if (e.getClickCount() == 2) {// �������
					int i = JOptionPane.showConfirmDialog(null, "Ҫ���Ȿ����", "��ʾ", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						// ��������
						Book book = new Book();
						book.setName(String.valueOf(userTable.getValueAt(userTable.getSelectedRow(), 0)));// ����0��ʼ�ƣ������ڵ�1�У�����Ϊ0
						// ���������װ
						returnBook(book);

						// ˢ�±��
						showBook(searchFlag,searchText);

						// �����ѽ��鼮�ͽ�������
						showUser();
					}
				}

			}
		}
	}

	// ���˫������
	class BookTableClicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (bookTable.getSelectedRow() != -1 && bookTable.getSelectedRow() < bookTable.getRowCount())
			// ѡ����ͼ����вŻ�����ʾ
			{
				if (e.getClickCount() == 2) {// �������
					int i = JOptionPane.showConfirmDialog(null, "Ҫ�����Ȿ����", "��ʾ", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						// �������������
						Book book = new Book();
						book.setName(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 2)));// ����0��ʼ�ƣ������ڵ����У�����Ϊ2
						// ���������װ
						book = borrow(book);

						// ���ı������
						bookTable.setValueAt(book.getCount(), bookTable.getSelectedRow(), 6);
						//ˢ���û�����
						showUser();
					}
				}
			}
		}
	}
	
	// ����Ҽ�����
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
