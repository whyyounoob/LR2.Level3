package by.company.Model;

import by.company.View.MainWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class Main {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ArrayList<Port> arrayList;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.ser"));
        arrayList = (ArrayList<Port>)ois.readObject();
        Town.getInstance().setPortList(arrayList);

        new FileInput().start();

        MainWindow mainWindow = new MainWindow();

    }
}
