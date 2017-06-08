package echo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 李恒名 on 2017/6/8.
 */
public class Client {

    private static final String HOST = "localhost";
    private static final int SERVER_PORT = 7777;


    public static void main(String[] args) {
        // use java 7 try-with-resource auto close resource.
        try (Socket client = new Socket(HOST, SERVER_PORT);
             DataInputStream in = new DataInputStream(client.getInputStream());
             DataOutputStream out = new DataOutputStream(client.getOutputStream())) {

            //创建系统输入扫描器，监听键盘输入
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please input word：");
            while (true){
                //获取键盘输入
                String word = scanner.nextLine();
                //发送给Server
                out.writeUTF(word);
                //接收Server 回应
                System.out.println(in.readUTF());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
