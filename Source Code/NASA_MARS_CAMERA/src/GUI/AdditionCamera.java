package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdditionCamera {

	private JFrame frame;
	private JTextField txtAdditionCamera;
	private JTextField txtHttp;
	public static int cameraNum = 4;

	/**
	 * Launch the application.
	 */
	
			public void run() {
				try {
					AdditionCamera window = new AdditionCamera();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
	/**
	 * Create the application.
	 */
	public AdditionCamera() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 239);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Camera");
		lblNewLabel.setBounds(176, 6, 117, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 53, 438, 43);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Display Name:");
		lblNewLabel_1.setBounds(6, 6, 91, 31);
		panel.add(lblNewLabel_1);
		
		txtAdditionCamera = new JTextField();
		txtAdditionCamera.setText("Addition Camera");
		txtAdditionCamera.setBounds(112, 7, 320, 28);
		panel.add(txtAdditionCamera);
		txtAdditionCamera.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 120, 438, 43);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCameraUrl = new JLabel("Camera URL:");
		lblCameraUrl.setBounds(6, 6, 91, 31);
		panel_1.add(lblCameraUrl);
		
		txtHttp = new JTextField();
		txtHttp.setText("http://nasamars.com/cameras");
		txtHttp.setColumns(10);
		txtHttp.setBounds(112, 7, 320, 28);
		panel_1.add(txtHttp);
		
		JButton btnAddCamera = new JButton("Add Camera");
		btnAddCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (++cameraNum <= 10) {
					new MainInter(300, 300, cameraNum);
				}
				else {
					JOptionPane.showMessageDialog(null,
							"Maximum Camera Number is 10 ",
							"Too Much Cameras", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddCamera.setBounds(161, 175, 117, 29);
		frame.getContentPane().add(btnAddCamera);
	}
}
