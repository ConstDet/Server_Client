package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        final int PORT = 8086;
        final String HOST = "localhost";
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            System.out.println(String.format("Подключились на %d порту", clientSocket.getPort()));
            out.println("Константин");
            String reception = in.readLine();
            System.out.println(reception);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
