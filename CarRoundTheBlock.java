/*
 *@author Levi Overcast
 *
 *I have included some of my test signatures
 */
import java.util.*;
public class CarRoundTheBlock {
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
		int winner = 0;
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
		
		
		PrintArr( cars);
		for (int j = 0; j < cars.length; j++) {
			System.out.printf("Car #%d has gas enough for %d miles, distance to neighbor %d miles\n",cars[j].getPos() , cars[j].getGas(),cars[j].getDist());
		}
		for (int j = 0 ; j < cars.length-1; j++){
			if (cars[j].getGas() >= cars[j+1].getDist()){
				winner = j;
			}
		}
		System.out.printf("The winner car is car # %d\n",winner);
		DrivingLog(winner, cars);
		/*System.out.println(findWinner(cars));
		for(int i = 0; i < cars.length; i++) {
			System.out.println(findWinner(cars));
			cars = shiftCars(cars);
		}
		*/
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
	
	public static void PrintArr(Car[] cars) {
		int lengthCarList = cars.length;
		System.out.printf("The number of cars on the road %d\n" ,lengthCarList  );
		return;
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
	
	public static void DrivingLog(int winner, Car[] cars){
		int i = 0;
		int start = winner;
		int totalDist = 0;
		boolean race = true;
		Car champ = new Car(cars[winner].getPos(), cars[winner].getGas(), cars[winner].getDist());
		Car[] raceList = cars.clone();
		
		System.out.println("Starting driving log");
		System.out.printf("Begin at position %d gas %d distance %d\n",champ.getPos(), champ.getGas(), champ.getDist());
		totalDist += champ.getDist();
		
		while (race){
			for (i=start +1; i < raceList.length; i++){
				champ.setGas( champ.getGas() - champ.getDist() );
				System.out.printf("At position %d gas %d, distance %d\n", raceList[i].getPos(), raceList[i].getGas(),raceList[i].getDist());
				System.out.printf("Getting %d miles worth of gas now have %d miles worth, starting next leg \n", raceList[i].getGas(), champ.getGas()+raceList[i].getGas());
				champ.setDist(raceList[i].getDist());
				totalDist += raceList[i].getDist();
				champ.setGas(champ.getGas()+ raceList[i].getGas());
				
			}
			for ( i=0 ; i < start; i++ ){
				champ.setGas( champ.getGas() - champ.getDist() );
				System.out.printf("At position %d gas %d, distance %d\n", raceList[i].getPos(), raceList[i].getGas(),raceList[i].getDist());
				System.out.printf("Getting %d miles worth of gas now have %d miles worth, starting next leg \n", raceList[i].getGas(), champ.getGas()+raceList[i].getGas());
				champ.setDist(raceList[i].getDist());
				totalDist += raceList[i].getDist();
				champ.setGas(champ.getGas()+ raceList[i].getGas());
				
			}
			System.out.printf("Finished driving, drove %d miles, gas left %d miles worth\n", totalDist, champ.getGas());
			race = false;
		}
		
		
	}
}