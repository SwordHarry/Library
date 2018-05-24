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

		// ȥ������߿�
		setUndecorated(true);
		// ���ڲ��ɵ�
		setResizable(false);
		// �϶�����
		dragUI();
		// 109, 127, 89, 18
		label[0] = new JLabel("    �û�����");
		label[1] = new JLabel("     ��  �룺");
		label[2] = new JLabel("ȷ�����룺");

		for (int i = 0; i < 3; i++) {
			label[i].setFont(new Font("΢���ź�", Font.PLAIN, 22));
			//������ɫ
			label[i].setForeground(Color.white);
			
			label[i].setBounds(350, 354 + 74 * i, 120, 30);
			contentPane.add(label[i]);

			text[i] = new JTextField();
			text[i].setFont(new Font("΢���ź�", Font.PLAIN, 18));
			text[i].setBounds(490, 354 + 74 * i, 269, 27);
			text[i].setOpaque(false);
			text[i].setBorder(null);
			contentPane.add(text[i]);
		}

		ButtonRegister = new JButton("");
		ButtonRegister.addActionListener(new ButtonRegister());
		ButtonRegister.setBounds(484, 561, 130, 37);
		// ��ť��ʼ��ͼƬ
		ButtonRegister
				.setIcon(new ImageIcon("picture//button//register//ע�ᰴť.jpg"));
		// ��ť����ͼƬ
		ButtonRegister.setPressedIcon(
				new ImageIcon("picture//button//register//ע�ᰴť3.jpg"));
		// ��꾭����ťͼƬ
		ButtonRegister.setRolloverIcon(
				new ImageIcon("picture//button//register//ע�ᰴť2.jpg"));
		// �����ޱ߿�
		ButtonRegister.setBorder(null);
		ButtonRegister.setContentAreaFilled(false);
		contentPane.add(ButtonRegister);

		ButtonReturn = new JButton("");
		ButtonReturn.addActionListener(new ButtonReturn());
		// ��ť��ʼ��ͼƬ
		ButtonReturn.setIcon(new ImageIcon("picture//button//return//���ذ�ť.jpg"));
		// ��ť����ͼƬ
		ButtonReturn.setPressedIcon(
				new ImageIcon("picture//button//return//���ذ�ť3.jpg"));
		// ��꾭����ťͼƬ
		ButtonReturn.setRolloverIcon(
				new ImageIcon("picture//button//return//���ذ�ť2.jpg"));
		// �����ޱ߿�
		ButtonReturn.setBorder(null);
		ButtonReturn.setContentAreaFilled(false);
		ButtonReturn.setBounds(634, 561, 130, 37);
		contentPane.add(ButtonReturn);

		// �˳���ť
		btnExit = new JButton("X");
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
		buttonMini = new JButton("-");
		buttonMini.addActionListener(new buttonMini());
		buttonMini.setBounds(1178, 0, 41, 27);
		buttonMini.setIcon(new ImageIcon("picture//button//mini//��С��.png"));
		buttonMini.setRolloverIcon(new ImageIcon("picture//button//mini//��С��2.png"));
		// ���ذ�ť�ķ���
		buttonMini.setBorder(null);
		buttonMini.setOpaque(false);
		buttonMini.setContentAreaFilled(false);
		// ȥ����ť������Χ�Ľ����
		buttonMini.setFocusPainted(false);
		contentPane.add(buttonMini);

		// ����ͼ
		bgp = new ImageIcon("picture//UI//ע�����.jpg");
		background = new JLabel(bgp);// ������ͼ���ڱ�ǩ�
		contentPane.add(background, new Integer(Integer.MIN_VALUE));// ��������ǩ��ӵ�login��LayeredPane����
		background.setBounds(-27,-324,1331, 1360);// ���ñ�����ǩ��λ��
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
				JOptionPane.showConfirmDialog(null, "�û����������벻��Ϊ�գ�", "��ʾ", JOptionPane.YES_NO_OPTION);
			} else {
				if (!(text[1].getText()).equals(text[2].getText())) {
					JOptionPane.showConfirmDialog(null, "�������벻ƥ�䣡", "��ʾ", JOptionPane.YES_NO_OPTION);
				} else {
					if (!ud.login(ui, false)) {
						if (ud.add(text[0].getText(), text[1].getText())) {
							int i = JOptionPane.showConfirmDialog(null, "ע��ɹ���", "��ʾ", JOptionPane.YES_NO_OPTION);
							if (i == JOptionPane.YES_OPTION || i == JOptionPane.NO_OPTION) {
								LoginUI login = new LoginUI();
								login.showUI(login);
								registerUI.setVisible(false);
							}
						}
					} else {
						JOptionPane.showConfirmDialog(null, "�û����ظ���", "��ʾ", JOptionPane.YES_NO_OPTION);
					}

					// ��������
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

	// ��ʾ����
	public void showUI(RegisterUI register) {
		this.registerUI = register;
		register.setVisible(true);
		reSize(registerUI);
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
					RegisterUI.this.setLocation(xx, yy);
				}
			}
		});
	}

	// Բ�ǻ�����
	public static void reSize(RegisterUI registerUI) {
		// ����Բ�Ǳ߿������Ҫ����ʵ�����Ժ�Ȼ���ָ��
		final Shape shape = new RoundRectangle2D.Double(0d, 0d, registerUI.getWidth(), registerUI.getHeight(), 30, 30);

		// Բ�ǻ�
		registerUI.addComponentListener(new ComponentAdapter() {

			public void componentResized(ComponentEvent e) {
				registerUI.setShape(shape);
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
			registerUI.setExtendedState(JFrame.ICONIFIED);
		}
	}
}
