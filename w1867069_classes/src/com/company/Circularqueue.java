package com.company;

public class Circularqueue {

    private int front;
    private int end;
    private String[] queueArray;

    public  Circularqueue(int size){
        queueArray = new String[size];
        front=-1;
        end=0;
    }

    public String firstName(){

        return queueArray[front];

    }
    public String QlastName(){
        return queueArray[end];
    }

    public void enqueue(String name){
        if((front == 0 && end == queueArray.length - 1) ||
                (end == (front - 1) % (queueArray.length - 1))) // Condition if queue is full
        {
            System.out.println("Queue Full!");
        }

        else if(front == -1) // Condition for empty queue.
        {
            front = 0;
            end = 0;
            queueArray[end]=name;
            System.out.println("Value added : "+name);
        }
        else if(end == queueArray.length - 1 && front != 0)
        {
            end = 0;
            queueArray[end]=name;
            System.out.println("Value added : "+name);
        }else
        {
            end = (end + 1);
            queueArray[end]=name;
        }


    }

    public String dequeue() {
        if (front == -1){
            System.out.println("Queue is empty");
        }

        String data = queueArray[front];
        queueArray[front] = String.valueOf(-1);
        if (front == end) {
            front = -1;
            end = -1;
        } else if (front == queueArray.length-1){
            front = 0;
        } else {
            front++;
        }
        return data;
    }


}
