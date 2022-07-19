package Spotifoo.src;
import static Spotifoo.src.Display.displayWarningMsg;
import static Spotifoo.src.Display.mainMenuDisplayOption;
import static Spotifoo.src.User.validateUserInput;
public class Main {

    public static void main(String[] args) {
       mainMenuSection();
    }

    protected static void mainMenuSection(){
        /*Main menu section starts here*/
        Menus mainMenuObj = new Menus();
        boolean correctInput = false;
        //This function call is made to display main menu to user
        mainMenuDisplayOption();
    while(!correctInput){
            try {
                int opt = validateUserInput(0, 5);
                if(opt == 0)
                {break;} else
                {
                    mainMenuObj.mainMenu(opt);
                    correctInput = true;
                }
            } catch (Exception e) {
                System.out.println(displayWarningMsg("Please enter the valid input"));
            }

}
    }


}
