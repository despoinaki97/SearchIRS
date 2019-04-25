
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George K
 */
public class QueryInfo {
    public static Map<String,Integer> words;
    public static int MaxFreq;
    static{
        MaxFreq=0;
        words=new HashMap<>();
    }
    
    public static void AddWord(String w){
        if(words.containsKey(w)){
            words.replace(w, words.get(w)+1);
            if(words.get(w)>MaxFreq){
                MaxFreq = words.get(w);
            }
        }else{
            words.put(w, 1);
            if(MaxFreq<1) MaxFreq=1;
        }
    }
    
    public static double CalculateNormal(int df){
        double total=0;
        for (Integer k : words.values()){
            double tf= k.doubleValue()/(double)MaxFreq;
            double iDF = (double)SearchWord.numberOfFiles /(double)df;
            iDF = Log2(iDF);
            total= total+Math.pow(iDF*tf, 2); 
        }
        return Math.sqrt(total);
        
    }
    
    private static double Log2(double n){
        return (Math.log(n) / Math.log(2));
    }
}
