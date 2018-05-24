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

	// ����Ա����
	private static AdminUI admin;
	private JPanel contentPane;
	private String searchText;
	private int searchFlag = 0;

	// ����ڵ�ǰ���������
	private int xOld, yOld;

	// �����Ϣ
	private JTable bookTable;
	private DefaultTableModel bookDtw;
	private DefaultTableCellRenderer tableCell;

	// ��С����ť
	private JButton buttonMini;
	// ע����ť
	private JButton buttonOff;
	// �˳���ť
	private JButton btnExit;

	// ����ͼƬ
	private ImageIcon bgp;
	private JLabel background;

	// ���ı���
	private JTextField[] text = new JTextField[6];
	private JTextField[] textId = new JTextField[2];
	private JTextField textSearch;

	// ����ǩ
	private JLabel[] label = new JLabel[6];
	private JLabel lblInputID;
	private JLabel lblId;
	private JLabel labelSelectSearch;
	private JLabel lblDelete;
	private JLabel lblInputId;
	private JLabel lblSearch;
	private JLabel lblNewLabel;

	// ����ť����ѯͼ����Ϣ�����ͼ����Ϣ��ɾ��ͼ����Ϣ���޸�ͼ����Ϣ����ѯ�û���Ϣ��
	private JButton buttonAdd;
	private JButton ButtonDelete;
	private JButton buttonAddSetVisible;
	private JButton ButtonDeleteSetVisible;
	private JButton buttonChange;
	private JButton buttonChangeSetVisible;
	private JButton buttonSure;
	private JButton buttonSearchSetVisible;
	private JButton btnSearch;

	// ������
	private JComboBox<String> comboBoxSearch;

	// ������
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
		setTitle("��ӭ�㣺");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 956, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		contentPane.setLayout(null);

		// ȥ������߿�
		setUndecorated(true);
		// ���ڲ��ɵ�
		setResizable(false);
		// ���϶�����
		dragUI();

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(38, 78, 678, 244);
		contentPane.add(scrollPane);

		bookTable = new JTable();
		// �����Ӧ�¼�
		bookTable.addMouseListener(new tableClicked());

		bookTable.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		bookTable.getTableHeader().setFont(new Font("΢���ź�", Font.PLAIN, 18));
		scrollPane.setViewportView(bookTable);
		bookTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		bookTable.setBounds(53, 63, 686, 272);

		bookDtw = new BookDAO().selectBook("all", null);
		bookTable.setModel(bookDtw);

		// ���Ԫ������ʾ
		tableCell = new DefaultTableCellRenderer();
		tableCell.setHorizontalAlignment(JLabel.CENTER);
		bookTable.setDefaultRenderer(Object.class, tableCell);

		// ���ñ�������еĿ�
		setTable();

		contentPane.add(scrollPane);

		label[0] = new JLabel("\u7C7B  \u578B\uFF1A");
		label[1] = new JLabel("\u540D  \u79F0\uFF1A");
		label[2] = new JLabel("\u51FA\u7248\u793E\uFF1A");
		label[3] = new JLabel("\u4F5C  \u8005\uFF1A");
		label[4] = new JLabel("\u4EF7  \u683C\uFF1A");
		label[5] = new JLabel("��  ����");

		for (int i = 0; i < 6; i++) {
			text[i] = new JTextField();
			text[i].setBounds(139, 406 + i * 30, 100, 25);
			text[i].setVisible(false);
			text[i].setFont(new Font("΢���ź�", Font.PLAIN, 18));
			contentPane.add(text[i]);
			text[i].setColumns(10);

			label[i].setBounds(67, 406 + i * 30, 72, 18);
			label[i].setVisible(false);
			label[i].setFont(new Font("΢���ź�", Font.PLAIN, 18));
			contentPane.add(label[i]);
		}

		buttonAdd = new JButton("\u6DFB\u52A0");
		buttonAdd.setFont(new Font("����", Font.PLAIN, 18));
		buttonAdd.addActionListener(new buttonAdd());
		buttonAdd.setBounds(563, 564, 113, 27);
		buttonAdd.setVisible(false);
		contentPane.add(buttonAdd);

		buttonAddSetVisible = new JButton("\u6DFB\u52A0\u56FE\u4E66\u4FE1\u606F");
		buttonAddSetVisible.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		buttonAddSetVisible.addActionListener(new buttonAddSetVisible());
		buttonAddSetVisible.setBounds(765, 148, 148, 27);
		contentPane.add(buttonAddSetVisible);

		ButtonDeleteSetVisible = new JButton("\u5220\u9664\u56FE\u4E66\u4FE1\u606F");
		ButtonDeleteSetVisible.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		ButtonDeleteSetVisible.addActionListener(new ButtonDeleteSetVisible());
		ButtonDeleteSetVisible.setBounds(765, 221, 148, 27);
		contentPane.add(ButtonDeleteSetVisible);

		ButtonDelete = new JButton("\u5220\u9664");
		ButtonDelete.setFont(new Font("����", Font.PLAIN, 18));
		ButtonDelete.addActionListener(new ButtonDelete());
		ButtonDelete.setBounds(563, 564, 113, 27);
		ButtonDelete.setVisible(false);
		contentPane.add(ButtonDelete);

		lblDelete = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u5220\u9664\u7684\u56FE\u4E66ID\uFF1A");
		lblDelete.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblDelete.setBounds(110, 398, 219, 18);
		lblDelete.setVisible(false);
		contentPane.add(lblDelete);

		textId[0] = new JTextField();
		textId[0].setBounds(358, 395, 148, 24);
		textId[0].setVisible(false);
		textId[0].setFont(new Font("����", Font.PLAIN, 18));
		contentPane.add(textId[0]);
		textId[0].setColumns(10);

		buttonChangeSetVisible = new JButton("\u4FEE\u6539\u56FE\u4E66\u4FE1\u606F");
		buttonChangeSetVisible.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		buttonChangeSetVisible.addActionListener(new buttonChangeSetVisible());
		buttonChangeSetVisible.setBounds(765, 295, 148, 27);
		contentPane.add(buttonChangeSetVisible);

		buttonSure = new JButton("\u786E\u8BA4");
		buttonSure.setFont(new Font("����", Font.PLAIN, 18));
		buttonSure.setBounds(563, 564, 113, 27);
		buttonSure.addActionListener(new buttonSure());
		buttonSure.setVisible(false);
		contentPane.add(buttonSure);

		lblInputId = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u4FEE\u6539\u7684\u56FE\u4E66ID\uFF1A");
		lblInputId.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblInputId.setBounds(110, 398, 219, 18);
		lblInputId.setVisible(false);
		contentPane.add(lblInputId);

		textId[1] = new JTextField();
		textId[1].setBounds(358, 395, 148, 24);
		textId[1].setVisible(false);
		textId[1].setFont(new Font("����", Font.PLAIN, 18));
		contentPane.add(textId[1]);
		textId[1].setColumns(10);

		lblId = new JLabel("    ID  \uFF1A");
		lblId.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblId.setBounds(67, 376, 72, 18);
		lblId.setVisible(false);
		contentPane.add(lblId);

		lblInputID = new JLabel("New label");
		lblInputID.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblInputID.setBounds(138, 373, 100, 25);// 139, 406+i*30, 100, 25
		lblInputID.setVisible(false);
		contentPane.add(lblInputID);

		buttonChange = new JButton("\u786E\u8BA4\u4FEE\u6539");
		buttonChange.setFont(new Font("����", Font.PLAIN, 18));
		buttonChange.addActionListener(new buttonChange());
		buttonChange.setBounds(563, 564, 113, 27);
		buttonChange.setVisible(false);
		contentPane.add(buttonChange);

		buttonSearchSetVisible = new JButton("\u67E5\u8BE2\u56FE\u4E66\u4FE1\u606F");
		buttonSearchSetVisible.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		buttonSearchSetVisible.addActionListener(new buttonSearchSetVisible());
		buttonSearchSetVisible.setBounds(765, 80, 148, 27);
		contentPane.add(buttonSearchSetVisible);

		labelSelectSearch = new JLabel(
				"\u8BF7\u9009\u62E9\u4F60\u8981\u67E5\u8BE2\u7684\u56FE\u4E66\u6761\u4EF6\uFF1A");
		labelSelectSearch.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		labelSelectSearch.setBounds(110, 398, 234, 18);
		labelSelectSearch.setVisible(false);
		contentPane.add(labelSelectSearch);

		comboBoxSearch = new JComboBox<String>();
		comboBoxSearch.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		comboBoxSearch.setBounds(358, 395, 140, 24);
		comboBoxSearch.addItem("��ѯ����ͼ��");
		comboBoxSearch.addItem("��������ѯ");
		comboBoxSearch.addItem("�����Ͳ�ѯ");
		comboBoxSearch.addItem("�����߲�ѯ");
		comboBoxSearch.addActionListener(new comboBoxSearch());// �����������ѯ��ť�����
		comboBoxSearch.setVisible(false);
		contentPane.add(comboBoxSearch);

		lblSearch = new JLabel("\u8BF7\u8F93\u5165\u4F60\u8981\u67E5\u8BE2\u7684\u56FE\u4E66\u5185\u5BB9\uFF1A");
		lblSearch.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblSearch.setBounds(110, 446, 234, 18);
		lblSearch.setVisible(false);
		contentPane.add(lblSearch);

		textSearch = new JTextField();
		textSearch.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textSearch.setBounds(358, 445, 221, 24);
		textSearch.setVisible(false);
		contentPane.add(textSearch);
		textSearch.setColumns(10);

		btnSearch = new JButton("\u67E5\u8BE2");
		btnSearch.setFont(new Font("����", Font.PLAIN, 18));
		btnSearch.setBounds(563, 564, 113, 27);
		btnSearch.addActionListener(new btnSearch());
		btnSearch.setVisible(false);
		contentPane.add(btnSearch);

		btnExit = new JButton();
		btnExit.addActionListener(new btnExit());
		btnExit.setIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//exit//�˳�.png"));
		btnExit.setRolloverIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//exit//�˳�2.png"));
		// ���ذ�ť�ķ���
		btnExit.setBorder(null);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		// ȥ����ť������Χ�Ľ����
		btnExit.setFocusPainted(false);
		btnExit.setBounds(892, 13, 50, 27);
		contentPane.add(btnExit);

		buttonMini = new JButton("");
		buttonMini.addActionListener(new buttonMini());
		buttonMini.setIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//mini//��С��.png"));
		buttonMini.setRolloverIcon(
				new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//mini//��С��2.png"));
		// ���ذ�ť�ķ���
		buttonMini.setBorder(null);
		buttonMini.setOpaque(false);
		buttonMini.setContentAreaFilled(false);
		// ȥ����ť������Χ�Ľ����
		buttonMini.setFocusPainted(false);
		buttonMini.setBounds(828, 13, 50, 27);
		contentPane.add(buttonMini);

		buttonOff = new JButton("");
		buttonOff.addActionListener(new buttonOff());
		buttonOff.setIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//off//ע��.png"));
		buttonOff.setPressedIcon(new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//button//off//ע��2.png"));
		// ���ذ�ť�ķ���
		buttonOff.setBorder(null);
		buttonOff.setOpaque(false);
		buttonOff.setContentAreaFilled(false);
		// ȥ����ť������Χ�Ľ����
		buttonOff.setFocusPainted(false);
		buttonOff.setBounds(742, 13, 72, 27);
		contentPane.add(buttonOff);

		lblNewLabel = new JLabel("��ӭ�㣺" + admin.getName() + "!");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(14, 7, 234, 27);
		contentPane.add(lblNewLabel);

		// ����ͼ
		bgp = new ImageIcon("F://JAVA//eclipse//workspace//Library//picture//UI//����Ա����2.jpg");// ���Ǳ���ͼƬ

		JButton buttonSearchUser = new JButton("\u67E5\u8BE2\u7528\u6237\u4FE1\u606F");
		buttonSearchUser.addActionListener(new buttonSearchUser());
		buttonSearchUser.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		buttonSearchUser.setBounds(765, 374, 148, 27);
		contentPane.add(buttonSearchUser);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 347, 678, 280);
		contentPane.add(scrollPane_1);
		background = new JLabel(bgp);// ������ͼ���ڱ�ǩ�
		contentPane.add(background, new Integer(Integer.MIN_VALUE));// ��������ǩ��ӵ�login��LayeredPane����
		background.setBounds(0, 0, this.getWidth(), this.getHeight());// ���ñ�����ǩ��λ��

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

	// ˢ�±������
	public void showData(int searchFlag, String searchText) {
		Book book = new Book();
		switch (searchFlag) {
		case 0:// ��ʾȫ���鼮
			bookDtw = new BookDAO().selectBook("all", null);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		case 1:// �鼮����
			book.setName(searchText);
			bookDtw = new BookDAO().selectBook("name", book);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		case 2:// �鼮����
			book.setType(searchText);
			bookDtw = new BookDAO().selectBook("type", book);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		case 3:// �鼮����
			book.setAuthor(searchText);
			bookDtw = new BookDAO().selectBook("author", book);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		case 10:// �û���Ϣ
			bookDtw = new UserDAO().selectUser("all", null);
			setTable();
			bookTable.setModel(bookDtw);
			break;
		}
	}

	// ��ʾ����
	public void showUI(AdminUI frame) {
		this.admin = frame;
		frame.setVisible(true);
		reSizeframeq(frame);
	}

	// Բ�ǻ�����
	public static void reSizeframeq(AdminUI frame) {
		// ����Բ�Ǳ߿������Ҫ����ʵ�����Ժ�Ȼ���ָ��
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, frame.getWidth(), frame.getHeight(), 30, 30);// ������Ƶ�ָ�룿

		// Բ�ǻ�
		frame.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				frame.setShape(shape);
			}
		});
	}

	// ��Ӱ�ť���ӻ�
	class buttonAddSetVisible implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// ����������
			text[0].setText("");
			text[1].setText("");
			text[2].setText("");
			text[3].setText("");
			text[4].setText("0");
			text[5].setText("0");

			// ����������ӻ�
			addShow(true);

			// ��������������
			changeShow(false);
			changeSureShow(false);
			deleteShow(false);
			searchShow(false);

			// ��ʾͼ��
			showData(0, searchText);
			searchFlag = 0;
		}
	}

	// ��Ӱ�ť
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
				JOptionPane.showConfirmDialog(null, "����������ͼ����Ϣ��", "��ʾ", JOptionPane.YES_NO_OPTION);
			}

			else {
				if (bd.add(book)) {
					JOptionPane.showConfirmDialog(null, "��ӳɹ���", "��ʾ", JOptionPane.YES_NO_OPTION);
					showData(searchFlag, searchText);

					// ������������
					text[0].setText("");
					text[1].setText("");
					text[2].setText("");
					text[3].setText("");
					text[4].setText("0");
					text[5].setText("0");

					// �����������
					addShow(false);

					buttonSure.setVisible(false);
					textId[1].setVisible(false);
					lblInputId.setVisible(false);
				}
			}

		}
	}

	// ɾ����ť
	class ButtonDelete implements ActionListener

	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// ����һ����ʱ���鼮�洢Ҫɾ��������
			Book book = new Book();

			// ��ɾ���Ĳ�����װ
			BookDAO bd = new BookDAO();

			if (textId[0].getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "������ͼ��ID��", "��ʾ", JOptionPane.YES_NO_OPTION);
			} else {
				book.setId(Integer.valueOf(textId[0].getText()));
				if (bd.delete(book)) {

					// ��ʾɾ�����
					JOptionPane.showConfirmDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.YES_NO_OPTION);

					// �ٴμ��������Ը��½���
					showData(searchFlag, searchText);

					// ������������
					textId[0].setText("");

					// ��ɾ��������
					deleteShow(false);
				} else {
					JOptionPane.showConfirmDialog(null, "ɾ��ʧ��", "��ʾ", JOptionPane.YES_NO_OPTION);
				}
			}

		}
	}

	// ɾ����ť���ӻ�
	class ButtonDeleteSetVisible implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// ��ɾ�������ӻ�
			deleteShow(true);

			// �����������ɼ�
			addShow(false);
			changeShow(false);
			changeSureShow(false);
			searchShow(false);

			// ��ʾͼ��
			showData(0, searchText);
			searchFlag = 0;
		}
	}

	// �޸İ�ť���ӻ�
	class buttonChangeSetVisible implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// �޸������ӻ�

			changeSureShow(true);

			// ��������������
			deleteShow(false);
			addShow(false);
			changeShow(false);
			searchShow(false);
			textId[1].setVisible(true);
			lblInputId.setVisible(true);

			// ��ʾͼ��
			showData(0, searchText);
			searchFlag = 0;
		}

	}

	// �޸�ǰ��ȷ��ID��ť
	class buttonSure implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// ����һ����ʱ���鼮�洢�û������id���������ݿ��в�ѯ���鼮��Ϣ
			if (textId[1].getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "������ͼ��ID��", "��ʾ", JOptionPane.YES_NO_OPTION);
			} else {
				Book book = new Book();
				book.setId(Integer.valueOf(textId[1].getText()));

				// list�Ǵ洢book��������飬�˴�����id��ѯƥ����鼮��list.get(0)��ΪҪ�޸ĵ��鼮
				List<Book> list = new BookDAO().getAll("id", book);

				// ���鼮��Ϣ��ʾ��ָ���ı�����
				lblInputID.setText(String.valueOf(list.get(0).getId()));
				text[0].setText(list.get(0).getType());
				text[1].setText(list.get(0).getName());
				text[2].setText(list.get(0).getPress());
				text[3].setText(list.get(0).getAuthor());
				text[4].setText(String.valueOf(list.get(0).getPrice()));
				text[5].setText(String.valueOf(list.get(0).getCount()));

				textId[1].setText("");

				// �޸�ǰ��ȷ��ID�������ӻ�
				changeSureShow(false);

				// �޸������ӻ�
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

	// �޸İ�ť
	class buttonChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// �洢�û��޸ĵ���Ϣ
			if (lblInputID.getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "����������ͼ����Ϣ��", "��ʾ", JOptionPane.YES_NO_OPTION);
			} else {
				Book book = new Book();
				book.setId(Integer.valueOf(lblInputID.getText()));
				book.setType(text[0].getText());
				book.setName(text[1].getText());
				book.setPress(text[2].getText());
				book.setAuthor(text[3].getText());
				book.setPrice(Float.valueOf(text[4].getText()));
				book.setCount(Integer.valueOf(text[5].getText()));

				// ���޸ĵĲ�����װ���޸ĳɹ�����true
				BookDAO bookDao = new BookDAO();
				boolean changeSuccess = bookDao.change(book);

				// �ٴμ��������Ը��½���
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

	// ��ѯ��ť���ӻ�
	class buttonSearchSetVisible implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// ��ѯ�����ӻ�
			searchShow(true);

			// �������������ӻ�
			addShow(false);

			changeShow(false);
			changeSureShow(false);
			deleteShow(false);

			// ��ʾͼ��
			showData(0, searchText);
			searchFlag = 0;
		}

	}

	// ����������¼�
	class comboBoxSearch implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String str = (String) comboBoxSearch.getSelectedItem();

			switch (str) {
			case "��ѯ����ͼ��":
				searchFlag = 0;
				bookDtw = new BookDAO().selectBook("all", null);
				setTable();
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

	// ��ѯ�û���ť
	class buttonSearchUser implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			searchFlag = 10;
			showData(searchFlag, searchText);
		}

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
			admin.setExtendedState(JFrame.ICONIFIED);
		}
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
					AdminUI.this.setLocation(xx, yy);
				}
			}
		});
	}

	// ��ѯ�����ӻ�
	public void searchShow(boolean b) {
		// TODO Auto-generated method stub
		labelSelectSearch.setVisible(b);
		comboBoxSearch.setVisible(b);
		textSearch.setVisible(b);
		lblSearch.setVisible(b);
		btnSearch.setVisible(b);
	}

	// �޸������ӻ�
	public void changeShow(boolean b) {
		// TODO Auto-generated method stub
		lblId.setVisible(b);
		lblInputID.setVisible(b);
		buttonChange.setVisible(b);
	}

	// �޸�ǰ��ȷ��ID�����ӻ�
	public void changeSureShow(boolean b) {
		// TODO Auto-generated method stub
		lblInputId.setVisible(b);
		buttonSure.setVisible(b);
		textId[1].setVisible(b);
	}

	// ɾ�������ӻ�
	public void deleteShow(boolean b) {
		// TODO Auto-generated method stub
		ButtonDelete.setVisible(b);
		textId[0].setVisible(b);
		lblDelete.setVisible(b);
	}

	// ��������ӻ�
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

	// ע����ť
	class buttonOff implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫע����", "��ʾ", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_NO_OPTION) {
				LoginUI login = new LoginUI();
				login.showUI(login);
				admin.setVisible(false);
			}
		}

	}

	// ���ñ��������
	public void setTable() {
		bookTable.setModel(bookDtw);
		// �����и�
		bookTable.setRowHeight(20);
		// �����п�
		bookTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(160);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(180);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(180);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(95);
		bookTable.getColumnModel().getColumn(6).setPreferredWidth(95);
	}

	// ���˫���޸ģ���֪��Ϊʲôֻ�ܷ������±ߣ������Ӱ�������ڲ��ࣩ
	class tableClicked extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (bookTable.getSelectedRow() != -1 && bookTable.getSelectedRow() < bookTable.getRowCount())
			// ѡ����ͼ����вŻ�����ʾ
			{
				if (e.getClickCount() == 2 && searchFlag != 10) {// �������
					int i = JOptionPane.showConfirmDialog(null, "Ҫ�޸��Ȿ����", "��ʾ", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						// �޸������ӻ�
						lblInputID.setVisible(true);
						lblId.setVisible(true);
						for (int a = 0; a < 6; a++) {
							label[a].setVisible(true);
							text[a].setVisible(true);
						}
						buttonChange.setVisible(true);

						// �������������ӻ�
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

						// ���鼮��Ϣ��ʾ��ָ���ı�����
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
