package Lesson_8.server;


import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Main {
    private Vector<ClientHandler> clients;

    public Main() throws SQLException {
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            AuthService.connect();

            // System.out.println(AuthService.getNickByLoginAndPass("login12", "pass1"));

            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(socket, this);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    // подписываем клиента на рассылку
    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    // отписываем клиента от рассылки сообщений
    public void unsubscribe(ClientHandler client){
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    public boolean isConnectedToServer(String nick) {
        for (ClientHandler client: clients) {
            if (client.getNick().toLowerCase().equals(nick.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void sendMsgToUsers(String userNickSend, String message, String... users) {
        final String msg = userNickSend + ": " + message;
        final Set<String> connectedUsers = new HashSet<>(users.length);
        ClientHandler myClient = null;


        for (ClientHandler client: clients) {
            if (client.getNick().toLowerCase().equals(userNickSend.toLowerCase())) {
                myClient = client;
                continue;
            }
            for (String user: users) {
                if (client.getNick().toLowerCase().equals(user.toLowerCase())) {
                    client.sendMsg(msg);
                    connectedUsers.add(user);
                    break;
                }
            }
        }

        if (!connectedUsers.isEmpty() && myClient != null) {
            final StringBuilder myMessage = new StringBuilder(userNickSend).append(" -> [");
            int i = 0;
            for (String user: connectedUsers) {
                final boolean isLast = (i + 1 == connectedUsers.size());

                myMessage.append(" ");
                myMessage.append(user);

                if (!isLast) {
                    myMessage.append(",");
                } else {
                    myMessage.append("]: ");
                    myMessage.append(message);
                }
                i++;
            }
            myClient.sendMsg(myMessage.toString());
        }
    }
}
