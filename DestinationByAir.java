public class DestinationByAir extends VacationRecomendation{
   public static final double MIN_COST = 79.00;
   public static final double MAX_COST = 419.00;
   
   private double ticketCost;
   private int numAirDestinations;
   
   public DestinationByAir(String memberName, String phone, String destination /*, String[] activities, double[] activityCosts*/){
      super(memberName, phone, destination /*, activities, activityCosts*/);
      ++numAirDestinations;
   }
   
   public double getTicketCost() {return this.ticketCost;}
   public int getNumAriDestinations() {return this.numAirDestinations;}
   
   public void setTicketCost(double ticketCost){
      if(ticketCost < MIN_COST || ticketCost > MAX_COST){
         throw new IllegalArgumentException("The ticket's cost must be between "+MIN_COST+" and "+MAX_COST);
      }
      this.ticketCost = ticketCost;
   }
   
   public String toString(){
      return super.toString()
         +"Air ticket cost: "+ this.getTicketCost()+"\n";
   }
   
}