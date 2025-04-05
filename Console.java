import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * prompts the user with a survey
 * 
 */
public class Console extends Frame implements ActionListener
{
    private TextArea textArea;
    private TextField textField;
    private ArrayList<String> consoleText = new ArrayList<>();

    private int countIO = -1;
    private int lastI = -1;
    private int lastO = -1;
    private boolean isScanning;
    private String scannedValue;
    
    public static JFrame frame = new JFrame("Hello World!");
    
    
    public JButton helpButton;
    public JButton gameButton;
    
    public Console()
    {
        setSize(800,800);
        setLayout(new BorderLayout());

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(new Color(189, 255, 249));
        add(textArea, BorderLayout.CENTER);

        textField = new TextField();
        textField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);
        textField.addActionListener(this);//adds an action listener to the input field
        add(textField, BorderLayout.SOUTH);
        
        helpButton = new JButton("Get Help");
        helpButton.addActionListener(this);
        helpButton.setPreferredSize(new Dimension(200, 75));
        add(helpButton, BorderLayout.NORTH);
        helpButton.setBackground(new Color(227, 34, 95));
        helpButton.setFont(new Font("Sans Serif", Font.PLAIN, 36));
        
        gameButton = new JButton("Play The Game!");
        gameButton.addActionListener(this);
        gameButton.setSize(300, 200);
        add(gameButton, BorderLayout.EAST);
        gameButton.setBackground(new Color(10, 240, 79));
        gameButton.setFont(new Font("Sans Serif", Font.PLAIN, 36));
        
        //formats the input window
        addWindowListener(new WindowAdapter() 
            {
                public void windowClosing(WindowEvent e) 
                {
                    exit();

                }
            });

        setVisible(true);
        //makes the window visible
    }

    public void exit()
    {
        print("Goodbye!");
        try 
        {
            Thread.sleep(100); // waits for 1 tenth of a second (100ms) before closing
        } 
        catch (InterruptedException e) //makes sure an interupted error is not thrown
        {
            print(e);//prints the exception object using the print method
        }
        System.exit(0);
        //exits the program without an error if the console is closed
    }

    public void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
            print("\n\nAn ERROR has occurred.\n\nPlease call 988 for assistance.");
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        //executes the method when enter is pressed
        if (e.getSource().equals(helpButton))
            openWebpage("https://www.thetrevorproject.org/get-help/");
        else if (e.getSource().equals(gameButton))
        {
            String command = "C:\\Users\\jwjar\\Downloads\\Labels\\Labels.exe";
            try
            {
            Process process = Runtime.getRuntime().exec(command);
            }
            catch(Exception e2)
            {
                
            }
        }
        else  
        {
            countIO++;//updates the string array index
            lastI = countIO;//records the index of the last input
            consoleText.add(textField.getText());//adds the new input text to the string array
            if (isScanning)
            {
                isScanning = false;
                scannedValue = consoleText.get(countIO);//outputs the text if the system is waiting for input
            }
            textField.setText("");//clears the input field
        }
    }
    
    public void print(Object o)
    {
        //executes the method when called using c.print("");

        countIO++;//updates the string array index
        lastO = countIO;//records the index of the last output
        consoleText.add(o.toString());
        textArea.append(consoleText.get(countIO));
    }
    
    public void println(Object o)
    {
        //executes the method when called using c.print("");

        countIO++;//updates the string array index
        lastO = countIO;//records the index of the last output
        consoleText.add(o.toString()+"\n");
        textArea.append(consoleText.get(countIO));
    }

    public String scanString()
    {
        isScanning = true;//tells the system to wait for input
        while (isScanning)
        {
            try 
            {
                Thread.sleep(10); // waits for 1 tenth of a second (100ms) before checking for input
            } 
            catch (InterruptedException e) //makes sure an interupted error is not thrown
            {
                print(e);//prints the exception object using the print method
            }
        }
        return scannedValue; //returns the scanned value after it has been set in the input handler
    }
    
    
    public void clear()
    {
        textArea.setText("");
    }
    
    public void makeButton(String title, int width, int height)
    {
        JButton button = new JButton("title");
        frame.add(button);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        
    }
    
}

