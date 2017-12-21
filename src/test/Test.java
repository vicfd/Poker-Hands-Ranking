
package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Test 
{
    private int nTest, timeToSave;
    
    public Test(int nTest)
    {
        this.nTest = nTest;
        this.timeToSave = (int) (nTest * 0.1);
    }
    
    private void read(double[] hands)
    {
        int i = 0;
        File fichero;
        Scanner read = null;

        try 
        {
            fichero = new File("save.txt");
            read = new Scanner(fichero);

            while (read.hasNextLine()) 
            {
                hands[i] = Double.parseDouble(read.nextLine());
                i++;
            }
        } 
        catch (FileNotFoundException | NumberFormatException ex) {} 
        finally 
        {
            try 
            {
                if (read != null)
                    read.close();
            } 
            catch (Exception ex2) {}
        }
    }
    
    private void save(double[] hands)
    {
        String data = "";
        for (int i = 0; i < hands.length; ++i)
            data += hands[i] + "\r\n";
        
        FileWriter save = null;
        try 
        {
            save = new FileWriter("save.txt");
            save.write(data);
            save.close();
        } 
        catch (IOException ex) {}
        finally 
        {
            try 
            {
                if (save != null)
                    save.close();
            } 
            catch (IOException ex2) {}
        }
    }
    
    public void exec()
    {
        double[] hands = new double[9];
        read(hands);
        
        for (int i = 1; i <= nTest; ++i)
        {
            TreeMap<Integer, Integer> selectCarts = new TreeMap<>();
            Calc hand = new Calc();
            int j = 0;
            
            while (j < 5)
            {
                int randomCart = (int) Math.floor(Math.random() * 52);
                
                if (!selectCarts.containsKey(randomCart))
                {
                    hand.addCart(randomCart % 13, randomCart / 13);
                    selectCarts.put(randomCart, 0);
                    j++;
                }
            }
            
            hands[hand.getHand()]++;
            
            if (i % timeToSave == 0)
                save(hands);
        }
        
        if (nTest % timeToSave != 0)
            save(hands);
        
        nTest = 0;
        
        for (int i = 0; i < 9; ++i)
            nTest += hands[i];

        Visual showHands = new Visual(hands, nTest);     
    }
}
