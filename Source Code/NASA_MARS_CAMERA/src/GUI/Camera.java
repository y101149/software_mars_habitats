package GUI;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.image.*;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

class Zoom extends Canvas implements ImageObserver {
	Image img;
	int iw;
	int ih;
	int x;
	int y;
	int adjX = 0;
	int adjY = 0;
	int adjW = 0;
	int adjH = 0;
	Dimension ds;

	Zoom() {
		img = getToolkit().getImage("lib/livingCell1.jpg");
//		ds = getToolkit().getScreenSize();
		
		ds = new Dimension(500,500);
		

	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		setImageBounds();
		g2d.translate(x, y);
		g2d.drawImage(img, 0, 0, iw, ih, this);

	}

	public void setImageBounds() {

		x = (int) ds.getWidth() / 2 - img.getWidth(this) / 2 + adjX;
		y = (int) ds.getHeight() / 2 - img.getHeight(this) / 2 + adjY;
		iw = img.getWidth(this) + adjW;
		ih = img.getHeight(this) + adjH;
		
//		x = (int) ds.getWidth() / 2 - img.getWidth(this) / 2 + adjX;
//		y = (int) ds.getHeight() / 2 - img.getHeight(this) / 2 + adjY;
//		iw = img.getWidth(this) + adjW;
//		ih = img.getHeight(this) + adjH;

	}

	public void zoomIn() {
		adjX -= 10;
		adjY -= 10;
		adjW += 20;
		adjH += 20;
	}

	public void zoomOut() {
//		if (x < (int) ds.getWidth() / 2 - img.getWidth(this) / 2) {
			adjX += 10;
			adjY += 10;
			adjW -= 20;
			adjH -= 20;
//		}
	}
	//自己写
	public void moveLeft(){
//		if (x < (int) ds.getWidth() / 2 - img.getWidth(this) / 2) {

			adjX += 10;
//		}

	}
	public void moveRight(){
//		if (x < (int) ds.getWidth() / 2 - img.getWidth(this) / 2) {

			adjX -= 10;
		
//		}
	}
	public void moveUp(){
			adjY += 10;
		

	}
	public void moveDown(){
			adjY -= 10;
		

	}

}

class MainInter extends JFrame implements ActionListener {
	
	
	int index = 0;
	int no = 0;
	String entity = "";
	
	JButton btZoomIn;
	JButton btZoomOut;
	//自己加
	JButton btMoveLeft;
	JButton btMoveRight;
	JButton btMoveUp;
	JButton btMoveDown;
	
	JButton next;
	JButton previous;
	
	
	
	JLabel info;

	

	
	JPanel panel;
	JPanel changeImage;
	Zoom z;
	
	HashMap<Integer, String> mars_location = new HashMap<Integer,String>();
	

	MainInter(int x, int y, int number) {
		
		
		
		
		
		
		mars_location.put(0, "lib/livingCell1.jpg");
		mars_location.put(1, "lib/livingCell2.jpg");
		mars_location.put(2, "lib/livingCell3.jpg");
		mars_location.put(3, "lib/livingCell4.jpg");
		mars_location.put(4, "lib/livingCell5.jpg");
		mars_location.put(5, "lib/livingCell6.jpg");
		mars_location.put(6, "lib/livingCell7.jpg");
		mars_location.put(7, "lib/livingCell8.jpg");
		mars_location.put(8, "lib/livingCell9.jpg");
		mars_location.put(9, "lib/livingCell10.jpg");
		mars_location.put(10, "lib/livingCell11.jpg");
		mars_location.put(11, "lib/livingCell12.jpg");
		mars_location.put(12, "lib/livingCell13.jpg");
		mars_location.put(13, "lib/livingCell14.jpg");
		mars_location.put(14, "lib/livingCell15.jpg");
		mars_location.put(15, "lib/commandSpace1.jpg");
		mars_location.put(16, "lib/commandSpace2.jpg");
		mars_location.put(17, "lib/labSpace1.jpg");
		mars_location.put(18, "lib/labSpace2.jpg");
		mars_location.put(19, "lib/labSpace3.jpg");
		mars_location.put(20, "lib/labSpace4.jpg");
		mars_location.put(21, "lib/equipSpace.jpg");
		mars_location.put(22, "lib/wellArea.jpg");
		mars_location.put(23, "lib/heatingArea.jpg");
		mars_location.put(24, "lib/external.jpg");


		if (number == 1) {
			setTitle("Console Camera #1");
		}
		else if (number == 2) {
			setTitle("Console Camera #2");
		}
		else if (number == 3) {
			setTitle("Console Camera #3");
		}
		else if (number == 4) {
			setTitle("Console Camera #4");
		}
		else if (number == 5) {
			setTitle("Console Camera #5");
		}
		else if (number == 6) {
			setTitle("Console Camera #6");
		}
		else if (number == 7) {
			setTitle("Console Camera #7");
		}
		else if (number == 8) {
			setTitle("Console Camera #8");
		}
		else if (number == 9) {
			setTitle("Console Camera #9");
		}
		else if (number == 10) {
			setTitle("Console Camera #10");
		}
		
		z = new Zoom();
		btZoomIn = new JButton("+");
		btZoomIn.addActionListener(this);
		btZoomIn.addMouseListener(new MouseListener() {
			Timer timer;

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				timer.cancel();
//				try {
//					Socket s = new Socket("localhost", 9999);
//					OutputStream os = s.getOutputStream();
//					ObjectOutputStream oos = new ObjectOutputStream(os);
//
//					oos.writeUTF("Camera#"+number + " Zoom In");
//					
//					oos.close();
//					os.close();
//					s.close();
//
//					
//
//				} catch (Exception e1) {
//					System.out.println(e1);
//					JOptionPane.showMessageDialog(null,
//					"Socket Error ",
//					"Error Message", JOptionPane.ERROR_MESSAGE);
//
//				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						z.zoomIn();
						z.repaint();
						
						try {
							Socket s = new Socket("localhost", 9999);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF("Camera#"+number + " Zoom In");
							
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
				}, 0, 100);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		btZoomOut = new JButton("-");
		btZoomOut.addActionListener(this);
		btZoomOut.setOpaque(false);
		btZoomOut.addMouseListener(new MouseListener() {
			Timer timer;

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				timer.cancel();
//				try {
//					Socket s = new Socket("localhost", 9999);
//					OutputStream os = s.getOutputStream();
//					ObjectOutputStream oos = new ObjectOutputStream(os);
//
//					oos.writeUTF("Camera#"+number + " Zoom Out");
//					
//					oos.close();
//					os.close();
//					s.close();
//
//					
//
//				} catch (Exception e1) {
//					System.out.println(e1);
//					JOptionPane.showMessageDialog(null,
//					"Socket Error ",
//					"Error Message", JOptionPane.ERROR_MESSAGE);
//
//				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						z.zoomOut();
						z.repaint();
						try {
							Socket s = new Socket("localhost", 9999);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF("Camera#"+number + " Zoom Out");
							
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
				}, 0, 100);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//自己加 
		btMoveLeft = new JButton("left");
		btMoveLeft.addActionListener(this);
		btMoveLeft.addMouseListener(new MouseListener() {
			Timer timer;

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				timer.cancel();
				try {
					Socket s = new Socket("localhost", 9999);
					OutputStream os = s.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);

					oos.writeUTF("Camera#"+number + " Move Left");
					
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
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						z.moveLeft();
						z.repaint();
						try {
							Socket s = new Socket("localhost", 9999);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF("Camera#"+number + " Move Left");
							
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
				}, 0, 100);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		btMoveRight = new JButton("right");
		btMoveRight.addActionListener(this);
		btMoveRight.addMouseListener(new MouseListener() {
			Timer timer;

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				timer.cancel();
//				try {
//					Socket s = new Socket("localhost", 9999);
//					OutputStream os = s.getOutputStream();
//					ObjectOutputStream oos = new ObjectOutputStream(os);
//
//					oos.writeUTF("Camera#"+number + " Move Right");
//					
//					oos.close();
//					os.close();
//					s.close();
//
//					
//
//				} catch (Exception e1) {
//					System.out.println(e1);
//					JOptionPane.showMessageDialog(null,
//					"Socket Error ",
//					"Error Message", JOptionPane.ERROR_MESSAGE);
//
//				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						z.moveRight();
						z.repaint();
						
						try {
							Socket s = new Socket("localhost", 9999);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF("Camera#"+number + " Move Right");
							
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
				}, 0, 100);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		btMoveUp = new JButton("up");
		btMoveUp.addActionListener(this);
		btMoveUp.addMouseListener(new MouseListener() {
			Timer timer;

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				timer.cancel();
//				try {
//					Socket s = new Socket("localhost", 9999);
//					OutputStream os = s.getOutputStream();
//					ObjectOutputStream oos = new ObjectOutputStream(os);
//
//					oos.writeUTF("Camera#"+number + " Move Up");
//					
//					oos.close();
//					os.close();
//					s.close();
//
//					
//
//				} catch (Exception e1) {
//					System.out.println(e1);
//					JOptionPane.showMessageDialog(null,
//					"Socket Error ",
//					"Error Message", JOptionPane.ERROR_MESSAGE);
//
//				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						z.moveUp();
						z.repaint();
						try {
							Socket s = new Socket("localhost", 9999);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF("Camera#"+number + " Move Up");
							
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
				}, 0, 100);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		btMoveDown = new JButton("down");
		btMoveDown.addActionListener(this);
		btMoveDown.addMouseListener(new MouseListener() {
			Timer timer;

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				timer.cancel();
//				try {
//					Socket s = new Socket("localhost", 9999);
//					OutputStream os = s.getOutputStream();
//					ObjectOutputStream oos = new ObjectOutputStream(os);
//
//					oos.writeUTF("Camera#"+number + " Move Down");
//					
//					oos.close();
//					os.close();
//					s.close();
//
//					
//
//				} catch (Exception e1) {
//					System.out.println(e1);
//					JOptionPane.showMessageDialog(null,
//					"Socket Error ",
//					"Error Message", JOptionPane.ERROR_MESSAGE);
//
//				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						z.moveDown();
						z.repaint();
						
						try {
							Socket s = new Socket("localhost", 9999);
							OutputStream os = s.getOutputStream();
							ObjectOutputStream oos = new ObjectOutputStream(os);

							oos.writeUTF("Camera#"+number + " Move Down");
							
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
				}, 0, 100);
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		next = new JButton("Next Location");
		next.addActionListener(this);
		previous = new JButton("Previous Location");
		previous.addActionListener(this);
		info = new JLabel();
		info.setText("livingCell1 Camera");
		
		
		
		
		
		add(z, BorderLayout.CENTER);
		
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(btZoomIn);
		panel.add(btZoomOut);
		//自己加
		panel.add(btMoveLeft);
		panel.add(btMoveRight);
		panel.add(btMoveUp);
		panel.add(btMoveDown);

		add(panel, BorderLayout.SOUTH);
		
		changeImage = new JPanel();
		changeImage.setLayout(new FlowLayout());
		
		changeImage.add(previous);
		changeImage.add(info);
		changeImage.add(next);


		add(changeImage, BorderLayout.NORTH);
		
		
		
		setLocation(x, y);
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {
			if (index == 24) {
				index = 0;
			}
			else {
				index++;
			}
			String infoText = mars_location.get(index).substring(4).split("\\.")[0];
			
			
			z.img = getToolkit().getImage(mars_location.get(index));
			info.setText(infoText + " Camera");
			z.adjX = 0;
			z.adjY = 0;
			z.adjW = 0;
			z.adjH = 0;
			
			z.repaint();
		}
		else if (e.getSource() == previous) {
			if (index == 0) {
				index = 24;
			}
			else {
				index--;
			}
			String infoText = mars_location.get(index).substring(4).split("\\.")[0];
			
			z.img = getToolkit().getImage(mars_location.get(index));
			info.setText(infoText + " Camera");
			

			z.img = getToolkit().getImage(mars_location.get(index));
			z.adjX = 0;
			z.adjY = 0;
			z.adjW = 0;
			z.adjH = 0;
			
			z.repaint();
		}
		
		else if (e.getSource() == btZoomIn) {
//			z.img = getToolkit().getImage("lib/emptyStar.png");
			z.zoomIn();
			z.repaint();
		} else if (e.getSource() == btZoomOut) {
			z.zoomOut();
			z.repaint();
		}
		//自己加
		else if (e.getSource() == btMoveLeft) {
			z.moveLeft();
			z.repaint();
		}
		else if (e.getSource() == btMoveRight) {
			z.moveRight();
			z.repaint();
		}
		else if (e.getSource() == btMoveUp) {
			z.moveUp();
			z.repaint();
		}
		else if (e.getSource() == btMoveDown) {
			z.moveDown();
			z.repaint();
		}
	}

}

//public class imageZoom {
//
//	public static void main(String args[]) {
//		new MainInter();
//
//	}
//
//}