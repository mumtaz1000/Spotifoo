package Spotifoo.src;
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

}/*Main menu section ends here*/
    }

    protected static String displayWarningMsg(String inputMsg){
        String warningTextColor = "\u001B[41m";
        String ANSI_RESET = "\u001B[0m";
        return (warningTextColor+inputMsg+ANSI_RESET) ;
    }

}
