import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Model {
Point point=new Point();//player position
private ArrayList<Enemy> enemies;
private ArrayList<Player> players;
boolean right=true;
private int enemyKilled=0,score=0,close=0;
private int power=0;
private ArrayList<Bullet> gears;
private static BufferedImage MlsGear;
public Model(int w,int h) {
	enemies=new ArrayList<Enemy>();
	players=new ArrayList<Player>();
	gears=new ArrayList<Bullet>();
	try {
		MlsGear=ImageIO.read(this.getClass().getResource("Th11Marisa.png"));
	} catch (IOException e) {
		System.out.println("MlsGear File wrong!");
	}
}
public void newPlayer(int w,int h) {
	if(players.isEmpty()) {//if there is no player
	point.setLocation(w/2, h*4/5);
	players.add(new Player(point, 100, 8));//(Point position, int life,int rateOfFire)
	players.get(0).shoot();
	}else {
		players.get(0).shoot();
	}
}
public void newEnemy(int w,int h) {
	Point point=new Point();
	point.setLocation(w*Math.random(), h*Math.random()/3);
	enemies.add(new Enemy(point,400,30));//Enemy(Point position,int life,int rateOfFire)
}
public void enemyFire() {
	for(int i=0; i<enemies.size();i++) {
		enemies.get(i).shoot();
	}
}
public void deleteB(int w,int h) {//delete bullet out of frame
	for(int i=0; i<enemies.size();i++) {
		enemies.get(i).deletBullet(w, h);
	}
	if(players.isEmpty()==false) {
	players.get(0).deletBullet(w, h);}
}
public void drawAll(Graphics g) {	
	int bx,by,px=0,py=0,ex=0,ey=0;
	if(players.isEmpty()==false) {
	px=players.get(0).getPosition().x;//get player position
	py=players.get(0).getPosition().y;
	players.get(0).draw(g);//draw player
	
	if(Input.getKey(KeyEvent.VK_Z))//if player press z then shoot
	{
		if(power<10) {
			
		players.get(0).shoot();
		}
		if(power>=10&&power<20) {
			
			players.get(0).setRate(4);
			players.get(0).shoot2();	
		}
		if(power>=20) {
			players.get(0).setRate(1);	
			players.get(0).shoot2();
		}
		if(power<0){
			players.get(0).shoot();
		}
	}
	if(Input.getKey(KeyEvent.VK_X)) {//remove all bullets
		if(power>5) {
			power-=5;
			for(int i=enemies.size()-1;i>-1;i--) {//search for every enemy
			enemies.get(i).bullets.clear();
			}
			updateView();
		}
		
	}
	
	for(int i=players.get(0).bullets.size()-1;i>=0;i--) {//player shoot
	players.get(0).bullets.get(i).moveBullet();
	g.setColor(Color.black);
	players.get(0).bullets.get(i).draw(g);
	bx=players.get(0).bullets.get(i).getPosition().x;
	by=players.get(0).bullets.get(i).getPosition().y;//get player bullets position
	for (int z = enemies.size() - 1; z > -1; z--) {//kill enemy
		ex=enemies.get(z).getPosition().x;//get enemies position
		ey=enemies.get(z).getPosition().y;
		if ((bx-ex)*(bx-ex)+(by-ey)*(by-ey)<1000) {
			enemies.get(z).beenHit(players.get(0).bullets.get(0).getAttack());
			players.get(0).bullets.remove(i);//remove bullet after hit
			if(enemies.get(z).getLife()<=0) {//enemies die
				dropGear(enemies.get(z).getPosition());//drop gear after die
				enemies.remove(z);
			System.out.println(++enemyKilled);
			updateView();
			//System.out.println(enemies.size()+"||"+players.size());//test
			
		}
	}
	//
	
	}
	}

	for(int i=0; i<enemies.size();i++) {
		g.setColor(Color.red);
		enemies.get(i).move(0,1);//move enemies
		enemies.get(i).draw(g);//draw all the enemies
		enemies.get(i).shoot(enemies.get(i).aim(players.get(0).position));//aim player and shoot
		for(int z=enemies.get(i).bullets.size()-1;z>=0;z--) {
			g.setColor(Color.black);
			enemies.get(i).bullets.get(z).moveBullet();//move bullets first
			enemies.get(i).bullets.get(z).draw(g);//draw all bullets
			//check if hit player
			bx=enemies.get(i).bullets.get(z).getPosition().x;
			by=enemies.get(i).bullets.get(z).getPosition().y;
			if((bx-px)*(bx-px)+(by-py)*(by-py)<500&&(bx-px)*(bx-px)+(by-py)*(by-py)>100) {
				if(enemies.get(i).bullets.get(z).getClose()==false) {//check if increased score
				close++;
				enemies.get(i).bullets.get(z).setClose(true);
				}
			}
			if((bx-px)*(bx-px)+(by-py)*(by-py)<100) {
				players.get(0).beenHitByE(enemies.get(i).bullets.get(z).getAttack());
				close--;
				//System.out.println(players.get(0).getLife());
				updateView();//update information
				enemies.get(i).bullets.remove(z);//remove bullet after hit
			}
		}
	}
	}
	//draw gears
	g.setColor(Color.pink);
	for(int ge=gears.size()-1;ge>-1;ge--) {
		gears.get(ge).moveBullet();
		//gears.get(ge).draw(g);
		
		//if player grab gear
		int gx,gy;
		gx=gears.get(ge).getPosition().x;
		gy=gears.get(ge).getPosition().y;
		g.drawImage(MlsGear, gx-25,gy-25,20,39, null);//draw MlsGear
		if ((px-gx)*(px-gx)+(py-gy)*(py-gy)<3000) {
			gears.remove(ge);//delete after grab
			power++;
			updateView();
		}
	}
	
}
public void deletEnemie(int w,int h) {//delete enemy and gear
	for (int i = enemies.size() - 1; i >= 0; i--) {
		if (enemies.get(i).getPosition().getX()>w||enemies.get(i).getPosition().getY()>h) {
			enemies.remove(i);
		}
	}
	for (int i = gears.size() - 1; i >= 0; i--) {
		if (gears.get(i).getPosition().getX()>w||gears.get(i).getPosition().getY()>h) {
			gears.remove(i);
		}
	}
}
public void dropGear(Point p) {
	Point temp=new Point();
	temp=p;
	gears.add(new Bullet(temp,0,3,90.0,true,true));//(Point position,int attackPower,int speed,double degree,boolean isGood,boolean close )
	
}
public void movePlayer(int w,int h) {
	/*int px=players.get(0).getPosition().x,py=players.get(0).getPosition().y;
	//move right and left
	Point temp=new Point();
	temp=players.get(0).getPosition();
	if(px<w-1&&right) {
		temp.setLocation(px+2,py);
		players.get(0).setPosition(temp);
		if(px>w-10) {
			right=false;
		}
	}else if(px>1&&right==false){
		temp.setLocation(px-2,py);
		players.get(0).setPosition(temp);
		if(px<10) {
			right=true;
		}
	}*/
	int px=players.get(0).getPosition().x,py=players.get(0).getPosition().y;
	int speed=6;
	Point temp=new Point();
	temp=players.get(0).getPosition();
	if(Input.getKey(KeyEvent.VK_LEFT)&&px>0)//left
		{px=px-speed;}
	if(Input.getKey(KeyEvent.VK_RIGHT)&&px<w)//right
		{px=px+speed;}
	if(Input.getKey(KeyEvent.VK_UP)&&py>speed)//up
		{py=py-speed;}
	if(Input.getKey(KeyEvent.VK_DOWN)&&py<h-speed)//down
		{py=py+speed;}
	temp.setLocation(px,py);
	players.get(0).setPosition(temp);
	
}
public void updateView() {
	InforPanel.update();
}
public int getScore() {
	score=enemyKilled*30+close*10;
	return score;
}
public int getEnemyKill() {
	return enemyKilled;
}
public int getLife() {
	return players.get(0).getLife();
}
public int getPower() {
	return power;
}
}
