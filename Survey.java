import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
/**
 * 
 * prompts the user with a survey
 * 
 */
public class Survey extends Frame implements ActionListener
{
    private TextArea textArea;
    private TextField textField;

    public Survey()
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
        textField.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        textField.setForeground(Color.BLACK);
        
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

    }
    
    public static void main()
    {
        
    }
}
