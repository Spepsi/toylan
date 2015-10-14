

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Vector;



public class Receiver extends Thread{


	int port;
	public int received = 0;
	Vector<String> depot;

	public DatagramSocket server;
	byte[] message;
	DatagramPacket packet;


	// DEBUGGING
	private boolean debug = false;

	public Receiver(Vector<String> depot, int port){
		this.depot = depot;
		this.port = port;
	}

	@Override
	public void run(){
		try{
			this.server = new DatagramSocket(port);
			if(debug)
				System.out.println("Cretion d'un receiver - " + port);
			while(!server.isClosed()){
				message = new byte[16000];
				packet = new DatagramPacket(message, message.length);
				try{
					server.receive(packet);
				} catch(java.net.SocketException e){
					break;
				}
				String msg = new String(packet.getData());
				if(debug) System.out.println("port : " + port + " message received: " + msg);
				if(msg.length()>0){
					depot.addElement(msg);
				}
			}

		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
