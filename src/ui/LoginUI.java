package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDAO;
import item.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

import java.awt.Shape;

import javax.swing.JPasswordField;

public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField textName;
	static LoginUI login;
	private int xOld, yOld;
	private ImageIcon bgp;
	private JLabel background;
	private JButton btnExit;
	private JButton buttonRegister;
	private JButton buttonMini;
	private JButton loginButton;

	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login = new LoginUI();
					reSize(login);
					login.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 1274, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setOpaque(false);

		// 去除界面边框
		setUndecorated(true);
		// 窗口不可调
		setResizable(false);
		// 拖动窗口
		dragUI();

		textName = new JTextField();
		textName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textName.setBorder(null);
		textName.setBounds(347, 375, 207, 31);
		textName.setOpaque(false);
		contentPane.add(textName);
		textName.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		passwordField.setBorder(null);
		passwordField.setBounds(578, 375, 207, 31);
		passwordField.setOpaque(false);
		contentPane.add(passwordField);

		// 登录按钮
		loginButton = new JButton();
		// 按钮初始化图片
		loginButton.setIcon(new ImageIcon(
				"picture\\button\\login\\\u767B\u5F55\u6309\u94AE.jpg"));
		// 按钮按下图片
		loginButton.setPressedIcon(
				new ImageIcon("picture\\button\\login\\登录按钮3.jpg"));
		// 鼠标经过按钮图片
		loginButton.setRolloverIcon(
				new ImageIcon("picture\\button\\login\\登录按钮2.jpg"));
		// 设置无边框
		loginButton.setBorder(null);
		loginButton.addActionListener(new ButtonLogin());
		loginButton.setBounds(799, 375, 147, 31);
		// 让按钮除文本外隐形
		loginButton.setContentAreaFilled(false);
		contentPane.add(loginButton);

		// 注册按钮
		buttonRegister = new JButton("");
		// 按钮初始化图片
		buttonRegister.setIcon(new ImageIcon(
				"picture\\button\\register\\\u6CE8\u518C\u6309\u94AE.jpg"));
		// 按钮按下图片
		buttonRegister.setPressedIcon(
				new ImageIcon("picture\\button\\register\\注册按钮3.jpg"));
		// 鼠标经过按钮图片
		buttonRegister.setRolloverIcon(
				new ImageIcon("picture\\button\\register\\注册按钮2.jpg"));
		// 设置无边框
		buttonRegister.setBorder(null);
		buttonRegister.addActionListener(new ButtonRegister());
		buttonRegister.setBounds(960, 375, 147, 31);
		contentPane.add(buttonRegister);

		// 退出按钮
		btnExit = new JButton("");
		btnExit.addActionListener(new btnExit());
		btnExit.setIcon(new ImageIcon("picture//button//exit//退出.png"));
		btnExit.setRolloverIcon(new ImageIcon("picture//button//exit//退出2.png"));
		// 隐藏按钮的方法
		btnExit.setBorder(null);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		// 去掉按钮文字周围的焦点框
		btnExit.setFocusPainted(false);
		btnExit.setBounds(1233, 0, 41, 27);
		contentPane.add(btnExit);

		// 最小化按钮
		buttonMini = new JButton("");
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
		buttonMini.setBounds(1178, 0, 41, 27);
		buttonMini.setBorder(null);
		contentPane.add(buttonMini);

		// 背景图
		bgp = new ImageIcon("picture//UI//登录界面.jpg");// 这是背景图片
		background = new JLabel(bgp);// 将背景图放在标签里。
		contentPane.add(background, new Integer(Integer.MIN_VALUE));// 将背景标签添加到login的LayeredPane面板里。
		background.setBounds(-27, -324, 1331, 1360);// 设置背景标签的位置

	}

	//注册按钮
	class ButtonRegister implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			RegisterUI register = new RegisterUI();
			register.showUI(register);
			login.setVisible(false);
		}

	}

	//登录按钮
	class ButtonLogin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub
			User ui = new User();
			ui.setName(textName.getText());
			ui.setPass(passwordField.getText());
			UserDAO ud = new UserDAO();

			// 判断是否输入为空textFiled.getText().length() == 0
			if (textName.getText().length() == 0 || passwordField.getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "用户名或者密码不能为空！", "提示", JOptionPane.YES_NO_OPTION);
			} else {
				if (ud.login(ui, true)) {
					int i = JOptionPane.showConfirmDialog(null, "登陆成功", "提示", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						// 得到当前登录的用户信息
						List<User> list = new UserDAO().getAll("username", ui);
						ui = list.get(0);

						// 判断是否为管理员
						if (ui.getIsAdmin() == 1) {
							AdminUI adminUI = new AdminUI(ui);
							adminUI.showUI(adminUI);
							login.setVisible(false);
						}

						else {
							UserUI userUI = new UserUI(ui);
							userUI.showUI(userUI);
							login.setVisible(false);
						}

					}
				} else {
					JOptionPane.showConfirmDialog(null, "登录失败！用户名或者密码错误！", "提示", JOptionPane.YES_NO_OPTION);
				}
			}
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
			login.setExtendedState(JFrame.ICONIFIED);
		}
	}

	// 显示界面
	public void showUI(LoginUI login) {
		this.login = login;
		login.setVisible(true);
		reSize(login);

	}

	// 圆角化窗口
	public static void reSize(LoginUI login) {
		// 下面圆角边框代码需要放在实例化以后不然会空指针
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, login.getWidth(), login.getHeight(), 30, 30);

		// 圆角化
		login.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				login.setShape(shape);
			}
		});
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
					LoginUI.this.setLocation(xx, yy);
				}
			}
		});
	}

}
