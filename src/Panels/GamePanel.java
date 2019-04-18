package Panels;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {

	public static int WIDTH = 1200;
	public static int HEIGHT = 720;
	
	private Ball ball1 = new Ball();
	
	private int fps=60;
	static int score=0;
	private boolean running=false;
	static boolean clicked=false;
	public int cursorX; 
	public int cursorY;
	private Graphics2D g;
	private Thread thread;
	private BufferedImage image;
	//konstruktor
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@Override
	public void run() {
		running = true;
		
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		long startTime;
		long URDTimeMillis;
		long waitTime;
		long targetTime = 1000 / fps;
		
		while(running) {
			startTime = System.nanoTime();
			gameRender();
			gameDraw();
			checkWinCondition();
			//fps
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			try {
				Thread.sleep(waitTime);
			}
			catch (Exception e) {}
			
			}
		
		
	}
	private void gameRender() {
		g.setColor(new Color(255,253,208));
		g.fillRect(0,0,WIDTH,HEIGHT);
		g.setColor(new Color(126,147,232));
		
		//ball
		g = ball1.draw(g,cursorX,cursorY,clicked);
		clicked = false;
		System.out.println(clicked);
		g.setColor(new Color(0,0,0));
		Font f = new Font("Calibri", Font.BOLD, 72);
		g.setFont(f);
		g.drawString("Score: "+score,10,640);
	}
	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	private boolean checkWinCondition() {
		if(score>10) {
			return true;
		}else {
			return false;		
			}
		}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		cursorX=e.getX();
		cursorY=e.getY();	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clicked=true;
		System.out.println("MYSZEK	 KLIKNIETY");
		//ball1.update(ball1.checkHit(cursorX, cursorY));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public static void addScore() {
		// TODO Auto-generated method stub
		score++;
		
	}



}
