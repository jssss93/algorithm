package SmartJpDatatest;

public class multiThread {

    public static void main(String[] args) {
        Runnable d = new CheckThread("손흥민1");
        Runnable d1 = new CheckThread("이강인2");
        Runnable d2 = new CheckThread("황의조3");
        Runnable d3 = new CheckThread("황희찬4");

        Thread thread = new Thread(d);
        Thread thread1 = new Thread(d1);
        Thread thread2 = new Thread(d2);
        Thread thread3 = new Thread(d3);

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();

    }

}

class CheckThread implements Runnable{

    String name;

    CheckThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        try{

            for(int i=0;i<5;i++){
                System.out.println(name);
                Thread.sleep(1000);
            }

        }catch (Exception e){
            System.out.println(e.toString());
        }

        System.out.println("쓰레드 종료 : "+ name);
    }
}