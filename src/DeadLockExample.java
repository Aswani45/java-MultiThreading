public class DeadLockExample {


    static class Pen{

        //synchronized locking(intrinsic) locks object , so lock is on the object , pen is locked here (this
        // funciton was called by pan , so pan object is locked here)
        synchronized void writeWithPenAndPaper(Paper paper){
                System.out.println("Writing with pen");
                paper.finializeWriting();
        }

        synchronized void finalizeWriting(){
            System.out.println("Finializing writing with pen");
        }

    }
    static class Paper{


        //synchronized locking(intrinsic) locks object , so lock is on the object , paper is locked here (this




        synchronized void writeWithPenAndPaper(Pen pen){
            System.out.println("Writing with paper");

            pen.finalizeWriting();
        }
        synchronized void finializeWriting(){
            System.out.println("Finializing writing");
        }


        // to avodi deadlock , i will not use synchronized here
//        void finializeWriting(){
//            System.out.println("Finializing writing with paper");
//        }
    }

    static void main() {
        Pen pen = new Pen();
        Paper paper = new Paper();
        Runnable r = new Runnable(){
            @Override
            public void run() {
                // to avoid deasdlock , i will request pen lock before paper lock
                synchronized (paper){
                    pen.writeWithPenAndPaper(paper);
                }
            }
        };
        Runnable r2 = new Runnable(){
            @Override
            public void run() {
                paper.writeWithPenAndPaper(pen);
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
