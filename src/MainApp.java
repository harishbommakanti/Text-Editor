import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

public class MainApp extends JFrame implements ActionListener
{
    //Main JFrame for the text editor
    private JFrame f;

    //Main text area space
    private JTextArea t;

    //main menu bar
    private JMenuBar menubar = new JMenuBar();

    //JMenu item for the directory
    private JMenu directory = new JMenu();


    public static void main(String[] args)
    {
        //call the constructor to set the JFrame attributes and set things in motion
        new MainApp();
    }

    public MainApp()
    {
        //name the window
        f = new JFrame("Text Editor");

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //styling
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e){}

        //sets up main text area
        t = new JTextArea();
        initializeTextArea(t/*,width,height*/);

        //enable scrolling
        JScrollPane scroll = new JScrollPane(t,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        f.add(scroll);

        //main menubar for 'new', 'open', and 'save' buttons
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
        menubar.add(directory);
        updateMenubar(null);

        //add code for when the X button is clicked
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveFile();
                System.out.println("Closing...");
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    private void initializeTextArea(JTextArea t/*, int w, int h*/)
    {
        t.setFont(new Font("Calibri", Font.PLAIN, 14));
        t.setLineWrap(true);
        t.setWrapStyleWord(true);

        t.setCaretColor(Color.WHITE);
        t.setEditable(true);
        t.setBackground(Color.decode("#2c2f33"));
        t.setForeground(Color.WHITE);
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

    //makes a new file AKA saves the current file, starts a new file
    private void makeNewFile()
    {
        saveFile();
        t.setText("");
        updateMenubar(null);
    }

    private void openFile()
    {
        //save the contents of the current file, 'close it'
        saveFile();
        t.setText("");

        //Directory which user can choose
        String directory = "C:\\Users\\haris\\Desktop";

        //initializes an explorer object of windows in JSwing
        JFileChooser explorer = new JFileChooser(directory);

        //opens the explorer and assigns an integer to watch for the actions
        int openDialogueVal = explorer.showOpenDialog(null);

        //if the person clicked the file
        if (openDialogueVal == JFileChooser.APPROVE_OPTION)
        {
            File chosen = explorer.getSelectedFile(); //get the file
            //t.append(chosen.getAbsolutePath());
            String chosenDirectory = chosen.getAbsolutePath();

            //update the directory in the menubar
            updateMenubar(chosenDirectory);

            //add the contents of that file to the text area line by line
            try
            {
                Scanner reader = new Scanner(chosen);
                while (reader.hasNextLine())
                    t.append(reader.nextLine() + "\n");
            }
            catch(FileNotFoundException e){}
        }
    }

    private void saveFile()
    {
        String currDirectory = getCurrDirectory(); //gets the current directory from the menubar
        if (currDirectory.equals("[No file chosen]")) //need to save to a new file
        {
            if (t.getText().equals("")) //don't do anything if there is no text to be saved
                return;
            JFileChooser explorer = new JFileChooser("C:\\Users\\haris\\Desktop");

            int r = explorer.showSaveDialog(null); //open the save button dialogue

            if (r == JFileChooser.APPROVE_OPTION) //if yes is clicked
            {
                String newPath = explorer.getSelectedFile().getAbsolutePath();
                updateMenubar(newPath);
                File newFile = new File(newPath);
                try
                {
                    //open an existing or new file and overwrite whatever it has
                    FileWriter w = new FileWriter(newFile, false);

                    w.write(t.getText());
                    w.flush();
                    w.close();
                } catch (Exception event)
                {
                    JOptionPane.showMessageDialog(f,event.getMessage());
                }
            }
        } else //save to the current file directory
        {
            currDirectory = currDirectory.substring(1,currDirectory.length()-1); //need to parse out the [ ]
            File currentFile = new File(currDirectory);
            writeToFile(currentFile);
        }
    }

    //updates the directory JMenu of the JMenuBar
    private void updateMenubar(String filename)
    { directory.setText("[" + ((filename == null)? "No file chosen" : filename) + "]"); }

    private String getCurrDirectory()
    { return directory.getText(); }

    private void writeToFile(File newFile)
    {
        try
        {
            //open an existing or new file and overwrite whatever it has
            FileWriter w = new FileWriter(newFile, false);

            w.write(t.getText());
            w.flush();
            w.close();
        } catch (Exception event)
        {
            JOptionPane.showMessageDialog(f,event.getMessage());
        }
    }
}