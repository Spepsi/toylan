import org.newdawn.slick.Color;
import org.newdawn.slick.Input;


public class Boule {
	
	public float x, y;
	public Color color;
	public float velocity = 1f;
	
	public Boule(float x, float y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	
	public void move(Input i){
		if(i.isKeyDown(Input.KEY_UP))
			this.y -= velocity;
		if(i.isKeyDown(Input.KEY_DOWN))
			this.y += velocity;
		if(i.isKeyDown(Input.KEY_LEFT))
			this.x -= velocity;
		if(i.isKeyDown(Input.KEY_RIGHT))
			this.x += velocity;
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
