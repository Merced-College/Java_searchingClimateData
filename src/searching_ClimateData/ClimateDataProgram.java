//Kathy Kanemoto
//3.12.23
//class to hold ask questions for the climate change data

package searching_ClimateData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClimateDataProgram {

	public static ArrayList<ClimateDataRecord> records = new ArrayList<ClimateDataRecord>();

	public static void main(String[] args) {
		//reading in data from a text file is "copy and paste" code
		String fileName = "GlobalLandTemperatures_GlobalLandTemperaturesByState.csv";
		//for testing
		//String fileName = "climatDataTestingSubset.csv";
		Scanner inputStream = null;

		try {
			inputStream = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + 
					fileName);
			System.exit(0);
		}

		//read in the header and do nothing with it
		inputStream.nextLine();

		//make an index to index in the array
		while (inputStream.hasNextLine())  {
			records.add(new ClimateDataRecord(inputStream.nextLine()));
		}

		quicksort(records, 0, records.size()-1);//adding the -1 here is important
		
		for(ClimateDataRecord c: records)
			System.out.println(c);
		
		//System.out.println("Searching for record");
		//ClimateDataRecord recordSearching = new ClimateDataRecord("1849-01-01,5.590999999999998,2.405,California,United States");
		//System.out.println(binarySearch(records, recordSearching));
	}//main

	public static void quicksort(ArrayList<ClimateDataRecord> list, int startIndex, int endIndex) {
		// Only attempt to sort the array segment if there are
		// at least 2 elements
		if (endIndex <= startIndex) {
			return;
		}

		// Partition the array segment
		int high = partition(list, startIndex, endIndex);

		// Recursively sort the left segment
		quicksort(list, startIndex, high);

		// Recursively sort the right segment
		quicksort(list, high + 1, endIndex);
	}

	public static int partition(ArrayList<ClimateDataRecord> list, int startIndex, int endIndex) {
		
		
		// Select the middle value as the pivot.
		int midpoint = startIndex + (endIndex - startIndex) / 2;
		ClimateDataRecord pivot = list.get(midpoint);//list[midpoint];

		// "low" and "high" start at the ends of the array segment
		// and move towards each other.
		int low = startIndex;
		int high = endIndex;
		
		//System.out.println("high above while loop" + high);
		//System.out.println("low above while loop" + low);

		boolean done = false;
		while (!done) {
			//System.out.println("high in while loop" + high);
			//System.out.println("low in while loop" + low);
			// Increment low while numbers[low] < pivot
			//while (numbers[low] < pivot) {
			while (list.get(low).compareTo(pivot) > 0) {
				//System.out.println("high in second while loop" + high);
				//System.out.println("low in second while loop" + low);
				low = low + 1;
			}

			// Decrement high while pivot < numbers[high]
			//while (pivot < numbers[high]) { ///had low and not high in parantheses
			while (list.get(high).compareTo(pivot) < 0) {
				//System.out.println("high in third while loop" + high);
				//System.out.println("low in third while loop" + low);
				high = high - 1;
			}

			// If low and high have crossed each other, the loop
			// is done. If not, the elements are swapped, low is
			// incremented and high is decremented.

			if (low >= high) {
				done = true;
			}
			else {
				//int temp = numbers[low];
				ClimateDataRecord temp = list.get(low);
				//numbers[low] = numbers[high];
				//System.out.println("high " + high);
				list.set(low,list.get(high));
				//numbers[high] = temp;
				list.set(high,temp);
				low++;
				high--;
			}
		}

		// "high" is the last index in the left segment.
		return high;
	}
	
	private static ClimateDataRecord binarySearch(ArrayList<ClimateDataRecord> list, ClimateDataRecord key) {
	      // Variables to hold the low, middle and high indices
	      // of the area being searched. Starts with entire range.
	      int low = 0;
	      int mid = list.size() / 2;
	      int high = list.size() - 1;
	   
	      // Loop until "low" passes "high"
	      while (high >= low) {
	         // Calculate the middle index
	         mid = (high + low) / 2;

	         // Cut the range to either the left or right half,
	         // unless numbers[mid] is the key
	         //if (numbers[mid] < key) {
	         if(list.get(low).compareTo(key) < 0) {
	            low = mid + 1;
	         }
	         //else if (numbers[mid] > key) {
	         else if (list.get(low).compareTo(key) > 0) {
	            high = mid - 1;
	         }
	         else {
	            return list.get(mid);
	         }
	      }
	   
	      return null; // not found
	   }
	
	/*
	private static void merge(ArrayList<ClimateDataRecord> list, int i, int j, int k) {
	      int mergedSize = k - i + 1;                // Size of merged partition
	      int[] mergedNumbers = new int[mergedSize]; // Dynamically allocates temporary
	                                                 // array for merged numbers
	      int mergePos = 0;                          // Position to insert merged number
	      int leftPos = i;                           // Initialize left partition position
	      int rightPos = j + 1;                      // Initialize right partition position
	      
	      // Add smallest element from left or right partition to merged numbers
	      while (leftPos <= j && rightPos <= k) {
	         //if (numbers[leftPos] <= numbers[rightPos]) {
	    	 if (list.get(leftPos).compareTo(list.get(rightPos)) > 0) {
	            mergedNumbers[mergePos] = numbers[leftPos];
	            leftPos++;
	         }
	         else {
	            mergedNumbers[mergePos] = numbers[rightPos];
	            rightPos++;
	         }
	         mergePos++;
	      }
	      
	      // If left partition is not empty, add remaining elements to merged numbers
	      while (leftPos <= j) {
	         mergedNumbers[mergePos] = numbers[leftPos];
	         leftPos++;
	         mergePos++;
	      }
	   
	      // If right partition is not empty, add remaining elements to merged numbers
	      while (rightPos <= k) {
	         mergedNumbers[mergePos] = numbers[rightPos];
	         rightPos++;
	         mergePos++;
	      }
	   
	      // Copy merged numbers back to numbers
	      for (mergePos = 0; mergePos < mergedSize; mergePos++) {
	         numbers[i + mergePos] = mergedNumbers[mergePos];
	      }
	   }
	   
	   private static void mergeSort(ArrayList<ClimateDataRecord> list, int i, int k) {
	      int j = 0;
	      
	      if (i < k) {
	         j = (i + k) / 2;  // Find the midpoint in the partition

	         // Recursively sort left and right partitions
	         mergeSort(list, i, j);
	         mergeSort(list, j + 1, k);
	            
	         // Merge left and right partition in sorted order
	         merge(list, i, j, k);
	      }
	   }*/
	
}//class

