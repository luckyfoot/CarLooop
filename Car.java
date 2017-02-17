/**
*@author Levi Overcast, Antonio Solorio
* 
*Car class will define the method to create a car object
*Each car object will have a value for:
*	- Distance to next car
*	- Amount of gas
*	- Position in the loop
*
*A getter for position, gas and distance
*
*toString will return a the information on the car
*
*/
public class Car {
	/**
	 * Private variables to be contained within the car object passes by the CarRoundTheBlock method
	 * @param pos = Cars position in the loop
	 * @param gas = random amount of gas
	 * @param dist = random distance from next car
	 * 
	 * 
	 */
   private int pos;
   private int gas;
   private int dist;

   public Car(int pos, int gas, int dist) {
      this.pos = pos;
      this.gas = gas;
      this.dist = dist;
      return;
   }
   
// Getters and setters for car object
   public int getPos() {
      return this.pos;
   }
   public void setPos(int pos){
	   this.pos = pos;
	   return;
   }
   public int getGas() {
      return this.gas;
   }
   public void setGas(int gas){
	   this.gas = gas;
	   return;
   }
   public int getDist() {
      return this.dist;
   }
   public void setDist(int dist){
	   this.dist = dist;
	   return;
   }

   public String toString(){
      String toStr = "";
      toStr += String.format("Car position: %d; ",this.pos);
      toStr += String.format("gas: %d; ", this.gas);
      toStr += String.format("distance: %d.", this.dist);
      return toStr;
   }

}
