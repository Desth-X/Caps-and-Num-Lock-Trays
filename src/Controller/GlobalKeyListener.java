package Controller;

import View.MainView;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyListener implements NativeKeyListener {

    MainView mainView;
    boolean isCapsLocked;
    boolean isNumLocked;

    public GlobalKeyListener(MainView mainView) {
        this.mainView = mainView;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        isCapsLocked = toolkit.getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        isNumLocked = toolkit.getLockingKeyState(KeyEvent.VK_NUM_LOCK);
        validateCapsLock();
        validateNumLock();
    }

    public void setUp() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (e.getKeyCode() == 58) {
            isCapsLocked = !isCapsLocked;
            validateCapsLock();
        }
        if (e.getKeyCode() == 69) {
            isNumLocked = !isNumLocked;
            validateNumLock();
        }
    }

    private void validateCapsLock() {
        if (isCapsLocked) {
            mainView.setCapsLockIconEnabled();
        } else {
            mainView.setCapsLockIconDisabled();
        }
    }

    private void validateNumLock() {
        if (isNumLocked) {
            mainView.setNumLockIconEnabled();
        } else {
            mainView.setNumLockIconDisabled();
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }
}
