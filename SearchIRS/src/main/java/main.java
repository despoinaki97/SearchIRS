/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author ΔΕΣΠΟΙΝΑ
 */
public class main {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        String path = "VocabularyFile.txt";

        try {
            LoadVocab.ReadFile(path);
            //System.out.println(LoadVocab.word);
            //System.out.println(LoadVocab.df);
            //System.out.println(LoadVocab.offset);

            String SW = SearchWord.inputword();
            int index = SearchWord.searchvocab(SW);
            ArrayList<String> filePaths = SearchWord.searchfiles(index);
            for(int i=0;i<filePaths.size();i++){
                System.out.println(filePaths.get(i));
            }
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

    }

}
