import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class Account {
    int amount = 100;
    //synchronised keyword will make other thread  to wait until this method is executed
    // i dont want other thread to wait infinite , i want like a thread should wait for a specific time to get the lcok
//    synchronized void withdraw(int amount){
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        if(this.amount < amount){
//            System.out.println("Insufficient balance");
//            return;
//        }
//        this.amount -= amount;
//        System.out.println("Withdrawn amount: " + amount + " Remaining balance: " + this.amount);
//    }

    Lock lock = new ReentrantLock();

     void withdraw(int amount){

         //lock,trylock will be in try block , to handle InterruptedException
        try {
            if(lock.tryLock(3, TimeUnit.SECONDS)){
                try {
                    Thread.sleep(3000);
                    if(this.amount < amount){
                        System.out.println("Insufficient balance");
                        return;
                    }
                    this.amount -= amount;
                    System.out.println("Withdrawn amount: " + amount + " Remaining balance: " + this.amount);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock(); // IMPORTANT: Always unlock in finally block
                }
            }else{
                System.out.println(Thread.currentThread().getName()+" Could not get the lock , remaining amount "+this.amount);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
