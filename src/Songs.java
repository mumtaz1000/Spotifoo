package Spotifoo.src;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Spotifoo.src.FileSystem.readFile;
import static Spotifoo.src.Main.*;
import static Spotifoo.src.Menus.*;

public class Songs {
    private static final List<String> songs = readFile(txtFilePath);
    private static final List<String> song = songDetails(songs, 0);
    protected static void songMenuOption(){
        List<String> songToPlay = songsMenuDisplayOption();
        String filename = songFilePath.concat(songToPlay.get(1));
        System.out.println("Song to play from menu " + filename);
        playMusic(songToPlay);
    }
    protected static void displayIndividualSongName(){
        int num = 0;
        for(String singleSong: song){
            num +=1;
            System.out.println("["+num+"] "+singleSong);
        }
        System.out.println("Enter the song number which you want to play.");
        System.out.println("Press [0] to exit");
    }
    protected static List songDetails(List<String> listOfSongs, int option){
        List<String> requiredData = new ArrayList<>();
        for (String song: listOfSongs) {
            String[] elements = song.split(",");
            List<String> fixedLengthList = Arrays.asList(elements);
            ArrayList<String> listOfString = new ArrayList<>(fixedLengthList);
            // System.out.println("Song details separated by commas "+listOfString.get(0));
            requiredData.add(listOfString.get(option));
        }
        return requiredData;
    }
    protected static List<String> getSongToPlay(){
        List<String> songToPlay = new ArrayList<>();
        List<String> songFile = songDetails(songs,4);
        List<String> songImg = songDetails(songs, 5);
        boolean correctSong = false;
        //Taking input from user
        while( !correctSong ) {
            try {
                int opt = validateUserInput(0, song.size());
                if (opt == 0) {
                    mainMenuSection();
                } else {
                    opt -= 1;
                    songToPlay.add(song.get(opt));
                    songToPlay.add(songFile.get(opt));
                    songToPlay.add(songImg.get(opt));
                    System.out.println("You choose song " + songToPlay.get(0));
                    correctSong = true;}
            } catch (Exception e) {
                System.out.println(displayWarningMsg("Please enter the valid input to play the song "));
            }
        }
        return songToPlay;
    }
    protected static void playMusic(List<String> songToPlay){
        try{
            Desktop d = Desktop.getDesktop();
            d.open(new File(songFilePath.concat(songToPlay.get(1))));
            System.out.println("Song file path "+songFilePath.concat(songToPlay.get(2)));
            d.open(new File(songImagePath.concat(songToPlay.get(2))));
        }
        catch(Exception e){
            System.out.println(displayWarningMsg("Error while playing mp3 file "+e));
        }
    }
}
