package by.company.Model;

import by.company.Controller.Town;
import by.company.View.MainWindow;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * It is start class for program.
 *
 * @author Maxim Borodin
 */

public final class Main {

    /**
     * Default constructor.
     */

    private Main() {

    }

    /**
     * From this method start our program.
     *
     * @param args - command line
     */

    public static void main(final String[] args) throws IOException,
            ClassNotFoundException {
        ArrayList<Port> arrayList;
        ObjectInputStream ois = null;
        ois = new ObjectInputStream(new FileInputStream("test.ser"));
        arrayList = (ArrayList<Port>) ois.readObject();
        Town.getInstance().setPortList(arrayList);

        new FileInput().start();
        MainWindow mainWindow = new MainWindow();
    }
}
