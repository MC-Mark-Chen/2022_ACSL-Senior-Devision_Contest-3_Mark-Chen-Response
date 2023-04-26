import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class App {
    public static String getTraversals(String input) {
        ArrayList<String> arrlS = new ArrayList<String>();
        ArrayList<Integer> arrlI = new ArrayList<Integer>();
        ArrayList<String> arrlSC = new ArrayList<String>();
        ArrayList<Integer> arrlIC = new ArrayList<Integer>();
        int criticalIdx = 0;
        int increment = 1;
        String str1 = "";
        String str2 = "";
        
        arrlS.add(input.substring(0, 1));
        arrlI.add(0);
        
        for(int i = 1; i < input.length(); i++)
        {
            for(int j = 0; j < arrlS.size(); j++)
            {
                if(input.charAt(i) <= arrlS.get(j).charAt(0))
                {
                    arrlS.add(j, input.substring(i, i + 1));
                    if(j == 0)
                    {
                        arrlI.add(j, arrlI.get(j) + 1);
                    }
                    else
                    {
                        arrlI.add(j, Math.max(arrlI.get(j - 1), arrlI.get(j)) + 1);
                    }
                    break;
                }
                else if(j == arrlS.size() - 1 && input.charAt(i) > arrlS.get(j).charAt(0))
                {
                    arrlS.add(input.substring(i, i + 1));
                    arrlI.add(j + 1, arrlI.get(j) + 1);
                    break;
                }
            }
        }

        for(int i = 0; i < arrlS.size(); i++)
        {
            arrlIC.add(arrlI.get(i));
            arrlSC.add(arrlS.get(i));
        }
        
        for(int i = 0; i < arrlIC.size(); i++)
        {
            if(arrlIC.get(i) == 0)
            {
                criticalIdx = i;
                break;
            }
        }

        str1 += arrlSC.get(criticalIdx);

        for(int i = 0; i < criticalIdx; i++)
        {
            for(int j = 0; j < criticalIdx; j++)
            {
                if(arrlIC.get(j) == increment)
                {
                    str1 += arrlSC.get(j);
                    arrlSC.remove(j);
                    arrlIC.remove(j);
                    increment++;
                    break;
                }
            }
        }

        increment = 1;

        for(int i = 0; i < arrlSC.size(); i++)
        {
            for(int j = 0; j < arrlSC.size(); j++)
            {
                if(arrlIC.get(j) == increment)
                {
                    str1 += arrlSC.get(j);
                    arrlSC.remove(j);
                    arrlIC.remove(j);
                    increment++;
                    break;
                }
                if(arrlIC.get(j) == 1)
                {
                    increment = 2;
                    for(int k = 0; k < j; k++)
                    {
                        arrlIC.remove(k);
                        arrlSC.remove(k);
                    }
                }
            }
        }

        System.out.println(str1 + " " + str2);
        return str1 + " " + str2;
    }

    public static void main(String[] args)
    {
        getTraversals("BINARYSEARCHTREE");
    }
}
