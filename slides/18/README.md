
## Lambda Expressions

さいしゅうかい


---

## ActionListener

```java

public class TestActionEvent {  
  
    public static void main(String[] args) {  
        ...
        Button b = new Button("Press me");  
        Monitor mo = new Monitor();  
        b.addActionListener(mo);  
        ...
    }  
}  
  
static class Monitor implements ActionListener {  
  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        System.out.println(e);  
    }  
  
} 

```

`Button`需要一个`ActionListener`

---

## 使用匿名类

```java
public class TestActionEvent {  
  
    public static void main(String[] args) {  
        ...
        Button b = new Button("Press me");  
        b.addActionListener(new ActionListener(){
            @Override  
            public void actionPerformed(ActionEvent e) {  
                System.out.println(e);  
            } 
        } );  
        ...
    }  
}  
  
```

`Button`并不在意`Listener`是谁，要的一个函数处理Action

---

## ActionListener

<small>
https://docs.oracle.com/javase/9/docs/api/java/awt/event/ActionListener.html</small>

`ActionListener`只定义了一个函数接口

---

## 那直接传个函数不好么？

```java
public class TestActionEvent {  
    public static void main(String[] args) {  
        Button b = new Button("Press me");  
        b.addActionListener((e) -> {
            System.out.println(e)
        });  
    }  
}  
```

`(e) -> {System.out.println(e)}`

---

## 此事并不稀奇

``` c
void MyFun(int x);   
void (*FunP)(int ); 

int main(int argc, char* argv[])
{
   MyFun(10);    
   FunP=&MyFun; 
   (*FunP)(20); 
}

void MyFun(int x) 
{
   printf(“%d\n”,x);
}
```

C Pointer

---

## 真的不稀奇

 ``` swift
 func backward(_ s1: String, _ s2: String) -> Bool {
    return s1 > s2
}
var reversedNames = names.sorted(by: backward)
```

Swift Clousure

---

## 真的一点不稀奇

``` objectivec
float price = 1.99; 
float (^finalPrice)(int) = ^(int quantity) {
	// Notice local variable price is 
	// accessible in the block
	return quantity * price;
};
int orderQuantity = 10;
NSLog(@"Ordering %d units, final price is: $%2.2f", orderQuantity, finalPrice(orderQuantity));
```
Objective-C block

---


## Java？


Java里将普通的方法或函数像参数一样传值并不简单

Java 世界是严格地以名词为中心的


[Execution in the Kingdom of Nouns](http://steve-yegge.blogspot.com/2006/03/execution-in-kingdom-of-nouns.html)



---


## Imperative versus Declarative

- <small>Imperative: is a style of programming where you program the  algorithm with control flow and explicit steps. </small>
- <small>Declarative:  is a style of programming where you declare what needs be done without concern for the control flow. </small>

- <small>Functional programming: is a declarative programming paradigm that treats computation as a series of functions and avoids state and mutable data to facilitate concurrency</small>

Java缺少函数式编程特点， 为此Java 8 增加了一个语言级的新特性，名为Lambda表达式。

---

## Lambda 表达式

Lambda表达式是一种匿名函数(并不完全正确)，简单地说，它是没有声明的方法，也即没有访问修饰符、返回值声明和名字。

<small>你可以将其想做一种速记，在你需要使用某个方法的地方写上它。当某个方法只使用一次，而且定义很简短，使用这种速记替代之尤其有效，这样，你就不必在类中费力写声明与方法了。</small>


---

## 语法

```java
(arg1, arg2...) -> { body }

(type1 arg1, type2 arg2...) -> { body }
```

---

## 例如

``` java
(int a, int b) -> {  return a + b; }

() -> System.out.println("Hello World");

(String s) -> { System.out.println(s); }

() -> 42

() -> { return 3.1415 };
```


---

## Lambda 表达式的结构

- <small>一个Lambda表达式可以有零个或多个参数</small>
- <small>参数的类型既可以明确声明，也可以根据上下文来推断。例如：`(int a)`与`(a)`效果相同</small>
- <small>所有参数需包含在圆括号内，参数之间用逗号相隔。例如：`(a, b)` 或 `(int a, int b)` 或 `(String a, int b, float c)`</small>
- <small>空圆括号代表参数集为空。例如：`() -> 42`</small>
- <small>当只有一个参数，且其类型可推导时，圆括号`（）`可省略。例如：`a -> return a*a`</small>
- <small>Lambda 表达式的主体可包含零条或多条语句</small>
- <small>如果Lambda表达式的主体只有一条语句，花括号`{}`可省略。匿名函数的返回类型与该主体表达式一致</small>
- <small>如果Lambda表达式的主体包含一条以上语句，则表达式必须包含在花括号`{}`中（形成代码块）。匿名函数的返回类型与代码块的返回类型一致，若没有返回则为空</small>

---

## 函数式接口

- 函数式接口是只包含一个抽象方法声明的接口
  - <small>`java.lang.Runnable`就是一种函数式接口，在`Runnable`接口中只声明了一个方法`void run()`</small>
  - <small>相似地，`ActionListener`接口也是一种函数式接口，我们使用匿名内部类来实例化函数式接口的对象，有了Lambda表达式，这一方式可以得到简化。</small>
- 每个 Lambda 表达式都能隐式地赋值给函数式接口

---

## 例如

```java
Runnable r = () -> System.out.println("hello world");
```

Or even

```java
new Thread(
   () -> System.out.println("hello world")
).start();
```
<small>根据线程类的构造函数签名`public Thread(Runnable r) { }`，将该Lambda表达式赋给`Runnable`接口。</small>

---

## 其他常见的函数式接口

```java
Consumer<Integer>  c = (int x) -> { System.out.println(x) };

BiConsumer<Integer, String> b = (Integer x, String y) -> System.out.println(x + " : " + y);

Predicate<String> p = (String s) -> { s == null };
```

---

## 定义自己的函数式接口

`@FunctionalInterface`是 Java 8 新加入的一种接口，用于指明该接口类型声明是根据 Java 语言规范定义的函数式接口。

```java
@FunctionalInterface public interface WorkerInterface {
   public void doSomeWork();
}
```

---

## 使用

```java
@FunctionalInterface
public interface WorkerInterface {
   public void doSomeWork();
}

public class WorkerInterfaceTest {
    public static void execute(WorkerInterface worker) {
        worker.doSomeWork();
    }
    public static void main(String [] args) {
        execute(new WorkerInterface() {
            @Override
            public void doSomeWork() {
                System.out.println("Worker invoked using Anonymous class");
            }
        });

        execute( () -> System.out.println("Worker invoked using Lambda expression") );
    }

}
```

---

## 参考文献

Lambda Quick Start

http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html
---

# 終わり


