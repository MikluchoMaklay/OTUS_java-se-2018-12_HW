package ru.otus.hw10;

import com.google.common.collect.Lists;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * Created by tully.
 * Modified by Michael
 * <p>
 * Example for L01.1
 * <p>
 * To start the application:
 * mvn package
 * java -cp ./target/HW1.0-maven.jar ru.otus.hw10.Main
 * java -jar ./target/HW1.0-maven.jar //java.lang.NoClassDefFoundError: com/google/common/collect/Lists
 * java -cp ./target/HW1.0-maven.jar;C:\Users\Michael\.m2\repository\com\google\guava\guava\25.0-jre\guava-25.0-jre.jar ru.otus.hw10.Main
 * <p>
 * To unzip the jar:
 * 7z x -oJAR ./target/HW1.0-maven.jar
 *
 * unzip -d JAR ./target/HW1.0-maven.jar
 * <p>
 * To build:
 * mvn package
 * mvn clean compile
 * mvn assembly:single
 * mvn clean compile assembly:single
 */
public class Main {
    private static final int MEASURE_COUNT = 1;

    public static void main(String... args) {
        Collection<Integer> example = new ArrayList<>();
        int min = 0;
        int max = 999_999;
        for (int i = min; i < max + 1; i++) {
            example.add(i);
        }

        List<Integer> result = new ArrayList<>();
        Collections.shuffle((List<Integer>) example);
        calcTime(() -> result.addAll(Lists.reverse((List<Integer>) example)));
        calcTimeNew(() -> result.addAll(Lists.reverse((List<Integer>) example)));
//        calcTime(() -> result.addAll((List<Integer>) example));
//        calcTimeNew(() -> result.addAll((List<Integer>) example));
    }

    private static void calcTime(Runnable runnable) {
        long startTime = System.nanoTime();
        for (int i = 0; i < MEASURE_COUNT; i++)
            runnable.run();
        long finishTime = System.nanoTime();
        long timeNs = (finishTime - startTime) / MEASURE_COUNT;
        System.out.println("Time spent: " + timeNs + "ns (" + timeNs / 1_000_000 + "ms)");
    }

    private static void calcTimeNew(Runnable runnable) {
        //long startTime = System.nanoTime();
        Instant startTime = Instant.now();
        for (int i = 0; i < MEASURE_COUNT; i++)
            runnable.run();
        //long finishTime = System.nanoTime();
        Instant finishTime = Instant.now();
        //long timeNs = (finishTime - startTime) / MEASURE_COUNT;
        // Using Instant and Duration is so much better
        long timeNs = Duration.between(startTime, finishTime).toNanos() / MEASURE_COUNT;
        System.out.println("Time spent: " + timeNs + "ns (" + timeNs / 1_000_000 + "ms)");
    }
}

