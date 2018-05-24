package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import item.Book;
import item.User;
import javax.swing.JTextArea;

import javax.swing.JScrollPane;

public class ExplainUI extends JFrame {

	private JPanel contentPane;
	private static User user;
	private int xOld, yOld;
	private JButton btnExit;
	private JButton buttonMini;
	private JButton buttonOff;
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneUser;
	private JScrollPane scrollPane2;
	private ImageIcon bgp;
	private ImageIcon bookpicture;
	private JLabel lblbgp;
	private static ExplainUI ExplainUI;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private Book book = new Book();
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel label_10;
	private JLabel label_11;
	private JLabel lblNewLabel_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExplainUI = new ExplainUI(user);
					reSize(ExplainUI);
					ExplainUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExplainUI(User user) {
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 956, 667);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnExit = new JButton("");
		btnExit.setBounds(905, 4, 41, 27);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExplainUI.setVisible(false);
			}
		});
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
		setTitle("haha ");
		// ȥ������߿�
		setUndecorated(true);
		// ���ڲ��ɵ�
		setResizable(false);
		// ���϶�����
		dragUI();
		buttonMini = new JButton("");
		buttonMini.setBounds(850, 4, 41, 27);
		buttonMini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExplainUI.setExtendedState(JFrame.ICONIFIED);
			}
		});

		buttonMini.setIcon(new ImageIcon("picture//button//mini//��С��.png"));
		buttonMini.setRolloverIcon(new ImageIcon("picture//button//mini//��С��2.png"));
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
		// ��С����ť

		JTextArea txtExplain = new JTextArea();
		txtExplain.setFont(new Font("����", Font.PLAIN, 18));
		txtExplain.setLineWrap(true);
		txtExplain.setWrapStyleWord(true);
		scrollPane = new JScrollPane(txtExplain);
		// ����Ӧ����
		txtExplain.setWrapStyleWord(true);
		// �Զ�����
		txtExplain.setLineWrap(true);

		// ���ı����ܻ�ý��㣬�������޸�
		txtExplain.setFocusable(false);
		scrollPane.setBounds(63, 60, 400, 510);
		txtExplain.setBounds(0, 0, 400, 510);
		txtExplain.setBackground(Color.WHITE);
		scrollPane.add(txtExplain);
		scrollPane.setViewportView(txtExplain);

		book = UserUI.passbookname();
		txtExplain.append(book.getAuthor() + book.getName());

		txtExplain.append(txt2String("picture//UI//" + book.getName() + ".txt"));
		contentPane.add(scrollPane);

		bookpicture = new ImageIcon("picture//UI//" + book.getName() + ".jpg");
		JLabel lblpicture = new JLabel(bookpicture);
		lblpicture.setBounds(555, 100, 110, 149);
		contentPane.add(lblpicture);

		lblNewLabel_1 = new JLabel("����:");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(680, 100, 55, 18);
		contentPane.add(lblNewLabel_1);

		lblNewLabel = new JLabel(book.getName());
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel.setBounds(750, 100, 100, 18);
		contentPane.add(lblNewLabel);

		lblNewLabel_2 = new JLabel("����:");
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(680, 150, 55, 18);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel(book.getAuthor());
		lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(750, 150, 150, 18);
		contentPane.add(lblNewLabel_3);

		JButton btnborrow = new JButton("\u501F\u4E66");
		btnborrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book = UserUI.borrow(book);
				UserUI.passframe().setVisible(true);
				UserUI.bookTable.setValueAt(book.getCount(), UserUI.bookTable.getSelectedRow(), 6);
				// ˢ���û�����
				UserUI.showUser();
				ExplainUI.setVisible(false);
			}
		});
		btnborrow.setBounds(630, 550, 113, 27);
		contentPane.add(btnborrow);

		label = new JLabel("\u51FA\u7248\u793E\uFF1A");
		label.setFont(new Font("����", Font.PLAIN, 18));
		label.setBounds(680, 200, 150, 18);
		contentPane.add(label);

		label_1 = new JLabel(book.getPress());
		label_1.setFont(new Font("����", Font.PLAIN, 18));
		label_1.setBounds(770, 200, 150, 18);
		contentPane.add(label_1);

		label_8 = new JLabel("\u79CD\u7C7B\uFF1A");
		label_8.setFont(new Font("����", Font.PLAIN, 18));
		label_8.setBounds(680, 250, 150, 18);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel(book.getType());
		label_9.setFont(new Font("����", Font.PLAIN, 18));
		label_9.setBounds(750, 250, 150, 18);
		contentPane.add(label_9);

		label_2 = new JLabel("\u8DA3\u5473\u6307\u6570\uFF1A");
		label_2.setFont(new Font("����", Font.PLAIN, 18));
		label_2.setBounds(680, 400, 150, 18);
		contentPane.add(label_2);

		label_3 = new JLabel("\u4E09\u661F");
		label_3.setFont(new Font("����", Font.PLAIN, 18));
		label_3.setBounds(800, 400, 150, 18);
		contentPane.add(label_3);

		label_4 = new JLabel("\u6587\u5B66\u6307\u6570\uFF1A");
		label_4.setFont(new Font("����", Font.PLAIN, 18));
		label_4.setBounds(680, 440, 150, 18);
		contentPane.add(label_4);

		label_5 = new JLabel("\u56DB\u661F");
		label_5.setFont(new Font("����", Font.PLAIN, 18));
		label_5.setBounds(800, 440, 150, 18);
		contentPane.add(label_5);

		label_6 = new JLabel("\u5B9E\u7528\u6307\u6570\uFF1A");
		label_6.setFont(new Font("����", Font.PLAIN, 18));
		label_6.setBounds(680, 480, 100, 18);
		contentPane.add(label_6);

		label_7 = new JLabel("\u4E00\u661F");
		label_7.setFont(new Font("����", Font.PLAIN, 18));
		label_7.setBounds(800, 480, 100, 18);
		contentPane.add(label_7);

		label_10 = new JLabel("\u7EFC\u5408\u6307\u6570\uFF1A");
		label_10.setFont(new Font("����", Font.PLAIN, 18));
		label_10.setBounds(680, 520, 100, 18);
		contentPane.add(label_10);

		label_11 = new JLabel("\u4E09\u661F");
		label_11.setFont(new Font("����", Font.PLAIN, 18));
		label_11.setBounds(800, 520, 100, 18);
		contentPane.add(label_11);

		JButton btnreturn = new JButton("\u8FD4\u56DE");
		btnreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUI.passframe().setVisible(true);
				ExplainUI.setVisible(false);
			}
		});
		btnreturn.setBounds(780, 550, 113, 27);
		contentPane.add(btnreturn);

		scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(515, 52, 396, 288);
		contentPane.add(scrollPaneUser);

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(515, 365, 396, 253);
		contentPane.add(scrollPane2);

		bgp = new ImageIcon("picture//UI//�鿴.jpg");
		JLabel labelWelcome = new JLabel("");
		labelWelcome.setBounds(14, 4, 245, 27);
		labelWelcome.setForeground(Color.WHITE);
		labelWelcome.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		labelWelcome.setText("��ӭ�㣺" + UserUI.passname() + "!");
		contentPane.add(labelWelcome);
		lblbgp = new JLabel(bgp);
		lblbgp.setBounds(0, 0, 956, 667);
		contentPane.add(lblbgp);

	}

	public static void reSize(ExplainUI ExplainUI) {
		// ����Բ�Ǳ߿������Ҫ����ʵ�����Ժ�Ȼ���ָ��
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, ExplainUI.getWidth(), ExplainUI.getHeight(), 30, 30);// ������Ƶ�ָ�룿

		// Բ�ǻ�
		ExplainUI.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				ExplainUI.setShape(shape);
			}
		});
	}

	public void showUI(ExplainUI ExplainUI) {
		this.ExplainUI = ExplainUI;
		this.ExplainUI.setVisible(true);
		reSize(ExplainUI);
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
					ExplainUI.this.setLocation(xx, yy);
				}
			}
		});
	}

	public static String txt2String(String fileaddress) {
		File file = new File(fileaddress);
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));// ����һ��BufferedReader������ȡ�ļ�
			String s = null;

			while ((s = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				result.append(System.lineSeparator() + s);

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
