# Java语言基础


---

## 符号

- 标识符
- 分隔符

---

## 标识符

- 程序中要用到许多名字，诸如类、对象、变量、方法等。标识符就是用来标识它们的唯一性和存在性的名字。
- 标识符的长度是任意的,标识符分为两类：
  - 保留字：是Java预定义的标识符，具有特定的含义，又称关键字。
  - 用户定义标识符：是程序设计者根据自己的需要为定义的类、对象、变量、方法等的命名。
- Java采用Unicode字符集，由16位构成，可以记录表达任何人类语言。

~~

## 用户自定义标识符的定义规则

- 以字母、下划线或$符开头的字母、下划线、数字、 $符的序列。
- 一些由开发环境自动生成的名称中会带有$符或下划线，因此虽然规则允许，但是自定义的变量名称中应尽量避免使用$符或下划线。
- 标识符区分大小写。
- 标识符不能与保留字同名。
- 标识符遵守先定义后使用的原则。
- 虽然`true`、`false`和`null`并不是关键字，但其代表的是值，也不可以用

---

## 分隔符

- 规定任意两个相邻标识符、数、保留字或两个语句之间必须至少有一个分隔符，以便编译程序能识别：
  - 空白分隔符：空格、TAB、换行符与回车符
  - 普通分隔符
    - `{ }` 用来定义复合语句、类体、方法体以及进行数组的初始化等。
    - `;` 表示一条语句的结束。
    - `,` 用来分隔变量的说明和方法的参数等。
    - `:` 有些地方会用到，你自会知道。


---

## 注释

- 注释用来对程序中的代码做出解释。注释部分对程序的执行不产生任何影响，可增加程序的可读性，有利于程序的修改、调试、交流。

```java
// 这是一个单行注释
```

```java
/* 这是也是注释 *／
```

```java
/* 
这是还是注释
可以多行 
*／
```

```java
/**
* 这也是多行注释
* 但称为说明注释，跟代码文档相关
*/
```

---

## 数据类型

- 基本数据类型
- 复合数据类型

---

## 基本数据类型

- 数值型
  - 整型
    - 字节型：`byte`，短整型：`short`，整型：`int`，长整型：`long`
  - 浮点型
    - 单精度：`float`，双精度：`double`
- 字符型：`char`
- 布尔型：`boolean`

---

## 数据存储空间大小

| 类型        | 内存          | 数值范围  |
| - |:-:| -:|
| `byte`      | 1 | `-128`～`127` |
| `short`      | 2      |   `-32768`～`32767` |
| `int` | 4      |   `-2147483648`～`2147483647` |
| `long` | 8      |    whatever |
| `float` | 4      |    who cares |
| `double` | 8      |    holy crap |
| `char` | 2      |    `\u0000`~`\uFFFF`|
| `boolean` | 1      |   `true` or `false` |

---

## SizeOf

```java
  private static void calSize() {  
        System.out.println("Integer: " + Integer.SIZE/8);           // 4  
        System.out.println("Short: " + Short.SIZE/8);               // 2
        System.out.println("Long: " + Long.SIZE/8);                 // 8  
        System.out.println("Byte: " + Byte.SIZE/8);                 // 1  
        System.out.println("Character: " + Character.SIZE/8);       // 2  
        System.out.println("Float: " + Float.SIZE/8);               // 4  
        System.out.println("Double: " + Double.SIZE/8);             // 8  
    }  
```

---

## 常量

- 常量是指在程序运行过程中其值不变的量
- 常量通过用关键字`final`来实现声明

```java
final int WIDTH=100;
```

此处`100`称为字面值(Literal)

---

## 变量

- 变量用来存放指定类型的数据，其值在程序运行过程中是可变的。
- 使用一个变量之前必须先声明它。一方面给该变量分配内存空间，另一方面防止在以后使用此变量时因错误输入而对不存在的变量进行操作。

```java
int a;
boolean found=false;
float b=0f,c,d=1.3f;
```

---

## 变量作用域

- 变量有一定的生存期和有效范围，变量的作用域指明可访问该变量的一段代码
  - 全局变量：可以在整个类中被访问；
  - 局部变量：在方法或方法的一个代码块中声明，它的作用域为它所在的代码块；
  - 类变量：在类中声明，而不是类的某个方法中声明，作用域为整个类；
  - 方法参数（变量）：作用域为传递给的那个方法；
  - 异常处理参数：传递给异常处理代码，作用域是异常处理部分。

---

## 运算符

- 程序中用来处理数据、表示数据运算、赋值和比较的符号称为运算符，参与运算的数据称为操作数。
  - 算术运算符：`+`,`-`,`*`,`/`,`%`,`++`,`--`...
  - 比较运算符: `>`,`<`,`==`,`!=`,`>=`...
  - 逻辑运算符：`!`,`&`,`|`,`^`,`&&`,`||`...
  - 位运算符: `~`,`&`,`|`,`^`,`<<`,`>>`,`>>>`
  - 条件运算符：`?:`
  - 赋值运算符：`=`,`+=`,`-=`,`/=`...

---

## 表达式（Expression）

- 表达式是由操作数和运算符按照一定的语法形式组成的符号序列，计算出单一值。Java允许使用各种较小的表达式构成复合表达式，但表达式各个部分的数据类型要匹配。

``` java
a=0
```

``` java
value1==value2
```

``` java
1 * 2 * 3
```

---

## 语句（Statement）

- 表达式加上“`;`”构成语句（表达式语句）
```java
a=0;
```
- 还有另外几种语句：声明语句、控制流语句、方法调用语句等

```java
int cadence = 0;
anArray[0] = 100;
System.out.println("Element 1 at index 0: " + anArray[0]);

int result = 1 + 2; // result is now 3
if (value1 == value2)
    System.out.println("value1 == value2");
```

---

## 块（Block）

- 是位于成对大括号之间的零个或多个语句的语句组，可以在允许使用单一语句的任何位置使用块。

``` java
class BlockDemo {
     public static void main(String[] args) {
          boolean condition = true;
          if (condition) { // begin block 1
               System.out.println("Condition is true.");
          } // end block one
          else { // begin block 2
               System.out.println("Condition is false.");
          } // end block 2
     }
}
```

---

## 编码规范

- 规范原则
  - 尽量使用完整的英文描述符；
  - 采用大小写混合使名字可读，采用适用于相关领域的术语；
  - 尽量少用缩写，若已使用尽量明智，且在整个文件或工程中通用；
  - 避免使用长的和类似的名字，或仅仅是大小写不同的名字；
  - 除静态常量外，尽量少用下划线。

~~

- 约定细则
  - 源文件命名规则
    - 源程序中包含有公共类的定义，源文件名必须与该公共类的名字一致。在一个源程序中至多只能有一个公共类的定义；
    - 源程序中不包含公共类，则该文件名只要和某个类名字相同即可；
    - 源程序中有多个类的定义，编译时将会为每个类生成一个class文件

~~

- 约定细则
  - 包：包名是全小写的名词，中间可以由点分隔开，如java.awt.event。
  - 类／接口：类／接口名首字母大写，若由多个单词合成一个类名，要求每个单词的字母也要大写，如MyFirstJava
  - 方法：由多个单词组成的方法名首字母小写，中间的每个单词首字母大写，如isButtonPressed。

~~

- 约定细则
  - 变量： 一般全小写，如length
  - 常量：一般全大写，如果由多个单词组成则中间用下划线相连。如果是对象类型的常量，则是大小写混合，由大写字母把单词隔开，如STR_LENGTH
  - 组件：使用完整的英文描述来说明组件的用途，尾部应该加上组件类型，如okButton。

---

### 示例

```java
/*
  Find Largest and Smallest Number in an Array Example
  This Java Example shows how to find largest and smallest number in an
  array.
*/
public class FindLargestSmallestNumber {
        public static void main(String[] args) {
                //array of 10 numbers
                int numbers[] = new int[]{32,43,53,54,32,65,63,98,43,23};
                //assign first element of an array to largest and smallest
                int smallest = numbers[0];
                int largetst = numbers[0];
                for(int i=1; i< numbers.length; i++)
                {
                        if(numbers[i] > largetst)
                                largetst = numbers[i];
                        else if (numbers[i] < smallest)
                                smallest = numbers[i];
                }
                System.out.println("Largest Number is : " + largetst);
                System.out.println("Smallest Number is : " + smallest);
        }
}

```

---

## 参考

Language Basics

<small> https://docs.oracle.com/javase/tutorial/java/nutsandbolts/index.html</small>

---

## 作业

<small>https://github.com/njuics/java-2018f-homework/tree/master/20180911</small>

---
# END