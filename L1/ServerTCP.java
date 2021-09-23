/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package simple.demo;

import java.net.*;
import java.io.*;

/**
 *
 * @author teacher
 */
public class ServerTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        int port;
        ServerSocket server_socket;
        BufferedReader input;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("port = 15876 (default)");
            port = 15876;
        }
        try {
            server_socket = new ServerSocket(port);
            System.out.println("Serverul este activ la portul "
                    + server_socket.getLocalPort());
            // bucla
            while (true) {
                Socket socket = server_socket.accept();
                System.out.println("Conexiune acceptata "
                        + socket.getInetAddress()
                        + ":" + socket.getPort());
                input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                // afiseaza informatiile primite
                try {
                    while (true) {
                        String message = input.readLine();
                        if (message == null) {
                            break;
                        }
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
                // inchide conexiunea cu clientul
                try {
                    socket.close();
                    System.out.println("Conexiune inchisa de client ");
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
