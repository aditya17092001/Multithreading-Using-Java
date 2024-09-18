public class Main extends Thread{
    
    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main thread = new Main();
        System.out.println(thread.getState()); // NEW
        thread.start(); // Starting the thread.
        System.out.println(thread.getState()); // RUNNING printing from overide method run.
        // System.out.println(Thread.currentThread().getState()); // Main method thread show RUNNABLE

        Thread.sleep(1000); // Making thread to sleep for sometime.
        System.out.println(thread.getState()); // TIMED_WAITING

        thread.join(); // This means main thread is waiting for the the thread to complete its execution.
        System.out.println(thread.getState());

    }
}