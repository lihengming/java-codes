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

    /**
     * 暴漏服务
     * @param serviceImpl 服务的实现
     * @param port 服务所处的端口号
     */
    public static void export(Object serviceImpl, int port) {
        try {
            try (ServerSocket server = new ServerSocket(port)) {
                while (!Thread.currentThread().isInterrupted()) {
                    Socket socket = server.accept();
                    new Thread(() -> {
                        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                            Method method = serviceImpl.getClass().getMethod(in.readUTF(), (Class<?>[]) in.readObject());
                            Object result = method.invoke(serviceImpl, (Object[]) in.readObject());
                            try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
                                out.writeObject(result);
                            }
                            System.out.println("Invoke method [" + method.getName() + "()] success, form " + socket.getRemoteSocketAddress());
                        } catch (Exception e) {
                            throw new RuntimeException("Export service fail .", e);
                        }
                    }).start();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Export service fail .", e);
        }
        System.out.println("Export service " + serviceImpl.getClass().getSimpleName() + " success on port " + port);
    }

    /**
     * 使用服务
     * @param serviceInterface 要使用的服务的接口
     * @param host 服务所处的主机名称
     * @param port 服务所处的端口号
     * @return 服务
     */
    public static <T> T use(Class<T> serviceInterface, String host, int port) {
        System.out.println(String.format("Use remote service [%s] from [%s:%s]", serviceInterface.getSimpleName(), host, port));
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, (proxy, method, args) -> {
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
