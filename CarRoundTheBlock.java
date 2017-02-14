/**
 * Car round the block class will create and fill an array with cars using the user input on the amount of cars to use
 * 
 * The array of cars will then be output to the terminal 
 * The find the winner method will determine the winner or return no winner if no car can win
 * 
 * @author scoob
 *
 */
import java.util.Random;
import java.util.ArrayList;

public class CarRoundTheBlock {
	
	
	public static ArrayList<Car> FillCarArray(int numCars){
		/**
		 *FillCarrArray take a int numcar to create a array of car objects equal to numcars
		 *these cars will have random numbers used to fill the car parameters 	
		 *@param ranGas = random number for gas 
		 *@param int ranDist = Random distance between the cars
		 *@param counter used to determine car position
		 */
		int i=0;
		int ranGas =0;
		int ranDist = 0;
		Random ranNum = new Random();
		ArrayList<Car> carsList;
		carsList = new ArrayList<Car>(numCars);
				
		for (i = 0 ; i < numCars; i++){
			ranGas= ranNum.nextInt(50);
			ranDist = ranNum.nextInt(25);
			carsList.add(new Car(i, ranGas, ranDist));
			}
		for (i = 0 ; i <numCars; i++){
			System.out.println(carsList.get(i).getDist() + " " + carsList.get(i).getGas() +  " " + carsList.get(i).getPos());
		}
		return carsList;
		
		
	}
}