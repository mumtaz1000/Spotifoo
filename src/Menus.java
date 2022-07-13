package Spotifoo.src;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static Spotifoo.src.Main.*;
import static Spotifoo.src.Songs.*;

public class Menus {

    public static String filePath (String filename){
        return String.valueOf(Paths.get("src/Spotifoo/assets/assets/", filename));
    }
    public static final String txtFilePath = filePath("data.txt");
    public static final String songFilePath = filePath("songs").concat("\\");
    public static final String songImagePath = filePath("albums").concat("\\");
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
    protected static List<String> songsMenuDisplayOption(){
        System.out.println("Songs display menu");
        displayIndividualSongName();
        //Taking input from user
        return getSongToPlay();
    }

    public void artistMenuDisplayOption(){

        System.out.println("This program is for displaying artist names");
    }

    public void mainMenu(int input){
        switch(input) {
            case 0:
                break;
            case 1:
                songMenuOption();
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
        while (!correctInput){
            input = menuOption.nextInt();
            if(input == 0){
                mainMenuSection();
            } else {
                if (input > minUserInput && input <= maxUserInput) {
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
