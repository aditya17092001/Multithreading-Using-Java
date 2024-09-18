public class Test1 implements Runnable{

    @Override
    public void run() {
        long sum = 0;
        for(int i = 0; i < 10000000; i++) {
            sum += i;
        }

        System.out.println(sum + " From thread "+Thread.currentThread().getName());
    }
}
