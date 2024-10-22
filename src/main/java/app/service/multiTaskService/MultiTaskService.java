package app.service.multiTaskService;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiTaskService {
    private final ExecutorService threadPool;

    public MultiTaskService(int maxThreadNum) {
        threadPool = Executors.newFixedThreadPool(maxThreadNum);
    }

    /**
     * handleTasks method
     *
     * @param tasks List of implement interface {@link Callable}
     * @return result of multiThread
     * @throws InterruptedException when error in thread
     */
    public List<Future<ResultTask<?>>> handleTasks(List<Callable<ResultTask<?>>> tasks)
            throws InterruptedException {
        return threadPool.invokeAll(tasks);
    }

    public void shutdown() {
        threadPool.shutdown();
    }
}
