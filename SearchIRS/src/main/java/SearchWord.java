
import java.util.Collections;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ΔΕΣΠΟΙΝΑ
 */
public class SearchWord {

    public static String Sword;

    public static String inputword() {
        Scanner user_input = new Scanner(System.in);
        Sword = user_input.next();
        return Sword;
    }

    public static int searchvocab(String sword) {
        sword = Sword;
        int index = Collections.binarySearch(LoadVocab.word, sword);
        if (index < 0) {
            System.out.println("404 Word not found");
            return -1;
        } else {
            return index;
        }
    }
}
