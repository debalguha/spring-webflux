package com.vinsguru.webfluxdemo;

/**
 * @author debal
 */
public class Check {
    static int limiter(int val) {
        if(val == 6) {
            return 3;
        } else {
            return 3 * limiter(val + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(limiter(5));
        System.out.println(limiter(3));
    }
}