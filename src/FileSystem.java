package novarepotential.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileSystem {
    public void readFile(String filename) {
        try {
            File myFileObj = new File(filename);
            Scanner myReadObj = new Scanner(myFileObj);
            int songNum = 0;
            while (myReadObj.hasNextLine()) {
                String data = myReadObj.nextLine();
                songNum +=1;
                System.out.println("["+songNum+"] " +data);
                String[] songs = data.split(",");
                //System.out.println("Song: " +songs);
                //System.out.println("Size of array is "+songs.length);
                // step two : convert String array to list of String
                List<String> songsList = Arrays.asList(songs);
                // step three : copy fixed list to an ArrayList
                ArrayList<String> listOfSongs = new ArrayList<String>(songsList);
                //System.out.println("list from comma separated String : " + listOfSongs);
                //System.out.println("size of ArrayList : " + listOfSongs.size());
            }
            myReadObj.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred while reading a file. " + e);
            e.printStackTrace();
        }
    }
}
