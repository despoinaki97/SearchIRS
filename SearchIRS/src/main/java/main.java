/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 *
 * @author ΔΕΣΠΟΙΝΑ
 */
public class main {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        String path = "VocabularyFile.txt";

        try {
            LoadVocab.ReadFile(path);
            System.out.println(LoadVocab.word);
            //System.out.println(LoadVocab.df);
            //System.out.println(LoadVocab.offset);

        } catch (IOException e) {
            System.err.println("The given file could not be found.");
        }
    }

}
