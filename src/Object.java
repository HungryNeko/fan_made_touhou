import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Object {
protected Point position;
protected int life;
protected ArrayList<Bullet> bullets;
protected int rateOfFire;
private int counter=0;
public Object(Point position,int life,int rateOfFire) {
	this.position=position;
	this.life=life;
	this.bullets=new ArrayList<Bullet>();
	this.rateOfFire=rateOfFire;
}
public int getLife() {
	return life;
}
public void setLife(int life) {
	this.life=life;
}
public void setPosition(Point Position) {
	this.position=Position;
}
public Point getPosition() {
	return position;
}
public void setRate(int rateOfFire) {
	this.rateOfFire=rateOfFire;
}
public int getRate() {
	return rateOfFire;
}
public Boolean canShoot(int rate) {
	counter++;
	if(counter==rate) {
	counter=0;
	return true;}
	else if(counter>rate)
	{
		counter=0;
		return true;
	}else {
		return false;
	}
}
public abstract void shoot();

public void deletBullet(int w,int h) {//delete the bullets out of frame
	for(int i=bullets.size()-1;i>=0;i--) {
		if(bullets.get(i).getPosition().getX()>w||bullets.get(i).getPosition().getY()>h||bullets.get(i).getPosition().getY()<0||bullets.get(i).getPosition().getX()<0){//if bullet if out of frame
			bullets.remove(i);
		}
	}
}
public void draw(Graphics g) {
	g.drawRect((int)position.getX()-10, (int)position.getY()-10,20,20);//draw all the objects
}
public void ReCanShoot() {
	counter=0;
}


}
