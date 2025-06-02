import java.io.*;
import java.net.*;

public class Worker {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Worker connected to master.");

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
            Task task = (Task) in.readObject();
            Object result = task.execute();
            out.writeObject(result);
            out.flush();
        }
    }
}
