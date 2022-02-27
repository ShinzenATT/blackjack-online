import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class GameConnection implements Runnable{
    private final Socket conn;
    private final BufferedReader input;
    private final PrintWriter output;

    public GameConnection(Socket s) throws IOException {
        conn = s;
        input = new BufferedReader( new InputStreamReader(conn.getInputStream()));
        output = new PrintWriter(conn.getOutputStream(), true);
    }

    @Override
    public void run() {
        try (conn) {
            System.out.println("Opened connection at " + Thread.currentThread().getName());
            while (conn.isConnected()){
                System.out.println(ReadFile());
                output.println("Ping " + Thread.currentThread().getName());
            }
            System.out.println("Closed connection at " + Thread.currentThread().getName());
        } catch (IOException e){
            e.printStackTrace();
        }

        //System.out.println("Closed connection at " + Thread.currentThread().getName());
    }

    private String ReadFile() throws IOException {
        StringBuilder str = new StringBuilder();
        String line;
        int nests = 0;
        do {
            line = input.readLine();
            str.append(line).append('\n');
            if(line.contains("{")){
                nests++;
            } if(line.contains("}")){
                nests--;
            }
        }while (nests > 0);

        return str.toString();
    }
}