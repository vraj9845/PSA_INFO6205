package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class HWQUPC_Solution {
    public static void main(String[] args) {
        int[] inp_values = new int[]{6,100,200,400,800,1000,2000,5000,10000};
            for(int i=0;i<inp_values.length;i++) {
                     countLooped(inp_values[i]);
                }
            }

    public static int[] count(int n) {
        UF h = new UF_HWQUPC(n,true);
        int connections=0;
        int count =0;
        Random rand = new Random();

            while(connections < n-1) {
                int number1=rand.nextInt(n) ;
                int number2=rand.nextInt(n);
                count = count + 1;
                if(h.isConnected(number1,number2)==false && number1!=number2){
                    h.union(number1,number2);
                    connections = connections +1;
                }
            }
//            System.out.println(connections);
        int[] arr = new int[]{count,connections};
        return arr;
    }

    public static void countLooped(int n) {
        int total =0;
        int connections =0;
        for (int i=0;i<10000;i++) {
            int[] arr = count(n);
            total = total+arr[0];
            connections = arr[1];
        }
        total = total/10000;
        System.out.println("Input:"+n+"\tConnections:" + connections+"\tRandom Pairs needed:"+total);
    }
}
