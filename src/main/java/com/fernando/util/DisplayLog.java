package com.fernando.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DisplayLog {
     
    private String fileOutput;
 
    public String getFileOutput() {
        return fileOutput;
    }
     
    public String display(String fName) {
        try {
            String fileName = fName;
            String txtFilePath = fileName;
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(txtFilePath));
            //BufferedReader br = new InputStreamReader(new FileInputStream(txtFilePath));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = reader.readLine())!= null){
                sb.append(line+"\n");
            }
            this.fileOutput = sb.toString();
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileOutput;
    }
 
}