//IP Address Analyzer

/*
 * Input an IP address in either dotted decimal or binary format & 
 * print the class of the IP address as well as the corresponding network address.
 */

import java.util.Scanner;

public class Exp4_NW
{

    // Method to check if the IP is in binary format
    private static boolean isBinaryFormat(String ip) 
    {
        return ip.matches("([01]{8}\\.){3}[01]{8}");
    }

    // Convert binary IP to decimal
    private static String binaryToDecimal(String binaryIP) 
    {
        String[] parts = binaryIP.split("\\.");
        StringBuilder decimalIP = new StringBuilder();
        for (String part : parts) 
        {
            decimalIP.append(Integer.parseInt(part, 2)).append(".");
        }
        return decimalIP.substring(0, decimalIP.length() - 1); // remove last dot
    }

    // Determine the class of the IP
    private static char getIPClass(int firstOctet) 
    {
        if (firstOctet >= 0 && firstOctet <= 127)
            return 'A';
        else if (firstOctet >= 128 && firstOctet <= 191)
            return 'B';
        else if (firstOctet >= 192 && firstOctet <= 223)
            return 'C';
        else if (firstOctet >= 224 && firstOctet <= 239)
            return 'D';
        else
            return 'E';
    }

    // Get the network address based on the class
    private static String getNetworkAddress(String[] octets, char ipClass) 
    {
        switch (ipClass) 
        {
            case 'A': return octets[0] + ".0.0.0";
            case 'B': return octets[0] + "." + octets[1] + ".0.0";
            case 'C': return octets[0] + "." + octets[1] + "." + octets[2] + ".0";
            default: return "Not applicable for Class " + ipClass;
        }
    }

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter IP address (dotted decimal or binary): ");
        String input = sc.nextLine();
    
        String ip = input;
    
        if (isBinaryFormat(input)) 
        {
            ip = binaryToDecimal(input);
            System.out.println("Converted Binary to Decimal: " + ip);
        }
    
        String[] octets = ip.split("\\.");
        if (octets.length != 4) 
        {
            System.out.println("Invalid IP address format: IP address consists of 4 octets");
            sc.close();
            return;
        }
    
        int firstOctet = 0;
        try 
        {
            for (String octet : octets) 
            {
                int val = Integer.parseInt(octet);
                if (val < 0 || val > 255) 
                {
                    System.out.println("Invalid IP address format: Each octet must be between 0 and 255");
                    sc.close();
                    return;
                }
            }
            firstOctet = Integer.parseInt(octets[0]);
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid IP address format: Octets must be numeric");
            sc.close();
            return;
        }
    
        char ipClass = getIPClass(firstOctet);
        String networkAddress = getNetworkAddress(octets, ipClass);
    
        System.out.println("IP Class: " + ipClass);
        System.out.println("Network Address: " + networkAddress);
    
        sc.close();
    }    
}


/*
Output:
------
Enter IP address (dotted decimal or binary): 10000000.00001011.00000011.00011111
Converted Binary to Decimal: 128.11.3.31
IP Class: B
Network Address: 128.11.0.0

Enter IP address (dotted decimal or binary): 128.11.3.31
IP Class: B
Network Address: 128.11.0.0
 */