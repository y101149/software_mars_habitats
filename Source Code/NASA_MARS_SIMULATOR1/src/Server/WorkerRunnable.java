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
import GUI.InputFrame;
import GUI.LoginGUI;
import JDBC.JDBCConnection;

class staticInfo {
	String text = "";
}

/**

 */
public class WorkerRunnable implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;

	InputStream input;
	ObjectInputStream ois;



	public WorkerRunnable(Socket clientSocket, String serverText) {
		this.clientSocket = clientSocket;
		this.serverText = serverText;
	}

	public void run() {

		try {
			input = clientSocket.getInputStream();
			ois = new ObjectInputStream(input);
			String operation_indicator = ois.readUTF() + "\n";
			InputFrame.operation_indicator.append(operation_indicator);
			
//			System.out.println(operation_indicator);
			
			
			
			
			input.close();

		} catch (Exception e) {
			// report exception somewhere.
			e.printStackTrace();
		}

	
	}

	
}