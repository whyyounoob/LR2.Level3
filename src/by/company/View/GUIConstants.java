package by.company.View;

import javax.swing.*;

public class GUIConstants {
    public static final String[] PIER_TABLE_HEADERS = {"Pier name", "Pier speed", "Pier status",
            "Ship name", "Cargo", "Weight", "Progress"};
    public static final Class[] PIER_COLUMN_CLASSES = {String.class, Integer.class, String.class,
            String.class, String.class, Long.class, JProgressBar.class};
    public static final String[] SHIP_TABLE_HEADERS = {"Ship name", "Target", "Cargo", "Weight"};
    public static final Class[] SHIP_COLUMN_CLASSES = {String.class, String.class, String.class, Integer.class};

}
