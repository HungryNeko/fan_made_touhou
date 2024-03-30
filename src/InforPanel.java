import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InforPanel extends JPanel{
private static Model m;
private static JLabel score;
private static JLabel enemyKill;
private static JLabel life;
private static JLabel power;
private static String space="    ";
public InforPanel(Model m) {
	this.m=m; 
	//this.setLayout(FlowLayout.);
	score=new JLabel(space+"����: 0");
	life=new JLabel(space+"�����۳���:0%");
	enemyKill=new JLabel(space+"ɱ��: 0");
	power=new JLabel(space+"Power: 0");
	Font font = new Font("Serief",Font.BOLD, 25);
	score.setFont(font);
	life.setFont(font);
	enemyKill.setFont(font);
	power.setFont(font);
	this.add(score);
	this.add(life);
	this.add(enemyKill);
	this.add(power);
	this.setLayout(new GridLayout(5,1,20,0));
	score.setLocation(100, 1000);
	
	
}
public static void update() {
	score.setText(space+"����: "+m.getScore());
	life.setText(space+"�����۳���:"+(100-m.getLife())+"%");
	enemyKill.setText(space+"ɱ��: "+m.getEnemyKill());
	power.setText(space+"Power: "+m.getPower());
}
}
