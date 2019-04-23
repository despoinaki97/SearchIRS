
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.*;
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
public class LoadVocab {

    public static ArrayList<String> word;
    public static ArrayList<Integer> df;
    public static ArrayList<Long> offset;

    LoadVocab() {
        word = new ArrayList<>();
        df = new ArrayList<>();
        offset = new ArrayList<>();

    }

    public static void ReadFile(String path) throws IOException {
        LoadVocab LV = new LoadVocab();
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            int count = tokenizer.countTokens();
            String one = tokenizer.nextToken();
            word.add(one);
            String two = tokenizer.nextToken();
            int docf = Integer.parseInt(two);
            df.add(docf);
            if(count>2){
                String thr = tokenizer.nextToken();
                long offs = Long.parseLong(thr);
                offset.add(offs);
            }else{
                long zero=0;
                offset.add(zero);
            }         


        }
    }

}
