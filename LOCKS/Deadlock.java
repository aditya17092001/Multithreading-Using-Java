class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName()+", is using Pen "+this+ "and trying to use paper "+paper);
        paper.finishWriting();
    }
    
    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName()+" finished using Pen "+this);
    }
}

class Paper {
    public synchronized void writeWithPaperAndPen(Pen pen) {
        System.out.println(Thread.currentThread().getName()+", is using Pen "+this+ "and trying to use paper "+pen);
        pen.finishWriting();
    }
    
    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName()+" finished using Pen "+this);
    }
}

class Task1 implements Runnable {
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper); //Thread 1 locks pen and tries to lock paper        
    }   
}

class Task2 implements Runnable {
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        // paper.writeWithPaperAndPen(pen); //Thread 2 locks paper and tries to lock pen

        // The above line leads to deadlock.
        // To resolve this we need to change the ordering of the lock and making the consitent lock we can resolve.

        synchronized(pen) {
            paper.writeWithPaperAndPen(pen);
        }
    } 
}

public class Deadlock {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task1(pen, paper), "Thread1"); 
        Thread thread2 = new Thread(new Task2(pen, paper), "Thread2");

        thread1.start();
        thread2.start();
    }
}
