package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class client extends Frame {
    @FXML
    Button sendBtn;
    @FXML
    TextField message;
    @FXML
    TextField username;

    @FXML
    Button exitBtn;

    @FXML
    public void buttonPress(ActionEvent event){
        addNewMessage();
    }

    @FXML
    public void buttonPress2(ActionEvent event){
        try {
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter networkOut = null;
    private BufferedReader networkIn = null;

    public  static String SERVER_ADDRESS = "localhost";
    public  static int    SERVER_PORT = 3030;

    public client() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: "+SERVER_ADDRESS);
        } catch (IOException e) {
            System.err.println("IOEXception while connecting to server: "+SERVER_ADDRESS);
        }
        if (socket == null) {
            System.err.println("socket is null");
        }
        try {
            networkOut = new PrintWriter(socket.getOutputStream(), true);
            networkIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("IOEXception while opening a read/write connection");
        }

        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public void addNewMessage() {
        networkOut.println(username.getText() +": "+ message.getText());
        System.out.println(username.getText() +": "+ message.getText());
    }


    public static void main(String[] args) {
        client app = new client();
    }
}
