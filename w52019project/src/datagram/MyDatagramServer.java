package datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class MyDatagramServer {
    private byte[] buf = new byte[256];

    public static void main(String[] args) {
        new MyDatagramServer();
    }

    public MyDatagramServer() {
        try {
            DatagramSocket socket = new DatagramSocket(8000);
            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length);

            while (true) {
                Arrays.fill(buf, (byte)0);
                socket.receive(receivePacket);
//        System.out.println(receivePacket.getAddress().getHostName() +
//          " and port number is " + receivePacket.getPort() + '\n');
//        System.out.println("Radius received from client is " +
//          new String(buf).trim() +  '\n');

                // Compute area
                double radius = Double.parseDouble(new String(buf).trim());
                double area = radius * radius * Math.PI;
                System.out.println("Area is " + area + '\n');

                // Send area to the client in a packet
                sendPacket.setAddress(receivePacket.getAddress());
                sendPacket.setPort(receivePacket.getPort());
                sendPacket.setData(new Double(area).toString().getBytes());
                socket.send(sendPacket);
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
