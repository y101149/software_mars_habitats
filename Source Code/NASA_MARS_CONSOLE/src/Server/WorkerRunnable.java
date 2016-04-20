package Server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Entity.belowHeatingArea;
import Entity.belowWellArea;
import Entity.commandSpace;
import Entity.equipStorageSpace;
import Entity.externalWall;
import Entity.labSpace;
import Entity.livingCell;
import GUI.LoginGUI;
import GUI.MainFrame;
import JDBC.JDBCConnection;


/**

 */
public class WorkerRunnable implements Runnable {

	int lightWidth = 30;
	int lightHeight = 30;
	public JScrollPane scrollPane = new JScrollPane();
	public JTable table;
	public JButton refresh = new JButton("Refresh");
	public String[][] data;

	protected Socket clientSocket = null;
	protected String serverText = null;

	InputStream input;
	ObjectInputStream ois;

	public void outsideTemperatureShoudShow() {
		if (staticInfo.livingCell1.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell2.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell3.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell4.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell5.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell6.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell7.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell8.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell9.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell10.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell11.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell12.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell13.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell14.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell15.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.commandSpace1.getErrorMessage().equals(
						"EMERGENCY")
				|| staticInfo.commandSpace2.getErrorMessage().equals(
						"EMERGENCY")
				|| staticInfo.labSpace1.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.labSpace2.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.labSpace3.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.labSpace4.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.equipSpace.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.wellArea.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.heatingArea.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.externalWall.getErrorMessage()
						.equals("EMERGENCY")) {
			LoginGUI.mframe.externalWall_outsideTemp.setText("--");
		} else {
			LoginGUI.mframe.externalWall_outsideTemp.setText(Double
					.toString(staticInfo.externalWall.getOutsiteTemp()));
		}
	}

	public static void alarmShouldStop() {
		if (staticInfo.livingCell1.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell2.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell3.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell4.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell5.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell6.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell7.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell8.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell9.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.livingCell10.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell11.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell12.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell13.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell14.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.livingCell15.getErrorMessage()
						.equals("EMERGENCY")
				|| staticInfo.commandSpace1.getErrorMessage().equals(
						"EMERGENCY")
				|| staticInfo.commandSpace2.getErrorMessage().equals(
						"EMERGENCY")
				|| staticInfo.labSpace1.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.labSpace2.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.labSpace3.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.labSpace4.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.equipSpace.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.wellArea.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.heatingArea.getErrorMessage().equals("EMERGENCY")
				|| staticInfo.externalWall.getErrorMessage()
						.equals("EMERGENCY")) {
		} else {
			LoginGUI.mframe.alarm.close();
		}
	}

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

	public WorkerRunnable(Socket clientSocket, String serverText) {
		this.clientSocket = clientSocket;
		this.serverText = serverText;
	}

	public void run() {
		try {
			input = clientSocket.getInputStream();
			ois = new ObjectInputStream(input);
			String identifier = ois.readUTF();
			// System.out.println(identifier);
			String no = ois.readUTF();
			// System.out.println(no);
			String source = ois.readUTF();
			if (identifier.equals("livingCell")) {
				if (no.equals("1")) {
					processLivingCell1(source);

				} else if (no.equals("2")) {
					processLivingCell2(source);
				} else if (no.equals("3")) {
					processLivingCell3(source);
				} else if (no.equals("4")) {
					processLivingCell4(source);
				} else if (no.equals("5")) {
					processLivingCell5(source);
				} else if (no.equals("6")) {
					processLivingCell6(source);
				} else if (no.equals("7")) {
					processLivingCell7(source);
				} else if (no.equals("8")) {
					processLivingCell8(source);
				} else if (no.equals("9")) {
					processLivingCell9(source);
				} else if (no.equals("10")) {
					processLivingCell10(source);
				} else if (no.equals("11")) {
					processLivingCell11(source);
				} else if (no.equals("12")) {
					processLivingCell12(source);
				} else if (no.equals("13")) {
					processLivingCell13(source);
				} else if (no.equals("14")) {
					processLivingCell14(source);
				} else if (no.equals("15")) {
					processLivingCell15(source);
				}

			} else if (identifier.equals("commandSpace")) {
				if (no.equals("1")) {
					processCommandSpace1(source);
				} else if (no.equals("2")) {
					processCommandSpace2(source);
				}
			} else if (identifier.equals("labSpace")) {
				if (no.equals("1")) {
					processLabSpace1(source);
				} else if (no.equals("2")) {
					processLabSpace2(source);
				} else if (no.equals("3")) {
					processLabSpace3(source);
				} else if (no.equals("4")) {
					processLabSpace4(source);
				}

			} else if (identifier.equals("equipSpace")) {
				processEquipStorage(source);
			} else if (identifier.equals("wellArea")) {
				processWellArea(source);
			} else if (identifier.equals("heatingArea")) {
				processHeatingArea(source);
			} else if (identifier.equals("externalWall")) {
				processExternalWall(source);
			}
			outsideTemperatureShoudShow();

			input.close();

		} catch (Exception e) {
			// report exception somewhere.
			e.printStackTrace();
		}

	}

	public void processExternalWall(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.externalWall = (externalWall) ois.readObject();
				System.out.println(staticInfo.externalWall.getExternalWallTemp());
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// set data
			LoginGUI.mframe.externalWall_extemperture.setText(Double
					.toString(staticInfo.externalWall.getExternalWallTemp()));
			LoginGUI.mframe.externalWall_outsideTemp.setText(Double
					.toString(staticInfo.externalWall.getOutsiteTemp()));

			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 1;
			String locationVar = "ExternalWall";
			int locationNumberVar = 1;
			externalWall entity = staticInfo.externalWall;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getExternalWallTemp();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"EMERGENCY")) {
				staticInfo.externalWall.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.externalWall_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"ERROR")) {
				staticInfo.externalWall.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.externalWall_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"WARNING")) {
				staticInfo.externalWall.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.externalWall_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.externalWall.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.externalWall_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			}
			if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.externalWall_extemperture
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";
			} else if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"ERROR")) {
				LoginGUI.mframe.externalWall_extemperture
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"WARNING")) {
				LoginGUI.mframe.externalWall_extemperture
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.externalWall_extemperture
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {
				try {
					externalWall temp = (externalWall) ois.readObject();
					staticInfo.externalWall.setExternalWallTemp(temp
							.getExternalWallTemp());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"EMERGENCY")) {
				System.out.println(222);
				staticInfo.externalWall.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.externalWall_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals("ERROR")
					) {
				staticInfo.externalWall.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.externalWall_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals("WARNING")) {
				staticInfo.externalWall.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.externalWall_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.externalWall.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.externalWall_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			}

			// set data
			LoginGUI.mframe.externalWall_extemperture.setText(Double
					.toString(staticInfo.externalWall.getExternalWallTemp()));

			if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.externalWall_extemperture
						.setForeground(Color.red);
			} else if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"ERROR")) {
				LoginGUI.mframe.externalWall_extemperture
						.setForeground(Color.pink);
			} else if (staticInfo.externalWall.checkForTemperature(
					staticInfo.externalWall.getExternalWallTemp()).equals(
					"WARNING")) {
				LoginGUI.mframe.externalWall_extemperture
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.externalWall_extemperture
						.setForeground(Color.black);
			}

		}

	}

	public void processHeatingArea(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.heatingArea = (belowHeatingArea) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.heatingArea.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "HeatingArea";
			int locationNumberVar = 1;
			belowHeatingArea entity = staticInfo.heatingArea;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.heatingArea.checkForHumidity(
							staticInfo.heatingArea.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.heatingArea.checkForOxygenLevel(
							staticInfo.heatingArea.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.heatingArea.checkForPressure(
							staticInfo.heatingArea.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.heatingArea.checkForSmoke(
							staticInfo.heatingArea.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.heatingArea.checkForGas(
							staticInfo.heatingArea.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.heatingArea.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.heatingArea_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature()).equals("ERROR")
					|| staticInfo.heatingArea.checkForHumidity(
							staticInfo.heatingArea.getHumidity()).equals(
							"ERROR")
					|| staticInfo.heatingArea.checkForOxygenLevel(
							staticInfo.heatingArea.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.heatingArea.checkForPressure(
							staticInfo.heatingArea.getPressure()).equals(
							"ERROR")
					|| staticInfo.heatingArea.checkForSmoke(
							staticInfo.heatingArea.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.heatingArea.checkForGas(
							staticInfo.heatingArea.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.heatingArea.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.heatingArea_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature()).equals("WARNING")
					|| staticInfo.heatingArea.checkForHumidity(
							staticInfo.heatingArea.getHumidity()).equals(
							"WARNING")
					|| staticInfo.heatingArea.checkForOxygenLevel(
							staticInfo.heatingArea.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.heatingArea.checkForPressure(
							staticInfo.heatingArea.getPressure()).equals(
							"WARNING")
					|| staticInfo.heatingArea.checkForSmoke(
							staticInfo.heatingArea.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.heatingArea.checkForGas(
							staticInfo.heatingArea.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.heatingArea.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.heatingArea_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.heatingArea.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.heatingArea_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.heatingArea_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.heatingArea_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.heatingArea_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.heatingArea_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.heatingArea.checkForHumidity(
					staticInfo.heatingArea.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.heatingArea_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.heatingArea.checkForHumidity(
					staticInfo.heatingArea.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.heatingArea_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.heatingArea.checkForHumidity(
					staticInfo.heatingArea.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.heatingArea_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.heatingArea_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.heatingArea.checkForOxygenLevel(
					staticInfo.heatingArea.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.heatingArea.checkForOxygenLevel(
					staticInfo.heatingArea.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.heatingArea.checkForOxygenLevel(
					staticInfo.heatingArea.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.heatingArea.checkForPressure(
					staticInfo.heatingArea.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.heatingArea_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.heatingArea.checkForPressure(
					staticInfo.heatingArea.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.heatingArea_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.heatingArea.checkForPressure(
					staticInfo.heatingArea.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.heatingArea_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.heatingArea_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.heatingArea.checkForSmoke(
					staticInfo.heatingArea.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.heatingArea_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.heatingArea_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.heatingArea.checkForGas(
					staticInfo.heatingArea.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.heatingArea_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.heatingArea_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					belowHeatingArea temp = (belowHeatingArea) ois.readObject();
					staticInfo.heatingArea
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.heatingArea_temperature.setText(Double
						.toString(staticInfo.heatingArea.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					belowHeatingArea temp = (belowHeatingArea) ois.readObject();
					staticInfo.heatingArea.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.heatingArea_humidity.setText(Double
						.toString(staticInfo.heatingArea.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					belowHeatingArea temp = (belowHeatingArea) ois.readObject();
					staticInfo.heatingArea
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.heatingArea_oxygen.setText(Double
						.toString(staticInfo.heatingArea.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					belowHeatingArea temp = (belowHeatingArea) ois.readObject();
					staticInfo.heatingArea.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.heatingArea_pressure.setText(Double
						.toString(staticInfo.heatingArea.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					belowHeatingArea temp = (belowHeatingArea) ois.readObject();
					staticInfo.heatingArea.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.heatingArea_smoke.setText(Double
						.toString(staticInfo.heatingArea.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					belowHeatingArea temp = (belowHeatingArea) ois.readObject();
					staticInfo.heatingArea.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.heatingArea_gas.setText(Double
						.toString(staticInfo.heatingArea.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.heatingArea.checkForHumidity(
							staticInfo.heatingArea.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.heatingArea.checkForOxygenLevel(
							staticInfo.heatingArea.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.heatingArea.checkForPressure(
							staticInfo.heatingArea.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.heatingArea.checkForSmoke(
							staticInfo.heatingArea.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.heatingArea.checkForGas(
							staticInfo.heatingArea.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.heatingArea.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.heatingArea_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature()).equals("ERROR")
					|| staticInfo.heatingArea.checkForHumidity(
							staticInfo.heatingArea.getHumidity()).equals(
							"ERROR")
					|| staticInfo.heatingArea.checkForOxygenLevel(
							staticInfo.heatingArea.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.heatingArea.checkForPressure(
							staticInfo.heatingArea.getPressure()).equals(
							"ERROR")
					|| staticInfo.heatingArea.checkForSmoke(
							staticInfo.heatingArea.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.heatingArea.checkForGas(
							staticInfo.heatingArea.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.heatingArea.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.heatingArea_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature()).equals("WARNING")
					|| staticInfo.heatingArea.checkForHumidity(
							staticInfo.heatingArea.getHumidity()).equals(
							"WARNING")
					|| staticInfo.heatingArea.checkForOxygenLevel(
							staticInfo.heatingArea.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.heatingArea.checkForPressure(
							staticInfo.heatingArea.getPressure()).equals(
							"WARNING")
					|| staticInfo.heatingArea.checkForSmoke(
							staticInfo.heatingArea.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.heatingArea.checkForGas(
							staticInfo.heatingArea.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.heatingArea.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.heatingArea_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.heatingArea.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.heatingArea_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.heatingArea_temperature
						.setForeground(Color.red);
			} else if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.heatingArea_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.heatingArea.checkForTemperature(
					staticInfo.heatingArea.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.heatingArea_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.heatingArea_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.heatingArea.checkForHumidity(
					staticInfo.heatingArea.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.heatingArea_humidity.setForeground(Color.red);
			} else if (staticInfo.heatingArea.checkForHumidity(
					staticInfo.heatingArea.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.heatingArea_humidity.setForeground(Color.pink);
			} else if (staticInfo.heatingArea.checkForHumidity(
					staticInfo.heatingArea.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.heatingArea_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.heatingArea_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.heatingArea.checkForOxygenLevel(
					staticInfo.heatingArea.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.red);
			} else if (staticInfo.heatingArea.checkForOxygenLevel(
					staticInfo.heatingArea.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.pink);
			} else if (staticInfo.heatingArea.checkForOxygenLevel(
					staticInfo.heatingArea.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.heatingArea_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.heatingArea.checkForPressure(
					staticInfo.heatingArea.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.heatingArea_pressure.setForeground(Color.red);
			} else if (staticInfo.heatingArea.checkForPressure(
					staticInfo.heatingArea.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.heatingArea_pressure.setForeground(Color.pink);
			} else if (staticInfo.heatingArea.checkForPressure(
					staticInfo.heatingArea.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.heatingArea_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.heatingArea_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.heatingArea.checkForSmoke(
					staticInfo.heatingArea.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.heatingArea_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.heatingArea_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.heatingArea.checkForGas(
					staticInfo.heatingArea.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.heatingArea_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.heatingArea_gas.setForeground(Color.black);
			}
		}

	}

	public void processWellArea(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.wellArea = (belowWellArea) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.wellArea.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "WellArea";
			int locationNumberVar = 1;
			belowWellArea entity = staticInfo.wellArea;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("EMERGENCY")
					|| staticInfo.wellArea.checkForHumidity(
							staticInfo.wellArea.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.wellArea.checkForOxygenLevel(
							staticInfo.wellArea.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.wellArea.checkForPressure(
							staticInfo.wellArea.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.wellArea.checkForSmoke(
							staticInfo.wellArea.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.wellArea.checkForGas(
							staticInfo.wellArea.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.wellArea.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.wellArea_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("ERROR")
					|| staticInfo.wellArea.checkForHumidity(
							staticInfo.wellArea.getHumidity()).equals("ERROR")
					|| staticInfo.wellArea.checkForOxygenLevel(
							staticInfo.wellArea.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.wellArea.checkForPressure(
							staticInfo.wellArea.getPressure()).equals("ERROR")
					|| staticInfo.wellArea.checkForSmoke(
							staticInfo.wellArea.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.wellArea.checkForGas(
							staticInfo.wellArea.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.wellArea.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.wellArea_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("WARNING")
					|| staticInfo.wellArea.checkForHumidity(
							staticInfo.wellArea.getHumidity())
							.equals("WARNING")
					|| staticInfo.wellArea.checkForOxygenLevel(
							staticInfo.wellArea.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.wellArea.checkForPressure(
							staticInfo.wellArea.getPressure())
							.equals("WARNING")
					|| staticInfo.wellArea.checkForSmoke(
							staticInfo.wellArea.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.wellArea.checkForGas(
							staticInfo.wellArea.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.wellArea.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.wellArea_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.wellArea.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.wellArea_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_temperature.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.wellArea_temperature.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.wellArea_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.wellArea_temperature.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.wellArea.checkForHumidity(
					staticInfo.wellArea.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.wellArea.checkForHumidity(
					staticInfo.wellArea.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.wellArea_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.wellArea.checkForHumidity(
					staticInfo.wellArea.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.wellArea_humidity.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.wellArea_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.wellArea.checkForOxygenLevel(
					staticInfo.wellArea.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.wellArea.checkForOxygenLevel(
					staticInfo.wellArea.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.wellArea_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.wellArea.checkForOxygenLevel(
					staticInfo.wellArea.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.wellArea_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.wellArea_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.wellArea.checkForPressure(
					staticInfo.wellArea.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.wellArea.checkForPressure(
					staticInfo.wellArea.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.wellArea_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.wellArea.checkForPressure(
					staticInfo.wellArea.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.wellArea_pressure.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.wellArea_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.wellArea.checkForSmoke(
					staticInfo.wellArea.getSmokeDetection())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.wellArea_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.wellArea.checkForGas(
					staticInfo.wellArea.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.wellArea_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					belowWellArea temp = (belowWellArea) ois.readObject();
					staticInfo.wellArea.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.wellArea_temperature.setText(Double
						.toString(staticInfo.wellArea.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					belowWellArea temp = (belowWellArea) ois.readObject();
					staticInfo.wellArea.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.wellArea_humidity.setText(Double
						.toString(staticInfo.wellArea.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					belowWellArea temp = (belowWellArea) ois.readObject();
					staticInfo.wellArea.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.wellArea_oxygen.setText(Double
						.toString(staticInfo.wellArea.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					belowWellArea temp = (belowWellArea) ois.readObject();
					staticInfo.wellArea.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.wellArea_pressure.setText(Double
						.toString(staticInfo.wellArea.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					belowWellArea temp = (belowWellArea) ois.readObject();
					staticInfo.wellArea.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.wellArea_smoke.setText(Double
						.toString(staticInfo.wellArea.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					belowWellArea temp = (belowWellArea) ois.readObject();
					staticInfo.wellArea.setGasDetection(temp.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.wellArea_gas.setText(Double
						.toString(staticInfo.wellArea.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("EMERGENCY")
					|| staticInfo.wellArea.checkForHumidity(
							staticInfo.wellArea.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.wellArea.checkForOxygenLevel(
							staticInfo.wellArea.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.wellArea.checkForPressure(
							staticInfo.wellArea.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.wellArea.checkForSmoke(
							staticInfo.wellArea.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.wellArea.checkForGas(
							staticInfo.wellArea.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.wellArea.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.wellArea_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("ERROR")
					|| staticInfo.wellArea.checkForHumidity(
							staticInfo.wellArea.getHumidity()).equals("ERROR")
					|| staticInfo.wellArea.checkForOxygenLevel(
							staticInfo.wellArea.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.wellArea.checkForPressure(
							staticInfo.wellArea.getPressure()).equals("ERROR")
					|| staticInfo.wellArea.checkForSmoke(
							staticInfo.wellArea.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.wellArea.checkForGas(
							staticInfo.wellArea.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.wellArea.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.wellArea_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("WARNING")
					|| staticInfo.wellArea.checkForHumidity(
							staticInfo.wellArea.getHumidity())
							.equals("WARNING")
					|| staticInfo.wellArea.checkForOxygenLevel(
							staticInfo.wellArea.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.wellArea.checkForPressure(
							staticInfo.wellArea.getPressure())
							.equals("WARNING")
					|| staticInfo.wellArea.checkForSmoke(
							staticInfo.wellArea.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.wellArea.checkForGas(
							staticInfo.wellArea.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.wellArea.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.wellArea_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.wellArea.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.wellArea_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_temperature.setForeground(Color.red);
			} else if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.wellArea_temperature.setForeground(Color.pink);
			} else if (staticInfo.wellArea.checkForTemperature(
					staticInfo.wellArea.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.wellArea_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.wellArea_temperature.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.wellArea.checkForHumidity(
					staticInfo.wellArea.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_humidity.setForeground(Color.red);
			} else if (staticInfo.wellArea.checkForHumidity(
					staticInfo.wellArea.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.wellArea_humidity.setForeground(Color.pink);
			} else if (staticInfo.wellArea.checkForHumidity(
					staticInfo.wellArea.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.wellArea_humidity.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.wellArea_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.wellArea.checkForOxygenLevel(
					staticInfo.wellArea.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_oxygen.setForeground(Color.red);
			} else if (staticInfo.wellArea.checkForOxygenLevel(
					staticInfo.wellArea.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.wellArea_oxygen.setForeground(Color.pink);
			} else if (staticInfo.wellArea.checkForOxygenLevel(
					staticInfo.wellArea.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.wellArea_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.wellArea_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.wellArea.checkForPressure(
					staticInfo.wellArea.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_pressure.setForeground(Color.red);
			} else if (staticInfo.wellArea.checkForPressure(
					staticInfo.wellArea.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.wellArea_pressure.setForeground(Color.pink);
			} else if (staticInfo.wellArea.checkForPressure(
					staticInfo.wellArea.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.wellArea_pressure.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.wellArea_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.wellArea.checkForSmoke(
					staticInfo.wellArea.getSmokeDetection())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.wellArea_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.wellArea.checkForGas(
					staticInfo.wellArea.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.wellArea_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.wellArea_gas.setForeground(Color.black);
			}
		}

	}

	public void processEquipStorage(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.equipSpace = (equipStorageSpace) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.equipSpace.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "EquipSpace";
			int locationNumberVar = 1;
			equipStorageSpace entity = staticInfo.equipSpace;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("EMERGENCY")
					|| staticInfo.equipSpace.checkForHumidity(
							staticInfo.equipSpace.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.equipSpace.checkForOxygenLevel(
							staticInfo.equipSpace.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.equipSpace.checkForPressure(
							staticInfo.equipSpace.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.equipSpace.checkForSmoke(
							staticInfo.equipSpace.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.equipSpace.checkForGas(
							staticInfo.equipSpace.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.equipSpace.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.equipSpace_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("ERROR")
					|| staticInfo.equipSpace.checkForHumidity(
							staticInfo.equipSpace.getHumidity())
							.equals("ERROR")
					|| staticInfo.equipSpace.checkForOxygenLevel(
							staticInfo.equipSpace.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.equipSpace.checkForPressure(
							staticInfo.equipSpace.getPressure())
							.equals("ERROR")
					|| staticInfo.equipSpace.checkForSmoke(
							staticInfo.equipSpace.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.equipSpace.checkForGas(
							staticInfo.equipSpace.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.equipSpace.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.equipSpace_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("WARNING")
					|| staticInfo.equipSpace.checkForHumidity(
							staticInfo.equipSpace.getHumidity()).equals(
							"WARNING")
					|| staticInfo.equipSpace.checkForOxygenLevel(
							staticInfo.equipSpace.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.equipSpace.checkForPressure(
							staticInfo.equipSpace.getPressure()).equals(
							"WARNING")
					|| staticInfo.equipSpace.checkForSmoke(
							staticInfo.equipSpace.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.equipSpace.checkForGas(
							staticInfo.equipSpace.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.equipSpace.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.equipSpace_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.equipSpace.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.equipSpace_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_temperature.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.equipSpace_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.equipSpace_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.equipSpace_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.equipSpace.checkForHumidity(
					staticInfo.equipSpace.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.equipSpace.checkForHumidity(
					staticInfo.equipSpace.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.equipSpace_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.equipSpace.checkForHumidity(
					staticInfo.equipSpace.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.equipSpace_humidity.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.equipSpace_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.equipSpace.checkForOxygenLevel(
					staticInfo.equipSpace.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.equipSpace.checkForOxygenLevel(
					staticInfo.equipSpace.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.equipSpace.checkForOxygenLevel(
					staticInfo.equipSpace.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.equipSpace.checkForPressure(
					staticInfo.equipSpace.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.equipSpace.checkForPressure(
					staticInfo.equipSpace.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.equipSpace_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.equipSpace.checkForPressure(
					staticInfo.equipSpace.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.equipSpace_pressure.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.equipSpace_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.equipSpace.checkForSmoke(
					staticInfo.equipSpace.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.equipSpace_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.equipSpace_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.equipSpace.checkForGas(
					staticInfo.equipSpace.getGasDetection())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.equipSpace_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					equipStorageSpace temp = (equipStorageSpace) ois.readObject();
					staticInfo.equipSpace.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.equipSpace_temperature.setText(Double
						.toString(staticInfo.equipSpace.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					equipStorageSpace temp = (equipStorageSpace) ois.readObject();
					staticInfo.equipSpace.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.equipSpace_humidity.setText(Double
						.toString(staticInfo.equipSpace.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					equipStorageSpace temp = (equipStorageSpace) ois.readObject();
					staticInfo.equipSpace.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.equipSpace_oxygen.setText(Double
						.toString(staticInfo.equipSpace.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					equipStorageSpace temp = (equipStorageSpace) ois.readObject();
					staticInfo.equipSpace.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.equipSpace_pressure.setText(Double
						.toString(staticInfo.equipSpace.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					equipStorageSpace temp = (equipStorageSpace) ois.readObject();
					staticInfo.equipSpace.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.equipSpace_smoke.setText(Double
						.toString(staticInfo.equipSpace.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					equipStorageSpace temp = (equipStorageSpace) ois.readObject();
					staticInfo.equipSpace.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.equipSpace_gas.setText(Double
						.toString(staticInfo.equipSpace.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("EMERGENCY")
					|| staticInfo.equipSpace.checkForHumidity(
							staticInfo.equipSpace.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.equipSpace.checkForOxygenLevel(
							staticInfo.equipSpace.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.equipSpace.checkForPressure(
							staticInfo.equipSpace.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.equipSpace.checkForSmoke(
							staticInfo.equipSpace.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.equipSpace.checkForGas(
							staticInfo.equipSpace.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.equipSpace.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.equipSpace_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("ERROR")
					|| staticInfo.equipSpace.checkForHumidity(
							staticInfo.equipSpace.getHumidity())
							.equals("ERROR")
					|| staticInfo.equipSpace.checkForOxygenLevel(
							staticInfo.equipSpace.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.equipSpace.checkForPressure(
							staticInfo.equipSpace.getPressure())
							.equals("ERROR")
					|| staticInfo.equipSpace.checkForSmoke(
							staticInfo.equipSpace.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.equipSpace.checkForGas(
							staticInfo.equipSpace.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.equipSpace.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.equipSpace_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("WARNING")
					|| staticInfo.equipSpace.checkForHumidity(
							staticInfo.equipSpace.getHumidity()).equals(
							"WARNING")
					|| staticInfo.equipSpace.checkForOxygenLevel(
							staticInfo.equipSpace.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.equipSpace.checkForPressure(
							staticInfo.equipSpace.getPressure()).equals(
							"WARNING")
					|| staticInfo.equipSpace.checkForSmoke(
							staticInfo.equipSpace.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.equipSpace.checkForGas(
							staticInfo.equipSpace.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.equipSpace.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.equipSpace_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.equipSpace.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.equipSpace_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_temperature.setForeground(Color.red);
			} else if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.equipSpace_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.equipSpace.checkForTemperature(
					staticInfo.equipSpace.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.equipSpace_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.equipSpace_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.equipSpace.checkForHumidity(
					staticInfo.equipSpace.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_humidity.setForeground(Color.red);
			} else if (staticInfo.equipSpace.checkForHumidity(
					staticInfo.equipSpace.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.equipSpace_humidity.setForeground(Color.pink);
			} else if (staticInfo.equipSpace.checkForHumidity(
					staticInfo.equipSpace.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.equipSpace_humidity.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.equipSpace_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.equipSpace.checkForOxygenLevel(
					staticInfo.equipSpace.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.red);
			} else if (staticInfo.equipSpace.checkForOxygenLevel(
					staticInfo.equipSpace.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.pink);
			} else if (staticInfo.equipSpace.checkForOxygenLevel(
					staticInfo.equipSpace.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.equipSpace_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.equipSpace.checkForPressure(
					staticInfo.equipSpace.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_pressure.setForeground(Color.red);
			} else if (staticInfo.equipSpace.checkForPressure(
					staticInfo.equipSpace.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.equipSpace_pressure.setForeground(Color.pink);
			} else if (staticInfo.equipSpace.checkForPressure(
					staticInfo.equipSpace.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.equipSpace_pressure.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.equipSpace_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.equipSpace.checkForSmoke(
					staticInfo.equipSpace.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.equipSpace_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.equipSpace_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.equipSpace.checkForGas(
					staticInfo.equipSpace.getGasDetection())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.equipSpace_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.equipSpace_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell1(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell1 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell1.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 1;
			livingCell entity = staticInfo.livingCell1;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell1.checkForHumidity(
							staticInfo.livingCell1.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell1.checkForOxygenLevel(
							staticInfo.livingCell1.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell1.checkForPressure(
							staticInfo.livingCell1.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell1.checkForSmoke(
							staticInfo.livingCell1.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell1.checkForGas(
							staticInfo.livingCell1.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell1.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell1_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell1.checkForHumidity(
							staticInfo.livingCell1.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell1.checkForOxygenLevel(
							staticInfo.livingCell1.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell1.checkForPressure(
							staticInfo.livingCell1.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell1.checkForSmoke(
							staticInfo.livingCell1.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell1.checkForGas(
							staticInfo.livingCell1.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell1.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell1_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell1.checkForHumidity(
							staticInfo.livingCell1.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell1.checkForOxygenLevel(
							staticInfo.livingCell1.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell1.checkForPressure(
							staticInfo.livingCell1.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell1.checkForSmoke(
							staticInfo.livingCell1.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell1.checkForGas(
							staticInfo.livingCell1.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell1.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell1.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell1_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell1_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell1_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell1_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell1.checkForHumidity(
					staticInfo.livingCell1.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell1_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell1.checkForHumidity(
					staticInfo.livingCell1.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell1_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell1.checkForHumidity(
					staticInfo.livingCell1.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell1_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell1_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell1.checkForOxygenLevel(
					staticInfo.livingCell1.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell1.checkForOxygenLevel(
					staticInfo.livingCell1.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell1.checkForOxygenLevel(
					staticInfo.livingCell1.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell1.checkForPressure(
					staticInfo.livingCell1.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell1_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell1.checkForPressure(
					staticInfo.livingCell1.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell1_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell1.checkForPressure(
					staticInfo.livingCell1.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell1_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell1_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell1.checkForSmoke(
					staticInfo.livingCell1.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell1_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell1_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell1.checkForGas(
					staticInfo.livingCell1.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell1_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell1_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell1
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell1_temperature.setText(Double
						.toString(staticInfo.livingCell1.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell1.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell1_humidity.setText(Double
						.toString(staticInfo.livingCell1.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell1
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell1_oxygen.setText(Double
						.toString(staticInfo.livingCell1.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell1.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell1_pressure.setText(Double
						.toString(staticInfo.livingCell1.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell1.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell1_smoke.setText(Double
						.toString(staticInfo.livingCell1.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell1.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell1_gas.setText(Double
						.toString(staticInfo.livingCell1.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell1.checkForHumidity(
							staticInfo.livingCell1.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell1.checkForOxygenLevel(
							staticInfo.livingCell1.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell1.checkForPressure(
							staticInfo.livingCell1.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell1.checkForSmoke(
							staticInfo.livingCell1.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell1.checkForGas(
							staticInfo.livingCell1.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell1.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell1_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell1.checkForHumidity(
							staticInfo.livingCell1.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell1.checkForOxygenLevel(
							staticInfo.livingCell1.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell1.checkForPressure(
							staticInfo.livingCell1.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell1.checkForSmoke(
							staticInfo.livingCell1.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell1.checkForGas(
							staticInfo.livingCell1.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell1.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell1_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell1.checkForHumidity(
							staticInfo.livingCell1.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell1.checkForOxygenLevel(
							staticInfo.livingCell1.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell1.checkForPressure(
							staticInfo.livingCell1.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell1.checkForSmoke(
							staticInfo.livingCell1.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell1.checkForGas(
							staticInfo.livingCell1.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell1.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell1.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell1_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell1_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell1.checkForTemperature(
					staticInfo.livingCell1.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell1_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell1_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell1.checkForHumidity(
					staticInfo.livingCell1.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell1_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell1.checkForHumidity(
					staticInfo.livingCell1.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell1_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell1.checkForHumidity(
					staticInfo.livingCell1.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell1_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell1_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell1.checkForOxygenLevel(
					staticInfo.livingCell1.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell1.checkForOxygenLevel(
					staticInfo.livingCell1.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell1.checkForOxygenLevel(
					staticInfo.livingCell1.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell1_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell1.checkForPressure(
					staticInfo.livingCell1.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell1_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell1.checkForPressure(
					staticInfo.livingCell1.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell1_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell1.checkForPressure(
					staticInfo.livingCell1.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell1_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell1_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell1.checkForSmoke(
					staticInfo.livingCell1.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell1_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell1_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell1.checkForGas(
					staticInfo.livingCell1.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell1_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell1_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell2(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell2 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell2.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 2;
			livingCell entity = staticInfo.livingCell2;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell2.checkForHumidity(
							staticInfo.livingCell2.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell2.checkForOxygenLevel(
							staticInfo.livingCell2.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell2.checkForPressure(
							staticInfo.livingCell2.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell2.checkForSmoke(
							staticInfo.livingCell2.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell2.checkForGas(
							staticInfo.livingCell2.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell2.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell2_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell2.checkForHumidity(
							staticInfo.livingCell2.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell2.checkForOxygenLevel(
							staticInfo.livingCell2.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell2.checkForPressure(
							staticInfo.livingCell2.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell2.checkForSmoke(
							staticInfo.livingCell2.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell2.checkForGas(
							staticInfo.livingCell2.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell2.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell2_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell2.checkForHumidity(
							staticInfo.livingCell2.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell2.checkForOxygenLevel(
							staticInfo.livingCell2.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell2.checkForPressure(
							staticInfo.livingCell2.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell2.checkForSmoke(
							staticInfo.livingCell2.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell2.checkForGas(
							staticInfo.livingCell2.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell2.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell2.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell2_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell2_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell2_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell2_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell2.checkForHumidity(
					staticInfo.livingCell2.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell2_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell2.checkForHumidity(
					staticInfo.livingCell2.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell2_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell2.checkForHumidity(
					staticInfo.livingCell2.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell2_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell2_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell2.checkForOxygenLevel(
					staticInfo.livingCell2.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell2.checkForOxygenLevel(
					staticInfo.livingCell2.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell2.checkForOxygenLevel(
					staticInfo.livingCell2.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell2.checkForPressure(
					staticInfo.livingCell2.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell2_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell2.checkForPressure(
					staticInfo.livingCell2.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell2_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell2.checkForPressure(
					staticInfo.livingCell2.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell2_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell2_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell2.checkForSmoke(
					staticInfo.livingCell2.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell2_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell2_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell2.checkForGas(
					staticInfo.livingCell2.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell2_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell2_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell2
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell2_temperature.setText(Double
						.toString(staticInfo.livingCell2.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell2.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell2_humidity.setText(Double
						.toString(staticInfo.livingCell2.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell2
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell2_oxygen.setText(Double
						.toString(staticInfo.livingCell2.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell2.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell2_pressure.setText(Double
						.toString(staticInfo.livingCell2.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell2.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell2_smoke.setText(Double
						.toString(staticInfo.livingCell2.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell2.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell2_gas.setText(Double
						.toString(staticInfo.livingCell2.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell2.checkForHumidity(
							staticInfo.livingCell2.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell2.checkForOxygenLevel(
							staticInfo.livingCell2.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell2.checkForPressure(
							staticInfo.livingCell2.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell2.checkForSmoke(
							staticInfo.livingCell2.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell2.checkForGas(
							staticInfo.livingCell2.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell2.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell2_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell2.checkForHumidity(
							staticInfo.livingCell2.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell2.checkForOxygenLevel(
							staticInfo.livingCell2.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell2.checkForPressure(
							staticInfo.livingCell2.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell2.checkForSmoke(
							staticInfo.livingCell2.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell2.checkForGas(
							staticInfo.livingCell2.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell2.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell2_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell2.checkForHumidity(
							staticInfo.livingCell2.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell2.checkForOxygenLevel(
							staticInfo.livingCell2.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell2.checkForPressure(
							staticInfo.livingCell2.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell2.checkForSmoke(
							staticInfo.livingCell2.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell2.checkForGas(
							staticInfo.livingCell2.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell2.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell2.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell2_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell2_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell2.checkForTemperature(
					staticInfo.livingCell2.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell2_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell2_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell2.checkForHumidity(
					staticInfo.livingCell2.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell2_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell2.checkForHumidity(
					staticInfo.livingCell2.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell2_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell2.checkForHumidity(
					staticInfo.livingCell2.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell2_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell2_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell2.checkForOxygenLevel(
					staticInfo.livingCell2.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell2.checkForOxygenLevel(
					staticInfo.livingCell2.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell2.checkForOxygenLevel(
					staticInfo.livingCell2.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell2_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell2.checkForPressure(
					staticInfo.livingCell2.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell2_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell2.checkForPressure(
					staticInfo.livingCell2.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell2_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell2.checkForPressure(
					staticInfo.livingCell2.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell2_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell2_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell2.checkForSmoke(
					staticInfo.livingCell2.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell2_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell2_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell2.checkForGas(
					staticInfo.livingCell2.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell2_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell2_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell3(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell3 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell3.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 3;
			livingCell entity = staticInfo.livingCell3;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell3.checkForHumidity(
							staticInfo.livingCell3.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell3.checkForOxygenLevel(
							staticInfo.livingCell3.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell3.checkForPressure(
							staticInfo.livingCell3.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell3.checkForSmoke(
							staticInfo.livingCell3.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell3.checkForGas(
							staticInfo.livingCell3.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell3.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell3_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell3.checkForHumidity(
							staticInfo.livingCell3.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell3.checkForOxygenLevel(
							staticInfo.livingCell3.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell3.checkForPressure(
							staticInfo.livingCell3.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell3.checkForSmoke(
							staticInfo.livingCell3.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell3.checkForGas(
							staticInfo.livingCell3.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell3.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell3_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell3.checkForHumidity(
							staticInfo.livingCell3.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell3.checkForOxygenLevel(
							staticInfo.livingCell3.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell3.checkForPressure(
							staticInfo.livingCell3.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell3.checkForSmoke(
							staticInfo.livingCell3.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell3.checkForGas(
							staticInfo.livingCell3.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell3.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell3_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell3.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell3_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell3_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell3_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell3_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell3_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell3.checkForHumidity(
					staticInfo.livingCell3.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell3_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell3.checkForHumidity(
					staticInfo.livingCell3.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell3_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell3.checkForHumidity(
					staticInfo.livingCell3.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell3_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell3_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell3.checkForOxygenLevel(
					staticInfo.livingCell3.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell3.checkForOxygenLevel(
					staticInfo.livingCell3.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell3.checkForOxygenLevel(
					staticInfo.livingCell3.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell3.checkForPressure(
					staticInfo.livingCell3.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell3_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell3.checkForPressure(
					staticInfo.livingCell3.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell3_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell3.checkForPressure(
					staticInfo.livingCell3.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell3_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell3_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell3.checkForSmoke(
					staticInfo.livingCell3.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell3_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell3_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell3.checkForGas(
					staticInfo.livingCell3.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell3_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell3_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell3
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell3_temperature.setText(Double
						.toString(staticInfo.livingCell3.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell3.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell3_humidity.setText(Double
						.toString(staticInfo.livingCell3.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell3
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell3_oxygen.setText(Double
						.toString(staticInfo.livingCell3.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell3.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell3_pressure.setText(Double
						.toString(staticInfo.livingCell3.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell3.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell3_smoke.setText(Double
						.toString(staticInfo.livingCell3.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell3.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell3_gas.setText(Double
						.toString(staticInfo.livingCell3.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell3.checkForHumidity(
							staticInfo.livingCell3.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell3.checkForOxygenLevel(
							staticInfo.livingCell3.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell3.checkForPressure(
							staticInfo.livingCell3.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell3.checkForSmoke(
							staticInfo.livingCell3.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell3.checkForGas(
							staticInfo.livingCell3.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell3.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell3_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell3.checkForHumidity(
							staticInfo.livingCell3.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell3.checkForOxygenLevel(
							staticInfo.livingCell3.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell3.checkForPressure(
							staticInfo.livingCell3.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell3.checkForSmoke(
							staticInfo.livingCell3.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell3.checkForGas(
							staticInfo.livingCell3.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell3.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell3_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell3.checkForHumidity(
							staticInfo.livingCell3.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell3.checkForOxygenLevel(
							staticInfo.livingCell3.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell3.checkForPressure(
							staticInfo.livingCell3.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell3.checkForSmoke(
							staticInfo.livingCell3.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell3.checkForGas(
							staticInfo.livingCell3.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell3.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell3_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell3.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell3_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell3_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell3_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell3.checkForTemperature(
					staticInfo.livingCell3.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell3_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell3_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell3.checkForHumidity(
					staticInfo.livingCell3.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell3_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell3.checkForHumidity(
					staticInfo.livingCell3.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell3_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell3.checkForHumidity(
					staticInfo.livingCell3.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell3_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell3_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell3.checkForOxygenLevel(
					staticInfo.livingCell3.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell3.checkForOxygenLevel(
					staticInfo.livingCell3.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell3.checkForOxygenLevel(
					staticInfo.livingCell3.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell3_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell3.checkForPressure(
					staticInfo.livingCell3.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell3_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell3.checkForPressure(
					staticInfo.livingCell3.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell3_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell3.checkForPressure(
					staticInfo.livingCell3.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell3_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell3_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell3.checkForSmoke(
					staticInfo.livingCell3.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell3_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell3_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell3.checkForGas(
					staticInfo.livingCell3.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell3_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell3_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell4(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell4 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell4.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 4;
			livingCell entity = staticInfo.livingCell4;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell4.checkForHumidity(
							staticInfo.livingCell4.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell4.checkForOxygenLevel(
							staticInfo.livingCell4.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell4.checkForPressure(
							staticInfo.livingCell4.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell4.checkForSmoke(
							staticInfo.livingCell4.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell4.checkForGas(
							staticInfo.livingCell4.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell4.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell4_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell4.checkForHumidity(
							staticInfo.livingCell4.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell4.checkForOxygenLevel(
							staticInfo.livingCell4.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell4.checkForPressure(
							staticInfo.livingCell4.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell4.checkForSmoke(
							staticInfo.livingCell4.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell4.checkForGas(
							staticInfo.livingCell4.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell4.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell4_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell4.checkForHumidity(
							staticInfo.livingCell4.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell4.checkForOxygenLevel(
							staticInfo.livingCell4.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell4.checkForPressure(
							staticInfo.livingCell4.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell4.checkForSmoke(
							staticInfo.livingCell4.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell4.checkForGas(
							staticInfo.livingCell4.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell4.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell4_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell4.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell4_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell4_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell4_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell4_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell4_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell4.checkForHumidity(
					staticInfo.livingCell4.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell4_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell4.checkForHumidity(
					staticInfo.livingCell4.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell4_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell4.checkForHumidity(
					staticInfo.livingCell4.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell4_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell4_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell4.checkForOxygenLevel(
					staticInfo.livingCell4.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell4.checkForOxygenLevel(
					staticInfo.livingCell4.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell4.checkForOxygenLevel(
					staticInfo.livingCell4.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell4.checkForPressure(
					staticInfo.livingCell4.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell4_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell4.checkForPressure(
					staticInfo.livingCell4.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell4_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell4.checkForPressure(
					staticInfo.livingCell4.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell4_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell4_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell4.checkForSmoke(
					staticInfo.livingCell4.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell4_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell4_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell4.checkForGas(
					staticInfo.livingCell4.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell4_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell4_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell4
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell4_temperature.setText(Double
						.toString(staticInfo.livingCell4.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell4.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell4_humidity.setText(Double
						.toString(staticInfo.livingCell4.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell4
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell4_oxygen.setText(Double
						.toString(staticInfo.livingCell4.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell4.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell4_pressure.setText(Double
						.toString(staticInfo.livingCell4.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell4.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell4_smoke.setText(Double
						.toString(staticInfo.livingCell4.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell4.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell4_gas.setText(Double
						.toString(staticInfo.livingCell4.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell4.checkForHumidity(
							staticInfo.livingCell4.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell4.checkForOxygenLevel(
							staticInfo.livingCell4.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell4.checkForPressure(
							staticInfo.livingCell4.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell4.checkForSmoke(
							staticInfo.livingCell4.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell4.checkForGas(
							staticInfo.livingCell4.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell4.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell4_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell4.checkForHumidity(
							staticInfo.livingCell4.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell4.checkForOxygenLevel(
							staticInfo.livingCell4.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell4.checkForPressure(
							staticInfo.livingCell4.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell4.checkForSmoke(
							staticInfo.livingCell4.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell4.checkForGas(
							staticInfo.livingCell4.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell4.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell4_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell4.checkForHumidity(
							staticInfo.livingCell4.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell4.checkForOxygenLevel(
							staticInfo.livingCell4.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell4.checkForPressure(
							staticInfo.livingCell4.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell4.checkForSmoke(
							staticInfo.livingCell4.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell4.checkForGas(
							staticInfo.livingCell4.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell4.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell4_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell4.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell4_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell4_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell4_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell4.checkForTemperature(
					staticInfo.livingCell4.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell4_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell4_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell4.checkForHumidity(
					staticInfo.livingCell4.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell4_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell4.checkForHumidity(
					staticInfo.livingCell4.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell4_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell4.checkForHumidity(
					staticInfo.livingCell4.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell4_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell4_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell4.checkForOxygenLevel(
					staticInfo.livingCell4.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell4.checkForOxygenLevel(
					staticInfo.livingCell4.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell4.checkForOxygenLevel(
					staticInfo.livingCell4.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell4_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell4.checkForPressure(
					staticInfo.livingCell4.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell4_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell4.checkForPressure(
					staticInfo.livingCell4.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell4_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell4.checkForPressure(
					staticInfo.livingCell4.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell4_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell4_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell4.checkForSmoke(
					staticInfo.livingCell4.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell4_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell4_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell4.checkForGas(
					staticInfo.livingCell4.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell4_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell4_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell5(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell5 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell5.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 5;
			livingCell entity = staticInfo.livingCell5;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell5.checkForHumidity(
							staticInfo.livingCell5.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell5.checkForOxygenLevel(
							staticInfo.livingCell5.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell5.checkForPressure(
							staticInfo.livingCell5.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell5.checkForSmoke(
							staticInfo.livingCell5.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell5.checkForGas(
							staticInfo.livingCell5.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell5.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell5_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell5.checkForHumidity(
							staticInfo.livingCell5.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell5.checkForOxygenLevel(
							staticInfo.livingCell5.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell5.checkForPressure(
							staticInfo.livingCell5.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell5.checkForSmoke(
							staticInfo.livingCell5.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell5.checkForGas(
							staticInfo.livingCell5.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell5.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell5_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell5.checkForHumidity(
							staticInfo.livingCell5.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell5.checkForOxygenLevel(
							staticInfo.livingCell5.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell5.checkForPressure(
							staticInfo.livingCell5.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell5.checkForSmoke(
							staticInfo.livingCell5.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell5.checkForGas(
							staticInfo.livingCell5.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell5.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell5_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell5.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell5_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell5_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell5_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell5_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell5_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell5.checkForHumidity(
					staticInfo.livingCell5.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell5_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell5.checkForHumidity(
					staticInfo.livingCell5.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell5_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell5.checkForHumidity(
					staticInfo.livingCell5.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell5_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell5_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell5.checkForOxygenLevel(
					staticInfo.livingCell5.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell5.checkForOxygenLevel(
					staticInfo.livingCell5.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell5.checkForOxygenLevel(
					staticInfo.livingCell5.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell5.checkForPressure(
					staticInfo.livingCell5.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell5_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell5.checkForPressure(
					staticInfo.livingCell5.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell5_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell5.checkForPressure(
					staticInfo.livingCell5.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell5_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell5_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell5.checkForSmoke(
					staticInfo.livingCell5.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell5_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell5_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell5.checkForGas(
					staticInfo.livingCell5.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell5_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell5_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell5
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell5_temperature.setText(Double
						.toString(staticInfo.livingCell5.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell5.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell5_humidity.setText(Double
						.toString(staticInfo.livingCell5.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell5
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell5_oxygen.setText(Double
						.toString(staticInfo.livingCell5.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell5.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell5_pressure.setText(Double
						.toString(staticInfo.livingCell5.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell5.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell5_smoke.setText(Double
						.toString(staticInfo.livingCell5.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell5.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell5_gas.setText(Double
						.toString(staticInfo.livingCell5.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell5.checkForHumidity(
							staticInfo.livingCell5.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell5.checkForOxygenLevel(
							staticInfo.livingCell5.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell5.checkForPressure(
							staticInfo.livingCell5.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell5.checkForSmoke(
							staticInfo.livingCell5.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell5.checkForGas(
							staticInfo.livingCell5.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell5.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell5_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell5.checkForHumidity(
							staticInfo.livingCell5.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell5.checkForOxygenLevel(
							staticInfo.livingCell5.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell5.checkForPressure(
							staticInfo.livingCell5.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell5.checkForSmoke(
							staticInfo.livingCell5.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell5.checkForGas(
							staticInfo.livingCell5.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell5.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell5_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell5.checkForHumidity(
							staticInfo.livingCell5.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell5.checkForOxygenLevel(
							staticInfo.livingCell5.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell5.checkForPressure(
							staticInfo.livingCell5.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell5.checkForSmoke(
							staticInfo.livingCell5.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell5.checkForGas(
							staticInfo.livingCell5.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell5.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell5_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell5.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell5_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell5_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell5_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell5.checkForTemperature(
					staticInfo.livingCell5.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell5_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell5_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell5.checkForHumidity(
					staticInfo.livingCell5.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell5_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell5.checkForHumidity(
					staticInfo.livingCell5.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell5_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell5.checkForHumidity(
					staticInfo.livingCell5.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell5_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell5_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell5.checkForOxygenLevel(
					staticInfo.livingCell5.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell5.checkForOxygenLevel(
					staticInfo.livingCell5.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell5.checkForOxygenLevel(
					staticInfo.livingCell5.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell5_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell5.checkForPressure(
					staticInfo.livingCell5.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell5_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell5.checkForPressure(
					staticInfo.livingCell5.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell5_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell5.checkForPressure(
					staticInfo.livingCell5.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell5_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell5_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell5.checkForSmoke(
					staticInfo.livingCell5.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell5_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell5_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell5.checkForGas(
					staticInfo.livingCell5.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell5_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell5_gas.setForeground(Color.black);
			}
		}

	}

	private void processLivingCell6(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell6 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell6.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 6;
			livingCell entity = staticInfo.livingCell6;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell6.checkForHumidity(
							staticInfo.livingCell6.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell6.checkForOxygenLevel(
							staticInfo.livingCell6.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell6.checkForPressure(
							staticInfo.livingCell6.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell6.checkForSmoke(
							staticInfo.livingCell6.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell6.checkForGas(
							staticInfo.livingCell6.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell6.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell6_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell6.checkForHumidity(
							staticInfo.livingCell6.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell6.checkForOxygenLevel(
							staticInfo.livingCell6.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell6.checkForPressure(
							staticInfo.livingCell6.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell6.checkForSmoke(
							staticInfo.livingCell6.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell6.checkForGas(
							staticInfo.livingCell6.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell6.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell6_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell6.checkForHumidity(
							staticInfo.livingCell6.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell6.checkForOxygenLevel(
							staticInfo.livingCell6.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell6.checkForPressure(
							staticInfo.livingCell6.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell6.checkForSmoke(
							staticInfo.livingCell6.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell6.checkForGas(
							staticInfo.livingCell6.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell6.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell6_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell6.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell6_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell6_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell6_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell6_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell6_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell6.checkForHumidity(
					staticInfo.livingCell6.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell6_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell6.checkForHumidity(
					staticInfo.livingCell6.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell6_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell6.checkForHumidity(
					staticInfo.livingCell6.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell6_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell6_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell6.checkForOxygenLevel(
					staticInfo.livingCell6.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell6.checkForOxygenLevel(
					staticInfo.livingCell6.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell6.checkForOxygenLevel(
					staticInfo.livingCell6.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell6.checkForPressure(
					staticInfo.livingCell6.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell6_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell6.checkForPressure(
					staticInfo.livingCell6.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell6_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell6.checkForPressure(
					staticInfo.livingCell6.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell6_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell6_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell6.checkForSmoke(
					staticInfo.livingCell6.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell6_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell6_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell6.checkForGas(
					staticInfo.livingCell6.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell6_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell6_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell6
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell6_temperature.setText(Double
						.toString(staticInfo.livingCell6.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell6.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell6_humidity.setText(Double
						.toString(staticInfo.livingCell6.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell6
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell6_oxygen.setText(Double
						.toString(staticInfo.livingCell6.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell6.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell6_pressure.setText(Double
						.toString(staticInfo.livingCell6.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell6.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell6_smoke.setText(Double
						.toString(staticInfo.livingCell6.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell6.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell6_gas.setText(Double
						.toString(staticInfo.livingCell6.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell6.checkForHumidity(
							staticInfo.livingCell6.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell6.checkForOxygenLevel(
							staticInfo.livingCell6.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell6.checkForPressure(
							staticInfo.livingCell6.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell6.checkForSmoke(
							staticInfo.livingCell6.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell6.checkForGas(
							staticInfo.livingCell6.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell6.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell6_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell6.checkForHumidity(
							staticInfo.livingCell6.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell6.checkForOxygenLevel(
							staticInfo.livingCell6.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell6.checkForPressure(
							staticInfo.livingCell6.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell6.checkForSmoke(
							staticInfo.livingCell6.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell6.checkForGas(
							staticInfo.livingCell6.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell6.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell6_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell6.checkForHumidity(
							staticInfo.livingCell6.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell6.checkForOxygenLevel(
							staticInfo.livingCell6.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell6.checkForPressure(
							staticInfo.livingCell6.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell6.checkForSmoke(
							staticInfo.livingCell6.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell6.checkForGas(
							staticInfo.livingCell6.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell6.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell6_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell6.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell6_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell6_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell6_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell6.checkForTemperature(
					staticInfo.livingCell6.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell6_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell6_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell6.checkForHumidity(
					staticInfo.livingCell6.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell6_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell6.checkForHumidity(
					staticInfo.livingCell6.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell6_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell6.checkForHumidity(
					staticInfo.livingCell6.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell6_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell6_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell6.checkForOxygenLevel(
					staticInfo.livingCell6.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell6.checkForOxygenLevel(
					staticInfo.livingCell6.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell6.checkForOxygenLevel(
					staticInfo.livingCell6.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell6_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell6.checkForPressure(
					staticInfo.livingCell6.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell6_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell6.checkForPressure(
					staticInfo.livingCell6.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell6_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell6.checkForPressure(
					staticInfo.livingCell6.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell6_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell6_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell6.checkForSmoke(
					staticInfo.livingCell6.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell6_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell6_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell6.checkForGas(
					staticInfo.livingCell6.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell6_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell6_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell7(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell7 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell7.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 7;
			livingCell entity = staticInfo.livingCell7;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell7.checkForHumidity(
							staticInfo.livingCell7.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell7.checkForOxygenLevel(
							staticInfo.livingCell7.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell7.checkForPressure(
							staticInfo.livingCell7.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell7.checkForSmoke(
							staticInfo.livingCell7.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell7.checkForGas(
							staticInfo.livingCell7.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell7.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell7_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell7.checkForHumidity(
							staticInfo.livingCell7.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell7.checkForOxygenLevel(
							staticInfo.livingCell7.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell7.checkForPressure(
							staticInfo.livingCell7.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell7.checkForSmoke(
							staticInfo.livingCell7.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell7.checkForGas(
							staticInfo.livingCell7.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell7.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell7_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell7.checkForHumidity(
							staticInfo.livingCell7.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell7.checkForOxygenLevel(
							staticInfo.livingCell7.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell7.checkForPressure(
							staticInfo.livingCell7.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell7.checkForSmoke(
							staticInfo.livingCell7.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell7.checkForGas(
							staticInfo.livingCell7.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell7.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell7_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell7.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell7_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell7_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell7_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell7_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell7_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell7.checkForHumidity(
					staticInfo.livingCell7.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell7_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell7.checkForHumidity(
					staticInfo.livingCell7.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell7_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell7.checkForHumidity(
					staticInfo.livingCell7.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell7_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell7_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell7.checkForOxygenLevel(
					staticInfo.livingCell7.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell7.checkForOxygenLevel(
					staticInfo.livingCell7.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell7.checkForOxygenLevel(
					staticInfo.livingCell7.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell7.checkForPressure(
					staticInfo.livingCell7.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell7_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell7.checkForPressure(
					staticInfo.livingCell7.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell7_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell7.checkForPressure(
					staticInfo.livingCell7.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell7_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell7_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell7.checkForSmoke(
					staticInfo.livingCell7.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell7_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell7_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell7.checkForGas(
					staticInfo.livingCell7.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell7_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell7_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell7
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell7_temperature.setText(Double
						.toString(staticInfo.livingCell7.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell7.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell7_humidity.setText(Double
						.toString(staticInfo.livingCell7.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell7
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell7_oxygen.setText(Double
						.toString(staticInfo.livingCell7.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell7.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell7_pressure.setText(Double
						.toString(staticInfo.livingCell7.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell7.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell7_smoke.setText(Double
						.toString(staticInfo.livingCell7.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell7.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell7_gas.setText(Double
						.toString(staticInfo.livingCell7.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell7.checkForHumidity(
							staticInfo.livingCell7.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell7.checkForOxygenLevel(
							staticInfo.livingCell7.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell7.checkForPressure(
							staticInfo.livingCell7.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell7.checkForSmoke(
							staticInfo.livingCell7.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell7.checkForGas(
							staticInfo.livingCell7.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell7.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell7_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell7.checkForHumidity(
							staticInfo.livingCell7.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell7.checkForOxygenLevel(
							staticInfo.livingCell7.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell7.checkForPressure(
							staticInfo.livingCell7.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell7.checkForSmoke(
							staticInfo.livingCell7.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell7.checkForGas(
							staticInfo.livingCell7.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell7.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell7_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell7.checkForHumidity(
							staticInfo.livingCell7.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell7.checkForOxygenLevel(
							staticInfo.livingCell7.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell7.checkForPressure(
							staticInfo.livingCell7.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell7.checkForSmoke(
							staticInfo.livingCell7.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell7.checkForGas(
							staticInfo.livingCell7.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell7.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell7_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell7.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell7_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell7_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell7_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell7.checkForTemperature(
					staticInfo.livingCell7.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell7_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell7_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell7.checkForHumidity(
					staticInfo.livingCell7.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell7_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell7.checkForHumidity(
					staticInfo.livingCell7.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell7_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell7.checkForHumidity(
					staticInfo.livingCell7.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell7_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell7_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell7.checkForOxygenLevel(
					staticInfo.livingCell7.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell7.checkForOxygenLevel(
					staticInfo.livingCell7.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell7.checkForOxygenLevel(
					staticInfo.livingCell7.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell7_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell7.checkForPressure(
					staticInfo.livingCell7.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell7_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell7.checkForPressure(
					staticInfo.livingCell7.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell7_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell7.checkForPressure(
					staticInfo.livingCell7.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell7_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell7_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell7.checkForSmoke(
					staticInfo.livingCell7.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell7_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell7_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell7.checkForGas(
					staticInfo.livingCell7.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell7_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell7_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell8(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell8 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell8.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 8;
			livingCell entity = staticInfo.livingCell8;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell8.checkForHumidity(
							staticInfo.livingCell8.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell8.checkForOxygenLevel(
							staticInfo.livingCell8.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell8.checkForPressure(
							staticInfo.livingCell8.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell8.checkForSmoke(
							staticInfo.livingCell8.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell8.checkForGas(
							staticInfo.livingCell8.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell8.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell8_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell8.checkForHumidity(
							staticInfo.livingCell8.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell8.checkForOxygenLevel(
							staticInfo.livingCell8.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell8.checkForPressure(
							staticInfo.livingCell8.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell8.checkForSmoke(
							staticInfo.livingCell8.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell8.checkForGas(
							staticInfo.livingCell8.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell8.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell8_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell8.checkForHumidity(
							staticInfo.livingCell8.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell8.checkForOxygenLevel(
							staticInfo.livingCell8.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell8.checkForPressure(
							staticInfo.livingCell8.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell8.checkForSmoke(
							staticInfo.livingCell8.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell8.checkForGas(
							staticInfo.livingCell8.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell8.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell8_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell8.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell8_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell8_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell8_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell8_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell8_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell8.checkForHumidity(
					staticInfo.livingCell8.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell8_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell8.checkForHumidity(
					staticInfo.livingCell8.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell8_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell8.checkForHumidity(
					staticInfo.livingCell8.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell8_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell8_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell8.checkForOxygenLevel(
					staticInfo.livingCell8.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell8.checkForOxygenLevel(
					staticInfo.livingCell8.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell8.checkForOxygenLevel(
					staticInfo.livingCell8.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell8.checkForPressure(
					staticInfo.livingCell8.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell8_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell8.checkForPressure(
					staticInfo.livingCell8.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell8_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell8.checkForPressure(
					staticInfo.livingCell8.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell8_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell8_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell8.checkForSmoke(
					staticInfo.livingCell8.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell8_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell8_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell8.checkForGas(
					staticInfo.livingCell8.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell8_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell8_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell8
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell8_temperature.setText(Double
						.toString(staticInfo.livingCell8.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell8.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell8_humidity.setText(Double
						.toString(staticInfo.livingCell8.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell8
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell8_oxygen.setText(Double
						.toString(staticInfo.livingCell8.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell8.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell8_pressure.setText(Double
						.toString(staticInfo.livingCell8.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell8.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell8_smoke.setText(Double
						.toString(staticInfo.livingCell8.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell8.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell8_gas.setText(Double
						.toString(staticInfo.livingCell8.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell8.checkForHumidity(
							staticInfo.livingCell8.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell8.checkForOxygenLevel(
							staticInfo.livingCell8.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell8.checkForPressure(
							staticInfo.livingCell8.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell8.checkForSmoke(
							staticInfo.livingCell8.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell8.checkForGas(
							staticInfo.livingCell8.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell8.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell8_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell8.checkForHumidity(
							staticInfo.livingCell8.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell8.checkForOxygenLevel(
							staticInfo.livingCell8.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell8.checkForPressure(
							staticInfo.livingCell8.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell8.checkForSmoke(
							staticInfo.livingCell8.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell8.checkForGas(
							staticInfo.livingCell8.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell8.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell8_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell8.checkForHumidity(
							staticInfo.livingCell8.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell8.checkForOxygenLevel(
							staticInfo.livingCell8.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell8.checkForPressure(
							staticInfo.livingCell8.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell8.checkForSmoke(
							staticInfo.livingCell8.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell8.checkForGas(
							staticInfo.livingCell8.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell8.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell8_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell8.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell8_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell8_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell8_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell8.checkForTemperature(
					staticInfo.livingCell8.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell8_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell8_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell8.checkForHumidity(
					staticInfo.livingCell8.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell8_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell8.checkForHumidity(
					staticInfo.livingCell8.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell8_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell8.checkForHumidity(
					staticInfo.livingCell8.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell8_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell8_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell8.checkForOxygenLevel(
					staticInfo.livingCell8.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell8.checkForOxygenLevel(
					staticInfo.livingCell8.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell8.checkForOxygenLevel(
					staticInfo.livingCell8.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell8_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell8.checkForPressure(
					staticInfo.livingCell8.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell8_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell8.checkForPressure(
					staticInfo.livingCell8.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell8_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell8.checkForPressure(
					staticInfo.livingCell8.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell8_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell8_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell8.checkForSmoke(
					staticInfo.livingCell8.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell8_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell8_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell8.checkForGas(
					staticInfo.livingCell8.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell8_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell8_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell9(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell9 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell9.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 9;
			livingCell entity = staticInfo.livingCell9;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell9.checkForHumidity(
							staticInfo.livingCell9.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell9.checkForOxygenLevel(
							staticInfo.livingCell9.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell9.checkForPressure(
							staticInfo.livingCell9.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell9.checkForSmoke(
							staticInfo.livingCell9.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell9.checkForGas(
							staticInfo.livingCell9.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell9.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell9_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell9.checkForHumidity(
							staticInfo.livingCell9.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell9.checkForOxygenLevel(
							staticInfo.livingCell9.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell9.checkForPressure(
							staticInfo.livingCell9.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell9.checkForSmoke(
							staticInfo.livingCell9.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell9.checkForGas(
							staticInfo.livingCell9.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell9.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell9_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell9.checkForHumidity(
							staticInfo.livingCell9.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell9.checkForOxygenLevel(
							staticInfo.livingCell9.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell9.checkForPressure(
							staticInfo.livingCell9.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell9.checkForSmoke(
							staticInfo.livingCell9.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell9.checkForGas(
							staticInfo.livingCell9.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell9.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell9_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell9.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell9_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell9_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell9_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell9_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell9_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell9.checkForHumidity(
					staticInfo.livingCell9.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell9_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell9.checkForHumidity(
					staticInfo.livingCell9.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell9_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell9.checkForHumidity(
					staticInfo.livingCell9.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell9_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell9_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell9.checkForOxygenLevel(
					staticInfo.livingCell9.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell9.checkForOxygenLevel(
					staticInfo.livingCell9.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell9.checkForOxygenLevel(
					staticInfo.livingCell9.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell9.checkForPressure(
					staticInfo.livingCell9.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell9_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell9.checkForPressure(
					staticInfo.livingCell9.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell9_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell9.checkForPressure(
					staticInfo.livingCell9.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell9_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell9_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell9.checkForSmoke(
					staticInfo.livingCell9.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell9_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell9_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell9.checkForGas(
					staticInfo.livingCell9.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell9_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell9_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell9
							.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell9_temperature.setText(Double
						.toString(staticInfo.livingCell9.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell9.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell9_humidity.setText(Double
						.toString(staticInfo.livingCell9.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell9
							.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell9_oxygen.setText(Double
						.toString(staticInfo.livingCell9.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell9.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell9_pressure.setText(Double
						.toString(staticInfo.livingCell9.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell9.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell9_smoke.setText(Double
						.toString(staticInfo.livingCell9.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell9.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell9_gas.setText(Double
						.toString(staticInfo.livingCell9.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature())
					.equals("EMERGENCY")
					|| staticInfo.livingCell9.checkForHumidity(
							staticInfo.livingCell9.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell9.checkForOxygenLevel(
							staticInfo.livingCell9.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell9.checkForPressure(
							staticInfo.livingCell9.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell9.checkForSmoke(
							staticInfo.livingCell9.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell9.checkForGas(
							staticInfo.livingCell9.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell9.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell9_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell9.checkForHumidity(
							staticInfo.livingCell9.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell9.checkForOxygenLevel(
							staticInfo.livingCell9.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell9.checkForPressure(
							staticInfo.livingCell9.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell9.checkForSmoke(
							staticInfo.livingCell9.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.livingCell9.checkForGas(
							staticInfo.livingCell9.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell9.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell9_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell9.checkForHumidity(
							staticInfo.livingCell9.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell9.checkForOxygenLevel(
							staticInfo.livingCell9.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell9.checkForPressure(
							staticInfo.livingCell9.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell9.checkForSmoke(
							staticInfo.livingCell9.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.livingCell9.checkForGas(
							staticInfo.livingCell9.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell9.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell9_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell9.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell9_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell9_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell9_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell9.checkForTemperature(
					staticInfo.livingCell9.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell9_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell9_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell9.checkForHumidity(
					staticInfo.livingCell9.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell9_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell9.checkForHumidity(
					staticInfo.livingCell9.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell9_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell9.checkForHumidity(
					staticInfo.livingCell9.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell9_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell9_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell9.checkForOxygenLevel(
					staticInfo.livingCell9.getOxygenLevel())
					.equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell9.checkForOxygenLevel(
					staticInfo.livingCell9.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell9.checkForOxygenLevel(
					staticInfo.livingCell9.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell9_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell9.checkForPressure(
					staticInfo.livingCell9.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell9_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell9.checkForPressure(
					staticInfo.livingCell9.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell9_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell9.checkForPressure(
					staticInfo.livingCell9.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell9_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell9_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell9.checkForSmoke(
					staticInfo.livingCell9.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell9_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell9_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell9.checkForGas(
					staticInfo.livingCell9.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell9_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell9_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell10(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell10 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell10.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 10;
			livingCell entity = staticInfo.livingCell10;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell10.checkForHumidity(
							staticInfo.livingCell10.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell10.checkForOxygenLevel(
							staticInfo.livingCell10.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell10.checkForPressure(
							staticInfo.livingCell10.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell10.checkForSmoke(
							staticInfo.livingCell10.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell10.checkForGas(
							staticInfo.livingCell10.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell10.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell10_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell10.checkForHumidity(
							staticInfo.livingCell10.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell10.checkForOxygenLevel(
							staticInfo.livingCell10.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell10.checkForPressure(
							staticInfo.livingCell10.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell10.checkForSmoke(
							staticInfo.livingCell10.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell10.checkForGas(
							staticInfo.livingCell10.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell10.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell10_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell10.checkForHumidity(
							staticInfo.livingCell10.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell10.checkForOxygenLevel(
							staticInfo.livingCell10.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell10.checkForPressure(
							staticInfo.livingCell10.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell10.checkForSmoke(
							staticInfo.livingCell10.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell10.checkForGas(
							staticInfo.livingCell10.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell10.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell10_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell10.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell10_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell10_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell10_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell10_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell10_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell10.checkForHumidity(
					staticInfo.livingCell10.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell10_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell10.checkForHumidity(
					staticInfo.livingCell10.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell10_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell10.checkForHumidity(
					staticInfo.livingCell10.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell10_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell10_humidity
						.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell10.checkForOxygenLevel(
					staticInfo.livingCell10.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell10.checkForOxygenLevel(
					staticInfo.livingCell10.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell10.checkForOxygenLevel(
					staticInfo.livingCell10.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell10.checkForPressure(
					staticInfo.livingCell10.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell10_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell10.checkForPressure(
					staticInfo.livingCell10.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell10_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell10.checkForPressure(
					staticInfo.livingCell10.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell10_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell10_pressure
						.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell10.checkForSmoke(
					staticInfo.livingCell10.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell10_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell10_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell10.checkForGas(
					staticInfo.livingCell10.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell10_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell10_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell10.setTemperature(temp
							.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell10_temperature.setText(Double
						.toString(staticInfo.livingCell10.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell10.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell10_humidity.setText(Double
						.toString(staticInfo.livingCell10.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell10.setOxygenLevel(temp
							.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell10_oxygen.setText(Double
						.toString(staticInfo.livingCell10.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell10.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell10_pressure.setText(Double
						.toString(staticInfo.livingCell10.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell10.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell10_smoke.setText(Double
						.toString(staticInfo.livingCell10.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell10.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell10_gas.setText(Double
						.toString(staticInfo.livingCell10.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell10.checkForHumidity(
							staticInfo.livingCell10.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell10.checkForOxygenLevel(
							staticInfo.livingCell10.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell10.checkForPressure(
							staticInfo.livingCell10.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell10.checkForSmoke(
							staticInfo.livingCell10.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell10.checkForGas(
							staticInfo.livingCell10.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell10.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell10_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell10.checkForHumidity(
							staticInfo.livingCell10.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell10.checkForOxygenLevel(
							staticInfo.livingCell10.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell10.checkForPressure(
							staticInfo.livingCell10.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell10.checkForSmoke(
							staticInfo.livingCell10.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell10.checkForGas(
							staticInfo.livingCell10.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell10.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell10_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell10.checkForHumidity(
							staticInfo.livingCell10.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell10.checkForOxygenLevel(
							staticInfo.livingCell10.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell10.checkForPressure(
							staticInfo.livingCell10.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell10.checkForSmoke(
							staticInfo.livingCell10.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell10.checkForGas(
							staticInfo.livingCell10.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell10.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell10_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell10.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell10_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell10_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell10_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell10.checkForTemperature(
					staticInfo.livingCell10.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell10_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell10_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell10.checkForHumidity(
					staticInfo.livingCell10.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell10_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell10.checkForHumidity(
					staticInfo.livingCell10.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell10_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell10.checkForHumidity(
					staticInfo.livingCell10.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell10_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell10_humidity
						.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell10.checkForOxygenLevel(
					staticInfo.livingCell10.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell10.checkForOxygenLevel(
					staticInfo.livingCell10.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell10.checkForOxygenLevel(
					staticInfo.livingCell10.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell10_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell10.checkForPressure(
					staticInfo.livingCell10.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell10_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell10.checkForPressure(
					staticInfo.livingCell10.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell10_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell10.checkForPressure(
					staticInfo.livingCell10.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell10_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell10_pressure
						.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell10.checkForSmoke(
					staticInfo.livingCell10.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell10_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell10_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell10.checkForGas(
					staticInfo.livingCell10.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell10_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell10_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell11(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell11 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell11.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 11;
			livingCell entity = staticInfo.livingCell11;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell11.checkForHumidity(
							staticInfo.livingCell11.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell11.checkForOxygenLevel(
							staticInfo.livingCell11.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell11.checkForPressure(
							staticInfo.livingCell11.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell11.checkForSmoke(
							staticInfo.livingCell11.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell11.checkForGas(
							staticInfo.livingCell11.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell11.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell11_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell11.checkForHumidity(
							staticInfo.livingCell11.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell11.checkForOxygenLevel(
							staticInfo.livingCell11.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell11.checkForPressure(
							staticInfo.livingCell11.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell11.checkForSmoke(
							staticInfo.livingCell11.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell11.checkForGas(
							staticInfo.livingCell11.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell11.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell11_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell11.checkForHumidity(
							staticInfo.livingCell11.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell11.checkForOxygenLevel(
							staticInfo.livingCell11.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell11.checkForPressure(
							staticInfo.livingCell11.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell11.checkForSmoke(
							staticInfo.livingCell11.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell11.checkForGas(
							staticInfo.livingCell11.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell11.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell11_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell11.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell11_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell11_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell11_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell11_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell11_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell11.checkForHumidity(
					staticInfo.livingCell11.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell11_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell11.checkForHumidity(
					staticInfo.livingCell11.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell11_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell11.checkForHumidity(
					staticInfo.livingCell11.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell11_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell11_humidity
						.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell11.checkForOxygenLevel(
					staticInfo.livingCell11.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell11.checkForOxygenLevel(
					staticInfo.livingCell11.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell11.checkForOxygenLevel(
					staticInfo.livingCell11.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell11.checkForPressure(
					staticInfo.livingCell11.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell11_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell11.checkForPressure(
					staticInfo.livingCell11.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell11_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell11.checkForPressure(
					staticInfo.livingCell11.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell11_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell11_pressure
						.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell11.checkForSmoke(
					staticInfo.livingCell11.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell11_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell11_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell11.checkForGas(
					staticInfo.livingCell11.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell11_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell11_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell11.setTemperature(temp
							.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell11_temperature.setText(Double
						.toString(staticInfo.livingCell11.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell11.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell11_humidity.setText(Double
						.toString(staticInfo.livingCell11.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell11.setOxygenLevel(temp
							.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell11_oxygen.setText(Double
						.toString(staticInfo.livingCell11.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell11.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell11_pressure.setText(Double
						.toString(staticInfo.livingCell11.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell11.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell11_smoke.setText(Double
						.toString(staticInfo.livingCell11.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell11.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell11_gas.setText(Double
						.toString(staticInfo.livingCell11.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell11.checkForHumidity(
							staticInfo.livingCell11.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell11.checkForOxygenLevel(
							staticInfo.livingCell11.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell11.checkForPressure(
							staticInfo.livingCell11.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell11.checkForSmoke(
							staticInfo.livingCell11.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell11.checkForGas(
							staticInfo.livingCell11.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell11.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell11_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell11.checkForHumidity(
							staticInfo.livingCell11.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell11.checkForOxygenLevel(
							staticInfo.livingCell11.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell11.checkForPressure(
							staticInfo.livingCell11.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell11.checkForSmoke(
							staticInfo.livingCell11.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell11.checkForGas(
							staticInfo.livingCell11.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell11.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell11_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell11.checkForHumidity(
							staticInfo.livingCell11.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell11.checkForOxygenLevel(
							staticInfo.livingCell11.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell11.checkForPressure(
							staticInfo.livingCell11.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell11.checkForSmoke(
							staticInfo.livingCell11.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell11.checkForGas(
							staticInfo.livingCell11.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell11.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell11_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell11.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell11_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell11_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell11_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell11.checkForTemperature(
					staticInfo.livingCell11.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell11_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell11_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell11.checkForHumidity(
					staticInfo.livingCell11.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell11_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell11.checkForHumidity(
					staticInfo.livingCell11.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell11_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell11.checkForHumidity(
					staticInfo.livingCell11.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell11_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell11_humidity
						.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell11.checkForOxygenLevel(
					staticInfo.livingCell11.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell11.checkForOxygenLevel(
					staticInfo.livingCell11.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell11.checkForOxygenLevel(
					staticInfo.livingCell11.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell11_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell11.checkForPressure(
					staticInfo.livingCell11.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell11_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell11.checkForPressure(
					staticInfo.livingCell11.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell11_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell11.checkForPressure(
					staticInfo.livingCell11.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell11_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell11_pressure
						.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell11.checkForSmoke(
					staticInfo.livingCell11.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell11_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell11_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell11.checkForGas(
					staticInfo.livingCell11.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell11_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell11_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell12(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell12 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell12.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 12;
			livingCell entity = staticInfo.livingCell12;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell12.checkForHumidity(
							staticInfo.livingCell12.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell12.checkForOxygenLevel(
							staticInfo.livingCell12.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell12.checkForPressure(
							staticInfo.livingCell12.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell12.checkForSmoke(
							staticInfo.livingCell12.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell12.checkForGas(
							staticInfo.livingCell12.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell12.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell12_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell12.checkForHumidity(
							staticInfo.livingCell12.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell12.checkForOxygenLevel(
							staticInfo.livingCell12.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell12.checkForPressure(
							staticInfo.livingCell12.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell12.checkForSmoke(
							staticInfo.livingCell12.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell12.checkForGas(
							staticInfo.livingCell12.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell12.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell12_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell12.checkForHumidity(
							staticInfo.livingCell12.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell12.checkForOxygenLevel(
							staticInfo.livingCell12.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell12.checkForPressure(
							staticInfo.livingCell12.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell12.checkForSmoke(
							staticInfo.livingCell12.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell12.checkForGas(
							staticInfo.livingCell12.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell12.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell12_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell12.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell12_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell12_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell12_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell12_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell12_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell12.checkForHumidity(
					staticInfo.livingCell12.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell12_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell12.checkForHumidity(
					staticInfo.livingCell12.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell12_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell12.checkForHumidity(
					staticInfo.livingCell12.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell12_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell12_humidity
						.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell12.checkForOxygenLevel(
					staticInfo.livingCell12.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell12.checkForOxygenLevel(
					staticInfo.livingCell12.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell12.checkForOxygenLevel(
					staticInfo.livingCell12.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell12.checkForPressure(
					staticInfo.livingCell12.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell12_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell12.checkForPressure(
					staticInfo.livingCell12.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell12_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell12.checkForPressure(
					staticInfo.livingCell12.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell12_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell12_pressure
						.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell12.checkForSmoke(
					staticInfo.livingCell12.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell12_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell12_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell12.checkForGas(
					staticInfo.livingCell12.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell12_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell12_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell12.setTemperature(temp
							.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell12_temperature.setText(Double
						.toString(staticInfo.livingCell12.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell12.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell12_humidity.setText(Double
						.toString(staticInfo.livingCell12.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell12.setOxygenLevel(temp
							.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell12_oxygen.setText(Double
						.toString(staticInfo.livingCell12.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell12.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell12_pressure.setText(Double
						.toString(staticInfo.livingCell12.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell12.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell12_smoke.setText(Double
						.toString(staticInfo.livingCell12.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell12.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell12_gas.setText(Double
						.toString(staticInfo.livingCell12.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell12.checkForHumidity(
							staticInfo.livingCell12.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell12.checkForOxygenLevel(
							staticInfo.livingCell12.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell12.checkForPressure(
							staticInfo.livingCell12.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell12.checkForSmoke(
							staticInfo.livingCell12.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell12.checkForGas(
							staticInfo.livingCell12.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell12.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell12_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell12.checkForHumidity(
							staticInfo.livingCell12.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell12.checkForOxygenLevel(
							staticInfo.livingCell12.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell12.checkForPressure(
							staticInfo.livingCell12.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell12.checkForSmoke(
							staticInfo.livingCell12.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell12.checkForGas(
							staticInfo.livingCell12.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell12.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell12_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell12.checkForHumidity(
							staticInfo.livingCell12.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell12.checkForOxygenLevel(
							staticInfo.livingCell12.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell12.checkForPressure(
							staticInfo.livingCell12.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell12.checkForSmoke(
							staticInfo.livingCell12.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell12.checkForGas(
							staticInfo.livingCell12.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell12.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell12_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell12.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell12_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell12_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell12_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell12.checkForTemperature(
					staticInfo.livingCell12.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell12_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell12_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell12.checkForHumidity(
					staticInfo.livingCell12.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell12_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell12.checkForHumidity(
					staticInfo.livingCell12.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell12_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell12.checkForHumidity(
					staticInfo.livingCell12.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell12_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell12_humidity
						.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell12.checkForOxygenLevel(
					staticInfo.livingCell12.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell12.checkForOxygenLevel(
					staticInfo.livingCell12.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell12.checkForOxygenLevel(
					staticInfo.livingCell12.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell12_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell12.checkForPressure(
					staticInfo.livingCell12.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell12_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell12.checkForPressure(
					staticInfo.livingCell12.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell12_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell12.checkForPressure(
					staticInfo.livingCell12.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell12_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell12_pressure
						.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell12.checkForSmoke(
					staticInfo.livingCell12.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell12_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell12_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell12.checkForGas(
					staticInfo.livingCell12.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell12_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell12_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell13(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell13 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell13.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 13;
			livingCell entity = staticInfo.livingCell13;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell13.checkForHumidity(
							staticInfo.livingCell13.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell13.checkForOxygenLevel(
							staticInfo.livingCell13.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell13.checkForPressure(
							staticInfo.livingCell13.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell13.checkForSmoke(
							staticInfo.livingCell13.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell13.checkForGas(
							staticInfo.livingCell13.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell13.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell13_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell13.checkForHumidity(
							staticInfo.livingCell13.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell13.checkForOxygenLevel(
							staticInfo.livingCell13.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell13.checkForPressure(
							staticInfo.livingCell13.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell13.checkForSmoke(
							staticInfo.livingCell13.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell13.checkForGas(
							staticInfo.livingCell13.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell13.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell13_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell13.checkForHumidity(
							staticInfo.livingCell13.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell13.checkForOxygenLevel(
							staticInfo.livingCell13.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell13.checkForPressure(
							staticInfo.livingCell13.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell13.checkForSmoke(
							staticInfo.livingCell13.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell13.checkForGas(
							staticInfo.livingCell13.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell13.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell13_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell13.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell13_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell13_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell13_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell13_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell13_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell13.checkForHumidity(
					staticInfo.livingCell13.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell13_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell13.checkForHumidity(
					staticInfo.livingCell13.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell13_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell13.checkForHumidity(
					staticInfo.livingCell13.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell13_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell13_humidity
						.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell13.checkForOxygenLevel(
					staticInfo.livingCell13.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell13.checkForOxygenLevel(
					staticInfo.livingCell13.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell13.checkForOxygenLevel(
					staticInfo.livingCell13.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell13.checkForPressure(
					staticInfo.livingCell13.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell13_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell13.checkForPressure(
					staticInfo.livingCell13.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell13_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell13.checkForPressure(
					staticInfo.livingCell13.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell13_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell13_pressure
						.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell13.checkForSmoke(
					staticInfo.livingCell13.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell13_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell13_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell13.checkForGas(
					staticInfo.livingCell13.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell13_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell13_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell13.setTemperature(temp
							.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell13_temperature.setText(Double
						.toString(staticInfo.livingCell13.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell13.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell13_humidity.setText(Double
						.toString(staticInfo.livingCell13.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell13.setOxygenLevel(temp
							.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell13_oxygen.setText(Double
						.toString(staticInfo.livingCell13.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell13.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell13_pressure.setText(Double
						.toString(staticInfo.livingCell13.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell13.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell13_smoke.setText(Double
						.toString(staticInfo.livingCell13.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell13.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell13_gas.setText(Double
						.toString(staticInfo.livingCell13.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell13.checkForHumidity(
							staticInfo.livingCell13.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell13.checkForOxygenLevel(
							staticInfo.livingCell13.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell13.checkForPressure(
							staticInfo.livingCell13.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell13.checkForSmoke(
							staticInfo.livingCell13.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell13.checkForGas(
							staticInfo.livingCell13.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell13.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell13_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell13.checkForHumidity(
							staticInfo.livingCell13.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell13.checkForOxygenLevel(
							staticInfo.livingCell13.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell13.checkForPressure(
							staticInfo.livingCell13.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell13.checkForSmoke(
							staticInfo.livingCell13.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell13.checkForGas(
							staticInfo.livingCell13.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell13.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell13_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell13.checkForHumidity(
							staticInfo.livingCell13.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell13.checkForOxygenLevel(
							staticInfo.livingCell13.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell13.checkForPressure(
							staticInfo.livingCell13.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell13.checkForSmoke(
							staticInfo.livingCell13.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell13.checkForGas(
							staticInfo.livingCell13.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell13.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell13_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell13.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell13_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell13_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell13_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell13.checkForTemperature(
					staticInfo.livingCell13.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell13_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell13_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell13.checkForHumidity(
					staticInfo.livingCell13.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell13_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell13.checkForHumidity(
					staticInfo.livingCell13.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell13_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell13.checkForHumidity(
					staticInfo.livingCell13.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell13_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell13_humidity
						.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell13.checkForOxygenLevel(
					staticInfo.livingCell13.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell13.checkForOxygenLevel(
					staticInfo.livingCell13.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell13.checkForOxygenLevel(
					staticInfo.livingCell13.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell13_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell13.checkForPressure(
					staticInfo.livingCell13.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell13_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell13.checkForPressure(
					staticInfo.livingCell13.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell13_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell13.checkForPressure(
					staticInfo.livingCell13.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell13_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell13_pressure
						.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell13.checkForSmoke(
					staticInfo.livingCell13.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell13_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell13_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell13.checkForGas(
					staticInfo.livingCell13.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell13_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell13_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell14(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell14 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell14.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 14;
			livingCell entity = staticInfo.livingCell14;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell14.checkForHumidity(
							staticInfo.livingCell14.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell14.checkForOxygenLevel(
							staticInfo.livingCell14.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell14.checkForPressure(
							staticInfo.livingCell14.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell14.checkForSmoke(
							staticInfo.livingCell14.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell14.checkForGas(
							staticInfo.livingCell14.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell14.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell14_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell14.checkForHumidity(
							staticInfo.livingCell14.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell14.checkForOxygenLevel(
							staticInfo.livingCell14.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell14.checkForPressure(
							staticInfo.livingCell14.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell14.checkForSmoke(
							staticInfo.livingCell14.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell14.checkForGas(
							staticInfo.livingCell14.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell14.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell14_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell14.checkForHumidity(
							staticInfo.livingCell14.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell14.checkForOxygenLevel(
							staticInfo.livingCell14.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell14.checkForPressure(
							staticInfo.livingCell14.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell14.checkForSmoke(
							staticInfo.livingCell14.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell14.checkForGas(
							staticInfo.livingCell14.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell14.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell14_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell14.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell14_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell14_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell14_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell14_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell14_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell14.checkForHumidity(
					staticInfo.livingCell14.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell14_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell14.checkForHumidity(
					staticInfo.livingCell14.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell14_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell14.checkForHumidity(
					staticInfo.livingCell14.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell14_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell14_humidity
						.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell14.checkForOxygenLevel(
					staticInfo.livingCell14.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell14.checkForOxygenLevel(
					staticInfo.livingCell14.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell14.checkForOxygenLevel(
					staticInfo.livingCell14.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell14.checkForPressure(
					staticInfo.livingCell14.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell14_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell14.checkForPressure(
					staticInfo.livingCell14.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell14_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell14.checkForPressure(
					staticInfo.livingCell14.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell14_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell14_pressure
						.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell14.checkForSmoke(
					staticInfo.livingCell14.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell14_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell14_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell14.checkForGas(
					staticInfo.livingCell14.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell14_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell14_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell14.setTemperature(temp
							.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell14_temperature.setText(Double
						.toString(staticInfo.livingCell14.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell14.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell14_humidity.setText(Double
						.toString(staticInfo.livingCell14.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell14.setOxygenLevel(temp
							.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell14_oxygen.setText(Double
						.toString(staticInfo.livingCell14.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell14.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell14_pressure.setText(Double
						.toString(staticInfo.livingCell14.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell14.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell14_smoke.setText(Double
						.toString(staticInfo.livingCell14.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell14.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell14_gas.setText(Double
						.toString(staticInfo.livingCell14.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell14.checkForHumidity(
							staticInfo.livingCell14.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell14.checkForOxygenLevel(
							staticInfo.livingCell14.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell14.checkForPressure(
							staticInfo.livingCell14.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell14.checkForSmoke(
							staticInfo.livingCell14.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell14.checkForGas(
							staticInfo.livingCell14.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell14.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell14_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell14.checkForHumidity(
							staticInfo.livingCell14.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell14.checkForOxygenLevel(
							staticInfo.livingCell14.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell14.checkForPressure(
							staticInfo.livingCell14.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell14.checkForSmoke(
							staticInfo.livingCell14.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell14.checkForGas(
							staticInfo.livingCell14.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell14.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell14_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell14.checkForHumidity(
							staticInfo.livingCell14.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell14.checkForOxygenLevel(
							staticInfo.livingCell14.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell14.checkForPressure(
							staticInfo.livingCell14.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell14.checkForSmoke(
							staticInfo.livingCell14.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell14.checkForGas(
							staticInfo.livingCell14.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell14.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell14_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell14.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell14_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell14_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell14_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell14.checkForTemperature(
					staticInfo.livingCell14.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell14_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell14_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell14.checkForHumidity(
					staticInfo.livingCell14.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell14_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell14.checkForHumidity(
					staticInfo.livingCell14.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell14_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell14.checkForHumidity(
					staticInfo.livingCell14.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell14_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell14_humidity
						.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell14.checkForOxygenLevel(
					staticInfo.livingCell14.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell14.checkForOxygenLevel(
					staticInfo.livingCell14.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell14.checkForOxygenLevel(
					staticInfo.livingCell14.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell14_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell14.checkForPressure(
					staticInfo.livingCell14.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell14_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell14.checkForPressure(
					staticInfo.livingCell14.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell14_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell14.checkForPressure(
					staticInfo.livingCell14.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell14_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell14_pressure
						.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell14.checkForSmoke(
					staticInfo.livingCell14.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell14_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell14_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell14.checkForGas(
					staticInfo.livingCell14.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell14_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell14_gas.setForeground(Color.black);
			}
		}

	}

	public void processLivingCell15(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.livingCell15 = (livingCell) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.livingCell15.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LivingCell";
			int locationNumberVar = 15;
			livingCell entity = staticInfo.livingCell15;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell15.checkForHumidity(
							staticInfo.livingCell15.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell15.checkForOxygenLevel(
							staticInfo.livingCell15.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell15.checkForPressure(
							staticInfo.livingCell15.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell15.checkForSmoke(
							staticInfo.livingCell15.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell15.checkForGas(
							staticInfo.livingCell15.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell15.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell15_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell15.checkForHumidity(
							staticInfo.livingCell15.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell15.checkForOxygenLevel(
							staticInfo.livingCell15.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell15.checkForPressure(
							staticInfo.livingCell15.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell15.checkForSmoke(
							staticInfo.livingCell15.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell15.checkForGas(
							staticInfo.livingCell15.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell15.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell15_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell15.checkForHumidity(
							staticInfo.livingCell15.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell15.checkForOxygenLevel(
							staticInfo.livingCell15.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell15.checkForPressure(
							staticInfo.livingCell15.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell15.checkForSmoke(
							staticInfo.livingCell15.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell15.checkForGas(
							staticInfo.livingCell15.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell15.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell15_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell15.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell15_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell15_temperature
						.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell15_temperature
						.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell15_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell15_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.livingCell15.checkForHumidity(
					staticInfo.livingCell15.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell15_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.livingCell15.checkForHumidity(
					staticInfo.livingCell15.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell15_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.livingCell15.checkForHumidity(
					staticInfo.livingCell15.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell15_humidity
						.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell15_humidity
						.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.livingCell15.checkForOxygenLevel(
					staticInfo.livingCell15.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.livingCell15.checkForOxygenLevel(
					staticInfo.livingCell15.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.livingCell15.checkForOxygenLevel(
					staticInfo.livingCell15.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.livingCell15.checkForPressure(
					staticInfo.livingCell15.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell15_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.livingCell15.checkForPressure(
					staticInfo.livingCell15.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell15_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.livingCell15.checkForPressure(
					staticInfo.livingCell15.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell15_pressure
						.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.livingCell15_pressure
						.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.livingCell15.checkForSmoke(
					staticInfo.livingCell15.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell15_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell15_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.livingCell15.checkForGas(
					staticInfo.livingCell15.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell15_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.livingCell15_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell15.setTemperature(temp
							.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell15_temperature.setText(Double
						.toString(staticInfo.livingCell15.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell15.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell15_humidity.setText(Double
						.toString(staticInfo.livingCell15.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell15.setOxygenLevel(temp
							.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell15_oxygen.setText(Double
						.toString(staticInfo.livingCell15.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell15.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell15_pressure.setText(Double
						.toString(staticInfo.livingCell15.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell15.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell15_smoke.setText(Double
						.toString(staticInfo.livingCell15.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					livingCell temp = (livingCell) ois.readObject();
					staticInfo.livingCell15.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.livingCell15_gas.setText(Double
						.toString(staticInfo.livingCell15.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.livingCell15.checkForHumidity(
							staticInfo.livingCell15.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell15.checkForOxygenLevel(
							staticInfo.livingCell15.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell15.checkForPressure(
							staticInfo.livingCell15.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.livingCell15.checkForSmoke(
							staticInfo.livingCell15.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.livingCell15.checkForGas(
							staticInfo.livingCell15.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.livingCell15.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell15_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals("ERROR")
					|| staticInfo.livingCell15.checkForHumidity(
							staticInfo.livingCell15.getHumidity()).equals(
							"ERROR")
					|| staticInfo.livingCell15.checkForOxygenLevel(
							staticInfo.livingCell15.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.livingCell15.checkForPressure(
							staticInfo.livingCell15.getPressure()).equals(
							"ERROR")
					|| staticInfo.livingCell15.checkForSmoke(
							staticInfo.livingCell15.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.livingCell15.checkForGas(
							staticInfo.livingCell15.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.livingCell15.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell15_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals("WARNING")
					|| staticInfo.livingCell15.checkForHumidity(
							staticInfo.livingCell15.getHumidity()).equals(
							"WARNING")
					|| staticInfo.livingCell15.checkForOxygenLevel(
							staticInfo.livingCell15.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.livingCell15.checkForPressure(
							staticInfo.livingCell15.getPressure()).equals(
							"WARNING")
					|| staticInfo.livingCell15.checkForSmoke(
							staticInfo.livingCell15.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.livingCell15.checkForGas(
							staticInfo.livingCell15.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.livingCell15.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell15_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.livingCell15.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.livingCell15_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell15_temperature
						.setForeground(Color.red);
			} else if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.livingCell15_temperature
						.setForeground(Color.pink);
			} else if (staticInfo.livingCell15.checkForTemperature(
					staticInfo.livingCell15.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.livingCell15_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell15_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.livingCell15.checkForHumidity(
					staticInfo.livingCell15.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell15_humidity.setForeground(Color.red);
			} else if (staticInfo.livingCell15.checkForHumidity(
					staticInfo.livingCell15.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.livingCell15_humidity.setForeground(Color.pink);
			} else if (staticInfo.livingCell15.checkForHumidity(
					staticInfo.livingCell15.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.livingCell15_humidity
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell15_humidity
						.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.livingCell15.checkForOxygenLevel(
					staticInfo.livingCell15.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.red);
			} else if (staticInfo.livingCell15.checkForOxygenLevel(
					staticInfo.livingCell15.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.pink);
			} else if (staticInfo.livingCell15.checkForOxygenLevel(
					staticInfo.livingCell15.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell15_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.livingCell15.checkForPressure(
					staticInfo.livingCell15.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.livingCell15_pressure.setForeground(Color.red);
			} else if (staticInfo.livingCell15.checkForPressure(
					staticInfo.livingCell15.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.livingCell15_pressure.setForeground(Color.pink);
			} else if (staticInfo.livingCell15.checkForPressure(
					staticInfo.livingCell15.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.livingCell15_pressure
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.livingCell15_pressure
						.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.livingCell15.checkForSmoke(
					staticInfo.livingCell15.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell15_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell15_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.livingCell15.checkForGas(
					staticInfo.livingCell15.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.livingCell15_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.livingCell15_gas.setForeground(Color.black);
			}
		}

	}

	public void processCommandSpace1(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.commandSpace1 = (commandSpace) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.commandSpace1.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "CommandSpace";
			int locationNumberVar = 1;
			commandSpace entity = staticInfo.commandSpace1;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.commandSpace1.checkForHumidity(
							staticInfo.commandSpace1.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace1.checkForOxygenLevel(
							staticInfo.commandSpace1.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace1.checkForPressure(
							staticInfo.commandSpace1.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace1.checkForSmoke(
							staticInfo.commandSpace1.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.commandSpace1.checkForGas(
							staticInfo.commandSpace1.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.commandSpace1.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command1_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature()).equals("ERROR")
					|| staticInfo.commandSpace1.checkForHumidity(
							staticInfo.commandSpace1.getHumidity()).equals(
							"ERROR")
					|| staticInfo.commandSpace1.checkForOxygenLevel(
							staticInfo.commandSpace1.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.commandSpace1.checkForPressure(
							staticInfo.commandSpace1.getPressure()).equals(
							"ERROR")
					|| staticInfo.commandSpace1.checkForSmoke(
							staticInfo.commandSpace1.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.commandSpace1.checkForGas(
							staticInfo.commandSpace1.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.commandSpace1.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command1_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature())
					.equals("WARNING")
					|| staticInfo.commandSpace1.checkForHumidity(
							staticInfo.commandSpace1.getHumidity()).equals(
							"WARNING")
					|| staticInfo.commandSpace1.checkForOxygenLevel(
							staticInfo.commandSpace1.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.commandSpace1.checkForPressure(
							staticInfo.commandSpace1.getPressure()).equals(
							"WARNING")
					|| staticInfo.commandSpace1.checkForSmoke(
							staticInfo.commandSpace1.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.commandSpace1.checkForGas(
							staticInfo.commandSpace1.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.commandSpace1.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.commandSpace1.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command1_temperature.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.command1_temperature.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature())
					.equals("WARNING")) {
				LoginGUI.mframe.command1_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.command1_temperature.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.commandSpace1.checkForHumidity(
					staticInfo.commandSpace1.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.command1_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.commandSpace1.checkForHumidity(
					staticInfo.commandSpace1.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.command1_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.commandSpace1.checkForHumidity(
					staticInfo.commandSpace1.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.command1_humidity.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.command1_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.commandSpace1.checkForOxygenLevel(
					staticInfo.commandSpace1.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command1_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.commandSpace1.checkForOxygenLevel(
					staticInfo.commandSpace1.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.command1_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.commandSpace1.checkForOxygenLevel(
					staticInfo.commandSpace1.getOxygenLevel())
					.equals("WARNING")) {
				LoginGUI.mframe.command1_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.command1_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.commandSpace1.checkForPressure(
					staticInfo.commandSpace1.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.command1_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.commandSpace1.checkForPressure(
					staticInfo.commandSpace1.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.command1_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.commandSpace1.checkForPressure(
					staticInfo.commandSpace1.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.command1_pressure.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.command1_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.commandSpace1.checkForSmoke(
					staticInfo.commandSpace1.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command1_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.command1_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.commandSpace1.checkForGas(
					staticInfo.commandSpace1.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command1_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.command1_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace1.setTemperature(temp
							.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command1_temperature.setText(Double
						.toString(staticInfo.commandSpace1.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace1.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command1_humidity.setText(Double
						.toString(staticInfo.commandSpace1.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace1.setOxygenLevel(temp
							.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command1_oxygen.setText(Double
						.toString(staticInfo.commandSpace1.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace1.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command1_pressure.setText(Double
						.toString(staticInfo.commandSpace1.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace1.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command1_smoke
						.setText(Double.toString(staticInfo.commandSpace1
								.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace1.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command1_gas.setText(Double
						.toString(staticInfo.commandSpace1.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.commandSpace1.checkForHumidity(
							staticInfo.commandSpace1.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace1.checkForOxygenLevel(
							staticInfo.commandSpace1.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace1.checkForPressure(
							staticInfo.commandSpace1.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace1.checkForSmoke(
							staticInfo.commandSpace1.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.commandSpace1.checkForGas(
							staticInfo.commandSpace1.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.commandSpace1.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command1_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature()).equals("ERROR")
					|| staticInfo.commandSpace1.checkForHumidity(
							staticInfo.commandSpace1.getHumidity()).equals(
							"ERROR")
					|| staticInfo.commandSpace1.checkForOxygenLevel(
							staticInfo.commandSpace1.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.commandSpace1.checkForPressure(
							staticInfo.commandSpace1.getPressure()).equals(
							"ERROR")
					|| staticInfo.commandSpace1.checkForSmoke(
							staticInfo.commandSpace1.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.commandSpace1.checkForGas(
							staticInfo.commandSpace1.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.commandSpace1.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command1_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature())
					.equals("WARNING")
					|| staticInfo.commandSpace1.checkForHumidity(
							staticInfo.commandSpace1.getHumidity()).equals(
							"WARNING")
					|| staticInfo.commandSpace1.checkForOxygenLevel(
							staticInfo.commandSpace1.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.commandSpace1.checkForPressure(
							staticInfo.commandSpace1.getPressure()).equals(
							"WARNING")
					|| staticInfo.commandSpace1.checkForSmoke(
							staticInfo.commandSpace1.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.commandSpace1.checkForGas(
							staticInfo.commandSpace1.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.commandSpace1.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.commandSpace1.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command1_temperature.setForeground(Color.red);
			} else if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.command1_temperature.setForeground(Color.pink);
			} else if (staticInfo.commandSpace1.checkForTemperature(
					staticInfo.commandSpace1.getTemperature())
					.equals("WARNING")) {
				LoginGUI.mframe.command1_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.command1_temperature.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.commandSpace1.checkForHumidity(
					staticInfo.commandSpace1.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.command1_humidity.setForeground(Color.red);
			} else if (staticInfo.commandSpace1.checkForHumidity(
					staticInfo.commandSpace1.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.command1_humidity.setForeground(Color.pink);
			} else if (staticInfo.commandSpace1.checkForHumidity(
					staticInfo.commandSpace1.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.command1_humidity.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.command1_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.commandSpace1.checkForOxygenLevel(
					staticInfo.commandSpace1.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command1_oxygen.setForeground(Color.red);
			} else if (staticInfo.commandSpace1.checkForOxygenLevel(
					staticInfo.commandSpace1.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.command1_oxygen.setForeground(Color.pink);
			} else if (staticInfo.commandSpace1.checkForOxygenLevel(
					staticInfo.commandSpace1.getOxygenLevel())
					.equals("WARNING")) {
				LoginGUI.mframe.command1_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.command1_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.commandSpace1.checkForPressure(
					staticInfo.commandSpace1.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.command1_pressure.setForeground(Color.red);
			} else if (staticInfo.commandSpace1.checkForPressure(
					staticInfo.commandSpace1.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.command1_pressure.setForeground(Color.pink);
			} else if (staticInfo.commandSpace1.checkForPressure(
					staticInfo.commandSpace1.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.command1_pressure.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.command1_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.commandSpace1.checkForSmoke(
					staticInfo.commandSpace1.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command1_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.command1_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.commandSpace1.checkForGas(
					staticInfo.commandSpace1.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command1_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.command1_gas.setForeground(Color.black);
			}
		}

	}

	public void processCommandSpace2(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.commandSpace2 = (commandSpace) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.commandSpace2.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "CommandSpace";
			int locationNumberVar = 2;
			commandSpace entity = staticInfo.commandSpace2;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.commandSpace2.checkForHumidity(
							staticInfo.commandSpace2.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace2.checkForOxygenLevel(
							staticInfo.commandSpace2.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace2.checkForPressure(
							staticInfo.commandSpace2.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace2.checkForSmoke(
							staticInfo.commandSpace2.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.commandSpace2.checkForGas(
							staticInfo.commandSpace2.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.commandSpace2.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command2_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature()).equals("ERROR")
					|| staticInfo.commandSpace2.checkForHumidity(
							staticInfo.commandSpace2.getHumidity()).equals(
							"ERROR")
					|| staticInfo.commandSpace2.checkForOxygenLevel(
							staticInfo.commandSpace2.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.commandSpace2.checkForPressure(
							staticInfo.commandSpace2.getPressure()).equals(
							"ERROR")
					|| staticInfo.commandSpace2.checkForSmoke(
							staticInfo.commandSpace2.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.commandSpace2.checkForGas(
							staticInfo.commandSpace2.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.commandSpace2.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command2_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature())
					.equals("WARNING")
					|| staticInfo.commandSpace2.checkForHumidity(
							staticInfo.commandSpace2.getHumidity()).equals(
							"WARNING")
					|| staticInfo.commandSpace2.checkForOxygenLevel(
							staticInfo.commandSpace2.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.commandSpace2.checkForPressure(
							staticInfo.commandSpace2.getPressure()).equals(
							"WARNING")
					|| staticInfo.commandSpace2.checkForSmoke(
							staticInfo.commandSpace2.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.commandSpace2.checkForGas(
							staticInfo.commandSpace2.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.commandSpace2.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.commandSpace2.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command2_temperature.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.command2_temperature.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature())
					.equals("WARNING")) {
				LoginGUI.mframe.command2_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.command2_temperature.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.commandSpace2.checkForHumidity(
					staticInfo.commandSpace2.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.command2_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.commandSpace2.checkForHumidity(
					staticInfo.commandSpace2.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.command2_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.commandSpace2.checkForHumidity(
					staticInfo.commandSpace2.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.command2_humidity.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.command2_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.commandSpace2.checkForOxygenLevel(
					staticInfo.commandSpace2.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command2_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.commandSpace2.checkForOxygenLevel(
					staticInfo.commandSpace2.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.command2_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.commandSpace2.checkForOxygenLevel(
					staticInfo.commandSpace2.getOxygenLevel())
					.equals("WARNING")) {
				LoginGUI.mframe.command2_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.command2_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.commandSpace2.checkForPressure(
					staticInfo.commandSpace2.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.command2_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.commandSpace2.checkForPressure(
					staticInfo.commandSpace2.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.command2_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.commandSpace2.checkForPressure(
					staticInfo.commandSpace2.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.command2_pressure.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.command2_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.commandSpace2.checkForSmoke(
					staticInfo.commandSpace2.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command2_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.command2_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.commandSpace2.checkForGas(
					staticInfo.commandSpace2.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command2_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.command2_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace2.setTemperature(temp
							.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command2_temperature.setText(Double
						.toString(staticInfo.commandSpace2.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace2.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command2_humidity.setText(Double
						.toString(staticInfo.commandSpace2.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace2.setOxygenLevel(temp
							.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command2_oxygen.setText(Double
						.toString(staticInfo.commandSpace2.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace2.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command2_pressure.setText(Double
						.toString(staticInfo.commandSpace2.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace2.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command2_smoke
						.setText(Double.toString(staticInfo.commandSpace2
								.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					commandSpace temp = (commandSpace) ois.readObject();
					staticInfo.commandSpace2.setGasDetection(temp
							.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.command2_gas.setText(Double
						.toString(staticInfo.commandSpace2.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature()).equals(
					"EMERGENCY")
					|| staticInfo.commandSpace2.checkForHumidity(
							staticInfo.commandSpace2.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace2.checkForOxygenLevel(
							staticInfo.commandSpace2.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace2.checkForPressure(
							staticInfo.commandSpace2.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.commandSpace2.checkForSmoke(
							staticInfo.commandSpace2.getSmokeDetection())
							.equals("EMERGENCY")
					|| staticInfo.commandSpace2.checkForGas(
							staticInfo.commandSpace2.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.commandSpace2.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command2_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature()).equals("ERROR")
					|| staticInfo.commandSpace2.checkForHumidity(
							staticInfo.commandSpace2.getHumidity()).equals(
							"ERROR")
					|| staticInfo.commandSpace2.checkForOxygenLevel(
							staticInfo.commandSpace2.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.commandSpace2.checkForPressure(
							staticInfo.commandSpace2.getPressure()).equals(
							"ERROR")
					|| staticInfo.commandSpace2.checkForSmoke(
							staticInfo.commandSpace2.getSmokeDetection())
							.equals("ERROR")
					|| staticInfo.commandSpace2.checkForGas(
							staticInfo.commandSpace2.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.commandSpace2.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command2_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature())
					.equals("WARNING")
					|| staticInfo.commandSpace2.checkForHumidity(
							staticInfo.commandSpace2.getHumidity()).equals(
							"WARNING")
					|| staticInfo.commandSpace2.checkForOxygenLevel(
							staticInfo.commandSpace2.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.commandSpace2.checkForPressure(
							staticInfo.commandSpace2.getPressure()).equals(
							"WARNING")
					|| staticInfo.commandSpace2.checkForSmoke(
							staticInfo.commandSpace2.getSmokeDetection())
							.equals("WARNING")
					|| staticInfo.commandSpace2.checkForGas(
							staticInfo.commandSpace2.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.commandSpace2.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.commandSpace2.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.command2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command2_temperature.setForeground(Color.red);
			} else if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.command2_temperature.setForeground(Color.pink);
			} else if (staticInfo.commandSpace2.checkForTemperature(
					staticInfo.commandSpace2.getTemperature())
					.equals("WARNING")) {
				LoginGUI.mframe.command2_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.command2_temperature.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.commandSpace2.checkForHumidity(
					staticInfo.commandSpace2.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.command2_humidity.setForeground(Color.red);
			} else if (staticInfo.commandSpace2.checkForHumidity(
					staticInfo.commandSpace2.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.command2_humidity.setForeground(Color.pink);
			} else if (staticInfo.commandSpace2.checkForHumidity(
					staticInfo.commandSpace2.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.command2_humidity.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.command2_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.commandSpace2.checkForOxygenLevel(
					staticInfo.commandSpace2.getOxygenLevel()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command2_oxygen.setForeground(Color.red);
			} else if (staticInfo.commandSpace2.checkForOxygenLevel(
					staticInfo.commandSpace2.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.command2_oxygen.setForeground(Color.pink);
			} else if (staticInfo.commandSpace2.checkForOxygenLevel(
					staticInfo.commandSpace2.getOxygenLevel())
					.equals("WARNING")) {
				LoginGUI.mframe.command2_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.command2_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.commandSpace2.checkForPressure(
					staticInfo.commandSpace2.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.command2_pressure.setForeground(Color.red);
			} else if (staticInfo.commandSpace2.checkForPressure(
					staticInfo.commandSpace2.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.command2_pressure.setForeground(Color.pink);
			} else if (staticInfo.commandSpace2.checkForPressure(
					staticInfo.commandSpace2.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.command2_pressure.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.command2_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.commandSpace2.checkForSmoke(
					staticInfo.commandSpace2.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command2_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.command2_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.commandSpace2.checkForGas(
					staticInfo.commandSpace2.getGasDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.command2_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.command2_gas.setForeground(Color.black);
			}
		}

	}

	private void processLabSpace1(String source) {

		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.labSpace1 = (labSpace) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.labSpace1.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LabSpace";
			int locationNumberVar = 1;
			labSpace entity = staticInfo.labSpace1;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("EMERGENCY")
					|| staticInfo.labSpace1.checkForHumidity(
							staticInfo.labSpace1.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace1.checkForOxygenLevel(
							staticInfo.labSpace1.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace1.checkForPressure(
							staticInfo.labSpace1.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace1.checkForSmoke(
							staticInfo.labSpace1.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace1.checkForGas(
							staticInfo.labSpace1.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.labSpace1.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace1_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("ERROR")
					|| staticInfo.labSpace1.checkForHumidity(
							staticInfo.labSpace1.getHumidity()).equals("ERROR")
					|| staticInfo.labSpace1.checkForOxygenLevel(
							staticInfo.labSpace1.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.labSpace1.checkForPressure(
							staticInfo.labSpace1.getPressure()).equals("ERROR")
					|| staticInfo.labSpace1.checkForSmoke(
							staticInfo.labSpace1.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.labSpace1.checkForGas(
							staticInfo.labSpace1.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.labSpace1.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace1_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("WARNING")
					|| staticInfo.labSpace1.checkForHumidity(
							staticInfo.labSpace1.getHumidity()).equals(
							"WARNING")
					|| staticInfo.labSpace1.checkForOxygenLevel(
							staticInfo.labSpace1.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.labSpace1.checkForPressure(
							staticInfo.labSpace1.getPressure()).equals(
							"WARNING")
					|| staticInfo.labSpace1.checkForSmoke(
							staticInfo.labSpace1.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.labSpace1.checkForGas(
							staticInfo.labSpace1.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.labSpace1.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.labSpace1.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_temperature.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.labSpace1_temperature.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.labSpace1_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace1_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.labSpace1.checkForHumidity(
					staticInfo.labSpace1.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.labSpace1.checkForHumidity(
					staticInfo.labSpace1.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.labSpace1_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.labSpace1.checkForHumidity(
					staticInfo.labSpace1.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.labSpace1_humidity.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace1_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.labSpace1.checkForOxygenLevel(
					staticInfo.labSpace1.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.labSpace1.checkForOxygenLevel(
					staticInfo.labSpace1.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.labSpace1.checkForOxygenLevel(
					staticInfo.labSpace1.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.labSpace1.checkForPressure(
					staticInfo.labSpace1.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.labSpace1.checkForPressure(
					staticInfo.labSpace1.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.labSpace1_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.labSpace1.checkForPressure(
					staticInfo.labSpace1.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.labSpace1_pressure.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace1_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.labSpace1.checkForSmoke(
					staticInfo.labSpace1.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.labSpace1_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.labSpace1_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.labSpace1.checkForGas(
					staticInfo.labSpace1.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.labSpace1_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace1.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace1_temperature.setText(Double
						.toString(staticInfo.labSpace1.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace1.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace1_humidity.setText(Double
						.toString(staticInfo.labSpace1.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace1.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace1_oxygen.setText(Double
						.toString(staticInfo.labSpace1.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace1.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace1_pressure.setText(Double
						.toString(staticInfo.labSpace1.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace1.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace1_smoke.setText(Double
						.toString(staticInfo.labSpace1.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace1
							.setGasDetection(temp.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace1_gas.setText(Double
						.toString(staticInfo.labSpace1.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("EMERGENCY")
					|| staticInfo.labSpace1.checkForHumidity(
							staticInfo.labSpace1.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace1.checkForOxygenLevel(
							staticInfo.labSpace1.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace1.checkForPressure(
							staticInfo.labSpace1.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace1.checkForSmoke(
							staticInfo.labSpace1.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace1.checkForGas(
							staticInfo.labSpace1.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.labSpace1.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace1_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("ERROR")
					|| staticInfo.labSpace1.checkForHumidity(
							staticInfo.labSpace1.getHumidity()).equals("ERROR")
					|| staticInfo.labSpace1.checkForOxygenLevel(
							staticInfo.labSpace1.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.labSpace1.checkForPressure(
							staticInfo.labSpace1.getPressure()).equals("ERROR")
					|| staticInfo.labSpace1.checkForSmoke(
							staticInfo.labSpace1.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.labSpace1.checkForGas(
							staticInfo.labSpace1.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.labSpace1.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace1_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("WARNING")
					|| staticInfo.labSpace1.checkForHumidity(
							staticInfo.labSpace1.getHumidity()).equals(
							"WARNING")
					|| staticInfo.labSpace1.checkForOxygenLevel(
							staticInfo.labSpace1.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.labSpace1.checkForPressure(
							staticInfo.labSpace1.getPressure()).equals(
							"WARNING")
					|| staticInfo.labSpace1.checkForSmoke(
							staticInfo.labSpace1.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.labSpace1.checkForGas(
							staticInfo.labSpace1.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.labSpace1.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.labSpace1.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace1_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_temperature.setForeground(Color.red);
			} else if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.labSpace1_temperature.setForeground(Color.pink);
			} else if (staticInfo.labSpace1.checkForTemperature(
					staticInfo.labSpace1.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.labSpace1_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace1_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.labSpace1.checkForHumidity(
					staticInfo.labSpace1.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_humidity.setForeground(Color.red);
			} else if (staticInfo.labSpace1.checkForHumidity(
					staticInfo.labSpace1.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.labSpace1_humidity.setForeground(Color.pink);
			} else if (staticInfo.labSpace1.checkForHumidity(
					staticInfo.labSpace1.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.labSpace1_humidity.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace1_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.labSpace1.checkForOxygenLevel(
					staticInfo.labSpace1.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.red);
			} else if (staticInfo.labSpace1.checkForOxygenLevel(
					staticInfo.labSpace1.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.pink);
			} else if (staticInfo.labSpace1.checkForOxygenLevel(
					staticInfo.labSpace1.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace1_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.labSpace1.checkForPressure(
					staticInfo.labSpace1.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_pressure.setForeground(Color.red);
			} else if (staticInfo.labSpace1.checkForPressure(
					staticInfo.labSpace1.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.labSpace1_pressure.setForeground(Color.pink);
			} else if (staticInfo.labSpace1.checkForPressure(
					staticInfo.labSpace1.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.labSpace1_pressure.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace1_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.labSpace1.checkForSmoke(
					staticInfo.labSpace1.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.labSpace1_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.labSpace1_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.labSpace1.checkForGas(
					staticInfo.labSpace1.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace1_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.labSpace1_gas.setForeground(Color.black);
			}
		}

	}

	public void processLabSpace2(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.labSpace2 = (labSpace) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.labSpace2.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LabSpace";
			int locationNumberVar = 2;
			labSpace entity = staticInfo.labSpace2;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("EMERGENCY")
					|| staticInfo.labSpace2.checkForHumidity(
							staticInfo.labSpace2.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace2.checkForOxygenLevel(
							staticInfo.labSpace2.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace2.checkForPressure(
							staticInfo.labSpace2.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace2.checkForSmoke(
							staticInfo.labSpace2.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace2.checkForGas(
							staticInfo.labSpace2.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.labSpace2.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace2_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("ERROR")
					|| staticInfo.labSpace2.checkForHumidity(
							staticInfo.labSpace2.getHumidity()).equals("ERROR")
					|| staticInfo.labSpace2.checkForOxygenLevel(
							staticInfo.labSpace2.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.labSpace2.checkForPressure(
							staticInfo.labSpace2.getPressure()).equals("ERROR")
					|| staticInfo.labSpace2.checkForSmoke(
							staticInfo.labSpace2.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.labSpace2.checkForGas(
							staticInfo.labSpace2.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.labSpace2.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace2_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("WARNING")
					|| staticInfo.labSpace2.checkForHumidity(
							staticInfo.labSpace2.getHumidity()).equals(
							"WARNING")
					|| staticInfo.labSpace2.checkForOxygenLevel(
							staticInfo.labSpace2.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.labSpace2.checkForPressure(
							staticInfo.labSpace2.getPressure()).equals(
							"WARNING")
					|| staticInfo.labSpace2.checkForSmoke(
							staticInfo.labSpace2.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.labSpace2.checkForGas(
							staticInfo.labSpace2.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.labSpace2.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.labSpace2.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_temperature.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.labSpace2_temperature.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.labSpace2_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace2_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.labSpace2.checkForHumidity(
					staticInfo.labSpace2.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.labSpace2.checkForHumidity(
					staticInfo.labSpace2.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.labSpace2_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.labSpace2.checkForHumidity(
					staticInfo.labSpace2.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.labSpace2_humidity.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace2_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.labSpace2.checkForOxygenLevel(
					staticInfo.labSpace2.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.labSpace2.checkForOxygenLevel(
					staticInfo.labSpace2.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.labSpace2.checkForOxygenLevel(
					staticInfo.labSpace2.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.labSpace2.checkForPressure(
					staticInfo.labSpace2.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.labSpace2.checkForPressure(
					staticInfo.labSpace2.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.labSpace2_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.labSpace2.checkForPressure(
					staticInfo.labSpace2.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.labSpace2_pressure.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace2_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.labSpace2.checkForSmoke(
					staticInfo.labSpace2.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.labSpace2_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.labSpace2_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.labSpace2.checkForGas(
					staticInfo.labSpace2.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.labSpace2_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace2.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace2_temperature.setText(Double
						.toString(staticInfo.labSpace2.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace2.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace2_humidity.setText(Double
						.toString(staticInfo.labSpace2.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace2.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace2_oxygen.setText(Double
						.toString(staticInfo.labSpace2.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace2.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace2_pressure.setText(Double
						.toString(staticInfo.labSpace2.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace2.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace2_smoke.setText(Double
						.toString(staticInfo.labSpace2.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace2
							.setGasDetection(temp.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace2_gas.setText(Double
						.toString(staticInfo.labSpace2.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("EMERGENCY")
					|| staticInfo.labSpace2.checkForHumidity(
							staticInfo.labSpace2.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace2.checkForOxygenLevel(
							staticInfo.labSpace2.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace2.checkForPressure(
							staticInfo.labSpace2.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace2.checkForSmoke(
							staticInfo.labSpace2.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace2.checkForGas(
							staticInfo.labSpace2.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.labSpace2.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace2_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("ERROR")
					|| staticInfo.labSpace2.checkForHumidity(
							staticInfo.labSpace2.getHumidity()).equals("ERROR")
					|| staticInfo.labSpace2.checkForOxygenLevel(
							staticInfo.labSpace2.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.labSpace2.checkForPressure(
							staticInfo.labSpace2.getPressure()).equals("ERROR")
					|| staticInfo.labSpace2.checkForSmoke(
							staticInfo.labSpace2.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.labSpace2.checkForGas(
							staticInfo.labSpace2.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.labSpace2.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace2_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("WARNING")
					|| staticInfo.labSpace2.checkForHumidity(
							staticInfo.labSpace2.getHumidity()).equals(
							"WARNING")
					|| staticInfo.labSpace2.checkForOxygenLevel(
							staticInfo.labSpace2.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.labSpace2.checkForPressure(
							staticInfo.labSpace2.getPressure()).equals(
							"WARNING")
					|| staticInfo.labSpace2.checkForSmoke(
							staticInfo.labSpace2.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.labSpace2.checkForGas(
							staticInfo.labSpace2.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.labSpace2.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.labSpace2.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace2_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_temperature.setForeground(Color.red);
			} else if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.labSpace2_temperature.setForeground(Color.pink);
			} else if (staticInfo.labSpace2.checkForTemperature(
					staticInfo.labSpace2.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.labSpace2_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace2_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.labSpace2.checkForHumidity(
					staticInfo.labSpace2.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_humidity.setForeground(Color.red);
			} else if (staticInfo.labSpace2.checkForHumidity(
					staticInfo.labSpace2.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.labSpace2_humidity.setForeground(Color.pink);
			} else if (staticInfo.labSpace2.checkForHumidity(
					staticInfo.labSpace2.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.labSpace2_humidity.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace2_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.labSpace2.checkForOxygenLevel(
					staticInfo.labSpace2.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.red);
			} else if (staticInfo.labSpace2.checkForOxygenLevel(
					staticInfo.labSpace2.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.pink);
			} else if (staticInfo.labSpace2.checkForOxygenLevel(
					staticInfo.labSpace2.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace2_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.labSpace2.checkForPressure(
					staticInfo.labSpace2.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_pressure.setForeground(Color.red);
			} else if (staticInfo.labSpace2.checkForPressure(
					staticInfo.labSpace2.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.labSpace2_pressure.setForeground(Color.pink);
			} else if (staticInfo.labSpace2.checkForPressure(
					staticInfo.labSpace2.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.labSpace2_pressure.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace2_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.labSpace2.checkForSmoke(
					staticInfo.labSpace2.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.labSpace2_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.labSpace2_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.labSpace2.checkForGas(
					staticInfo.labSpace2.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace2_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.labSpace2_gas.setForeground(Color.black);
			}
		}

	}

	public void processLabSpace3(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.labSpace3 = (labSpace) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.labSpace3.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LabSpace";
			int locationNumberVar = 3;
			labSpace entity = staticInfo.labSpace3;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("EMERGENCY")
					|| staticInfo.labSpace3.checkForHumidity(
							staticInfo.labSpace3.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace3.checkForOxygenLevel(
							staticInfo.labSpace3.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace3.checkForPressure(
							staticInfo.labSpace3.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace3.checkForSmoke(
							staticInfo.labSpace3.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace3.checkForGas(
							staticInfo.labSpace3.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.labSpace3.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace3_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("ERROR")
					|| staticInfo.labSpace3.checkForHumidity(
							staticInfo.labSpace3.getHumidity()).equals("ERROR")
					|| staticInfo.labSpace3.checkForOxygenLevel(
							staticInfo.labSpace3.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.labSpace3.checkForPressure(
							staticInfo.labSpace3.getPressure()).equals("ERROR")
					|| staticInfo.labSpace3.checkForSmoke(
							staticInfo.labSpace3.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.labSpace3.checkForGas(
							staticInfo.labSpace3.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.labSpace3.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace3_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("WARNING")
					|| staticInfo.labSpace3.checkForHumidity(
							staticInfo.labSpace3.getHumidity()).equals(
							"WARNING")
					|| staticInfo.labSpace3.checkForOxygenLevel(
							staticInfo.labSpace3.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.labSpace3.checkForPressure(
							staticInfo.labSpace3.getPressure()).equals(
							"WARNING")
					|| staticInfo.labSpace3.checkForSmoke(
							staticInfo.labSpace3.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.labSpace3.checkForGas(
							staticInfo.labSpace3.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.labSpace3.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace3_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.labSpace3.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace3_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_temperature.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.labSpace3_temperature.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.labSpace3_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace3_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.labSpace3.checkForHumidity(
					staticInfo.labSpace3.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.labSpace3.checkForHumidity(
					staticInfo.labSpace3.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.labSpace3_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.labSpace3.checkForHumidity(
					staticInfo.labSpace3.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.labSpace3_humidity.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace3_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.labSpace3.checkForOxygenLevel(
					staticInfo.labSpace3.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.labSpace3.checkForOxygenLevel(
					staticInfo.labSpace3.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.labSpace3.checkForOxygenLevel(
					staticInfo.labSpace3.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.labSpace3.checkForPressure(
					staticInfo.labSpace3.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.labSpace3.checkForPressure(
					staticInfo.labSpace3.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.labSpace3_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.labSpace3.checkForPressure(
					staticInfo.labSpace3.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.labSpace3_pressure.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace3_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.labSpace3.checkForSmoke(
					staticInfo.labSpace3.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.labSpace3_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.labSpace3_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.labSpace3.checkForGas(
					staticInfo.labSpace3.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.labSpace3_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace3.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace3_temperature.setText(Double
						.toString(staticInfo.labSpace3.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace3.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace3_humidity.setText(Double
						.toString(staticInfo.labSpace3.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace3.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace3_oxygen.setText(Double
						.toString(staticInfo.labSpace3.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace3.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace3_pressure.setText(Double
						.toString(staticInfo.labSpace3.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace3.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace3_smoke.setText(Double
						.toString(staticInfo.labSpace3.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace3
							.setGasDetection(temp.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace3_gas.setText(Double
						.toString(staticInfo.labSpace3.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("EMERGENCY")
					|| staticInfo.labSpace3.checkForHumidity(
							staticInfo.labSpace3.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace3.checkForOxygenLevel(
							staticInfo.labSpace3.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace3.checkForPressure(
							staticInfo.labSpace3.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace3.checkForSmoke(
							staticInfo.labSpace3.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace3.checkForGas(
							staticInfo.labSpace3.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.labSpace3.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace3_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("ERROR")
					|| staticInfo.labSpace3.checkForHumidity(
							staticInfo.labSpace3.getHumidity()).equals("ERROR")
					|| staticInfo.labSpace3.checkForOxygenLevel(
							staticInfo.labSpace3.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.labSpace3.checkForPressure(
							staticInfo.labSpace3.getPressure()).equals("ERROR")
					|| staticInfo.labSpace3.checkForSmoke(
							staticInfo.labSpace3.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.labSpace3.checkForGas(
							staticInfo.labSpace3.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.labSpace3.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace3_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("WARNING")
					|| staticInfo.labSpace3.checkForHumidity(
							staticInfo.labSpace3.getHumidity()).equals(
							"WARNING")
					|| staticInfo.labSpace3.checkForOxygenLevel(
							staticInfo.labSpace3.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.labSpace3.checkForPressure(
							staticInfo.labSpace3.getPressure()).equals(
							"WARNING")
					|| staticInfo.labSpace3.checkForSmoke(
							staticInfo.labSpace3.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.labSpace3.checkForGas(
							staticInfo.labSpace3.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.labSpace3.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace3_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.labSpace3.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace3_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_temperature.setForeground(Color.red);
			} else if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.labSpace3_temperature.setForeground(Color.pink);
			} else if (staticInfo.labSpace3.checkForTemperature(
					staticInfo.labSpace3.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.labSpace3_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace3_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.labSpace3.checkForHumidity(
					staticInfo.labSpace3.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_humidity.setForeground(Color.red);
			} else if (staticInfo.labSpace3.checkForHumidity(
					staticInfo.labSpace3.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.labSpace3_humidity.setForeground(Color.pink);
			} else if (staticInfo.labSpace3.checkForHumidity(
					staticInfo.labSpace3.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.labSpace3_humidity.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace3_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.labSpace3.checkForOxygenLevel(
					staticInfo.labSpace3.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.red);
			} else if (staticInfo.labSpace3.checkForOxygenLevel(
					staticInfo.labSpace3.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.pink);
			} else if (staticInfo.labSpace3.checkForOxygenLevel(
					staticInfo.labSpace3.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace3_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.labSpace3.checkForPressure(
					staticInfo.labSpace3.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_pressure.setForeground(Color.red);
			} else if (staticInfo.labSpace3.checkForPressure(
					staticInfo.labSpace3.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.labSpace3_pressure.setForeground(Color.pink);
			} else if (staticInfo.labSpace3.checkForPressure(
					staticInfo.labSpace3.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.labSpace3_pressure.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace3_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.labSpace3.checkForSmoke(
					staticInfo.labSpace3.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.labSpace3_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.labSpace3_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.labSpace3.checkForGas(
					staticInfo.labSpace3.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace3_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.labSpace3_gas.setForeground(Color.black);
			}
		}

	}

	public void processLabSpace4(String source) {
		if (source.equals("shouldStoreInDataBase")) {
			try {
				staticInfo.labSpace4 = (labSpace) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(staticInfo.labSpace4.toString());
			// set data
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
			// 处理将记录存入数据库的操作
			// --------------------
			int dataNum = 6;
			String locationVar = "LabSpace";
			int locationNumberVar = 4;
			labSpace entity = staticInfo.labSpace4;
			String[] eventType = new String[dataNum];// 需要根据情况定
			String[] eventAttribute = new String[dataNum];
			eventAttribute[0] = "Temperature";
			eventAttribute[1] = "Humidity";
			eventAttribute[2] = "Oxygen Level";
			eventAttribute[3] = "Pressure";
			eventAttribute[4] = "Smoke";
			eventAttribute[5] = "Gas";
			String[] location = new String[dataNum];
			for (int i = 0; i < dataNum; i++) {
				location[i] = locationVar;
			}
			int[] locationNumber = new int[dataNum];
			for (int i = 0; i < dataNum; i++) {
				locationNumber[i] = locationNumberVar;
			}
			double[] sensorData = new double[dataNum];
			sensorData[0] = entity.getTemperature();
			sensorData[1] = entity.getHumidity();
			sensorData[2] = entity.getOxygenLevel();
			sensorData[3] = entity.getPressure();
			sensorData[4] = entity.getSmokeDetection();
			sensorData[5] = entity.getGasDetection();
			Timestamp[] time = new Timestamp[dataNum];
			for (int i = 0; i < dataNum; i++) {
				time[i] = new Timestamp(System.currentTimeMillis());
			}
			// --------------1
			// 更改mainframe的界面的数据

			if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("EMERGENCY")
					|| staticInfo.labSpace4.checkForHumidity(
							staticInfo.labSpace4.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace4.checkForOxygenLevel(
							staticInfo.labSpace4.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace4.checkForPressure(
							staticInfo.labSpace4.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace4.checkForSmoke(
							staticInfo.labSpace4.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace4.checkForGas(
							staticInfo.labSpace4.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.labSpace4.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace4_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("ERROR")
					|| staticInfo.labSpace4.checkForHumidity(
							staticInfo.labSpace4.getHumidity()).equals("ERROR")
					|| staticInfo.labSpace4.checkForOxygenLevel(
							staticInfo.labSpace4.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.labSpace4.checkForPressure(
							staticInfo.labSpace4.getPressure()).equals("ERROR")
					|| staticInfo.labSpace4.checkForSmoke(
							staticInfo.labSpace4.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.labSpace4.checkForGas(
							staticInfo.labSpace4.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.labSpace4.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace4_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("WARNING")
					|| staticInfo.labSpace4.checkForHumidity(
							staticInfo.labSpace4.getHumidity()).equals(
							"WARNING")
					|| staticInfo.labSpace4.checkForOxygenLevel(
							staticInfo.labSpace4.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.labSpace4.checkForPressure(
							staticInfo.labSpace4.getPressure()).equals(
							"WARNING")
					|| staticInfo.labSpace4.checkForSmoke(
							staticInfo.labSpace4.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.labSpace4.checkForGas(
							staticInfo.labSpace4.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.labSpace4.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace4_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.labSpace4.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace4_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_temperature.setForeground(Color.red);
				eventType[0] = "EMERGENCY";// /////////--
			} else if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.labSpace4_temperature.setForeground(Color.pink);
				eventType[0] = "ERROR";
			} else if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.labSpace4_temperature
						.setForeground(Color.yellow);
				eventType[0] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace4_temperature
						.setForeground(Color.black);
				eventType[0] = "NORMAL";
			}
			// 处理湿度
			if (staticInfo.labSpace4.checkForHumidity(
					staticInfo.labSpace4.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_humidity.setForeground(Color.red);
				eventType[1] = "EMERGENCY";
			} else if (staticInfo.labSpace4.checkForHumidity(
					staticInfo.labSpace4.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.labSpace4_humidity.setForeground(Color.pink);
				eventType[1] = "ERROR";
			} else if (staticInfo.labSpace4.checkForHumidity(
					staticInfo.labSpace4.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.labSpace4_humidity.setForeground(Color.yellow);
				eventType[1] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace4_humidity.setForeground(Color.black);
				eventType[1] = "NORMAL";
			}
			// 处理氧气level
			if (staticInfo.labSpace4.checkForOxygenLevel(
					staticInfo.labSpace4.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.red);
				eventType[2] = "EMERGENCY";
			} else if (staticInfo.labSpace4.checkForOxygenLevel(
					staticInfo.labSpace4.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.pink);
				eventType[2] = "ERROR";
			} else if (staticInfo.labSpace4.checkForOxygenLevel(
					staticInfo.labSpace4.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.yellow);
				eventType[2] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.black);
				eventType[2] = "NORMAL";
			}
			// 处理压力
			if (staticInfo.labSpace4.checkForPressure(
					staticInfo.labSpace4.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_pressure.setForeground(Color.red);
				eventType[3] = "EMERGENCY";
			} else if (staticInfo.labSpace4.checkForPressure(
					staticInfo.labSpace4.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.labSpace4_pressure.setForeground(Color.pink);
				eventType[3] = "ERROR";
			} else if (staticInfo.labSpace4.checkForPressure(
					staticInfo.labSpace4.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.labSpace4_pressure.setForeground(Color.yellow);
				eventType[3] = "WARNING";
			} else {
				LoginGUI.mframe.labSpace4_pressure.setForeground(Color.black);
				eventType[3] = "NORMAL";
			}
			// 处理smoke
			if (staticInfo.labSpace4.checkForSmoke(
					staticInfo.labSpace4.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.labSpace4_smoke.setForeground(Color.red);
				eventType[4] = "EMERGENCY";
			} else {
				LoginGUI.mframe.labSpace4_smoke.setForeground(Color.black);
				eventType[4] = "NORMAL";
			}
			// 处理gas
			if (staticInfo.labSpace4.checkForGas(
					staticInfo.labSpace4.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_gas.setForeground(Color.red);
				eventType[5] = "EMERGENCY";
			} else {
				LoginGUI.mframe.labSpace4_gas.setForeground(Color.black);
				eventType[5] = "NORMAL";
			}
			// TODO 插入数据库相应的event信息
			// ----------------------------3
			insertIntoEventTable(dataNum, eventType, eventAttribute, location,
					locationNumber, sensorData, time);
		} else {
			if (source.equals("Temperature")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace4.setTemperature(temp.getTemperature());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace4_temperature.setText(Double
						.toString(staticInfo.labSpace4.getTemperature()));

			} else if (source.equals("Humidity")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace4.setHumidity(temp.getHumidity());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace4_humidity.setText(Double
						.toString(staticInfo.labSpace4.getHumidity()));

			} else if (source.equals("Oxygen Level")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace4.setOxygenLevel(temp.getOxygenLevel());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace4_oxygen.setText(Double
						.toString(staticInfo.labSpace4.getOxygenLevel()));

			} else if (source.equals("Pressure")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace4.setPressure(temp.getPressure());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace4_pressure.setText(Double
						.toString(staticInfo.labSpace4.getPressure()));

			} else if (source.equals("Smoke")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace4.setSmokeDetection(temp
							.getSmokeDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace4_smoke.setText(Double
						.toString(staticInfo.labSpace4.getSmokeDetection()));

			} else if (source.equals("Gas")) {

				try {
					labSpace temp = (labSpace) ois.readObject();
					staticInfo.labSpace4
							.setGasDetection(temp.getGasDetection());

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// set data
				LoginGUI.mframe.labSpace4_gas.setText(Double
						.toString(staticInfo.labSpace4.getGasDetection()));

			}
			// 更改mainframe的界面的数据

			if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("EMERGENCY")
					|| staticInfo.labSpace4.checkForHumidity(
							staticInfo.labSpace4.getHumidity()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace4.checkForOxygenLevel(
							staticInfo.labSpace4.getOxygenLevel()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace4.checkForPressure(
							staticInfo.labSpace4.getPressure()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace4.checkForSmoke(
							staticInfo.labSpace4.getSmokeDetection()).equals(
							"EMERGENCY")
					|| staticInfo.labSpace4.checkForGas(
							staticInfo.labSpace4.getGasDetection()).equals(
							"EMERGENCY")) {
				staticInfo.labSpace4.setErrorMessage("EMERGENCY");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace4_light.setIcon(flash);
				LoginGUI.mframe.alarm.play();
			} else if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("ERROR")
					|| staticInfo.labSpace4.checkForHumidity(
							staticInfo.labSpace4.getHumidity()).equals("ERROR")
					|| staticInfo.labSpace4.checkForOxygenLevel(
							staticInfo.labSpace4.getOxygenLevel()).equals(
							"ERROR")
					|| staticInfo.labSpace4.checkForPressure(
							staticInfo.labSpace4.getPressure()).equals("ERROR")
					|| staticInfo.labSpace4.checkForSmoke(
							staticInfo.labSpace4.getSmokeDetection()).equals(
							"ERROR")
					|| staticInfo.labSpace4.checkForGas(
							staticInfo.labSpace4.getGasDetection()).equals(
							"ERROR")) {
				staticInfo.labSpace4.setErrorMessage("ERROR");
				ImageIcon flash = new ImageIcon("lib/flash.gif");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace4_light.setIcon(flash);
				// 关闭警报
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("WARNING")
					|| staticInfo.labSpace4.checkForHumidity(
							staticInfo.labSpace4.getHumidity()).equals(
							"WARNING")
					|| staticInfo.labSpace4.checkForOxygenLevel(
							staticInfo.labSpace4.getOxygenLevel()).equals(
							"WARNING")
					|| staticInfo.labSpace4.checkForPressure(
							staticInfo.labSpace4.getPressure()).equals(
							"WARNING")
					|| staticInfo.labSpace4.checkForSmoke(
							staticInfo.labSpace4.getSmokeDetection()).equals(
							"WARNING")
					|| staticInfo.labSpace4.checkForGas(
							staticInfo.labSpace4.getGasDetection()).equals(
							"WARNING")) {
				staticInfo.labSpace4.setErrorMessage("WARNING");
				ImageIcon flash = new ImageIcon("lib/yellowlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace4_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}
			} else {
				staticInfo.labSpace4.setErrorMessage("NORMAL");
				ImageIcon flash = new ImageIcon("lib/greenlight.jpg");
				flash.setImage(flash.getImage().getScaledInstance(lightWidth,
						lightHeight, Image.SCALE_DEFAULT));
				LoginGUI.mframe.labSpace4_light.setIcon(flash);
				if (LoginGUI.mframe.alarm.flag == 1) {
					alarmShouldStop();
				}

			}
			// 到这里灯和警报处理完毕 开始处理字体颜色 以提示哪些属性发生了变化
			// 处理温度
			if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_temperature.setForeground(Color.red);
			} else if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("ERROR")) {
				LoginGUI.mframe.labSpace4_temperature.setForeground(Color.pink);
			} else if (staticInfo.labSpace4.checkForTemperature(
					staticInfo.labSpace4.getTemperature()).equals("WARNING")) {
				LoginGUI.mframe.labSpace4_temperature
						.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace4_temperature
						.setForeground(Color.black);
			}
			// 处理湿度
			if (staticInfo.labSpace4.checkForHumidity(
					staticInfo.labSpace4.getHumidity()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_humidity.setForeground(Color.red);
			} else if (staticInfo.labSpace4.checkForHumidity(
					staticInfo.labSpace4.getHumidity()).equals("ERROR")) {
				LoginGUI.mframe.labSpace4_humidity.setForeground(Color.pink);
			} else if (staticInfo.labSpace4.checkForHumidity(
					staticInfo.labSpace4.getHumidity()).equals("WARNING")) {
				LoginGUI.mframe.labSpace4_humidity.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace4_humidity.setForeground(Color.black);
			}
			// 处理氧气level
			if (staticInfo.labSpace4.checkForOxygenLevel(
					staticInfo.labSpace4.getOxygenLevel()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.red);
			} else if (staticInfo.labSpace4.checkForOxygenLevel(
					staticInfo.labSpace4.getOxygenLevel()).equals("ERROR")) {
				LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.pink);
			} else if (staticInfo.labSpace4.checkForOxygenLevel(
					staticInfo.labSpace4.getOxygenLevel()).equals("WARNING")) {
				LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace4_oxygen.setForeground(Color.black);
			}
			// 处理压力
			if (staticInfo.labSpace4.checkForPressure(
					staticInfo.labSpace4.getPressure()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_pressure.setForeground(Color.red);
			} else if (staticInfo.labSpace4.checkForPressure(
					staticInfo.labSpace4.getPressure()).equals("ERROR")) {
				LoginGUI.mframe.labSpace4_pressure.setForeground(Color.pink);
			} else if (staticInfo.labSpace4.checkForPressure(
					staticInfo.labSpace4.getPressure()).equals("WARNING")) {
				LoginGUI.mframe.labSpace4_pressure.setForeground(Color.yellow);
			} else {
				LoginGUI.mframe.labSpace4_pressure.setForeground(Color.black);
			}
			// 处理smoke
			if (staticInfo.labSpace4.checkForSmoke(
					staticInfo.labSpace4.getSmokeDetection()).equals(
					"EMERGENCY")) {
				LoginGUI.mframe.labSpace4_smoke.setForeground(Color.red);
			} else {
				LoginGUI.mframe.labSpace4_smoke.setForeground(Color.black);
			}
			// 处理gas
			if (staticInfo.labSpace4.checkForGas(
					staticInfo.labSpace4.getGasDetection()).equals("EMERGENCY")) {
				LoginGUI.mframe.labSpace4_gas.setForeground(Color.red);
			} else {
				LoginGUI.mframe.labSpace4_gas.setForeground(Color.black);
			}
		}

	}

}