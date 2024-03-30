import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Object{
	private Point Eposition,Pposition;
	private static BufferedImage Alice;
	public Enemy(Point position,int life,int rateOfFire) {
		super(position,life,rateOfFire);
		try {
			Alice=ImageIO.read(this.getClass().getResource("Illust_54550636_20200315_054607.png"));
		} catch (IOException e) {
			System.out.println("Alice File wrong!");
		}
	}
	public void shoot(double degree) {
		if(canShoot(rateOfFire)) {
		Eposition=new Point();
		Eposition.setLocation(this.position);
		bullets.add(new Bullet(Eposition,1,1,degree,false,false));}
	}
	public void shoot(Point p) {
		if(canShoot(rateOfFire)) {
		Eposition=new Point();
		Eposition.setLocation(this.position);
		bullets.add(new Bullet(Eposition,5,5,p,false,false));}//(Point position,int attackPower,int speed,Point p,boolean isGood,boolean close)
	}
	
	public void getPlayer(Point position) {
		Pposition=position;
	}
	public Point aim(Point p) {
		Eposition=new Point();
		Eposition.setLocation(this.position);//get own position
		int x,y,px,py;
		x=Eposition.x;
		y=Eposition.y;
		px=p.x;
		py=p.y;
		//get degree
		return new Point(px-x,py-y);
		
	}/**/
	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}
	public void move(int x,int y) {
		position.setLocation(position.x+x,position.y+y);
	}
	public void beenHit(int power) {
		if(life>0) {
			life=life-power;
		}else if(life<=0){
			System.out.println("Dead");
		}
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(Alice, (int)position.getX()-25, (int)position.getY()-25,30,50, null);
	}
}