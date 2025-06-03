import java.io.*;
import java.net.*;

public class Linker {
    private int myId;
    private int N;
    private Socket[] sockets;
    private ObjectInputStream[] input;
    private ObjectOutputStream[] output;
    private ServerSocket server;

    public Linker(int myId, int N) throws Exception {
        this.myId = myId;
        this.N = N;
        sockets = new Socket[N];
        input = new ObjectInputStream[N];
        output = new ObjectOutputStream[N];

        if (myId == 0) {
            server = new ServerSocket(5000);
            System.out.println("Master waiting for workers...");
            for (int i = 1; i < N; i++) {
                Socket s = server.accept();
                input[i] = new ObjectInputStream(s.getInputStream());
                output[i] = new ObjectOutputStream(s.getOutputStream());
                sockets[i] = s;
                System.out.println("Master accepted connection from worker " + i);
            }
        } else {
            sockets[0] = new Socket("localhost", 5000);
            output[0] = new ObjectOutputStream(sockets[0].getOutputStream());
            input[0] = new ObjectInputStream(sockets[0].getInputStream());
            System.out.println("Worker " + myId + " connected to master.");
        }

        System.out.println("Process " + myId + " connected.");
    }

    public void sendMessage(int destId, String tag, Object msg) throws Exception {
        output[destId].writeObject(new Message(myId, destId, tag, msg));
        output[destId].flush();
    }

    public Object receiveMessage(int fromId, String tag) throws Exception {
        while (true) {
            Message m = (Message) input[fromId].readObject();
            if (m.tag.equals(tag)) {
                return m.data;
            }
        }
    }
}
