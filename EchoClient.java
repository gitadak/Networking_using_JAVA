//Ass7Q1 --- EchoClient.java

import java.io.*;
import java.net.*;

public class EchoClient 
{
    public static void main(String[] args) 
    {
        String serverAddress = "localhost";
        int port = 6000;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) 
        {

            System.out.println("Connected to Echo Server. Type messages, type 'bye' to exit.");

            String input;
            while (true) 
            {
                System.out.print("You: ");
                input = userInput.readLine();
                out.println(input);

                String response = in.readLine();
                System.out.println(response);

                if ("bye".equalsIgnoreCase(input)) 
                {
                    break;
                }
            }
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