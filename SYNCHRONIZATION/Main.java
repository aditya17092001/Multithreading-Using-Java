public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter count = new Counter(); // This object is the common resourse for the both threads t1 and t2.
        MyThread t1 = new MyThread(count);
        MyThread t2 = new MyThread(count);
        t1.start();
        t2.start(); 
        t1.join(); // Wait for t1 execution.
        t2.join(); // Wait for t2 execution.
        System.out.println(count.getCount());
    }
}
