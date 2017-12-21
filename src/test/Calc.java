package test;

import java.util.TreeMap;

public class Calc
{
    private final TreeMap<Integer, Integer> carts, color;
    boolean doColor, doStraight;
    
    public Calc()
    {
        carts = new TreeMap<>();
        color = new TreeMap<>();
        doColor = false;
        doStraight = true;
    }
    
    public void addCart(int value, int suit)
    {
        Integer auxValue = carts.get(value);
        Integer auxSuit = color.get(suit);
        
        if (auxValue == null)
            auxValue = 1;
        else
        {
            auxValue++;
            doStraight = false;
        }
        
        if (auxSuit == null)
            auxSuit = 1;
        else
            auxSuit++;
        
        carts.put(value, auxValue);
        color.put(suit, auxSuit);
        
        if (auxSuit == 5)
            doColor = true;
    }
    
    public int getHand()
    {
        int pair = 0, three = 0, poker = 0, last = -1, hand = 0;
        boolean firstCart = true;
        
        for (int i = 0; i < 13; i++)
        {
            if (carts.containsKey(i))
            {
                if (firstCart)
                {
                    last = i - 1;
                    firstCart = false;
                }                    
                
                switch (carts.get(i))
                {
                    case 1:
                        if(doStraight)
                        {
                            if (i - 1 == last || (i == 12 && carts.containsKey(4)))
                                last = i;
                            else
                                doStraight = false;
                        }
                        break;
                    case 2:
                        pair++;
                        break;
                    case 3:
                        three++;
                        break;
                    case 4:
                        poker++;
                }
            }
        }
        
        if(doColor && doStraight)
            hand = 8;
        else if (poker == 1)
            hand = 7;
        else if(three == 1 && pair == 1)
            hand = 6;      
        else if(doColor)
            hand = 5;
        else if(doStraight)
            hand = 4;
        else if (three == 1)
            hand = 3;
        else if (pair == 2)
            hand = 2;
        else if(pair == 1)
            hand = 1;
        
        return hand;
    }
}