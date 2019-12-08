package ForkJoin;

/**
 * @author zjt
 * @desc：
 * @Date create in 2019-12-08
 */

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * 测试3种结果
 *  1。正常
 *  2。forkjoin
 *  3。stream
 */
public class ForkJoinWorkDemo {
    public static void main(String[] args) {
          test();
          test2();
          test3();

    }
    public static void test(){
        long l=System.currentTimeMillis();
        //ForkPOOL
        ForkJoinPool forkJoinPool=new ForkJoinPool(4);   //通过ForkJoinPool执行代码
        ForkJoinWork task=new ForkJoinWork(0L,1000000000L);
        Long invoke=forkJoinPool.invoke(task);
        long l2=System.currentTimeMillis();
        System.out.println(l2-l+"        "+invoke);
    }

    /**
     * 普通线程实现
     */
    public static void test2(){
        Long x=0L;
        Long y=1000000000L;
        long l=System.currentTimeMillis();
        for (Long i = x; i <=y; i++) {
            x +=i;
        }
        long l2=System.currentTimeMillis();
        System.out.println(l2-l+"     "+x);
    }

    /**
     * Stream计算,特别像mapreduce了
     */

    public static void test3() {
        Long x=0L;
        Long y=1000000000L;
        long l=System.currentTimeMillis();
        long reduce= LongStream.rangeClosed(0,1000000000L).parallel().reduce(0,Long::sum);
        long l2=System.currentTimeMillis();
        System.out.println(l2-l+"     "+reduce);

    }
}
