import java.util.Vector;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame{


	public Vector<Boule> boules = new Vector<Boule>();
	public int currentPlayer = 1;
	public Vector<String> inBox = new Vector<String>();
	public Vector<String> outBox = new Vector<String>();

	public Game(String title) {
		super(title);
		boules.add(new Boule((float)(Math.random()*600),(float)(Math.random()*600),Color.gray));
		boules.add(new Boule((float)(Math.random()*600),(float)(Math.random()*600),Color.cyan));
		boules.add(new Boule((float)(Math.random()*600),(float)(Math.random()*600),Color.pink));
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 600);
		for(Boule boule : boules){
			g.setColor(boule.color);
			g.fillOval(boule.x-20,boule.y-20,40,40);
		}
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		Sender sender = new Sender("127.0.0.1",3024,outBox);
		Receiver receiver = new Receiver(inBox,3024);
		sender.start();
		receiver.start();

	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		this.boules.get(currentPlayer).move(gc.getInput());
		this.outBox.addElement(boules.get(currentPlayer).toString());
		if(this.inBox.size()>0)
			this.boules.get(3-currentPlayer).update(this.inBox.remove(0));
	}

}
