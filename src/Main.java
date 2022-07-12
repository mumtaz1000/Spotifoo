package Spotifoo.src;

import java.awt.*;
import java.io.File;

import static Spotifoo.src.Menus.validateUserInput;
public class Main {

    public static void main(String[] args) {
       mainMenuSection();

    }
    public static void mainMenuSection(){
        /*Main menu section starts here*/
        Menus mainMenuObj = new Menus();
        boolean correctInput = false;
        //This function call is made to display main menu to user
        mainMenuObj.mainMenuDisplayOption();
    while(correctInput != true){
            try{
                int opt = validateUserInput(1,5);
                mainMenuObj.mainMenu(opt);
                correctInput = true;
            }
            catch(Exception e){
                System.out.println(displayWarningMsg("Please enter the valid input"));
            }
}


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
        return (warningTextColor+inputMsg+ANSI_RESET) ;
    }

}
