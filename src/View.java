import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class View extends JPanel{
private Model m;
private int w,h,i=0;
boolean p=false;
public View(Model m) {
	this.m=m;
	//this.setBackground(Color.GRAY);
	new Timer(1, new ActionListener() {//add a timer

		@Override
		public void actionPerformed(ActionEvent e) {
			m.deleteB(getWidth(), getHeight());
			setWH(getWidth(), getHeight());
			if(p==false) {//only one player
			m.newPlayer(w,h);
			p=true;
			}
			if(i==100) {//a new enemy every 1s
				m.newEnemy(w,h);
				i=0;
				
			}
			i++;
			m.movePlayer(w,h);
			m.deletEnemie(w,h);
			repaint();
		}
	}).start();
	
	

}
protected void paintComponent(Graphics g) {//paint all bubbles
	super.paintComponent(g);
	m.drawAll(g);
}
public void setWH(int w,int h) {
this.w=w;
this.h=h;
}
}
