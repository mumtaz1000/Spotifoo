package Spotifoo.src;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.*;

import static Spotifoo.src.FileSystem.readFile;
import static Spotifoo.src.Main.displayWarningMsg;
import static Spotifoo.src.Main.mainMenuSection;
import static Spotifoo.src.Menus.*;
import static Spotifoo.src.User.validateUserInput;


public class Songs {
    private static final List<String> songs = readFile(txtFilePath);
    protected final List<String> song = songDetails(songs, 0);
    protected static void songMenuOption(){
        List<String> songToPlay = songsMenuDisplayOption();
        String filename = songFilePath.concat(songToPlay.get(1));
        System.out.println("Song to play from menu " + filename);
        playMusic(songToPlay);
    }
    protected static void displayIndividualSongElement(List<String> list){
        int num = 0;
        for(String singleSong: list){
            num +=1;
            System.out.println("["+num+"] "+singleSong);
        }
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
        List<String> songFile = new ArrayList<>();
        songFile = songDetails(songs,4);
        List<String> songImg = new ArrayList<>();
        songImg = songDetails(songs, 5);
        Songs songObj = new Songs();
        boolean correctSong = false;
        //Taking input from user
        while( !correctSong ) {
            try {
                int opt = validateUserInput(0, songObj.song.size());
                if (opt == 0) {
                    mainMenuSection();
                } else {
                    opt -= 1;
                    songToPlay.add(songObj.song.get(opt));
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
    protected static void displayArtistName(){
        List<String> artistName = new ArrayList<>();
        artistName = songDetails(songs,1);
        Collections.sort(artistName);
        Set<String> artists = new LinkedHashSet<>();
        int num = 0;
        for (String name: artistName) {
            artists.add(name);
        }
        for (String name: artists) {
            num +=1;
            System.out.println("["+num+"]"+" "+name);
        }
    }
    protected static void displayIndividualAlbumName(){
        System.out.println("Albums");
    }
    protected static void displayIndividualGenre(){
        System.out.println("Genres");
    }
}
