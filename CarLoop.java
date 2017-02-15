/*
 *@author Levi Overcast
 *
 *I have included some of my test signatures
 */
import java.util.*;
public class CarLoop {
	public static void main(String[] args) {
		//Car A = new Car(0,10,25);
		//Car B = new Car(1,50,25);
		//Car C = new Car(2,20,25);
		//Car D = new Car(3,20,25);
		//ArrayList<Car> arr = new ArrayList<Car>();
		//arr.add(A);
		//arr.add(B);
		//arr.add(C);
		//arr.add(D);
		String in_str;
		int in_int;
		int temp = 0;
		int count = 0;
		int[] data = {};
		Scanner scnr = new Scanner(System.in);
		System.out.print("Enter data: ");
		do {
			
			in_str = scnr.next();
			in_int = Integer.parseInt(in_str);
			count++;
			temp = in_int;
			if (!(in_int == -1)) {data = add_to_array(data, in_int);}
			
		} while (in_int != -1);
		
		Car [] cars = new Car [data.length/2];
		for (int i = 0; i < data.length; i++) {
			if (i%2 == 0) {temp = data[i];}
			if (i%2 == 1) {cars[(i+1)/2-1] = new Car((i+1)/2-1, temp, data[i]);}
		}
		
		
		//for (int i = 0; i < arr.size();i++) {
		//	printArr("Arr", arr);
			//System.out.println("Car " + i + ": " + findWinner(arr));
			//System.out.println();
			//arr.add(arr.get(0));
			//arr.remove(0);
			
		//}
		
		
		printArr("Cars", cars);
		for (int j = 0; j < cars.length; j++) {
			System.out.println(cars[j].getPos() + " " + cars[j].getGas() + " " + cars[j].getDist());
		}
		
		//System.out.println(findWinner(cars));
		for(int i = 0; i < cars.length; i++) {
			System.out.println(findWinner(cars));
			cars = shiftCars(cars);
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
	public static boolean findWinner(Car[] arr) {
		//base case: if length of arr is 1, return if the car can make it on its own
		if (arr.length == 1) {
			//System.out.println(1);
			return (arr[0].getGas() >= arr[0].getDist());
		}
		
		//recursive case: if length > 1, check if the lead car can make it on its own
		// if it can, merge it with the next car and check the new array
		// if it can't, return false
		else {
			//System.out.println(2);
			Car[] temp1 = new Car[1];
			//System.out.println(arr.size());
			temp1[0] = arr[0];
			if (!(findWinner(temp1))) {
				//System.out.println("break");
				return false;
			}
			
			Car[] temp2 = new Car[0];
			for (int i = 0; i < arr.length-1; i++) {
				if (i == 0) {
					temp2 = add_to_car_array(temp2, join(arr[0],arr[1])); 
				}
				else {
					temp2 = add_to_car_array(temp2, arr[i+1]);
				}
			}
			//printArr("temp2", temp2);
			return findWinner(temp2);
			
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
	 * A visualization method for arr, a potential winning cycle. Ex. print : "Arr: [1,2,3,4,5]\n"
	 * @param String name - the name of arr to be printed
	 * @param Car ArrayList arr
	 */
	
	public static void printArr(String name, Car[] cars) {
		System.out.print(name + ": [");
		for (int i = 0; i < cars.length; i++) {
			if (i == cars.length-1) {
				System.out.println(cars[i].getPos() + "]");
			}
			else{System.out.print(cars[i].getPos() + ",");}
		}
	}
	
	public static void printArrList(String name, ArrayList<Car> arr) {
		System.out.print(name + ": [");
		for (int i = 0; i < arr.size(); i++) {
			if (i == arr.size()-1) {
				System.out.println(arr.get(i).getPos() + "]");
			}
			else{System.out.print(arr.get(i).getPos() + ",");}
		}
	}
	
	public static int[] add_to_array(int[] data, int new_item) {
		int[] new_data = new int[data.length + 1];
		for (int i = 0; i < new_data.length; i++) {
			if (i == data.length) {new_data[i] = new_item;}
			else {new_data[i] = data[i];}
		}
		return new_data;
	}
	
	public static Car[] add_to_car_array(Car[] cars, Car new_item) {
		Car[] new_Cars = new Car[cars.length + 1];
		for (int i = 0; i < new_Cars.length; i++) {
			if (i == cars.length) {new_Cars[i] = new_item;}
			else {new_Cars[i] = cars[i];}
		}
		return new_Cars;
	}
	
	public static Car[] shiftCars(Car[] cars) {
		Car[] newCars = new Car[cars.length];
		for (int i = 1; i < cars.length; i++) {
			newCars[i-1] = cars[i];
		}
		newCars[cars.length-1] = cars[0];
		return newCars;
		
	}
	
}
