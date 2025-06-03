import java.io.Serializable;

public class Message implements Serializable {
    public int source;
    public int dest;
    public String tag;
    public Object data;

    public Message(int source, int dest, String tag, Object data) {
        this.source = source;
        this.dest = dest;
        this.tag = tag;
        this.data = data;
    }
}
