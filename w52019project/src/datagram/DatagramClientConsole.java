package datagram;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.Arrays;

public class DatagramClientConsole {

        private static DatagramSocket socket;
        private static DatagramPacket sendPacket, receivePacket;
        private static InetAddress serverAddress;

        // The byte array for sending and receiving datagram packets
        private static byte[] buf = new byte[256];

        private static void createDatagramSocket(){
            try {
                // get a datagram socket
                socket = new DatagramSocket();
                serverAddress = InetAddress.getByName("localhost");
                sendPacket = new DatagramPacket(buf, buf.length, serverAddress, 8000);
                receivePacket = new DatagramPacket(buf, buf.length);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        public static void main(String[] args){
            createDatagramSocket();
            Double d = 78.0;
            System.out.println(d+" will be sent now");
            try {
                // Initialize buffer for each iteration
                Arrays.fill(buf, (byte)0);
                // send radius to the server in a packet
                sendPacket.setData(d.toString().getBytes());
                socket.send(sendPacket);
                // receive area from the server in a packet
                socket.receive(receivePacket);
                // Display to the text area

                System.out.println("Radius is " + d.toString() + "\n");
                System.out.println("Area received from the server is "+  Double.parseDouble(new String(buf).trim()) + '\n');
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


