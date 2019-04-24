
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import mitos.stemmer.Stemmer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George K
 */
public class Parser {
    public static ArrayList<String> stopwords;
    
    static{
        stopwords = new ArrayList<>();
    }
    
    public static ArrayList<String> InitStopwords() throws IOException {
        
        File stopwordsFile = new File("..\\..\\Resources\\stopwords\\stopwordsEn.txt");
        BufferedReader br = new BufferedReader(new FileReader(stopwordsFile));
        String swen="";
        int i = 0;
        
        while ((swen = br.readLine()) != null) {
            //System.out.println(swen);
            stopwords.add(swen);
            //    System.out.println(stopwords.get(i));
            i++;
        }
        
        stopwordsFile = new File("..\\..\\Resources\\stopwords\\stopwordsGr.txt");
        br = new BufferedReader(new FileReader(stopwordsFile));
        
        while ((swen = br.readLine()) != null) {
            //System.out.println(swen);
            stopwords.add(swen);
            //    System.out.println(stopwords.get(i));
            i++;
        }
        
        return stopwords;
    }

    
    public static void tokenize(String line) {
        String delimiter = "\t\n\r\f ";
        int i = 0;
        StringTokenizer tokenizer = new StringTokenizer(line, delimiter);
        while (tokenizer.hasMoreTokens()) {
            String currentToken = tokenizer.nextToken();
            
            String CurrT = "";
            if(!SearchWord.isNumeric(currentToken))
                CurrT = currentToken.replaceAll("\\p{Punct}", "");
            else CurrT = currentToken;
            
            if(!stopwords.contains(CurrT)){

                CurrT= Stemmer.Stem(CurrT);
                QueryInfo.AddWord(CurrT);
                
            }
            i++;
        }
        
    }
    
   
}
