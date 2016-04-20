package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import JDBC.JDBCConnection;
import User.User;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class LoginGUI {
	private JFrame NasaLoginFrame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
				LoginGUI window = new LoginGUI();
				window.NasaLoginFrame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		NasaLoginFrame = new JFrame();
		NasaLoginFrame.setResizable(false);
		NasaLoginFrame.setTitle("NASA MARS SYSTEM");
		NasaLoginFrame.setBounds(300, 200, 600, 500);
		NasaLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		NasaLoginFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				String usr = username.getText().trim().toString();
				String pass = password.getText().toString();
				user.setUsername(usr);
				user.setPassword(pass);
//				System.out.println(usr+" "+pass);
				int flag = 0;
				String sqlstr = "select username,password from users";
				JDBCConnection.executeQuery(sqlstr);
//				System.out.println(JDBCConnection.next());
				while (JDBCConnection.next())
				{
					try {
						if(user.getUsername().equals(JDBCConnection.resultSet.getString(1)) && user.getPassword().equals(JDBCConnection.resultSet.getString(2)))
						{
							flag = 1;
							break;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(flag == 1)
				{
					new MainInter(0,0,1);
					new MainInter(600,0,2);
					new MainInter(0,600,3);
					new MainInter(600,600,4);
					new AdditionCamera().run();
					NasaLoginFrame.dispose();
				}
				else 
				{
					//System.out.println("fail");
					JOptionPane.showMessageDialog(null, "Invalid username and password", "Login Fail", JOptionPane.ERROR_MESSAGE);				
				}
			}
		});
		login.setFont(new Font("Arial", Font.PLAIN, 15));
		login.setBounds(130, 374, 117, 29);
		panel.add(login);
		JLabel lblNewLabel = new JLabel("NASA MARS HABITATS SYSTEM");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 34));
		lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(78, 48, 448, 37);
		panel.add(lblNewLabel);
		JLabel lblGroup = new JLabel("Group 8");
		lblGroup.setForeground(new Color(255, 0, 0));
		lblGroup.setFont(new Font("Arial", Font.PLAIN, 25));
		lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroup.setBounds(162, 91, 244, 37);
		panel.add(lblGroup);
		JLabel lblYixinYin = new JLabel("Yixin Yin");
		lblYixinYin.setForeground(Color.ORANGE);
		lblYixinYin.setFont(new Font("Apple Chancery", Font.BOLD, 14));
		lblYixinYin.setBounds(249, 130, 73, 29);
		panel.add(lblYixinYin);
		JLabel labelWeihuaWang = new JLabel("Weihua Wang");
		labelWeihuaWang.setForeground(Color.ORANGE);
		labelWeihuaWang.setFont(new Font("Apple Chancery", Font.PLAIN, 14));
		labelWeihuaWang.setBounds(249, 170, 91, 29);
		panel.add(labelWeihuaWang);
		JLabel labelQiLiu = new JLabel("Qi Liu");
		labelQiLiu.setForeground(Color.ORANGE);
		labelQiLiu.setFont(new Font("Apple Chancery", Font.BOLD | Font.ITALIC, 14));
		labelQiLiu.setBounds(249, 150, 73, 29);
		panel.add(labelQiLiu);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblUsername.setBounds(114, 237, 121, 37);
		panel.add(lblUsername);
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblPassword.setBounds(114, 301, 129, 36);
		panel.add(lblPassword);
		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					login.doClick();
				}
			}
		});
		username.setBounds(265, 235, 173, 37);
		panel.add(username);
		username.setColumns(10);
		password = new JPasswordField();
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					login.doClick();
				}
			}
		});
		password.setEchoChar('*');
		password.setBounds(265, 301, 173, 37);
		panel.add(password);
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
		reset.setFont(new Font("Arial", Font.PLAIN, 15));
		reset.setBounds(338, 374, 117, 29);
		panel.add(reset);
		int width = 600;
        int height = 500;
        ImageIcon mars = new ImageIcon("lib/mars.jpg");
        mars.setImage(mars.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		JLabel lblNewLabel_1 = new JLabel(mars);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 600, 478);
		panel.add(lblNewLabel_1);
		
		
		
		
		
	}
}
