package com.example.consultants.week2weekend.job;

public class FibTask {

    public static int start(int input) throws InterruptedException {
        return fibonacci(input);
    }

    static int fibonacci(int n)
    {
        if (n <= 1)
            return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
