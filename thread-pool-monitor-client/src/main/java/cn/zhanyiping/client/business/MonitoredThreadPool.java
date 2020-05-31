package cn.zhanyiping.client.business;

import cn.zhanyiping.client.business.mbean.ThreadPoolManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class MonitoredThreadPool extends ThreadPoolExecutor {

    private final static Logger log = LoggerFactory.getLogger(MonitoredThreadPool.class);

    public MonitoredThreadPool(String threadPoolName , int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        try {
            ThreadPoolManageService.getInstance().setExecutor(this , threadPoolName);
        } catch (Throwable t) {
            log.error("Failed to register jmx" , t);
        }
    }

    public MonitoredThreadPool(String threadPoolName , int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        try {
            ThreadPoolManageService.getInstance().setExecutor(this , threadPoolName);
        } catch (Throwable t) {
            log.error("Failed to register jmx" , t);
        }
    }

    public MonitoredThreadPool(String threadPoolName , int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        try {
            ThreadPoolManageService.getInstance().setExecutor(this , threadPoolName);
        } catch (Throwable t) {
            log.error("Failed to register jmx" , t);
        }
    }

    public MonitoredThreadPool(String threadPoolName , int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        try {
            ThreadPoolManageService.getInstance().setExecutor(this , threadPoolName);
        } catch (Throwable t) {
            log.error("Failed to register jmx" , t);
        }
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return super.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return super.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return super.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        return super.awaitTermination(timeout , unit);
    }


    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return super.submit(task , result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
        return super.invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
            throws InterruptedException {
        return super.invokeAll(tasks , timeout ,unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        return super.invokeAny(tasks);
    }


    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return super.invokeAny(tasks, timeout , unit);
    }
}
