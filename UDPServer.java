
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class UDPServer {

    private static final int SERVER_PORT_NUMBER = 61118;

    public static void main(String[] args) throws Exception{

        //Set up the UDP client arraylist to handle mult-thread
        System.out.println(InetAddress.getLocalHost().toString());
        DatagramSocket datagramSocket = null;
        ArrayList<ServerThread> serverHandleThread = new ArrayList<>();

        try {
            datagramSocket = new DatagramSocket(SERVER_PORT_NUMBER);

            while (true) {
                serverHandleThread.add(new ServerThread(datagramSocket));
                serverHandleThread.get(serverHandleThread.size()-1).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            datagramSocket.close();
        }
    }
}



