package model.json_data;

public class RecievingCmd {
    public String command;
    public int value;

    public RecievingCmd(String c, int v){
        command = c;
        value = v;
    }

    @Override
    public String toString() {
        return "RecievingCmd{" +
                "command='" + command + '\'' +
                ", value=" + value +
                '}';
    }
}
