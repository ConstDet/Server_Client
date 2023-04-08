package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 8086;
        String city = "";
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { // открыли порт для приема сообщений
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("Принято новое соединение!");
                    if (city.isEmpty()) {
                        out.println("???");//отправили сообщение и...
                        city = in.readLine();//ждем город
                        out.println(String.format("Принято %s", city));
                    } else {
                        out.println(city);//отправили город
                        String newCity = in.readLine();//ждем новый
                        if (newCity.equals("Stop")) {
                            out.println("Сервер остановлен");
                            return;
                        }
                        if (newCity.toLowerCase().charAt(0) == city.toLowerCase().charAt(city.length() - 1)) {
                            out.println("OK");
                            city = newCity;
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}