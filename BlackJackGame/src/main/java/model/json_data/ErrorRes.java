package model.json_data;

/**
 * Class used to handle custom written error messages.
 * @author Alen
 * @version 2022-03-07
 */
public class ErrorRes {
    public final String errorType;
    public final String message;

    /**
     * Creates a new error message with
     * specified error type and error message.
     * 
     * @param errorType The type of error.
     * @param message The custom error message that is more specific.
     */
    public ErrorRes(String errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    /**
     * Converts an error message and returns it in a single string.
     */
    @Override
    public String toString() {
        return "ErrorRes{" +
                "errorType='" + errorType + "'\n" +
                ", message='" + message + '\'' +
                '}';
    }

    /**
     * Gets the specified error message of the error.
     * @return The error message.
     */
    public String getError() {
        return message;
    }
}
