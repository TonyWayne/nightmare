package com.apache.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Rain extends JDialog implements ActionListener {
	private Random random = new Random();
	private Dimension screenSize;
	private JPanel graphicsPanel;
	private final static int gap = 10;
	private int[] posArr;
	private int lines;
	private int columns;
	
	static ArrayList<String> arrays = new ArrayList<String>();

	public Rain() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		graphicsPanel = new GraphicsPanel();
		add(graphicsPanel, BorderLayout.CENTER);
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		Image image = defaultToolkit.createImage(new MemoryImageSource(0, 0, null, 0, 0));
		Cursor invisibleCursor = defaultToolkit.createCustomCursor(image, new Point(0, 0), "cursor");
		setCursor(invisibleCursor);
		KeyPressListener keyPressListener = new KeyPressListener();
		this.addKeyListener(keyPressListener);
		// this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		lines = screenSize.height / gap;
		columns = screenSize.width / gap;

		posArr = new int[columns + 1];
		random = new Random();
		for (int i = 0; i < posArr.length; i++) {
			posArr[i] = random.nextInt(lines);
		}

		// 姣忕10甯分�
		new Timer(100, this).start();
	}

	/**
	 * @return 闅忔満瀛楃
	 */
	private char getChr() {
		return (char) (random.nextInt(94) + 33);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		graphicsPanel.repaint();
	}

	private class GraphicsPanel extends JPanel {
		Random random=new Random();
		int colorType=random.nextInt(2);
		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setFont(getFont().deriveFont(Font.BOLD));
			g2d.setColor(Color.black);
			
			g2d.fillRect(0, 0, screenSize.width, screenSize.height);

			// 鍥剧墖
			BufferedImage image;
			try {
				int currentColumn = 0;
				image = ImageIO.read(new File("resources/timg.jpg"));
				int height = image.getHeight();
				int width = image.getWidth();
				int width_1 = screenSize.width / width;
				int height_1 = screenSize.height / height;
				int num = (width_1 < height_1) ? width_1 : height_1;
				for (int x = 0; x < screenSize.width; x += gap) {
					int endPos = posArr[currentColumn];
					int cg = 0;
					for (int j = endPos - 20; j < endPos; j++) {
						// 娓愬彉鑹�
						cg += 20;
						if (cg > 255) {
							cg = 255;
						}
						if(colorType==1) {
							g2d.setColor(new Color(cg,0,0));
						}else {
							g2d.setColor(new Color(0,cg,0));
						}
						
//						g2d.setColor(new Color(0,cg,0));
						g2d.drawString(String.valueOf(getChr()), x, j * gap);
					}
					if (x >= ((screenSize.width - width * num) / 2)
							&& x <= ((width - 1) * num + (screenSize.width - width * num) / 2)
							&& endPos * gap >= ((screenSize.height - height * num) / 2)
							&& endPos * gap <= ((height - 1) * num + (screenSize.height - height * num) / 2)) {
						int color = image.getRGB((x - ((screenSize.width - width * num) / 2)) / num,
								(endPos * gap - ((screenSize.height - height * num) / 2)) / num);
						int r = (color & 0xff0000) >> 16;
						int green = (color & 0xff00) >> 8;
						int b = (color & 0xff);
						float gray = 0.299f * r + 0.578f * green + 0.114f * b;
						
						if (gray < 230) {
							String s = String.valueOf(x).concat(",").concat(String.valueOf(endPos * gap));
							arrays.add(s);
						}
					}
					// 娌℃斁瀹屼竴甯э紝褰撳墠鍒椾笂闆ㄧ偣鐨勪綅缃殢鏈轰笅绉�1~5琛�
					posArr[currentColumn] += random.nextInt(5);
					// 褰撻洦鐐逛綅缃秴杩囧睆骞曢珮搴︽椂锛岄噸鏂颁骇鐢熶竴涓殢鍗充綅缃�
					if (posArr[currentColumn] * gap > getHeight()) {
						posArr[currentColumn] = random.nextInt(lines);
					}
					currentColumn++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for(String s : arrays) {
				String[] s1 = s.split(",");
				int x_1 = Integer.parseInt(s1[0]);
				int y_1 = Integer.parseInt(s1[1]);
				if(colorType==1) {
					g2d.setColor(new Color(255, 0, 0));
				}else {
					g2d.setColor(new Color(0, 255, 0));
				}
				
				g2d.drawString(String.valueOf(getChr()), x_1, y_1);
			}
		}
	}

	private class KeyPressListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		new Rain();
	}
}