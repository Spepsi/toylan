

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

import org.newdawn.slick.GameContainer;


public class Sender extends Thread{

	InetAddress address;
	int port;
	Game g;
	Vector<String> depot;

	// DEBUGGING
	private boolean debug =false;
	int sent = 0;
	
	public Sender(String address, int port, Vector<String> depot){
		this.depot = depot;
		try {
			this.address = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			System.out.println("philippe!!!!!");
		}
		this.port = port;
	}

	public void run(){
		try {
			@SuppressWarnings("resource")
			DatagramSocket server = new DatagramSocket();
			byte[] message;
			DatagramPacket packet;
			if(debug)
				System.out.println("Cretion d'un sender - " + port);
			while(true){
				if(this.depot.size()>0){
					message = (depot.get(0).toString()).getBytes();
					packet = new DatagramPacket(message, message.length, this.address, this.port);
					packet.setData(message);
					server.send(packet);
					sent++;
					//System.out.println("sent :" + sent);
					if(debug)
						System.out.println("port : " + port + " message sent: " + this.depot.get(0));
					this.depot.remove(0);
				}
				try{
					Thread.sleep(1);
				} catch (InterruptedException e){
					if(debug)
						System.out.println("death of sender " + this);
					break;
				}
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
