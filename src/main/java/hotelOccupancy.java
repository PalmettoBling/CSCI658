//  Hotel Suites occupancy
// This program calculates the occupancy rate for a hotel's suites.
// These are located on floors 10-16. There is no 13th floor.
import java.util.Scanner;
import java.text.*;
public class hotelOccupancy
{
public void calcRate()
{
	final int
		SUITES_PER_FLOOR = 20,   // Number of suites per floor
		MIN_FLOOR = 10,          // Lowest floor of suite units
		MAX_FLOOR = 16,          // Highest floor of suite units
		
		TOTAL_SUITES = (MAX_FLOOR - MIN_FLOOR) * SUITES_PER_FLOOR;
	   
	    Scanner scan = new Scanner(System.in);
	    DecimalFormat fmt = new DecimalFormat();

	int occupied,                // Number of occupied suites on the floor
		totalOccupied = 0;       // Total number of occupied suites
	
	double occupancyRate;        // % of the suites that are occupied

	// Get and validate occupancy information for each floor
	System.out.println("Enter the number of occupied suites on each of the following floors.\n");

	for (int floor = MIN_FLOOR; floor <= MAX_FLOOR; floor++)
	{
		if (floor == 13)
     		continue;		  // Skip thirteenth floor
		
		System.out.println("\nFloor " + floor+ ": "); 
		occupied=scan.nextInt();										
		while (occupied < 0 && occupied > SUITES_PER_FLOOR)
		{
			System.out.println("\nThe number of occupied suites must be between 0 and " +  SUITES_PER_FLOOR ); 
			System.out.println("\n Re-enter the number of occupied suites on floor "  + floor + ": ");
			occupied = scan.nextInt();
		}
      
		// Add occupied suites on this floor to the total
		totalOccupied += occupied;
	}
	
	// Compute occupancy rate in % form
	occupancyRate = 100* totalOccupied / TOTAL_SUITES;	

	// Display results
	
	System.out.println("\n\nThe hotel has a total of " + TOTAL_SUITES + " suites.\n");
	System.out.println(totalOccupied+ " are currently occupied.\n");
	System.out.println("This is an occupancy rate of " + fmt.format(occupancyRate)+ "% \n");

	
}
}