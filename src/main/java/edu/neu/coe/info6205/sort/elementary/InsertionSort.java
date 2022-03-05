/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.*;
import java.util.function.Supplier;

public class InsertionSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for any sub-classes to use.
     *
     * @param description the description.
     * @param N           the number of elements expected.
     * @param config      the configuration.
     */
    protected InsertionSort(String description, int N, Config config) {
        super(description, N, config);
    }

    /**
     * Constructor for InsertionSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public InsertionSort(int N, Config config) {
        this(DESCRIPTION, N, config);
    }

    public InsertionSort(Config config) {
        this(new BaseHelper<>(DESCRIPTION, config));
    }

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        super(helper);
    }

    public InsertionSort() {
        this(BaseHelper.getHelper(InsertionSort.class));
    }
//    public void swap(int i,int j, X[] xs) {
//        X x = xs[i];
//        xs[i]=xs[j];
//        xs[j]=x;
//    }
    //                swap(j-1,j,xs);
    /**
     * Sort the sub-array xs:from:to using insertion sort.
     *
     * @param xs   sort the array xs from "from" to "to".
     * @param from the index of the first element to sort
     * @param to   the index of the first element not to sort
     */
    public void sort(X[] xs, int from, int to) {
        final Helper<X> helper = getHelper();
        for(int i=from+1;i<to;i++) {
            for(int j=i;j>from;j--) { // more efficient way is by adding  && xs[j-1].compareTo(xs[j]) > 0
                if(helper.swapStableConditional(xs,j) == false) {
                    break;
                }
            }
        }
    }

    public static final String DESCRIPTION = "Insertion sort";

    public static <T extends Comparable<T>> void sort(T[] ts) {
        new InsertionSort<T>().mutatingSort(ts);
    }

    public static void main(String args[]) {
        try {
        int[] arr_n = new int[]{20,40,80,160,320};
        for(int j=0;j<arr_n.length;j++) {

                Thread.sleep(2000);


             Double t1 = orderedInputSort(arr_n[j]);

                Thread.sleep(2000);

            Double t2 = reverseOrderedInputSort(arr_n[j]);

                Thread.sleep(2000);

            Double t3 =  partiallyOrderedInputSort(arr_n[j]);

                Thread.sleep(2000);

            Double t4 = randomOrderedInputSort(arr_n[j]);

            Thread.sleep(2000);

            HashMap<Double,String> map = new HashMap<>();
            map.put(t1,"ordered input");
            map.put(t2,"reversed input");
            map.put(t3,"partially ordered input");
            map.put(t4,"random input");
            Double[] arr = new Double[]{t1,t2,t3,t4};
            sort(arr);
            System.out.println("The order of growth is:");
            for(int i=0;i<arr.length;i++){
                System.out.println(map.get(arr[i])+" Time: "+arr[i]);
            }
        }
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    public static double orderedInputSort(int n) {
        final Supplier<Integer[]> intsSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++){
                list.add(i); // adding values in apt order.
            }
            Integer[] xs = list.toArray(new Integer[0]);
            return xs;
        };

        InsertionSort<Integer> sorter = new InsertionSort<>();

        final double t1 = new Benchmark_Timer<Integer[]>(
                "ArrayInsertionSorter",
                (Integer[] xs) -> Arrays.copyOf(xs, xs.length),
                (Integer[] xs) -> sorter.sort(xs,0,xs.length),null

        ).runFromSupplier(intsSupplier, 1000);


        return t1;
    }

    public static double reverseOrderedInputSort(int n) {
        final Supplier<Integer[]> intsSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++){
                list.add(-i); // adding values in reverse order.
            }
            Integer[] xs = list.toArray(new Integer[0]);
            return xs;
        };

        InsertionSort<Integer> sorter = new InsertionSort<>();
        final double t1 = new Benchmark_Timer<Integer[]>(
                "ArrayInsertionSorter",
                (Integer[] xs) -> Arrays.copyOf(xs, xs.length),
                (Integer[] xs) -> sorter.sort(xs,0,xs.length),null

        ).runFromSupplier(intsSupplier, 1000);
//        System.out.println(t1);
//        for (TimeLogger timeLogger : timeLoggersLinearithmic) {
//            timeLogger.log(t1, n);
//        }

        return t1;
    }

    public static double partiallyOrderedInputSort(int n) {
        final Random random = new Random();

        final Supplier<Integer[]> intsSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n/2; i++){
                list.add(i); // adding values in apt order (partially).
            }
            for (int i = 0; i < n/2; i++){
                list.add(random.nextInt(1000)); // adding values in random order (partially).
            }
            Integer[] xs = list.toArray(new Integer[0]);
            return xs;
        };

        InsertionSort<Integer> sorter = new InsertionSort<>();
        final double t1 = new Benchmark_Timer<Integer[]>(
                "ArrayInsertionSorter",
                (Integer[] xs) -> Arrays.copyOf(xs, xs.length),
                (Integer[] xs) -> sorter.sort(xs,0,xs.length),null

        ).runFromSupplier(intsSupplier, 1000);

//        System.out.println(t1);
//        for (TimeLogger timeLogger : timeLoggersLinearithmic) {
//            timeLogger.log(t1, n);
//        }

        return t1;
    }
    public static double randomOrderedInputSort(int n) {
        final Random random = new Random();
        final Supplier<Integer[]> intsSupplier = () -> {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++){
                list.add(random.nextInt(1000)); // adding values in random order.
            }
            Integer[] xs = list.toArray(new Integer[0]);
            return xs;
        };

        InsertionSort<Integer> sorter = new InsertionSort<>();

        final double t1 = new Benchmark_Timer<Integer[]>(
                "ArrayInsertionSorter",
                (Integer[] xs) -> Arrays.copyOf(xs, xs.length),
                (Integer[] xs) -> sorter.sort(xs,0,xs.length),null

        ).runFromSupplier(intsSupplier, 1000);

//        System.out.println(t1);
//        for (TimeLogger timeLogger : timeLoggersLinearithmic) {
//            timeLogger.log(t1, n);
//        }
        return t1;
    }
}