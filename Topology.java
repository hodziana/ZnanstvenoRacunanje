import java.io.*;
import java.util.*;

public class Topology {
    public static void readNeighbors(int myId, int N, List<Integer> neighbors) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("topology" + myId));
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                int neighbor = Integer.parseInt(st.nextToken());
                neighbors.add(neighbor);
            }
        } catch (IOException e) {
            for (int i = 0; i < N; i++) {
                if (i != myId) neighbors.add(i);
            }
        }
    }
}
