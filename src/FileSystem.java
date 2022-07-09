package spotifoo.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileSystem {
    public List<String> readFile(String filename) {
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
    public List songDetails(List<String> listOfSongs, int option){
        List<String> requiredData = new ArrayList<>();
        for (String song: listOfSongs) {
            System.out.println("List of songs ");
            String[] elements = song.split(",");
            List<String> fixedLengthList = Arrays.asList(elements);
            ArrayList<String> listOfString = new ArrayList<String>(fixedLengthList);
           // System.out.println("Song details separated by commas "+listOfString.get(0));
            requiredData.add(listOfString.get(0));
        }
        return requiredData;

    }
}
