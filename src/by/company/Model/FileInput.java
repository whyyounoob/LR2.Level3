package by.company.Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileInput extends Thread {

    public void run() {
        while (true) {
            try {
                inputFile();
                System.out.println("Zapiska");
                Thread.sleep(Constants.FILE_INPUT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void inputFile() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("test.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(Town.getInstance().getPortList());
            oos.flush();
            oos.close();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
