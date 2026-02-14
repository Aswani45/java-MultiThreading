import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

   static  class Counter{
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();
        int count = 0;

        void increment(){
            writeLock.lock();
            try{
                count++;
            }finally{
                writeLock.unlock();
            }
        }

        int getCount(){
            readLock.lock();
            try{
                return count;
            }finally{
                readLock.unlock();
            }
        }
    }

    static void main() throws InterruptedException {
        Counter counter = new Counter();
        Runnable r = new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    counter.increment();
                    System.out.println("Incremented by "+Thread.currentThread().getName() + " count is "+counter.getCount());
                }
            }
        };
        Runnable r2 = new Runnable(){
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    int count = counter.getCount();
                    System.out.println("Count by "+Thread.currentThread().getName()+" is "+count);
                }
            }
        };
        Thread t1 = new Thread(r);

        Thread t3 = new Thread(r2);
        Thread t4 = new Thread(r2);
        t1.start();
        t3.start();
        t4.start();
        t1.join();
        t3.join();
        t4.join();
        System.out.println(counter.count);
    }



}
