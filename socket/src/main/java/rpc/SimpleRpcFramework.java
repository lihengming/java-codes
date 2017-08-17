package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 李恒名 on 2017/8/17.
 */
public class SimpleRpcFramework {


    public static void export(Object service, int port) {
        System.out.println("Export service " + service.getClass().getSimpleName() + " success on port " + port);
        try {
            try (ServerSocket server = new ServerSocket(port)) {
                while (!Thread.currentThread().isInterrupted()) {
                    Socket socket = server.accept();
                    new Thread(() -> {
                        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                            Method method = service.getClass().getMethod(in.readUTF(), (Class<?>[]) in.readObject());
                            Object result = method.invoke(service, (Object[]) in.readObject());
                            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                                out.writeObject(result);
                            }
                            System.out.println("Invoke method [" + method.getName() + "()] success, form " + socket.getRemoteSocketAddress());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T use(Class<T> interfaceClass, String host, int port) {
        System.out.println(String.format("Use remote service [%s] from [%s:%s]", interfaceClass.getSimpleName(), host, port));
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, (proxy, method, args) -> {
            try (Socket socket = new Socket(host, port)) {
                try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                    out.writeUTF(method.getName());
                    out.writeObject(method.getParameterTypes());
                    out.writeObject(args);
                    try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                        return in.readObject();
                    }
                }
            }
        });
    }
}
