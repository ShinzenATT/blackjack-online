package json_data;

/**
 * Class that handles commands to the server.
 * @author Alen
 * @version 2022-03-07
 */
public class RecievingCmd {
    public String command;
    public int value;

    /**
     * Creates a command object with given command and value.
     * @param c The command.
     * @param v The value.
     */
    public RecievingCmd(String c, int v){
        command = c;
        value = v;
    }

    /**
     * Converts the data from a command object to a single string.
     */
    @Override
    public String toString() {
        return "RecievingCmd{" +
                "command='" + command + '\'' +
                ", value=" + value +
                '}';
    }
}
