/*
 *@author Levi Overcast
 *
 *I have included some of my test signatures
 */
import java.util.*;
public class CarLoop {
	public static void main(String[] args) {
		Car A = new Car(0,10,25);
		Car B = new Car(1,50,25);
		Car C = new Car(2,20,25);
		Car D = new Car(3,20,25);
		ArrayList<Car> arr = new ArrayList<Car>(4);
		arr.add(A);
		arr.add(B);
		arr.add(C);
		arr.add(D);
		
		
		for (int i = 0; i < arr.size();i++) {
			printArr("Arr", arr);
			System.out.println("Car " + i + ": " + aroundTheBlock(arr));
			System.out.println();
			arr.add(arr.get(0));
			arr.remove(0);
			
		}	
		return;
	}
	
	/*
	 * recursive algorithm:
	 * finds if an input array is a winning configuration
	 * @param arr, an arraylist of cars ordered st. the first car is the car being
	 *  examined for success potential and each subsequent car is the next car in
	 *  the cycle
	 * 
	 */ 
	public static boolean aroundTheBlock(ArrayList<Car> arr) {
		//base case: if length of arr is 1, return if the car can make it on its own
		if (arr.size() == 1) {
			//System.out.println(1);
			return (arr.get(0).getGas() >= arr.get(0).getDist());
		}
		
		//recursive case: if length > 1, check if the lead car can make it on its own
		// if it can, merge it with the next car and check the new array
		// if it can't, return false
		else {
			//System.out.println(2);
			ArrayList<Car> temp1 = new ArrayList<Car>(1);
			//System.out.println(arr.size());
			temp1.add(arr.get(0));
			if (!(aroundTheBlock(temp1))) {
				//System.out.println("break");
				return false;
			}
			
			ArrayList<Car> temp2 = new ArrayList<Car>();
			for (int i = 0; i < arr.size()-1; i++) {
				if (i == 0) {
					temp2.add(join(arr.get(0),arr.get(1))); 
				}
				else {
					temp2.add(arr.get(i+1));
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
	public static void printArr(String name, ArrayList<Car> arr) {
		System.out.print(name + ": [");
		for (int i = 0; i < arr.size(); i++) {
			if (i == arr.size()-1) {
				System.out.println(arr.get(i).getPos() + "]");
			}
			else{System.out.print(arr.get(i).getPos() + ",");}
		}
	}
	
}
