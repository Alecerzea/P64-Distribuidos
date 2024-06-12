import java.io.Serializable;

public class CustomObject implements Serializable {
    private String name;
    private String message;

    public CustomObject(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
