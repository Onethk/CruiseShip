package com.company;

public class Passenger {

    String firstName = "Empty";
    String lastName = "Empty";
    double expenses = 0.0;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }
}
