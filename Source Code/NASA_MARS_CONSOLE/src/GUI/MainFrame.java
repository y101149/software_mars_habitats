package GUI;
import GUI.MainFrame;
import MP3.MP3;
import Server.MultiThreadedServer;
import Server.WorkerRunnable;
import Server.staticInfo;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.TitledBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import Entity.belowHeatingArea;
import Entity.belowWellArea;
import Entity.commandSpace;
import Entity.equipStorageSpace;
import Entity.externalWall;
import Entity.labSpace;
import Entity.livingCell;

import javax.swing.border.LineBorder;
import javax.swing.Icon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
public class MainFrame {
	private JFrame displayFrame;
	//livingCell1 Info
	public static JLabel livingCell1_temperature;
	public static JLabel livingCell1_humidity;
	public static JLabel livingCell1_oxygen;
	public static JLabel livingCell1_pressure;
	public static JLabel livingCell1_smoke;
	public static JLabel livingCell1_gas;
	public static JLabel livingCell1_light;
	//livingCell2 Info
	public static JLabel livingCell2_temperature;
	public static JLabel livingCell2_humidity;
	public static JLabel livingCell2_oxygen;
	public static JLabel livingCell2_pressure;
	public static JLabel livingCell2_smoke;
	public static JLabel livingCell2_gas;
	public static JLabel livingCell2_light;
	//livingCell3 Info
	public static JLabel livingCell3_temperature;
	public static JLabel livingCell3_humidity;
	public static JLabel livingCell3_oxygen;
	public static JLabel livingCell3_pressure;
	public static JLabel livingCell3_smoke;
	public static JLabel livingCell3_gas;
	public static JLabel livingCell3_light;
	//livingCell4 Info
	public static JLabel livingCell4_temperature;
	public static JLabel livingCell4_humidity;
	public static JLabel livingCell4_oxygen;
	public static JLabel livingCell4_pressure;
	public static JLabel livingCell4_smoke;
	public static JLabel livingCell4_gas;
	public static JLabel livingCell4_light;
	//livingCell5 Info 
	public static JLabel livingCell5_temperature;
	public static JLabel livingCell5_humidity;
	public static JLabel livingCell5_oxygen;
	public static JLabel livingCell5_pressure;
	public static JLabel livingCell5_smoke;
	public static JLabel livingCell5_gas;
	public static JLabel livingCell5_light;
	//livingCell6 Info
	public static JLabel livingCell6_temperature;
	public static JLabel livingCell6_humidity;
	public static JLabel livingCell6_oxygen;
	public static JLabel livingCell6_pressure;
	public static JLabel livingCell6_smoke;
	public static JLabel livingCell6_gas;
	public static JLabel livingCell6_light;
	//livingCell7 Info
	public static JLabel livingCell7_temperature;
	public static JLabel livingCell7_humidity;
	public static JLabel livingCell7_oxygen;
	public static JLabel livingCell7_pressure;
	public static JLabel livingCell7_smoke;
	public static JLabel livingCell7_gas;
	public static JLabel livingCell7_light;
	//livingCell8 Info
	public static JLabel livingCell8_temperature;
	public static JLabel livingCell8_humidity;
	public static JLabel livingCell8_oxygen;
	public static JLabel livingCell8_pressure;
	public static JLabel livingCell8_smoke;
	public static JLabel livingCell8_gas;
	public static JLabel livingCell8_light;
	//livingCell 9 Info
	public static JLabel livingCell9_temperature;
	public static JLabel livingCell9_humidity;
	public static JLabel livingCell9_oxygen;
	public static JLabel livingCell9_pressure;
	public static JLabel livingCell9_smoke;
	public static JLabel livingCell9_gas;
	public static JLabel livingCell9_light;
	//livingCell 10 Info
	public static JLabel livingCell10_temperature;
	public static JLabel livingCell10_humidity;
	public static JLabel livingCell10_oxygen;
	public static JLabel livingCell10_pressure;
	public static JLabel livingCell10_smoke;
	public static JLabel livingCell10_gas;
	public static JLabel livingCell10_light;
	//livingCell 11 Info
	public static JLabel livingCell11_temperature;
	public static JLabel livingCell11_humidity;
	public static JLabel livingCell11_oxygen;
	public static JLabel livingCell11_pressure;
	public static JLabel livingCell11_smoke;
	public static JLabel livingCell11_gas;
	public static JLabel livingCell11_light;
	//livingCell 12 Info
	public static JLabel livingCell12_temperature;
	public static JLabel livingCell12_humidity;
	public static JLabel livingCell12_oxygen;
	public static JLabel livingCell12_pressure;
	public static JLabel livingCell12_smoke;
	public static JLabel livingCell12_gas;
	public static JLabel livingCell12_light;
	//livingCell 13 Info
	public static JLabel livingCell13_temperature;
	public static JLabel livingCell13_humidity;
	public static JLabel livingCell13_oxygen;
	public static JLabel livingCell13_pressure;
	public static JLabel livingCell13_smoke;
	public static JLabel livingCell13_gas;
	public static JLabel livingCell13_light;
	//livingCell 14 Info
	public static JLabel livingCell14_temperature;
	public static JLabel livingCell14_humidity;
	public static JLabel livingCell14_oxygen;
	public static JLabel livingCell14_pressure;
	public static JLabel livingCell14_smoke;
	public static JLabel livingCell14_gas;
	public static JLabel livingCell14_light;
	//livingCell 15 info
	public static JLabel livingCell15_temperature;
	public static JLabel livingCell15_humidity;
	public static JLabel livingCell15_oxygen;
	public static JLabel livingCell15_pressure;
	public static JLabel livingCell15_smoke;
	public static JLabel livingCell15_gas;
	public static JLabel livingCell15_light;
	//CommandSpace 1 Info
	public static JLabel command1_temperature;
	public static JLabel command1_humidity;
	public static JLabel command1_oxygen;
	public static JLabel command1_pressure;
	public static JLabel command1_smoke;
	public static JLabel command1_gas;
	public static JLabel command1_light;
	//CommandSpace 2 Info
	public static JLabel command2_temperature;
	public static JLabel command2_humidity;
	public static JLabel command2_oxygen;
	public static JLabel command2_pressure;
	public static JLabel command2_smoke;
	public static JLabel command2_gas;
	public static JLabel command2_light;
	//labSpace 1 Info
	public static JLabel labSpace1_temperature;
	public static JLabel labSpace1_humidity;
	public static JLabel labSpace1_oxygen;
	public static JLabel labSpace1_pressure;
	public static JLabel labSpace1_smoke;
	public static JLabel labSpace1_gas;
	public static JLabel labSpace1_light;
	//labSpace 2 Info
	public static JLabel labSpace2_temperature;
	public static JLabel labSpace2_humidity;
	public static JLabel labSpace2_oxygen;
	public static JLabel labSpace2_pressure;
	public static JLabel labSpace2_smoke;
	public static JLabel labSpace2_gas;
	public static JLabel labSpace2_light;
	//labSpace 3 Info
	public static JLabel labSpace3_temperature;
	public static JLabel labSpace3_humidity;
	public static JLabel labSpace3_oxygen;
	public static JLabel labSpace3_pressure;
	public static JLabel labSpace3_smoke;
	public static JLabel labSpace3_gas;
	public static JLabel labSpace3_light;
	//labSpace 4 Info
	public static JLabel labSpace4_temperature;
	public static JLabel labSpace4_humidity;
	public static JLabel labSpace4_oxygen;
	public static JLabel labSpace4_pressure;
	public static JLabel labSpace4_smoke;
	public static JLabel labSpace4_gas;
	public static JLabel labSpace4_light;
	//equipment Space Info
	public static JLabel equipSpace_temperature;
	public static JLabel equipSpace_humidity;
	public static JLabel equipSpace_oxygen;
	public static JLabel equipSpace_pressure;
	public static JLabel equipSpace_smoke;
	public static JLabel equipSpace_gas;
	public static JLabel equipSpace_light;
	//Well Area Info
	public static JLabel wellArea_temperature;
	public static JLabel wellArea_humidity;
	public static JLabel wellArea_oxygen;
	public static JLabel wellArea_pressure;
	public static JLabel wellArea_smoke;
	public static JLabel wellArea_gas;
	public static JLabel wellArea_light;
	//Heating and Cooling Area Info
	public static JLabel heatingArea_temperature;
	public static JLabel heatingArea_humidity;
	public static JLabel heatingArea_oxygen;
	public static JLabel heatingArea_pressure;
	public static JLabel heatingArea_smoke;
	public static JLabel heatingArea_gas;
	public static JLabel heatingArea_light;
	//External Wall Info
	public static JLabel externalWall_extemperture;
	public static JLabel externalWall_outsideTemp;
	public static JLabel externalWall_light;
	public static MP3 alarm = new MP3("lib/alarm.mp3");
	
	Timer timer;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
				MainFrame window = new MainFrame();
				window.displayFrame.setVisible(true);
			} catch (Exception e) {
					e.printStackTrace();
			}
		}
	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		displayFrame = new JFrame();
		displayFrame.addWindowListener(new WindowAdapter() {//退出后终止音效
			@Override
			public void windowClosing(WindowEvent e) {
				alarm.close();
				System.exit(0);
			}
		});
		displayFrame.setResizable(false);
		displayFrame.setBounds(00, 00, 1203, 773);
		displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		displayFrame.getContentPane().setLayout(null);
		//initial green light picture
		int lightWidth = 30;
	    int lightHeight = 30;
	    ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
	    flash.setImage(flash.getImage().getScaledInstance(lightWidth,lightHeight,Image.SCALE_DEFAULT));
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 1216, 39);
		displayFrame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel label = new JLabel("NASA Mars Habitat Display Console\n");
		label.setBounds(443, 6, 348, 23);
		panel.add(label);
		label.setFont(new Font("Kokonor", Font.BOLD, 20));
		label.setForeground(Color.ORANGE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel livingCellPanel = new JPanel();
		livingCellPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCellPanel.setBounds(0, 51, 690, 667);
		displayFrame.getContentPane().add(livingCellPanel);
		ImageIcon livingCellImage = new ImageIcon("lib/livingCell.jpg");
		int width = 106;
        int height = 93;
        livingCellImage.setImage(livingCellImage.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
		livingCellPanel.setLayout(null);
		JLabel lblLiving = new JLabel("LivingCell");
		lblLiving.setBounds(131, 6, 348, 25);
		lblLiving.setHorizontalAlignment(SwingConstants.CENTER);
		lblLiving.setForeground(Color.BLUE);
		lblLiving.setFont(new Font("Kokonor", Font.BOLD, 20));
		livingCellPanel.add(lblLiving);
		JPanel livingCell1Panel = new JPanel();
		livingCell1Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell1Panel.setBounds(6, 40, 203, 114);
		livingCellPanel.add(livingCell1Panel);
		livingCell1Panel.setLayout(null);
		JLabel lblLivingcell = new JLabel("LivingCell 1");
		lblLivingcell.setBounds(62, 0, 90, 16);
		livingCell1Panel.add(lblLivingcell);
		JLabel lblTemperature = new JLabel("Temperature:");
		lblTemperature.setBounds(6, 20, 88, 16);
		livingCell1Panel.add(lblTemperature);
	    livingCell1_temperature = new JLabel("72.0");
		livingCell1_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell1_temperature.setBounds(96, 20, 36, 16);
		livingCell1Panel.add(livingCell1_temperature);
		JLabel lblF = new JLabel("F");
		lblF.setBounds(139, 20, 16, 16);
		livingCell1Panel.add(lblF);
		JLabel lblHumidity = new JLabel("Humidity:");
		lblHumidity.setBounds(6, 35, 88, 16);
		livingCell1Panel.add(lblHumidity);
		livingCell1_humidity = new JLabel("45.0");
		livingCell1_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell1_humidity.setBounds(96, 35, 36, 16);
		livingCell1Panel.add(livingCell1_humidity);
		JLabel label_2 = new JLabel("%");
		label_2.setBounds(139, 35, 16, 16);
		livingCell1Panel.add(label_2);
		JLabel lblO = new JLabel("Oxygen Level:");
		lblO.setBounds(6, 50, 88, 16);
		livingCell1Panel.add(lblO);
		livingCell1_oxygen = new JLabel("21.0");
		livingCell1_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell1_oxygen.setBounds(96, 50, 36, 16);
		livingCell1Panel.add(livingCell1_oxygen);
		JLabel label_4 = new JLabel("%");
		label_4.setBounds(139, 50, 16, 16);
		livingCell1Panel.add(label_4);
		JLabel lblPressure = new JLabel("Pressure:");
		lblPressure.setBounds(6, 65, 88, 16);
		livingCell1Panel.add(lblPressure);
		livingCell1_pressure = new JLabel("14.5");
		livingCell1_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell1_pressure.setBounds(96, 65, 36, 16);
		livingCell1Panel.add(livingCell1_pressure);
		JLabel lblPsi = new JLabel("psi");
		lblPsi.setBounds(139, 65, 23, 16);
		livingCell1Panel.add(lblPsi);
		JLabel lblSmoke = new JLabel("Smoke:");
		lblSmoke.setBounds(6, 80, 88, 16);
		livingCell1Panel.add(lblSmoke);
		livingCell1_smoke = new JLabel("0");
		livingCell1_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell1_smoke.setBounds(96, 80, 36, 16);
		livingCell1Panel.add(livingCell1_smoke);
		JLabel lblGas = new JLabel("Gas:");
		lblGas.setBounds(6, 95, 88, 16);
		livingCell1Panel.add(lblGas);
		livingCell1_gas = new JLabel("0");
		livingCell1_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell1_gas.setBounds(96, 95, 36, 16);
		livingCell1Panel.add(livingCell1_gas);
		livingCell1_light = new JLabel(flash);
		livingCell1_light.setBounds(167, 40, 30, 30);
		livingCell1Panel.add(livingCell1_light);
		JPanel livingCell2Panel = new JPanel();
		livingCell2Panel.setLayout(null);
		livingCell2Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell2Panel.setBounds(6, 166, 203, 114);
		livingCellPanel.add(livingCell2Panel);
		JLabel lblLivingcell_1 = new JLabel("LivingCell 2");
		lblLivingcell_1.setBounds(62, 0, 90, 16);
		livingCell2Panel.add(lblLivingcell_1);
		JLabel label_3 = new JLabel("Temperature:");
		label_3.setBounds(6, 20, 88, 16);
		livingCell2Panel.add(label_3);
		livingCell2_temperature = new JLabel("72.0");
		livingCell2_temperature.setForeground(Color.BLACK);
		livingCell2_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell2_temperature.setBounds(96, 20, 36, 16);
		livingCell2Panel.add(livingCell2_temperature);
		JLabel label_6 = new JLabel("F");
		label_6.setBounds(139, 20, 16, 16);
		livingCell2Panel.add(label_6);
		JLabel label_7 = new JLabel("Humidity:");
		label_7.setBounds(6, 35, 88, 16);
		livingCell2Panel.add(label_7);
		livingCell2_humidity = new JLabel("45.0");
		livingCell2_humidity.setForeground(Color.BLACK);
		livingCell2_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell2_humidity.setBounds(96, 35, 36, 16);
		livingCell2Panel.add(livingCell2_humidity);
		JLabel label_9 = new JLabel("%");
		label_9.setBounds(139, 35, 16, 16);
		livingCell2Panel.add(label_9);
		JLabel label_10 = new JLabel("Oxygen Level:");
		label_10.setBounds(6, 50, 88, 16);
		livingCell2Panel.add(label_10);
		livingCell2_oxygen = new JLabel("21.0");
		livingCell2_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell2_oxygen.setBounds(96, 50, 36, 16);
		livingCell2Panel.add(livingCell2_oxygen);
		JLabel label_12 = new JLabel("%");
		label_12.setBounds(139, 50, 16, 16);
		livingCell2Panel.add(label_12);
		JLabel label_13 = new JLabel("Pressure:");
		label_13.setBounds(6, 65, 88, 16);
		livingCell2Panel.add(label_13);
		livingCell2_pressure = new JLabel("14.5");
		livingCell2_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell2_pressure.setBounds(96, 65, 36, 16);
		livingCell2Panel.add(livingCell2_pressure);
		JLabel label_15 = new JLabel("psi");
		label_15.setBounds(139, 65, 23, 16);
		livingCell2Panel.add(label_15);
		JLabel label_16 = new JLabel("Smoke:");
		label_16.setBounds(6, 80, 88, 16);
		livingCell2Panel.add(label_16);
		livingCell2_smoke = new JLabel("0");
		livingCell2_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell2_smoke.setBounds(96, 80, 36, 16);
		livingCell2Panel.add(livingCell2_smoke);
		JLabel label_18 = new JLabel("Gas:");
		label_18.setBounds(6, 95, 88, 16);
		livingCell2Panel.add(label_18);
		livingCell2_gas = new JLabel("0");
		livingCell2_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell2_gas.setBounds(96, 95, 36, 16);
		livingCell2Panel.add(livingCell2_gas);
		livingCell2_light = new JLabel(flash);
		livingCell2_light.setBounds(167, 40, 30, 30);
		livingCell2Panel.add(livingCell2_light);
		JPanel livingCell3Panel = new JPanel();
		livingCell3Panel.setLayout(null);
		livingCell3Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell3Panel.setBounds(6, 292, 203, 114);
		livingCellPanel.add(livingCell3Panel);
		JLabel lblLivingcell_2 = new JLabel("LivingCell 3");
		lblLivingcell_2.setBounds(62, 0, 90, 16);
		livingCell3Panel.add(lblLivingcell_2);
		JLabel label_22 = new JLabel("Temperature:");
		label_22.setBounds(6, 20, 88, 16);
		livingCell3Panel.add(label_22);
		livingCell3_temperature = new JLabel("72.0");
		livingCell3_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell3_temperature.setBounds(96, 20, 36, 16);
		livingCell3Panel.add(livingCell3_temperature);
		JLabel label_24 = new JLabel("F");
		label_24.setBounds(139, 20, 16, 16);
		livingCell3Panel.add(label_24);
		JLabel label_25 = new JLabel("Humidity:");
		label_25.setBounds(6, 35, 88, 16);
		livingCell3Panel.add(label_25);
		livingCell3_humidity = new JLabel("45.0");
		livingCell3_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell3_humidity.setBounds(96, 35, 36, 16);
		livingCell3Panel.add(livingCell3_humidity);
		JLabel label_27 = new JLabel("%");
		label_27.setBounds(139, 35, 16, 16);
		livingCell3Panel.add(label_27);
		JLabel label_28 = new JLabel("Oxygen Level:");
		label_28.setBounds(6, 50, 88, 16);
		livingCell3Panel.add(label_28);
		livingCell3_oxygen = new JLabel("21.0");
		livingCell3_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell3_oxygen.setBounds(96, 50, 36, 16);
		livingCell3Panel.add(livingCell3_oxygen);
		JLabel label_30 = new JLabel("%");
		label_30.setBounds(139, 50, 16, 16);
		livingCell3Panel.add(label_30);
		JLabel label_31 = new JLabel("Pressure:");
		label_31.setBounds(6, 65, 88, 16);
		livingCell3Panel.add(label_31);
		livingCell3_pressure = new JLabel("14.5");
		livingCell3_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell3_pressure.setBounds(96, 65, 36, 16);
		livingCell3Panel.add(livingCell3_pressure);
		JLabel label_33 = new JLabel("psi");
		label_33.setBounds(139, 65, 23, 16);
		livingCell3Panel.add(label_33);
		JLabel label_34 = new JLabel("Smoke:");
		label_34.setBounds(6, 80, 88, 16);
		livingCell3Panel.add(label_34);
		livingCell3_smoke = new JLabel("0");
		livingCell3_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell3_smoke.setBounds(96, 80, 36, 16);
		livingCell3Panel.add(livingCell3_smoke);
		JLabel label_36 = new JLabel("Gas:");
		label_36.setBounds(6, 95, 88, 16);
		livingCell3Panel.add(label_36);
		livingCell3_gas = new JLabel("0");
		livingCell3_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell3_gas.setBounds(96, 95, 36, 16);
		livingCell3Panel.add(livingCell3_gas);
		livingCell3_light = new JLabel(flash);
		livingCell3_light.setBounds(167, 40, 30, 30);
		livingCell3Panel.add(livingCell3_light);
		JPanel livingCell4Panel = new JPanel();
		livingCell4Panel.setLayout(null);
		livingCell4Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell4Panel.setBounds(6, 418, 203, 114);
		livingCellPanel.add(livingCell4Panel);
		JLabel lblLivingcell_3 = new JLabel("LivingCell 4");
		lblLivingcell_3.setBounds(62, 0, 90, 16);
		livingCell4Panel.add(lblLivingcell_3);
		JLabel label_40 = new JLabel("Temperature:");
		label_40.setBounds(6, 20, 88, 16);
		livingCell4Panel.add(label_40);
		livingCell4_temperature = new JLabel("72.0");
		livingCell4_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell4_temperature.setBounds(96, 20, 36, 16);
		livingCell4Panel.add(livingCell4_temperature);
		JLabel label_42 = new JLabel("F");
		label_42.setBounds(139, 20, 16, 16);
		livingCell4Panel.add(label_42);
		JLabel label_43 = new JLabel("Humidity:");
		label_43.setBounds(6, 35, 88, 16);
		livingCell4Panel.add(label_43);
		livingCell4_humidity = new JLabel("45.0");
		livingCell4_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell4_humidity.setBounds(96, 35, 36, 16);
		livingCell4Panel.add(livingCell4_humidity);
		JLabel label_45 = new JLabel("%");
		label_45.setBounds(139, 35, 16, 16);
		livingCell4Panel.add(label_45);
		JLabel label_46 = new JLabel("Oxygen Level:");
		label_46.setBounds(6, 50, 88, 16);
		livingCell4Panel.add(label_46);
		livingCell4_oxygen = new JLabel("21.0");
		livingCell4_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell4_oxygen.setBounds(96, 50, 36, 16);
		livingCell4Panel.add(livingCell4_oxygen);
		JLabel label_48 = new JLabel("%");
		label_48.setBounds(139, 50, 16, 16);
		livingCell4Panel.add(label_48);
		JLabel label_49 = new JLabel("Pressure:");
		label_49.setBounds(6, 65, 88, 16);
		livingCell4Panel.add(label_49);
		livingCell4_pressure = new JLabel("14.5");
		livingCell4_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell4_pressure.setBounds(96, 65, 36, 16);
		livingCell4Panel.add(livingCell4_pressure);
		JLabel label_51 = new JLabel("psi");
		label_51.setBounds(139, 65, 23, 16);
		livingCell4Panel.add(label_51);
		JLabel label_52 = new JLabel("Smoke:");
		label_52.setBounds(6, 80, 88, 16);
		livingCell4Panel.add(label_52);
		livingCell4_smoke = new JLabel("0");
		livingCell4_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell4_smoke.setBounds(96, 80, 36, 16);
		livingCell4Panel.add(livingCell4_smoke);
		JLabel label_54 = new JLabel("Gas:");
		label_54.setBounds(6, 95, 88, 16);
		livingCell4Panel.add(label_54);
		livingCell4_gas = new JLabel("0");
		livingCell4_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell4_gas.setBounds(96, 95, 36, 16);
		livingCell4Panel.add(livingCell4_gas);
		livingCell4_light = new JLabel(flash);
		livingCell4_light.setBounds(167, 40, 30, 30);
		livingCell4Panel.add(livingCell4_light);
		JPanel livingCell5Panel = new JPanel();
		livingCell5Panel.setLayout(null);
		livingCell5Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell5Panel.setBounds(6, 544, 203, 114);
		livingCellPanel.add(livingCell5Panel);
		JLabel lblLivingcell_4 = new JLabel("LivingCell 5");
		lblLivingcell_4.setBounds(62, 0, 90, 16);
		livingCell5Panel.add(lblLivingcell_4);
		JLabel label_58 = new JLabel("Temperature:");
		label_58.setBounds(6, 20, 88, 16);
		livingCell5Panel.add(label_58);
		livingCell5_temperature = new JLabel("72.0");
		livingCell5_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell5_temperature.setBounds(96, 20, 36, 16);
		livingCell5Panel.add(livingCell5_temperature);
		JLabel label_60 = new JLabel("F");
		label_60.setBounds(139, 20, 16, 16);
		livingCell5Panel.add(label_60);
		JLabel label_61 = new JLabel("Humidity:");
		label_61.setBounds(6, 35, 88, 16);
		livingCell5Panel.add(label_61);
		livingCell5_humidity = new JLabel("45.0");
		livingCell5_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell5_humidity.setBounds(96, 35, 36, 16);
		livingCell5Panel.add(livingCell5_humidity);
		JLabel label_63 = new JLabel("%");
		label_63.setBounds(139, 35, 16, 16);
		livingCell5Panel.add(label_63);
		JLabel label_64 = new JLabel("Oxygen Level:");
		label_64.setBounds(6, 50, 88, 16);
		livingCell5Panel.add(label_64);
		livingCell5_oxygen = new JLabel("21.0");
		livingCell5_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell5_oxygen.setBounds(96, 50, 36, 16);
		livingCell5Panel.add(livingCell5_oxygen);
		JLabel label_66 = new JLabel("%");
		label_66.setBounds(139, 50, 16, 16);
		livingCell5Panel.add(label_66);
		JLabel label_67 = new JLabel("Pressure:");
		label_67.setBounds(6, 65, 88, 16);
		livingCell5Panel.add(label_67);
		livingCell5_pressure = new JLabel("14.5");
		livingCell5_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell5_pressure.setBounds(96, 65, 36, 16);
		livingCell5Panel.add(livingCell5_pressure);
		JLabel label_69 = new JLabel("psi");
		label_69.setBounds(139, 65, 23, 16);
		livingCell5Panel.add(label_69);
		JLabel label_70 = new JLabel("Smoke:");
		label_70.setBounds(6, 80, 88, 16);
		livingCell5Panel.add(label_70);
		livingCell5_smoke = new JLabel("0");
		livingCell5_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell5_smoke.setBounds(96, 80, 36, 16);
		livingCell5Panel.add(livingCell5_smoke);
		JLabel label_72 = new JLabel("Gas:");
		label_72.setBounds(6, 95, 88, 16);
		livingCell5Panel.add(label_72);
		livingCell5_gas = new JLabel("0");
		livingCell5_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell5_gas.setBounds(96, 95, 36, 16);
		livingCell5Panel.add(livingCell5_gas);
		livingCell5_light = new JLabel(flash);
		livingCell5_light.setBounds(167, 40, 30, 30);
		livingCell5Panel.add(livingCell5_light);
		JPanel livingCell6Panel = new JPanel();
		livingCell6Panel.setLayout(null);
		livingCell6Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell6Panel.setBounds(241, 40, 203, 114);
		livingCellPanel.add(livingCell6Panel);
		JLabel lblLivingcell_5 = new JLabel("LivingCell 6");
		lblLivingcell_5.setBounds(62, 0, 90, 16);
		livingCell6Panel.add(lblLivingcell_5);
		JLabel label_76 = new JLabel("Temperature:");
		label_76.setBounds(6, 20, 88, 16);
		livingCell6Panel.add(label_76);
	    livingCell6_temperature = new JLabel("72.0");
		livingCell6_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell6_temperature.setBounds(96, 20, 36, 16);
		livingCell6Panel.add(livingCell6_temperature);
		JLabel label_78 = new JLabel("F");
		label_78.setBounds(139, 20, 16, 16);
		livingCell6Panel.add(label_78);
		JLabel label_79 = new JLabel("Humidity:");
		label_79.setBounds(6, 35, 88, 16);
		livingCell6Panel.add(label_79);
		livingCell6_humidity = new JLabel("45.0");
		livingCell6_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell6_humidity.setBounds(96, 35, 36, 16);
		livingCell6Panel.add(livingCell6_humidity);
		JLabel label_81 = new JLabel("%");
		label_81.setBounds(139, 35, 16, 16);
		livingCell6Panel.add(label_81);
		JLabel label_82 = new JLabel("Oxygen Level:");
		label_82.setBounds(6, 50, 88, 16);
		livingCell6Panel.add(label_82);
		livingCell6_oxygen = new JLabel("21.0");
		livingCell6_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell6_oxygen.setBounds(96, 50, 36, 16);
		livingCell6Panel.add(livingCell6_oxygen);
		JLabel label_84 = new JLabel("%");
		label_84.setBounds(139, 50, 16, 16);
		livingCell6Panel.add(label_84);
		JLabel label_85 = new JLabel("Pressure:");
		label_85.setBounds(6, 65, 88, 16);
		livingCell6Panel.add(label_85);
		livingCell6_pressure = new JLabel("14.5");
		livingCell6_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell6_pressure.setBounds(96, 65, 36, 16);
		livingCell6Panel.add(livingCell6_pressure);
		JLabel label_87 = new JLabel("psi");
		label_87.setBounds(139, 65, 23, 16);
		livingCell6Panel.add(label_87);
		JLabel label_88 = new JLabel("Smoke:");
		label_88.setBounds(6, 80, 88, 16);
		livingCell6Panel.add(label_88);
		livingCell6_smoke = new JLabel("0");
		livingCell6_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell6_smoke.setBounds(96, 80, 36, 16);
		livingCell6Panel.add(livingCell6_smoke);
		JLabel label_90 = new JLabel("Gas:");
		label_90.setBounds(6, 95, 88, 16);
		livingCell6Panel.add(label_90);
		livingCell6_gas = new JLabel("0");
		livingCell6_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell6_gas.setBounds(96, 95, 36, 16);
		livingCell6Panel.add(livingCell6_gas);
		livingCell6_light = new JLabel(flash);
		livingCell6_light.setBounds(167, 40, 30, 30);
		livingCell6Panel.add(livingCell6_light);
		JPanel livingCell7Panel = new JPanel();
		livingCell7Panel.setLayout(null);
		livingCell7Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell7Panel.setBounds(241, 166, 203, 114);
		livingCellPanel.add(livingCell7Panel);
		JLabel lblLivingcell_6 = new JLabel("LivingCell 7");
		lblLivingcell_6.setBounds(62, 0, 90, 16);
		livingCell7Panel.add(lblLivingcell_6);
		JLabel label_94 = new JLabel("Temperature:");
		label_94.setBounds(6, 20, 88, 16);
		livingCell7Panel.add(label_94);
		 livingCell7_temperature = new JLabel("72.0");
		livingCell7_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell7_temperature.setBounds(96, 20, 36, 16);
		livingCell7Panel.add(livingCell7_temperature);
		JLabel label_96 = new JLabel("F");
		label_96.setBounds(139, 20, 16, 16);
		livingCell7Panel.add(label_96);
		JLabel label_97 = new JLabel("Humidity:");
		label_97.setBounds(6, 35, 88, 16);
		livingCell7Panel.add(label_97);
		 livingCell7_humidity = new JLabel("45.0");
		livingCell7_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell7_humidity.setBounds(96, 35, 36, 16);
		livingCell7Panel.add(livingCell7_humidity);
		JLabel label_99 = new JLabel("%");
		label_99.setBounds(139, 35, 16, 16);
		livingCell7Panel.add(label_99);
		JLabel label_100 = new JLabel("Oxygen Level:");
		label_100.setBounds(6, 50, 88, 16);
		livingCell7Panel.add(label_100);
		 livingCell7_oxygen = new JLabel("21.0");
		livingCell7_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell7_oxygen.setBounds(96, 50, 36, 16);
		livingCell7Panel.add(livingCell7_oxygen);
		JLabel label_102 = new JLabel("%");
		label_102.setBounds(139, 50, 16, 16);
		livingCell7Panel.add(label_102);
		JLabel label_103 = new JLabel("Pressure:");
		label_103.setBounds(6, 65, 88, 16);
		livingCell7Panel.add(label_103);
		 livingCell7_pressure = new JLabel("14.5");
		livingCell7_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell7_pressure.setBounds(96, 65, 36, 16);
		livingCell7Panel.add(livingCell7_pressure);
		JLabel label_105 = new JLabel("psi");
		label_105.setBounds(139, 65, 23, 16);
		livingCell7Panel.add(label_105);
		JLabel label_106 = new JLabel("Smoke:");
		label_106.setBounds(6, 80, 88, 16);
		livingCell7Panel.add(label_106);
		 livingCell7_smoke = new JLabel("0");
		livingCell7_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell7_smoke.setBounds(96, 80, 36, 16);
		livingCell7Panel.add(livingCell7_smoke);
		JLabel label_108 = new JLabel("Gas:");
		label_108.setBounds(6, 95, 88, 16);
		livingCell7Panel.add(label_108);
		 livingCell7_gas = new JLabel("0");
		livingCell7_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell7_gas.setBounds(96, 95, 36, 16);
		livingCell7Panel.add(livingCell7_gas);
		 livingCell7_light = new JLabel(flash);
		livingCell7_light.setBounds(167, 40, 30, 30);
		livingCell7Panel.add(livingCell7_light);
		JPanel livingCell8Panel = new JPanel();
		livingCell8Panel.setLayout(null);
		livingCell8Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell8Panel.setBounds(241, 292, 203, 114);
		livingCellPanel.add(livingCell8Panel);
		JLabel lblLivingcell_7 = new JLabel("LivingCell 8");
		lblLivingcell_7.setBounds(62, 0, 90, 16);
		livingCell8Panel.add(lblLivingcell_7);
		JLabel label_112 = new JLabel("Temperature:");
		label_112.setBounds(6, 20, 88, 16);
		livingCell8Panel.add(label_112);
		 livingCell8_temperature = new JLabel("72.0");
		livingCell8_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell8_temperature.setBounds(96, 20, 36, 16);
		livingCell8Panel.add(livingCell8_temperature);
		JLabel label_114 = new JLabel("F");
		label_114.setBounds(139, 20, 16, 16);
		livingCell8Panel.add(label_114);
		JLabel label_115 = new JLabel("Humidity:");
		label_115.setBounds(6, 35, 88, 16);
		livingCell8Panel.add(label_115);
		 livingCell8_humidity = new JLabel("45.0");
		livingCell8_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell8_humidity.setBounds(96, 35, 36, 16);
		livingCell8Panel.add(livingCell8_humidity);
		JLabel label_117 = new JLabel("%");
		label_117.setBounds(139, 35, 16, 16);
		livingCell8Panel.add(label_117);
		JLabel label_118 = new JLabel("Oxygen Level:");
		label_118.setBounds(6, 50, 88, 16);
		livingCell8Panel.add(label_118);
		 livingCell8_oxygen = new JLabel("21.0");
		livingCell8_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell8_oxygen.setBounds(96, 50, 36, 16);
		livingCell8Panel.add(livingCell8_oxygen);
		JLabel label_120 = new JLabel("%");
		label_120.setBounds(139, 50, 16, 16);
		livingCell8Panel.add(label_120);
		JLabel label_121 = new JLabel("Pressure:");
		label_121.setBounds(6, 65, 88, 16);
		livingCell8Panel.add(label_121);
		 livingCell8_pressure = new JLabel("14.5");
		livingCell8_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell8_pressure.setBounds(96, 65, 36, 16);
		livingCell8Panel.add(livingCell8_pressure);
		JLabel label_123 = new JLabel("psi");
		label_123.setBounds(139, 65, 23, 16);
		livingCell8Panel.add(label_123);
		JLabel label_124 = new JLabel("Smoke:");
		label_124.setBounds(6, 80, 88, 16);
		livingCell8Panel.add(label_124);
		 livingCell8_smoke = new JLabel("0");
		livingCell8_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell8_smoke.setBounds(96, 80, 36, 16);
		livingCell8Panel.add(livingCell8_smoke);
		JLabel label_126 = new JLabel("Gas:");
		label_126.setBounds(6, 95, 88, 16);
		livingCell8Panel.add(label_126);
		 livingCell8_gas = new JLabel("0");
		livingCell8_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell8_gas.setBounds(96, 95, 36, 16);
		livingCell8Panel.add(livingCell8_gas);
		 livingCell8_light = new JLabel(flash);
		livingCell8_light.setBounds(167, 40, 30, 30);
		livingCell8Panel.add(livingCell8_light);
		JPanel livingCell9Panel = new JPanel();
		livingCell9Panel.setLayout(null);
		livingCell9Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell9Panel.setBounds(241, 418, 203, 114);
		livingCellPanel.add(livingCell9Panel);
		JLabel lblLivingcell_8 = new JLabel("LivingCell 9");
		lblLivingcell_8.setBounds(62, 0, 90, 16);
		livingCell9Panel.add(lblLivingcell_8);
		JLabel label_130 = new JLabel("Temperature:");
		label_130.setBounds(6, 20, 88, 16);
		livingCell9Panel.add(label_130);
		 livingCell9_temperature = new JLabel("72.0");
		livingCell9_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell9_temperature.setBounds(96, 20, 36, 16);
		livingCell9Panel.add(livingCell9_temperature);
		JLabel label_132 = new JLabel("F");
		label_132.setBounds(139, 20, 16, 16);
		livingCell9Panel.add(label_132);
		JLabel label_133 = new JLabel("Humidity:");
		label_133.setBounds(6, 35, 88, 16);
		livingCell9Panel.add(label_133);
		 livingCell9_humidity = new JLabel("45.0");
		livingCell9_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell9_humidity.setBounds(96, 35, 36, 16);
		livingCell9Panel.add(livingCell9_humidity);
		JLabel label_135 = new JLabel("%");
		label_135.setBounds(139, 35, 16, 16);
		livingCell9Panel.add(label_135);
		JLabel label_136 = new JLabel("Oxygen Level:");
		label_136.setBounds(6, 50, 88, 16);
		livingCell9Panel.add(label_136);
		 livingCell9_oxygen = new JLabel("21.0");
		livingCell9_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell9_oxygen.setBounds(96, 50, 36, 16);
		livingCell9Panel.add(livingCell9_oxygen);
		JLabel label_138 = new JLabel("%");
		label_138.setBounds(139, 50, 16, 16);
		livingCell9Panel.add(label_138);
		JLabel label_139 = new JLabel("Pressure:");
		label_139.setBounds(6, 65, 88, 16);
		livingCell9Panel.add(label_139);
		 livingCell9_pressure = new JLabel("14.5");
		livingCell9_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell9_pressure.setBounds(96, 65, 36, 16);
		livingCell9Panel.add(livingCell9_pressure);
		JLabel label_141 = new JLabel("psi");
		label_141.setBounds(139, 65, 23, 16);
		livingCell9Panel.add(label_141);
		JLabel label_142 = new JLabel("Smoke:");
		label_142.setBounds(6, 80, 88, 16);
		livingCell9Panel.add(label_142);
		 livingCell9_smoke = new JLabel("0");
		livingCell9_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell9_smoke.setBounds(96, 80, 36, 16);
		livingCell9Panel.add(livingCell9_smoke);
		JLabel label_144 = new JLabel("Gas:");
		label_144.setBounds(6, 95, 88, 16);
		livingCell9Panel.add(label_144);
		 livingCell9_gas = new JLabel("0");
		livingCell9_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell9_gas.setBounds(96, 95, 36, 16);
		livingCell9Panel.add(livingCell9_gas);
		 livingCell9_light = new JLabel(flash);
		livingCell9_light.setBounds(167, 40, 30, 30);
		livingCell9Panel.add(livingCell9_light);
		JPanel livingCell10Panel = new JPanel();
		livingCell10Panel.setLayout(null);
		livingCell10Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell10Panel.setBounds(241, 544, 203, 114);
		livingCellPanel.add(livingCell10Panel);
		JLabel lblLivingcell_9 = new JLabel("LivingCell 10");
		lblLivingcell_9.setBounds(62, 0, 90, 16);
		livingCell10Panel.add(lblLivingcell_9);
		JLabel label_148 = new JLabel("Temperature:");
		label_148.setBounds(6, 20, 88, 16);
		livingCell10Panel.add(label_148);
		 livingCell10_temperature = new JLabel("72.0");
		livingCell10_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell10_temperature.setBounds(96, 20, 36, 16);
		livingCell10Panel.add(livingCell10_temperature);
		JLabel label_150 = new JLabel("F");
		label_150.setBounds(139, 20, 16, 16);
		livingCell10Panel.add(label_150);
		JLabel label_151 = new JLabel("Humidity:");
		label_151.setBounds(6, 35, 88, 16);
		livingCell10Panel.add(label_151);
		 livingCell10_humidity = new JLabel("45.0");
		livingCell10_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell10_humidity.setBounds(96, 35, 36, 16);
		livingCell10Panel.add(livingCell10_humidity);
		JLabel label_153 = new JLabel("%");
		label_153.setBounds(139, 35, 16, 16);
		livingCell10Panel.add(label_153);
		JLabel label_154 = new JLabel("Oxygen Level:");
		label_154.setBounds(6, 50, 88, 16);
		livingCell10Panel.add(label_154);
		 livingCell10_oxygen = new JLabel("21.0");
		livingCell10_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell10_oxygen.setBounds(96, 50, 36, 16);
		livingCell10Panel.add(livingCell10_oxygen);
		JLabel label_156 = new JLabel("%");
		label_156.setBounds(139, 50, 16, 16);
		livingCell10Panel.add(label_156);
		JLabel label_157 = new JLabel("Pressure:");
		label_157.setBounds(6, 65, 88, 16);
		livingCell10Panel.add(label_157);
		 livingCell10_pressure = new JLabel("14.5");
		livingCell10_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell10_pressure.setBounds(96, 65, 36, 16);
		livingCell10Panel.add(livingCell10_pressure);
		JLabel label_159 = new JLabel("psi");
		label_159.setBounds(139, 65, 23, 16);
		livingCell10Panel.add(label_159);
		JLabel label_160 = new JLabel("Smoke:");
		label_160.setBounds(6, 80, 88, 16);
		livingCell10Panel.add(label_160);
		 livingCell10_smoke = new JLabel("0");
		livingCell10_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell10_smoke.setBounds(96, 80, 36, 16);
		livingCell10Panel.add(livingCell10_smoke);
		JLabel label_162 = new JLabel("Gas:");
		label_162.setBounds(6, 95, 88, 16);
		livingCell10Panel.add(label_162);
		 livingCell10_gas = new JLabel("0");
		livingCell10_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell10_gas.setBounds(96, 95, 36, 16);
		livingCell10Panel.add(livingCell10_gas);
		 livingCell10_light = new JLabel(flash);
		livingCell10_light.setBounds(167, 40, 30, 30);
		livingCell10Panel.add(livingCell10_light);
		JPanel livingCell11Panel = new JPanel();
		livingCell11Panel.setLayout(null);
		livingCell11Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell11Panel.setBounds(476, 40, 203, 114);
		livingCellPanel.add(livingCell11Panel);
		JLabel lblLivingcell_10 = new JLabel("LivingCell 11");
		lblLivingcell_10.setBounds(62, 0, 90, 16);
		livingCell11Panel.add(lblLivingcell_10);
		JLabel label_166 = new JLabel("Temperature:");
		label_166.setBounds(6, 20, 88, 16);
		livingCell11Panel.add(label_166);
		 livingCell11_temperature = new JLabel("72.0");
		livingCell11_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell11_temperature.setBounds(96, 20, 36, 16);
		livingCell11Panel.add(livingCell11_temperature);
		JLabel label_168 = new JLabel("F");
		label_168.setBounds(139, 20, 16, 16);
		livingCell11Panel.add(label_168);
		JLabel label_169 = new JLabel("Humidity:");
		label_169.setBounds(6, 35, 88, 16);
		livingCell11Panel.add(label_169);
		 livingCell11_humidity = new JLabel("45.0");
		livingCell11_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell11_humidity.setBounds(96, 35, 36, 16);
		livingCell11Panel.add(livingCell11_humidity);
		JLabel label_171 = new JLabel("%");
		label_171.setBounds(139, 35, 16, 16);
		livingCell11Panel.add(label_171);
		JLabel label_172 = new JLabel("Oxygen Level:");
		label_172.setBounds(6, 50, 88, 16);
		livingCell11Panel.add(label_172);
		 livingCell11_oxygen = new JLabel("21.0");
		livingCell11_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell11_oxygen.setBounds(96, 50, 36, 16);
		livingCell11Panel.add(livingCell11_oxygen);
		JLabel label_174 = new JLabel("%");
		label_174.setBounds(139, 50, 16, 16);
		livingCell11Panel.add(label_174);
		JLabel label_175 = new JLabel("Pressure:");
		label_175.setBounds(6, 65, 88, 16);
		livingCell11Panel.add(label_175);
		 livingCell11_pressure = new JLabel("14.5");
		livingCell11_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell11_pressure.setBounds(96, 65, 36, 16);
		livingCell11Panel.add(livingCell11_pressure);
		JLabel label_177 = new JLabel("psi");
		label_177.setBounds(139, 65, 23, 16);
		livingCell11Panel.add(label_177);
		JLabel label_178 = new JLabel("Smoke:");
		label_178.setBounds(6, 80, 88, 16);
		livingCell11Panel.add(label_178);
		 livingCell11_smoke = new JLabel("0");
		livingCell11_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell11_smoke.setBounds(96, 80, 36, 16);
		livingCell11Panel.add(livingCell11_smoke);
		JLabel label_180 = new JLabel("Gas:");
		label_180.setBounds(6, 95, 88, 16);
		livingCell11Panel.add(label_180);
		 livingCell11_gas = new JLabel("0");
		livingCell11_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell11_gas.setBounds(96, 95, 36, 16);
		livingCell11Panel.add(livingCell11_gas);
		 livingCell11_light = new JLabel(flash);
		livingCell11_light.setBounds(167, 40, 30, 30);
		livingCell11Panel.add(livingCell11_light);
		JPanel livingCell12Panel = new JPanel();
		livingCell12Panel.setLayout(null);
		livingCell12Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell12Panel.setBounds(476, 166, 203, 114);
		livingCellPanel.add(livingCell12Panel);
		JLabel lblLivingcell_11 = new JLabel("LivingCell 12");
		lblLivingcell_11.setBounds(62, 0, 90, 16);
		livingCell12Panel.add(lblLivingcell_11);
		JLabel label_184 = new JLabel("Temperature:");
		label_184.setBounds(6, 20, 88, 16);
		livingCell12Panel.add(label_184);
		 livingCell12_temperature = new JLabel("72.0");
		livingCell12_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell12_temperature.setBounds(96, 20, 36, 16);
		livingCell12Panel.add(livingCell12_temperature);
		JLabel label_186 = new JLabel("F");
		label_186.setBounds(139, 20, 16, 16);
		livingCell12Panel.add(label_186);
		JLabel label_187 = new JLabel("Humidity:");
		label_187.setBounds(6, 35, 88, 16);
		livingCell12Panel.add(label_187);
		 livingCell12_humidity = new JLabel("45.0");
		livingCell12_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell12_humidity.setBounds(96, 35, 36, 16);
		livingCell12Panel.add(livingCell12_humidity);
		JLabel label_189 = new JLabel("%");
		label_189.setBounds(139, 35, 16, 16);
		livingCell12Panel.add(label_189);
		JLabel label_190 = new JLabel("Oxygen Level:");
		label_190.setBounds(6, 50, 88, 16);
		livingCell12Panel.add(label_190);
		 livingCell12_oxygen = new JLabel("21.0");
		livingCell12_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell12_oxygen.setBounds(96, 50, 36, 16);
		livingCell12Panel.add(livingCell12_oxygen);
		JLabel label_192 = new JLabel("%");
		label_192.setBounds(139, 50, 16, 16);
		livingCell12Panel.add(label_192);
		JLabel label_193 = new JLabel("Pressure:");
		label_193.setBounds(6, 65, 88, 16);
		livingCell12Panel.add(label_193);
		 livingCell12_pressure = new JLabel("14.5");
		livingCell12_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell12_pressure.setBounds(96, 65, 36, 16);
		livingCell12Panel.add(livingCell12_pressure);
		JLabel label_195 = new JLabel("psi");
		label_195.setBounds(139, 65, 23, 16);
		livingCell12Panel.add(label_195);
		JLabel label_196 = new JLabel("Smoke:");
		label_196.setBounds(6, 80, 88, 16);
		livingCell12Panel.add(label_196);
		 livingCell12_smoke = new JLabel("0");
		livingCell12_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell12_smoke.setBounds(96, 80, 36, 16);
		livingCell12Panel.add(livingCell12_smoke);
		JLabel label_198 = new JLabel("Gas:");
		label_198.setBounds(6, 95, 88, 16);
		livingCell12Panel.add(label_198);
		 livingCell12_gas = new JLabel("0");
		livingCell12_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell12_gas.setBounds(96, 95, 36, 16);
		livingCell12Panel.add(livingCell12_gas);
		 livingCell12_light = new JLabel(flash);
		livingCell12_light.setBounds(167, 40, 30, 30);
		livingCell12Panel.add(livingCell12_light);
		JPanel livingCell13Panel = new JPanel();
		livingCell13Panel.setLayout(null);
		livingCell13Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell13Panel.setBounds(476, 292, 203, 114);
		livingCellPanel.add(livingCell13Panel);
		JLabel lblLivingcell_12 = new JLabel("LivingCell 13");
		lblLivingcell_12.setBounds(62, 0, 90, 16);
		livingCell13Panel.add(lblLivingcell_12);
		JLabel label_202 = new JLabel("Temperature:");
		label_202.setBounds(6, 20, 88, 16);
		livingCell13Panel.add(label_202);
		 livingCell13_temperature = new JLabel("72.0");
		livingCell13_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell13_temperature.setBounds(96, 20, 36, 16);
		livingCell13Panel.add(livingCell13_temperature);
		JLabel label_204 = new JLabel("F");
		label_204.setBounds(139, 20, 16, 16);
		livingCell13Panel.add(label_204);
		JLabel label_205 = new JLabel("Humidity:");
		label_205.setBounds(6, 35, 88, 16);
		livingCell13Panel.add(label_205);
		 livingCell13_humidity = new JLabel("45.0");
		livingCell13_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell13_humidity.setBounds(96, 35, 36, 16);
		livingCell13Panel.add(livingCell13_humidity);
		JLabel label_207 = new JLabel("%");
		label_207.setBounds(139, 35, 16, 16);
		livingCell13Panel.add(label_207);
		JLabel label_208 = new JLabel("Oxygen Level:");
		label_208.setBounds(6, 50, 88, 16);
		livingCell13Panel.add(label_208);
		 livingCell13_oxygen = new JLabel("21.0");
		livingCell13_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell13_oxygen.setBounds(96, 50, 36, 16);
		livingCell13Panel.add(livingCell13_oxygen);
		JLabel label_210 = new JLabel("%");
		label_210.setBounds(139, 50, 16, 16);
		livingCell13Panel.add(label_210);
		JLabel label13pre = new JLabel("Pressure:");
		label13pre.setBounds(6, 65, 88, 16);
		livingCell13Panel.add(label13pre);
		 livingCell13_pressure = new JLabel("14.5");
		livingCell13_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell13_pressure.setBounds(96, 65, 36, 16);
		livingCell13Panel.add(livingCell13_pressure);
		JLabel label_213 = new JLabel("psi");
		label_213.setBounds(139, 65, 23, 16);
		livingCell13Panel.add(label_213);
		JLabel label_214 = new JLabel("Smoke:");
		label_214.setBounds(6, 80, 88, 16);
		livingCell13Panel.add(label_214);
		 livingCell13_smoke = new JLabel("0");
		livingCell13_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell13_smoke.setBounds(96, 80, 36, 16);
		livingCell13Panel.add(livingCell13_smoke);
		JLabel label_216 = new JLabel("Gas:");
		label_216.setBounds(6, 95, 88, 16);
		livingCell13Panel.add(label_216);
		 livingCell13_gas = new JLabel("0");
		livingCell13_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell13_gas.setBounds(96, 95, 36, 16);
		livingCell13Panel.add(livingCell13_gas);
		 livingCell13_light = new JLabel(flash);
		livingCell13_light.setBounds(167, 40, 30, 30);
		livingCell13Panel.add(livingCell13_light);
		JPanel livingCell14Panel = new JPanel();
		livingCell14Panel.setLayout(null);
		livingCell14Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell14Panel.setBounds(476, 418, 203, 114);
		livingCellPanel.add(livingCell14Panel);
		JLabel lblLivingcell_13 = new JLabel("LivingCell 14");
		lblLivingcell_13.setBounds(62, 0, 90, 16);
		livingCell14Panel.add(lblLivingcell_13);
		JLabel label_220 = new JLabel("Temperature:");
		label_220.setBounds(6, 20, 88, 16);
		livingCell14Panel.add(label_220);
		 livingCell14_temperature = new JLabel("72.0");
		livingCell14_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell14_temperature.setBounds(96, 20, 36, 16);
		livingCell14Panel.add(livingCell14_temperature);
		JLabel label_222 = new JLabel("F");
		label_222.setBounds(139, 20, 16, 16);
		livingCell14Panel.add(label_222);
		JLabel label_223 = new JLabel("Humidity:");
		label_223.setBounds(6, 35, 88, 16);
		livingCell14Panel.add(label_223);
		 livingCell14_humidity = new JLabel("45.0");
		livingCell14_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell14_humidity.setBounds(96, 35, 36, 16);
		livingCell14Panel.add(livingCell14_humidity);
		JLabel label_225 = new JLabel("%");
		label_225.setBounds(139, 35, 16, 16);
		livingCell14Panel.add(label_225);
		JLabel label_226 = new JLabel("Oxygen Level:");
		label_226.setBounds(6, 50, 88, 16);
		livingCell14Panel.add(label_226);
		 livingCell14_oxygen = new JLabel("21.0");
		livingCell14_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell14_oxygen.setBounds(96, 50, 36, 16);
		livingCell14Panel.add(livingCell14_oxygen);
		JLabel label_228 = new JLabel("%");
		label_228.setBounds(139, 50, 16, 16);
		livingCell14Panel.add(label_228);
		JLabel label14pre = new JLabel("Pressure:");
		label14pre.setBounds(6, 65, 88, 16);
		livingCell14Panel.add(label14pre);
		 livingCell14_pressure = new JLabel("14.5");
		livingCell14_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell14_pressure.setBounds(96, 65, 36, 16);
		livingCell14Panel.add(livingCell14_pressure);
		JLabel label_231 = new JLabel("psi");
		label_231.setBounds(139, 65, 23, 16);
		livingCell14Panel.add(label_231);
		JLabel label_232 = new JLabel("Smoke:");
		label_232.setBounds(6, 80, 88, 16);
		livingCell14Panel.add(label_232);
		 livingCell14_smoke = new JLabel("0");
		livingCell14_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell14_smoke.setBounds(96, 80, 36, 16);
		livingCell14Panel.add(livingCell14_smoke);
		JLabel label_234 = new JLabel("Gas:");
		label_234.setBounds(6, 95, 88, 16);
		livingCell14Panel.add(label_234);
		 livingCell14_gas = new JLabel("0");
		livingCell14_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell14_gas.setBounds(96, 95, 36, 16);
		livingCell14Panel.add(livingCell14_gas);
		 livingCell14_light = new JLabel(flash);
		livingCell14_light.setBounds(167, 40, 30, 30);
		livingCell14Panel.add(livingCell14_light);
		JPanel livingCell15Panel = new JPanel();
		livingCell15Panel.setLayout(null);
		livingCell15Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCell15Panel.setBounds(476, 544, 203, 114);
		livingCellPanel.add(livingCell15Panel);
		JLabel lblLivingcell_14 = new JLabel("LivingCell 15");
		lblLivingcell_14.setBounds(62, 0, 90, 16);
		livingCell15Panel.add(lblLivingcell_14);
		JLabel label_238 = new JLabel("Temperature:");
		label_238.setBounds(6, 20, 88, 16);
		livingCell15Panel.add(label_238);
		 livingCell15_temperature = new JLabel("72.0");
		livingCell15_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell15_temperature.setBounds(96, 20, 36, 16);
		livingCell15Panel.add(livingCell15_temperature);
		JLabel label_240 = new JLabel("F");
		label_240.setBounds(139, 20, 16, 16);
		livingCell15Panel.add(label_240);
		JLabel label_241 = new JLabel("Humidity:");
		label_241.setBounds(6, 35, 88, 16);
		livingCell15Panel.add(label_241);
		 livingCell15_humidity = new JLabel("45.0");
		livingCell15_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell15_humidity.setBounds(96, 35, 36, 16);
		livingCell15Panel.add(livingCell15_humidity);
		JLabel label_243 = new JLabel("%");
		label_243.setBounds(139, 35, 16, 16);
		livingCell15Panel.add(label_243);
		JLabel label_244 = new JLabel("Oxygen Level:");
		label_244.setBounds(6, 50, 88, 16);
		livingCell15Panel.add(label_244);
		 livingCell15_oxygen = new JLabel("21.0");
		livingCell15_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell15_oxygen.setBounds(96, 50, 36, 16);
		livingCell15Panel.add(livingCell15_oxygen);
		JLabel label_246 = new JLabel("%");
		label_246.setBounds(139, 50, 16, 16);
		livingCell15Panel.add(label_246);
		JLabel label15pre = new JLabel("Pressure:");
		label15pre.setBounds(6, 65, 88, 16);
		livingCell15Panel.add(label15pre);
		 livingCell15_pressure = new JLabel("14.5");
		livingCell15_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell15_pressure.setBounds(96, 65, 36, 16);
		livingCell15Panel.add(livingCell15_pressure);
		JLabel label_249 = new JLabel("psi");
		label_249.setBounds(139, 65, 23, 16);
		livingCell15Panel.add(label_249);
		JLabel label_250 = new JLabel("Smoke:");
		label_250.setBounds(6, 80, 88, 16);
		livingCell15Panel.add(label_250);
		 livingCell15_smoke = new JLabel("0");
		livingCell15_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell15_smoke.setBounds(96, 80, 36, 16);
		livingCell15Panel.add(livingCell15_smoke);
		JLabel label_252 = new JLabel("Gas:");
		label_252.setBounds(6, 95, 88, 16);
		livingCell15Panel.add(label_252);
		 livingCell15_gas = new JLabel("0");
		livingCell15_gas.setHorizontalAlignment(SwingConstants.CENTER);
		livingCell15_gas.setBounds(96, 95, 36, 16);
		livingCell15Panel.add(livingCell15_gas);
		 livingCell15_light = new JLabel(flash);
		livingCell15_light.setBounds(167, 40, 30, 30);
		livingCell15Panel.add(livingCell15_light);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(702, 51, 476, 141);
		displayFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		JLabel lblL = new JLabel("Command Space");
		lblL.setHorizontalAlignment(SwingConstants.CENTER);
		lblL.setForeground(Color.BLUE);
		lblL.setFont(new Font("Kokonor", Font.BOLD, 20));
		lblL.setBounds(44, 0, 348, 25);
		panel_1.add(lblL);
		JPanel commandSpace1Panel = new JPanel();
		commandSpace1Panel.setLayout(null);
		commandSpace1Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		commandSpace1Panel.setBounds(6, 23, 203, 114);
		panel_1.add(commandSpace1Panel);
		JLabel lblCommandSpace = new JLabel("Command Space 1");
		lblCommandSpace.setBounds(40, 0, 132, 16);
		commandSpace1Panel.add(lblCommandSpace);
		JLabel label_5 = new JLabel("Temperature:");
		label_5.setBounds(6, 20, 88, 16);
		commandSpace1Panel.add(label_5);
		 command1_temperature = new JLabel("72.0");
		command1_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		command1_temperature.setBounds(96, 20, 36, 16);
		commandSpace1Panel.add(command1_temperature);
		JLabel label_11 = new JLabel("F");
		label_11.setBounds(139, 20, 16, 16);
		commandSpace1Panel.add(label_11);
		JLabel label_14 = new JLabel("Humidity:");
		label_14.setBounds(6, 35, 88, 16);
		commandSpace1Panel.add(label_14);
		 command1_humidity = new JLabel("45.0");
		command1_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		command1_humidity.setBounds(96, 35, 36, 16);
		commandSpace1Panel.add(command1_humidity);
		JLabel label_19 = new JLabel("%");
		label_19.setBounds(139, 35, 16, 16);
		commandSpace1Panel.add(label_19);
		JLabel label_20 = new JLabel("Oxygen Level:");
		label_20.setBounds(6, 50, 88, 16);
		commandSpace1Panel.add(label_20);
		 command1_oxygen = new JLabel("21.0");
		command1_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		command1_oxygen.setBounds(96, 50, 36, 16);
		commandSpace1Panel.add(command1_oxygen);
		JLabel label_23 = new JLabel("%");
		label_23.setBounds(139, 50, 16, 16);
		commandSpace1Panel.add(label_23);
		JLabel label_26 = new JLabel("Pressure:");
		label_26.setBounds(6, 65, 88, 16);
		commandSpace1Panel.add(label_26);
		 command1_pressure = new JLabel("14.5");
		command1_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		command1_pressure.setBounds(96, 65, 36, 16);
		commandSpace1Panel.add(command1_pressure);
		JLabel label_32 = new JLabel("psi");
		label_32.setBounds(139, 65, 23, 16);
		commandSpace1Panel.add(label_32);
		JLabel label_35 = new JLabel("Smoke:");
		label_35.setBounds(6, 80, 88, 16);
		commandSpace1Panel.add(label_35);
		 command1_smoke = new JLabel("0");
		command1_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		command1_smoke.setBounds(96, 80, 36, 16);
		commandSpace1Panel.add(command1_smoke);
		JLabel label_38 = new JLabel("Gas:");
		label_38.setBounds(6, 95, 88, 16);
		commandSpace1Panel.add(label_38);
		 command1_gas = new JLabel("0");
		command1_gas.setHorizontalAlignment(SwingConstants.CENTER);
		command1_gas.setBounds(96, 95, 36, 16);
		commandSpace1Panel.add(command1_gas);
		 command1_light = new JLabel(flash);
		command1_light.setBounds(167, 40, 30, 30);
		commandSpace1Panel.add(command1_light);
		JPanel commandSpace2Panel = new JPanel();
		commandSpace2Panel.setLayout(null);
		commandSpace2Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		commandSpace2Panel.setBounds(243, 23, 203, 114);
		panel_1.add(commandSpace2Panel);
		JLabel lblCommandSpace_1 = new JLabel("Command Space 2");
		lblCommandSpace_1.setBounds(40, 0, 122, 16);
		commandSpace2Panel.add(lblCommandSpace_1);
		JLabel label_47 = new JLabel("Temperature:");
		label_47.setBounds(6, 20, 88, 16);
		commandSpace2Panel.add(label_47);
		 command2_temperature = new JLabel("72.0");
		command2_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		command2_temperature.setBounds(96, 20, 36, 16);
		commandSpace2Panel.add(command2_temperature);
		JLabel label_53 = new JLabel("F");
		label_53.setBounds(139, 20, 16, 16);
		commandSpace2Panel.add(label_53);
		JLabel label_55 = new JLabel("Humidity:");
		label_55.setBounds(6, 35, 88, 16);
		commandSpace2Panel.add(label_55);
		 command2_humidity = new JLabel("45.0");
		command2_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		command2_humidity.setBounds(96, 35, 36, 16);
		commandSpace2Panel.add(command2_humidity);
		JLabel label_57 = new JLabel("%");
		label_57.setBounds(139, 35, 16, 16);
		commandSpace2Panel.add(label_57);
		JLabel label_59 = new JLabel("Oxygen Level:");
		label_59.setBounds(6, 50, 88, 16);
		commandSpace2Panel.add(label_59);
		 command2_oxygen = new JLabel("21.0");
		command2_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		command2_oxygen.setBounds(96, 50, 36, 16);
		commandSpace2Panel.add(command2_oxygen);
		JLabel label_65 = new JLabel("%");
		label_65.setBounds(139, 50, 16, 16);
		commandSpace2Panel.add(label_65);
		JLabel label_68 = new JLabel("Pressure:");
		label_68.setBounds(6, 65, 88, 16);
		commandSpace2Panel.add(label_68);
		 command2_pressure = new JLabel("14.5");
		command2_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		command2_pressure.setBounds(96, 65, 36, 16);
		commandSpace2Panel.add(command2_pressure);
		JLabel label_73 = new JLabel("psi");
		label_73.setBounds(139, 65, 23, 16);
		commandSpace2Panel.add(label_73);
		JLabel label_74 = new JLabel("Smoke:");
		label_74.setBounds(6, 80, 88, 16);
		commandSpace2Panel.add(label_74);
		 command2_smoke = new JLabel("0");
		command2_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		command2_smoke.setBounds(96, 80, 36, 16);
		commandSpace2Panel.add(command2_smoke);
		JLabel label_77 = new JLabel("Gas:");
		label_77.setBounds(6, 95, 88, 16);
		commandSpace2Panel.add(label_77);
		 command2_gas = new JLabel("0");
		command2_gas.setHorizontalAlignment(SwingConstants.CENTER);
		command2_gas.setBounds(96, 95, 36, 16);
		commandSpace2Panel.add(command2_gas);
		 command2_light = new JLabel(flash);
		command2_light.setBounds(167, 40, 30, 30);
		commandSpace2Panel.add(command2_light);
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setLayout(null);
		panel_4.setBounds(702, 195, 476, 258);
		displayFrame.getContentPane().add(panel_4);
		JLabel lblLab = new JLabel("Laboratory Space");
		lblLab.setHorizontalAlignment(SwingConstants.CENTER);
		lblLab.setForeground(Color.BLUE);
		lblLab.setFont(new Font("Kokonor", Font.BOLD, 20));
		lblLab.setBounds(44, 0, 348, 25);
		panel_4.add(lblLab);
		JPanel LabSpace1Panel = new JPanel();
		LabSpace1Panel.setLayout(null);
		LabSpace1Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		LabSpace1Panel.setBounds(6, 23, 203, 114);
		panel_4.add(LabSpace1Panel);
		JLabel lblLabor = new JLabel("Laboratory Space 1");
		lblLabor.setBounds(43, 0, 119, 16);
		LabSpace1Panel.add(lblLabor);
		JLabel label_91 = new JLabel("Temperature:");
		label_91.setBounds(6, 20, 88, 16);
		LabSpace1Panel.add(label_91);
		 labSpace1_temperature = new JLabel("72.0");
		labSpace1_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace1_temperature.setBounds(96, 20, 36, 16);
		LabSpace1Panel.add(labSpace1_temperature);
		JLabel label_93 = new JLabel("F");
		label_93.setBounds(139, 20, 16, 16);
		LabSpace1Panel.add(label_93);
		JLabel label_95 = new JLabel("Humidity:");
		label_95.setBounds(6, 35, 88, 16);
		LabSpace1Panel.add(label_95);
		 labSpace1_humidity = new JLabel("45.0");
		labSpace1_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace1_humidity.setBounds(96, 35, 36, 16);
		LabSpace1Panel.add(labSpace1_humidity);
		JLabel label_101 = new JLabel("%");
		label_101.setBounds(139, 35, 16, 16);
		LabSpace1Panel.add(label_101);
		JLabel label_104 = new JLabel("Oxygen Level:");
		label_104.setBounds(6, 50, 88, 16);
		LabSpace1Panel.add(label_104);
		 labSpace1_oxygen = new JLabel("21.0");
		labSpace1_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace1_oxygen.setBounds(96, 50, 36, 16);
		LabSpace1Panel.add(labSpace1_oxygen);
		JLabel label_109 = new JLabel("%");
		label_109.setBounds(139, 50, 16, 16);
		LabSpace1Panel.add(label_109);
		JLabel label_110 = new JLabel("Pressure:");
		label_110.setBounds(6, 65, 88, 16);
		LabSpace1Panel.add(label_110);
		 labSpace1_pressure = new JLabel("14.5");
		labSpace1_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace1_pressure.setBounds(96, 65, 36, 16);
		LabSpace1Panel.add(labSpace1_pressure);
		JLabel label_113 = new JLabel("psi");
		label_113.setBounds(139, 65, 23, 16);
		LabSpace1Panel.add(label_113);
		JLabel label_116 = new JLabel("Smoke:");
		label_116.setBounds(6, 80, 88, 16);
		LabSpace1Panel.add(label_116);
		 labSpace1_smoke = new JLabel("0");
		labSpace1_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace1_smoke.setBounds(96, 80, 36, 16);
		LabSpace1Panel.add(labSpace1_smoke);
		JLabel label_122 = new JLabel("Gas:");
		label_122.setBounds(6, 95, 88, 16);
		LabSpace1Panel.add(label_122);
		 labSpace1_gas = new JLabel("0");
		labSpace1_gas.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace1_gas.setBounds(96, 95, 36, 16);
		LabSpace1Panel.add(labSpace1_gas);
		 labSpace1_light = new JLabel(flash);
		labSpace1_light.setBounds(167, 40, 30, 30);
		LabSpace1Panel.add(labSpace1_light);
		JPanel LabSpace3Panel = new JPanel();
		LabSpace3Panel.setLayout(null);
		LabSpace3Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		LabSpace3Panel.setBounds(243, 23, 203, 114);
		panel_4.add(LabSpace3Panel);
		JLabel lblLaboratorySpace_1 = new JLabel("Laboratory Space 3");
		lblLaboratorySpace_1.setBounds(45, 0, 126, 16);
		LabSpace3Panel.add(lblLaboratorySpace_1);
		JLabel label_129 = new JLabel("Temperature:");
		label_129.setBounds(6, 20, 88, 16);
		LabSpace3Panel.add(label_129);
		 labSpace3_temperature = new JLabel("72.0");
		labSpace3_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace3_temperature.setBounds(96, 20, 36, 16);
		LabSpace3Panel.add(labSpace3_temperature);
		JLabel label_134 = new JLabel("F");
		label_134.setBounds(139, 20, 16, 16);
		LabSpace3Panel.add(label_134);
		JLabel label_137 = new JLabel("Humidity:");
		label_137.setBounds(6, 35, 88, 16);
		LabSpace3Panel.add(label_137);
		 labSpace3_humidity = new JLabel("45.0");
		labSpace3_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace3_humidity.setBounds(96, 35, 36, 16);
		LabSpace3Panel.add(labSpace3_humidity);
		JLabel label_143 = new JLabel("%");
		label_143.setBounds(139, 35, 16, 16);
		LabSpace3Panel.add(label_143);
		JLabel label_145 = new JLabel("Oxygen Level:");
		label_145.setBounds(6, 50, 88, 16);
		LabSpace3Panel.add(label_145);
		 labSpace3_oxygen = new JLabel("21.0");
		labSpace3_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace3_oxygen.setBounds(96, 50, 36, 16);
		LabSpace3Panel.add(labSpace3_oxygen);
		JLabel label_147 = new JLabel("%");
		label_147.setBounds(139, 50, 16, 16);
		LabSpace3Panel.add(label_147);
		JLabel label_149 = new JLabel("Pressure:");
		label_149.setBounds(6, 65, 88, 16);
		LabSpace3Panel.add(label_149);
		 labSpace3_pressure = new JLabel("14.5");
		labSpace3_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace3_pressure.setBounds(96, 65, 36, 16);
		LabSpace3Panel.add(labSpace3_pressure);
		JLabel label_155 = new JLabel("psi");
		label_155.setBounds(139, 65, 23, 16);
		LabSpace3Panel.add(label_155);
		JLabel label_158 = new JLabel("Smoke:");
		label_158.setBounds(6, 80, 88, 16);
		LabSpace3Panel.add(label_158);
		 labSpace3_smoke = new JLabel("0");
		labSpace3_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace3_smoke.setBounds(96, 80, 36, 16);
		LabSpace3Panel.add(labSpace3_smoke);
		JLabel label_163 = new JLabel("Gas:");
		label_163.setBounds(6, 95, 88, 16);
		LabSpace3Panel.add(label_163);
		 labSpace3_gas = new JLabel("0");
		labSpace3_gas.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace3_gas.setBounds(96, 95, 36, 16);
		LabSpace3Panel.add(labSpace3_gas);
		 labSpace3_light = new JLabel(flash);
		labSpace3_light.setBounds(167, 40, 30, 30);
		LabSpace3Panel.add(labSpace3_light);
		JPanel LabSpace2Panel = new JPanel();
		LabSpace2Panel.setLayout(null);
		LabSpace2Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		LabSpace2Panel.setBounds(6, 140, 203, 114);
		panel_4.add(LabSpace2Panel);
		JLabel lblLaboratorySpace = new JLabel("Laboratory Space 2");
		lblLaboratorySpace.setBounds(43, 0, 119, 16);
		LabSpace2Panel.add(lblLaboratorySpace);
		JLabel label_167 = new JLabel("Temperature:");
		label_167.setBounds(6, 20, 88, 16);
		LabSpace2Panel.add(label_167);
		 labSpace2_temperature = new JLabel("72.0");
		labSpace2_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace2_temperature.setBounds(96, 20, 36, 16);
		LabSpace2Panel.add(labSpace2_temperature);
		JLabel label_173 = new JLabel("F");
		label_173.setBounds(139, 20, 16, 16);
		LabSpace2Panel.add(label_173);
		JLabel label_176 = new JLabel("Humidity:");
		label_176.setBounds(6, 35, 88, 16);
		LabSpace2Panel.add(label_176);
		 labSpace2_humidity = new JLabel("45.0");
		labSpace2_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace2_humidity.setBounds(96, 35, 36, 16);
		LabSpace2Panel.add(labSpace2_humidity);
		JLabel label_181 = new JLabel("%");
		label_181.setBounds(139, 35, 16, 16);
		LabSpace2Panel.add(label_181);
		JLabel label_182 = new JLabel("Oxygen Level:");
		label_182.setBounds(6, 50, 88, 16);
		LabSpace2Panel.add(label_182);
		 labSpace2_oxygen = new JLabel("21.0");
		labSpace2_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace2_oxygen.setBounds(96, 50, 36, 16);
		LabSpace2Panel.add(labSpace2_oxygen);
		JLabel label_185 = new JLabel("%");
		label_185.setBounds(139, 50, 16, 16);
		LabSpace2Panel.add(label_185);
		JLabel label_188 = new JLabel("Pressure:");
		label_188.setBounds(6, 65, 88, 16);
		LabSpace2Panel.add(label_188);
		 labSpace2_pressure = new JLabel("14.5");
		labSpace2_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace2_pressure.setBounds(96, 65, 36, 16);
		LabSpace2Panel.add(labSpace2_pressure);
		JLabel label_194 = new JLabel("psi");
		label_194.setBounds(139, 65, 23, 16);
		LabSpace2Panel.add(label_194);
		JLabel label_197 = new JLabel("Smoke:");
		label_197.setBounds(6, 80, 88, 16);
		LabSpace2Panel.add(label_197);
		 labSpace2_smoke = new JLabel("0");
		labSpace2_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace2_smoke.setBounds(96, 80, 36, 16);
		LabSpace2Panel.add(labSpace2_smoke);
		JLabel label_200 = new JLabel("Gas:");
		label_200.setBounds(6, 95, 88, 16);
		LabSpace2Panel.add(label_200);
		 labSpace2_gas = new JLabel("0");
		labSpace2_gas.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace2_gas.setBounds(96, 95, 36, 16);
		LabSpace2Panel.add(labSpace2_gas);
		 labSpace2_light = new JLabel(flash);
		labSpace2_light.setBounds(167, 40, 30, 30);
		LabSpace2Panel.add(labSpace2_light);
		JPanel LabSpace4Panel = new JPanel();
		LabSpace4Panel.setLayout(null);
		LabSpace4Panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		LabSpace4Panel.setBounds(243, 140, 203, 114);
		panel_4.add(LabSpace4Panel);
		JLabel lblLaboratorySpace_2 = new JLabel("Laboratory Space 4");
		lblLaboratorySpace_2.setBounds(46, 0, 127, 16);
		LabSpace4Panel.add(lblLaboratorySpace_2);
		JLabel label_209 = new JLabel("Temperature:");
		label_209.setBounds(6, 20, 88, 16);
		LabSpace4Panel.add(label_209);
		 labSpace4_temperature = new JLabel("72.0");
		labSpace4_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace4_temperature.setBounds(96, 20, 36, 16);
		LabSpace4Panel.add(labSpace4_temperature);
		JLabel label_215 = new JLabel("F");
		label_215.setBounds(139, 20, 16, 16);
		LabSpace4Panel.add(label_215);
		JLabel label_217 = new JLabel("Humidity:");
		label_217.setBounds(6, 35, 88, 16);
		LabSpace4Panel.add(label_217);
		 labSpace4_humidity = new JLabel("45.0");
		labSpace4_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace4_humidity.setBounds(96, 35, 36, 16);
		LabSpace4Panel.add(labSpace4_humidity);
		JLabel label_219 = new JLabel("%");
		label_219.setBounds(139, 35, 16, 16);
		LabSpace4Panel.add(label_219);
		JLabel label_221 = new JLabel("Oxygen Level:");
		label_221.setBounds(6, 50, 88, 16);
		LabSpace4Panel.add(label_221);
		 labSpace4_oxygen = new JLabel("21.0");
		labSpace4_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace4_oxygen.setBounds(96, 50, 36, 16);
		LabSpace4Panel.add(labSpace4_oxygen);
		JLabel label_227 = new JLabel("%");
		label_227.setBounds(139, 50, 16, 16);
		LabSpace4Panel.add(label_227);
		JLabel label_229 = new JLabel("Pressure:");
		label_229.setBounds(6, 65, 88, 16);
		LabSpace4Panel.add(label_229);
		 labSpace4_pressure = new JLabel("14.5");
		labSpace4_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace4_pressure.setBounds(96, 65, 36, 16);
		LabSpace4Panel.add(labSpace4_pressure);
		JLabel label_235 = new JLabel("psi");
		label_235.setBounds(139, 65, 23, 16);
		LabSpace4Panel.add(label_235);
		JLabel label_236 = new JLabel("Smoke:");
		label_236.setBounds(6, 80, 88, 16);
		LabSpace4Panel.add(label_236);
		 labSpace4_smoke = new JLabel("0");
		labSpace4_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace4_smoke.setBounds(96, 80, 36, 16);
		LabSpace4Panel.add(labSpace4_smoke);
		JLabel label_239 = new JLabel("Gas:");
		label_239.setBounds(6, 95, 88, 16);
		LabSpace4Panel.add(label_239);
		 labSpace4_gas = new JLabel("0");
		labSpace4_gas.setHorizontalAlignment(SwingConstants.CENTER);
		labSpace4_gas.setBounds(96, 95, 36, 16);
		LabSpace4Panel.add(labSpace4_gas);
		 labSpace4_light = new JLabel(flash);
		labSpace4_light.setBounds(167, 40, 30, 30);
		LabSpace4Panel.add(labSpace4_light);
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setLayout(null);
		panel_9.setBounds(702, 454, 476, 264);
		displayFrame.getContentPane().add(panel_9);
		JLabel lblOtherSpace = new JLabel("Other Space");
		lblOtherSpace.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtherSpace.setForeground(Color.BLUE);
		lblOtherSpace.setFont(new Font("Kokonor", Font.BOLD, 20));
		lblOtherSpace.setBounds(44, 0, 348, 25);
		panel_9.add(lblOtherSpace);
		JPanel EquipSpacePanel = new JPanel();
		EquipSpacePanel.setLayout(null);
		EquipSpacePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		EquipSpacePanel.setBounds(6, 23, 203, 114);
		panel_9.add(EquipSpacePanel);
		JLabel lblEquipmentStorageSpace = new JLabel("Equipment storage space");
		lblEquipmentStorageSpace.setBounds(23, 0, 158, 16);
		EquipSpacePanel.add(lblEquipmentStorageSpace);
		JLabel label_253 = new JLabel("Temperature:");
		label_253.setBounds(6, 20, 88, 16);
		EquipSpacePanel.add(label_253);
		 equipSpace_temperature = new JLabel("72.0");
		equipSpace_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		equipSpace_temperature.setBounds(96, 20, 36, 16);
		EquipSpacePanel.add(equipSpace_temperature);
		JLabel label_255 = new JLabel("F");
		label_255.setBounds(139, 20, 16, 16);
		EquipSpacePanel.add(label_255);
		JLabel label_256 = new JLabel("Humidity:");
		label_256.setBounds(6, 35, 88, 16);
		EquipSpacePanel.add(label_256);
		 equipSpace_humidity = new JLabel("45.0");
		equipSpace_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		equipSpace_humidity.setBounds(96, 35, 36, 16);
		EquipSpacePanel.add(equipSpace_humidity);
		JLabel label_258 = new JLabel("%");
		label_258.setBounds(139, 35, 16, 16);
		EquipSpacePanel.add(label_258);
		JLabel label_259 = new JLabel("Oxygen Level:");
		label_259.setBounds(6, 50, 88, 16);
		EquipSpacePanel.add(label_259);
		 equipSpace_oxygen = new JLabel("21.0");
		equipSpace_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		equipSpace_oxygen.setBounds(96, 50, 36, 16);
		EquipSpacePanel.add(equipSpace_oxygen);
		JLabel label_261 = new JLabel("%");
		label_261.setBounds(139, 50, 16, 16);
		EquipSpacePanel.add(label_261);
		JLabel label_262 = new JLabel("Pressure:");
		label_262.setBounds(6, 65, 88, 16);
		EquipSpacePanel.add(label_262);
		 equipSpace_pressure = new JLabel("14.5");
		equipSpace_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		equipSpace_pressure.setBounds(96, 65, 36, 16);
		EquipSpacePanel.add(equipSpace_pressure);
		JLabel label_264 = new JLabel("psi");
		label_264.setBounds(139, 65, 23, 16);
		EquipSpacePanel.add(label_264);
		JLabel label_265 = new JLabel("Smoke:");
		label_265.setBounds(6, 80, 88, 16);
		EquipSpacePanel.add(label_265);
		 equipSpace_smoke = new JLabel("0");
		equipSpace_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		equipSpace_smoke.setBounds(96, 80, 36, 16);
		EquipSpacePanel.add(equipSpace_smoke);
		JLabel label_267 = new JLabel("Gas:");
		label_267.setBounds(6, 95, 88, 16);
		EquipSpacePanel.add(label_267);
		 equipSpace_gas = new JLabel("0");
		equipSpace_gas.setHorizontalAlignment(SwingConstants.CENTER);
		equipSpace_gas.setBounds(96, 95, 36, 16);
		EquipSpacePanel.add(equipSpace_gas);
		 equipSpace_light = new JLabel(flash);
		equipSpace_light.setBounds(167, 40, 30, 30);
		EquipSpacePanel.add(equipSpace_light);
		JPanel WellAreaPanel = new JPanel();
		WellAreaPanel.setLayout(null);
		WellAreaPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		WellAreaPanel.setBounds(243, 23, 203, 114);
		panel_9.add(WellAreaPanel);
		JLabel lblBelowSurfaceWell = new JLabel("Below surface well area");
		lblBelowSurfaceWell.setBounds(29, 0, 153, 16);
		WellAreaPanel.add(lblBelowSurfaceWell);
		JLabel label_271 = new JLabel("Temperature:");
		label_271.setBounds(6, 20, 88, 16);
		WellAreaPanel.add(label_271);
		 wellArea_temperature = new JLabel("35.0");
		wellArea_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		wellArea_temperature.setBounds(96, 20, 36, 16);
		WellAreaPanel.add(wellArea_temperature);
		JLabel label_273 = new JLabel("F");
		label_273.setBounds(139, 20, 16, 16);
		WellAreaPanel.add(label_273);
		JLabel label_274 = new JLabel("Humidity:");
		label_274.setBounds(6, 35, 88, 16);
		WellAreaPanel.add(label_274);
		 wellArea_humidity = new JLabel("75.0");
		wellArea_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		wellArea_humidity.setBounds(96, 35, 36, 16);
		WellAreaPanel.add(wellArea_humidity);
		JLabel label_276 = new JLabel("%");
		label_276.setBounds(139, 35, 16, 16);
		WellAreaPanel.add(label_276);
		JLabel label_277 = new JLabel("Oxygen Level:");
		label_277.setBounds(6, 50, 88, 16);
		WellAreaPanel.add(label_277);
		 wellArea_oxygen = new JLabel("21.0");
		wellArea_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		wellArea_oxygen.setBounds(96, 50, 36, 16);
		WellAreaPanel.add(wellArea_oxygen);
		JLabel label_279 = new JLabel("%");
		label_279.setBounds(139, 50, 16, 16);
		WellAreaPanel.add(label_279);
		JLabel label_280 = new JLabel("Pressure:");
		label_280.setBounds(6, 65, 88, 16);
		WellAreaPanel.add(label_280);
		 wellArea_pressure = new JLabel("14.5");
		wellArea_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		wellArea_pressure.setBounds(96, 65, 36, 16);
		WellAreaPanel.add(wellArea_pressure);
		JLabel label_282 = new JLabel("psi");
		label_282.setBounds(139, 65, 23, 16);
		WellAreaPanel.add(label_282);
		JLabel label_283 = new JLabel("Smoke:");
		label_283.setBounds(6, 80, 88, 16);
		WellAreaPanel.add(label_283);
		 wellArea_smoke = new JLabel("0");
		wellArea_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		wellArea_smoke.setBounds(96, 80, 36, 16);
		WellAreaPanel.add(wellArea_smoke);
		JLabel label_285 = new JLabel("Gas:");
		label_285.setBounds(6, 95, 88, 16);
		WellAreaPanel.add(label_285);
		 wellArea_gas = new JLabel("0");
		wellArea_gas.setHorizontalAlignment(SwingConstants.CENTER);
		wellArea_gas.setBounds(96, 95, 36, 16);
		WellAreaPanel.add(wellArea_gas);
		 wellArea_light = new JLabel(flash);
		wellArea_light.setBounds(167, 40, 30, 30);
		WellAreaPanel.add(wellArea_light);
		JPanel heatingAreaPanel = new JPanel();
		heatingAreaPanel.setLayout(null);
		heatingAreaPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		heatingAreaPanel.setBounds(6, 140, 203, 114);
		panel_9.add(heatingAreaPanel);
		JLabel lblBelowSurfaceHeating = new JLabel("Below heating and cooling area");
		lblBelowSurfaceHeating.setBounds(0, 0, 197, 16);
		heatingAreaPanel.add(lblBelowSurfaceHeating);
		JLabel label_289 = new JLabel("Temperature:");
		label_289.setBounds(6, 20, 88, 16);
		heatingAreaPanel.add(label_289);
		 heatingArea_temperature = new JLabel("90.0");
		heatingArea_temperature.setHorizontalAlignment(SwingConstants.CENTER);
		heatingArea_temperature.setBounds(96, 20, 36, 16);
		heatingAreaPanel.add(heatingArea_temperature);
		JLabel label_291 = new JLabel("F");
		label_291.setBounds(139, 20, 16, 16);
		heatingAreaPanel.add(label_291);
		JLabel label_292 = new JLabel("Humidity:");
		label_292.setBounds(6, 35, 88, 16);
		heatingAreaPanel.add(label_292);
		 heatingArea_humidity = new JLabel("45.0");
		heatingArea_humidity.setHorizontalAlignment(SwingConstants.CENTER);
		heatingArea_humidity.setBounds(96, 35, 36, 16);
		heatingAreaPanel.add(heatingArea_humidity);
		JLabel label_294 = new JLabel("%");
		label_294.setBounds(139, 35, 16, 16);
		heatingAreaPanel.add(label_294);
		JLabel label_295 = new JLabel("Oxygen Level:");
		label_295.setBounds(6, 50, 88, 16);
		heatingAreaPanel.add(label_295);
		 heatingArea_oxygen = new JLabel("21.0");
		heatingArea_oxygen.setHorizontalAlignment(SwingConstants.CENTER);
		heatingArea_oxygen.setBounds(96, 50, 36, 16);
		heatingAreaPanel.add(heatingArea_oxygen);
		JLabel label_297 = new JLabel("%");
		label_297.setBounds(139, 50, 16, 16);
		heatingAreaPanel.add(label_297);
		JLabel label_298 = new JLabel("Pressure:");
		label_298.setBounds(6, 65, 88, 16);
		heatingAreaPanel.add(label_298);
		 heatingArea_pressure = new JLabel("14.5");
		heatingArea_pressure.setHorizontalAlignment(SwingConstants.CENTER);
		heatingArea_pressure.setBounds(96, 65, 36, 16);
		heatingAreaPanel.add(heatingArea_pressure);
		JLabel label_300 = new JLabel("psi");
		label_300.setBounds(139, 65, 23, 16);
		heatingAreaPanel.add(label_300);
		JLabel label_301 = new JLabel("Smoke:");
		label_301.setBounds(6, 80, 88, 16);
		heatingAreaPanel.add(label_301);
		 heatingArea_smoke = new JLabel("0");
		heatingArea_smoke.setHorizontalAlignment(SwingConstants.CENTER);
		heatingArea_smoke.setBounds(96, 80, 36, 16);
		heatingAreaPanel.add(heatingArea_smoke);
		JLabel label_303 = new JLabel("Gas:");
		label_303.setBounds(6, 95, 88, 16);
		heatingAreaPanel.add(label_303);
		 heatingArea_gas = new JLabel("0");
		heatingArea_gas.setHorizontalAlignment(SwingConstants.CENTER);
		heatingArea_gas.setBounds(96, 95, 36, 16);
		heatingAreaPanel.add(heatingArea_gas);
		 heatingArea_light = new JLabel(flash);
		heatingArea_light.setBounds(167, 40, 30, 30);
		heatingAreaPanel.add(heatingArea_light);
		JPanel ExternalWallPanel = new JPanel();
		ExternalWallPanel.setLayout(null);
		ExternalWallPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ExternalWallPanel.setBounds(243, 140, 203, 114);
		panel_9.add(ExternalWallPanel);
		JLabel lblExternalwallMonitor = new JLabel("ExternalWall Monitor");
		lblExternalwallMonitor.setBounds(32, 0, 130, 16);
		ExternalWallPanel.add(lblExternalwallMonitor);
		JLabel lblExternalWallTemperature = new JLabel("External Wall Temperature:");
		lblExternalWallTemperature.setBounds(6, 20, 191, 16);
		ExternalWallPanel.add(lblExternalWallTemperature);
		 externalWall_extemperture = new JLabel("28.0");
		externalWall_extemperture.setHorizontalAlignment(SwingConstants.RIGHT);
		externalWall_extemperture.setBounds(16, 36, 73, 16);
		ExternalWallPanel.add(externalWall_extemperture);
		JLabel label_309 = new JLabel("F");
		label_309.setBounds(96, 36, 16, 16);
		ExternalWallPanel.add(label_309);
		JLabel lblOutsideTemperature = new JLabel("Outside Temperature:");
		lblOutsideTemperature.setBounds(6, 64, 191, 16);
		ExternalWallPanel.add(lblOutsideTemperature);
		 externalWall_outsideTemp = new JLabel("-243.0");
		externalWall_outsideTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		externalWall_outsideTemp.setBounds(32, 81, 57, 16);
		ExternalWallPanel.add(externalWall_outsideTemp);
		 externalWall_light = new JLabel(flash);
		externalWall_light.setBounds(167, 40, 30, 30);
		ExternalWallPanel.add(externalWall_light);
		JLabel label_247 = new JLabel("F");
		label_247.setBounds(96, 81, 16, 16);
		ExternalWallPanel.add(label_247);
		JButton resetButton = new JButton("Reset Alarm");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alarm.close();
			}
		});
		resetButton.setBounds(376, 722, 160, 29);
		displayFrame.getContentPane().add(resetButton);
		
		JButton btnRepairAll = new JButton("Repair All");
		btnRepairAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					
					@Override
					public void run() {
						if (staticInfo.livingCell1.getErrorMessage().equals("NORMAL") && staticInfo.livingCell2.getErrorMessage().equals("NORMAL") && 
							staticInfo.livingCell3.getErrorMessage().equals("NORMAL") && staticInfo.livingCell4.getErrorMessage().equals("NORMAL") &&
							staticInfo.livingCell5.getErrorMessage().equals("NORMAL") && staticInfo.livingCell6.getErrorMessage().equals("NORMAL") &&
							staticInfo.livingCell7.getErrorMessage().equals("NORMAL") && staticInfo.livingCell8.getErrorMessage().equals("NORMAL") &&
							staticInfo.livingCell9.getErrorMessage().equals("NORMAL") && staticInfo.livingCell10.getErrorMessage().equals("NORMAL") &&
							staticInfo.livingCell11.getErrorMessage().equals("NORMAL") && staticInfo.livingCell12.getErrorMessage().equals("NORMAL") &&
							staticInfo.livingCell13.getErrorMessage().equals("NORMAL") && staticInfo.livingCell14.getErrorMessage().equals("NORMAL") &&
							staticInfo.livingCell15.getErrorMessage().equals("NORMAL") && staticInfo.commandSpace1.getErrorMessage().equals("NORMAL") &&
							staticInfo.commandSpace2.getErrorMessage().equals("NORMAL") && staticInfo.labSpace1.getErrorMessage().equals("NORMAL") &&
							staticInfo.labSpace2.getErrorMessage().equals("NORMAL") && staticInfo.labSpace3.getErrorMessage().equals("NORMAL") &&
							staticInfo.labSpace4.getErrorMessage().equals("NORMAL") && staticInfo.equipSpace.getErrorMessage().equals("NORMAL")&&
							staticInfo.wellArea.getErrorMessage().equals("NORMAL") && staticInfo.heatingArea.getErrorMessage().equals("NORMAL") &&
							staticInfo.externalWall.getErrorMessage().equals("NORMAL") ){
							
							timer.cancel();
							JOptionPane.showMessageDialog(null, "Robot Finish The Repairing Task", "Repairing Finished", JOptionPane.WARNING_MESSAGE);				
							
						}
						else {
							robotRepair();
						}
						
					}
				}, 0, 3000);
				
				
			}
		});
		btnRepairAll.setBounds(662, 722, 117, 29);
		displayFrame.getContentPane().add(btnRepairAll);
		
		
		
		
	}
	public void robotRepair() {
		
		int lightWidth = 30;
		int lightHeight = 30;
		if (LoginGUI.mframe.alarm.flag == 1) {
			WorkerRunnable.alarmShouldStop();
		}		
		if (!staticInfo.livingCell1.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell1 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell1_temperature.setText(Double
					.toString(staticInfo.livingCell1.getTemperature()));
			LoginGUI.mframe.livingCell1_humidity.setText(Double
					.toString(staticInfo.livingCell1.getHumidity()));
			LoginGUI.mframe.livingCell1_oxygen.setText(Double
					.toString(staticInfo.livingCell1.getOxygenLevel()));
			LoginGUI.mframe.livingCell1_pressure.setText(Double
					.toString(staticInfo.livingCell1.getPressure()));
			LoginGUI.mframe.livingCell1_smoke.setText(Double
					.toString(staticInfo.livingCell1.getSmokeDetection()));
			LoginGUI.mframe.livingCell1_gas.setText(Double
					.toString(staticInfo.livingCell1.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell1_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell1_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell1_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell1_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell1_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell1_gas.setForeground(Color.black);
		
		}
		else if (!staticInfo.livingCell2.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell2 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell2_temperature.setText(Double
					.toString(staticInfo.livingCell2.getTemperature()));
			LoginGUI.mframe.livingCell2_humidity.setText(Double
					.toString(staticInfo.livingCell2.getHumidity()));
			LoginGUI.mframe.livingCell2_oxygen.setText(Double
					.toString(staticInfo.livingCell2.getOxygenLevel()));
			LoginGUI.mframe.livingCell2_pressure.setText(Double
					.toString(staticInfo.livingCell2.getPressure()));
			LoginGUI.mframe.livingCell2_smoke.setText(Double
					.toString(staticInfo.livingCell2.getSmokeDetection()));
			LoginGUI.mframe.livingCell2_gas.setText(Double
					.toString(staticInfo.livingCell2.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell2_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell2_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell2_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell2_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell2_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell2_gas.setForeground(Color.black);
			
		}
		else if (!staticInfo.livingCell3.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell3 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell3_temperature.setText(Double
					.toString(staticInfo.livingCell3.getTemperature()));
			LoginGUI.mframe.livingCell3_humidity.setText(Double
					.toString(staticInfo.livingCell3.getHumidity()));
			LoginGUI.mframe.livingCell3_oxygen.setText(Double
					.toString(staticInfo.livingCell3.getOxygenLevel()));
			LoginGUI.mframe.livingCell3_pressure.setText(Double
					.toString(staticInfo.livingCell3.getPressure()));
			LoginGUI.mframe.livingCell3_smoke.setText(Double
					.toString(staticInfo.livingCell3.getSmokeDetection()));
			LoginGUI.mframe.livingCell3_gas.setText(Double
					.toString(staticInfo.livingCell3.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell3_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell3_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell3_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell3_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell3_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell3_gas.setForeground(Color.black);
			
		}
		else if (!staticInfo.livingCell4.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell4 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell4_temperature.setText(Double
					.toString(staticInfo.livingCell4.getTemperature()));
			LoginGUI.mframe.livingCell4_humidity.setText(Double
					.toString(staticInfo.livingCell4.getHumidity()));
			LoginGUI.mframe.livingCell4_oxygen.setText(Double
					.toString(staticInfo.livingCell4.getOxygenLevel()));
			LoginGUI.mframe.livingCell4_pressure.setText(Double
					.toString(staticInfo.livingCell4.getPressure()));
			LoginGUI.mframe.livingCell4_smoke.setText(Double
					.toString(staticInfo.livingCell4.getSmokeDetection()));
			LoginGUI.mframe.livingCell4_gas.setText(Double
					.toString(staticInfo.livingCell4.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell4_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell4_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell4_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell4_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell4_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell4_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell5.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell5 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell5_temperature.setText(Double
					.toString(staticInfo.livingCell5.getTemperature()));
			LoginGUI.mframe.livingCell5_humidity.setText(Double
					.toString(staticInfo.livingCell5.getHumidity()));
			LoginGUI.mframe.livingCell5_oxygen.setText(Double
					.toString(staticInfo.livingCell5.getOxygenLevel()));
			LoginGUI.mframe.livingCell5_pressure.setText(Double
					.toString(staticInfo.livingCell5.getPressure()));
			LoginGUI.mframe.livingCell5_smoke.setText(Double
					.toString(staticInfo.livingCell5.getSmokeDetection()));
			LoginGUI.mframe.livingCell5_gas.setText(Double
					.toString(staticInfo.livingCell5.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell5_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell5_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell5_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell5_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell5_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell5_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell6.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell6 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell6_temperature.setText(Double
					.toString(staticInfo.livingCell6.getTemperature()));
			LoginGUI.mframe.livingCell6_humidity.setText(Double
					.toString(staticInfo.livingCell6.getHumidity()));
			LoginGUI.mframe.livingCell6_oxygen.setText(Double
					.toString(staticInfo.livingCell6.getOxygenLevel()));
			LoginGUI.mframe.livingCell6_pressure.setText(Double
					.toString(staticInfo.livingCell6.getPressure()));
			LoginGUI.mframe.livingCell6_smoke.setText(Double
					.toString(staticInfo.livingCell6.getSmokeDetection()));
			LoginGUI.mframe.livingCell6_gas.setText(Double
					.toString(staticInfo.livingCell6.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell6_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell6_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell6_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell6_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell6_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell6_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell7.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell7 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell7_temperature.setText(Double
					.toString(staticInfo.livingCell7.getTemperature()));
			LoginGUI.mframe.livingCell7_humidity.setText(Double
					.toString(staticInfo.livingCell7.getHumidity()));
			LoginGUI.mframe.livingCell7_oxygen.setText(Double
					.toString(staticInfo.livingCell7.getOxygenLevel()));
			LoginGUI.mframe.livingCell7_pressure.setText(Double
					.toString(staticInfo.livingCell7.getPressure()));
			LoginGUI.mframe.livingCell7_smoke.setText(Double
					.toString(staticInfo.livingCell7.getSmokeDetection()));
			LoginGUI.mframe.livingCell7_gas.setText(Double
					.toString(staticInfo.livingCell7.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell7_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell7_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell7_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell7_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell7_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell7_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell8.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell8 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell8_temperature.setText(Double
					.toString(staticInfo.livingCell8.getTemperature()));
			LoginGUI.mframe.livingCell8_humidity.setText(Double
					.toString(staticInfo.livingCell8.getHumidity()));
			LoginGUI.mframe.livingCell8_oxygen.setText(Double
					.toString(staticInfo.livingCell8.getOxygenLevel()));
			LoginGUI.mframe.livingCell8_pressure.setText(Double
					.toString(staticInfo.livingCell8.getPressure()));
			LoginGUI.mframe.livingCell8_smoke.setText(Double
					.toString(staticInfo.livingCell8.getSmokeDetection()));
			LoginGUI.mframe.livingCell8_gas.setText(Double
					.toString(staticInfo.livingCell8.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell8_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell8_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell8_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell8_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell8_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell8_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell9.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell9 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell9_temperature.setText(Double
					.toString(staticInfo.livingCell9.getTemperature()));
			LoginGUI.mframe.livingCell9_humidity.setText(Double
					.toString(staticInfo.livingCell9.getHumidity()));
			LoginGUI.mframe.livingCell9_oxygen.setText(Double
					.toString(staticInfo.livingCell9.getOxygenLevel()));
			LoginGUI.mframe.livingCell9_pressure.setText(Double
					.toString(staticInfo.livingCell9.getPressure()));
			LoginGUI.mframe.livingCell9_smoke.setText(Double
					.toString(staticInfo.livingCell9.getSmokeDetection()));
			LoginGUI.mframe.livingCell9_gas.setText(Double
					.toString(staticInfo.livingCell9.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell9_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell9_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell9_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell9_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell9_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell9_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell10.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell10 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell10_temperature.setText(Double
					.toString(staticInfo.livingCell10.getTemperature()));
			LoginGUI.mframe.livingCell10_humidity.setText(Double
					.toString(staticInfo.livingCell10.getHumidity()));
			LoginGUI.mframe.livingCell10_oxygen.setText(Double
					.toString(staticInfo.livingCell10.getOxygenLevel()));
			LoginGUI.mframe.livingCell10_pressure.setText(Double
					.toString(staticInfo.livingCell10.getPressure()));
			LoginGUI.mframe.livingCell10_smoke.setText(Double
					.toString(staticInfo.livingCell10.getSmokeDetection()));
			LoginGUI.mframe.livingCell10_gas.setText(Double
					.toString(staticInfo.livingCell10.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell10_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell10_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell10_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell10_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell10_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell10_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell11.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell11 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell11_temperature.setText(Double
					.toString(staticInfo.livingCell11.getTemperature()));
			LoginGUI.mframe.livingCell11_humidity.setText(Double
					.toString(staticInfo.livingCell11.getHumidity()));
			LoginGUI.mframe.livingCell11_oxygen.setText(Double
					.toString(staticInfo.livingCell11.getOxygenLevel()));
			LoginGUI.mframe.livingCell11_pressure.setText(Double
					.toString(staticInfo.livingCell11.getPressure()));
			LoginGUI.mframe.livingCell11_smoke.setText(Double
					.toString(staticInfo.livingCell11.getSmokeDetection()));
			LoginGUI.mframe.livingCell11_gas.setText(Double
					.toString(staticInfo.livingCell11.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell11_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell11_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell11_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell11_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell11_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell11_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell12.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell12 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell12_temperature.setText(Double
					.toString(staticInfo.livingCell12.getTemperature()));
			LoginGUI.mframe.livingCell12_humidity.setText(Double
					.toString(staticInfo.livingCell12.getHumidity()));
			LoginGUI.mframe.livingCell12_oxygen.setText(Double
					.toString(staticInfo.livingCell12.getOxygenLevel()));
			LoginGUI.mframe.livingCell12_pressure.setText(Double
					.toString(staticInfo.livingCell12.getPressure()));
			LoginGUI.mframe.livingCell12_smoke.setText(Double
					.toString(staticInfo.livingCell12.getSmokeDetection()));
			LoginGUI.mframe.livingCell12_gas.setText(Double
					.toString(staticInfo.livingCell12.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell12_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell12_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell12_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell12_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell12_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell12_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell13.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell13 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell13_temperature.setText(Double
					.toString(staticInfo.livingCell13.getTemperature()));
			LoginGUI.mframe.livingCell13_humidity.setText(Double
					.toString(staticInfo.livingCell13.getHumidity()));
			LoginGUI.mframe.livingCell13_oxygen.setText(Double
					.toString(staticInfo.livingCell13.getOxygenLevel()));
			LoginGUI.mframe.livingCell13_pressure.setText(Double
					.toString(staticInfo.livingCell13.getPressure()));
			LoginGUI.mframe.livingCell13_smoke.setText(Double
					.toString(staticInfo.livingCell13.getSmokeDetection()));
			LoginGUI.mframe.livingCell13_gas.setText(Double
					.toString(staticInfo.livingCell13.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell13_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell13_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell13_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell13_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell13_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell13_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell14.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell14 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell14_temperature.setText(Double
					.toString(staticInfo.livingCell14.getTemperature()));
			LoginGUI.mframe.livingCell14_humidity.setText(Double
					.toString(staticInfo.livingCell14.getHumidity()));
			LoginGUI.mframe.livingCell14_oxygen.setText(Double
					.toString(staticInfo.livingCell14.getOxygenLevel()));
			LoginGUI.mframe.livingCell14_pressure.setText(Double
					.toString(staticInfo.livingCell14.getPressure()));
			LoginGUI.mframe.livingCell14_smoke.setText(Double
					.toString(staticInfo.livingCell14.getSmokeDetection()));
			LoginGUI.mframe.livingCell14_gas.setText(Double
					.toString(staticInfo.livingCell14.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell14_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell14_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell14_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell14_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell14_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell14_gas.setForeground(Color.black);
		}
		else if (!staticInfo.livingCell15.getErrorMessage().equals("NORMAL")) {
			staticInfo.livingCell15 = new livingCell(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.livingCell15_temperature.setText(Double
					.toString(staticInfo.livingCell15.getTemperature()));
			LoginGUI.mframe.livingCell15_humidity.setText(Double
					.toString(staticInfo.livingCell15.getHumidity()));
			LoginGUI.mframe.livingCell15_oxygen.setText(Double
					.toString(staticInfo.livingCell15.getOxygenLevel()));
			LoginGUI.mframe.livingCell15_pressure.setText(Double
					.toString(staticInfo.livingCell15.getPressure()));
			LoginGUI.mframe.livingCell15_smoke.setText(Double
					.toString(staticInfo.livingCell15.getSmokeDetection()));
			LoginGUI.mframe.livingCell15_gas.setText(Double
					.toString(staticInfo.livingCell15.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.livingCell15_light.setIcon(flash);
			
			LoginGUI.mframe.livingCell15_temperature.setForeground(Color.black);
			LoginGUI.mframe.livingCell15_humidity.setForeground(Color.black);
			LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.black);
			LoginGUI.mframe.livingCell15_pressure.setForeground(Color.black);
			LoginGUI.mframe.livingCell15_smoke.setForeground(Color.black);
			LoginGUI.mframe.livingCell15_gas.setForeground(Color.black);
		}
		else if (!staticInfo.commandSpace1.getErrorMessage().equals("NORMAL")) {
			staticInfo.commandSpace1 = new commandSpace(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.command1_temperature.setText(Double
					.toString(staticInfo.commandSpace1.getTemperature()));
			LoginGUI.mframe.command1_humidity.setText(Double
					.toString(staticInfo.commandSpace1.getHumidity()));
			LoginGUI.mframe.command1_oxygen.setText(Double
					.toString(staticInfo.commandSpace1.getOxygenLevel()));
			LoginGUI.mframe.command1_pressure.setText(Double
					.toString(staticInfo.commandSpace1.getPressure()));
			LoginGUI.mframe.command1_smoke.setText(Double
					.toString(staticInfo.commandSpace1.getSmokeDetection()));
			LoginGUI.mframe.command1_gas.setText(Double
					.toString(staticInfo.commandSpace1.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.command1_light.setIcon(flash);
			
			LoginGUI.mframe.command1_temperature.setForeground(Color.black);
			LoginGUI.mframe.command1_humidity.setForeground(Color.black);
			LoginGUI.mframe.command1_oxygen.setForeground(Color.black);
			LoginGUI.mframe.command1_pressure.setForeground(Color.black);
			LoginGUI.mframe.command1_smoke.setForeground(Color.black);
			LoginGUI.mframe.command1_gas.setForeground(Color.black);
			
		}
		else if (!staticInfo.commandSpace2.getErrorMessage().equals("NORMAL")) {
			staticInfo.commandSpace2 = new commandSpace(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.command2_temperature.setText(Double
					.toString(staticInfo.commandSpace2.getTemperature()));
			LoginGUI.mframe.command2_humidity.setText(Double
					.toString(staticInfo.commandSpace2.getHumidity()));
			LoginGUI.mframe.command2_oxygen.setText(Double
					.toString(staticInfo.commandSpace2.getOxygenLevel()));
			LoginGUI.mframe.command2_pressure.setText(Double
					.toString(staticInfo.commandSpace2.getPressure()));
			LoginGUI.mframe.command2_smoke.setText(Double
					.toString(staticInfo.commandSpace2.getSmokeDetection()));
			LoginGUI.mframe.command2_gas.setText(Double
					.toString(staticInfo.commandSpace2.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.command2_light.setIcon(flash);
			
			LoginGUI.mframe.command2_temperature.setForeground(Color.black);
			LoginGUI.mframe.command2_humidity.setForeground(Color.black);
			LoginGUI.mframe.command2_oxygen.setForeground(Color.black);
			LoginGUI.mframe.command2_pressure.setForeground(Color.black);
			LoginGUI.mframe.command2_smoke.setForeground(Color.black);
			LoginGUI.mframe.command2_gas.setForeground(Color.black);
		}
		else if (!staticInfo.labSpace1.getErrorMessage().equals("NORMAL")) {
			staticInfo.labSpace1 = new labSpace(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.labSpace1_temperature.setText(Double
					.toString(staticInfo.labSpace1.getTemperature()));
			LoginGUI.mframe.labSpace1_humidity.setText(Double
					.toString(staticInfo.labSpace1.getHumidity()));
			LoginGUI.mframe.labSpace1_oxygen.setText(Double
					.toString(staticInfo.labSpace1.getOxygenLevel()));
			LoginGUI.mframe.labSpace1_pressure.setText(Double
					.toString(staticInfo.labSpace1.getPressure()));
			LoginGUI.mframe.labSpace1_smoke.setText(Double
					.toString(staticInfo.labSpace1.getSmokeDetection()));
			LoginGUI.mframe.labSpace1_gas.setText(Double
					.toString(staticInfo.labSpace1.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.labSpace1_light.setIcon(flash);
			
			LoginGUI.mframe.labSpace1_temperature.setForeground(Color.black);
			LoginGUI.mframe.labSpace1_humidity.setForeground(Color.black);
			LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.black);
			LoginGUI.mframe.labSpace1_pressure.setForeground(Color.black);
			LoginGUI.mframe.labSpace1_smoke.setForeground(Color.black);
			LoginGUI.mframe.labSpace1_gas.setForeground(Color.black);
			
		}
		else if (!staticInfo.labSpace2.getErrorMessage().equals("NORMAL")) {
			staticInfo.labSpace2 = new labSpace(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.labSpace2_temperature.setText(Double
					.toString(staticInfo.labSpace2.getTemperature()));
			LoginGUI.mframe.labSpace2_humidity.setText(Double
					.toString(staticInfo.labSpace2.getHumidity()));
			LoginGUI.mframe.labSpace2_oxygen.setText(Double
					.toString(staticInfo.labSpace2.getOxygenLevel()));
			LoginGUI.mframe.labSpace2_pressure.setText(Double
					.toString(staticInfo.labSpace2.getPressure()));
			LoginGUI.mframe.labSpace2_smoke.setText(Double
					.toString(staticInfo.labSpace2.getSmokeDetection()));
			LoginGUI.mframe.labSpace2_gas.setText(Double
					.toString(staticInfo.labSpace2.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.labSpace2_light.setIcon(flash);
			
			LoginGUI.mframe.labSpace2_temperature.setForeground(Color.black);
			LoginGUI.mframe.labSpace2_humidity.setForeground(Color.black);
			LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.black);
			LoginGUI.mframe.labSpace2_pressure.setForeground(Color.black);
			LoginGUI.mframe.labSpace2_smoke.setForeground(Color.black);
			LoginGUI.mframe.labSpace2_gas.setForeground(Color.black);
		}
		else if (!staticInfo.labSpace3.getErrorMessage().equals("NORMAL")) {
			staticInfo.labSpace3 = new labSpace(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.labSpace3_temperature.setText(Double
					.toString(staticInfo.labSpace3.getTemperature()));
			LoginGUI.mframe.labSpace3_humidity.setText(Double
					.toString(staticInfo.labSpace3.getHumidity()));
			LoginGUI.mframe.labSpace3_oxygen.setText(Double
					.toString(staticInfo.labSpace3.getOxygenLevel()));
			LoginGUI.mframe.labSpace3_pressure.setText(Double
					.toString(staticInfo.labSpace3.getPressure()));
			LoginGUI.mframe.labSpace3_smoke.setText(Double
					.toString(staticInfo.labSpace3.getSmokeDetection()));
			LoginGUI.mframe.labSpace3_gas.setText(Double
					.toString(staticInfo.labSpace3.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.labSpace3_light.setIcon(flash);
			
			LoginGUI.mframe.labSpace3_temperature.setForeground(Color.black);
			LoginGUI.mframe.labSpace3_humidity.setForeground(Color.black);
			LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.black);
			LoginGUI.mframe.labSpace3_pressure.setForeground(Color.black);
			LoginGUI.mframe.labSpace3_smoke.setForeground(Color.black);
			LoginGUI.mframe.labSpace3_gas.setForeground(Color.black);
		}
		else if (!staticInfo.labSpace4.getErrorMessage().equals("NORMAL")) {
			staticInfo.labSpace4 = new labSpace(72.0, 45.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.labSpace4_temperature.setText(Double
					.toString(staticInfo.labSpace4.getTemperature()));
			LoginGUI.mframe.labSpace4_humidity.setText(Double
					.toString(staticInfo.labSpace4.getHumidity()));
			LoginGUI.mframe.labSpace4_oxygen.setText(Double
					.toString(staticInfo.labSpace4.getOxygenLevel()));
			LoginGUI.mframe.labSpace4_pressure.setText(Double
					.toString(staticInfo.labSpace4.getPressure()));
			LoginGUI.mframe.labSpace4_smoke.setText(Double
					.toString(staticInfo.labSpace4.getSmokeDetection()));
			LoginGUI.mframe.labSpace4_gas.setText(Double
					.toString(staticInfo.labSpace4.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.labSpace4_light.setIcon(flash);
			
			LoginGUI.mframe.labSpace4_temperature.setForeground(Color.black);
			LoginGUI.mframe.labSpace4_humidity.setForeground(Color.black);
			LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.black);
			LoginGUI.mframe.labSpace4_pressure.setForeground(Color.black);
			LoginGUI.mframe.labSpace4_smoke.setForeground(Color.black);
			LoginGUI.mframe.labSpace4_gas.setForeground(Color.black);
		}
		else if (!staticInfo.equipSpace.getErrorMessage().equals("NORMAL")) {
			staticInfo.equipSpace = new equipStorageSpace(72.0,
					45.0, 21.0, 14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.equipSpace_temperature.setText(Double
					.toString(staticInfo.equipSpace.getTemperature()));
			LoginGUI.mframe.equipSpace_humidity.setText(Double
					.toString(staticInfo.equipSpace.getHumidity()));
			LoginGUI.mframe.equipSpace_oxygen.setText(Double
					.toString(staticInfo.equipSpace.getOxygenLevel()));
			LoginGUI.mframe.equipSpace_pressure.setText(Double
					.toString(staticInfo.equipSpace.getPressure()));
			LoginGUI.mframe.equipSpace_smoke.setText(Double
					.toString(staticInfo.equipSpace.getSmokeDetection()));
			LoginGUI.mframe.equipSpace_gas.setText(Double
					.toString(staticInfo.equipSpace.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.equipSpace_light.setIcon(flash);
			
			LoginGUI.mframe.equipSpace_temperature.setForeground(Color.black);
			LoginGUI.mframe.equipSpace_humidity.setForeground(Color.black);
			LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.black);
			LoginGUI.mframe.equipSpace_pressure.setForeground(Color.black);
			LoginGUI.mframe.equipSpace_smoke.setForeground(Color.black);
			LoginGUI.mframe.equipSpace_gas.setForeground(Color.black);
		}
		else if (!staticInfo.heatingArea.getErrorMessage().equals("NORMAL")) {
			staticInfo.heatingArea = new belowHeatingArea(90.0,
						45.0, 21.0, 14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.heatingArea_temperature.setText(Double
					.toString(staticInfo.heatingArea.getTemperature()));
			LoginGUI.mframe.heatingArea_humidity.setText(Double
					.toString(staticInfo.heatingArea.getHumidity()));
			LoginGUI.mframe.heatingArea_oxygen.setText(Double
					.toString(staticInfo.heatingArea.getOxygenLevel()));
			LoginGUI.mframe.heatingArea_pressure.setText(Double
					.toString(staticInfo.heatingArea.getPressure()));
			LoginGUI.mframe.heatingArea_smoke.setText(Double
					.toString(staticInfo.heatingArea.getSmokeDetection()));
			LoginGUI.mframe.heatingArea_gas.setText(Double
					.toString(staticInfo.heatingArea.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.heatingArea_light.setIcon(flash);
			
			LoginGUI.mframe.heatingArea_temperature.setForeground(Color.black);
			LoginGUI.mframe.heatingArea_humidity.setForeground(Color.black);
			LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.black);
			LoginGUI.mframe.heatingArea_pressure.setForeground(Color.black);
			LoginGUI.mframe.heatingArea_smoke.setForeground(Color.black);
			LoginGUI.mframe.heatingArea_gas.setForeground(Color.black);
		}
		else if (!staticInfo.wellArea.getErrorMessage().equals("NORMAL")) {
			staticInfo.wellArea = new belowWellArea(35.0, 75.0, 21.0,
					14.5, 0, 0, "NORMAL");
			LoginGUI.mframe.wellArea_temperature.setText(Double
					.toString(staticInfo.wellArea.getTemperature()));
			LoginGUI.mframe.wellArea_humidity.setText(Double
					.toString(staticInfo.wellArea.getHumidity()));
			LoginGUI.mframe.wellArea_oxygen.setText(Double
					.toString(staticInfo.wellArea.getOxygenLevel()));
			LoginGUI.mframe.wellArea_pressure.setText(Double
					.toString(staticInfo.wellArea.getPressure()));
			LoginGUI.mframe.wellArea_smoke.setText(Double
					.toString(staticInfo.wellArea.getSmokeDetection()));
			LoginGUI.mframe.wellArea_gas.setText(Double
					.toString(staticInfo.wellArea.getGasDetection()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.wellArea_light.setIcon(flash);
			
			LoginGUI.mframe.wellArea_temperature.setForeground(Color.black);
			LoginGUI.mframe.wellArea_humidity.setForeground(Color.black);
			LoginGUI.mframe.wellArea_oxygen.setForeground(Color.black);
			LoginGUI.mframe.wellArea_pressure.setForeground(Color.black);
			LoginGUI.mframe.wellArea_smoke.setForeground(Color.black);
			LoginGUI.mframe.wellArea_gas.setForeground(Color.black);
		}
		else if (!staticInfo.externalWall.getErrorMessage().equals("NORMAL")) {
			staticInfo.externalWall = new externalWall(28.0, -243.0,
					"NORMAL");
			LoginGUI.mframe.externalWall_extemperture.setText(Double
					.toString(staticInfo.externalWall.getExternalWallTemp()));
			LoginGUI.mframe.externalWall_outsideTemp.setText(Double
					.toString(staticInfo.externalWall.getOutsiteTemp()));
			ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
			flash.setImage(flash.getImage().getScaledInstance(lightWidth,
					lightHeight, Image.SCALE_DEFAULT));
			
			LoginGUI.mframe.externalWall_light.setIcon(flash);
			LoginGUI.mframe.externalWall_extemperture.setForeground(Color.black);
			LoginGUI.mframe.externalWall_outsideTemp.setForeground(Color.black);
		}
		
	}
}
