import javax.swing.JOptionPane;
public class RetirementVacation{
   public static void main(String[] args){
      VacationRecomendation[] recomendations = new VacationRecomendation[VacationRecomendation.MAX_FAMILY_MEMBERS];
      VacationRecomendation oneRecommendation;
      
      int menuChoice = getMenu();
      while (menuChoice != 5){
         switch(menuChoice){
            case 1:
               if(VacationRecomendation.getNumMembers() > VacationRecomendation.MAX_FAMILY_MEMBERS){
                  JOptionPane.showMessageDialog(null, "The limit of recommendations has been reached");
               }
               else{
                  try{
                     oneRecommendation = getNewRecommendation();
                     populateRecommendation(oneRecommendation);
                     recomendations[VacationRecomendation.getNumMembers() -1] = oneRecommendation;
                  }
                  catch (NumberFormatException e) {
                     JOptionPane.showMessageDialog(null, "You did not enter a number. Please try again");
                  }               
                  catch(IllegalArgumentException e){
                     JOptionPane.showMessageDialog(null, "Child could not be created\n" + e.getMessage());
                  }                  
               }      
               break;
            case 2:
               printAllRecommendations(recomendations, VacationRecomendation.getNumMembers());
               break;
            case 3:
               //printBudgetRecommendation(recomendations, VacationRecomendation.getNumMembers());
               break;
            case 4:
               //printExtravagantRecommendation(recomendations, VacationRecomendation.getNumMembers());
               break;
               
            default:
               throw new RuntimeException("Unknown error in the menu");
         } 
         menuChoice = getMenu(); 
      }
      JOptionPane.showMessageDialog(null, "Thank you for using this program!");
 
   }
   
   public static void printAllRecommendations(VacationRecomendation[] recomendations, int numMembers){
      if(numMembers > 0){
         String report = "";
         double total = 0;
         for(int x = 0; x < numMembers; x++){
            report += "[" + recomendations[x].getClass().getName() +"]\n" + recomendations[x].toString() + "\n";
         } 
         JOptionPane.showMessageDialog(null, report);  
      }
      else{
         JOptionPane.showMessageDialog(null, "There are no recommendations");
      }
   }
   
   public static void populateRecommendation(VacationRecomendation oneRecommendation){
      //Determine which additional questions to ask
      if(oneRecommendation instanceof DestinationByAir){
         boolean ticketCostSet = false;
         do{
            try{
               //downcast required to get to setTicketCost method
               ((DestinationByAir)oneRecommendation).setTicketCost(Double.parseDouble(JOptionPane.showInputDialog("Enter plane ticket cost: ")));
               ticketCostSet = true;
            }
            catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, "Invalid ticket amount! Please enter a value greater than "+ DestinationByAir.MIN_COST);
            }
         }while(!ticketCostSet);
      }
      
      if(oneRecommendation instanceof DestinationByCar){
         int index = 0;
         boolean numberMilesSet = false;
         do{
            try{ 
               //downcast required to get to setNumberMiles method
               ((DestinationByCar)oneRecommendation).setNumberMiles(Integer.parseInt(JOptionPane.showInputDialog("Enter the number of miles: ")));
               numberMilesSet = true;
            }
            catch(NumberFormatException e){
               JOptionPane.showMessageDialog(null, "Miles entered must be as a number");
            }
            catch(IllegalArgumentException e){
               JOptionPane.showMessageDialog(null, e.getMessage());
            }
         }while(!numberMilesSet); 
         
         if(index < VacationRecomendation.MAX_FAMILY_MEMBERS){
            boolean carSet = false;
            do{
               try{
                  //downcast required to get to setACar method
                  ((DestinationByCar)oneRecommendation).setACar(JOptionPane.showInputDialog("Enter the car model! do you want to recommend: "));
                  carSet = true;
               }
               catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, "Car model could not be added.\n" + e.getMessage());
               }
            }while((!carSet) || (JOptionPane.showConfirmDialog(null, "Enter another car model?")==JOptionPane.YES_OPTION));
         }
         else{
            JOptionPane.showMessageDialog(null, "You have reach the limit of entering car models");
         }     
      }
      
      
   } 
   
   public static VacationRecomendation getNewRecommendation(){
      VacationRecomendation oneRecommendation;
      
      Object[] options = {"Destination by Air", "Destination by Car"};
      int destinationType = JOptionPane.showOptionDialog(null, "What type of recommendation for the destination are you sugesting?",
         "Create Recommendation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      
      String memberName = answerString("Enter the family member's name: ");
      String phone = answerString("Enter the phone number: ");
      String destination = answerString("Enter the destination family menber sugested: ");
     /* String activities = "";
      double activityCosts = 0.0;
      for(int x = 0; x < MAX_ACTIVITIES; x++){
         
      }*/
     
      switch (destinationType) {
         case 0: //Destination by Air
            oneRecommendation = new DestinationByAir(memberName, phone, destination);
            boolean question; 
            do{
               question = answerYesNo("Do you want to add and activity?");
               if(question){
                  addActivity(oneRecommendation);
               }
               else{
                  JOptionPane.showMessageDialog(null, "At this time you must privide an activity");
               }
            }while(!question);   
            break; 
         case 1: //Destination by Car
            oneRecommendation = new DestinationByCar(memberName, phone, destination);
            boolean question2;
            do{
               question2 = answerYesNo("Do you want to add and activity?");
               if(question2){
                  addActivity(oneRecommendation);
               }
               else{
                  JOptionPane.showMessageDialog(null, "At this time you must privide an activity");
               }
            }while(!question2);             
            break;  
         default:
            oneRecommendation = null;
            break;                  
      }
      return oneRecommendation;
   }
   
   public static void addActivity(VacationRecomendation oneRecommendation){
     // String enterAnotherActivity;
      //int index = 0;
      do{
            //if(index < VacationRecomendation.MAX_ACTIVITIES){
               try{
                  oneRecommendation.setAnActivity(
                     JOptionPane.showInputDialog("Enter an activity recommendation"),
                     Double.parseDouble(JOptionPane.showInputDialog("Enter the cost for the activity"))
                  );
               }
               catch(NumberFormatException e){
                  JOptionPane.showMessageDialog(null, "Cost must be numeric");
               }
               catch(IllegalArgumentException e){
                  JOptionPane.showMessageDialog(null, "Activity could not be addes.\n" + e.getMessage());
               }
               //index++;
            //}
            //else{
              // JOptionPane.showMessageDialog(null, "You have reach the limit of activities");
            //}
            //enterAnotherActivity = JOptionPane.showInputDialog("Would you like to enter another Activity? Enter (Y) to continue!");                        
      //}while(enterAnotherActivity.equalsIgnoreCase("Y") /*&& index < VacationRecomendation.MAX_ACTIVITIES*/);  
      }while(JOptionPane.showConfirmDialog(null, "Enter another activity?")==JOptionPane.YES_OPTION);
   }
   
   public static boolean answerYesNo(String prompt){
      return JOptionPane.showConfirmDialog(null, prompt)==JOptionPane.YES_OPTION;
   }
   
   public static String answerString(String prompt){
      String answerString = "";
      do{
         answerString = JOptionPane.showInputDialog(prompt);
         if(answerString.trim().equals("")){
            JOptionPane.showMessageDialog(null, "Please enter a value");
         }
      }while(answerString.trim().equals(""));
      return answerString;
   }
   
   public static int getMenu() {
      int menuChoice;
      do{
         try{
            menuChoice = Integer.parseInt(JOptionPane.showInputDialog(
               "Enter one option of this selection:" +
               "\n 1) Add Recommendation " +
               "\n 2) Print All Recommendations" + 
               "\n 3) Print Budget Recommendation \n     (Recommendation with the lowest cost) " + 
               "\n 4) Print Extravagant Recommendation \n     (Recommendation with the highest cost) " + 
               "\n 5) Quit"            
            ));
         }
         catch(NumberFormatException e){
            menuChoice = 0;
         }
         if(menuChoice < 1 || menuChoice > 5){
            JOptionPane.showMessageDialog(null, "Please enter a valid menu choice selection");
         }
      }while(menuChoice < 1 || menuChoice > 5);
      
      return menuChoice;   
   }   
   
}
