import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
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

    public Console()
    {
        setSize(800,800);
        setLayout(new BorderLayout());

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        textArea.setForeground(Color.BLACK);
        textArea.setBackground(Color.WHITE);
        add(textArea, BorderLayout.CENTER);

        textField = new TextField();
        textField.setFont(new Font("Sans Serif", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);
        textField.addActionListener(this);//adds an action listener to the input field
        add(textField, BorderLayout.SOUTH);
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

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        //executes the method when enter is pressed

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

    public void print(Object o)
    {
        //executes the method when called using c.print("");

        countIO++;//updates the string array index
        lastO = countIO;//records the index of the last output
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

    public static void main(String[] args)
    {
        
    }
    
}

