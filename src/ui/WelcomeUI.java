package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.LoginUI;

public class WelcomeUI extends JFrame {

	private JPanel contentPane;
	private ImageIcon bgp;
	private JLabel background;
    private static WelcomeUI welcome;
	private int xOld, yOld;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome = new WelcomeUI();
					welcome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WelcomeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 1274, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// ȥ������߿�
		setUndecorated(true);
		// ���ڲ��ɵ�
		setResizable(false);
		// �϶�����
		dragUI();
				
		try {
			bgp = new ImageIcon("picture//ui//��ӭ����.jpg");// ���Ǳ���ͼƬ
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("����ʧ��");
		}
		
		contentPane.setLayout(null);
		background = new JLabel(bgp);// ������ͼ���ڱ�ǩ�
		
		background.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//	           int x = e.getX();
//	           int y = e.getY();
//	           System.out.println(x);
//	           System.out.println(y);
	           
	           if(e.getX() >= 510 && e.getX() <= 791 && e.getY() >= 673 && e.getY() <= 709){
	        	   LoginUI login = new LoginUI();
	   			   login.showUI(login);
	   			   welcome.setVisible(false);
	           }
	           
	           if(e.getX() >= 510 && e.getX() <= 791 && e.getY() >= 745 && e.getY() <= 784){
	        	   System.exit(0);
	           }
	           
	           if(e.getX() >= 510 && e.getX() <= 791 && e.getY() >= 818 && e.getY() <= 856){
	        	   JOptionPane.showConfirmDialog(null, "�������", "��ʾ", JOptionPane.YES_NO_OPTION);
	           }
			}
		});
		
		contentPane.add(background);// ��������ǩ��ӵ�login��LayeredPane����
		background.setBounds(-27, -324, 1331, 1360);// ���ñ�����ǩ��λ��
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
						WelcomeUI.this.setLocation(xx, yy);
					}
				}
			});
		}

}
