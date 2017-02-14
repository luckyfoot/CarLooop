/*
 *@author Levi Overcast
 *
 *I have included some of my test signatures
 */
import java.util.*;
public class CarLoop {
	public static ArrayList <Car> cars;
	
	public static void main(String[] args) {
		
		Scanner amountCars = new Scanner(System.in);
		int carNum = 0;
		
		
		System.out.println("How many cars in the race?");
		carNum = amountCars.nextInt();
		cars = CarRoundTheBlock.FillCarArray(carNum);
		
		for (int i = 0; i < cars.size();i++) {
			printArr("Arr", cars);
			System.out.println("Car " + i + ": " + aroundTheBlock(cars));
			System.out.println();
			cars.add(cars.get(0));
			cars.remove(0);
			
		}
		amountCars.close();
		return;
	}
	
	
/*
 * recursive algorithm:
 * finds if an input array is a winning configuration
 * @param cars, an arraylist of cars ordered st. the first car is the car being
 *  examined for success potential and each subsequent car is the next car in
 *  the cycle
 * 
 */ 
public static boolean aroundTheBlock(ArrayList<Car> cars) {
	//base case: if length of cars is 1, return if the car can make it on its own
	if (cars.size() == 1) {
		//System.out.println(1);
		return (cars.get(0).getGas() >= cars.get(0).getDist());
	}
	
	//recursive case: if length > 1, check if the lead car can make it on its own
	// if it can, merge it with the next car and check the new carsay
	// if it can't, return false
	else {
		//System.out.println(2);
		ArrayList<Car> temp1 = new ArrayList<Car>(1);
		//System.out.println(cars.size());
		temp1.add(cars.get(0));
		if (!(aroundTheBlock(temp1))) {
			//System.out.println("break");
			return false;
		}
		
		ArrayList<Car> temp2 = new ArrayList<Car>();
		for (int i = 0; i < cars.size()-1; i++) {
			if (i == 0) {
				temp2.add(join(cars.get(0),cars.get(1))); 
			}
			else {
				temp2.add(cars.get(i+1));
			}
		}
		//printArr("temp2", temp2);
		return aroundTheBlock(temp2);
	}
	
}
//end aroundTheBlock

/*
 * join method - collapses two cars into one
 * @param Car A - first input car. Car A points at Car B
 * @param Car B - second input car
 * @output Car C - a car with the position of car A and the combined
 *  gas and distance metrics of Cars A and B. 
 */
public static Car join(Car A, Car B) {
	Car C = new Car(A.getPos(), A.getGas() + B.getGas(), A.getDist() + B.getDist());
	return C;
}

/*
 * A visualization method for arr, a potential winning cycle
 * @param String name - the name of arr to be printed
 * @param Car ArrayList arr
 */
public static void printArr(String name, ArrayList<Car> cars) {
	System.out.print(name + ": [");
	for (int i = 0; i < cars.size(); i++) {
		if (i == cars.size()-1) {
			System.out.println(cars.get(i).getPos() + "]");
		}
		else{System.out.print(cars.get(i).getPos() + ",");}
	}

}
}
	


