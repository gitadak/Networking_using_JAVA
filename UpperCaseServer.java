//Ass9Q1 --- UpperCaseServer.java

import java.io.*;
import java.net.*;

public class UpperCaseServer 
{
    public static void main(String[] args) 
    {
        int port = 7000;

        try (ServerSocket serverSocket = new ServerSocket(port)) 
        {
            System.out.println("Server is running and waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String input = in.readLine();
            System.out.println("Received from client: " + input);

            String upper = input.toUpperCase();
            out.println(upper);

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