package myVelibCLUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UserLoop {
	//When you loop, you read the line entered by the user
	//The first word is used as the command
	//Then you split the line to obtain the parameters and their number
	//Regarding to the number of parameters, you deduce what the user want to do
	//You parse every parameter, and send error if parsing don't work
	//You call the right function
	
	
	//Exit Command
	
   public static final String EXIT_COMMAND = "exit";
   public static final int NBR_PARAM_EXIT = 0;
   public static final String DESCRIPTION_EXIT =
		"exit : Exit the program\r\n"+"\n";
   
   //Help Command
   
   public static final String HELP_COMMAND = "help";
   public static final int NBR_PARAM_HELP = 0;
   public static final String DESCRIPTION_HELP = 
		"help : Call the help screen\r\n"+"\n";
   
   //Setup Command
   
   public static final String SETUP_COMMAND = "setup";
   
   public static final int NBR1_PARAM_SETUP = 1;
   public static final String DESCRIPTION1_SETUP = 
		"	setup <velibNetworkName>: to create a myVelib network with given name and\r\n" + 
   		"	consisting of 10 stations each of which has 10 parking slots and such that stations\r\n" + 
   		"	are arranged on a square grid whose of side 4km and initially populated with a 75%\r\n" + 
   		"	bikes randomly distributed over the 10 stations\r\n";
   
   public static final int NBR2_PARAM_SETUP = 5;
   public static final String DESCRIPTION2_SETUP = 
   "	setup <name> <nstations> <nslots> <sidearea> <nbikes>: to create a myVelib net-\r\n" +
   "	work with given name and consisting of nstations stations each of which has nslots\r\n" +
   "	parking slots and such that stations are arranged on a square grid whose of side\r\n" +
   "	sidearea and initially populated with a nbikes bikes randomly distributed over the\r\n" +
   "	nstations stations\r\n";
   
   public static final String DESCRIPTION_SETUP = 
		   "setup : used to create a myVelib network\r\n"+"\n" + DESCRIPTION1_SETUP + DESCRIPTION2_SETUP+"\n";
   
   //addUser Command
   
   public static final String ADDUSER_COMMAND = "addUser";
   
   public static final int NBR1_PARAM_ADDUSER = 3;
   public static final String DESCRIPTION1_ADDUSER =
		   "	addUser <userName,cardType, velibnetworkName> : to add a user with name\r\n" +
		   "	userName and card cardType (i.e. ``none'' if the user has no card) to a myVelib net-\r\n" +
		   "	work velibnetworkName\r\n";
   
   public static final String DESCRIPTION_ADDUSER = 
		   "addUser : used to add an user\r\n"+"\n" + DESCRIPTION1_ADDUSER +"\n";
   
   //Offline Command
   
   public static final String OFFLINE_COMMAND = "offline";
   
   public static final int NBR1_PARAM_OFFLINE = 2;
   public static final String DESCRIPTION1_OFFLINE =
		   "	offline <velibnetworkName, stationID> : to put offline the station stationID\r\n"+
		   "	of the myVelib network velibnetworkName\r\n";
   
   public static final String DESCRIPTION_OFFLINE = 
		   "offline : used to put offline station"+"\n" + DESCRIPTION1_OFFLINE +"\n";
   
	//Online Command
   
   public static final String ONLINE_COMMAND = "online";

   public static final int NBR1_PARAM_ONLINE = 2;
   public static final String DESCRIPTION1_ONLINE =
		   "	online <velibnetworkName, stationID> : to put online the station stationID\r\n"+
		   "	of the myVelib network velibnetworkName\r\n";
   
   public static final String DESCRIPTION_ONLINE  = 
		   "online : used to put online station"+"\n" + DESCRIPTION1_ONLINE +"\n";
  
   //RentBike Command
   
   public static final String RENTBIKE_COMMAND = "rentBike";

   public static final int NBR1_PARAM_RENTBIKE = 3;
   public static final String DESCRIPTION1_RENTBIKE =
		   "	rentBike <userID, stationID,bycicleType> : to let the user userID renting a bike from station\r\n"+
		   "	stationID from the given type (if no bikes are available should behave accordingly)\r\n";
   
   public static final String DESCRIPTION_RENTBIKE = 
		   "rentBike : used to rent a bike"+"\n" + DESCRIPTION1_RENTBIKE +"\n";
   
   //ReturnBike Command
   
   public static final String RETURNBIKE_COMMAND = "returnBike";

   public static final int NBR1_PARAM_RETURNBIKE = 3;
   public static final String DESCRIPTION1_RETURNBIKE =
		   "	returnBike <userID, stationID, time> : to let the user userID returning a bike\r\n" +
		   "	to station stationID at a given instant of time time (if no parking bay is available\r\n"+
		   "	should behave accordingly). This command should display the cost of the rent\r\n";
   
   public static final String DESCRIPTION_RETURNBIKE = 
		   "returnBike : used to return a bike"+"\n" + DESCRIPTION1_RETURNBIKE +"\n";
   
   //DisplayStation Command
   
   public static final String DISPLAYSTATION_COMMAND = "displayStation";

   public static final int NBR1_PARAM_DISPLAYSTATION = 2;
   public static final String DESCRIPTION1_DISPLAYSTATION =
		  "		displayStation<velibnetworkName, stationID> : to display the statistics (as of\r\n"+
		  "		Section ??) of station stationID of a myVelib network velibnetwork.\r\n";
   
   public static final String DESCRIPTION_DISPLAYSTATION = 
		   "displayStation : used to display a station"+"\n" + DESCRIPTION1_DISPLAYSTATION +"\n";
   
   //DisplayUser Command
   
   public static final String DISPLAYUSER_COMMAND = "displayUser";

   public static final int NBR1_PARAM_DISPLAYUSER = 2;
   public static final String DESCRIPTION1_DISPLAYUSER =
		  "		displayUser<velibnetworkName, userID> : to display the statistics (as of\r\n"+
		  "		Section ??) of user userID of a myVelib network velibnetwork.\r\n";
   
   public static final String DESCRIPTION_DISPLAYUSER = 
		   "displayUser : used to display a station"+"\n" + DESCRIPTION1_DISPLAYUSER +"\n";
   
   //SortStation Command
   
   public static final String SORTSTATION_COMMAND = "sortStation";

   public static final int NBR1_PARAM_SORTSTATION = 2;
   public static final String DESCRIPTION1_SORTSTATION =
		   "	sortStation<velibnetworkName, sortpolicy> : to display the stations in increas-\r\n"+
		   "	ing order w.r.t. to the sorting policy (as of Section ??) of user sortpolicy.\r\n";
   
   public static final String DESCRIPTION_SORTSTATION = 
		   "sortStation : used to sort the stations"+"\n" + DESCRIPTION1_SORTSTATION +"\n";
   
   //Display Command
   
   public static final String DISPLAY_COMMAND = "display";

   public static final int NBR1_PARAM_DISPLAY = 1;
   public static final String DESCRIPTION1_DISPLAY =
		   "	display <velibnetworkName>: to display the entire status (stations, parking bays,\r\n"+
		   "	users) of an a myVelib network velibnetworkName.\r\n";
   
   public static final String DESCRIPTION_DISPLAY = 
		   "display : used to display the network"+"\n" + DESCRIPTION1_DISPLAY +"\n";
   
   //ReadFile Command
   
   public static final String READFILE_COMMAND = "readFile";

   public static final int NBR1_PARAM_READFILE = 1;
   public static final String DESCRIPTION1_READFILE =
		   "	file <fileName>: to execute the command line written in a txt file\r\n";
   
   public static final String DESCRIPTION_READFILE = 
		   "readFile : used to read a file"+"\n" + DESCRIPTION1_READFILE +"\n";
   
   //Other messages
   
   public static final String INCORRECT_PARAMETERS_NUMBER_MSG = "You've entered the wrong number of parameters for the command.\r\n"+
		   														"Type help if you need assistance.\r\n"+"\n";
   public static final String HELP_MSG = DESCRIPTION_SETUP + DESCRIPTION_ADDUSER + DESCRIPTION_OFFLINE + DESCRIPTION_ONLINE + DESCRIPTION_RENTBIKE + DESCRIPTION_RETURNBIKE + DESCRIPTION_DISPLAYSTATION + DESCRIPTION_DISPLAYUSER + DESCRIPTION_SORTSTATION + DESCRIPTION_DISPLAY;
   public static final String UNRECOGNIZED_COMMAND_MSG ="You entered a unrecognized command ! Remember you can type help for help ;)";
   
   public static final String READING_FILE_BEGINNING_MSG = "Beginning to read command from following file :";
   public static final String READING_FILE_COMPLETE_MSG = "Reading from file complete !";
   public static final String FAILING_TO_READ_FILE_MSG = "Failing to read the file because of an incorrect path... Please type an other command";
   
   public static final String EXITING_MSG = "Exit";
   public static final String DEADLOCK_EXITING_MSG = "You've ended in a deadlock ! :( Please contact the author of the program in order to fix this bug !!!\r\n" +
		   											 "Now Exiting...";
   // ==== MAIN ====
   
   public static void main(final String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Welcome to MyVelib 1.0 !");
      System.out.println("Enter a command or type " + HELP_COMMAND + " for help. Type " + EXIT_COMMAND + " to quit.");
      
      boolean isUserInsideDeadlock;
      boolean listenToUser = true;
      boolean listenToAFile = false;
      String command = "";
      String[] inputForParsing = null;
      int numberOfParametersEntered = 0;
      Scanner txtFromFile = null;
      
      while (true) {
    	 
    	  isUserInsideDeadlock = true; // To verify that the user can type a command or a file is read, else the program will exit
    	 
    	  if(listenToAFile) {
      		isUserInsideDeadlock = false;
      		if(txtFromFile.hasNextLine()) {
      			String input = txtFromFile.nextLine();
      			inputForParsing = input.split(" ");
         		 	command = inputForParsing[0];
         		 	numberOfParametersEntered = inputForParsing.length-1;
      			System.out.println(input);
      		}
      		else {
      			System.out.println(READING_FILE_COMPLETE_MSG);
      			listenToUser = true;
      			listenToAFile = false;
      			txtFromFile.close();
      		}
      	 } 
    	  
    	 if(listenToUser) {
    		 isUserInsideDeadlock = false;
    		 System.out.print("> ");
    		 String input = br.readLine();
    		 inputForParsing = input.split(" ");
    		 command = inputForParsing[0];
    		 numberOfParametersEntered = inputForParsing.length-1;
    	 }
    	 
    	 
    	 
    	 if(isUserInsideDeadlock) {
    		 System.out.println(DEADLOCK_EXITING_MSG);
    		 return;
    	 }
    
    	 // CHECKING OF SPECIAL COMMAND
         
         if (command.equalsIgnoreCase(EXIT_COMMAND)) {
        	 if (numberOfParametersEntered == NBR_PARAM_EXIT) {
                 System.out.println(EXITING_MSG);
                 return;
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG );}
         }
         
         else if (command.equalsIgnoreCase(HELP_COMMAND)) {
        	 if(numberOfParametersEntered == NBR_PARAM_HELP) {
        	 System.out.print(HELP_MSG);
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG );}
         }
         
         else if (command.equalsIgnoreCase(READFILE_COMMAND)) {
        	 if(numberOfParametersEntered == NBR1_PARAM_READFILE) {
        		 try{
        		 String fileName = inputForParsing[1];
        		 File file = new File(fileName);
        		 txtFromFile = new Scanner(file);
        		 System.out.print(READING_FILE_BEGINNING_MSG);
        		 listenToUser = false;
        		 listenToAFile = true;
        		 }
        		 catch(Exception e) {
        			System.out.println(FAILING_TO_READ_FILE_MSG);
         			listenToUser = true;
        			listenToAFile = false;
        		 }
        	 }
        	 else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG );}
         }
         
         else if (command.equalsIgnoreCase(SETUP_COMMAND)) {
        	if(numberOfParametersEntered==NBR1_PARAM_SETUP) {
        		ParsingAndCalling.setupWith1Param(inputForParsing);
        	}
        	else if(numberOfParametersEntered==NBR2_PARAM_SETUP) {
        		ParsingAndCalling.setupWith5Param(inputForParsing);
        	}
        	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(ADDUSER_COMMAND)) {
         	if(numberOfParametersEntered==NBR1_PARAM_ADDUSER) {
         		ParsingAndCalling.addUserWith3Param(inputForParsing);;
         	}
         	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(OFFLINE_COMMAND)) {
          	if(numberOfParametersEntered==NBR1_PARAM_OFFLINE) {
          		ParsingAndCalling.offlineWith2Param(inputForParsing);
          	}
          	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(ONLINE_COMMAND)) {
           	if(numberOfParametersEntered==NBR1_PARAM_ONLINE) {
           		ParsingAndCalling.onlineWith2Param(inputForParsing);
           	}
           	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(RENTBIKE_COMMAND)) {
            	if(numberOfParametersEntered==NBR1_PARAM_RENTBIKE) {
            		ParsingAndCalling.rentBikeWith2Param(inputForParsing);
            	}
            	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
          }
         
         else if (command.equalsIgnoreCase(RETURNBIKE_COMMAND)) {
         	if(numberOfParametersEntered==NBR1_PARAM_RETURNBIKE) {
         		ParsingAndCalling.returnBikeWith3Param(inputForParsing);
         	}
         	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(DISPLAYSTATION_COMMAND)) {
          	if(numberOfParametersEntered==NBR1_PARAM_DISPLAYSTATION) {
          		ParsingAndCalling.displayStationWith2Param(inputForParsing);
          	}
          	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(DISPLAYUSER_COMMAND)) {
           	if(numberOfParametersEntered==NBR1_PARAM_DISPLAYUSER) {
           		ParsingAndCalling.displayUserWith2Param(inputForParsing);
           	}
           	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(SORTSTATION_COMMAND)) {
            	if(numberOfParametersEntered==NBR1_PARAM_SORTSTATION) {
            		ParsingAndCalling.sortStationWith2Param(inputForParsing);
            	}
            	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else if (command.equalsIgnoreCase(DISPLAY_COMMAND)) {
         	if(numberOfParametersEntered==NBR1_PARAM_DISPLAY) {
         		ParsingAndCalling.displayWith1Param(inputForParsing);
         	}
         	else {System.out.println(INCORRECT_PARAMETERS_NUMBER_MSG);}
         }
         
         else{System.out.println(UNRECOGNIZED_COMMAND_MSG);}
      }
   }
}