package test;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Visual extends JFrame
{
    public Visual (double[] hands, int nTest)
    {
        initPanel(hands, nTest);
        initJFrame();
    }
    
    private void initPanel(double[] hands, int nTest)
    {
        JPanel panel = new JPanel();
        JLabel[] handText = new JLabel[9];
        JProgressBar[] handProgressBar = new JProgressBar[9];        
        panel.setLayout(new GridLayout(9, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        for (int i = 0; i < handText.length; ++i) 
        {
            handText[i] = new JLabel();
            handProgressBar[i] = new JProgressBar();
            handProgressBar[i].setStringPainted(true);
        }
        
        DecimalFormat df = new DecimalFormat("#.####");
        handText[0].setText("HIGH CARD: (" + df.format((hands[0] / nTest) * 100) + "%)");
        handText[1].setText("1 PAIR: (" + df.format((hands[1] / nTest) * 100) + "%)");
        handText[2].setText("2 PAIR: (" + df.format((hands[2] / nTest) * 100) + "%)");
        handText[3].setText("3 OF A KIND: (" + df.format((hands[3] / nTest) * 100) + "%)");
        handText[4].setText("STRAIGHT: (" + df.format((hands[4] / nTest) * 100) + "%)");
        handText[5].setText("FLUSH: (" + df.format((hands[5] / nTest) * 100) + "%)");
        handText[6].setText("FULL HOUSE: (" + df.format((hands[6] / nTest) * 100) + "%)");
        handText[7].setText("4 OF A KIND: (" + df.format((hands[7] / nTest) * 100) + "%)");
        handText[8].setText("STRAIGHT FLUSH: (" + df.format((hands[8] / nTest) * 100) + "%)");

        for (int i = 0; i < handProgressBar.length; ++i)
            handProgressBar[i].setValue((int) ((hands[i] / nTest) * 100));
        
        for (int i = 0; i < handText.length; ++i)
        {
            panel.add(handText[i]);
            panel.add(handProgressBar[i]);
        }
        
        this.add(panel);
    }
    
    private void initJFrame()
    {
        setTitle("Poker - Hands Ranking");
        setSize(700, 500);
        setVisible(true);
        setMinimumSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}