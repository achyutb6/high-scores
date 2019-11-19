/*Written by Achyut Bhandiwad for CS6326.001, assignment 5,starting October 21, 2018
 * Net ID: aab180004
 * This is the File I/O class
 * This class handles the read and write functions to the file
 */

package me.achyutbhandiwad.highscores;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FileIO {

    Context context;
    /*Name of the text file to store the scores*/
    private static final String FILE_NAME="scores.txt";
    public FileIO(Context context) {
        this.context = context;
    }
    /*THis method is to read the file line by line and return array list of strings */
    public List<Score> readFile(){
        List<Score> data=new ArrayList<>();

        FileInputStream is;
        BufferedReader reader;
        String path = context.getFilesDir()+"/"+FILE_NAME;
        final File file = new File(path);
        /*Try catch for checking whether the file exits*/
        try {
            if (file.exists()) {
                is = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(is));
                /*Read a line and add each line of the file to an array list*/
                String line = reader.readLine();
                while (line != null) {
                    String[] lineArray = line.split("\t");
                    Score score = new Score(lineArray[0],lineArray[1],lineArray[2]);
                    data.add(score);
                    line = reader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    /*This method takes a String of the total lines and writes to a file every time*/
    public boolean writeFile(List<Score> data){
        String path = context.getFilesDir()+"/"+FILE_NAME;
        System.out.println(path);
        File file = new File(path);
        /*If file doesn't exist create one*/
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*Write the entire file every time*/
        try {
            FileWriter fw = new FileWriter(file);
            for(Score score: data){
                fw.append(score.toString()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
