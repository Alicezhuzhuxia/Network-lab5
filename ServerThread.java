import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerThread extends Thread{
    DatagramSocket datagramSocket;
    DatagramPacket datagramSendPacket;
    DatagramPacket datagramReceivePacket;
    byte[] serverReceive = new byte[1024];
    byte[] serverSend = new byte[1024];

    public ServerThread(DatagramSocket datagramSocket){
        this.datagramSocket = datagramSocket;
    }

    public void run(){

        try {
            //Receive message from client
            datagramReceivePacket = new DatagramPacket(serverReceive, serverReceive.length);
            datagramSocket.receive(datagramReceivePacket);
            String receiveMessage = new String(datagramReceivePacket.getData()).trim();
            System.out.println("Server receive message: "+ receiveMessage);
            System.out.println("From: "+ datagramReceivePacket.getAddress());
            System.out.print("Port: "+ datagramReceivePacket.getPort());

            //Send message to client
            String sendMessage = receiveMessage.toUpperCase();
            serverSend = sendMessage.getBytes();
            datagramSendPacket = new DatagramPacket(serverSend,serverSend.length,datagramReceivePacket.getAddress(),datagramReceivePacket.getPort());
            datagramSocket.send(datagramSendPacket);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
