/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gr.uoc.csd.hy463.Topic;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.util.Pair;


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
        String folderNameRes = "..\\..\\Results\\";
        File folderF = new File(folderNameRes);
        if(!folderF.exists())
            folderF.mkdir();
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(folderNameRes+"Results.txt"));
            
        Map<Integer,Map<String,Double>> allScores = new HashMap<>();
        try {
            
            LoadVocab.ReadFile(path);
            Parser.InitStopwords();
            ArrayList<Topic> topics=null;
            try{
                topics = TopicsQueries.readtopics();
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
            for(int q=0;q<topics.size();q++){
                System.out.println("TopicNO:"+ q);
                String tSum=topics.get(q).getSummary();
                String tType=topics.get(q).getType().toString();
                String query = tSum;
                Parser.tokenize(query);
                
                ArrayList<HashMap<String,Double>> scores = new ArrayList<>();
                HashMap<String,Double> fScores= new HashMap<>();
                
                for(String w: QueryInfo.words.keySet()){
                    int index = SearchWord.searchvocab(w);
                    if(index>-1){
                        scores.add(SearchWord.searchfiles(index, w));
                    }else{
                        scores.add(new HashMap<String,Double>());
                    }
                }
                
                for(int i=0;i<scores.size();i++){
                    if(scores.get(i).isEmpty()) continue;
                    for(String k : scores.get(i).keySet()){
                        double finalScore=scores.get(i).get(k);
                        for(int j=i+1;j<scores.size();j++){
                            for(String k2 : scores.get(j).keySet()){
                                if(k2.compareTo(k)==0 ){
                                    finalScore = finalScore+scores.get(j).get(k2);

                                }
                            }
                        }
                        if(!fScores.containsKey(k)&&finalScore>0)
                            fScores.put(k, finalScore);
                    }
                }
                QueryInfo.words.clear();
                allScores.put(q, fScores);
            }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            
            for(int i=0;i<30;i++){
                if(allScores.get(i).isEmpty()){
                    allScores.remove(i);
                }
            }
            ArrayList<myfileEntry> allScores_sorted = new ArrayList<>();
            while(!allScores.isEmpty()){
                double max=0;
                int maxi=0;
                String maxk="";
                
                double temp=0;
                
                for(Integer i: allScores.keySet()){
                    for(String k: allScores.get(i).keySet()){
                        temp= allScores.get(i).get(k);
                        if(temp>max){
                            max=temp;
                            maxi = i;
                            maxk = k;
                        }
                    }
                }
                myfileEntry fE;
                fE = new myfileEntry(maxi,maxk,max);
                allScores_sorted.add(fE);
                
               
                allScores.get(maxi).remove(maxk,max);
                if(allScores.get(maxi).isEmpty()){
                    allScores.remove(maxi);
                }   
                
            }
            
            
            for(int i=0;i<allScores_sorted.size();i++){
                try {
                    writer.append(allScores_sorted.get(i).topiq_no+1+" ");
                    writer.append("0"+" ");
                    String pcmid = allScores_sorted.get(i).name;
                    pcmid=pcmid.subSequence(0, pcmid.length()-6).toString();
                    StringTokenizer tokens = new StringTokenizer(pcmid,"\\ ");
                    String fpcmid = pcmid;
                    while(tokens.hasMoreTokens()) fpcmid = tokens.nextToken();
                    writer.append(fpcmid+" ");
                    writer.append(i+1+" ");
                    writer.append(String.format("%.5f", allScores_sorted.get(i).fscore));
                    
                    writer.append("\n");
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            QueryInfo.Clear();
             
            writer.close();
        
        
        
    }

}
