//Ass6Q1 --- DateTimeServer.java

import java.io.*;
import java.net.*;

public class DateTimeClient 
{
    public static void main(String[] args) 
    {
        String serverAddress = "localhost"; // or use IP address
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) 
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = in.readLine();
            System.out.println("Received from server: " + response);

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}

/*
Output:
------
DateTimeServer.java
-------------------
C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>javac DateTimeServer.java

C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>java DateTimeServer
Server is running and waiting for client connection...
Client connected.
Connection closed.

DateTimeClient.java
-------------------
C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>javac DateTimeClient.java

C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>java DateTimeClient
Received from server: Current Date and Time: 2025-05-01 21:36:32

*/