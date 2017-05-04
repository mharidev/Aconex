
/**
 * Created by meghanaharidev
 */

package com.aconex.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Common {

    public static final String DELIMITER = "\\s+";

    public static final String EMPTY_STRING = "";

    public static final int ROOT_LEVEL = -1;

    public static final String ROOT_TAG = "gedcom";

    public static final String INDENT = "    ";

    public static final String LEVEL_PATTERN = "^[+\\-]?\\d+$";

    // Tags are 3 or 4 letter words
    public static final String TAG_PATTERN = "[a-zA-Z]{3,4}+";

    public static final String RESOURCES_ROOT = "resources/";

    public static boolean fileCompare(String file1, String file2) throws IOException {

        // Used to compare two files
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));

        String strLine;

        while((strLine = reader1.readLine()) != null)
        {
            if(!strLine.equals(reader2.readLine())) {
                return false;
            }
        }

        if(reader2.readLine() != null) {
            return false;
        }

        return true;
    }
}
