package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.JTable;

import javax.swing.border.LineBorder;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import item.Book;
import item.User;
import dao.BookDAO;
import dao.UserDAO;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import javax.swing.JComboBox;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.Shape;

public class AdminUI extends JFrame {

	// 管理员界面
	private static AdminUI admin;
	private JPanel contentPane;
	private String searchText;
	private int searchFlag = 0;

	// 鼠标在当前界面的坐标
	private int xOld, yOld;

	// 表格信息
	private JTable bookTable;
	private DefaultTableModel bookDtw;
	private DefaultTableCellRenderer tableCell;

	// 最小化按钮
	private JButton buttonMini;
	// 注销按钮
	private JButton buttonOff;
	// 退出按钮
	private JButton btnExit;

	// 背景图片
	private ImageIcon bgp;
	private JLabel background;

	// 各文本框
	private JTextField[] text = new JTextField[6];
	private JTextField[] textId = new JTextField[2];
	private JTextField textSearch;

	// 各标签
	private JLabel[] label = new JLabel[6];
	private JLabel lblInputID;
	private JLabel lblId;
	private JLabel labelSelectSearch;
	private JLabel lblDelete;
	private JLabel lblInputId;
	private JLabel lblSearch;
	private JLabel lblNewLabel;

	// 各按钮，查询图书信息，添加图书信息，删除图书信息，修改图书信息，查询用户信息等
	private JButton buttonAdd;
	private JButton ButtonDelete;
	private JButton buttonAddSetVisible;
	private JButton ButtonDeleteSetVisible;
	private JButton buttonChange;
	private JButton buttonChangeSetVisible;
	private JButton buttonSure;
	private JButton buttonSearchSetVisible;
	private JButton btnSearch;

	// 下拉框
	private JComboBox<String> comboBoxSearch;

	// 滚动条
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// admin = new AdminUI();
					admin.setVisible(true);
					// reSize(admin);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminUI(User admin) {
		setTitle("欢迎你：");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 956, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);

		// 去除界面边框
		setUndecorated(true);
		// 窗口不可调
		setResizable(false);
		// 可拖动窗口
		dragUI();

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(38, 78, 678, 244);
		contentPane.add(scrollPane);

		bookTable = new JTable();
		// 表格响应事件
		bookTable.addMouseListener(new tableClicked());

		bookTable.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		bookTable.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 18));
		scrollPane.setViewportView(bookTable);
		bookTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		bookTable.setBounds(53, 63, 686, 272);

		bookDtw = new BookDAO().selectBook("all", null);
		bookTable.setModel(bookDtw);

		// 表格单元居中显示
		tableCell = new DefaultTableCellRenderer();
		tableCell.setHorizontalAlignment(JLabel.CENTER);
		bookTable.setDefaultRenderer(Object.class, tableCell);

		// 设置表格列与行的宽
		setTable();

		contentPane.add(scrollPane);

		label[0] = new JLabel("\u7C7B  \u578B\uFF1A");
		label[1] = new JLabel("\u540D  \u79F0\uFF1A");
		label[2] = new JLabel("\u51FA\u7248\u793E\uFF1A");
		label[3] = new JLabel("\u4F5C  \u8005\uFF1A");
		label[4] = new JLabel("\u4EF7  \u683C\uFF1A");
		label[5] = new JLabel("数  量：");

		for (int i = 0; i < 6; i++) {
			text[i] = new JTextField();
			text[i].setBounds(139, 406 + i * 30, 100, 25);
			text[i].setVisible(false);
			text[i].setFont(new Font("微软雅黑", Font.PLAIN, 18));
			contentPane.add(text[i]);
			text[i].setColumns(10);

			label[i].setBounds(67, 406 + i * 30, 72, 18);
			label[i].setVisible(false);
			label[i].setFont(new Font("微软雅黑", Font.PLAIN, 18));
			contentPane.add(label[i]);
		}

		buttonAdd = new JButton("\u6DFB\u52A0");
		buttonAdd.setFont(new Font("宋体", Font.PLAIN, 18));
		buttonAdd.addActionListener(new buttonAdd());
		buttonAdd.setBounds(563, 564, 113, 27);
		buttonAdd.setVisible(false);
		contentPane.add(buttonAdd);

		buttonAddSetVisible = new JButton("\u6DFB\u52A0\u56FE\u4E66\u4FE1\u606F");
		buttonAddSetVisible.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		buttonAddSetVisible.addActionListener(new buttonAddSetVisible());
		buttonAddSetVisible.setBounds(765, 148, 148, 27);
		contentPane.add(buttonAddSetVisible);

		ButtonDeleteSetVisible = new JButton("\u5220\u9664\u56FE\u4E66\u4FE1\u606F");
		ButtonDeleteSetVisible.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		ButtonDeleteSetVisible.addActionListener(new ButtonDeleteSetVisible());
		ButtonDeleteSetVisible.setBounds(765, 221, 148, 27);
		contentPane.add(ButtonDeleteSetVisible);

		ButtonDelete = new JButton("\u5220\u9664");
		ButtonDelete.setFont(new Font("宋体", Font.PLAIN, 18));
		ButtonDelete.addActionListener(new ButtonDelete());
		ButtonDelete.setBounds(563, 564, 113, 27);
		ButtonDelete.setVisible(false);
		contentPane.add(ButtonDelete);

		lblDelete = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u5220\u9664\u7684\u56FE\u4E66ID\uFF1A");
		lblDelete.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblDelete.setBounds(110, 398, 219, 18);
		lblDelete.setVisible(false);
		contentPane.add(lblDelete);

		textId[0] = new JTextField();
		textId[0].setBounds(358, 395, 148, 24);
		textId[0].setVisible(false);
		textId[0].setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(textId[0]);
		textId[0].setColumns(10);

		buttonChangeSetVisible = new JButton("\u4FEE\u6539\u56FE\u4E66\u4FE1\u606F");
		buttonChangeSetVisible.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		buttonChangeSetVisible.addActionListener(new buttonChangeSetVisible());
		buttonChangeSetVisible.setBounds(765, 295, 148, 27);
		contentPane.add(buttonChangeSetVisible);

		buttonSure = new JButton("\u786E\u8BA4");
		buttonSure.setFont(new Font("宋体", Font.PLAIN, 18));
		buttonSure.setBounds(563, 564, 113, 27);
		buttonSure.addActionListener(new buttonSure());
		buttonSure.setVisible(false);
		contentPane.add(buttonSure);

		lblInputId = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u4FEE\u6539\u7684\u56FE\u4E66ID\uFF1A");
		lblInputId.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblInputId.setBounds(110, 398, 219, 18);
		lblInputId.setVisible(false);
		contentPane.add(lblInputId);

		textId[1] = new JTextField();
		textId[1].setBounds(358, 395, 148, 24);
		textId[1].setVisible(false);
		textId[1].setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(textId[1]);
		textId[1].setColumns(10);

		lblId = new JLabel("    ID  \uFF1A");
		lblId.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblId.setBounds(67, 376, 72, 18);
		lblId.setVisible(false);
		contentPane.add(lblId);

		lblInputID = new JLabel("New label");
		lblInputID.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblInputID.setBounds(138, 373, 100, 25);// 139, 406+i*30, 100, 25
		lblInputID.setVisible(false);
		contentPane.add(lblInputID);

		buttonChange = new JButton("\u786E\u8BA4\u4FEE\u6539");
		buttonChange.setFont(new Font("宋体", Font.PLAIN, 18));
		buttonChange.addActionListener(new buttonChange());
		buttonChange.setBounds(563, 564, 113, 27);
		buttonChange.setVisible(false);
		contentPane.add(buttonChange);

		buttonSearchSetVisible = new JButton("\u67E5\u8BE2\u56FE\u4E66\u4FE1\u606F");
		buttonSearchSetVisible.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		buttonSearchSetVisible.addActionListener(new buttonSearchSetVisible());
		buttonSearchSetVisible.setBounds(765, 80, 148, 27);
		contentPane.add(buttonSearchSetVisible);

		labelSelectSearch = new JLabel(
				"\u8BF7\u9009\u62E9\u4F60\u8981\u67E5\u8BE2\u7684\u56FE\u4E66\u6761\u4EF6\uFF1A");
		labelSelectSearch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		labelSelectSearch.setBounds(110, 398, 234, 18);
		labelSelectSearch.setVisible(false);
		contentPane.add(labelSelectSearch);

		comboBoxSearch = new JComboBox<String>();
		comboBoxSearch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		comboBoxSearch.setBounds(358, 395, 140, 24);
		comboBoxSearch.addItem("查询所有图书");
		comboBoxSearch.addItem("按书名查询");
		comboBoxSearch.addItem("按类型查询");
		comboBoxSearch.addItem("按作者查询");
		comboBoxSearch.addActionListener(new comboBoxSearch());// 将下拉框与查询按钮相关联
		comboBoxSearch.setVisible(false);
		contentPane.add(comboBoxSearch);

		lblSearch = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u67E5\u8BE2\u7684\u56FE\u4E66\u5185\u5BB9\uFF1A");
		lblSearch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblSearch.setBounds(110, 446, 234, 18);
		lblSearch.setVisible(false);
		contentPane.add(lblSearch);

		textSearch = new JTextField();
		textSearch.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textSearch.setBounds(358, 445, 221, 24);
		textSearch.setVisible(false);
		contentPane.add(textSearch);
		textSearch.setColumns(10);

		btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.setFont(new Font("宋体", Font.PLAIN, 18));
		btnSearch.setBounds(563, 564, 113, 27);
		btnSearch.addActionListener(new btnSearch());
		btnSearch.setVisible(false);
		contentPane.add(btnSearch);

		btnExit = new JButton();
		btnExit.addActionListener(new btnExit());
		btnExit.setIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//exit//退出.png"));
		btnExit.setRolloverIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//exit//退出2.png"));
		// 隐藏按钮的方法
		btnExit.setBorder(null);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		// 去掉按钮文字周围的焦点框
		btnExit.setFocusPainted(false);
		btnExit.setBounds(892, 13, 50, 27);
		contentPane.add(btnExit);

		buttonMini = new JButton("");
		buttonMini.addActionListener(new buttonMini());
		buttonMini.setIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//mini//最小化.png"));
		buttonMini.setRolloverIcon(
				new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//mini//最小化2.png"));
		// 隐藏按钮的方法
		buttonMini.setBorder(null);
		buttonMini.setOpaque(false);
		buttonMini.setContentAreaFilled(false);
		// 去掉按钮文字周围的焦点框
		buttonMini.setFocusPainted(false);
		buttonMini.setBounds(828, 13, 50, 27);
		contentPane.add(buttonMini);

		buttonOff = new JButton("");
		buttonOff.addActionListener(new buttonOff());
		buttonOff.setIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//off//注销.png"));
		buttonOff.setPressedIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//off//注销2.png"));
		// 隐藏按钮的方法
		buttonOff.setBorder(null);
		buttonOff.setOpaque(false);
		buttonOff.setContentAreaFilled(false);
		// 去掉按钮文字周围的焦点框
		buttonOff.setFocusPainted(false);
		buttonOff.setBounds(742, 13, 72, 27);
		contentPane.add(buttonOff);

		lblNewLabel = new JLabel("欢迎你：" + admin.getName() + "!");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(14, 7, 234, 27);
		contentPane.add(lblNewLabel);

		// 背景图
		bgp = new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//UI//管理员界面2.jpg");// 这是背景图片

		JButton buttonSearchUser = new JButton("\u67E5\u8BE2\u7528\u6237\u4FE1\u606F");
		buttonSearchUser.addActionListener(new buttonSearchUser());
		buttonSearchUser.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		buttonSearchUser.setBounds(765, 374, 148, 27);
		contentPane.add(buttonSearchUser);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 347, 678, 280);
		contentPane.add(scrollPane_1);
		background = new JLabel(bgp);// 将背景图放在标签里。
		contentPane.add(background, new Integer(Integer.MIN_VALUE));// 将背景标签添加到login的LayeredPane面板里。
		background.setBounds(0, 0, this.getWidth(), this.getHeight());// 设置背景标签的位置

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

	// 刷新表格数据
	public void showData(int searchFlag, String searchText) {
		Book book = new Book();
		switch (searchFlag) {
		case 0:// 显示全部书籍
			bookDtw = new BookDAO().selectBook("all", null);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		case 1:// 书籍名字
			book.setName(searchText);
			bookDtw = new BookDAO().selectBook("name", book);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		case 2:// 书籍类型
			book.setType(searchText);
			bookDtw = new BookDAO().selectBook("type", book);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		case 3:// 书籍作者
			book.setAuthor(searchText);
			bookDtw = new BookDAO().selectBook("author", book);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		case 10:// 用户信息
			bookDtw = new UserDAO().selectUser("all", null);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		}
	}

	// 显示窗口
	public void showUI(AdminUI frame) {
		this.admin = frame;
		frame.setVisible(true);
		reSizeframeq(frame);
	}

	// 圆角化窗口
	public static void reSizeframeq(AdminUI frame) {
		// 下面圆角边框代码需要放在实例化以后不然会空指针
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, frame.getWidth(), frame.getHeight(), 30, 30);// 空你妈逼的指针？

		// 圆角化
		frame.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				frame.setShape(shape);
			}
		});
	}

	// 添加按钮可视化
	class buttonAddSetVisible implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// 先清空输入框
			text[0].setText("");
			text[1].setText("");
			text[2].setText("");
			text[3].setText("");
			text[4].setText("0");
			text[5].setText("0");

			// 让添加栏可视化
			addShow(true);

			// 让其他栏不可视
			changeShow(false);
			changeSureShow(false);
			deleteShow(false);
			searchShow(false);

			// 显示图书
			showData(0, searchText);
			searchFlag = 0;
		}
	}

	// 添加按钮
	class buttonAdd implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			BookDAO bd = new BookDAO();
			Book book = new Book();
			book.setType(text[0].getText());
			book.setName(text[1].getText());
			book.setPress(text[2].getText());
			book.setAuthor(text[3].getText());
			book.setPrice(Float.valueOf(text[4].getText()));
			book.setCount(Integer.valueOf(text[5].getText()));

			if (text[0].getText().length() == 0 || text[1].getText().length() == 0 || text[2].getText().length() == 0
					|| text[3].getText().length() == 0 || text[4].getText().length() == 0
					|| text[5].getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "请输入完整图书信息！", "提示", JOptionPane.YES_NO_OPTION);
			}

			else {
				if (bd.add(book)) {
					JOptionPane.showConfirmDialog(null, "添加成功！", "提示", JOptionPane.YES_NO_OPTION);
					showData(searchFlag, searchText);

					// 并且清空输入框
					text[0].setText("");
					text[1].setText("");
					text[2].setText("");
					text[3].setText("");
					text[4].setText("0");
					text[5].setText("0");

					// 让添加栏隐藏
					addShow(false);

					buttonSure.setVisible(false);
					textId[1].setVisible(false);
					lblInputId.setVisible(false);
				}
			}

		}
	}

	// 删除按钮
	class ButtonDelete implements ActionListener

	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 创建一个暂时的书籍存储要删除的数据
			Book book = new Book();

			// 将删除的操作封装
			BookDAO bd = new BookDAO();

			if (textId[0].getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "请输入图书ID！", "提示", JOptionPane.YES_NO_OPTION);
			} else {
				book.setId(Integer.valueOf(textId[0].getText()));
				if (bd.delete(book)) {

					// 提示删除结果
					JOptionPane.showConfirmDialog(null, "删除成功", "提示", JOptionPane.YES_NO_OPTION);

					// 再次加载数据以更新界面
					showData(searchFlag, searchText);

					// 并且清空输入框
					textId[0].setText("");

					// 让删除栏隐藏
					deleteShow(false);
				} else {
					JOptionPane.showConfirmDialog(null, "删除失败", "提示", JOptionPane.YES_NO_OPTION);
				}
			}

		}
	}

	// 删除按钮可视化
	class ButtonDeleteSetVisible implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// 让删除栏可视化
			deleteShow(true);

			// 让其他栏不可见
			addShow(false);
			changeShow(false);
			changeSureShow(false);
			searchShow(false);

			// 显示图书
			showData(0, searchText);
			searchFlag = 0;
		}
	}

	// 修改按钮可视化
	class buttonChangeSetVisible implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// 修改栏可视化

			changeSureShow(true);

			// 让其他栏不可视
			deleteShow(false);
			addShow(false);
			changeShow(false);
			searchShow(false);
			textId[1].setVisible(true);
			lblInputId.setVisible(true);

			// 显示图书
			showData(0, searchText);
			searchFlag = 0;
		}

	}

	// 修改前的确认ID按钮
	class buttonSure implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// 创建一个暂时的书籍存储用户输入的id用于在数据库中查询该书籍信息
			if (textId[1].getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "请输入图书ID！", "提示", JOptionPane.YES_NO_OPTION);
			} else {
				Book book = new Book();
				book.setId(Integer.valueOf(textId[1].getText()));

				// list是存储book对象的数组，此处根据id查询匹配的书籍，list.get(0)即为要修改的书籍
				List<Book> list = new BookDAO().getAll("id", book);

				// 将书籍信息显示在指定文本框内
				lblInputID.setText(String.valueOf(list.get(0).getId()));
				text[0].setText(list.get(0).getType());
				text[1].setText(list.get(0).getName());
				text[2].setText(list.get(0).getPress());
				text[3].setText(list.get(0).getAuthor());
				text[4].setText(String.valueOf(list.get(0).getPrice()));
				text[5].setText(String.valueOf(list.get(0).getCount()));

				textId[1].setText("");

				// 修改前的确认ID栏不可视化
				changeSureShow(false);

				// 修改栏可视化
				changeShow(true);
				for (int i = 0; i < 6; i++) {
					label[i].setVisible(true);
				}

				for (int i = 0; i < 6; i++) {
					text[i].setVisible(true);
				}

				lblId.setVisible(true);
				lblInputID.setVisible(true);
			}
		}
	}

	// 修改按钮
	class buttonChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// 存储用户修改的信息
			if (lblInputID.getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "请输入完整图书信息！", "提示", JOptionPane.YES_NO_OPTION);
			} else {
				Book book = new Book();
				book.setId(Integer.valueOf(lblInputID.getText()));
				book.setType(text[0].getText());
				book.setName(text[1].getText());
				book.setPress(text[2].getText());
				book.setAuthor(text[3].getText());
				book.setPrice(Float.valueOf(text[4].getText()));
				book.setCount(Integer.valueOf(text[5].getText()));

				// 将修改的操作封装，修改成功返回true
				BookDAO bookDao = new BookDAO();
				boolean changeSuccess = bookDao.change(book);

				// 再次加载数据以更新界面
				showData(searchFlag, searchText);
				searchFlag = 0;

				for (int i = 0; i < 4; i++)
					text[i].setText("");

				text[4].setText("0");
				text[5].setText("0");
				lblInputID.setText("");
			}

		}
	}

	// 查询按钮可视化
	class buttonSearchSetVisible implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// 查询栏可视化
			searchShow(true);

			// 让其他栏不可视化
			addShow(false);

			changeShow(false);
			changeSureShow(false);
			deleteShow(false);

			// 显示图书
			showData(0, searchText);
			searchFlag = 0;
		}

	}

	// 下拉框监听事件
	class comboBoxSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String str = (String) comboBoxSearch.getSelectedItem();

			switch (str) {
			case "查询所有图书":
				searchFlag = 0;
				bookDtw = new BookDAO().selectBook("all", null);
				setTable();
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
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			searchText = textSearch.getText();
			Book book = new Book();

			switch (searchFlag) {
			case 1:
				book.setName(textSearch.getText());
				bookDtw = new BookDAO().selectBook("name", book);
				setTable();
				break;
			case 2:
				book.setType(textSearch.getText());
				bookDtw = new BookDAO().selectBook("type", book);
				setTable();
				break;
			case 3:
				book.setAuthor(textSearch.getText());
				bookDtw = new BookDAO().selectBook("author", book);
				setTable();
				break;
			}
		}
	}

	// 查询用户按钮
	class buttonSearchUser implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			searchFlag = 10;
			showData(searchFlag, searchText);
		}

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
			admin.setExtendedState(JFrame.ICONIFIED);
		}
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
					AdminUI.this.setLocation(xx, yy);
				}
			}
		});
	}

	// 查询栏可视化
	public void searchShow(boolean b) {
		// TODO Auto-generated method stub
		labelSelectSearch.setVisible(b);
		comboBoxSearch.setVisible(b);
		textSearch.setVisible(b);
		lblSearch.setVisible(b);
		btnSearch.setVisible(b);
	}

	// 修改栏可视化
	public void changeShow(boolean b) {
		// TODO Auto-generated method stub
		lblId.setVisible(b);
		lblInputID.setVisible(b);
		buttonChange.setVisible(b);
	}

	// 修改前的确认ID栏可视化
	public void changeSureShow(boolean b) {
		// TODO Auto-generated method stub
		lblInputId.setVisible(b);
		buttonSure.setVisible(b);
		textId[1].setVisible(b);
	}

	// 删除栏可视化
	public void deleteShow(boolean b) {
		// TODO Auto-generated method stub
		ButtonDelete.setVisible(b);
		textId[0].setVisible(b);
		lblDelete.setVisible(b);
	}

	// 添加栏可视化
	public void addShow(boolean b) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 6; i++) {
			label[i].setVisible(b);
		}

		for (int i = 0; i < 6; i++) {
			text[i].setVisible(b);
		}

		buttonAdd.setVisible(b);

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
				admin.setVisible(false);
			}
		}

	}

	// 设置表格列与行
	public void setTable() {
		bookTable.setModel(bookDtw);
		// 设置行高
		bookTable.setRowHeight(20);
		// 设置列宽
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(160);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(180);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(180);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(95);
		bookTable.getColumnModel().getColumn(6).setPreferredWidth(95);
	}

	// 表格双击修改（不知道为什么只能放在最下边，否则会影响其他内部类）
	class tableClicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (bookTable.getSelectedRow() != -1 && bookTable.getSelectedRow() < bookTable.getRowCount())
			// 选中有图书的行才会有提示
			{
				if (e.getClickCount() == 2 && searchFlag != 10) {// 点击几次
					int i = JOptionPane.showConfirmDialog(null, "要修改这本书吗？", "提示", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						// 修改栏可视化
						lblInputID.setVisible(true);
						lblId.setVisible(true);
						for (int a = 0; a < 6; a++) {
							label[a].setVisible(true);
							text[a].setVisible(true);
						}
						buttonChange.setVisible(true);

						// 让其他栏不可视化
						buttonAdd.setVisible(false);

						buttonSure.setVisible(false);
						textId[1].setVisible(false);

						lblInputId.setVisible(false);

						ButtonDelete.setVisible(false);
						textId[0].setVisible(false);
						lblDelete.setVisible(false);

						labelSelectSearch.setVisible(false);
						comboBoxSearch.setVisible(false);

						textSearch.setVisible(false);
						lblSearch.setVisible(false);
						btnSearch.setVisible(false);

						// 将书籍信息显示在指定文本框内
						lblInputID.setText(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 0)));
						text[0].setText(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 1)));
						text[1].setText(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 2)));
						text[2].setText(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 3)));
						text[3].setText(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 4)));
						text[4].setText(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 5)));
						text[5].setText(String.valueOf(bookTable.getValueAt(bookTable.getSelectedRow(), 6)));
					}
				}
			}
		}
	}

}
