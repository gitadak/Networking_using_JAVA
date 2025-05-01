//Ass7Q1 --- EchoServer.java

import java.io.*;
import java.net.*;

public class EchoServer 
{
    public static void main(String[] args)
    {
        int port = 6000;

        try (ServerSocket serverSocket = new ServerSocket(port)) 
        {
            System.out.println("Echo Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) 
            {
                System.out.println("Received from client: " + inputLine);
                out.println("Echo: " + inputLine);

                if ("bye".equalsIgnoreCase(inputLine)) 
                {
                    break;
                }
            }

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
EchoServer.java
---------------
C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>javac EchoServer.java

C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>java EchoServer
Echo Server started. Waiting for client...
Client connected.
Received from client: hello
Received from client: Good bye!
Received from client: bye
Connection closed.

EchoClient.java
---------------
C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>javac EchoClient.java

C:\Users\DELL\OneDrive\Desktop\Networking_using_JAVA>java EchoClient
Connected to Echo Server. Type messages, type 'bye' to exit.
You: hello
Echo: hello
You: Good bye!
Echo: Good bye!
You: bye
Echo: bye

*/