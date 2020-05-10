import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    private static final int SERVER_PORT_NUMBER = 61118;

    public static void main(String[] args){

        System.out.println("Please input the server address: ");
        String serverAddress = new Scanner(System.in).nextLine();

        DatagramSocket clientsocket = null;
        DatagramPacket clientSendpacket;
        DatagramPacket clientReceivepacket;
        BufferedReader bufferedReader;
        InetAddress inetAddress;
        byte [] clientsendbyte = new byte[1024];
        byte [] clientreceivebyte = new byte[1024];
        String sendMessage;
        String receiveMessage;

        try{
            clientsocket = new DatagramSocket();


            //Send message
            do{
            System.out.println("Please input your message, and EXIT to quit: ");
            inetAddress = InetAddress.getByName(serverAddress);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            sendMessage = bufferedReader.readLine();
            clientsendbyte = sendMessage.getBytes();
            clientSendpacket = new DatagramPacket(clientsendbyte,clientsendbyte.length,inetAddress,SERVER_PORT_NUMBER);
            clientsocket.send(clientSendpacket);}
            while(!sendMessage.contains("EXIT"));

            //Receive message
            do{
            clientReceivepacket = new DatagramPacket(clientreceivebyte,clientreceivebyte.length);
            clientsocket.receive(clientReceivepacket);
            receiveMessage = new String(clientReceivepacket.getData()).trim();
            System.out.println("Receive uppercase message : "+ receiveMessage );
            System.out.println("Receive from: "+ clientReceivepacket.getAddress().toString());
            System.out.println("The port is :"+ clientReceivepacket.getPort());}
            while(!receiveMessage.contains("EXIT"));

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                clientsocket.close();
            }catch (Exception a){
                a.printStackTrace();
            }
        }

    }
}
