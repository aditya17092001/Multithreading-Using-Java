public class Deamon extends Thread{
    @Override
    public void run() {
        // Infinite loop.
        while(true) { 
            System.out.println("Hello"); 
        }
    }

    public static void main(String[] args) {
        Deamon thread = new Deamon();
        /*
         * 
            The setDaemon() method in Java is used to mark a thread as a daemon thread or a user thread. Daemon threads are low-priority threads that run in the background and provide support for other threads, usually performing tasks like garbage collection or background monitoring.
         */
        thread.setDaemon(true);  // With the use of setDeamon as soon as main thread terminates it will stop the infinite loop execution.
        thread.start();
        System.out.println("Main completed!");
    }
}
