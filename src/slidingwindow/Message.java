package slidingwindow;

public class Message {
    private String message;
    private int token;

    public Message(String message, int token) {
        this.message = message;
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public String getMessage(){
        return message;
    }
}
