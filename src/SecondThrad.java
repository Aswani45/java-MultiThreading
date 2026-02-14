//public class SecondThrad extends Thread{
//
//    @Override
//    public void run() {
//        while(true){
//            System.out.println("I am the second thread "+Thread.currentThread().getName());
//        }
//    }
//}

public class SecondThrad implements Runnable {

    @Override
    public void run() {
        while(true){
            System.out.println("I am the second thread "+Thread.currentThread().getName());
        }
    }
}