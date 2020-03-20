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

    //Main text area space
    JTextArea t;

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
        f.setResizable(false);

        //styling
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e){}

        //sets up main text area
        t = new JTextArea();
        initializeTextArea(t,width,height);
        f.add(t);

        //main menubar for 'new', 'open', and 'save' buttons
        JMenuBar menubar = new JMenuBar();
        f.setJMenuBar(menubar);

        JMenu m = new JMenu("File");

        //main menu buttons
        JMenuItem menuNew = new JMenuItem("New");
        JMenuItem menuOpen = new JMenuItem("Open");
        JMenuItem menuSave = new JMenuItem("Save");

        m.add(menuNew);
        m.add(menuOpen);
        m.add(menuSave);

        menubar.add(m);


        //final methods to show the box
        f.setLayout(null);
        f.setVisible(true);
    }

    private void initializeTextArea(JTextArea t, int w, int h)
    {
        t.setBounds(0,0,w,h);
        t.setFont(new Font("Calibri", Font.PLAIN, 14));
        t.setLineWrap(true);
        t.setWrapStyleWord(true);
    }
}