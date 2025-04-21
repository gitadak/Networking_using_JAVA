//Java pogram to access IP address of local machine or any oher machine

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ExpQ5
{
    public static void main(String[] args)
    {
        try
        {
            //Getting IP address of thr local machine
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Local Host Name: " + localHost.getHostName());
            System.out.println("Local IP Address: " + localHost.getHostAddress());

            //Getting IP address of another machine (e.g., www.google.com)
            String otherHostName = "172.16.1.32"; //you can change this to another hostname or IP
            InetAddress remoteHost = InetAddress.getByName(otherHostName);
            System.out.println("Remote Host Name: " + remoteHost.getHostName());
            System.out.println("Remote IP Address: " + remoteHost.getHostAddress()); 
        }
        catch(UnknownHostException e)
        {
            System.out.println("Host could not be resolved: " + e.getMessage());
        }
    }
}

/*
Local Host Name: C1-34
Local IP Address: 172.16.1.34
Remote Host Name: C1-32
Remote IP Address: 172.16.1.32
*/