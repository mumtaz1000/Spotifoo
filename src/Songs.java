package Spotifoo.src;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Spotifoo.src.Display.displayWarningMsg;
import static Spotifoo.src.Display.songsMenuDisplayOption;
import static Spotifoo.src.FileSystem.readFile;
import static Spotifoo.src.Main.mainMenuSection;
import static Spotifoo.src.Menus.*;
import static Spotifoo.src.User.validateUserInput;


public class Songs {
    protected final List<String> songsAll = readFile(txtFilePath);
    protected final List<String> songsNames = songDetails(songsAll, 0);
    protected static void songMenuOption(){
        //This function attach required song mp3 file name
        //with all mp3 songs folder path string and save it
        //to string that is used as a parameter in playMusic
        //function to play song
        List<String> songToPlay = songsMenuDisplayOption();
        String filename = songFilePath.concat(songToPlay.get(1));
        System.out.println("Song to play from menu " + filename);
        playMusic(songToPlay);
    }

    protected static List<String> songDetails(List<String> listOfSongs, int option){
        //This function is used to separate all the song info and details
        //and save them in individual list by taking option and list of
        //all songs along with their data and returning a list containing
        //the required data for example list containing only songs name
        //requiredData = [song1, song2, song3, ....]
        List<String> requiredData = new ArrayList<>();
        for (String song: listOfSongs) {
            String[] elements = song.split(",");
            List<String> fixedLengthList = Arrays.asList(elements);
            ArrayList<String> listOfString = new ArrayList<>(fixedLengthList);
            requiredData.add(listOfString.get(option));
        }
        return requiredData;
    }
    protected static List<String> getSongToPlay(List<String> songsList){
        List<String> songToPlay = new ArrayList<>();
        List<String> songFile = songDetails(songsList,4);
        List<String> songImg = songDetails(songsList, 5);
        boolean correctSong = false;
        //Taking input from user
        while( !correctSong ) {
            try {
                int opt = validateUserInput(0, songsList.size());
                if (opt == 0) {
                    mainMenuSection();
                } else {
                    opt -= 1;
                    songToPlay.add(songsList.get(opt));
                    songToPlay.add(songFile.get(opt));
                    songToPlay.add(songImg.get(opt));
                    System.out.println("You choose song " + songToPlay.get(0));
                    correctSong = true;}
            } catch (Exception e) {
                System.out.println(displayWarningMsg("Please enter the valid input to play the song "+e));
            }
        }
        return songToPlay;
    }

    protected static void playMusic(List<String> songToPlay){
        //This function is used to open laptop media player and
        //play mp3 file and to show png picture
        System.out.println(songToPlay);
        try{
            Desktop d = Desktop.getDesktop();
            d.open(new File(songFilePath.concat(songToPlay.get(1))));
            System.out.println("Song file path "+songFilePath.concat(songToPlay.get(2)));
            d.open(new File(songImagePath.concat(songToPlay.get(2))));
            System.out.println("Press 0 to go back to main menu.");
        }
        catch(Exception e){
            System.out.println(displayWarningMsg("Error while playing mp3 file "+e));
        }
    }


}
