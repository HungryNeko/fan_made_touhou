import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Object{
	private Point Pposition;
	private int i=0;
	private static BufferedImage Mls;
public Player(Point position, int life,int rateOfFire) {
		super(position, life,rateOfFire);
		try {
			Mls=ImageIO.read(this.getClass().getResource("Collectible_marisas_broom.png"));
		} catch (IOException e) {
			System.out.println("Mls File wrong!");
		}
	}

@Override
public void shoot() {
	if(canShoot(rateOfFire)) {
		Pposition=new Point();
		Pposition.setLocation(this.position);
		bullets.add(new Bullet(Pposition,100,10,270.0,false,false));//(Point position,int attackPower,int speed,double degree,boolean isGood,boolean close )
	}
}
public void shoot2() {
	if(canShoot(rateOfFire)) {
		Pposition=new Point();
		Pposition.setLocation(this.position);
		if(i==0) {
			i=1;
		bullets.add(new Bullet(Pposition,100,10,280.0,false,false));//(Point position,int attackPower,int speed,double degree,boolean isGood,boolean close )
		}else if(i==1) {
			i=2;
		bullets.add(new Bullet(Pposition,100,10,270.0,false,false));
			}
		else if(i==2) {
			i=0;
		bullets.add(new Bullet(Pposition,100,10,260.0,false,false));
			}
		}
	
}
public void superShoot(int i) {//wait to finish!!!!!!!!!!!!!!!
	Pposition=new Point();
	Pposition.setLocation(this.position);
	if(i==1) {
		bullets.add(new Bullet(Pposition,100,10,260.0,false,false));
	}
}
@Override
public void draw(Graphics g) {
	//g.setColor(Color.blue);
	//g.drawRect((int)position.getX()-10, (int)position.getY()-10,20,20);//draw all the objects
	g.drawImage(Mls, (int)position.getX()-25, (int)position.getY()-25,50,50, null);
}
public boolean beenHitByE(int power) {

	if(life>0) {
		life-=power;
		return false;
	}else {
		return true;
	}
}


}
