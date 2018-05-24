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

		// ȥ������߿�
		setUndecorated(true);
		// ���ڲ��ɵ�
		setResizable(false);
		// �϶�����
		dragUI();

		textName = new JTextField();
		textName.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textName.setBorder(null);
		textName.setBounds(347, 375, 207, 31);
		textName.setOpaque(false);
		contentPane.add(textName);
		textName.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		passwordField.setBorder(null);
		passwordField.setBounds(578, 375, 207, 31);
		passwordField.setOpaque(false);
		contentPane.add(passwordField);

		// ��¼��ť
		loginButton = new JButton();
		// ��ť��ʼ��ͼƬ
		loginButton.setIcon(new ImageIcon(
				"picture\\button\\login\\\u767B\u5F55\u6309\u94AE.jpg"));
		// ��ť����ͼƬ
		loginButton.setPressedIcon(
				new ImageIcon("picture\\button\\login\\��¼��ť3.jpg"));
		// ��꾭����ťͼƬ
		loginButton.setRolloverIcon(
				new ImageIcon("picture\\button\\login\\��¼��ť2.jpg"));
		// �����ޱ߿�
		loginButton.setBorder(null);
		loginButton.addActionListener(new ButtonLogin());
		loginButton.setBounds(799, 375, 147, 31);
		// �ð�ť���ı�������
		loginButton.setContentAreaFilled(false);
		contentPane.add(loginButton);

		// ע�ᰴť
		buttonRegister = new JButton("");
		// ��ť��ʼ��ͼƬ
		buttonRegister.setIcon(new ImageIcon(
				"picture\\button\\register\\\u6CE8\u518C\u6309\u94AE.jpg"));
		// ��ť����ͼƬ
		buttonRegister.setPressedIcon(
				new ImageIcon("picture\\button\\register\\ע�ᰴť3.jpg"));
		// ��꾭����ťͼƬ
		buttonRegister.setRolloverIcon(
				new ImageIcon("picture\\button\\register\\ע�ᰴť2.jpg"));
		// �����ޱ߿�
		buttonRegister.setBorder(null);
		buttonRegister.addActionListener(new ButtonRegister());
		buttonRegister.setBounds(960, 375, 147, 31);
		contentPane.add(buttonRegister);

		// �˳���ť
		btnExit = new JButton("");
		btnExit.addActionListener(new btnExit());
		btnExit.setIcon(new ImageIcon("picture//button//exit//�˳�.png"));
		btnExit.setRolloverIcon(new ImageIcon("picture//button//exit//�˳�2.png"));
		// ���ذ�ť�ķ���
		btnExit.setBorder(null);
		btnExit.setOpaque(false);
		btnExit.setContentAreaFilled(false);
		// ȥ����ť������Χ�Ľ����
		btnExit.setFocusPainted(false);
		btnExit.setBounds(1233, 0, 41, 27);
		contentPane.add(btnExit);

		// ��С����ť
		buttonMini = new JButton("");
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
		buttonMini.setBounds(1178, 0, 41, 27);
		buttonMini.setBorder(null);
		contentPane.add(buttonMini);

		// ����ͼ
		bgp = new ImageIcon("picture//UI//��¼����.jpg");// ���Ǳ���ͼƬ
		background = new JLabel(bgp);// ������ͼ���ڱ�ǩ�
		contentPane.add(background, new Integer(Integer.MIN_VALUE));// ��������ǩ��ӵ�login��LayeredPane����
		background.setBounds(-27, -324, 1331, 1360);// ���ñ�����ǩ��λ��

	}

	//ע�ᰴť
	class ButtonRegister implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			RegisterUI register = new RegisterUI();
			register.showUI(register);
			login.setVisible(false);
		}

	}

	//��¼��ť
	class ButtonLogin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub
			User ui = new User();
			ui.setName(textName.getText());
			ui.setPass(passwordField.getText());
			UserDAO ud = new UserDAO();

			// �ж��Ƿ�����Ϊ��textFiled.getText().length() == 0
			if (textName.getText().length() == 0 || passwordField.getText().length() == 0) {
				JOptionPane.showConfirmDialog(null, "�û����������벻��Ϊ�գ�", "��ʾ", JOptionPane.YES_NO_OPTION);
			} else {
				if (ud.login(ui, true)) {
					int i = JOptionPane.showConfirmDialog(null, "��½�ɹ�", "��ʾ", JOptionPane.YES_NO_OPTION);
					if (i == JOptionPane.YES_OPTION) {
						// �õ���ǰ��¼���û���Ϣ
						List<User> list = new UserDAO().getAll("username", ui);
						ui = list.get(0);

						// �ж��Ƿ�Ϊ����Ա
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
					JOptionPane.showConfirmDialog(null, "��¼ʧ�ܣ��û��������������", "��ʾ", JOptionPane.YES_NO_OPTION);
				}
			}
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
			login.setExtendedState(JFrame.ICONIFIED);
		}
	}

	// ��ʾ����
	public void showUI(LoginUI login) {
		this.login = login;
		login.setVisible(true);
		reSize(login);

	}

	// Բ�ǻ�����
	public static void reSize(LoginUI login) {
		// ����Բ�Ǳ߿������Ҫ����ʵ�����Ժ�Ȼ���ָ��
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, login.getWidth(), login.getHeight(), 30, 30);

		// Բ�ǻ�
		login.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				login.setShape(shape);
			}
		});
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
					LoginUI.this.setLocation(xx, yy);
				}
			}
		});
	}

}
