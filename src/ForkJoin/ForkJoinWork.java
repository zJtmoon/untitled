package ForkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author zjt
 * @desc：
 * @Date create in 2019-12-08
 */
//JUC并发编程
public class ForkJoinWork extends RecursiveTask<Long> {
    private Long start;//起始值
    private Long end;//结束值
    public static final Long critcal = 10000000L;//临近点

    public ForkJoinWork(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //计算
    @Override
    protected Long compute() {
        System.out.println("zzzzzzzzzzzzzzz");
        //是否拆分完毕
        long length = end - start;
        if (length < critcal) {
            Long sum = 0L;
            for (Long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        } else {
            //拆分任务；
            Long middle = (end + start) / 2;
            ForkJoinWork right = new ForkJoinWork(start, middle);//第一条线
            right.fork();
            ForkJoinWork left = new ForkJoinWork(middle + 1, end);//第二条线
            left.fork();

            //合并
            long s = right.join();
            long s2 = left.join();
            long sum1 = s + s2;
            return sum1;
        }
    }
}
