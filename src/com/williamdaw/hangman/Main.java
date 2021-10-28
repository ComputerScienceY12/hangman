package com.williamdaw.hangman;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Main {
    public static void display_hanging_man_thing(int count) {
        if (count == 1) System.out.println("  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========");
        if (count == 2) System.out.println("  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========");
        if (count == 3) System.out.println("  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========");
        if (count == 4) System.out.println("  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========");
        if (count == 5) System.out.println("  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========");
        if (count == 6) System.out.println("  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========");
        if (count == 7) System.out.println("  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n=========");
    }
    public static void main(String[] args) {
        int mistakes = 0;
        ArrayList<String> guessed_letters = new ArrayList<>();

        String[] list_of_words = {"time", "dog"};

        int amount_words = list_of_words.length;
        int random_number = (int) (Math.random() * (amount_words + 1) - 1);

        String random_word = list_of_words[random_number].toUpperCase();
        char[] random_word_chars = random_word.toCharArray();

        char[] display_word = "-".repeat(random_word.length()).toCharArray();
        boolean playing = TRUE;
        while (playing == TRUE) {
            System.out.println(String.valueOf(display_word));

            String input = "";
            boolean valid_input = FALSE;
            while (valid_input == FALSE) {
                System.out.println("Enter a guess:");
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine().toUpperCase();
                if ((input.length() > 0)&&(!(guessed_letters.contains(input)))) valid_input = TRUE;
            }

            guessed_letters.add(input);
            int occurrences = 0;

            Matcher m = Pattern.compile(input).matcher(random_word);
            while (m.find()) {
                occurrences++;
                int index = m.start();
                System.arraycopy(random_word_chars, index, display_word, index, input.length());
            }
            if (occurrences == 0) display_hanging_man_thing(mistakes++ + 1);
            if (mistakes > 6) System.out.println("You lose");
            if (random_word.equals(String.valueOf(display_word))) System.out.println("You win, the word was " + random_word);
            if (input.equals("MUM")) System.out.println("Your mums kinda leng you know");
            if ((random_word.equals(String.valueOf(display_word)))||(mistakes > 6)) playing = FALSE;
        }
    } 
}
