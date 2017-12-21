package test;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JFrame
{
    public Menu ()
    {
        initPanel();
        initJFrame();
    }
    
    private void initPanel()
    {
        JPanel panel = new JPanel();
        JLabel nText = new JLabel("Number of tests: ");
        JTextField nTest = new JTextField();
        JButton submit = new JButton("Calculate");
        panel.setLayout(new GridLayout(1, 3, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        submit.addActionListener((ActionEvent e) -> 
        {
            try
            {
                Test evaluatePoker = new Test(Integer.parseInt(nTest.getText()));
                evaluatePoker.exec();
            }
            catch (NumberFormatException ex)
            {
                nTest.setText("Insert a number");
            }
        });
        
        panel.add(nText);
        panel.add(nTest);
        panel.add(submit);
        this.add(panel);
    }
    
    private void initJFrame()
    {
        setTitle("Poker");
        setSize(400, 100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}