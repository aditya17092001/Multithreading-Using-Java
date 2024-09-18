/*
 * Why we have Thread class and Runnable interface when ever we wanted to extends the Thread class which has already extends another class then its impossible because it may leads to famous diamond problem.
 * 
 * Let's understand 
 * public class A extends B, Thread
 * in java its impossible to use the above method to achieve, so we can solve this by implementing the Runnable interface.
 */
public class A extends B implements Runnable{
    
    @Override
    public void run() {
        
    }
}