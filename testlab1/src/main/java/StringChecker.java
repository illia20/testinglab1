import java.io.*;
import java.util.*;

public class StringChecker {
    private String file;
    private ArrayList<String> words = new ArrayList<>();

    public StringChecker(String file){
        this.file = file;
    }
    public void read(){
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(file)))){
            String line;
            while ((line = bf.readLine()) != null){
                List<String> wordsInLine = Arrays.asList(line.split("[^a-zA-ZА-Яа-яЮюЄєІіЇїЫыЁёЭэъ]", 0));
                for (String word : wordsInLine){
                    String current = beautify(word);
                    if(!correctString(current)){
                        continue;
                    }
                    words.add(current);
                }

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void print(){
        for(String word : words){
            System.out.print(word + " ");
        }
    }
    public boolean correctString(String string){
        if(words.contains(string) || string.isEmpty()){
            return false;
        }
        for (int i = 0; i < string.length() - 1; i++){
            char first = string.charAt(i);
            for (int j = i + 1; j < string.length(); j++){
                if(first == string.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
    public String beautify(String string){
        string = string.trim().toLowerCase();
        if(string.length() > 30){
            string = string.substring(0, 30);
        }
        return string;
    }
    public void clearWords(){
        this.words.clear();
    }
    public void addWord(String string){
        this.words.add(string);
    }
    public void setFile(String string){
        this.file = string;
    }
    public ArrayList<String> arrWords(){
        return this.words;
    }
}