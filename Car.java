import java.util.*;
/**
*@author Levi Overcast, Antonio Solorio
* 
*
*
*/
public class Car {
   private int pos;
   private int gas;
   private int dist;

   public Car(int pos, int gas, int dist) {
      this.pos = pos;
      this.gas = gas;
      this.dist = dist;
   }

   public int getPos() {
      return this.pos;
   }
   public int getGas() {
      return this.gas;
   }
   public int getDist() {
      return this.dist;
   }

   public String toString(){
      String toStr = "";
      toStr += String.format("Car position: %d; ",this.pos);
      toStr += String.format("gas: %d; ", this.gas);
      toStr += String.format("distance: %d.", this.dist);
      return toStr;
   }

}
