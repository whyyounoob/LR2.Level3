package by.company.Model;

import by.company.Controller.Town;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class records the state of a port city in a file.
 *
 * @author Maxim Borodin
 */

public class FileInput extends Thread {

    /**
     * Default constructor.
     */

    public FileInput() {

    }

    /**
     * This method creates a writing thread.
     */

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

    /**
     * This method writes to a file.
     */

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
