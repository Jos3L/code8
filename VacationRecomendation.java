public abstract class VacationRecomendation{
   public static final int MAX_FAMILY_MEMBERS = 13;
   public static final int MIN_ACTIVITY = 1;
   public static final int MAX_ACTIVITY = 5;
   public static final double MIN_ACTIVITY_COST = 0;   
   public static final double MAX_ACTIVITY_COST = 25;
   public static final int MAX_ACTIVITIES = 5; 
   
   private String memberName;
   private String phone;
   private String destination;
   private String[] activities;
   private double[] activityCosts; 
   private int numActivities;
   private static int numMembers;
   
   public VacationRecomendation(String memberName, String phone, String destination){
      if(memberName == null || memberName.equals("")){
         throw new IllegalArgumentException("You must provide a family member's name");
      }
      if(!validPhone(phone)){
         throw new IllegalArgumentException("The format of a valid phone is: 7774445555");
      }
      if(destination == null || destination.equals("")){
         throw new IllegalArgumentException("Destination of the vacation is required");
      }
      this.activities = new String[MAX_ACTIVITIES];
      this.activityCosts = new double[MAX_ACTIVITIES];
      this.memberName = memberName;
      this.phone = phone;
      this.destination =  destination;
      ++numMembers;
   }
   
   /*public VacationRecomendation(String memberName, String phone, String destination, String[] activities, double[] activityCosts){
      this(memberName, phone, destination);
      if(activities == null){
         throw new IllegalArgumentException("Array of activities must be provided");
      }
      if(activities.length > MAX_FAMILY_MEMBERS){
         throw new IllegalArgumentException("There is only room for "+MAX_FAMILY_MEMBERS+" activities");
      }
      if(activityCosts == null){
         throw new IllegalArgumentException("Array of activities must be provided");
      }
      if(activityCosts.length > MAX_FAMILY_MEMBERS){
         throw new IllegalArgumentException("There is only room for "+MAX_FAMILY_MEMBERS+" activities");
      }
      //Copy parameter array to object's acitivities array
      for(int x=0; x < activities.length; x++){
         if(activities[x] == null || activities[x].equals("")){
            throw new IllegalArgumentException("Array must be provided");
         }
         this.activities[x] = activities[x];
      }
      this.numActivities = activities.length;  
      //Copy parameter array to object's acitivityCosts array
      for(int x=0; x < activityCosts.length; x++){
         if(activityCosts[x] < MIN_ACTIVITY_COST || activityCosts[x] > MAX_ACTIVITY_COST){
            throw new IllegalArgumentException("Array must be provided");
         }
         this.activityCosts[x] = activityCosts[x];
      }
      this.numActivities = activityCosts.length;           
   }*/
   
   public String getMemberName() {return this.memberName;}
   public String getPhone() {return this.phone;}
   public String getDestination() {return this.destination;}
   
   public String getActivity(int index) {
      if(index < 0 || index >= this.getNumActivities()){
         throw new IllegalArgumentException("Activity does not exist at this index");
      }
      return this.activities[index]; 
   }
   
   public double getActivityCost(int index) {
      if(index < 0 || index >= this.getNumActivities()){
         throw new IllegalArgumentException("The activity cost does not exist at this index");
      }
      return this.activityCosts[index];
   }
   public int getNumActivities() {return this.numActivities;}
   public static int getNumMembers() {return numMembers;}
   
   public String[] getActivities() {
      String[] tempArray = new String[this.getNumActivities()];
      for(int x = 0; x < this.getNumActivities(); x++){
         tempArray[x] = this.getActivity(x);
      }
      return tempArray;
   }
   
   public double[] getActivityCosts(){
      double[] tempArray = new double[this.getNumActivities()];
      for(int x = 0; x < this.getNumActivities(); x++){
         tempArray[x] = this.getActivityCost(x);
      }
      return tempArray;
   }
   
   public void setMemberName(String memberName){
      if(memberName == null || memberName.equals("")){
         throw new IllegalArgumentException("You must provide a family member's name");
      }
      this.memberName = memberName;   
   }
   
   public void setPhone(String phone){
      if(!validPhone(phone)){
         throw new IllegalArgumentException("The format of a valid phone is: 7774445555");
      }
      this.phone = phone;   
   }
   
   public void setDestination(String destination){
      if(destination == null || destination.equals("")){
         throw new IllegalArgumentException("Destination of the vacation is required");
      }
      this.destination = destination;   
   }
   
   public void setAnActivity(String activity, double cost){
      if(this.numActivities >= MAX_ACTIVITIES){
         throw new IllegalArgumentException("The limit of activities per member has been reached");
      }
      if(activity == null || activity.equals("")){
         throw new IllegalArgumentException("An activity sugestion is required");
      }
      if(cost < MIN_ACTIVITY_COST){
         throw new IllegalArgumentException("Activity cost must be a positive number");      
      }
      if(cost > MAX_ACTIVITY_COST){
         throw new IllegalArgumentException("Activity cost cannot exceed "+MAX_ACTIVITY_COST);            
      }
      this.activities[numActivities] = activity;
      this.activityCosts[numActivities++] = cost;
   }
   
   public boolean validPhone(String phone){
      boolean validNumber = true;//change to false
      return validNumber;  
   }
   
   public String toString(){
      String output = "Member name: " + this.getMemberName() + "\n";
      output += "Phone number: " + this.getPhone() + "\n";
      output+= "Sugested destination: " + this.getDestination() + "\n";
      if(this.getNumActivities() > 0){
         output += "***Activities and costs***\n";
         for(int x = 0; x < this.getNumActivities(); x++){
            output += "Activity: " + this.getActivity(x) + " With a cost of: " + this.getActivityCost(x) + "\n";
         }
      }
      else{
         output += "No activities entered";
      }
      return output;
   }
}