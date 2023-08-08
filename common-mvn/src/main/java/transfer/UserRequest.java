package transfer;

import java.io.Serializable;

public class UserRequest implements Serializable{
	private int operation;
    private Object parametar;

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
}
