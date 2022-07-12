package Spotifoo.src;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static Spotifoo.src.Main.displayWarningMsg;
import static Spotifoo.src.Main.mainMenuSection;

public class Menus {

    public static String filePath (String filename){
        String file = filename;
        String path = String.valueOf(Paths.get("src/Spotifoo/assets/assets/", file));
        return path;
    }
    public static final String txtFilePath = filePath("data.txt");
    public final String songFilePath = filePath("songs").concat("\\");
    public void mainMenuDisplayOption(){
        System.out.println("Main menu options:");
        System.out.println("[1] Songs");
        System.out.println("[2] Artists");
        System.out.println("[3] Albums");
        System.out.println("[4] Genres");
        System.out.println("[5] Search");
        System.out.println("Press [0] to exit");
        System.out.println("Choose an option and press enter: ");
    }
    public static String songsMenuDisplayOption(){
        System.out.println("Songs display menu");

        int num = 0;
        FileSystem fileObj = new FileSystem();
        List<String> songs = fileObj.readFile(txtFilePath);

        List<String> song = fileObj.songDetails(songs, 0);
        for(String singleSong: song){
            num +=1;
            System.out.println("["+num+"] "+singleSong);
        }
        String userSong = getUserSong(song);
        return userSong;
    }
    private static String getUserSong(List<String> song){
        String choosenSong = new String();
        boolean correctSong = false;
        System.out.println("Enter the song number which you want to play.");
        System.out.println("Press [0] to exit");
        //Taking input from user
        while( correctSong != true ) {

                try {
                    int opt = validateUserInput(0, song.size());
                    if (opt == 0) {
                        mainMenuSection();
                    } else {
                    opt -= 1;
                    choosenSong = song.get(opt);
                    System.out.println("Song size " + song.size());
                    System.out.println("You choose song " + choosenSong);
                    correctSong = true;}
                } catch (Exception e) {
                    System.out.println(displayWarningMsg("Please enter the valid input to play the song "));
                }
            }

        return choosenSong.toLowerCase().replace(' ','-');
    }
    public void artistMenuDisplayOption(){
        System.out.println("This program is for displaying artist names");
    }

    public void mainMenu(int input) throws IOException {
        switch(input) {
            case 0:
                break;
            case 1:
                String songName = songsMenuDisplayOption();
                Main mainObj = new Main();
                String filename = songFilePath;
                System.out.println("Song to play from menu " + filename.concat(songName).concat(".mp3"));
                mainObj.playMusic(filename.concat(songName).concat(".mp3"));
                break;
            case 2:
                artistMenuDisplayOption();
                break;
            case 3:
                System.out.println("Albums");
                break;
            case 4:
                System.out.println("Genres");
                break;
            case 5:
                System.out.println("Search");
                break;
            default:
                System.out.println("Please enter the number between 0 to 5");
                break;
        }
    }
    public static int validateUserInput(int minUserInput,int maxUserInput){
        Scanner menuOption = new Scanner(System.in);
        boolean correctInput = false;
        int input = 0;
        while (correctInput != true){
            input = menuOption.nextInt();
            if(input == 0){break;}
            else {
                if (input >= minUserInput && input <= maxUserInput) {
                    System.out.println(input);
                    correctInput = true;
                } else {
                    System.out.println(displayWarningMsg("Please enter the number between " + minUserInput + " to " + maxUserInput));
                }
            }
        }
        return input;
    }
}
