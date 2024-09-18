public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World"); // Prints the Hello World

        // This will print the name of the thread. As we know when the java program starts, one thread begins running immedialtely, which is called the main thread. This thread is responsible for executing the main method of a program.

        System.out.println(Thread.currentThread().getName()); 


        /*
         * Suppose if we want to make the two thread running simultaneously we can acheive by creating a new class by extends the Thread class or implementing the Runnable interface.
         * 
         * Why we should use if we sum form 0 to 1000000 it will take time but we want it to be calculate simultaneously, we can acheive from the threads.
        */

        /*
         * Let's create the object the Test class
        */

        Test test = new Test();
        test.start();

        long sum = 0;
        for(double i = 0; i < 1000000; i++) {
            sum += i;
        }

        System.out.println(sum+ " From thread "+ Thread.currentThread().getName());

        /*
         * In the console we can watch both main and Thread-0 printing simultaneously.
        */

        /*
         * Below is the process of the creating the thread using Runnable interface.
        */

        Test1 test1 = new Test1();
        Thread thread = new Thread(test1);
        thread.start();
    }
}