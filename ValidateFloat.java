
/**
 * Program to validate a floating point from user input
 * Allow only characters which are digits (0 through 9) 
 * At most one decimal point.
 * At most one occurrence of the letter ‘E’. At most two positive or negative signs.
 * @author Ivy P.
 * @version June 6, 2020
 */
 public class ValidateFloat
 {
   private String input;
   private boolean validEntry;
   private StringBuilder errorMessages;
   
   public ValidateFloat(String str){ // constructor to set initial values for the object
     input = str.toUpperCase();      //convert input characters to Upper Case 
     validEntry = true;              // set the String validity to true until it fail to meet the requirements 
     errorMessages = new StringBuilder();
   }
  
   public boolean validateInput(){  
      /** this method is to validate Floating Point input. It also append error messages (if any) to the errorMessage field.
      *  It will check:
      *  1. 'E' character
      *  2. valid number(s) from 0 - 9
      *  3. decimal point(s)
      *  4. +/- sign
      *  @param None
      *  @return boolean value for valid/invalid String input
      */
     
      errorMessages = errorMessages.append(this.checkE()).append(this.checkNum()).append(this.checkDecimalPoint()).append(this.checkSign()); 
     
      return validEntry;
   }
   
   public StringBuilder getErrorMessages() {
      return errorMessages;
    }
    
   private String checkE() { // this method to check if there are more than 1 'E' in the String OR 'E' appears before the decimal point
       String errorE = "";
       int countE = 0;  // set varible to count number of occurences of 'E' in the string
       int ePos = -1; // set varible to hold index position of 'E' in the string
       
      for (int i = 0; i < input.length(); i++) { // loop thru the string to count 'E'
        if (input.charAt(i) == 'E') {
          ePos = i;  // update index position of 'E'
          countE++;
        }
      }
       
      if (countE > 1) { // check if there are more than 1 'E'
         errorE = "\n |   Too many 'E'                        |";
         validEntry = false;
      }
      // check if 'E' is in the string but there is no decimal point OR 'E' appears before the decimal point 
      // OR 'E' is the last character in the string
      else if (countE == 1 && (input.indexOf('.') == -1 || (input.indexOf('.') != -1 && ePos < input.indexOf('.')) 
                               || (ePos == input.length() - 1))){ 
         errorE = "\n |   Invalid 'E' position                |";
         validEntry = false;
      }
      return errorE;
    }
    
   private String checkNum() { // this method to check valid characters for floating point number besides -/+ sign, 'E', decimal point
       String errorValidNum = "";
       
          for (int i = 0; i < input.length(); i++) {
            
            if (input.charAt(i) == 'E'|| input.charAt(i) == '.' || input.charAt(i) == '-' 
                || input.charAt(i) == '+' || Character.isDigit(input.charAt(i)) ) 
            // skip validation for digit
              continue;
            else { // return error message
              errorValidNum = "\n |   Invalid character(s)                |";
              validEntry = false;
              break;
            }
          }
        return errorValidNum;
   }
   
   private String checkDecimalPoint() { // this method to check for decimal point. Only 1 decimal can be accepted
       String errorDecimalPoint = "";
       int countDecPoint = 0;
        
         for (int i = 0; i < input.length(); i++) { //loop thru the string to find decimal point
            if (input.charAt(i) == '.')
               countDecPoint++;
         }
         
         if (countDecPoint > 1) {
           errorDecimalPoint = "\n |   Too many decimal points             |";
           validEntry = false;
         }
       return errorDecimalPoint;
   }
   
   private String checkSign() { // this method to check for +/- sign. Most can be accepted are 2 and validate the position(s) of the +/- sign in the number
        String errorSign = "";
        int countSign = 0; // variable to count how many +/- sign in the number
        int signPos = 0;   // variable to hold index of the +/- sign.
        
        for (int i = 0; i < input.length(); i++) {
          if (input.charAt(i) == '-' || input.charAt(i) == '+') {
            signPos = i; //assign index of the +/- sign. If there are more than 1 occurence, it will hold the index of the last occurence when the loop ends. (1)
            countSign++; 
          }
        }

        switch (countSign) { // switch statement to check each case of +/- sign(s)
            case 0: break;  // if no +/- sign included, nothing to check here.
            
            case 1: if (input.indexOf('E') == -1 && (signPos != 0)) { //if there is 1 +/- sign & no 'E' then the sign has to be at the start of the number
                     errorSign = "\n |   Invalid -/+ sign position             |";    
                     validEntry = false;
                    }
                    else if (input.indexOf('E') != -1) { // if there is 1 +/- sign and 'E' is present
                              if (signPos == 0) //if the sign at the start of the string, it's good
                              break;
                              // if the sign appears after 'E' & the sign is not the last character of the string then it's good
                              // for example: 89.e- is invalid.
                              else if (signPos == input.indexOf('E') + 1 && signPos != input.length() - 1)
                              break;
                              else { // if the sign is not located at the proper position then it's invalid
                                     errorSign = "\n |   Invalid -/+ sign position           |";    
                                     validEntry = false;
                                   }
                            }
                    break;
                   
            case 2: if (input.indexOf('E') == -1) { // check if there are 2 +/- signs but no 'E' then it's invalid
                     errorSign = "\n |   Too many -/+ signs                  |";    
                     validEntry = false;
                    } 
                    else if (input.indexOf('E') != -1) { 
                            // check if there are 2 +/- signs and e is included 
                            //then the first occurence of +/- has to be at the beginning of the string
                            // for example: 8-90.e-3 is invalid
                              if (input.charAt(0) != '-' && input.charAt(0) != '+' ) {
                                  errorSign = "\n |   Invalid -/+ sign position           |";    
                                  validEntry = false;
                              }
                            // check if the 2nd occurence of +/- sign is right after 'E'. If not, it's invalid
                            // for example: -567.-8e is invalid
                              else if (signPos != input.indexOf('E') + 1) {
                                  errorSign = "\n |   Invalid -/+ sign position           |";    
                                  validEntry = false;
                              }
                            // check if the 2nd occurence of +/- sign is right after 'E' right but there is no more number after the +/- sign.
                            // for example: -567.e- is invalid
                              else if (signPos == input.indexOf('E') + 1 && signPos == input.length() - 1) {
                                  errorSign = "\n |   Invalid -/+ sign position           |";    
                                  validEntry = false;
                              }
                    }
                    break;
                     
            default: errorSign = "\n |   Too many -/+ signs                  |";    
                     validEntry = false;
        }
        return errorSign;
   }
   
} // end of class
