import java.io.Serializable;

public class CustomObject implements Serializable {
    private String name;
    private int value;

    public CustomObject(String name, String string) {
        this.name = name;
        this.value = string;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CustomObject{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public String getMessage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }
}