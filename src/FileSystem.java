package novarepotential.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileSystem {
    public ArrayList<String> readFile(String filename) {
        ArrayList<String> listOfSongs = new ArrayList<>();
        ArrayList<String> songName = new ArrayList<>();
        try {
            File myFileObj = new File(filename);
            Scanner myReadObj = new Scanner(myFileObj);
            int songNum = 0;
            while (myReadObj.hasNextLine()) {
                String data = myReadObj.nextLine();
                songNum += 1;
                Collections.addAll(listOfSongs,data);
            }
            for(String item : listOfSongs) {
                String singleSong = item;
                String[] singleElements = singleSong.split(",");
                List<String> songList = Arrays.asList(singleElements);

                //System.out.println("Song List "+songList);
                ArrayList<String> songDetails = new ArrayList<String>(songList);
                songName.add(songDetails.get(0));
            }
            myReadObj.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred while reading a file. " + e);
            e.printStackTrace();
        }
            return songName;
    }
}
