/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
        String path = "..\\..\\CollectionIndex\\VocabularyFile.txt";
        String Dpath = "..\\..\\CollectionIndex\\DocumentsFile.txt";
        int fileCounter=0;
        
        try{
            File DFile = new File(Dpath);
            FileReader fr = new FileReader(DFile);
            BufferedReader br = new BufferedReader(fr);
            while (br.readLine()!= null) fileCounter++;
            SearchWord.numberOfFiles = fileCounter;
        }catch(IOException e){
            
        }
        
        try {
            LoadVocab.ReadFile(path);
            //System.out.println(LoadVocab.word);
            //System.out.println(LoadVocab.df);
            //System.out.println(LoadVocab.offset);
            Parser.InitStopwords();
            String Query = SearchWord.inputword();
            Parser.tokenize(Query);
            //QueryInfo.
            ArrayList<Double> normals = new ArrayList<>();
            
            for(String w: QueryInfo.words.keySet()){
                int index = SearchWord.searchvocab(w);
                //    normals.add(SearchWord.searchfiles(index,w));
            }
            System.out.println(normals);
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

    }

}
