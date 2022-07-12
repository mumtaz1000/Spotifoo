package Spotifoo.src;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       mainMenuSection();

    }
    public static void mainMenuSection(){
        /*Main menu section starts here*/
        Menus mainMenuObj = new Menus();
        Scanner menuOption = new Scanner(System.in);

        //This function call is made to display main menu to user
        mainMenuObj.mainMenuDisplayOption();

        //Taking input from user
        int opt = 1;
        while( opt != 0) {
        try{
            opt = menuOption.nextInt();
            if(opt > 0 && opt < 6)
            {System.out.println(opt);
            mainMenuObj.mainMenu(opt);}
            else {
                System.out.println(displayWarningMsg("Please enter the number between 0 to 5"));
                mainMenuSection();
            }
        } catch (Exception e){
            System.out.println(displayWarningMsg("Please enter the valid input"));
            mainMenuSection();
        }}
        /*Main menu section ends here*/
    }
    public void playMusic(String filename){
            try{
                Desktop d = Desktop.getDesktop();
                d.open(new File(filename));
            }
            catch(Exception e){
                System.out.println(displayWarningMsg("Cannot play mp3 file."));
            }
    }
    public static String displayWarningMsg(String inputMsg){
        String warningTextColor = "\u001B[41m";
        String ANSI_RESET = "\u001B[0m";
        String warningMsg = warningTextColor+inputMsg+ANSI_RESET ;
        return warningMsg;
    }

}
