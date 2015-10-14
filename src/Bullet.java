import org.newdawn.slick.Color;


public class Bullet extends Boule {
	int dir;
	int id;
	
	public Bullet(float x, float y,int dir,Game g,int team) {
		super(x, y, Color.green,g,team);
		this.velocity = 2f;
		this.dir = dir;
		this.id = g.idBullet;
		g.idBullet++;
		// TODO Auto-generated constructor stub
	}
	public Bullet(String s, int id) {
		this.id = id;
		this.velocity = 2f;
		this.update(s);
		// TODO Auto-generated constructor stub
	}
	
	
	public void move(){
		
		switch(dir){
		case(2):this.y+=velocity;break;
		case(8):this.y-=velocity;break;
		case(4):this.x-=velocity;break;
		case(6):this.x+=velocity;break;
		
			
		}
		
	}
	
	public String toString(){
		String s = "";
		return id+"-"+team+" "+dir+" "+x+" "+y;
	}
	
	public void update(String s){
		int i=0,j=0,k=0;
		while(s.charAt(i)!=' ')
			i++;
		j=i+1;
		while(s.charAt(j)!=' ')
			j++;
		k=j+1;
		while(s.charAt(k)!=' ')
			k++;
		this.team = Integer.parseInt(s.substring(0, i));
		this.dir = Integer.parseInt(s.substring(i+1, j));
		this.x = Float.parseFloat(s.substring(j+1, k));
		this.y = Float.parseFloat(s.substring(k+1, s.length()));
	}
	
	
}
