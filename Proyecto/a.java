import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MathBean {
    private int num1;
    private int num2;
    private int result;

    // getters and setters for num1, num2 and result

    public void add() {
        result = num1 + num2;
    }

    public void subtract() {
        result = num1 - num2;
    }

    public void multiply() {
        result = num1 * num2;
    }

    public void divide() {
        if(num2 != 0) {
            result = num1 / num2;
        } else {
            // handle division by zero
        }
    }
}
