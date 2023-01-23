package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class task3 {

    public static void main(String[] args){

        Circularqueue myq = new Circularqueue(12);

        Passenger[][] myPassenger = new Passenger[12][3];

        for(int i=0;i<myPassenger.length;i++) {
            for(int x=0;x< myPassenger[i].length;x++){
                myPassenger[i][x] = new Passenger();
            }
        }

        Cabin[] myCabin = new Cabin[12];

        for(int i=0;i<myCabin.length;i++){
            myCabin[i] = new Cabin();
        }

        intialise(myCabin,myPassenger);
        boolean chance = true;
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
            System.out.println("T: Display expense");
            System.out.print("Enter your choice : ");
            char customer = Character.toUpperCase(choice.next().charAt(0));

            switch (customer){

                case 'E':
                    emptycabins(myCabin,myPassenger);
                    break;
                case 'D':
                    deleteCustomer(myCabin,myPassenger,myq);
                    break;
                case 'F':
                    findCustomer(myCabin,myPassenger);
                    break;
                case 'S':
                    storeData(myCabin,myPassenger);
                    break;
                case 'L':
//                    loadData(cruise);
                    break;
                case 'A':
                    addCustomer(myCabin,myPassenger,myq);
                    break;
                case 'V':
                    viewCabins(myCabin,myPassenger);
                    break;
                case 'O':
                    viewOrder(myCabin,myPassenger);
                    break;
                case 'T':
                    expense(myCabin,myPassenger);
                    break;
                default:
                    System.out.println("Wrong Input");

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
    public static void expense(Cabin[] myCab,Passenger[][] myPass){
        Scanner input = new Scanner(System.in);
        double expenseTotal = 0;

        System.out.print("Enter the cabin number of the customer : ");
        int cb = input.nextInt();
        while(cb>11){
            System.out.print("Wrong cabin number, enter cabin number again : ");
            cb = input.nextInt();
        }
        System.out.print("Enter the passenger position of the customer in the cabin : ");
        int pp = input.nextInt();

        for(int x=0;x< myCab.length;x++){
            for(int y=0;y<myPass[x].length;y++){
                expenseTotal = expenseTotal + myPass[x][y].getExpenses();

            }
        }

        System.out.println("Expense : "+myPass[cb][pp].getExpenses());
        System.out.println("Total Expenses : "+expenseTotal);
    }

    public static void emptycabins(Cabin[] myCab,Passenger[][] myPass){

        for(int x=0;x<myCab.length;x++){
            if(myCab[x].getCabinNum() == -1){
                System.out.println("Cabin "+x + " is empty");
            }

        }
    }
    public static void deleteCustomer(Cabin[] myCab,Passenger[][] myPass,Circularqueue myq){

        Scanner input = new Scanner(System.in);


        System.out.print("Enter the cabin of the passenger you want to delete : ");

        int deleteCabin = input.nextInt();
        while(deleteCabin>11){
            System.out.print("Wrong cabin number, enter cabin number again : ");
            deleteCabin = input.nextInt();
        }
        System.out.print("Enter the position of the passenger you want to delete : ");
        int deletePass = input.nextInt();
        myPass[deleteCabin][deletePass].setFirstName("Empty");
        myPass[deleteCabin][deletePass].setLastName("Empty");
        myPass[deleteCabin][deletePass].setExpenses(-1);

        System.out.println("Passenger is deleted");

        if(myq.QlastName() != null){
            for (int i = 0; i < myPass.length; i++){
                for (int k = 0; k < myPass[i].length; k++){
                    if (myPass[i][k].getFirstName().equals("Empty")){

                        myPass[i][k].setFirstName(myq.firstName());
                        System.out.print("Enter last name: ");
                        String tempLn = input.next();
                        myPass[i][k].setLastName(tempLn);
                        System.out.print("Enter your expense : ");
                        double tempEx = input.nextDouble();
                        myPass[i][k].setExpenses(tempEx);
                    }
                }
            }
        }

    }

    public static void findCustomer(Cabin[] myCab,Passenger[][] myPass){

        boolean found = false;

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first name of the customer you want to find : ");
        String fn = input.next();
        for(int x=0;x<myCab.length;x++){
            for(int y=0;y<myPass[x].length;y++){
                if(myPass[x][y].getFirstName().equals(fn) && !found){
                    found = true;
                    System.out.println(fn+" is in cabin "+x);
                }
            }
        }
        if(!found){
            System.out.println(fn+" not found");
        }

    }

    public static void storeData(Cabin[] myCab,Passenger[][] myPass){
        try{
            FileWriter inputFile1 = new FileWriter("storedata.txt");
            for(int x=0;x<myCab.length;x++) {
                for (int y = 0; y < myPass[x].length; y++) {
                    if(myCab[x].getCabinNum() != -1 ){
                        if(!myPass[x][y].getFirstName().equals("Empty")){
                            inputFile1.write("\n" + "Cabin number: " + myCab[x].getCabinNum() + " Passenger " + y + "\n");
                            inputFile1.write("Passenger firstname: " + myPass[x][y].getFirstName() + "\n" + "Passenger surname: "
                                    + myPass[x][y].getLastName() + "\n" + "Passenger Expenses: " + myPass[x][y].getExpenses());
                        }else{
                            inputFile1.write("\n"+"Cabin "+x+" passenger "+y+" is empty");
                        }
                    }else{
                        inputFile1.write("\n"+"Cabin "+x+" is empty");
                        break;
                    }
                }
            }

            inputFile1.close();

        }catch(IOException e){
            System.out.println(e.getMessage());

        }

    }
    public static void loadData(String[] cruise){
        try{
            File fileobj = new File("storedata.txt");
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
    public static void viewOrder(Cabin[] myCab,Passenger[][] myPass) {

        String[] tempArray = new String[12];

        int s = 0;

        for(int x=0;x< myPass.length;x++){

            for(int y=0;y< myPass[x].length;y++){

                tempArray[s++] = myPass[x][y].getFirstName();

            }
        }
//        for(int i=0;i< tempArray.length;i++){
//            System.out.println(tempArray[i]);
//        }
        for (int i = 0; i < tempArray.length-1; i++) {
            for (int j = 0; j < tempArray.length -i-1; j++) {
                if (tempArray[j].compareTo(tempArray[j+1]) > 0) {
                    String temp1 = tempArray[j];
                    tempArray[j] = tempArray[j+1];
                    tempArray[j+1] = temp1;
                }
            }
        }

        for (int i = 0; i < tempArray.length; i++){
            if(tempArray[i] != "Empty" ){
                System.out.println(tempArray[i]);
            }
        }
    }

    public static void viewCabins(Cabin[] myCab,Passenger[][] myPass){
        for(int x=0;x<myCab.length;x++){
            if(myCab[x].getCabinNum() != -1){
                for(int i=0;i< myPass[x].length;i++){
                    if(!myPass[x][i].getFirstName().equals("Empty")){
                        System.out.println("\n" + "Cabin number: " + myCab[x].getCabinNum() + " Passenger " + i + "\n");
                        System.out.println("Passenger firstname: " + myPass[x][i].getFirstName() + "\n" + "Passenger surname: "
                                + myPass[x][i].getLastName() + "\n" + "Passenger Expenses: " + myPass[x][i].getExpenses());
                    }else{
                        System.out.println("Cabin "+x+" Passenger"+i+" is empty");
                    }
                }
            }else{
                System.out.println(x + " is empty");
            }
        }
    }


    public static void addCustomer(Cabin[] myCab,Passenger[][] myPass,Circularqueue myq){
        Scanner input = new Scanner(System.in);

        int cabinNum;
        int cabPos;

        int c = 0;

        for(int x=0;x<myCab.length;x++){
            for(int y=0;y<myPass[x].length;y++) {
                if(! myPass[x][y].getFirstName().equals("Empty")){
                    c = c+1;
                }
            }
        }
        if(c==36){
            System.out.println("Ship is full");
            System.out.print("Do you want to move to the waiting queue yes/y or no/n : ");
            String wq = input.next();
            if(wq.equals("yes") || wq.equals("y")) {
                System.out.print("Enter first name : ");
                String fn = input.next();
                myq.enqueue(fn);    // customer added to the waiting queue
            }else{
                System.out.println("You are out ! ");
            }
        }else{
            System.out.print("How many people you want to add : ");
            int ppl = input.nextInt();

            if(ppl==1) {
                System.out.print("Enter cabin number : ");
                cabinNum = input.nextInt();
                while(cabinNum>11){
                    System.out.print("Wrong cabin number, enter cabin number again : ");
                    cabinNum = input.nextInt();
                }

                myCab[cabinNum].setCabinNum(cabinNum);

                System.out.print("Enter cabin position : ");
                cabPos = input.nextInt();

                if (myPass[cabinNum][cabPos].getFirstName().equals("Empty")) {

                    System.out.print("Enter first name : ");
                    myPass[cabinNum][cabPos].setFirstName(input.next());
                    System.out.print("Enter last name : ");
                    String ln = input.next();
                    myPass[cabinNum][cabPos].setLastName(ln);
                    System.out.print("Enter expense : ");
                    double exp = input.nextDouble();
                    myPass[cabinNum][cabPos].setExpenses(exp);
                } else {
                    System.out.println("The position you request is occupied.");
                }
            }else if(ppl==0){
                System.out.println("Wrong Input");
            }else{
                System.out.print("Enter cabin number : ");
                cabinNum = input.nextInt();
                while(cabinNum>11){
                    System.out.print("Wrong cabin number, enter cabin number again : ");
                    cabinNum = input.nextInt();
                }

                if(myPass[cabinNum][0].getFirstName().equals("Empty") || myPass[cabinNum][1].getFirstName().equals("Empty") || myPass[cabinNum][2].getFirstName().equals("Empty")) {


                    myCab[cabinNum].setCabinNum(cabinNum);

                    for (int i = 0; i < ppl; i++) {
                        System.out.print("Enter first name : ");
                        myPass[cabinNum][i].setFirstName(input.next());
                        System.out.print("Enter last name : ");
                        String ln = input.next();
                        myPass[cabinNum][i].setLastName(ln);
                        System.out.print("Enter expense : ");
                        double exp = input.nextDouble();
                        myPass[cabinNum][i].setExpenses(exp);
                    }
                }else {
                    System.out.println("The position you request is occupied.");
                }

            }
        }

    }

    private static void intialise(Cabin[] thisCabin,Passenger[][] thisPassenger){

        for(int i=0;i< thisCabin.length;i++){
            thisCabin[i].setCabinNum(-1);
        }
        for (int i=0;i< thisPassenger.length;i++) {
            for (int j = 0; j < thisPassenger[i].length; j++){
                thisPassenger[i][j].setFirstName("Empty");
                thisPassenger[i][j].setLastName("Empty");
                thisPassenger[i][j].setExpenses(0.0);

            }

        }
    }
}
