package transfer;

import java.io.Serializable;

public class ServerResponse implements Serializable{
	private Object data;
    private int succesfull; // 1 - success, else failed
    private Exception exception;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getSuccesfull() {
        return succesfull;
    }

    public void setSuccesfull(int succesfull) {
        this.succesfull = succesfull;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
