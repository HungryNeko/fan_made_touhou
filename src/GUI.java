import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {
public static void main(String[] args) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {

		@Override
		public void run() {
			new MyFrame();
		}

	});
}
}
