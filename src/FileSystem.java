package Spotifoo.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileSystem {
    public static List<String> readFile(String filename) {
        ArrayList<String> listOfSongs = new ArrayList<>();

        try {
            File myFileObj = new File(filename);
            Scanner myReadObj = new Scanner(myFileObj);
            int songNum = 0;
            while (myReadObj.hasNextLine()) {
                String data = myReadObj.nextLine();
                songNum += 1;
                Collections.addAll(listOfSongs,data);
            }
            myReadObj.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred while reading a file. " + e);
            e.printStackTrace();
        }
            return  listOfSongs;
    }

}
