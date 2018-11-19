
## 图形化

<small>"Make simple things easy, and difficult things possible."</small>

<small>https://docs.oracle.com/javase/tutorial/uiswing/index.html</small>
<small>https://docs.oracle.com/javase/tutorial/2d/index.html</small>
<small>https://docs.oracle.com/javase/8/javase-clienttechnologies.htm</small>

🚺

---

## JAVA GUI......过时了吗？

![](images/Java2D.jpeg)<!-- .element height="60%" width="60%" -->

---

## JAVA GUI......过时了吗？

![](images/SwingSet.jpeg)<!-- .element height="60%" width="60%" -->

---

## JAVA GUI......过时了吗？

![](images/JavaFX-Modena.png)<!-- .element height="60%" width="60%" -->

---

## JAVA GUI......过时了吗？

![](images/JavaFX-Ensemble8.png)<!-- .element height="60%" width="60%" -->

---

## JAVA GUI......过时了吗？

![](images/minecraft.jpg)<!-- .element height="60%" width="60%" -->


---

## Java GUI APIs

- Java 1.0: AWT APIs
- Java 1.1: AWT APIs extensive
- Java 2: JFC - Java Foundation Classes
  + AWT
  + Swing
  + Java2D APIs
- JDK 7 + 
  + JavaFX

---

## 图形界面设计基础

- AWT: Abstract Window Toolkit，依赖于主平台绘制用户界面组件
  + java.awt.*
- Swing: AWT的改良版，在主平台提供的窗口中绘制和管理界面组件
  + javax. swing.*
  + java.awt.event.*
- JavaFX: 应用于创建Rich Internet Applications (RIAs)
  + javafx.application.*
  + javafx.scene.*

---

## AWT

- The AWT is written in ... C
- For every AWT component, there is a native peer.
- Flaws
  + Graphic OS's vary in behavior
  + Performance - using native controls is actually slower
- AWT controls are called <font color=red>HeavyWeight</font>

---

## Swing APIs

- 三种类或接口
  + ***javax.swing.JComponent*** 及其子类（J-）
  + 提供重要服务的非可见支持类（no J-）
  + 相关接口

- 轻量级组件 vs 重量级组件
  + 重量级组件：在底层的本地窗口支持操作系统中有一个对等组件
  + 轻量级组件：没有本地对等组件

---

## AWT vs. Swing
<br/>

![](images/SwingAWT.jpeg)<!-- .element height="60%" width="60%" -->

---

## 组件和容器

- <font size=6>组件(Component)是图形界面的基本元素，用户可以直接操作，例如按钮。容器(Container)是图形界面的复合元素，容器可以包含组件，例如面板。 </font>

- <font size=6>为了统一管理组件和容器，为所有组件类定义超类，把组件的共有操作都定义在Component类中。同样，为所有容器类定义超类Container类，把容器的共有操作都定义在Container类中。例如，Container类中定义了add()方法，大多数容器都可以用add()方法向容器添加组件。</font>

- <font size=6>Component、Container和Graphics类是AWT库中的关键类。为能层次地构造复杂的图形界面，容器被当作特殊的组件，可以把容器放入另一个容器中。</font>

---

## GUI Class Hierarchy

![](images/GuiHierarchy.jpeg)<!-- .element height="60%" width="60%" -->

---

## Container Classes

![](images/ContainerClasses.jpeg)<!-- .element height="60%" width="60%" -->

---

## Swing GUI Components

![](images/SwingGUI.jpeg)<!-- .element height="60%" width="60%" -->

---

## 事件驱动程序设计

- 事件
  + <font size=6>图形界面上的事件是指在某个组件上发生用户操作。</font>

- 事件源
  + <font size=6>产生事件的组件为事件源。</font>

- 监视器
  + <font size=6>对事件作监视的对象称为监视器，监视器提供响应事件的处理方法。为了让监视器与事件对象关联起来，需要对事件对象作监视器注册，告诉系统事件对象的监视器。</font>

---

## 事件驱动程序设计

- 事件处理
  - Java 1.0 Hierarchical event model
  - Java 1.1 Delegation event model

---

## Event Classes

java.util.EventObject

![](images/EventClasses.jpeg)<!-- .element height="60%" width="60%" -->

---

## 组件和事件类型

![](images/CompEvents.jpeg)<!-- .element height="60%" width="60%" -->


---

## 监听器类型

![](images/Listeners.jpeg)<!-- .element height="50%" width="50%" -->

---

## Delegation Model

![](images/DEM.png)<!-- .element height="50%" width="50%" -->

+ <font size=5>当事件源对象发生了某一事件后，事件信息将被打包为ActionEvent类，并发送给事件监听器，而后事件监听器将做出相应的处理（执行actionPerformed()函数）。实现了特定接口的事件监听器为了对事件源对象的某一特定事件进行监测，因此必须进行注册。即告知事件源对象已被监听。</font>


---

## Example

```java
/* 
 * 事件监听的四步： 
 * 1.要有事件源对象：button 
 * 2.要发生某种事件（类）：ActionEvent，封装了事件的信息 
 * 3.要有监听器（接口）：Monitor  接到事件后进行处理 
 * 4.要把监听器注册到事件源对象上 b.addActionListener(mo); 
 * 事件与监听器一一对应 
 * 取决也事件源对象能添加哪种事件（注册哪种事件） 
 */  
import java.awt.BorderLayout;  
import java.awt.Button;  
import java.awt.Frame;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
public class TestActionEvent {  
  
    public static void main(String[] args) {  
        Frame f = new Frame("Test");  
        Button b = new Button("Press me");  
        Monitor mo = new Monitor();  
        b.addActionListener(mo);  
        b.setActionCommand("GAME OVER!");
        f.add(b, BorderLayout.CENTER);  
        f.pack();  
        f.setVisible(true);  
    }  
  
}  
  
class Monitor implements ActionListener {  
  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        System.out.println("a button has been pressed\n" + 
        "The relative info is:" + e.getActionCommand());  
    }  
  
} 

```

---

## Delegation Model with Adapter

![](images/DelegationAdapter.jpeg)<!-- .element height="60%" width="60%" -->

---

## Example

```java
class Terminator extends WindowAdapter {
  public void windowClosing (WindowEvent e) {
    system.exit(0);
  }
}

```

---

## 示例: Containers (top level)

![](images/Containers1.jpeg)<!-- .element height="50%" width="50%" -->

---

## 示例: Containers (general)

![](images/Containers2.jpeg)<!-- .element height="60%" width="60%" -->

---

## 示例: Containers (special)

![](images/Containers3.jpeg)<!-- .element height="60%" width="60%" -->

---

## 示例: Basic Controls

![](images/BasicControls.jpeg)<!-- .element height="60%" width="60%" -->

---

## 示例: Uneditable Displays

![](images/Uneditable.jpeg)<!-- .element height="60%" width="60%" -->

---

## 示例: Editable Formatted Displays

![](images/Editable.jpeg)<!-- .element height="60%" width="60%" -->

---

## 框架窗口

- 三种窗口：
  + Applet窗口：Applet类管理这个窗口，当应用程序程序启动时，由系统创建和处理；
  + 框架窗口(JFrame)：这是通常意义上的窗口，它支持窗口周边的框架、标题栏，以及最小化、最大化和关闭按钮；
  + 一种无边框窗口(JWindow)：没有标题栏，没有框架，只是一个空的矩形。


---

## 标签和按钮

- 标签(JLabel)是最简单的Swing组件。标签对象的作用是对位于其后的界面组件作说明。可以设置标签的属性，即前景色，背景色、字体等，但不能动态地编辑标签中的文本。

- 按钮(JButton)在界面设计中用于激发动作事件。按钮可显示文本，当按钮被激活时，能激发动作事件。


---

## 面板

- 面板是一种通用容器，JPanel的作用是实现界面的层次结构，在它上面放入一些组件，也可以在上面绘画，将放有组件和有画的JPanel再放入另一个容器里。JPanel的默认布局为FlowLayout。
- JScrollPane是带有滚动条的面板。JScrollPane是Container类的子类，也是一种容器，但是只能添加一个组件。JScrollPane的一般用法是先将一些组件添加到一个JPanel中，然后再把这个JPanel添加到JScrollPane中。

---

## 布局设计

- FlowLayout：依次放置组件。（panel，applet）
- BorderLayout：将组件放置在边界上。(window, frame)
- CardLayout：将组件像扑克牌一样叠放，而每次只能显示其中一个组件。
- GridLayout：将显示区域按行、列划分成一个个相等的格子，组件依次放入这些格子中。
- GridBagLayout：将显示区域划分成许多矩形小单元，每个组件可占用一个或多个小单元。

---

## 如何选择

- 用最大空间显示每个组件：BorderLayout, GridBagLayout
- 按照组件大小在一行中紧缩显示一排组件：FlowLayout
- 在一行或一列中显示一样大小的组件：GridLayout
- No layout：setLayout(null)

---

## 布局示例

![](images/Layout.jpeg)<!-- .element height="60%" width="60%" -->

---

## 文本框和文本区

- 文本框(JTextField)是界面中用于输入和输出一行文本的框。JTextField类用来建立文本框。与文本框相关的接口是ActionListener。

- 文本区(JTextArea)是窗体中一个放置文本的区域。文本区与文本框的主要区别是文本区可存放多行文本。javax.swing包中的JTextArea类用来建立文本区。JTextArea组件没有事件。

- 常用文本框和文本区实现数据的输入和输出：getText()和setText()


---

## 选择框和单选按钮

- <font size=6>选择框(JCheckBox)的选中与否开状是一个小方框，被选中则在框中打勾。当在一个容器中有多个选择框，同时可以有多个选择框被选中，这样的选择框也称复选框。与选择框相关的接口是ItemListener，事件类是ItemEvent。</font>

- <font size=6>单选按钮(JRadioButton)的功能与单选框相似。使用单选按钮的方法是将一些单选按钮用ButtonGroup对象分组，使同一组的单选按钮只允许有一个被选中。单选按钮与单选框的差异是显示的样式不同，单选按钮是一个圆形的按钮，单选框是一个小方框。</font>

---

## 列表

- <font size=6>列表(JList)在界面中表现为列表框，是JList类或它的子类的对象。程序可以在列表框中加入多个文本选择项条目。列表事件的事件源有两种：</font>
- <font size=6>1. 鼠标双击某个选项：双击选项是动作事件，与该事件相关的接口是ActionListener，注册监视器的方法是addActionListener()，接口方法是actionPerformed(ActionEvent e)。</font>
- <font size=6>2. 鼠标单击某个选项：单击选项是选项事件，与选项事件相关的接口是ListSelectionListener，注册监视器的方法是addListSelectionListener，接口方法是valueChanged(ListSelectionEvent e)。</font>

---

## 组合框

- <font size=6>组合框(JComboBox)是文本框和列表的组合，可以在文本框中输入选项，也可以单击下拉按钮从显示的列表中进行选择。</font>

- <font size=6>在JComboBox对象上发生事件分为两类。一是用户选定项目，事件响应程序获取用户所选的项目。二是用户输入项目后按回车键，事件响应程序读取用户的输入。第一类事件的接口是ItemListener；第二类事件是输入事件，接口是ActionListener。</font>

---

## 菜单

- <font size=6>有两种类型的菜单：下拉式菜单和弹出式菜单。</font>

- <font size=6>在下拉式菜单或弹出式菜单中选择一个选项就产生一个ActionEvent事件。该事件被发送给那个选项的监视器，事件的意义由监视器解释。</font>

- <font size=6>一个菜单条可以放多个菜单(JMenu)，每个菜单又可以有许多菜单项(JMenuItem)。</font>

- <font size=6>菜单的事件源是用鼠标点击某个菜单项。处理该事件的接口是ActionListener，要实现的接口方法是actionPerformed(ActionEvent e)，获得事件源的方法getSource()。</font>

- <font size=6>菜单也可以包含具有持久的选择状态的选项，这种特殊的菜单可由JCheckBoxMenuItem类来定义。</font>


---

## 对话框

- <font size=6>对话框是一个临时窗口，可以在其中放置用于得到用户输入的控件。在Swing中，有两个对话框类，它们是JDialog类和JOptionPane类。JDialog类提供构造并管理通用对话框；JOptionPane类给一些常见的对话框提供许多便于使用的选项</font>

- <font size=6>JDialog类是对话框的基类，对话框依赖其他窗口，当它所依赖的窗口消失或最小化时，对话框也将消失；窗口还原时，对话框又会自动恢复。对话框的默认布局为BorderLayout布局。</font>


---

## 滚动条

- <font size=6>滚动条(JScrollBar)也称为滑块，用来表示一个相对值，该值代表指定范围内的一个整数。</font>

- <font size=6>JScrollBar类对象的事件类型是AdjustmentEvent；类要实现的接口是AdjustmentListener，接口方法是adjustmentValueChanged()；注册监视器的方法是addAdjustmentListener()；获取事件源对象的方法是getAdjustable()。</font>

---

## 鼠标事件

- <font size=6>鼠标事件的事件源往往与容器相关，当鼠标进入容器、离开容器，或者在容器中单击鼠标、拖动鼠标时都会发生鼠标事件。java语言为处理鼠标事件提供两个接口：MouseListener，MouseMotionListener接口。</font>

- <font size=6>MouseListener接口能处理5种鼠标事件：按下鼠标，释放鼠标，点击鼠标、鼠标进入、鼠标退出。</font>

- <font size=6>MouseMotionListener接口处理拖动鼠标和鼠标移动两种事件。</font>

---

## 键盘事件

- <font size=6>键盘事件的事件源一般与组件相关，当一个组件处于激活状态时，按下、释放或敲击键盘上的某个键时就会发生键盘事件。</font>

- <font size=6>键盘事件的接口是KeyListener，注册键盘事件监视器的方法是addKeyListener(监视器)。</font>

- <font size=6>管理键盘事件的类是KeyEvent，该类提供方法：
public int getKeyCode()，获得按动的键码。</font>


---

## 绘图基础

- <font size=6>要在平面上显示文字和绘图，首先要确定一个平面坐标系。Java语言约定，显示屏上一个长方形区域为程序绘图区域，坐标原点(0,0）位于整个区域的左上角。一个坐标点（x,y）对应屏幕窗口中的一个像素，是整数。</font>

- <font size=6>在java.awt包中，类Graphics提供的功能有：建立字体、设定显示颜色、显示图像和文本，绘制和填充各种几何图形。</font>

- <font size=6>在某个组件中绘图，一般应该为这个组件所属的子类重写paint()方法，在该重写的方法中进行绘图。</font>

---

## Graphics类的绘图方法

- Graphics类提供基本的几何图形绘制方法，主要有：画线段、画矩形、画圆、画带颜色的图形、画椭圆、画圆弧、画多边形等。

---

## Graphics2D类的绘图方法

- <font size=6>Java语言在Graphics类提供绘制各种基本的几何图形的基础上，扩展Graphics类提供一个Graphics2D类，它拥用更强大的二维图形处理能力，提供、坐标转换、颜色管理以及文字布局等更精确的控制。</font>

- <font size=6>Graphics2D定义了几种方法，用于添加或改变图形的状态属性。可以通过设定和修改状态属性，指定画笔宽度和画笔的连接方式；设定平移、旋转、缩放或修剪变换图形；以及设定填充图形的颜色和图案等。图形状态属性用特定的对象存储。</font>

---

## 图像处理基础

- <font size=6>图像是由一组像素构成，用二进制形式保存的图片。Java语言支持GIF、JPEG和BMP这3种主要图像文件格式。Java语言的图像处理功能被封装在Image类中。</font>

- <font size=6>载入图像文件的方法有两个：</font>
  ```java 
   Image getImage(URL url) //url指明图像所在位置和文件名。
   Image getImage(URL url,String name) //url指明图像所在位置，name是文件名。
  ```
- <font size=6>组件也可以使用Toolkit提供的静态方法getDefaultToolkit()获得一个缺省的Toolkit对象,并用它加载图像。此时，载入图像的代码常写成这样：</font>
  ```java
  Image img = Toolkit.getDefaultToolkit().getImage(url);
  ```

---

## 图像缓冲技术

- 缓冲技术不仅可以解决闪烁问题，并且由于在计算机内存中创建图像，程序可以对图像进行像素级处理，完成复杂的图像变换后再显示。

- BufferedImage类

```java
  BufferedImage bimage = (BufferedImage)this.createImage(this.getWidth(),this.getHeight());
  Graphics2D g2d = bimge.createGraphics();

```

---

## 多媒体

- 可以用Java程序播放幻灯片、动画、视频、声音等

- javax.swing.Timer: a source component that fires an
ActionEvent at a predefined rate.

- 结合多线程编程技术

---

## JavaFX

- JavaFX is a set of graphics and media packages that enables developers to design, create, test, debug, and deploy rich client applications that operate consistently across diverse platforms.

- to build network-aware applications that are deployed across multiple platforms and display information in a high-performance modern user interface that features audio, video, graphics, and animation.

---

## JavaFX key features

- Java APIs
- FXML and Scene Builder
- WebView
- Swing interoperability
- Built-in UI controls and CSS
- 3D Graphics Features
- Canvas API, Pringting API, Rich Text Support, Multitouch Support...

---

## JavaFX Architecture

![](images/jfxar_dt_001_arch-diag.png)<!-- .element height="60%" width="60%" -->

---

## JavaFX Samples

```java
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MyApp extends Application {
    public void start(Stage stage) {
        Circle circ = new Circle(40, 40, 30);
        Group root = new Group(circ);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

---

## JavaFX Application Lifecycle

- The entry point for JavaFX applications is the Application class.

  + <small>1. Constructs an instance of the specified Application class</small>
  + <small>2. Calls the init() method</small>
  + <small>3. Calls the start(javafx.stage.Stage) method</small>
  + <small>4. Waits for the application to finish, which happens when either of the following occur:</small>
    + <small>the application calls Platform.exit()</small>
    + <small>the last window has been closed and the implicitExit attribute on Platform is true</small>
  + <small>5. Calls the stop() method</small>

---

## Scene

![](images/helloworld_scenegraph.png)<!-- .element height="60%" width="60%" -->

---

## Stage

- The JavaFX Stage class is the top level JavaFX container. 
- The primary Stage is constructed by the platform. Additional Stage objects may be constructed by the application.
- Stage objects must be constructed and modified on the JavaFX Application Thread.

---

## Colorful Samples

- Are you ready for JavaFX?

---

让你的葫芦娃们在丰富多彩的图形界面里和妖精们战斗！

---

# END