/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import accounts.BankAccount;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Eric
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        boolean cont = true;

        Scanner scan = new Scanner(System.in);

        Socket myClient = new Socket("192.168.2.100", 4444);

        ObjectInputStream objectIn = new ObjectInputStream(myClient.getInputStream());
        PrintStream textOut = new PrintStream(myClient.getOutputStream());

        while (cont) {
            System.out.println("Would you like to retreive account? (y/n)");
            char response = scan.nextLine().charAt(0);
            switch (Character.toUpperCase(response)) {
                case 'Y':
                    System.out.println(getAccount(textOut, objectIn).toString());
                    break;
                case 'N':
                    closeServer(textOut);
                    cont = false;
                    break;
                case 'Q':
                    cont = false;
                    break;
            }
        }


        myClient.close();
        objectIn.close();
        textOut.close();

    }

    public static BankAccount getAccount(PrintStream textOut, ObjectInputStream objectIn) throws IOException, ClassNotFoundException {

        textOut.println("SEND");

        BankAccount b = (BankAccount) objectIn.readObject();

        return b;
    }

    public static void closeServer(PrintStream textOut) throws IOException {

        textOut.println("QUIT");

    }
}
