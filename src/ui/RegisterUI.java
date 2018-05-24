package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Shape;

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

import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterUI extends JFrame {

	private JPanel contentPane;

	private static RegisterUI registerUI;

	private ImageIcon bgp;
	private JLabel background;

	private int xOld, yOld;

	private JButton ButtonRegister;
	private JButton ButtonReturn;

	private JButton btnExit;
	private JButton buttonMini;

	private JLabel[] label = new JLabel[3];
	private JTextField[] text = new JTextField[3];

	public RegisterUI getRegister() {
		return registerUI;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerUI = new RegisterUI();
					registerUI.setVisible(true);
					reSize(registerUI);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 1274, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 去除界面边框
		setUndecorated(true);
		// 窗口不可调
		setResizable(false);
		// 拖动窗口
		dragUI();
		// 109, 127, 89, 18
		label[0] = new JLabel("    用户名：");
		label[1] = new JLabel("     密  码：");
		label[2] = new JLabel("确认密码：");

		for (int i = 0; i < 3; i++) {
			label[i].setFont(new Font("微软雅黑", Font.PLAIN, 22));
			//设置颜色
			label[i].setForeground(Color.white);
			
			label[i].setBounds(350, 354 + 74 * i, 120, 30);
			contentPane.add(label[i]);

			text[i] = new JTextField();
			text[i].setFont(new Font("微软雅黑", Font.PLAIN, 18));
			text[i].setBounds(490, 354 + 74 * i, 269, 27);
			text[i].setOpaque(false);
			text[i].setBorder(null);
			contentPane.add(text[i]);
		}

		ButtonRegister = new JButton("");
		ButtonRegister.addActionListener(new ButtonRegister());
		ButtonRegister.setBounds(484, 561, 130, 37);
		// 按钮初始化图片
		ButtonRegister
				.setIcon(new ImageIcon("picture//button//register//注册按钮.jpg"));
		// 按钮按下图片
		ButtonRegister.setPressedIcon(
				new ImageIcon("picture//button//register//注册按钮3.jpg"));
		// 鼠标经过按钮图片
		ButtonRegister.setRolloverIcon(
				new ImageIcon("picture//button//register//注册按钮2.jpg"));
		// 设置无边框
		ButtonRegister.setBorder(null);
		ButtonRegister.setContentAreaFilled(false);
		contentPane.add(ButtonRegister);

		ButtonReturn = new JButton("");
		ButtonReturn.addActionListener(new ButtonReturn());
		// 按钮初始化图片
		ButtonReturn.setIcon(new ImageIcon("picture//button//return//返回按钮.jpg"));
		// 按钮按下图片
		ButtonReturn.setPressedIcon(
				new ImageIcon("picture//button//return//返回按钮3.jpg"));
		// 鼠标经过按钮图片
		ButtonReturn.setRolloverIcon(
				new ImageIcon("picture//button//return//返回按钮2.jpg"));
		// 设置无边框
		ButtonReturn.setBorder(null);
		ButtonReturn.setContentAreaFilled(false);
		ButtonReturn.setBounds(634, 561, 130, 37);
		contentPane.add(ButtonReturn);

		// 退出按钮
		btnExit = new JButton("X");
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
		buttonMini = new JButton("-");
		buttonMini.addActionListener(new buttonMini());
		buttonMini.setBounds(1178, 0, 41, 27);
		buttonMini.setIcon(new ImageIcon("picture//button//mini//最小化.png"));
		buttonMini.setRolloverIcon(new ImageIcon("picture//button//mini//最小化2.png"));
		// 隐藏按钮的方法
		buttonMini.setBorder(null);
		buttonMini.setOpaque(false);
		buttonMini.setContentAreaFilled(false);
		// 去掉按钮文字周围的焦点框
		buttonMini.setFocusPainted(false);
		contentPane.add(buttonMini);

		// 背景图
		bgp = new ImageIcon("picture//UI//注册界面.jpg");
		background = new JLabel(bgp);// 将背景图放在标签里。
		contentPane.add(background, new Integer(Integer.MIN_VALUE));// 将背景标签添加到login的LayeredPane面板里。
		background.setBounds(-27,-324,1331, 1360);// 设置背景标签的位置
	}

	class ButtonRegister implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			UserDAO ud = new UserDAO();
			User ui = new User();
			ui.setName(text[0].getText());
			ui.setPass(text[1].getText());

			if (text[0].getText().length() == 0 || text[1].getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "用户名或者密码不能为空！", "提示", JOptionPane.YES_NO_OPTION);
			} else {
				if (!(text[1].getText()).equals(text[2].getText())) {
					JOptionPane.showConfirmDialog(null, "两次密码不匹配！", "提示", JOptionPane.YES_NO_OPTION);
				} else {
					if (!ud.login(ui, false)) {
						if (ud.add(text[0].getText(), text[1].getText())) {
							int i = JOptionPane.showConfirmDialog(null, "注册成功！", "提示", JOptionPane.YES_NO_OPTION);
							if (i == JOptionPane.YES_OPTION || i == JOptionPane.NO_OPTION) {
								LoginUI login = new LoginUI();
								login.showUI(login);
								registerUI.setVisible(false);
							}
						}
					} else {
						JOptionPane.showConfirmDialog(null, "用户名重复！", "提示", JOptionPane.YES_NO_OPTION);
					}

					// 清空输入框
					text[0].setText("");
					text[1].setText("");
					text[2].setText("");
				}
			}
		}
	}

	class ButtonReturn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			LoginUI login = new LoginUI();
			login.showUI(login);
			registerUI.setVisible(false);
		}

	}

	// 显示窗口
	public void showUI(RegisterUI register) {
		this.registerUI = register;
		register.setVisible(true);
		reSize(registerUI);
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
					RegisterUI.this.setLocation(xx, yy);
				}
			}
		});
	}

	// 圆角化窗口
	public static void reSize(RegisterUI registerUI) {
		// 下面圆角边框代码需要放在实例化以后不然会空指针
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, registerUI.getWidth(), registerUI.getHeight(), 30, 30);

		// 圆角化
		registerUI.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				registerUI.setShape(shape);
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
			registerUI.setExtendedState(JFrame.ICONIFIED);
		}
	}
}
