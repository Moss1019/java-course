package com.mossonthetree;

public class Program {
    public static void main(String[] args) {
        String input = getInput();
        // Start here
    }

    private static String getInput() {
        try {
            byte[] buffer = new byte[1024];
            int read = System.in.read(buffer);
            return new String(buffer, 0, read - 1);
        } catch (Exception ex) {
            System.out.println("What is that, " + ex.getMessage());
            return "";
        }
    }
}
