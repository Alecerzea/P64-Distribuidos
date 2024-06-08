import java.io.Serializable;

public class CustomObject implements Serializable {
    private String name;
    private int value;

    public CustomObject(String name, int value) {
        this.name = name;
        this.value = value;
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
}