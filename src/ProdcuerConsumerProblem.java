
class SharedResource{
    int data = 0;
    boolean hasData = false;


    public synchronized void produce(int data) throws InterruptedException {
        while(hasData){
            wait();
        }
        this.data = data;
        hasData = true;
        System.out.println("Produced "+data);
        notifyAll();
    }

    synchronized public void consume() throws InterruptedException {
        while(!hasData){
            wait();
        }
        System.out.println("Consumed "+data);
        hasData = false;
        notifyAll();
    }
}

class Producer implements Runnable{

    SharedResource sharedResource;
    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            for(int i=0;i<10;i++){
                sharedResource.produce(i);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}

class Consumer implements Runnable{
    SharedResource sharedResource;
    public Consumer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        try {
            for(int i=0;i<10;i++){
                sharedResource.consume();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}



public class ProdcuerConsumerProblem {



    public static void main() throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Producer producer = new Producer(sharedResource);
        Consumer consumer = new Consumer(sharedResource);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}