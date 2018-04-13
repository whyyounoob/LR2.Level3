package by.company.View;

import javax.swing.*;

/**
 * This class contains constants for GUI.
 *
 * @author Maxim Baradzin
 */

public class GUIConstants {

    /**
     * Array of headers for main table.
     */
    public static final String[] PIER_TABLE_HEADERS = {"Pier name", "Pier speed", "Pier status",
            "Ship name", "Cargo", "Weight", "Progress"};

    /**
     * Type of each column in main table.
     */
    public static final Class[] PIER_COLUMN_CLASSES = {String.class, Integer.class, String.class,
            String.class, String.class, Long.class, JProgressBar.class};

    /**
     * Headers for table which show ship queue.
     */

    public static final String[] SHIP_TABLE_HEADERS = {"Ship name", "Target", "Cargo", "Weight"};

    /**
     * Type of each column in table which show ship queue.
     */

    public static final Class[] SHIP_COLUMN_CLASSES = {String.class, String.class, String.class, Integer.class};

}
