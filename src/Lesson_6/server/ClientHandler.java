package Lesson_6.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Main server;
    private Thread clientThread;

    public ClientHandler(Socket socket, Main server, InteractionClient interactionClient) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            clientThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            final String str = in.readUTF();
                            if (str.equals("/end")) {
                                break;
                            }
                            server.broadCastMsg(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        interactionClient.closeSocket(ClientHandler.this);
                    }
                }
            });

            clientThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
