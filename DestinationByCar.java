public class DestinationByCar extends VacationRecomendation{
   public static final int MIN_MILES = 0;
   public static final int MAX_MILES = 425;
   public static final double IRS_RATE_MILES = 57.5;
   
   private int numberMiles;
   private String[] listOfCars;
   private int numCarDestinations;
   
   public DestinationByCar(String memberName, String phone, String destination /*, String[] activities, double[] activityCosts*/){
      super(memberName, phone, destination /*, activities, activityCosts*/);
      this.listOfCars = new String[MAX_FAMILY_MEMBERS];
      ++numCarDestinations;
   }
   
   public int getNumberMiles() {return this.numberMiles;}
   public String getListOfCar(int index) {
      if(index < 0 || index >= super.getNumMembers()){
         throw new IllegalArgumentException("Car model does not exist in this index");
      }
      return this.listOfCars[index];
   }
   public int getNumCarDestinations() {return this.numCarDestinations;}
   
   public String[] getListOfCars(){
      String[] tempArray = new String[super.getNumMembers()];
      for(int x=0; x < super.getNumMembers(); x++){
         tempArray[x] = this.getListOfCar(x);
      }
      return tempArray;
   } 
   
   public void setNumberMiles(int numberMiles){
      if(numberMiles < MIN_MILES || numberMiles > MAX_MILES){
         throw new IllegalArgumentException("The number of miles must be between "+MIN_MILES+" and "+MAX_MILES);
      }
      this.numberMiles = numberMiles;      
   } 
   
   public void setACar(String carModel){
      if(super.getNumMembers() >= MAX_FAMILY_MEMBERS){
         throw new IllegalArgumentException("The list of cars has been reached");
      }
      if(carModel == null || carModel.equals("")){
         throw new IllegalArgumentException("A car model is required");
      }
      this.listOfCars[super.getNumMembers()] = carModel;
   }
   
   public String toString(){
      String output = "";
          output += super.toString();
          output += "Number of miles: " + this.getNumberMiles() + "\n";
         if(super.getNumMembers() > 0){
            output += "***List of car models***\n";
            for(int x = 0; x < super.getNumMembers(); x++){
               output += "Car list: " + this.getListOfCar(x) + "\n";
            }
         }
         else{
            output += "No car models entered";
         } 
      return output;     
   } 
}