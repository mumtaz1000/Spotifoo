import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Songs {
    protected final List<String> songsAll = FileSystem.readFile(Menus.txtFilePath);
    protected final List<String> songsNames = songDetails(songsAll, 0);
    protected static void songMenuOption(){
        List<String> songToPlay = Display.songsMenuDisplayOption();
        String filename = Menus.songFilePath.concat(songToPlay.get(1));
        System.out.println("Song to play from menu " + filename);
        playMusic(songToPlay);
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


    protected static List<String> songDetails(String songString){
        List<String> requiredData = new ArrayList<>();
        String[] parts = songString.split(",");
        requiredData.add(parts[0]);
        requiredData.add(parts[4]);
        requiredData.add(parts[5]);
        return requiredData;
    }

    protected static List<String> getSongToPlay(List<String> songsList,List<String> songFile, List<String> songImg){
        List<String> songToPlay = new ArrayList<>();
        boolean correctSong = false;
        //Taking input from user
        while( !correctSong ) {
            try {
                int opt = User.validateUserInput(0, songsList.size());
                if (opt == 0) {
                    Main.mainMenuSection();
                } else {
                    opt -= 1;
                    songToPlay.add(songsList.get(opt));
                    songToPlay.add(songFile.get(opt));
                    songToPlay.add(songImg.get(opt));
                    System.out.println("You choose song " + songToPlay.get(0));
                    correctSong = true;}
            } catch (Exception e) {
                System.out.println(Display.displayWarningMsg("Please enter the valid input to play the song "));
            }
        }
        return songToPlay;
    }

    protected static void playMusic(List<String> songToPlay){
        try{
            Desktop d = Desktop.getDesktop();
            d.open(new File(Menus.songFilePath.concat(songToPlay.get(1))));
            System.out.println("Song file path "+ Menus.songFilePath.concat(songToPlay.get(2)));
            if(songToPlay.get(2).equals("no file")){
                d.open(new File(Menus.noPicturePath));
            } else {
                d.open(new File(Menus.songImagePath.concat(songToPlay.get(2))));
            }

            //System.out.println("Press 0 to go back to main menu.");
        }
        catch(Exception e){
            System.out.println(Display.displayWarningMsg("Error while playing mp3 file "+e));
        }
    }

    protected static List<String> displayArtistsOrAlbumsOrGenre(List<String> displayName){
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
        System.out.println("Press 0 to go back to main menu.");
        return requiredList;
    }
    protected static void takingArtistOrAlbumNameOrGenreAsUserInput(List<String> inputList){
        Songs songObj = new Songs();
        List<String> requiredOutputList = new ArrayList<>();
        List<String> songFile;
        List<String> songImg;
        boolean correct = false;
        while( !correct ) {
            try {
                int opt = User.validateUserInput(0, inputList.size());
                if (opt == 0) {
                    Main.mainMenuSection();
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
                System.out.println(Display.displayWarningMsg("Please enter the valid input to play the song "));
            }
        }
    }



}
