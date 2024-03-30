import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Input implements KeyListener{
private static HashMap<Integer,Boolean> keys;

public Input() {
	keys = new HashMap<Integer, Boolean>(300);
	for(int i = 0; i < 300; i++)
	{
		keys.put(i, false);
	}
}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys.put(e.getKeyCode(), true);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.put(e.getKeyCode(), false);
		
	}
	public static Boolean getKey(int keyCode) {
		return keys.get(keyCode);
	}
	

}
