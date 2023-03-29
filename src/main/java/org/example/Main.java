package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws ParseException {
        List<Purchase> basket = new ArrayList<>();
        Categories categories = new Categories();
        CountingMaxCategories countingMaxCategories = new CountingMaxCategories();
        categories.getCategories();
        countingMaxCategories.getMaxCategory(basket);

        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            System.out.println("Сервер запущен...");
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    String getAnswer = in.readLine();
                    Gson gson = new Gson();
                    Purchase purchase = gson.fromJson(getAnswer, Purchase.class);
                    basket.add(purchase);
                    String str = CountingMaxCategories.getMaxCategory(basket);
                    out.println(str);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}