package com.hust.soict.bao.client_server;

import com.hust.soict.bao.helper.SelectionSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Arrays;

public class server {
    public static void main(String[] args) throws IOException {
        System.out.println("The Sorter Server is running!");
        int clientNumber = 0;
        try (ServerSocket listener = new ServerSocket(9898)) {
            while (true) {
                new Sorter(listener.accept(), clientNumber++).start();
            }
        }
    }

    private static class Sorter extends Thread {
        private final Socket socket;
        private final int clientNumber;

        public Sorter(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            System.out.println("New client #" + clientNumber + " connected at " + socket);
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Send a welcome message to the client.
                out.println("Hello, you are client #" + clientNumber);

                // Get messages from the client, line by line; Each line has several numbers separated by a space character
                while (true) {
                    String input = in.readLine();
                    if (input == null || input.isEmpty()) {
                        break;
                    }
                    //Put it in a string array
                    String[] nums = input.split(" ");

                    //Convert this string array to an int array
                    int[] intarr = new int[nums.length];

                    int i = 0;

                    for (String textValue : nums) {
                        intarr[i] = Integer.parseInt(textValue);
                        i++;
                    }

                    //Sort the numbers in this int array
                    new SelectionSort().sort(intarr);
                    //Convert the int array to String
                    String[] strArray = Arrays.stream(intarr).mapToObj(String::valueOf).toArray(String[]::new);

                    //Send the result to Client
                    out.println(Arrays.toString(strArray));
                }
            } catch (IOException e) {
                System.out.println("Error handling client #" + clientNumber);
            } finally {
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
                System.out.println("Connection with client # " + clientNumber + " closed");
            }
        }
    }
}
