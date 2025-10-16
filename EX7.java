package SAMPLE;
import java.util.Scanner;
public class EX7 {

	public static void main(String[] args) {
		        Scanner sc = new Scanner(System.in);
		        try {
		        	System.out.println("PRASANNADEVI M");
		            System.out.println("2117240070228");
		            System.out.print("Enter numerator: ");
		            int num = sc.nextInt();
		            System.out.print("Enter denominator: ");
		            int den = sc.nextInt();
		            // Try division
		            int result = num / den;
		            System.out.println("Result: " + result);
		        } catch (ArithmeticException e) {
		            // Handle divide by zero
		            System.out.println("Exception handled: Cannot divide by zero!");
		        }
		        sc.close();
		    }
	}


