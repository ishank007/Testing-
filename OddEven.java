public class OddEven implements Runnable {
    @Override
    public void run() {
        int end=50;
        for (int i = 1; i <= end; i++) {
            synchronized (this) {
                if (i % 2 == 0 && Thread.currentThread().getName().equals("t2")) {
                    try {
                        notifyAll();//wakes up all thread waiting for object
                        System.out.println("Even Thread : " + i);
                        if(end!=i){
                            wait();
                        }
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                } else if (i % 2 != 0
                        && Thread.currentThread().getName().equals("t1")) {
                    try {
                        notifyAll();//wakes up all thread waiting for object
                        System.out.println("Odd Thread : " + i);
                        if(end!=i){
                            wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void main(String[] args) {

        OddEven obj = new OddEven();
        Thread t1 = new Thread(obj, "t1");
        Thread t2 = new Thread(obj, "t2");
        t1.start();
        t2.start();

    }
}