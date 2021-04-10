package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

public class serverThread extends Thread{
    @FXML
    TextArea textArea;

    protected Socket socket       = null;
    protected PrintWriter out     = null;
    protected BufferedReader in   = null;
    protected Vector messages     = null;


    public serverThread(Socket socket, Vector messages) {
        super();
        this.socket = socket;
        this.messages = messages;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOEXception while opening a read/write connection");
        }
    }

    public void run() {
        /*
        boolean ok = false;
        while (ok) {
            try {
                processCommand();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         */
    }

    protected boolean processCommand() throws IOException {
        String message1 = null;
        message1 = in.readLine();

        if(in.readLine() != null){
            return false;
        }

        StringTokenizer st = new StringTokenizer(message1);
        String command = st.nextToken();
        String args = null;
        if (st.hasMoreTokens()) {
            args = message1.substring(command.length()+1, message1.length());
        }

        textArea.setText(command + ": " + args);
        return false;
    }

}
