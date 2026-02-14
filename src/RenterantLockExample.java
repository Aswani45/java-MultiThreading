import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RenterantLockExample{

    Lock lock = new ReentrantLock();

    void doSomething(){
        lock.lock();
        try{
            System.out.println("Doing something");
            doSomethingElse();
        }finally{
            lock.unlock();
        }
    }

    void doSomethingElse(){
        lock.lock();
        try{
            System.out.println("Doing something else");
        }finally{
            lock.unlock();
        }
    }


    static void main() {
        RenterantLockExample example = new RenterantLockExample();
        Runnable r = new Runnable(){
            @Override
            public void run() {
                example.doSomething(   );
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}
