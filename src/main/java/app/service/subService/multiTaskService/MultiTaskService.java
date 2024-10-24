package app.service.subService.multiTaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiTaskService {
    private final ExecutorService threadPool;
    private List<Callable<ResultTask<?>>> workingTasks;

    /**
     * MultiTaskService constructor.
     *
     * @param maxThreadNum max thread available in ThreadPool
     */
    public MultiTaskService(int maxThreadNum) {
        threadPool = Executors.newFixedThreadPool(maxThreadNum);
        workingTasks = new ArrayList<>();
    }

    /**
     * Add task method.
     *
     * @param task one of task Service want to add to thread pool
     */
    public void addTasks(Callable<ResultTask<?>> task) {
        this.workingTasks.add(task);
    }

    /**
     * handleTasks method.
     *
     * @return result of multiThread
     * @throws InterruptedException when error in thread
     * @throws ExecutionException   when converting Future object failed
     */
    public List<ResultTask<?>> handleTasks()
            throws InterruptedException, ExecutionException {
        List<Future<ResultTask<?>>> rawResults = this.threadPool.invokeAll(workingTasks);
        List<ResultTask<?>> cleanResults = new ArrayList<>();
        for (Future<ResultTask<?>> rawResult : rawResults) {
            cleanResults.add(rawResult.get());
        }
        return cleanResults;
    }

    /**
     * Shutdown thread pool.
     */
    public void shutdown() {
        threadPool.shutdown();
    }
}
