import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

public class MainApp extends JFrame implements ActionListener
{
    //Main JFrame for the text editor
    private JFrame f;

    //Main text area space
    private JTextArea t;

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

        //adding a menu button for main file operations
        JMenu file = new JMenu("File");
        JMenuItem menuNew = new JMenuItem("New");
        JMenuItem menuOpen = new JMenuItem("Open");
        JMenuItem menuSave = new JMenuItem("Save");

        //adding event listeners
        menuNew.addActionListener(this);
        menuOpen.addActionListener(this);
        menuSave.addActionListener(this);

        //adding them to the menu
        file.add(menuNew);
        file.add(menuOpen);
        file.add(menuSave);
        menubar.add(file);


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

    //@Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        switch (command) {
            case "New": makeNewFile(); break;
            case "Open": openFile(); break;
            case "Save": saveFile(); break;
        }
    }

    private void makeNewFile()
    {

    }

    private void openFile()
    {
        //Directory which user can choose
        String directory = "C:\\Users:\\haris";

        //initializes an explorer object of windows in JSwing
        JFileChooser explorer = new JFileChooser(directory);

        //opens the explorer and assigns an integer to watch for the actions
        int openDialogueVal = explorer.showOpenDialog(null);

        //if the person clicked the file
        if (openDialogueVal == JFileChooser.APPROVE_OPTION)
        {
            File chosen = explorer.getSelectedFile(); //get the file
            //t.append(chosen.getAbsolutePath());
        }
    }

    private void saveFile()
    {

    }
}