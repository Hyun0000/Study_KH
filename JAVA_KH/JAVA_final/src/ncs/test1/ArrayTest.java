package ncs.test1;

public class ArrayTest {
	
//============================================================	
	public int sum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		return sum;
	}
//============================================================	
	public double avg(int[] arr) {
		double avg = 0;
		
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		avg = sum/(double)(arr.length);
		
		return avg;
	}
//============================================================
	public int max(int[] arr) {
		int max = 0;
		
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[i] > arr[i+1]) {
					int tmp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = tmp;
				}
			}
		}
		max = arr[arr.length - 1];
		return max;
	}
//============================================================
	public int min(int[] arr) {
		int min = 0;
		
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[i] > arr[i+1]) {
					int tmp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = tmp;
				}
			}
		}
		min = arr[0];
		return min;
	}
//============================================================
	public int evenCount(int[] arr) {
		int evenCount = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				evenCount++;
		 	}
		}
		return evenCount;
	}
//============================================================
	public int oddCount(int[] arr) {
		int oddCount = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) {
				oddCount++;
		 	}
		}
		return oddCount;
	}
//============================================================
	public void print(int[] arr) {
		System.out.print("array : ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
//============================================================
	
	public static void main(String[] args) {
		
		int[] array = new int[10];
		
		ArrayTest arrayTest = new ArrayTest();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = (int)(Math.random() * 100);
		}
		System.out.println("????????? ????????? ?????? : " + array.length);
		//===============?????? ??????===============
		arrayTest.print(array);
		//===============?????? ??? ???===============
		System.out.println("?????? ??? ??? : " + arrayTest.max(array));
		//===============?????? ?????? ???===============
		System.out.println("?????? ?????? ???: " + arrayTest.min(array));
		//===============????????? ??????===============
		System.out.println("????????? ?????? : " + arrayTest.evenCount(array));
		//===============????????? ??????===============
		System.out.println("????????? ?????? : " + arrayTest.oddCount(array));
		//===============??????===============
		System.out.println("?????? : " + arrayTest.sum(array));
		//===============??????===============
		System.out.printf("?????? : " + "%.2f", arrayTest.avg(array));
		System.out.println();
	}
}