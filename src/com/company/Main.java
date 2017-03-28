package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try{
            ConsoleHelper.writeMessage("Input file.txt file location");
            String fileLocation = ConsoleHelper.readLine();
            ConsoleHelper.writeMessage("Input pattern.txt file location");
            String patternLocation = ConsoleHelper.readLine();
            LineMatcher lineMatcher = new LineMatcher(new File(fileLocation), new File(patternLocation));
            lineMatcher.matchPatternExactly(lineMatcher.getFileList(), lineMatcher.getPatternList());
            lineMatcher.matchSomeWhereInLine(lineMatcher.getFileList(), lineMatcher.getPatternList());
            lineMatcher.matchWithinEditDistance(lineMatcher.getFileList(), lineMatcher.getPatternList());

        }
        catch (FileNotFoundException e){
            ConsoleHelper.writeMessage("File not found!");
        }
        catch (IOException e){
            ConsoleHelper.writeMessage("Input error!");
        }
    }
}
/*
2) Write an application that does the following:
Two text files are given:​input.txt​ and ​ patterns.txt​ , where ​ input.txt​is a free-text document
composed of 1 or more lines of text, and ​ patterns.txt​is a set of search strings (1 per line).
Your application should be able to run in one of three different modes:
Required:
1) Output all the lines from ​ input.txt​that match exactly any pattern from ​ patterns.txt
Optional:
2) output all the lines from ​ input.txt​that contain a match from ​ patterns.txt​somewhere in the
line.
3) output all the lines from ​ input.txt​that contain a match within the edit distance of <= 1
patterns.txt

For example:

input.txt​ contains the below:
Hello. This is line 1 of text.
and this is another.
line 3 here
the end

patterns.txt ​contains the below:
the end
matches
line 3
and this is anoother.

Mode 1 outputs:
the end
Mode 2 outputs:
line 3 here
the end
Mode 3 outputs:
and this is another.
the end
 */