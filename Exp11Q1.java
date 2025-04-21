/*
Write a program to implement error detection using CRC used in Data Link layer
*/

import java.util.Scanner;

public class Exp11Q1
{
    private static String xor(String dividend, String divisor)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < divisor.length(); i++)
        {
            result.append(dividend.charAt(i) == divisor.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

  
    private static String mod2Division(String dividend, String divisor)
    {
        int pick = divisor.length();
        String temp = dividend.substring(0, pick);

        while (pick < dividend.length())
        {
            if (temp.charAt(0) == '1')
            {
                temp = xor(temp, divisor) + dividend.charAt(pick);
            }
            else 
            {
                temp = xor(temp, "0".repeat(divisor.length())) + dividend.charAt(pick);
            }
            pick++;
        }

        if (temp.charAt(0) == '1') 
        {
            temp = xor(temp, divisor);
        } 
        else 
        {
            temp = xor(temp, "0".repeat(divisor.length()));
        }

        return temp;
    }

   
    private static String encodeData(String dataword, String generator) 
    {
        int generatorLength = generator.length();
        String appendedData = dataword + "0".repeat(generatorLength - 1);
        String remainder = mod2Division(appendedData, generator);
        return dataword + remainder;
    }

    private static boolean checkData(String receivedData, String generator) 
    {
        String remainder = mod2Division(receivedData, generator);
        return remainder.replace("0", "").isEmpty();
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter dataword (binary): ");
        String dataword = scanner.next();

        System.out.print("Enter generator polynomial (binary): ");
        String generator = scanner.next();

        
        String codeword = encodeData(dataword, generator);
        System.out.println("Transmitted codeword: " + codeword);

        
        System.out.print("Enter received codeword (binary): ");
        String receivedData = scanner.next();

        
        if (checkData(receivedData, generator)) 
        {
            System.out.println("No error detected in received data.");
        } 
        else 
        {
            System.out.println("Error detected in received data!");
        }

        scanner.close();
    }
}
