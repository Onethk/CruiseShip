
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.*;

public class CruiseShip {


    public static void main (String[] args){


        int roomNum=0;

        // array implemented to create 12 cabins
        String[] cruise = new String[12];


        // cruise array initialised using a function defined below
        intialise(cruise);

        boolean chance = true;

        // while loop used to ask the user if he wants to enter another choice

        while(chance==true){
            Scanner choice = new Scanner(System.in);
            System.out.println("E: Display Empty cabins");
            System.out.println("D: Delete customer from cabin");
            System.out.println("F: Find cabin from customer name");
            System.out.println("S: Store program data into file");
            System.out.println("L: Load program data from file");
            System.out.println("O: View passengers Ordered alphabetically by name");
            System.out.println("V: View all cabins");
            System.out.println("A: Add customer to cabin");
            System.out.print("Enter your choice : ");
            char customer = Character.toUpperCase(choice.next().charAt(0));

            switch (customer){

                case 'E':
                    emptycabins(cruise);
                    break;
                case 'D':
                    deleteCustomer(cruise);
                    break;
                case 'F':
                    findCustomer(cruise);
                    break;
                case 'S':
                    storeData(cruise);
                    break;
                case 'L':
                    loadData(cruise);
                    break;
                case 'A':
                    addCustomer(cruise);
                    break;
                case 'V':
                    viewCabins(cruise);
                    break;
                case 'O':
                    viewOrder(cruise);
                    break;
                default:
                    System.out.println("done");

            }
            System.out.print("Do you want to choose again from the menu, enter yes or no : ");
            String answer = choice.next();
            if(answer.equals("yes")){
                chance = true;
            }else{
                chance = false;
            }

        }

    }

    public static void emptycabins(String[] cruise){
        for(int x=0;x<12;x++){
            if(cruise[x].equals("No one")){
                System.out.println("Room "+x+" is empty");
            }
        }
    }
    public static void deleteCustomer(String[] cruise){
        System.out.print("Enter the room number you want to delete : ");
        Scanner deletenum = new Scanner(System.in);
        int deleteNum = deletenum.nextInt();
        while(deleteNum>11){
            System.out.print("Invalid, enter room number (0-11) or 12 to stop :");
            deleteNum = deletenum.nextInt();
        }
        for (int x=0;x<12;x++){
            if(deleteNum == x){
                cruise[x] = "No one";
                // cruise name is assigned as "No one"
            }
        }
    }

    public static void findCustomer(String[] cruise){
        boolean found = false;
        Scanner name = new Scanner(System.in);
        System.out.print("Enter the customer name you want to find : ");
        String cusName = name.next();
        for(int x=0;x<12;x++){
            if (cruise[x].equals(cusName)){
                found = true;
                System.out.println(cusName+" is in cabin "+x);
                break;
            }else{
                found = false;
            }

        }
        if(found==false){
            System.out.println("Customer not found");

        }
    }

    public static void storeData(String[] cruise){
        try{

            FileWriter inputFile = new FileWriter("storedata.txt");   // creating object
            for (int x = 0; x < 12; x++) {
                if(cruise[x]!="No one"){
                    inputFile.write("Cabin "+x+" : "+cruise[x]+"\n");
                }
            }
            inputFile.close();  // object closed

        }catch(IOException e){
            System.out.println(e.getMessage());

        // try catch used for error handling
        }

    }
    public static void loadData(String[] cruise){
        try{
            File fileobj = new File("storedata.txt");   // creating file object
            Scanner sc = new Scanner(fileobj);
            while (sc.hasNextLine()){
                String data = sc.nextLine();
                System.out.println(data);
            }
            sc.close();
        }catch (FileNotFoundException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }

    }
    public static void viewOrder(String[] cruise) {

        for (int i = 0; i < cruise.length; i++) {    // bubble sort used
            for (int j = 0; j < cruise.length-i-1; j++) {
                if (cruise[j].compareTo(cruise[j+1]) > 0) {
                    String temp1 = cruise[j];
                    cruise[j] = cruise[j+1];
                    cruise[j+1] = temp1;
                }
            }
        }
        for (int i = 0; i < cruise.length; i++){
            if(cruise[i] != "No one" ){
                System.out.println(cruise[i]);
            }

        }
    }
    public static void viewCabins(String[] cruise){
        for(int x=0; x<12 ;x++){
            if(cruise[x].equals("No one")){
                System.out.println("Cabin "+x+" is empty");
            }else{
                System.out.println("Cabin "+x+" is occupied by "+cruise[x]);
            }
        }

    }

    public static void addCustomer(String[] cruise){
        int roomNum=0;
        String roomName = "No one";

        Scanner input = new Scanner(System.in);


        System.out.print("Enter room number (0-11) or 12 to stop :");
        roomNum = input.nextInt();
        while(roomNum>11){
            System.out.print("Invalid, enter room number (0-11) or 12 to stop :");
            roomNum = input.nextInt();
        }
        if(cruise[roomNum].equals("No one")){
            System.out.print("Enter name for room "+roomNum+" :");
            roomName = input.next();
            cruise[roomNum] = roomName;
        }else{
            System.out.println("Cabin "+roomNum+" is occupied");
        }

    }

    private static void intialise(String[] cabinRef){
        for (int i=0;i<12;i++){
            cabinRef[i] = "No one";
        }
    }


}
