package cn.edu.gzu.fjbai;

public class CallmeSynchronizationTest1 {

	void Call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
       }
       System.out.println("]");
    }
}

class Caller2 implements Runnable {
    String msg;
    CallmeSynchronizationTest1 target;
    Thread t;
    public Caller2(CallmeSynchronizationTest1 targ, String s) {
        target = targ;
        msg = s;
        t = new Thread(this);
        t.start();
    }
    public void run() {
    	synchronized(target){
            target.Call(msg);    		
    	}
    }
}

class Synch2 {
    public static void main(String args[]) {
    	CallmeSynchronizationTest1 target = new CallmeSynchronizationTest1();
        Caller2 ob1 = new Caller2(target, "Hello");
        Caller2 ob2 = new Caller2(target, "Synchronized");
        Caller2 ob3 = new Caller2(target, "World");
        // wait for threads to end
        try {
          ob1.t.join();
          ob2.t.join();
          ob3.t.join();
       } catch(InterruptedException e) {
          System.out.println("Interrupted");
       }
    }

}
