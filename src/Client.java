import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

public class Client {
	public static void main(String [] args) throws IOException
	{
		//Creates a new socket on localhost on port 3000
		Socket clientSocket = new Socket("Localhost", 3000);
		
		//String that will hold the input from the user (temp and format)
		String convertKey = new String();
		//Creates a new scanner object to receive input from the user
		Scanner keyboard = new Scanner(System.in);
		//Creates a new scanner to get input from the Server
		Scanner input = new Scanner(clientSocket.getInputStream());
		//Creates a new PrintStream to send output to the server
		PrintStream output = new PrintStream(clientSocket.getOutputStream());
		
		//Loops while the user does not input a temperature that is a number (decimal or no)
		//	followed by either an F or C
		//Uses a regular expression that will check for an optional negative, any number of digits,
		//	an optional decimal, followed by optional digits after the decimal, followed by either
		//	an F or C. The regex is compared against the user input.
		while (Pattern.matches("-?[\\d]+\\.?[\\d]*[FC]", convertKey) == false)
		{
			//Asks and receives input from the user
			System.out.println("Enter your temperature followed by either an F or a C (ex: 100F): ");
			convertKey = keyboard.next();
		}
		
		//Sends the user input to the server
		output.println(convertKey);

		//Prints the converted temperature by receiving input from the server
		System.out.println("Your converted temperature is: " + input.nextLine());
		
		//Closes the scanners and the socket to avoid memory leaks
		keyboard.close();
		clientSocket.close();
		input.close();
	}
}
