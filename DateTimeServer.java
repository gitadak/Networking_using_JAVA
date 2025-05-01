//Ass6Q1 --- DateTimeServer.java

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeServer 
{
    public static void main(String[] args) 
    {
        int port = 5000; // You can use any available port

        try (ServerSocket serverSocket = new ServerSocket(port)) 
        {
            System.out.println("Server is running and waiting for client connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Get current date and time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateTime = now.format(formatter);

            out.println("Current Date and Time: " + dateTime);

            socket.close();
            System.out.println("Connection closed.");
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