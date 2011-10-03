/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 7071-766
 */
public class ClientThread extends Thread {

    Socket myClient;
    int clientID = -1;
    boolean running = true;
    long startTime = System.currentTimeMillis();

    public ClientThread(Socket client, int id) {
        myClient = client;
        clientID = id;
    }

    @Override
    public void run() {
        Scanner scan = null;
        PrintStream print = null;
        try {
            scan = new Scanner(myClient.getInputStream());
            print = new PrintStream(myClient.getOutputStream());
            while (true) {
                if (scan.hasNext()) {
                    String input = scan.nextLine();
                    int length = input.length();
                    if (length == 2 && input.charAt(0) == '/') {
                        switch (input.charAt(1)) {
                            case 'q':
                                print.println("Server Closed. All Connections terminated");
                                System.exit(0);
                                break;
                            case 'd':
                                print.println("Disconnected.");
                                this.interrupt();
                                break;
                            case 'e':
                                print.println(System.currentTimeMillis() - startTime);
                                break;
                            case 'p':
                                print.println("PING");
                                break;
                            case 'h':
                                print.println("Commands: e - connetion time, d - disconnect, q - close server");
                                break;
                        }
                    } else if (length >= 4 && input.substring(0, 4).equals("echo")) {
                        print.println(input.substring(input.indexOf("echo") + 5));
                    } else {
                        print.println("Not a Command.");
                    }
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                }
            }
        } catch (IOException ex) {
            System.out.println("Client Closed.");
            return;
        } finally {
            scan.close();
            print.close();
            System.out.println("Client Closed.");
        }
    }
}
