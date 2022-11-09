import java.io.InputStream;
import java.net.Socket;
import java.util.Timer;

public class TCPLFD3 {
    
    public static int count = 0;
    public static Socket client;
    public static Socket gfd;

    // Initialization
    public TCPLFD3() {
        Timer time = new Timer(); // Instantiate Timer Object
        try {
            client = new Socket(TCPService3.SERVICE_IP,TCPService3.SERVICE_PORT);
            gfd = new Socket(GFD.SERVICE_IP,GFD.SERVICE_PORT);
            ScheduledTask3 st = new ScheduledTask3(client, TCPService3.SERVICE_IP,TCPService3.SERVICE_PORT, gfd); // Instantiate SheduledTask class
            time.schedule(st, 0, 5000); // Create Repetitively task for every 1 secs
        } catch (Exception e) {
            //System.out.println("catch th"e);
        }

    }
    

    public static void main(String[] args) {
        TCPLFD3 lfd = new TCPLFD3();

        while (true) {
            StringBuilder receiveMsg = new StringBuilder();
            try {
               InputStream in = TCPLFD3.client.getInputStream();
               for (int c = in.read(); c != TCPService.END_CHAR; c = in.read()) {
                if(c==-1)
                    break;
                receiveMsg.append((char)c);
                }
                if (receiveMsg != null && receiveMsg.length() != 0) {
                    System.out.println(receiveMsg.toString() + "length with: " + receiveMsg.length());
                    TCPLFD3.count = 0;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
		}
    }
}