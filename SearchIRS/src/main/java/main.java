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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;


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
            ArrayList<HashMap<String,Double>> scores = new ArrayList<>();
            HashMap<String,Double> fScores= new HashMap<>();
            for(String w: QueryInfo.words.keySet()){
                int index = SearchWord.searchvocab(w);
                scores.add(SearchWord.searchfiles(index,w));
                
            }
            
            for(int i=0;i<scores.size();i++){
                for(String k : scores.get(i).keySet()){
                    double finalScore=scores.get(i).get(k);
                    for(int j=i+1;j<scores.size();j++){
                        for(String k2 : scores.get(j).keySet()){
                            if(k2.compareTo(k)==0 ){
                                finalScore = finalScore+scores.get(j).get(k2);
                                
                            }
                        }
                    }
                    if(!fScores.containsKey(k))
                        fScores.put(k, finalScore);
                }
            }
            
            Map<String,Double> fScores_sorted = fScores.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                    Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                    LinkedHashMap::new));
            
            
            fScores_sorted.keySet().forEach((k) -> {
                System.out.print(k+"  Scores: ");
                System.out.printf("%.3f", fScores.get(k));
                System.out.println();
                
            });
        } catch (IOException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

    }

}
