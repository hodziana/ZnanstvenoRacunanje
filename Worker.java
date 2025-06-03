public class Worker {
    public static void main(String[] args) throws Exception {
        int myId = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);

        Linker linker = new Linker(myId, N);
        System.out.println("Process " + myId + " connected.");

        while (true) {
            System.out.println("Worker " + myId + " waiting for task...");
            Object obj = linker.receiveMessage(0, "task");

            if (obj instanceof String s && s.equals("terminate")) {
                System.out.println("Worker " + myId + " shutting down.");
                break;
            }

            if (obj instanceof Task task) {
                System.out.println("Worker " + myId + " executing task...");
                Object result = task.execute();
                linker.sendMessage(0, "result", result);
                System.out.println("Worker " + myId + " sent result.");
            } else {
                System.out.println("Worker " + myId + " received unknown object: " + obj);
            }
        }
    }
}
