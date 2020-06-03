package god.help.me.repository;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class IdProvider {
    private static final String STORAGE = "id.dat";

    public static synchronized int createId() throws IOException {
        File file = new File(STORAGE);
        int id;
        if (file.isFile()) {
            try (Scanner scanner = new Scanner(file)) {
                String sid = scanner.nextLine();
                id = Integer.parseInt(sid) + 1;
            }
        } else {
            file.createNewFile();
            id = 1;
        }

        try (FileWriter fileWriter = new FileWriter(file);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.write(Integer.toString(id));
        }
        return id;
    }

}
