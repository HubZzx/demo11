package Demo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class UseFuture {
    //实现callable接口，允许有返回值
    private static class UseCallable implements Callable<Integer>{
        private int sum;
        @Override
        public Integer call() throws Exception {
            // TODO Auto-generated method stub
            System.out.println("callable子线程开始计算");
            //Thread.sleep(1000);
            for(int i = 0 ; i < 5000;i++){
                //测试是否当前线程已被中断 中断返回true，否则返回false
                //总的说，这句就是无限判断当前线程状态，如果没有中断，就一直执行while内容。 
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("callable子线程计算任务中断");
                    return null;
                }
                sum=sum+i; 
            }
            System.out.println("callable子线程计算结束，结果为："+sum);
            return sum;
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseCallable useCallabl = new UseCallable();
        //定义一个FutureTask包装callable,用来接收返回值
        FutureTask<Integer> futureTask = new FutureTask<>(useCallabl);
        new Thread(futureTask).start();
        Random r = new Random();
        if(r.nextInt(100) > 50){
            System.out.println("获取结果："+futureTask.get());
        }else{
            System.out.println("cancel............");;
            futureTask.cancel(true);
        }
        
        
    }
    
}