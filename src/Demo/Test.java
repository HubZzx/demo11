package Demo;

import java.util.concurrent.*;
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executorService.submit(futureTask);
        executorService.shutdown();
        
        System.out.println("主线程在执行任务...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
         
        try {
            System.out.println("task运行结果:"+futureTask.get());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        }
         
        System.out.println("所有任务执行完毕");
    }
}
class Task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在执行任务...");
        //模拟任务耗时
        Thread.sleep(5000);
        return 1000;
    }
}