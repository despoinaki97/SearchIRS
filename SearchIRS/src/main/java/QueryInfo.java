
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
    public static ArrayList<Double> weights;
    public static double Normal;
    public static int MaxFreq;
    static{
        MaxFreq=0;
        words=new HashMap<>();
        weights = new ArrayList<>();
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
    
    public static void CalculateNormal(){
        
    }
}
