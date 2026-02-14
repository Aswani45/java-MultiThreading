public class ThreadPriority extends Thread{

    ThreadPriority(String name){
        super(name);
    }

    @Override
    public void run() {
        String a = " ";
        for(int i=0;i<100;i++){
            a += i;
        }
        for(int i=0;i<5;i++){
            System.out.println("I am the thread "+Thread.currentThread().getName()+" with priority "+Thread.currentThread().getPriority());

            // ask CPU to give chance to other thread we use yeild function
            Thread.yield();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadPriority lowPriority = new ThreadPriority("Low Priority");
        lowPriority.setPriority(Thread.MIN_PRIORITY);

        ThreadPriority mediumPriority = new ThreadPriority("Medium Priority");
        mediumPriority.setPriority(Thread.NORM_PRIORITY);

        ThreadPriority highPriority = new ThreadPriority("High Priority");
        highPriority.setPriority(Thread.MAX_PRIORITY);
        lowPriority.start();
        mediumPriority.start();
        highPriority.start();
    }
}
