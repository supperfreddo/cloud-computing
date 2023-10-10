package nl.inholland;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Pi{
    //Calculate Pi using MapReduce
    public static void main(String[] args) {
        //Init variables
        int numThreads = Runtime.getRuntime().availableProcessors();
        int numIterations = 100000000;
        double pi = 0;

        //Create thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        //Create tasks
        class PiTask implements Callable<Double> {
            private final long numIterations;

            public PiTask(long numIterations) {
                this.numIterations = numIterations;
            }

            @Override
            public Double call() {
                double sum = 0.0;
                for (long i = 0; i < numIterations; i++) {
                    double x = Math.random();
                    double y = Math.random();
                    if (x * x + y * y <= 1.0) {
                        sum += 1.0;
                    }
                }
                return 4.0 * sum / numIterations;
            }
        }

        List<Callable<Double>> tasks = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            tasks.add(new PiTask(numIterations / numThreads));
        }

        //Execute tasks
        try {
            List<Future<Double>> results = executorService.invokeAll(tasks);
            for (Future<Double> result : results) {
                pi += result.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //Shutdown thread pool
        executorService.shutdown();

        //Display result
        System.out.println("Pi: " + pi);
    }
}