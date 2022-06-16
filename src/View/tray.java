package View;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class tray {

    public static void main(String[] args) {
                createAndShowGUI();
    }

    private static void createAndShowGUI() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon
                = new TrayIcon(createImage("images/icon.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        try {
            tray.add(trayIcon);
            tray.remove(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = tray.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
