import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by 李恒名 on 2017/6/8.
 */
public class ReadFile {

    public static void read(File file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readByNIO(File file) {
        try (FileChannel channel = new FileInputStream(file).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
            channel.read(buffer);
            System.out.println(new String(buffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readByNIO2(File file) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File("README.md");

        read(file);

        readByNIO(file);

        readByNIO2(file);


    }
}
