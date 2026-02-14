class Main{
    static void main() throws InterruptedException{


        //this way we can crate thread using extending Thread class
//        SecondThrad st = new SecondThrad();
//
//        st.start();

        //this way we can create thread using runnable interface
//        SecondThrad st = new SecondThrad();
//        Thread t1 = new Thread(st);
//        t1.start();
//
//
//        t1.join(); // current thread will wait for t1 to finish , before executing the next statement
//        while(true){
//            System.out.println(Thread.currentThread().getName());
//        }


        Account account = new Account();
        Runnable r = new Runnable(){
            @Override
            public void run() {
                account.withdraw(50);
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(account.amount);
    }
}