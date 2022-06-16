/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.GlobalKeyListener;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.swing.ImageIcon;

/**
 *
 * @author Taavet
 */
public class MainView {

    TrayIcon capsLockTrayIcon;
    TrayIcon numLockTrayIcon;
    Image imgCapsLockEnabled;
    Image imgCapsLockDisabled;
    Image imgNumLockEnabled;
    Image imgNumLockDisabled;

    public MainView() {
        createGUI();
        GlobalKeyListener globalKeyListener = new GlobalKeyListener(this);
        globalKeyListener.setUp();
    }

    private void createGUI() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        imgCapsLockEnabled = getImage("images/CapsLockEnabled.png");
        imgCapsLockDisabled = getImage("images/CapsLockDisabled.png");
        capsLockTrayIcon = new TrayIcon(imgCapsLockEnabled);
        capsLockTrayIcon.setImageAutoSize(true);

        imgNumLockEnabled = getImage("images/NumLockEnabled.png");
        imgNumLockDisabled = getImage("images/NumLockDisabled.png");
        numLockTrayIcon = new TrayIcon(imgNumLockEnabled);
        numLockTrayIcon.setImageAutoSize(true);
    }

    private Image getImage(String strPath) {
        return new ImageIcon(this.getClass().getResource(strPath)).getImage();
    }

    public void setCapsLockIconDisabled() {
        capsLockTrayIcon.setImage(imgCapsLockDisabled);
    }

    public void setCapsLockIconEnabled() {
        capsLockTrayIcon.setImage(imgCapsLockEnabled);
    }

    public void setNumLockIconDisabled() {
        numLockTrayIcon.setImage(imgNumLockDisabled);
    }

    public void setNumLockIconEnabled() {
        numLockTrayIcon.setImage(imgNumLockEnabled);
    }

    public void setVisible() {
        try {
            SystemTray.getSystemTray().add(capsLockTrayIcon);
            SystemTray.getSystemTray().add(numLockTrayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added. " + e);
        }
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.setVisible();
    }
}
