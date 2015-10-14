
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	public static int framerate = 60;
	
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		int resolutionX = 600;		
		int resolutionY = 600;
		
		try {
			Game game = new Game("gilles");
			AppGameContainer app = new AppGameContainer( game );
			
			app.setDisplayMode(resolutionX, resolutionY,false);
			app.setTargetFrameRate(Main.framerate);
			app.setVSync(true);
			//app.setMaximumLogicUpdateInterval(16);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
