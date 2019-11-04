package SystemTray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SystemTrayDemo {

    private static void constructGUI() { // FLAGG NOTIFICATION
        SystemTray tray = SystemTray.getSystemTray();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        toolkit.beep();
        toolkit.beep();
        toolkit.beep();
        toolkit.beep();

        Image image = toolkit.getImage("C:\\Users\\Mertali\\Desktop\\AdvJavaSecond\\src\\SystemTray\\myImage.jpg");


        PopupMenu menu = new PopupMenu();
        MenuItem m1 = new MenuItem("Say Hello");
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello");
            }
        });
        menu.add(m1);

        MenuItem m2 = new MenuItem("Open pdf");
        m2.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("C:\\Users\\Mertali\\Desktop\\AdvJavaSecond\\src\\SystemTray\\myPdf.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException e1) {
                        System.out.println("SomeThing happened i dont know");
                    }

                }
            }
        });
        menu.add(m2);

        TrayIcon icon = new TrayIcon(image, "SystemTray Demo", menu);
        icon.setImageAutoSize(true);

        try {
            tray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                constructGUI();
            }
        });

    }


}
