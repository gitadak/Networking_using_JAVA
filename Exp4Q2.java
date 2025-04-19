//Subnet Calculator - for Classful addressing

/*
Input a site address and the number of subnets (that may not be power of 2, 
so modify the program accordingly) as input and print the subnet mask 
as well as subnetwork address of each subnet.
*/

import java.util.*;

public class Exp4Q2
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the IP address (e.g., 192.168.0.56): ");
        String ip = sc.nextLine();

        // Validate IP
        if (!isValidIP(ip))
        {
            sc.close();
            return;
        }

        System.out.print("Enter the number of subnets: ");
        int numSubnets = sc.nextInt();

        char ipClass = getIPClass(ip);
        int defaultMaskBits = getDefaultMaskBits(ipClass);
        if (defaultMaskBits == -1)
        {
            System.out.println("Invalid or unsupported IP address class.");
            sc.close();
            return;
        }

        String networkAddress = getNetworkAddress(ip, defaultMaskBits);
        int subnetBits = calculateSubnetBits(numSubnets);
        int totalMaskBits = defaultMaskBits + subnetBits;

        if (totalMaskBits > 30)
        {
            System.out.println("Too many subnets requested. Not feasible.");
            sc.close();
            return;
        }

        String subnetMask = calculateSubnetMask(totalMaskBits);
        System.out.println("\nIP Class: " + ipClass);
        System.out.println("Detected Network Address: " + networkAddress);
        System.out.println("Subnet Mask: " + subnetMask);

        System.out.println("\nSubnet Details:");
        displaySubnetDetails(networkAddress, totalMaskBits, numSubnets, defaultMaskBits);

        sc.close();
    }

    static boolean isValidIP(String ip)
    {
        String[] octets = ip.split("\\.");
        if (octets.length != 4)
        {
            System.out.println("Invalid IP address format: IP address must consist of 4 octets.");
            return false;
        }

        try
        {
            for (String octet : octets)
            {
                int val = Integer.parseInt(octet);
                if (val < 0 || val > 255)
                {
                    System.out.println("Invalid IP address format: Each octet must be between 0 and 255.");
                    return false;
                }
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Invalid IP address format: Octets must be numeric.");
            return false;
        }

        return true;
    }

    static char getIPClass(String ip)
    {
        int firstOctet = Integer.parseInt(ip.split("\\.")[0]);
        if (firstOctet >= 1 && firstOctet <= 126) return 'A';
        if (firstOctet >= 128 && firstOctet <= 191) return 'B';
        if (firstOctet >= 192 && firstOctet <= 223) return 'C';
        return 'X'; // unsupported
    }

    static int getDefaultMaskBits(char ipClass)
    {
        if (ipClass == 'A') return 8;
        if (ipClass == 'B') return 16;
        if (ipClass == 'C') return 24;
        return -1;
    }

    static String getNetworkAddress(String ip, int defaultMaskBits)
    {
        String[] parts = ip.split("\\.");
        int[] ipParts = new int[4];
        for (int i = 0; i < 4; i++)
        {
            ipParts[i] = Integer.parseInt(parts[i]);
        }

        int[] netParts = new int[4];

        for (int i = 0; i < 4; i++)
        {
            if (defaultMaskBits >= (i + 1) * 8)
            {
                netParts[i] = ipParts[i];
            }
            else
            {
                netParts[i] = 0;
            }
        }

        return netParts[0] + "." + netParts[1] + "." + netParts[2] + "." + netParts[3];
    }

    static int calculateSubnetBits(int numSubnets)
    {
        int bits = 0;
        while (Math.pow(2, bits) < numSubnets)
        {
            bits++;
        }
        return bits;
    }

    static String calculateSubnetMask(int totalBits)
    {
        int[] mask = new int[4];
        for (int i = 0; i < totalBits; i++)
        {
            mask[i / 8] |= (1 << (7 - (i % 8)));
        }
        return mask[0] + "." + mask[1] + "." + mask[2] + "." + mask[3];
    }

    static void displaySubnetDetails(String networkIp, int totalMaskBits, int numSubnetsToPrint, int defaultMaskBits)
    {
        String[] parts = networkIp.split("\\.");
        int baseIp = (Integer.parseInt(parts[0]) << 24)
                | (Integer.parseInt(parts[1]) << 16)
                | (Integer.parseInt(parts[2]) << 8)
                | Integer.parseInt(parts[3]);

        int hostBits = 32 - totalMaskBits;
        int blockSize = (int) Math.pow(2, hostBits);

        int classfulRange = (int) Math.pow(2, 32 - defaultMaskBits);
        for (int i = 0; i < numSubnetsToPrint; i++)
        {
            int subnetAddress = baseIp + (i * blockSize);
            int broadcastAddress;

            // For the last subnet, extend to end of classful block
            if (i == numSubnetsToPrint - 1)
            {
                broadcastAddress = baseIp + classfulRange - 1;
            }
            else
            {
                broadcastAddress = subnetAddress + blockSize - 1;
            }

            int firstHost = (blockSize > 2) ? subnetAddress + 1 : subnetAddress;
            int lastHost = (blockSize > 2) ? broadcastAddress - 1 : broadcastAddress;

            System.out.println("Subnet " + (i + 1) + ":");
            System.out.println("  Subnet Address : " + intToIp(subnetAddress));
            System.out.println("  First Host     : " + intToIp(firstHost));
            System.out.println("  Last Host      : " + intToIp(lastHost));
            System.out.println("  Broadcast Addr : " + intToIp(broadcastAddress));
            System.out.println();
        }
    }

    static String intToIp(int ip)
    {
        return ((ip >> 24) & 0xFF) + "." +
            ((ip >> 16) & 0xFF) + "." +
            ((ip >> 8) & 0xFF) + "." +
            (ip & 0xFF);
    }
}

/*
Output:
------
Enter the IP address (e.g., 192.168.0.56): 200.10.20.0
Enter the number of subnets: 5

IP Class: C
Detected Network Address: 200.10.20.0
Subnet Mask: 255.255.255.224

Subnet Details:
Subnet 1:
  Subnet Address : 200.10.20.0
  First Host     : 200.10.20.1
  Last Host      : 200.10.20.30
  Broadcast Addr : 200.10.20.31

Subnet 2:
  Subnet Address : 200.10.20.32
  First Host     : 200.10.20.33
  Last Host      : 200.10.20.62
  Broadcast Addr : 200.10.20.63

Subnet 3:
  Subnet Address : 200.10.20.64
  First Host     : 200.10.20.65
  Last Host      : 200.10.20.94
  Broadcast Addr : 200.10.20.95

Subnet 4:
  Subnet Address : 200.10.20.96
  First Host     : 200.10.20.97
  Last Host      : 200.10.20.126
  Broadcast Addr : 200.10.20.127

Subnet 5:
  Subnet Address : 200.10.20.128
  First Host     : 200.10.20.129
  Last Host      : 200.10.20.254
  Broadcast Addr : 200.10.20.255
*/