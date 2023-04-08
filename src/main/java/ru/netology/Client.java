package ru.netology;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int PORT = 8086;
        final String HOST = "localhost";
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            System.out.println(String.format("Подключились на %d порту", clientSocket.getPort()));
            String reception = in.readLine();//приняли город или ???
            System.out.println(reception);
            String city = scanner.nextLine();
            out.println(city);
            reception = in.readLine();
            System.out.println(reception);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
