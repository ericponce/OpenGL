/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author 7071-766
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket myServer = new ServerSocket(4444);
        Socket client;
        int id = 0;

        System.out.println("Successfully Started. Waiting for Clients");
        Scanner sc = new Scanner(System.in);
        while (true){
            new Thread(new Runnable() {

                public void run() {
                    Scanner sc = new Scanner(System.in);
                    while (true) {
                        if (sc.hasNext()) {
                            if (sc.next().equalsIgnoreCase("q")) {
                                System.exit(0);
                            }
                        }
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            }).start();
            client = myServer.accept();
            ClientThread nThread = new ClientThread(client, id++);
            nThread.start();

            System.out.println("Client Accepted.");
        }
    }

}
