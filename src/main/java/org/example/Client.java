package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;

public class Client {
    public static void main(String[] args) throws IOException, ParseException {
        String host = "127.0.0.1";
        int port = 8989;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            Purchase purchase = new Purchase("колбаса", "2023.03.25", 500);
            Gson gson = new Gson();
            String purchaseToJson = gson.toJson(purchase);

            out.println(purchaseToJson);


            String getMaxCategory = in.readLine();
            System.out.println("Максимальная по абсолютным тратам категория за весь период: " + getMaxCategory);

        }
    }
}