import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
public MyFrame() {
	Model m=new Model(this.getWidth(),this.getHeight());
	View v=new View(m);
	InforPanel i=new InforPanel(m);
	this.add(i);
	this.add(v);
	i.setBounds(750, 1, 250, 1000);
	i.setBackground(Color.GRAY);
	v.setBounds(1, 1,750 , 1000);
	this.setLayout(null);
	this.setSize(1000,1040);
	this.setTitle("重要的标题偷走了魔理沙");
	this.addKeyListener(new Input());
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
}
}
