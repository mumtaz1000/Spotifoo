package novarepotential.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileSystem {
    public void readFile(String filename) {
        try {
            File myFileObj = new File(filename);
            Scanner myReadObj = new Scanner(myFileObj);
            int songNum = 0;
            ArrayList<String> listOfSongs = new ArrayList<>();
            while (myReadObj.hasNextLine()) {
                String data = myReadObj.nextLine();
                songNum += 1;
                System.out.println("[" + songNum + "] " + data);
                //String[] songs = data.split(",");
                Collections.addAll(listOfSongs,data);
            }
            myReadObj.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred while reading a file. " + e);
            e.printStackTrace();
        }

    }
}
