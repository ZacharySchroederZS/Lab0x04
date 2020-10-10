package com.company;
import java.lang.management.*;

public class Main {
    static int n1=0,n2=1,n3=0;
    private static final int MAX_N = 100000000; //Size of Fibonacci

    //Loop with recursion page 57
    public static long fibLoop(int N){
        if(N == 0) return 0;
        if(N == 1) return 1;
        return fibLoop(N-1) + fibLoop(N-2);
    }
    //Recursive
    public static void fibRecur(int count){
        if(count>0){
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            System.out.print("\n"+n3);
            fibRecur(count-1);
        }
    }

    //Cache
    private final static long[] fibCache = new long[MAX_N];

    public static long fibCache(int n) {
        if (n <= 1) {
            return n;
        } else if (fibCache[n] != 0) {
            // we found previous computed result
            // just return it.
            return fibCache[n];

        } else {
            long l = fibCache(n - 1) + fibCache(n - 2);
            fibCache[n] = l;
            return fibCache[n];
        }
    }

    //Matrix
    public static void fibMatrix(int n){
        int previousNumber = 0;
        int nextNumber = 1;

        for (int i = 1; i <= n; ++i)
        {
            System.out.print(previousNumber+"\n");

            int sum = previousNumber + nextNumber;
            previousNumber = nextNumber;
            nextNumber = sum;
        }
    }




    public static void main(String[] args) {

        long timeStampBefore = getCpuTime();
        long timeStampAfter = getCpuTime();
        long timeMeasureForNothing = timeStampAfter - timeStampBefore;
        System.out.println(timeMeasureForNothing);

        timeStampBefore = getCpuTime();

/*
        //Loop
        for(int i = 0; i < MAX_N; i++){
            System.out.println(fibLoop(i));
        }
/*
        //Recursive
        System.out.print(n1+"\n"+n2);
        fibRecur(MAX_N-2);
*/
/*
        //Cache
        System.out.print("\n0\n");
        for (int i = 1; i < MAX_N; i++) {
            System.out.print(fibCache(i) + "\n");
        }
*/

        //Matrix fastest
        fibMatrix(MAX_N);

        timeStampAfter = getCpuTime();
        long timeMeasureForPrintln = timeStampAfter - timeStampBefore;
        System.out.println("\nmilliseconds: "+timeMeasureForPrintln);
    }
    public static long getCpuTime( ) {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;
    }
}
