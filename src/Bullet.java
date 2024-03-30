import java.awt.Graphics;
import java.awt.Point;

public class Bullet {
private int attackPower;
private Point position;
private int speed;
private double degree;
private boolean isGood;
private Point p;
private boolean close;
public Bullet(Point position,int attackPower,int speed,double degree,boolean isGood,boolean close ) {
	this.position=position;
	this.attackPower=attackPower;
	this.speed=speed;
	this.degree=degree;
	this.isGood=isGood;
	this.close=close;
	
	
}
public Bullet(Point position,int attackPower,int speed,Point p,boolean isGood,boolean close) {//if have aimed
	this.position=position;
	this.attackPower=attackPower;
	this.speed=speed;
	this.p=new Point();
	this.p=p;
	this.isGood=isGood;
	this.close=close;
	
}
public Point getPosition() {
	return position;
}
public void moveBullet() {
	double x1=position.x;
	double y1=position.y;
	if(p==null) {
	double x=speed*Math.cos(Math.toRadians(degree));//speed*cos
	double y=speed*Math.sin(Math.toRadians(degree));//speed*sin
	this.position.setLocation(x+x1, y+y1);
	}else {
		//get sin and cos first
	double z=Math.sqrt(p.x*p.x+p.y*p.y);
	double x=speed*p.x/z;//speed*cos
	double y=speed*p.y/z;//speed*sin
	position.setLocation(x+x1, y+y1);
	}
}
public void setPosition(Point position) {
	this.position=position;
}
public int getAttack() {
	return attackPower;
}
public int getSpeed() {
	return speed;
}
public void setSpeed(int speed) {
	this.speed=speed;
}
public void setDegree(double degree) {
	this.degree=degree;
}
public double getDegree() {
	return degree;
}
public boolean getClose() {
	return close;
}
public void setClose(boolean close) {
	this.close=close;
}
public void draw(Graphics g) {
	g.drawOval((int)position.getX()-10, (int)position.getY()-10,20,20);//draw bullets
	
}

}
