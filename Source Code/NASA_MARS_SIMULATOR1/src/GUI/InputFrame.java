package GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.sound.sampled.AudioInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import Entity.belowHeatingArea;
import Entity.belowWellArea;
import Entity.commandSpace;
import Entity.equipStorageSpace;
import Entity.externalWall;
import Entity.labSpace;
import Entity.livingCell;
import JDBC.JDBCConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.DoubleBuffer;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class InputFrame {
	private JFrame frame;
	// public livingCell livingCell1;
	// public livingCell livingCell2;
	// public livingCell livingCell3;
	// public livingCell livingCell4;
	// public livingCell livingCell5;
	// public livingCell livingCell6;
	// public livingCell livingCell7;
	// public livingCell livingCell8;
	// public livingCell livingCell9;
	// public livingCell livingCell10;
	// public livingCell livingCell11;
	// public livingCell livingCell12;
	// public livingCell livingCell13;
	// public livingCell livingCell14;
	// public livingCell livingCell15;
	// public commandSpace commandSpace1;
	// public commandSpace commandSpace2;
	// public labSpace labSpace1;
	// public labSpace labSpace2;
	// public labSpace labSpace3;
	// public labSpace labSpace4;
	// public equipStorageSpace equipSpace;
	// public belowWellArea wellArea;
	// public belowHeatingArea heatingArea;
	// public externalWall externalWall;

	private JTextField livingCell_no;
	private JTextField livingCell_temperature;
	private JTextField livingCell_humidity;
	private JTextField livingCell_oxygen;
	private JTextField livingCell_pressure;
	private JTextField livingCell_smoke;
	private JTextField livingCell_gas;
	private JTextField labSpace_no;
	private JTextField labSpace_temperature;
	private JTextField labSpace_humidity;
	private JTextField labSpace_oxygen;
	private JTextField labSpace_pressure;
	private JTextField labSpace_smoke;
	private JTextField labSpace_gas;
	private JTextField wellArea_temperature;
	private JTextField wellArea_humidity;
	private JTextField wellArea_oxygen;
	private JTextField wellArea_pressure;
	private JTextField wellArea_smoke;
	private JTextField wellArea_gas;
	private JTextField externalWall_extemperture;
	private JTextField externalWall_outsideTemp;
	private JTextField commandSpace_no;
	private JTextField commandSpace_temperature;
	private JTextField commandSpace_humidity;
	private JTextField commandSpace_oxygen;
	private JTextField commandSpace_pressure;
	private JTextField commandSpace_smoke;
	private JTextField commandSpace_gas;
	private JTextField equipSpace_temperature;
	private JTextField equipSpace_humidity;
	private JTextField equipSpace_oxygen;
	private JTextField equipSpace_pressure;
	private JTextField equipSpace_smoke;
	private JTextField equipSpace_gas;
	private JTextField heatingArea_temperature;
	private JTextField heatingArea_humidity;
	private JTextField heatingArea_oxygen;
	private JTextField heatingArea_pressure;
	private JTextField heatingArea_smoke;
	private JTextField heatingArea_gas;
	public JScrollPane scrollPane = new JScrollPane();
	public JTable table;
	public JButton refresh = new JButton("Refresh");
	public String[][] data;
	
	
	Timer timer;
	public String count = "";
	public static int loopNumber = 0;
	
	public static JTextArea operation_indicator;

	public void insertIntoEventTable(int dataNum, String[] eventType,
			String[] eventAttribute, String[] location, int[] locationNumber,
			double[] sensorData, Timestamp[] time) {
		for (int i = 0; i < dataNum; i++) {
			String sql = "insert into Event (eventType,eventAttribute,location,locationNumber,sensorData,eventTime) values ("
					+ "'"
					+ eventType[i]
					+ "'"
					+ ","
					+ "'"
					+ eventAttribute[i]
					+ "'"
					+ ","
					+ "'"
					+ location[i]
					+ "'"
					+ ","
					+ locationNumber[i]
					+ ","
					+ sensorData[i]
					+ ","
					+ "'"
					+ time[i] + "'" + ");";
			JDBCConnection.executeUpdate(sql);
		}
	}

	public void refreshTable() {
		// 初始化表名数组
		String[] columnNames = new String[6];
		columnNames[0] = "Type";
		columnNames[1] = "Attribute";
		columnNames[2] = "Location";
		columnNames[3] = "No.";
		columnNames[4] = "Data";
		columnNames[5] = "Time";
//		String count;
		int row = 0;
		String sqlcount = "select count(*) from Event";
		JDBCConnection.executeQuery(sqlcount);
		while (JDBCConnection.next()) {
			try {
				count = JDBCConnection.resultSet.getString(1);
				 row = Integer.parseInt(count);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		row = row + 20;
		data = new String[row][6];
		String sqlstr = "select * from Event order by id desc";
		JDBCConnection.executeQuery(sqlstr);
		int i = 0;
		while (JDBCConnection.next()) {
			for (int column = 0; column < 6; column++) {
				try {
					data[i][column] = JDBCConnection.resultSet
							.getString(column + 2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// System.out.println(data[i][column]);
			}
			i++;
		}
		table = new JTable(data, columnNames) {// 表格可以被选中但不可以被编辑
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
	}

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			InputFrame window = new InputFrame();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public InputFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(150, 150, 1201, 770);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 1200, 55);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel lblNasaMarsHabitat = new JLabel(
				"NASA Mars Habitat Input Simulator\n");
		lblNasaMarsHabitat.setForeground(Color.MAGENTA);
		lblNasaMarsHabitat.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblNasaMarsHabitat.setHorizontalAlignment(SwingConstants.CENTER);
		lblNasaMarsHabitat.setBounds(412, 6, 345, 49);
		panel.add(lblNasaMarsHabitat);
		JPanel livingCellInputPanel = new JPanel();
		livingCellInputPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		livingCellInputPanel.setBounds(10, 67, 259, 312);
		frame.getContentPane().add(livingCellInputPanel);
		livingCellInputPanel.setLayout(null);
		JLabel lblLivingcellInput = new JLabel("LivingCell Input");
		lblLivingcellInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivingcellInput.setForeground(Color.MAGENTA);
		lblLivingcellInput.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblLivingcellInput.setBounds(36, 6, 190, 32);
		livingCellInputPanel.add(lblLivingcellInput);
		JLabel lblLivingcellNo = new JLabel("No:");
		lblLivingcellNo.setBounds(6, 50, 88, 28);
		livingCellInputPanel.add(lblLivingcellNo);
		JLabel lblTemperature = new JLabel("Temperature:");
		lblTemperature.setBounds(6, 80, 88, 28);
		livingCellInputPanel.add(lblTemperature);
		JLabel lblHumidity = new JLabel("Humidity:");
		lblHumidity.setBounds(6, 110, 88, 28);
		livingCellInputPanel.add(lblHumidity);
		JLabel lblOxygenLevel = new JLabel("Oxygen Level:");
		lblOxygenLevel.setBounds(6, 140, 88, 28);
		livingCellInputPanel.add(lblOxygenLevel);
		JLabel lblPressure = new JLabel("Pressure:");
		lblPressure.setBounds(6, 170, 88, 28);
		livingCellInputPanel.add(lblPressure);
		JLabel lblSmoke = new JLabel("Smoke:");
		lblSmoke.setBounds(6, 200, 88, 28);
		livingCellInputPanel.add(lblSmoke);
		JLabel lblGas = new JLabel("Gas:");
		lblGas.setBounds(6, 230, 88, 28);
		livingCellInputPanel.add(lblGas);
		livingCell_no = new JTextField();
		livingCell_no.setForeground(Color.BLACK);
		livingCell_no.setText("1");
		livingCell_no.setBounds(95, 50, 100, 28);
		livingCellInputPanel.add(livingCell_no);
		livingCell_no.setColumns(10);
		livingCell_temperature = new JTextField();
		livingCell_temperature.setForeground(Color.BLACK);
		livingCell_temperature.setText("72.0");
		livingCell_temperature.setColumns(10);
		livingCell_temperature.setBounds(95, 80, 100, 28);
		livingCellInputPanel.add(livingCell_temperature);
		livingCell_humidity = new JTextField();
		livingCell_humidity.setText("45.0");
		livingCell_humidity.setColumns(10);
		livingCell_humidity.setBounds(95, 110, 100, 28);
		livingCellInputPanel.add(livingCell_humidity);
		livingCell_oxygen = new JTextField();
		livingCell_oxygen.setText("21.0");
		livingCell_oxygen.setColumns(10);
		livingCell_oxygen.setBounds(95, 140, 100, 28);
		livingCellInputPanel.add(livingCell_oxygen);
		livingCell_pressure = new JTextField();
		livingCell_pressure.setText("14.5");
		livingCell_pressure.setColumns(10);
		livingCell_pressure.setBounds(95, 170, 100, 28);
		livingCellInputPanel.add(livingCell_pressure);
		livingCell_smoke = new JTextField();
		livingCell_smoke.setText("0");
		livingCell_smoke.setColumns(10);
		livingCell_smoke.setBounds(95, 200, 100, 28);
		livingCellInputPanel.add(livingCell_smoke);
		livingCell_gas = new JTextField();
		livingCell_gas.setText("0");
		livingCell_gas.setColumns(10);
		livingCell_gas.setBounds(95, 230, 100, 28);
		livingCellInputPanel.add(livingCell_gas);
		JLabel lblfromTo = new JLabel("(From 1 to 15)");
		lblfromTo.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblfromTo.setBounds(196, 58, 61, 16);
		livingCellInputPanel.add(lblfromTo);
		JLabel lblF = new JLabel("F");
		lblF.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblF.setBounds(196, 86, 61, 16);
		livingCellInputPanel.add(lblF);
		JLabel label_1 = new JLabel("%");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_1.setBounds(196, 116, 61, 16);
		livingCellInputPanel.add(label_1);
		JLabel label_2 = new JLabel("%");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_2.setBounds(196, 146, 61, 16);
		livingCellInputPanel.add(label_2);
		JLabel lblPsi = new JLabel("psi");
		lblPsi.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblPsi.setBounds(196, 176, 61, 16);
		livingCellInputPanel.add(lblPsi);
		JLabel lblOr = new JLabel("(0 or 1)");
		lblOr.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblOr.setBounds(196, 206, 61, 16);
		livingCellInputPanel.add(lblOr);
		JLabel lblOr_1 = new JLabel("(0 or 1)");
		lblOr_1.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblOr_1.setBounds(196, 236, 61, 16);
		livingCellInputPanel.add(lblOr_1);
		JButton livingCell_simulate = new JButton("Simulate");
		livingCell_simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entity = "livingCell";
				try {
					String id = livingCell_no.getText();
					String source = "shouldStoreInDataBase";
					double temperature = Double
							.parseDouble(livingCell_temperature.getText());
					double humidity = Double.parseDouble(livingCell_humidity
							.getText());
					double oxygenLevel = Double.parseDouble(livingCell_oxygen
							.getText());
					double pressure = Double.parseDouble(livingCell_pressure
							.getText());
					double smokeDetection = Double.parseDouble(livingCell_smoke
							.getText());
					double gasDetection = Double.parseDouble(livingCell_gas
							.getText());

					if (Integer.parseInt(id) > 15) {
						JOptionPane.showMessageDialog(null,
								"LivingCell No Must Be Among 1 to 15",
								"Error Message", JOptionPane.ERROR_MESSAGE);
					} else {
						livingCell livingCell = new livingCell(temperature,
								humidity, oxygenLevel, pressure,
								smokeDetection, gasDetection);

						try {
							Socket s = new Socket("localhost", 2002);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF(entity);
							oos.writeUTF(id);
							oos.writeUTF(source);
							oos.writeObject(livingCell);
							// oos.writeObject(new
							// String("another object from the client"));
							oos.close();
							os.close();
							s.close();

							try {
								Thread.sleep(500);
								refreshTable();

								// Then do something meaningful...
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}

						} catch (Exception e1) {
							System.out.println(e1);
							JOptionPane.showMessageDialog(null,
							"Socket Error ",
							"Error Message", JOptionPane.ERROR_MESSAGE);

						}
					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"Value Must Be In Appropriate Format",
							"Error Message", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		livingCell_simulate.setBounds(6, 270, 117, 29);
		livingCellInputPanel.add(livingCell_simulate);
		JButton livingCell_reset = new JButton("Reset");
		livingCell_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 充值textfield内容
				livingCell_no.setText("1");
				livingCell_temperature.setText("72.0");
				livingCell_humidity.setText("45.0");
				livingCell_oxygen.setText("21.0");
				livingCell_pressure.setText("14.5");
				livingCell_smoke.setText("0");
				livingCell_gas.setText("0");
			}
		});
		livingCell_reset.setBounds(120, 270, 117, 29);
		livingCellInputPanel.add(livingCell_reset);
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(269, 67, 259, 312);
		frame.getContentPane().add(panel_1);
		JLabel lblLaboratoryInput = new JLabel("Laboratory Input");
		lblLaboratoryInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblLaboratoryInput.setForeground(Color.MAGENTA);
		lblLaboratoryInput.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblLaboratoryInput.setBounds(36, 6, 190, 32);
		panel_1.add(lblLaboratoryInput);
		JLabel label_3 = new JLabel("No:");
		label_3.setBounds(6, 50, 88, 28);
		panel_1.add(label_3);
		JLabel label_4 = new JLabel("Temperature:");
		label_4.setBounds(6, 80, 88, 28);
		panel_1.add(label_4);
		JLabel label_5 = new JLabel("Humidity:");
		label_5.setBounds(6, 110, 88, 28);
		panel_1.add(label_5);
		JLabel label_6 = new JLabel("Oxygen Level:");
		label_6.setBounds(6, 140, 88, 28);
		panel_1.add(label_6);
		JLabel label_7 = new JLabel("Pressure:");
		label_7.setBounds(6, 170, 88, 28);
		panel_1.add(label_7);
		JLabel label_8 = new JLabel("Smoke:");
		label_8.setBounds(6, 200, 88, 28);
		panel_1.add(label_8);
		JLabel label_9 = new JLabel("Gas:");
		label_9.setBounds(6, 230, 88, 28);
		panel_1.add(label_9);
		labSpace_no = new JTextField();
		labSpace_no.setText("1");
		labSpace_no.setForeground(Color.BLACK);
		labSpace_no.setColumns(10);
		labSpace_no.setBounds(95, 50, 100, 28);
		panel_1.add(labSpace_no);
		labSpace_temperature = new JTextField();
		labSpace_temperature.setText("72.0");
		labSpace_temperature.setForeground(Color.BLACK);
		labSpace_temperature.setColumns(10);
		labSpace_temperature.setBounds(95, 80, 100, 28);
		panel_1.add(labSpace_temperature);
		labSpace_humidity = new JTextField();
		labSpace_humidity.setText("45.0");
		labSpace_humidity.setColumns(10);
		labSpace_humidity.setBounds(95, 110, 100, 28);
		panel_1.add(labSpace_humidity);
		labSpace_oxygen = new JTextField();
		labSpace_oxygen.setText("21.0");
		labSpace_oxygen.setColumns(10);
		labSpace_oxygen.setBounds(95, 140, 100, 28);
		panel_1.add(labSpace_oxygen);
		labSpace_pressure = new JTextField();
		labSpace_pressure.setText("14.5");
		labSpace_pressure.setColumns(10);
		labSpace_pressure.setBounds(95, 170, 100, 28);
		panel_1.add(labSpace_pressure);
		labSpace_smoke = new JTextField();
		labSpace_smoke.setText("0");
		labSpace_smoke.setColumns(10);
		labSpace_smoke.setBounds(95, 200, 100, 28);
		panel_1.add(labSpace_smoke);
		labSpace_gas = new JTextField();
		labSpace_gas.setText("0");
		labSpace_gas.setColumns(10);
		labSpace_gas.setBounds(95, 230, 100, 28);
		panel_1.add(labSpace_gas);
		JLabel lblfromTo_2 = new JLabel("(From 1 to 4)");
		lblfromTo_2.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblfromTo_2.setBounds(196, 58, 61, 16);
		panel_1.add(lblfromTo_2);
		JLabel label_11 = new JLabel("F");
		label_11.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_11.setBounds(196, 86, 61, 16);
		panel_1.add(label_11);
		JLabel label_12 = new JLabel("%");
		label_12.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_12.setBounds(196, 116, 61, 16);
		panel_1.add(label_12);
		JLabel label_13 = new JLabel("%");
		label_13.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_13.setBounds(196, 146, 61, 16);
		panel_1.add(label_13);
		JLabel label_14 = new JLabel("psi");
		label_14.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_14.setBounds(196, 176, 61, 16);
		panel_1.add(label_14);
		JLabel label_15 = new JLabel("(0 or 1)");
		label_15.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_15.setBounds(196, 206, 61, 16);
		panel_1.add(label_15);
		JLabel label_16 = new JLabel("(0 or 1)");
		label_16.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_16.setBounds(196, 236, 61, 16);
		panel_1.add(label_16);
		JButton labSpace_simulate = new JButton("Simulate");
		labSpace_simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String entity = "labSpace";
				try {
					String id = labSpace_no.getText();
					String source = "shouldStoreInDataBase";
					double temperature = Double
							.parseDouble(labSpace_temperature.getText());
					double humidity = Double.parseDouble(labSpace_humidity
							.getText());
					double oxygenLevel = Double.parseDouble(labSpace_oxygen
							.getText());
					double pressure = Double.parseDouble(labSpace_pressure
							.getText());
					double smokeDetection = Double.parseDouble(labSpace_smoke
							.getText());
					double gasDetection = Double.parseDouble(labSpace_gas
							.getText());

					if (Integer.parseInt(id) > 4) {
						JOptionPane.showMessageDialog(null,
								"labSpace No Must Be Among 1 to 4",
								"Error Message", JOptionPane.ERROR_MESSAGE);
					} else {
						labSpace labSpace = new labSpace(temperature, humidity,
								oxygenLevel, pressure, smokeDetection,
								gasDetection);

						try {
							Socket s = new Socket("localhost", 2002);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF(entity);
							oos.writeUTF(id);
							oos.writeUTF(source);
							oos.writeObject(labSpace);
							// oos.writeObject(new
							// String("another object from the client"));
							oos.close();
							os.close();
							s.close();

							try {
								Thread.sleep(500);
								refreshTable();

								// Then do something meaningful...
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}

						} catch (Exception e1) {
							System.out.println(e1);
							JOptionPane.showMessageDialog(null,
							"Socket Error ",
							"Error Message", JOptionPane.ERROR_MESSAGE);

						}
					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"Value Must Be In Appropriate Format",
							"Error Message", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		labSpace_simulate.setBounds(6, 270, 117, 29);
		panel_1.add(labSpace_simulate);
		JButton labSpace_reset = new JButton("Reset");
		labSpace_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labSpace_no.setText("1");
				labSpace_temperature.setText("72.0");
				labSpace_humidity.setText("45.0");
				labSpace_oxygen.setText("21.0");
				labSpace_pressure.setText("14.5");
				labSpace_smoke.setText("0");
				labSpace_gas.setText("0");
			}
		});
		labSpace_reset.setBounds(135, 270, 117, 29);
		panel_1.add(labSpace_reset);
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_2.setBounds(528, 67, 259, 312);
		frame.getContentPane().add(panel_2);
		JLabel lblWellAreaInput = new JLabel("Well Area Input");
		lblWellAreaInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblWellAreaInput.setForeground(Color.MAGENTA);
		lblWellAreaInput.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblWellAreaInput.setBounds(36, 6, 190, 32);
		panel_2.add(lblWellAreaInput);
		JLabel label_19 = new JLabel("Temperature:");
		label_19.setBounds(6, 80, 88, 28);
		panel_2.add(label_19);
		JLabel label_20 = new JLabel("Humidity:");
		label_20.setBounds(6, 110, 88, 28);
		panel_2.add(label_20);
		JLabel label_21 = new JLabel("Oxygen Level:");
		label_21.setBounds(6, 140, 88, 28);
		panel_2.add(label_21);
		JLabel label_22 = new JLabel("Pressure:");
		label_22.setBounds(6, 170, 88, 28);
		panel_2.add(label_22);
		JLabel label_23 = new JLabel("Smoke:");
		label_23.setBounds(6, 200, 88, 28);
		panel_2.add(label_23);
		JLabel label_24 = new JLabel("Gas:");
		label_24.setBounds(6, 230, 88, 28);
		panel_2.add(label_24);
		wellArea_temperature = new JTextField();
		wellArea_temperature.setText("35.0");
		wellArea_temperature.setForeground(Color.BLACK);
		wellArea_temperature.setColumns(10);
		wellArea_temperature.setBounds(95, 80, 100, 28);
		panel_2.add(wellArea_temperature);
		wellArea_humidity = new JTextField();
		wellArea_humidity.setText("75.0");
		wellArea_humidity.setColumns(10);
		wellArea_humidity.setBounds(95, 110, 100, 28);
		panel_2.add(wellArea_humidity);
		wellArea_oxygen = new JTextField();
		wellArea_oxygen.setText("21.0");
		wellArea_oxygen.setColumns(10);
		wellArea_oxygen.setBounds(95, 140, 100, 28);
		panel_2.add(wellArea_oxygen);
		wellArea_pressure = new JTextField();
		wellArea_pressure.setText("14.5");
		wellArea_pressure.setColumns(10);
		wellArea_pressure.setBounds(95, 170, 100, 28);
		panel_2.add(wellArea_pressure);
		wellArea_smoke = new JTextField();
		wellArea_smoke.setText("0");
		wellArea_smoke.setColumns(10);
		wellArea_smoke.setBounds(95, 200, 100, 28);
		panel_2.add(wellArea_smoke);
		wellArea_gas = new JTextField();
		wellArea_gas.setText("0");
		wellArea_gas.setColumns(10);
		wellArea_gas.setBounds(95, 230, 100, 28);
		panel_2.add(wellArea_gas);
		JLabel label_26 = new JLabel("F");
		label_26.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_26.setBounds(196, 86, 61, 16);
		panel_2.add(label_26);
		JLabel label_27 = new JLabel("%");
		label_27.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_27.setBounds(196, 116, 61, 16);
		panel_2.add(label_27);
		JLabel label_28 = new JLabel("%");
		label_28.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_28.setBounds(196, 146, 61, 16);
		panel_2.add(label_28);
		JLabel label_29 = new JLabel("psi");
		label_29.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_29.setBounds(196, 176, 61, 16);
		panel_2.add(label_29);
		JLabel label_30 = new JLabel("(0 or 1)");
		label_30.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_30.setBounds(196, 206, 61, 16);
		panel_2.add(label_30);
		JLabel label_31 = new JLabel("(0 or 1)");
		label_31.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_31.setBounds(196, 236, 61, 16);
		panel_2.add(label_31);
		JButton wellArea_simulate = new JButton("Simulate");
		wellArea_simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String entity = "wellArea";
				try {
					String id = "1";
					String source = "shouldStoreInDataBase";
					double temperature = Double
							.parseDouble(wellArea_temperature.getText());
					double humidity = Double.parseDouble(wellArea_humidity
							.getText());
					double oxygenLevel = Double.parseDouble(wellArea_oxygen
							.getText());
					double pressure = Double.parseDouble(wellArea_pressure
							.getText());
					double smokeDetection = Double.parseDouble(wellArea_smoke
							.getText());
					double gasDetection = Double.parseDouble(wellArea_gas
							.getText());

					belowWellArea wellArea = new belowWellArea(temperature,
							humidity, oxygenLevel, pressure, smokeDetection,
							gasDetection);

					try {
						Socket s = new Socket("localhost", 2002);
						OutputStream os = s.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(os);

						oos.writeUTF(entity);
						oos.writeUTF(id);
						oos.writeUTF(source);
						oos.writeObject(wellArea);
						// oos.writeObject(new
						// String("another object from the client"));
						oos.close();
						os.close();
						s.close();

						try {
							Thread.sleep(500);
							refreshTable();

							// Then do something meaningful...
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

					} catch (Exception e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null,
							"Socket Error ",
							"Error Message", JOptionPane.ERROR_MESSAGE);

					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"Value Must Be In Appropriate Format",
							"Error Message", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		wellArea_simulate.setBounds(6, 270, 117, 29);
		panel_2.add(wellArea_simulate);
		JButton wellArea_reset = new JButton("Reset");
		wellArea_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wellArea_temperature.setText("35.0");
				wellArea_humidity.setText("75.0");
				wellArea_oxygen.setText("21.0");
				wellArea_pressure.setText("14.5");
				wellArea_smoke.setText("0");
				wellArea_gas.setText("0");
			}
		});
		wellArea_reset.setBounds(135, 270, 117, 29);
		panel_2.add(wellArea_reset);
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_3.setBounds(787, 67, 408, 129);
		frame.getContentPane().add(panel_3);
		JLabel lblExternalWallInput = new JLabel("External Wall Input");
		lblExternalWallInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblExternalWallInput.setForeground(Color.MAGENTA);
		lblExternalWallInput.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblExternalWallInput.setBounds(115, 6, 190, 32);
		panel_3.add(lblExternalWallInput);
		JLabel lblExternalWallTemperature = new JLabel(
				"External Wall Temperature:");
		lblExternalWallTemperature.setBounds(16, 38, 180, 28);
		panel_3.add(lblExternalWallTemperature);
		JLabel lblOutsideTemperature = new JLabel("Outside Temperature:");
		lblOutsideTemperature.setBounds(16, 64, 136, 28);
		panel_3.add(lblOutsideTemperature);
		externalWall_extemperture = new JTextField();
		externalWall_extemperture.setText("28.0");
		externalWall_extemperture.setForeground(Color.BLACK);
		externalWall_extemperture.setColumns(10);
		externalWall_extemperture.setBounds(205, 38, 100, 28);
		panel_3.add(externalWall_extemperture);
		externalWall_outsideTemp = new JTextField();
		externalWall_outsideTemp.setText("-243.0");
		externalWall_outsideTemp.setColumns(10);
		externalWall_outsideTemp.setBounds(205, 64, 100, 28);
		panel_3.add(externalWall_outsideTemp);
		JLabel label_41 = new JLabel("F");
		label_41.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_41.setBounds(305, 44, 61, 16);
		panel_3.add(label_41);
		JButton externalWall_simulate = new JButton("Simulate");
		externalWall_simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String entity = "externalWall";
				try {
					String id = "1";
					String source = "shouldStoreInDataBase";
					double exTemperature = Double
							.parseDouble(externalWall_extemperture.getText());
					double outsideTemperature = Double
							.parseDouble(externalWall_outsideTemp.getText());

					externalWall externalWall = new externalWall(exTemperature,
							outsideTemperature);

					try {
						Socket s = new Socket("localhost", 2002);
						OutputStream os = s.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(os);

						oos.writeUTF(entity);
						oos.writeUTF(id);
						oos.writeUTF(source);
						oos.writeObject(externalWall);
						// oos.writeObject(new
						// String("another object from the client"));
						oos.close();
						os.close();
						s.close();

						try {
							Thread.sleep(500);
							refreshTable();

							// Then do something meaningful...
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

					} catch (Exception e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null,
							"Socket Error ",
							"Error Message", JOptionPane.ERROR_MESSAGE);

					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"Value Must Be In Appropriate Format",
							"Error Message", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		externalWall_simulate.setBounds(26, 94, 117, 29);
		panel_3.add(externalWall_simulate);
		JLabel label = new JLabel("F");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label.setBounds(305, 70, 61, 16);
		panel_3.add(label);
		JButton externalWall_reset = new JButton("Reset");
		externalWall_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				externalWall_extemperture.setText("28.0");
				externalWall_outsideTemp.setText("-243.0");
			}
		});
		externalWall_reset.setBounds(268, 94, 117, 29);
		panel_3.add(externalWall_reset);
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_4.setBounds(10, 414, 259, 312);
		frame.getContentPane().add(panel_4);
		JLabel lblCommandspaceInput = new JLabel("CommandSpace Input");
		lblCommandspaceInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommandspaceInput.setForeground(Color.MAGENTA);
		lblCommandspaceInput.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblCommandspaceInput.setBounds(36, 6, 190, 32);
		panel_4.add(lblCommandspaceInput);
		JLabel label_48 = new JLabel("No:");
		label_48.setBounds(6, 50, 88, 28);
		panel_4.add(label_48);
		JLabel label_49 = new JLabel("Temperature:");
		label_49.setBounds(6, 80, 88, 28);
		panel_4.add(label_49);
		JLabel label_50 = new JLabel("Humidity:");
		label_50.setBounds(6, 110, 88, 28);
		panel_4.add(label_50);
		JLabel label_51 = new JLabel("Oxygen Level:");
		label_51.setBounds(6, 140, 88, 28);
		panel_4.add(label_51);
		JLabel label_52 = new JLabel("Pressure:");
		label_52.setBounds(6, 170, 88, 28);
		panel_4.add(label_52);
		JLabel label_53 = new JLabel("Smoke:");
		label_53.setBounds(6, 200, 88, 28);
		panel_4.add(label_53);
		JLabel label_54 = new JLabel("Gas:");
		label_54.setBounds(6, 230, 88, 28);
		panel_4.add(label_54);
		commandSpace_no = new JTextField();
		commandSpace_no.setText("1");
		commandSpace_no.setForeground(Color.BLACK);
		commandSpace_no.setColumns(10);
		commandSpace_no.setBounds(95, 50, 100, 28);
		panel_4.add(commandSpace_no);
		commandSpace_temperature = new JTextField();
		commandSpace_temperature.setText("72.0");
		commandSpace_temperature.setForeground(Color.BLACK);
		commandSpace_temperature.setColumns(10);
		commandSpace_temperature.setBounds(95, 80, 100, 28);
		panel_4.add(commandSpace_temperature);
		commandSpace_humidity = new JTextField();
		commandSpace_humidity.setText("45.0");
		commandSpace_humidity.setColumns(10);
		commandSpace_humidity.setBounds(95, 110, 100, 28);
		panel_4.add(commandSpace_humidity);
		commandSpace_oxygen = new JTextField();
		commandSpace_oxygen.setText("21.0");
		commandSpace_oxygen.setColumns(10);
		commandSpace_oxygen.setBounds(95, 140, 100, 28);
		panel_4.add(commandSpace_oxygen);
		commandSpace_pressure = new JTextField();
		commandSpace_pressure.setText("14.5");
		commandSpace_pressure.setColumns(10);
		commandSpace_pressure.setBounds(95, 170, 100, 28);
		panel_4.add(commandSpace_pressure);
		commandSpace_smoke = new JTextField();
		commandSpace_smoke.setText("0");
		commandSpace_smoke.setColumns(10);
		commandSpace_smoke.setBounds(95, 200, 100, 28);
		panel_4.add(commandSpace_smoke);
		commandSpace_gas = new JTextField();
		commandSpace_gas.setText("0");
		commandSpace_gas.setColumns(10);
		commandSpace_gas.setBounds(95, 230, 100, 28);
		panel_4.add(commandSpace_gas);
		JLabel lblfromTo_1 = new JLabel("(From 1 to 2)");
		lblfromTo_1.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		lblfromTo_1.setBounds(196, 58, 61, 16);
		panel_4.add(lblfromTo_1);
		JLabel label_56 = new JLabel("F");
		label_56.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_56.setBounds(196, 86, 61, 16);
		panel_4.add(label_56);
		JLabel label_57 = new JLabel("%");
		label_57.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_57.setBounds(196, 116, 61, 16);
		panel_4.add(label_57);
		JLabel label_58 = new JLabel("%");
		label_58.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_58.setBounds(196, 146, 61, 16);
		panel_4.add(label_58);
		JLabel label_59 = new JLabel("psi");
		label_59.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_59.setBounds(196, 176, 61, 16);
		panel_4.add(label_59);
		JLabel label_60 = new JLabel("(0 or 1)");
		label_60.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_60.setBounds(196, 206, 61, 16);
		panel_4.add(label_60);
		JLabel label_61 = new JLabel("(0 or 1)");
		label_61.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_61.setBounds(196, 236, 61, 16);
		panel_4.add(label_61);
		// 处理commandSpace simulate button
		JButton commandSpace_simulate = new JButton("Simulate");
		commandSpace_simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String entity = "commandSpace";
				String source = "shouldStoreInDataBase";
				try {
					String id = commandSpace_no.getText();
					double temperature = Double
							.parseDouble(commandSpace_temperature.getText());
					double humidity = Double.parseDouble(commandSpace_humidity
							.getText());
					double oxygenLevel = Double.parseDouble(commandSpace_oxygen
							.getText());
					double pressure = Double.parseDouble(commandSpace_pressure
							.getText());
					double smokeDetection = Double
							.parseDouble(commandSpace_smoke.getText());
					double gasDetection = Double.parseDouble(commandSpace_gas
							.getText());

					if (Integer.parseInt(id) > 2) {
						JOptionPane.showMessageDialog(null,
								"CommandSpace No Must Be Among 1 to 2",
								"Error Message", JOptionPane.ERROR_MESSAGE);
					} else {
						commandSpace commandSpace = new commandSpace(
								temperature, humidity, oxygenLevel, pressure,
								smokeDetection, gasDetection);

						try {
							Socket s = new Socket("localhost", 2002);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF(entity);
							oos.writeUTF(id);
							oos.writeUTF(source);
							oos.writeObject(commandSpace);
							// oos.writeObject(new
							// String("another object from the client"));
							oos.close();
							os.close();
							s.close();

							try {
								Thread.sleep(500);
								refreshTable();

								// Then do something meaningful...
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}

						} catch (Exception e1) {
							System.out.println(e1);
							JOptionPane.showMessageDialog(null,
							"Socket Error ",
							"Error Message", JOptionPane.ERROR_MESSAGE);

						}
					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"Value Must Be In Appropriate Format",
							"Error Message", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		commandSpace_simulate.setBounds(6, 270, 117, 29);
		panel_4.add(commandSpace_simulate);
		JButton commandSpace_reset = new JButton("Reset");
		commandSpace_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commandSpace_no.setText("1");
				commandSpace_temperature.setText("72.0");
				commandSpace_humidity.setText("45.0");
				commandSpace_oxygen.setText("21.0");
				commandSpace_pressure.setText("14.5");
				commandSpace_smoke.setText("0");
				commandSpace_gas.setText("0");
			}
		});
		commandSpace_reset.setBounds(123, 270, 117, 29);
		panel_4.add(commandSpace_reset);
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_5.setBounds(269, 414, 259, 312);
		frame.getContentPane().add(panel_5);
		JLabel lblEquipmentStorageInput = new JLabel("Equipment Space Input");
		lblEquipmentStorageInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipmentStorageInput.setForeground(Color.MAGENTA);
		lblEquipmentStorageInput.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblEquipmentStorageInput.setBounds(22, 6, 220, 32);
		panel_5.add(lblEquipmentStorageInput);
		JLabel label_64 = new JLabel("Temperature:");
		label_64.setBounds(6, 80, 88, 28);
		panel_5.add(label_64);
		JLabel label_65 = new JLabel("Humidity:");
		label_65.setBounds(6, 110, 88, 28);
		panel_5.add(label_65);
		JLabel label_66 = new JLabel("Oxygen Level:");
		label_66.setBounds(6, 140, 88, 28);
		panel_5.add(label_66);
		JLabel label_67 = new JLabel("Pressure:");
		label_67.setBounds(6, 170, 88, 28);
		panel_5.add(label_67);
		JLabel label_68 = new JLabel("Smoke:");
		label_68.setBounds(6, 200, 88, 28);
		panel_5.add(label_68);
		JLabel label_69 = new JLabel("Gas:");
		label_69.setBounds(6, 230, 88, 28);
		panel_5.add(label_69);
		equipSpace_temperature = new JTextField();
		equipSpace_temperature.setText("72.0");
		equipSpace_temperature.setForeground(Color.BLACK);
		equipSpace_temperature.setColumns(10);
		equipSpace_temperature.setBounds(95, 80, 100, 28);
		panel_5.add(equipSpace_temperature);
		equipSpace_humidity = new JTextField();
		equipSpace_humidity.setText("45.0");
		equipSpace_humidity.setColumns(10);
		equipSpace_humidity.setBounds(95, 110, 100, 28);
		panel_5.add(equipSpace_humidity);
		equipSpace_oxygen = new JTextField();
		equipSpace_oxygen.setText("21.0");
		equipSpace_oxygen.setColumns(10);
		equipSpace_oxygen.setBounds(95, 140, 100, 28);
		panel_5.add(equipSpace_oxygen);
		equipSpace_pressure = new JTextField();
		equipSpace_pressure.setText("14.5");
		equipSpace_pressure.setColumns(10);
		equipSpace_pressure.setBounds(95, 170, 100, 28);
		panel_5.add(equipSpace_pressure);
		equipSpace_smoke = new JTextField();
		equipSpace_smoke.setText("0");
		equipSpace_smoke.setColumns(10);
		equipSpace_smoke.setBounds(95, 200, 100, 28);
		panel_5.add(equipSpace_smoke);
		equipSpace_gas = new JTextField();
		equipSpace_gas.setText("0");
		equipSpace_gas.setColumns(10);
		equipSpace_gas.setBounds(95, 230, 100, 28);
		panel_5.add(equipSpace_gas);
		JLabel label_71 = new JLabel("F");
		label_71.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_71.setBounds(196, 86, 61, 16);
		panel_5.add(label_71);
		JLabel label_72 = new JLabel("%");
		label_72.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_72.setBounds(196, 116, 61, 16);
		panel_5.add(label_72);
		JLabel label_73 = new JLabel("%");
		label_73.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_73.setBounds(196, 146, 61, 16);
		panel_5.add(label_73);
		JLabel label_74 = new JLabel("psi");
		label_74.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_74.setBounds(196, 176, 61, 16);
		panel_5.add(label_74);
		JLabel label_75 = new JLabel("(0 or 1)");
		label_75.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_75.setBounds(196, 206, 61, 16);
		panel_5.add(label_75);
		JLabel label_76 = new JLabel("(0 or 1)");
		label_76.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_76.setBounds(196, 236, 61, 16);
		panel_5.add(label_76);
		JButton equipSpace_simulate = new JButton("Simulate");
		equipSpace_simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entity = "equipSpace";
				String source = "shouldStoreInDataBase";
				try {
					String id = "1";
					double temperature = Double
							.parseDouble(equipSpace_temperature.getText());
					double humidity = Double.parseDouble(equipSpace_humidity
							.getText());
					double oxygenLevel = Double.parseDouble(equipSpace_oxygen
							.getText());
					double pressure = Double.parseDouble(equipSpace_pressure
							.getText());
					double smokeDetection = Double.parseDouble(equipSpace_smoke
							.getText());
					double gasDetection = Double.parseDouble(equipSpace_gas
							.getText());

					equipStorageSpace equipSpace = new equipStorageSpace(
							temperature, humidity, oxygenLevel, pressure,
							smokeDetection, gasDetection);

					try {
						Socket s = new Socket("localhost", 2002);
						OutputStream os = s.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(os);

						oos.writeUTF(entity);
						oos.writeUTF(id);
						oos.writeUTF(source);
						oos.writeObject(equipSpace);
						// oos.writeObject(new
						// String("another object from the client"));
						oos.close();
						os.close();
						s.close();

						try {
							Thread.sleep(500);
							refreshTable();

							// Then do something meaningful...
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

					} catch (Exception e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null,
							"Socket Error ",
							"Error Message", JOptionPane.ERROR_MESSAGE);

					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"Value Must Be In Appropriate Format",
							"Error Message", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		equipSpace_simulate.setBounds(6, 270, 117, 29);
		panel_5.add(equipSpace_simulate);
		JButton equipSpace_reset = new JButton("Reset");
		equipSpace_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equipSpace_temperature.setText("72.0");
				equipSpace_humidity.setText("45.0");
				equipSpace_oxygen.setText("21.0");
				equipSpace_pressure.setText("14.5");
				equipSpace_smoke.setText("0");
				equipSpace_gas.setText("0");
			}
		});
		equipSpace_reset.setBounds(122, 270, 117, 29);
		panel_5.add(equipSpace_reset);
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_6.setBounds(528, 414, 259, 312);
		frame.getContentPane().add(panel_6);
		JLabel lblHeatingCooling = new JLabel("Heating & Cooling Area");
		lblHeatingCooling.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeatingCooling.setForeground(Color.MAGENTA);
		lblHeatingCooling.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblHeatingCooling.setBounds(21, 6, 220, 32);
		panel_6.add(lblHeatingCooling);
		JLabel label_79 = new JLabel("Temperature:");
		label_79.setBounds(6, 80, 88, 28);
		panel_6.add(label_79);
		JLabel label_80 = new JLabel("Humidity:");
		label_80.setBounds(6, 110, 88, 28);
		panel_6.add(label_80);
		JLabel label_81 = new JLabel("Oxygen Level:");
		label_81.setBounds(6, 140, 88, 28);
		panel_6.add(label_81);
		JLabel label_82 = new JLabel("Pressure:");
		label_82.setBounds(6, 170, 88, 28);
		panel_6.add(label_82);
		JLabel label_83 = new JLabel("Smoke:");
		label_83.setBounds(6, 200, 88, 28);
		panel_6.add(label_83);
		JLabel label_84 = new JLabel("Gas:");
		label_84.setBounds(6, 230, 88, 28);
		panel_6.add(label_84);
		heatingArea_temperature = new JTextField();
		heatingArea_temperature.setText("90.0");
		heatingArea_temperature.setForeground(Color.BLACK);
		heatingArea_temperature.setColumns(10);
		heatingArea_temperature.setBounds(95, 80, 100, 28);
		panel_6.add(heatingArea_temperature);
		heatingArea_humidity = new JTextField();
		heatingArea_humidity.setText("45.0");
		heatingArea_humidity.setColumns(10);
		heatingArea_humidity.setBounds(95, 110, 100, 28);
		panel_6.add(heatingArea_humidity);
		heatingArea_oxygen = new JTextField();
		heatingArea_oxygen.setText("21.0");
		heatingArea_oxygen.setColumns(10);
		heatingArea_oxygen.setBounds(95, 140, 100, 28);
		panel_6.add(heatingArea_oxygen);
		heatingArea_pressure = new JTextField();
		heatingArea_pressure.setText("14.5");
		heatingArea_pressure.setColumns(10);
		heatingArea_pressure.setBounds(95, 170, 100, 28);
		panel_6.add(heatingArea_pressure);
		heatingArea_smoke = new JTextField();
		heatingArea_smoke.setText("0");
		heatingArea_smoke.setColumns(10);
		heatingArea_smoke.setBounds(95, 200, 100, 28);
		panel_6.add(heatingArea_smoke);
		heatingArea_gas = new JTextField();
		heatingArea_gas.setText("0");
		heatingArea_gas.setColumns(10);
		heatingArea_gas.setBounds(95, 230, 100, 28);
		panel_6.add(heatingArea_gas);
		JLabel label_86 = new JLabel("F");
		label_86.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_86.setBounds(196, 86, 61, 16);
		panel_6.add(label_86);
		JLabel label_87 = new JLabel("%");
		label_87.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_87.setBounds(196, 116, 61, 16);
		panel_6.add(label_87);
		JLabel label_88 = new JLabel("%");
		label_88.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_88.setBounds(196, 146, 61, 16);
		panel_6.add(label_88);
		JLabel label_89 = new JLabel("psi");
		label_89.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_89.setBounds(196, 176, 61, 16);
		panel_6.add(label_89);
		JLabel label_90 = new JLabel("(0 or 1)");
		label_90.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_90.setBounds(196, 206, 61, 16);
		panel_6.add(label_90);
		JLabel label_91 = new JLabel("(0 or 1)");
		label_91.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		label_91.setBounds(196, 236, 61, 16);
		panel_6.add(label_91);
		JButton heatingArea_simulate = new JButton("Simulate");
		heatingArea_simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String entity = "heatingArea";
				String source = "shouldStoreInDataBase";

				try {
					String id = "1";
					double temperature = Double
							.parseDouble(heatingArea_temperature.getText());
					double humidity = Double.parseDouble(heatingArea_humidity
							.getText());
					double oxygenLevel = Double.parseDouble(heatingArea_oxygen
							.getText());
					double pressure = Double.parseDouble(heatingArea_pressure
							.getText());
					double smokeDetection = Double
							.parseDouble(heatingArea_smoke.getText());
					double gasDetection = Double.parseDouble(heatingArea_gas
							.getText());

					belowHeatingArea heatingArea = new belowHeatingArea(
							temperature, humidity, oxygenLevel, pressure,
							smokeDetection, gasDetection);

					try {
						Socket s = new Socket("localhost", 2002);
						OutputStream os = s.getOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(os);

						oos.writeUTF(entity);
						oos.writeUTF(id);
						oos.writeUTF(source);
						oos.writeObject(heatingArea);
						// oos.writeObject(new
						// String("another object from the client"));
						oos.close();
						os.close();
						s.close();

						try {
							Thread.sleep(500);
							refreshTable();

							// Then do something meaningful...
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}

					} catch (Exception e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(null,
							"Socket Error ",
							"Error Message", JOptionPane.ERROR_MESSAGE);

					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"Value Must Be In Appropriate Format",
							"Error Message", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		heatingArea_simulate.setBounds(6, 270, 117, 29);
		panel_6.add(heatingArea_simulate);
		JButton heatingArea_reset = new JButton("Reset");
		heatingArea_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heatingArea_temperature.setText("90.0");
				heatingArea_humidity.setText("45.0");
				heatingArea_oxygen.setText("21.0");
				heatingArea_pressure.setText("14.5");
				heatingArea_smoke.setText("0");
				heatingArea_gas.setText("0");
			}
		});
		heatingArea_reset.setBounds(135, 270, 117, 29);
		panel_6.add(heatingArea_reset);
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_7.setBounds(787, 201, 408, 522);
		frame.getContentPane().add(panel_7);
		panel_7.setLayout(null);
		JLabel lblDatabaseSimulate = new JLabel("DataBase Simulate");
		lblDatabaseSimulate.setBounds(115, 6, 190, 32);
		lblDatabaseSimulate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatabaseSimulate.setForeground(Color.MAGENTA);
		lblDatabaseSimulate.setFont(new Font("Kokonor", Font.PLAIN, 19));
		panel_7.add(lblDatabaseSimulate);
		scrollPane.setBounds(6, 36, 396, 224);
		panel_7.add(scrollPane);
		JButton database_simulate = new JButton("Simulate");
		database_simulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int lightWidth = 30;
				int lightHeight = 30;
				// 处理从数据库表上模拟各参数的输入
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null,
							"Please Select Row First");
				} else {
					loopNumber = selectedRow;
						timer = new Timer();
						timer.scheduleAtFixedRate(new TimerTask() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								
								String eventAttribute = data[loopNumber][1];
								String location = data[loopNumber][2];
								String locationNumber = data[loopNumber][3];
								String sensorData = data[loopNumber][4];
								
								table.setRowSelectionInterval(loopNumber, loopNumber);
								if (loopNumber!=0) {
									loopNumber--;
								}
								else {
									timer.cancel();
								}
								String source = eventAttribute;

								if (location.equals("LivingCell")) {
									String entity = "livingCell";

									double temperature = 0;
									double humidity = 0;
									double oxygenLevel = 0;
									double pressure = 0;
									double smokeDetection = 0;
									double gasDetection = 0;
									livingCell livingCell = new livingCell(temperature,
											humidity, oxygenLevel, pressure,
											smokeDetection, gasDetection);
									if (eventAttribute.equals("Temperature")) {
										livingCell.setTemperature(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Humidity")) {
										livingCell.setHumidity(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Oxygen Level")) {
										livingCell.setOxygenLevel(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Pressure")) {
										livingCell.setPressure(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Smoke")) {
										livingCell.setSmokeDetection(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Gas")) {
										livingCell.setGasDetection(Double
												.parseDouble(sensorData));
									}
									try {
										Socket s = new Socket("localhost", 2002);
										OutputStream os = s.getOutputStream();
										ObjectOutputStream oos = new ObjectOutputStream(os);

										oos.writeUTF(entity);
										oos.writeUTF(locationNumber);
										oos.writeUTF(source);
										oos.writeObject(livingCell);
										// oos.writeObject(new
										// String("another object from the client"));
										oos.close();
										os.close();
										s.close();

										

									} catch (Exception e1) {
										System.out.println(e1);
										JOptionPane.showMessageDialog(null,
										"Socket Error ",
										"Error Message", JOptionPane.ERROR_MESSAGE);

									}

								} else if (location.equals("CommandSpace")) {
									String entity = "commandSpace";

									double temperature = 0;
									double humidity = 0;
									double oxygenLevel = 0;
									double pressure = 0;
									double smokeDetection = 0;
									double gasDetection = 0;
									commandSpace commandSpace = new commandSpace(
											temperature, humidity, oxygenLevel, pressure,
											smokeDetection, gasDetection);
									if (eventAttribute.equals("Temperature")) {
										commandSpace.setTemperature(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Humidity")) {
										commandSpace.setHumidity(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Oxygen Level")) {
										commandSpace.setOxygenLevel(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Pressure")) {
										commandSpace.setPressure(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Smoke")) {
										commandSpace.setSmokeDetection(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Gas")) {
										commandSpace.setGasDetection(Double
												.parseDouble(sensorData));
									}
									try {
										Socket s = new Socket("localhost", 2002);
										OutputStream os = s.getOutputStream();
										ObjectOutputStream oos = new ObjectOutputStream(os);

										oos.writeUTF(entity);
										oos.writeUTF(locationNumber);
										oos.writeUTF(source);
										oos.writeObject(commandSpace);
										// oos.writeObject(new
										// String("another object from the client"));
										oos.close();
										os.close();
										s.close();

										

									} catch (Exception e1) {
										System.out.println(e1);
										JOptionPane.showMessageDialog(null,
										"Socket Error ",
										"Error Message", JOptionPane.ERROR_MESSAGE);

									}

								} else if (location.equals("LabSpace")) {
									String entity = "labSpace";

									double temperature = 0;
									double humidity = 0;
									double oxygenLevel = 0;
									double pressure = 0;
									double smokeDetection = 0;
									double gasDetection = 0;
									labSpace labSpace = new labSpace(temperature, humidity,
											oxygenLevel, pressure, smokeDetection,
											gasDetection);
									if (eventAttribute.equals("Temperature")) {
										labSpace.setTemperature(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Humidity")) {
										labSpace.setHumidity(Double.parseDouble(sensorData));
									} else if (eventAttribute.equals("Oxygen Level")) {
										labSpace.setOxygenLevel(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Pressure")) {
										labSpace.setPressure(Double.parseDouble(sensorData));
									} else if (eventAttribute.equals("Smoke")) {
										labSpace.setSmokeDetection(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Gas")) {
										labSpace.setGasDetection(Double
												.parseDouble(sensorData));
									}
									try {
										Socket s = new Socket("localhost", 2002);
										OutputStream os = s.getOutputStream();
										ObjectOutputStream oos = new ObjectOutputStream(os);

										oos.writeUTF(entity);
										oos.writeUTF(locationNumber);
										oos.writeUTF(source);
										oos.writeObject(labSpace);
										// oos.writeObject(new
										// String("another object from the client"));
										oos.close();
										os.close();
										s.close();

										
									} catch (Exception e1) {
										System.out.println(e1);
										JOptionPane.showMessageDialog(null,
										"Socket Error ",
										"Error Message", JOptionPane.ERROR_MESSAGE);

									}

								} else if (location.equals("EquipSpace")) {
									String entity = "equipSpace";

									double temperature = 0;
									double humidity = 0;
									double oxygenLevel = 0;
									double pressure = 0;
									double smokeDetection = 0;
									double gasDetection = 0;
									equipStorageSpace equipSpace = new equipStorageSpace(
											temperature, humidity, oxygenLevel, pressure,
											smokeDetection, gasDetection);
									if (eventAttribute.equals("Temperature")) {
										equipSpace.setTemperature(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Humidity")) {
										equipSpace.setHumidity(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Oxygen Level")) {
										equipSpace.setOxygenLevel(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Pressure")) {
										equipSpace.setPressure(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Smoke")) {
										equipSpace.setSmokeDetection(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Gas")) {
										equipSpace.setGasDetection(Double
												.parseDouble(sensorData));
									}
									try {
										Socket s = new Socket("localhost", 2002);
										OutputStream os = s.getOutputStream();
										ObjectOutputStream oos = new ObjectOutputStream(os);

										oos.writeUTF(entity);
										oos.writeUTF(locationNumber);
										oos.writeUTF(source);
										oos.writeObject(equipSpace);
										// oos.writeObject(new
										// String("another object from the client"));
										oos.close();
										os.close();
										s.close();

										

									} catch (Exception e1) {
										System.out.println(e1);
										JOptionPane.showMessageDialog(null,
										"Socket Error ",
										"Error Message", JOptionPane.ERROR_MESSAGE);

									}

								} else if (location.equals("WellArea")) {
									String entity = "wellArea";

									double temperature = 0;
									double humidity = 0;
									double oxygenLevel = 0;
									double pressure = 0;
									double smokeDetection = 0;
									double gasDetection = 0;
									belowWellArea wellArea = new belowWellArea(temperature,
											humidity, oxygenLevel, pressure,
											smokeDetection, gasDetection);
									if (eventAttribute.equals("Temperature")) {
										wellArea.setTemperature(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Humidity")) {
										wellArea.setHumidity(Double.parseDouble(sensorData));
									} else if (eventAttribute.equals("Oxygen Level")) {
										wellArea.setOxygenLevel(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Pressure")) {
										wellArea.setPressure(Double.parseDouble(sensorData));
									} else if (eventAttribute.equals("Smoke")) {
										wellArea.setSmokeDetection(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Gas")) {
										wellArea.setGasDetection(Double
												.parseDouble(sensorData));
									}
									try {
										Socket s = new Socket("localhost", 2002);
										OutputStream os = s.getOutputStream();
										ObjectOutputStream oos = new ObjectOutputStream(os);

										oos.writeUTF(entity);
										oos.writeUTF(locationNumber);
										oos.writeUTF(source);
										oos.writeObject(wellArea);
										// oos.writeObject(new
										// String("another object from the client"));
										oos.close();
										os.close();
										s.close();

										

									} catch (Exception e1) {
										System.out.println(e1);
										JOptionPane.showMessageDialog(null,
										"Socket Error ",
										"Error Message", JOptionPane.ERROR_MESSAGE);

									}

								} else if (location.equals("HeatingArea")) {
									String entity = "heatingArea";

									double temperature = 0;
									double humidity = 0;
									double oxygenLevel = 0;
									double pressure = 0;
									double smokeDetection = 0;
									double gasDetection = 0;
									belowHeatingArea heatingArea = new belowHeatingArea(
											temperature, humidity, oxygenLevel, pressure,
											smokeDetection, gasDetection);
									if (eventAttribute.equals("Temperature")) {
										heatingArea.setTemperature(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Humidity")) {
										heatingArea.setHumidity(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Oxygen Level")) {
										heatingArea.setOxygenLevel(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Pressure")) {
										heatingArea.setPressure(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Smoke")) {
										heatingArea.setSmokeDetection(Double
												.parseDouble(sensorData));
									} else if (eventAttribute.equals("Gas")) {
										heatingArea.setGasDetection(Double
												.parseDouble(sensorData));
									}
									try {
										Socket s = new Socket("localhost", 2002);
										OutputStream os = s.getOutputStream();
										ObjectOutputStream oos = new ObjectOutputStream(os);

										oos.writeUTF(entity);
										oos.writeUTF(locationNumber);
										oos.writeUTF(source);
										oos.writeObject(heatingArea);
										// oos.writeObject(new
										// String("another object from the client"));
										oos.close();
										os.close();
										s.close();

										

									} catch (Exception e1) {
										System.out.println(e1);
										JOptionPane.showMessageDialog(null,
										"Socket Error ",
										"Error Message", JOptionPane.ERROR_MESSAGE);

									}

								} else if (location.equals("ExternalWall")) {
									String entity = "externalWall";

									double externalWallTemp = 0;
									double outsideTemp = 0;

									externalWall externalWall = new externalWall(
											externalWallTemp, outsideTemp);
									if (eventAttribute.equals("Temperature")) {
										externalWall.setExternalWallTemp(Double
												.parseDouble(sensorData));
									}

									try {
										Socket s = new Socket("localhost", 2002);
										OutputStream os = s.getOutputStream();
										ObjectOutputStream oos = new ObjectOutputStream(os);

										oos.writeUTF(entity);
										oos.writeUTF(locationNumber);
										oos.writeUTF(source);
										oos.writeObject(externalWall);
										// oos.writeObject(new
										// String("another object from the client"));
										oos.close();
										os.close();
										s.close();


									} catch (Exception e1) {
										System.out.println(e1);
										JOptionPane.showMessageDialog(null,
										"Socket Error ",
										"Error Message", JOptionPane.ERROR_MESSAGE);

									}
									


								}
								
							}
						}, 0, 2000);
					
					
				}
			}
		});
		database_simulate.setBounds(32, 261, 117, 29);
		panel_7.add(database_simulate);
		refresh = new JButton("Refresh");
		refresh.setBounds(147, 261, 117, 29);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		panel_7.add(refresh);
		
		JLabel lblCameraOperationIndicator = new JLabel("Camera Operation Indicator");
		lblCameraOperationIndicator.setHorizontalAlignment(SwingConstants.CENTER);
		lblCameraOperationIndicator.setForeground(Color.MAGENTA);
		lblCameraOperationIndicator.setFont(new Font("Kokonor", Font.PLAIN, 19));
		lblCameraOperationIndicator.setBounds(76, 291, 243, 32);
		panel_7.add(lblCameraOperationIndicator);
		
		operation_indicator = new JTextArea();
		operation_indicator.setEditable(false);
		operation_indicator.setBounds(6, 321, 396, 195);
//		panel_7.add(operation_indicator);
		JScrollPane scroll = new JScrollPane(operation_indicator);
		scroll.setBounds(6, 321, 396, 195);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    panel_7.add(scroll);
	    
	    JButton cancel = new JButton("Pause");
	    cancel.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		timer.cancel();
	    	}
	    });
	    cancel.setBounds(261, 261, 117, 29);
	    panel_7.add(cancel);
		refreshTable();
	}
}
