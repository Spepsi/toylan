import org.newdawn.slick.Color;
import org.newdawn.slick.Input;


public class Boule {
	int team;
	Game g;
	public float x, y;
	public Color color;
	public float velocity = 1f;

	public Boule(float x, float y, Color color,Game g,int team) {
		this.g = g;
		this.team = team;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public Boule(){}

	public void move(Input i){
		if(i.isKeyDown(Input.KEY_UP))
			this.y -= velocity;
		if(i.isKeyDown(Input.KEY_DOWN))
			this.y += velocity;
		if(i.isKeyDown(Input.KEY_LEFT))
			this.x -= velocity;
		if(i.isKeyDown(Input.KEY_RIGHT))
			this.x += velocity;

		if(i.isKeyPressed(Input.KEY_Z))
			g.bullets.addElement(new Bullet(x,y,8,g,team));
		if(i.isKeyPressed(Input.KEY_S))
			g.bullets.addElement(new Bullet(x,y,2,g,team));
		if(i.isKeyPressed(Input.KEY_Q))
			g.bullets.addElement(new Bullet(x,y,4,g,team));
		if(i.isKeyPressed(Input.KEY_D))
			g.bullets.addElement(new Bullet(x,y,6,g,team));
	}
	public void move(String s){
		if(s.charAt(3)=='1')
			this.y -= velocity;
		if(s.charAt(0)=='1')
			this.y += velocity;
		if(s.charAt(1)=='1')
			this.x -= velocity;
		if(s.charAt(2)=='1')
			this.x += velocity;
		if(s.charAt(4)=='1')
			g.bullets.addElement(new Bullet(x,y,2,g,team));
		if(s.charAt(5)=='1')
			g.bullets.addElement(new Bullet(x,y,4,g,team));
		if(s.charAt(6)=='1')
			g.bullets.addElement(new Bullet(x,y,6,g,team));
		if(s.charAt(7)=='1')
			g.bullets.addElement(new Bullet(x,y,8,g,team));
	}

	public String toString(){
		return x+" "+y;
	}

	public void update(String s){
		int i=0;
		while(s.charAt(i)!=' ')
			i++;
		x = Float.parseFloat(s.substring(0, i));
		y = Float.parseFloat(s.substring(i+1, s.length()));
	}

}
