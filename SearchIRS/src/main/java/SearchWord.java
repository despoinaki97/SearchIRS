
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

    public static String Query;
    public static ArrayList<String> foundfiles;
    public static int numberOfFiles;
    public static double docNormal;
    
    public static String inputword() {
        System.out.println("Please give topic type:");
        
        Scanner user_input = new Scanner(System.in);
        String topic  = user_input.nextLine();
        System.out.println("Please give summary or description:");
        String sum = user_input.nextLine();
        Query = topic +" "+sum+" "+topic;
        return Query;
    }

    public static int searchvocab(String sword) {
        int index = Collections.binarySearch(LoadVocab.word, sword);
        if (index < 0) {
            return -1;
        } else {
            return index;
        }
    }

    public static HashMap<String,Double> searchfiles(int index,String w) throws IOException {
        int i = 0;
        double total=0;
        HashMap<String,Double> weights=new HashMap<>();
        long offsone = LoadVocab.offset.get(index);
        int df = LoadVocab.df.get(index);
        long offstwo = LoadVocab.offset.get(index++);
        RandomAccessFile posting = new RandomAccessFile("..\\..\\CollectionIndex\\PostingFile.txt", "r");
        RandomAccessFile dFile = new RandomAccessFile("..\\..\\CollectionIndex\\DocumentsFile.txt", "r");
        
        posting.seek(offsone);
        String line;
        for (i = 0; i < df; i++) {
            
            line = posting.readUTF();
            StringTokenizer stringToken = new StringTokenizer(line.trim()," ");
            String currentToken=stringToken.nextToken();
            String document ="";
            while(!isNumeric(currentToken)){
                document += currentToken+" ";
                currentToken= stringToken.nextToken();
            }
            
            double tf=Double.parseDouble(currentToken);
            String positions=stringToken.nextToken();
            long docOffset = Long.parseLong(stringToken.nextToken().trim());
            dFile.seek(docOffset);
            String dNormal=" ";
            String line2 = dFile.readLine();
            
            StringTokenizer documentToken = new StringTokenizer(line2.trim()," ");
            documentToken.nextToken();
            
            while(!isNumeric(dNormal)){
                dNormal = documentToken.nextToken();
            }
            docNormal = Double.parseDouble(dNormal);
            double MaxFreq =Double.parseDouble(documentToken.nextToken());
            
            if(MaxFreq!=0)
                tf = tf/MaxFreq;
            else
                System.err.println("MaxFreq was 0. Sth went really bad");
            double iDF =  numberOfFiles/df;
            
            double weight = tf*log2(iDF);
            
            double qTF=QueryInfo.words.get(w).doubleValue()/ (new Integer(QueryInfo.MaxFreq)).doubleValue();
            double qWeight = qTF*log2(iDF);
            
            double fWeight = qWeight*weight/(QueryInfo.CalculateNormal(df)*docNormal);
            weights.put(document, fWeight);
            
        }
        
        return weights;
        //posting.seek(offstwo);
        
    }

    
     private static double log2(double n)
    {
        return (Math.log(n) / Math.log(2));
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
