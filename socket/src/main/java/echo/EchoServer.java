package echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 李恒名 on 2017/6/8.
 *
 * 一个Socket入门小例子，将客户端的输入返回给客户端，支持多客户端连接。
 */
public class EchoServer {
    private static final int SERVER_PORT = 7777;

    public static void main(String[] args) {
        // use java 7 try-with-resource auto close resource.
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started listening port：" + SERVER_PORT);
            //持续接收客户端连接请求
           while (true){
               Socket client = server.accept();
               System.out.println("Accept connect client：" + client);
               //为每一个Client Socket单独创建一个线程去处理
               new Thread(new ClientHandler(client)).start();
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {

        private final Socket client;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try (DataInputStream in = new DataInputStream(client.getInputStream());
                 DataOutputStream out = new DataOutputStream(client.getOutputStream())) {
                //保持连接，持续监听客户端输入。
                while (true) {
                    out.writeUTF("Server echo：" + in.readUTF());
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
