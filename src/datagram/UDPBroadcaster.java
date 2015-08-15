package datagram;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class UDPBroadcaster implements Runnable {
	DatagramSocket bSocket;
	DatagramPacket bPacket;
	InetAddress serverAddress;
	byte[] sendData;

	public UDPBroadcaster(byte[] dataToSend) {
		super();
		try {
			sendData = dataToSend;
			bSocket = new DatagramSocket();// Socket is bound to this port
			bSocket.setBroadcast(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try { 
			bPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888);
			bSocket.send(bPacket);
			System.out.println(getClass().getName() + ">>>sent packet to 255.255.255.255(DEFAULT)");
			bSocket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			bSocket.close();
		}

	}

}
