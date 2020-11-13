package Demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class Future例子 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("主线程开始");
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future> queue = new ArrayList<>();
		for(int i=0;i<10;i++) {
			Future<String> future = executorService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					System.out.println("子线程开始");
					Thread.sleep(4000);
					return "123";
				}
			});
			queue.add(future);
		}
		int i=1;
		for (Future future : queue) {
			System.out.println(future.get()+"是"+i);
			i++;
		}
		
		
		executorService.shutdown();
	}

}
