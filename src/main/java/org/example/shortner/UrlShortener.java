package org.example.shortner;

public class UrlShortener {
    private static final char[] BASE52 =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Letters + digits → for remaining characters
    private static final char[] BASE62 =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private static final int BASE52_LEN = BASE52.length; // 52
    private static final int BASE62_LEN = BASE62.length; // 62

    // Secret key for scrambling (keep private)
    private static final long SECRET_KEY = 983497527459489345L;

    public static String encode(long id) {
        long scrambled = scramble(id);// use xor of key and id
        System.out.println("Scrambled:" + scrambled);

        char[] result = new char[7];

        // First character (Base52, no digits)
        int firstIndex = (int) (Math.abs(scrambled) % BASE52_LEN);
        result[0] = BASE52[firstIndex];
        System.out.println(result[0] + " - first char");

        // Remaining characters (Base62)
        long remaining = Math.abs(scrambled) / BASE52_LEN;
        System.out.println("remaining - " + remaining);

        for (int i = 6; i >= 1; i--) {
            result[i] = BASE62[(int) (remaining % BASE62_LEN)];
            System.out.println("result- " + result[i]);
            remaining /= BASE62_LEN;

        }
        return new String(result);
    }

    private static long scramble(long id) {
        // XOR removes sequential patterns but is reversible
        return id ^ SECRET_KEY;
    }

    public static class Main {
        public static void main(String[] args) {
            long id = System.currentTimeMillis();
            System.out.println(id + " -> " + UrlShortener.encode(id));
        }
    }

}

