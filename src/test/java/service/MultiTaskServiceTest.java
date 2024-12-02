package service;

import app.service.subService.multiTaskService.MultiTaskService;
import app.service.subService.multiTaskService.ResultTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MultiTaskServiceTest {
    private MultiTaskService multiTaskService;

    @BeforeEach
    public void initialize() {
        multiTaskService = new MultiTaskService(15);
    }

    @Test
    public void testMultiTask1() {
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });

        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });
        multiTaskService.addTasks(() -> {
            int sum1 = 0;
            for (int i = 1; i <= 100000000; i++) {
                sum1 += i;
            }
            return new ResultTask<>("int", sum1);
        });


        try {
            List<ResultTask<?>> results = multiTaskService.handleTasks();
            for (ResultTask<?> rs : results) {
                System.out.println(rs.getData());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
