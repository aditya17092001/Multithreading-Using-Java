public class Main extends Thread{
    
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
    }

    public static void main(String[] args) throws InterruptedException {
        Main thread = new Main();
        thread.start(); // To run the Thread
        thread.join(); // main thread is waiting to finish the Thread-0 to complete.
        System.out.println("Hello");
    }
}