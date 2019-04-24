
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

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
    public static ArrayList<String> foundfiles;

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

    public static ArrayList<String> searchfiles(int index) throws IOException {
        int i = 0;
        ArrayList<String> docs = new ArrayList<>();
        long offsone = LoadVocab.offset.get(index);
        int df = LoadVocab.df.get(index);
        long offstwo = LoadVocab.offset.get(index++);
        RandomAccessFile posting = new RandomAccessFile("src/main/java/CollectionIndex/PostingFile.txt", "r");
        posting.seek(offsone);
        String line;
        System.out.println(df);
        for (i = 0; i < df; i++) {
            
            line = posting.readUTF();
            
            StringTokenizer stringToken = new StringTokenizer(line," ");
            String currentToken=stringToken.nextToken();
            String document ="";
            while(!isNumeric(currentToken)){
                document += currentToken+" ";
                currentToken= stringToken.nextToken();
            }
            docs.add(document);
            
        }
        return docs;
        //posting.seek(offstwo);
        
    }

    
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
}
