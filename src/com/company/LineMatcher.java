package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unix on 3/24/17.
 */
public class LineMatcher {
    private List<String> fileList;
    private List<String> patternList;

    public LineMatcher(File inputFileName, File patternFileName) throws IOException {

        this.fileList = Files.readAllLines(inputFileName.toPath());
        this.patternList = Files.readAllLines(patternFileName.toPath());
    }

    public List<String> getFileList() {
        return fileList;
    }

    public List<String> getPatternList() {
        return patternList;
    }

    public  void matchPatternExactly(List<String> files, List<String> patterns){
        ConsoleHelper.writeMessage("---Match line exactly any pattern from patterns: ");
        for(String file: files){
            patterns.stream().filter(pattern -> file.equals(pattern)).forEach(pattern -> ConsoleHelper.writeMessage(file));
        }
    }
    public  void matchSomeWhereInLine(List<String> files, List<String> patterns){
        ConsoleHelper.writeMessage("---Match from patterns somewhere in the line: ");
        for(String file: files){
            for(String pattern: patterns){
                if (file.matches("(.*)" + pattern + "(.*)" ))
                    ConsoleHelper.writeMessage(file);
            }
        }
    }
    public  void matchWithinEditDistance(List<String> files, List<String> patterns){
        ConsoleHelper.writeMessage("---Match within the edit distance of <= 1 from patterns: ");
        for(String file: files){
            for(String pattern: newPatternVariants(patterns)){
                if(file.equals(pattern))
                    ConsoleHelper.writeMessage("\t" + file);
            }
        }

    }
    public static List<String> newPatternVariants(List<String> patterns){
        String newPattern = "";
        List<String> newPatternList = new ArrayList<>();
        for(String pattern: patterns) {
            newPatternList.add(pattern);
            char[] patternLineArray = pattern.toCharArray();
            for (int k = 0; k < patternLineArray.length; k++)
            {

                for (int j = 0; j < patternLineArray.length; j++)
                {
                    if(k==j) continue;
                    else newPattern = newPattern + patternLineArray[j];
                }
                newPatternList.add(newPattern);
                newPattern="";
            }
        }
        int count = 0;
        for (int i = 0; i < newPatternList.size(); i++) {
            for (int j = 0; j < newPatternList.size(); j++) {
                if(newPatternList.get(i).equals(newPatternList.get(j))) {
                    count = count + 1;
                    if (count==2) newPatternList.remove(i);
                }
            }count = 0;
        }
        return newPatternList;
    }
}
