public class SetPriority extends Thread {

    public SetPriority(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /*
         * Setting the priority of a thread in Java does not guarantee that the thread with a higher priority will always run before a thread with a lower priority. Thread priority is a suggestion to the thread scheduler on how to schedule threads, but the actual scheduling behavior depends on the underlying operating system's thread scheduler, which might not strictly adhere to the priority settings.
         */
        SetPriority high = new SetPriority("High");
        SetPriority medium = new SetPriority("Medium");
        SetPriority low = new SetPriority("Low");
        high.start();
        medium.start();
        low.start();
    }
}
