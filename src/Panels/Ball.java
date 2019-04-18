package Panels;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Ball {
	int x=500;
	int y=300;
	Random generator = new Random();
	public Ball() {
		
	}
	public Graphics2D draw(Graphics2D g, int cursorX, int cursorY,boolean clicked) {
		update(cursorX,cursorY,clicked);
		g.setColor(new Color(126,147,232));
		g.fillOval(x, y, 100, 100);
		System.out.println();
		
		return g;
		
	}
	public void update(int cursorX, int cursorY,boolean clicked) {
		int tempx = x+50;
		int tempy = y+50;
		int distance = (int) Math.sqrt(Math.pow((cursorX-tempx),2)+Math.pow((cursorY-tempy),2));
		/*System.out.println("Distance: "+distance);
		System.out.println("cursorX: "+cursorX);
		System.out.println("cursorY: "+cursorY);
		System.out.println("tempx: "+tempx);
		System.out.println("tempy: "+tempy);*/
		if ((distance<50)&&(clicked==true)){
			hitBall();
			GamePanel.addScore();
		}
		
	}
	public void hitBall() {
		this.x=(int) (generator.nextDouble()*1100);
		this.y=(int) (generator.nextDouble()*600);
	}
}

