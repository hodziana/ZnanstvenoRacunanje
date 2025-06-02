import java.io.*;
import java.net.*;
import java.util.*;

public class Master {

    private List<Socket> workers = new ArrayList<>();
    private int numberOfWorkers;

    public Master(int numberOfWorkers) {
        this.numberOfWorkers = numberOfWorkers;
    }

    public void startServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Master started. Waiting for workers...");

        while (workers.size() < numberOfWorkers) {
            Socket workerSocket = serverSocket.accept();
            System.out.println("Worker connected: " + workerSocket);
            workers.add(workerSocket);
        }
    }

    public void dispatchTasks(List<Task> tasks) throws IOException, ClassNotFoundException {
        for (int i = 0; i < tasks.size(); i++) {
            Socket worker = workers.get(i % numberOfWorkers);
            sendTask(worker, tasks.get(i));
        }

        for (int i = 0; i < tasks.size(); i++) {
            Socket worker = workers.get(i % numberOfWorkers);
            Object result = receiveResult(worker);
            System.out.println("Result from worker " + (i % numberOfWorkers) + ": " + result);
        }
    }

    private void sendTask(Socket socket, Task task) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(task);
        out.flush();
    }

    private Object receiveResult(Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return in.readObject();
    }

    public static void main(String[] args) throws Exception {
        Master master = new Master(3); // npr. 3 radnika
        master.startServer(5000);

        List<Task> tasks = new ArrayList<>();
        // Privremeni primjer zadatka:
        for (int i = 0; i < 3; i++) {
            final int taskId = i; 
            tasks.add(() -> "Hello from task " + taskId);
        }

        master.dispatchTasks(tasks);
    }
}
