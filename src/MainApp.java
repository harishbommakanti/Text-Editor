import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

public class MainApp extends JFrame
{
    //Main JFrame for the text editor
    JFrame f;

    public static void main(String[] args)
    {
        //call the constructor to set the JFrame attributes and set things in motion
        new MainApp();
    }

    public MainApp()
    {
        //name the window
        f = new JFrame("Text Editor");

        //set width and height
        int width=600;
        int height=700;
        f.setSize(width,height);

        //styling
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e){}

        //final methods to show the box
        f.setLayout(null);
        f.setVisible(true);
    }
}