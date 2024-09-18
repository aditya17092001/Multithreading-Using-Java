public class Interrupt extends Thread{

    public Interrupt(String name) {
        super(name);
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+" is running, value: "+i);
            Thread.yield(); // A hint to the scheduler that the current thread is willing to yield its current use of a processor. The scheduler is free to ignore this hint.
            /*
            *   With yeild() method
                t2 is running, value: 0
                t1 is running, value: 0
                t2 is running, value: 1
                t1 is running, value: 1
                t2 is running, value: 2
                t1 is running, value: 2
                t2 is running, value: 3
                t1 is running, value: 3
                t2 is running, value: 4
                t1 is running, value: 4

                With out yeild() method
                t1 is running, value: 0
                t1 is running, value: 1
                t2 is running, value: 0
                t1 is running, value: 2
                t1 is running, value: 3
                t2 is running, value: 1
                t2 is running, value: 2
                t1 is running, value: 4
                t2 is running, value: 3
                t2 is running, value: 4
             */
        }
    }

    public static void main(String[] args) {
        Interrupt thread1 = new Interrupt("t1");
        Interrupt thread2 = new Interrupt("t2");
        thread1.start();
        thread2.start();
        // thread1.interrupt(); // interrupt() method will change the state of the thread means if the thread is in sleep state it will make it runnable or vise versa.

    }
}
