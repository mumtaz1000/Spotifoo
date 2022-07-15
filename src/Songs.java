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
    protected final List<String> songsAll = readFile(txtFilePath);
    protected final List<String> songsNames = songDetails(songsAll, 0);
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
    protected static List<String> songDetails(List<String> listOfSongs, int option){
        List<String> requiredData = new ArrayList<>();
        for (String song: listOfSongs) {
            String[] elements = song.split(",");
            List<String> fixedLengthList = Arrays.asList(elements);
            ArrayList<String> listOfString = new ArrayList<>(fixedLengthList);
            requiredData.add(listOfString.get(option));
        }
        return requiredData;
    }
    protected static List<String> getSongToPlay(List<String> songsList,List<String> songFile, List<String> songImg){
        List<String> songToPlay = new ArrayList<>();
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
            System.out.println("Press 0 to go back to main menu.");
        }
        catch(Exception e){
            System.out.println(displayWarningMsg("Error while playing mp3 file "+e));
        }
    }
    protected static void displayArtistName(){
        Songs songObj = new Songs();
        List<String> artistName;
        artistName = songDetails(songObj.songsAll,1);
        Collections.sort(artistName);
        List<String> artists;
        artists = displayArtistsOrAlbums(artistName);
        System.out.println("Choose the artist ");
        //Taking input from user
        takingArtistOrAlbumNameAsUserInput(artists);
    }
    private static List<String> displayArtistsOrAlbums(List<String> displayName){
        Collections.sort(displayName);
        List<String> requiredList = new ArrayList<>();
        int num = 0;
        for (String name: displayName) {
            if(!requiredList.contains(name)){
                requiredList.add(name);
            }
        }
        for (String name: requiredList) {
            num +=1;
            System.out.println("["+num+"]"+" "+name);
        }
        return requiredList;
    }
    private static void takingArtistOrAlbumNameAsUserInput(List<String> inputList){
        Songs songObj = new Songs();
        List<String> requiredOutputList = new ArrayList<>();
        List<String> songFile;
        List<String> songImg;
        boolean correct = false;
        while( !correct ) {
            try {
                int opt = validateUserInput(0, inputList.size());
                if (opt == 0) {
                    mainMenuSection();
                } else {
                    opt -= 1;
                    System.out.println("You choose " + inputList.get(opt));
                    int songNum = 0;
                    for (String songName : songObj.songsAll) {
                        if(songName.contains(inputList.get(opt))){
                            songNum += 1;
                            requiredOutputList.add(songName);
                            System.out.println("["+songNum+"] "+songDetails(Collections.singletonList(songName),0));
                            //System.out.println("Song info "+artistsSongsList.get(songNum-1));
                        }
                    }
                    System.out.println("Choose the song to play ");
                    songFile = songDetails(requiredOutputList,4);
                    songImg = songDetails(requiredOutputList, 5);
                    playMusic(getSongToPlay(requiredOutputList,songFile, songImg));
                    correct = true;}
            } catch (Exception e) {
                System.out.println(displayWarningMsg("Please enter the valid input to play the song "));
            }
        }
    }
    protected static void displayIndividualAlbumName(){
        Songs songObj = new Songs();
        List<String> albumName;
        albumName = songDetails(songObj.songsAll,2);
        Collections.sort(albumName);
        List<String> albums;
        albums = displayArtistsOrAlbums(albumName);
        System.out.println("Choose the artist ");
        //Taking input from user
        takingArtistOrAlbumNameAsUserInput(albums);
        System.out.println("Albums");
    }
    protected static void displayIndividualGenre(){
        System.out.println("Genres");
    }


}
