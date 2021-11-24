package SMART_JP;

public class ThreadTest {

    public static void main(String[] args) {
        Runnable d = new CheckThread("손흥민1","TOT");
        Runnable d1 = new CheckThread("이강인2","BAL");
        Runnable d2 = new CheckThread("황의조3","BOR");
        Runnable d3 = new CheckThread("황희찬4","BOR");
        

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
    String type;
    CheckThread(String name,String type){
        this.name = name;
        this.type = type;
    }

    @Override
    public void run() {
        try{
        	for(int i=0;i<5;i++){
        		if(type.equals("TOT")){
                    TOT(name);
        		}else if(type.equals("BAL")){
        			BAL(name);
        		}else if(type.equals("BOR")){
        			BOR(name);
        		}
        		Thread.sleep(1000);
        	}

        }catch (Exception e){
            System.out.println(e.toString());
        }

        System.out.println("쓰레드 종료 : "+ name);
    }

    public static void TOT(String name){
    	System.out.println("TOT :: "+name);
    }
    public static void BAL(String name){
    	System.out.println("BAL :: "+name);
    }
    public static void BOR(String name){
    	System.out.println("BOR :: "+name);
    }
}
