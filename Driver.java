
/**
 * This Driver class will prompt the user to enter candidate strings and prints a message
 * stating whether the entry is a valid floating point number or not
 * @author Ivy P.
 * @version June 6, 2020
 */
import java.util.*; 

public class Driver
{
   public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("\n Enter a floating point number: ");
        String userInput = input.next();
         
        do {
        ValidateFloat floatNumber = new ValidateFloat(userInput);  //create new instance of the ValidateFloat class
        
          if (floatNumber.validateInput()){ // check if input is validate. If yes, print message to the console
             System.out.println("\n -----------------------------------------");
             System.out.println("  " + userInput + " is a valid floating point");
             System.out.println(" -----------------------------------------\n");
          } 
          else { // if not, print error messages with details why it's not valid
             System.out.println("\n -----------------------------------------");
              System.out.println("  " + userInput + " is NOT a valid floating point"  );
             System.out.print(" | *** ERROR *** Your input includes:    |");
             System.out.println(floatNumber.getErrorMessages()); // call the method to get error messages
             System.out.println(" -----------------------------------------\n");
          }
        System.out.print(" Enter a floating point number OR enter \"quit\" to exit the program: "); // accept new input
        userInput = input.next();
      } while (!userInput.equalsIgnoreCase("quit")); // check if the program should continue or terminate base on user's input
      
      System.out.println("\n *** Thank you for using the program! Good bye! ***");
   }//end of main method;
    
}// end of Driver class
