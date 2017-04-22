import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	public static void main(String [] args) throws IOException
	{
		//Creates a new socket on the port 3000
		ServerSocket newSocket = new ServerSocket(3000);
		//Accepts the connection formed
		Socket socketConnect = newSocket.accept();
		
		//Scanner to receive input from the client
		Scanner input = new Scanner(socketConnect.getInputStream());
		//PrintStream to send output to the client
		PrintStream output = new PrintStream(socketConnect.getOutputStream());
		//String to hold the value of the temperature
		String convertKey = new String();
		//Receives the temperature and F or C from the client
		convertKey = input.nextLine();
		//Stores the format the original temperature is in
		char originFormat = convertKey.charAt(convertKey.length() - 1);
		//Creates a StringBuilder out of the temperature and format string
		StringBuilder mutableKey = new StringBuilder(convertKey);
		//Deletes the F or C at the end of the temperature to allow mathematical operations
		mutableKey.deleteCharAt(mutableKey.length() - 1);
		//Saves the mutated string back into convertKey
		convertKey = mutableKey.toString();
			
		//runs if the original format is in Fahrenheit
		if (originFormat == 'F')
		{
			//Converts the convertKey string into a double
			double firstTemp = Double.parseDouble(convertKey);
			double toCelsius = (firstTemp - 32) / 1.8;
			//After conversion, sends the new temperature and new format back to client
			output.println(toCelsius + "C");
		}
		else
		{
			//Same as above, except from Celsius to Fahrenheit
			double firstTemp = Double.parseDouble(convertKey);
			double toFahrenheit = (firstTemp * 1.8) + 32;
			output.println(toFahrenheit + "F");
		}

		//Closes the socket and input Scanner to avoid memory leaks
		newSocket.close();
		input.close();
	}
}
