package model.json_data;

public class ErrorRes {
    public final String errorType;
    public final String message;

    public ErrorRes(String errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorRes{" +
                "errorType='" + errorType + "'\n" +
                ", message='" + message + '\'' +
                '}';
    }

    public String getError() {
        return message;
    }
}
