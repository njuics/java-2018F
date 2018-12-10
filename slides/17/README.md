
## 并发编程

<small>--这可能是Slides最长的一章。</small>


---

"空间是并存事物的次序，或是同时发生的所有事物存在的次序"

--Leibniz <!-- .element align="right" -->


---

## 概念篇

---

## 进程 vs. 线程

- 进程：是系统进行资源分配和调度的一个独立单位，也是一个具有独立功能的程序；
- 线程：线程依托于进程而存在,是CPU调度和分派的基本单位,它是比进程更小的能独立运行的基本单位。线程自己基本上不拥有系统资源,只拥有一点在运行中必不可少的资源(如程序计数器,一组寄存器和栈),但是它可与同属一个进程的其他的线程共享进程所拥有的全部资源。

<small>区别在于，进程属于资源分配的单位，而线程则是作业调度的单位；进程拥有自己的地址空间，而多个线程拥有自己的堆栈和局部变量，并共享所依托于进程的资源。</small>

---

## 多线程的意义

- 多进程操作的意义在于多个进程轮流共享CPU的时间片(针对单处理器情形，多处理器可理解为并行)，属于并发操作。
- 多线程的并发操作优势在于当程序遇到阻塞的情形。当程序的某个任务因外部条件问题，导致阻塞，如果没有并发，则整个程序停止，直到外部条件发生变化，使用并发后，程序中的其他任务还可以继续执行，如果没有阻塞，在单处理器上考虑多线程并发也就没有意义了，线程之间的切换会造成大量的时间消耗。


---

## 并发 vs 并行
![](http://images.cnitblog.com/blog/94031/201307/01001508-e1a4c7ed78274ec4aa325b928af044e5.png)

---


## JVM Threads

![](https://equinoxrar.files.wordpress.com/2014/08/threads1.png?w=705)


---

## 基础篇

---

## Runnable


java.lang.Runnable

``` java
public interface Runnable {
    public abstract void run();
}

public class LiftOff implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            Thread.yield(); //后面解释
        }
    }
}

public class MainThread {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
```

<small>`Runnable`接口仅仅定义“任务”</small>

---


## Thread

java.lang.Thread

``` java
public class BasicThreads {
    public static void main(String[] args) {
        //把任务装进线程里
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("Waiting for LiftOff");
    }
}
```

<small>`Thread`对象像是运载火箭，`Runnable`的实现对象就是一个荷载（payload）</small>

---

## 或者

``` java
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        // Store the thread name:
        super(Integer.toString(++threadCount));
        start();
    }

    public String toString() {
        return "#" + getName() + "(" + countDown + "), ";
    }

    public void run() {
        while (true) {
            System.out.print(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new SimpleThread();
    }
}
```

<small>实际上</small>

<small>`public class Thread implements Runnable`</small>

---

## 多线程，走起

```java
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff");
    }
}
```

直接启动多个`Thread`

---

## 或者用ExecutorService启动

``` java
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}
```
<small>`CachedThreadPool`：根据需要创建新线程的线程池，如果现有线程没有可用的，则创建一个新线程并添加到池中，如果有被使用完但是还没销毁的线程，就复用该线程</small>

---

## 线程池？

- 在面向对象编程中，创建和销毁对象是很费时间的，因为创建一个对象要获取内存资源或者其它更多资源。
- 在Java中更是如此，虚拟机将试图跟踪每一个对象，以便能够在对象销毁后进行垃圾回收。
- 所以提高服务程序效率的一个手段就是尽可能减少创建和销毁对象的次数，特别是一些很耗资源的对象创建和销毁。
- 如何利用已有对象来服务就是一个需要解决的关键问题，其实这就是一些"池化资源"技术产生的原因。

---

## 或者另一种策略

``` java
public class FixedThreadPool {
    public static void main(String[] args) {
        // Constructor argument is number of threads:
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}
```
<small>创建一个固定线程数的线程池，在任何时候最多只有n个线程被创建。如果在所有线程都处于活动状态时，有其他任务提交，他们将等待队列中直到线程可用。如果任何线程由于执行过程中的故障而终止，将会有一个新线程将取代这个线程执行后续任务。</small>

<small>如果需要获得异步执行的任务的结果怎么办？</small><!-- .element: class="fragment" -->



---

## Callable

``` java

public interface Callable<V> {
    V call() throws Exception;
}

class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() {
        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results =
                new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++)
            results.add(exec.submit(new TaskWithResult(i)));
        for (Future<String> fs : results)
            try {
                // get() blocks until completion:
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
    }
}
```


---

## Future

``` java
class MyCallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("做一些耗时的任务...");
        Thread.sleep(5000);
        return "OK";
    }
}

public class FutureSimpleDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());

        System.out.println("dosomething...");

        System.out.println("得到异步任务返回结果：" + future.get());
        System.out.println("Completed!");
    }
}
```

<small>当调用`Future`的`get()`方法以获得结果时，当前线程就开始阻塞，直接`call()`方法结束返回结果。</small>

---

## SLEEP

``` java
public class SleepingTask extends LiftOff {
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.print(status());
                // Old-style:
                // Thread.sleep(100);
                // Java SE5/6-style:
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            //可能被打断
            System.err.println("Interrupted");
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new SleepingTask());
        exec.shutdown();
    }
}
```

<small>运行结果看起来很均衡，但实际并不完全如此...</small> <!-- .element: class="fragment" -->

跟`yeild()`语义不一样

---

## Yeild 让位 

- `yield`和`sleep`的主要区别是
  - yield方法会临时暂停当前正在执行的线程，来让有同样优先级的正在等待的线程有机会执行
  - 如果没有正在等待的线程，或者所有正在等待的线程的优先级都比较低，那么该线程会继续运行
  - 执行了yield方法的线程什么时候会继续运行由线程调度器来决定，不同的厂商可能有不同的行为
  - yield方法不保证当前的线程会暂停或者停止，但是可以保证当前线程在调用yield方法时会放弃CPU。


---

## Priority

``` java
public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization 后面再解释
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            // An expensive, interruptable operation:
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(
                    new SimplePriorities(Thread.MIN_PRIORITY));
        exec.execute(
                new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
```

改变线程优先级这件事可以做，但尽量不要做<!-- .element: class="fragment" -->

---


## Deamon 线程

``` java
public class SimpleDaemons implements Runnable {
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true); // Must call before start()
            daemon.start();
        }
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(99);
    }
}
```

后台运行线程，当所有非后台线程结束时，应用退出，所有Deamon线程被杀😢

---

## 小结一下

Java关于线程编程的抽象


`Thread`对象像是运载火箭，`Runnable`的实现对象就是一个荷载（payload）


Runnable/Callable --> Task <!-- .element: class="fragment" -->

Thread --> let tasks go <!-- .element: class="fragment" -->


---

## 中级篇


---

## Join

``` java
class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            print(getName() + " was interrupted. " +
                    "isInterrupted(): " + isInterrupted());
            return;
        }
        print(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            print("Interrupted");
        }
        print(getName() + " join completed");
    }
}

public class Joining {
    public static void main(String[] args) {
        Sleeper
                sleepy = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 1500);
        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }
}
```

---

## Uncaught Exceptions

```java
public class ExceptionThread implements Runnable {
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}

public class NaiveExceptionHandling {
    public static void main(String[] args) {
        try {
            ExecutorService exec =
                    Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (RuntimeException ue) {
            // This statement will NOT execute!
            System.out.println("Exception has been handled!");
        }
    }
}
```

```
Exception in thread "pool-1-thread-1" java.lang.RuntimeException
	at concurrency.ExceptionThread.run(ExceptionThread.java:7)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
```

---

UncaughtExceptionHandler

```java
class ExceptionThread2 implements Runnable {
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println(
                "eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements
        Thread.UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler());
        System.out.println(
                "eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(
                new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
        exec.shutdown();
    }
}
```

---

## 或者简单一点

``` java
public class SettingDefaultHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}
```

---

## 资源共享问题

一个葫芦娃在战场上可以随意走，多个葫芦娃在战场上是随意走，那就会撞头。

因为一个空间位置，是不能共享的。

---

## 示例

EvenGenerator

``` java
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    // Allow this to be canceled:
    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}

public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public int next() {
        ++currentEvenValue; // Danger point here!
        ++currentEvenValue;
        return currentEvenValue;
    }
}
```

---

EvenChecker

``` java

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                generator.cancel(); // Cancels all EvenCheckers
            }
        }
    }

    // Test any type of IntGenerator:
    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++)
            exec.execute(new EvenChecker(gp, i));
        exec.shutdown();
    }

    // Default value for count:
    public static void test(IntGenerator gp) {
        test(gp, 10);
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }

}

```

---

## 解决方法

对资源加锁，使得对资源的访问顺序化，确保在某一时刻只有一个任务在使用共享资源（使其互斥）

Mutual Exclusion （Mutex）


---

## Synchronized

``` java
public class
SynchronizedEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield(); 
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}
```

---

## Lock

``` java

public class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    public int next() {
        //加锁
        lock.lock();
        try {
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            return currentEvenValue;
        } finally {
            //一定要用try-catch的finally去释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
} 
```

<small>`ReentrantLock`允许你尝试加锁</small>

<small>`lock.tryLock(2, TimeUnit.SECONDS)`</small>

<small>如果失败做其他处理</small>


---

## Critical Sections 临界区

```java
synchronized(syncObject){
    //balabala
}
```

加锁代码片段


---

## 示例

``` java

// Synchronize the entire method:
class PairManager1 extends PairManager {
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

// Use a critical section:
class PairManager2 extends PairManager {
    public void increment() {
        Pair temp;
        synchronized (this) {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}
```

---

## Lock on Object
![](http://4.bp.blogspot.com/-kRUcoXzDmAM/T48eQOjNzuI/AAAAAAAAA38/TaU6Eub90uA/s1600/Object-Monitor-Threads.PNG)

---

## Thread local Storage

![](https://i.stack.imgur.com/Dhws6.jpg)


--- 

## 示例

``` java
class Accessor implements Runnable {
    private final int id;

    public Accessor(int idn) {
        id = idn;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    public String toString() {
        return "#" + id + ": " +
                ThreadLocalVariableHolder.get();
    }
}

public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value =
            new ThreadLocal<Integer>() {
                private Random rand = new Random(47);

                protected synchronized Integer initialValue() {
                    return rand.nextInt(10000);
                }
            };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Accessor(i));
        TimeUnit.SECONDS.sleep(3);  // Run for a while
        exec.shutdownNow();         // All Accessors will quit
    }
}
```

---

## 从竞争到协作

```java
wait();
notify();
notifyAll();
```

`Object`类型上的三个方法

---

## 还是看个例子

```java
class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true; // Ready to buff
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false; // Ready for another coat of wax
        notifyAll();
    }

    public synchronized void waitForWaxing()
            throws InterruptedException {
        while (waxOn == false)
            wait();
    }

    public synchronized void waitForBuffing()
            throws InterruptedException {
        while (waxOn == true)
            wait();
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                printnb("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                printnb("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        exec.shutdownNow(); // Interrupt all tasks
    }
}
```

---

## 线程状态

![](https://www.w3resource.com/w3r_images/java-threadclass-methods-and-threadstates-jimage1.png)

---

## wait和sleep的区别

- 调用wait方法时，线程在等待的时候会释放掉它所获得的monitor，但是调用Thread.sleep()方法时，线程在等待的时候仍然会持有monitor或者锁，wait方法应在同步代码块中调用，但是sleep方法不需要
- Thread.sleep()方法是一个静态方法，作用在当前线程上；但是wait方法是一个实例方法，并且只能在其他线程调用本实例的notify()方法时被唤醒

如果需要暂停线程一段特定的时间就使用sleep()方法，如果要实现线程间通信就使用wait()方法。

---


## 高级篇

---

## 高级设施

- `CountDownLatch`
- `CyclicBarrier`
- `DelayQueue`
- `PriorityBlockingQueue`
- `ScheduledExector`
- `Semaphore`
- `Exchanger`



---

让你的葫芦娃们抖动起来！


---

# END