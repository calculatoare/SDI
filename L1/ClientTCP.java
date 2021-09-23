/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package simple.demo;

import java.net.*;
import java.io.*;

public class ClientTCP {

    public static void main(String[] args) {
        int port = 15876;
        String server = "127.0.0.1"; // clientul va rula pe aceasi masina
        Socket socket = null;
        String lineToBeSent;
        BufferedReader input;
        PrintWriter output;
        int ERROR = 1;
        // citeste argumentele
        if (args.length == 2) {
            server = args[0];
            try {
                port = Integer.parseInt(args[1]);
            } catch (Exception e) {
                System.out.println("server port = 15876 (default)");
                port = 15876;
            }
        }
        // conectare la server
        try {
            socket = new Socket(server, port);
            System.out.println("Conectat la serverul "
                    + socket.getInetAddress()
                    + ":" + socket.getPort());
        } catch (UnknownHostException e) {
            System.out.println(e);
            System.exit(ERROR);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(ERROR);
        }
        try {
            input = new BufferedReader(
                    new InputStreamReader(System.in));
            output = new PrintWriter(socket.getOutputStream(), true);
            // preia informatiile si le transmite la server
            while (true) {
                lineToBeSent = input.readLine();
                // programul se opreste daca intalneste "."
                if (lineToBeSent.equals(".")) {
                    break;
                }
                output.println(lineToBeSent);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
