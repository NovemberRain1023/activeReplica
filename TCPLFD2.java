import java.io.InputStream;
import java.net.Socket;
import java.util.Timer;

public class TCPLFD2 {
    
    public static int count = 0;
    public static Socket client;
    public static Socket gfd;

    // Initialization
    public TCPLFD2() {
        Timer time = new Timer(); // Instantiate Timer Object
        try {
            client = new Socket(TCPService2.SERVICE_IP,TCPService2.SERVICE_PORT);
            gfd = new Socket(GFD.SERVICE_IP,GFD.SERVICE_PORT);
            ScheduledTask2 st = new ScheduledTask2(client, TCPService2.SERVICE_IP,TCPService2.SERVICE_PORT, gfd); // Instantiate SheduledTask class
            time.schedule(st, 0, 5000); // Create Repetitively task for every 1 secs
        } catch (Exception e) {
            //System.out.println("catch th"e);
        }

    }
    

    public static void main(String[] args) {
        TCPLFD2 lfd = new TCPLFD2();

        while (true) {
            StringBuilder receiveMsg = new StringBuilder();
            try {
               InputStream in = TCPLFD2.client.getInputStream();
               for (int c = in.read(); c != TCPService.END_CHAR; c = in.read()) {
                if(c==-1)
                    break;
                receiveMsg.append((char)c);
                }
                if (receiveMsg != null && receiveMsg.length() != 0) {
                    System.out.println(receiveMsg.toString() + "length with: " + receiveMsg.length());
                    TCPLFD2.count = 0;
                }

            } catch (Exception e) {
                // System.out.println(e.getMessage());
            }
            
		}
    }
}