import java.util.Vector;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame{


	public Vector<Boule> boules = new Vector<Boule>();
	public int currentPlayer = 1;
	public Vector<String> inBox = new Vector<String>();
	public Vector<String> outBox = new Vector<String>();
	
	public String lan;
	
	//concerning allhost
	public boolean host=false;

	public Game(String title, String lan) {
		super(title);
		this.lan = lan;
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
		Sender sender = new Sender("192.168.1.27",3024,outBox);
		Receiver receiver = new Receiver(inBox,3024);
		sender.start();
		receiver.start();

	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		switch(lan){
		case "basic": updateBasicLan(gc);
		case "allhost": updateAllHost(gc);
		default:
		}
		
	}

	private void updateAllHost(GameContainer gc) {
		if(host){
			if(this.inBox.size()>0)
				this.boules.get(3-currentPlayer).move(inBox.remove(0));
			inBox.clear();
			this.boules.get(currentPlayer).move(gc.getInput());
			outBox.add(this.toString());
		} else {
			this.outBox.addElement(inputToString(gc.getInput()));
			if(this.inBox.size()>0)
				this.update(inBox.remove(0));
		}
		
	}

	private void updateBasicLan(GameContainer gc) {
		this.boules.get(currentPlayer).move(gc.getInput());
		this.outBox.addElement(boules.get(currentPlayer).toString());
		if(this.inBox.size()>0)
			this.boules.get(3-currentPlayer).update(this.inBox.remove(0));
		
		this.inBox.clear();
	}
	
	public static String inputToString(Input i){
		return ""+(i.isKeyDown(Input.KEY_DOWN)?1:0)+(i.isKeyDown(Input.KEY_LEFT)?1:0)+(i.isKeyDown(Input.KEY_RIGHT)?1:0)+(i.isKeyDown(Input.KEY_UP)?1:0);
	}
	public String toString(){
		String s = "";
		for(Boule b: this.boules)
			s+=b.toString()+"|";
		return s;
	}
	public void update(String s){
		System.out.println(s);
		String s1 = s;
		int i = 0, j = 0;
		while(j<3){
			i = 0;
			while(s1.charAt(i)!='|')
				i++;
			System.out.println(s1.substring(0, i));
			this.boules.get(j).update(s1.substring(0, i));
			s1 = s1.substring(i+1, s1.length());
			j++;
		}
	}

}
