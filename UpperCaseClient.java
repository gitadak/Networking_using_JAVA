//Ass9Q1 --- UpperCaseClient.java

import java.io.*;
import java.net.*;

public class UpperCaseClient 
{
    public static void main(String[] args) 
    {
        String serverAddress = "localhost";
        int port = 7000;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) 
        {

            System.out.print("Enter a lowercase string: ");
            String input = userInput.readLine();
            out.println(input);

            String response = in.readLine();
            System.out.println("Received from server (uppercase): " + response);

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
UpperCaseServer.java
--------------------
C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>javac UpperCaseServer.java

C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>java UpperCaseServer
Server is running and waiting for client...
Client connected.
Received from client: Hello World!
Connection closed.

UpperCaseClient.java
--------------------
C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>javac UpperCaseClient.java

C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>java UpperCaseClient
Enter a lowercase string: Hello World!
Received from server (uppercase): HELLO WORLD!

*/