public class Counter {
    int count = 0;

    //synchronized keyword ensures that only one thread can execute this method at a time
//    synchronized void increment(){
//        count++;
//    }



    void increment(){

        //synchronized block ensures that only one thread can execute this block of code at a time
        synchronized(this){
            count++;
        }
    }

    int getCount(){
        return count;
    }

}
