//package Spotifoo.src;

public class Main {

    public static void main(String[] args) {
       mainMenuSection();
    }

    protected static void mainMenuSection(){
        /*Main menu section starts here*/
        Menus mainMenuObj = new Menus();
        boolean correctInput = false;
        //This function call is made to display main menu to user
        Display.mainMenuDisplayOption();
    while(!correctInput){
            try {
                int opt = User.validateUserInput(0, 5);
                if(opt == 0)
                {break;} else
                {
                    mainMenuObj.mainMenu(opt);
                    correctInput = true;
                }
            } catch (Exception e) {
                System.out.println(Display.displayWarningMsg("Please enter the valid input"));
            }

}
    }


}
