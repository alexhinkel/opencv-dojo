package de.lv1871.util;

import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.function.Consumer;

public final class OpenCVWindow {

    private JFrame frame;
    private JLabel label;

    public OpenCVWindow() {
        this("OpenCV");
    }

    public OpenCVWindow(String title) {
        this.frame = new JFrame(title);
        this.label = new JLabel();
        frame.add(label);
        frame.setVisible(true);
    }

    public void onWindowClosed(Consumer<WindowEvent> windowClosedListener) {
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                windowClosedListener.accept(e);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public void onKeyPressed(Consumer<KeyEvent> keyPressedListener) {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyPressedListener.accept(e);
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public void update(Mat image) {
        Mat updateImage = image;
        if (image.type() > 16) {
            Imgproc.cvtColor(image, updateImage, Imgproc.COLOR_BGRA2BGR);
        }

        ImageIcon icon = new ImageIcon(HighGui.toBufferedImage(updateImage));
        label.setIcon(icon);
        frame.pack();
    }
}
