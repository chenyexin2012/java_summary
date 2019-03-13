## Java运行时内存区域划分

### 程序计数器

线程私有，它是一块很小的内存区域，可以被看做是当前线程执行字节码的行号指示器。

### Java虚拟机栈

线程私有，它被用来描述java方法执行的内存模型。实际上，Java虚拟机栈是由一个个栈帧组成，而每个栈帧中都拥有：局部变量表、操作数栈、
动态链接、方法出口信息。

### 本地方法栈

线程私有，与虚拟机栈相似，用来为本地方法服务。

### Java堆

线程共享，它是虚拟机所管理的内存中最大的一块，用来存储java中的对象实例，几乎所有的对象的空间都由此分配，同时也是java垃圾收集机制管理的主要
区域。

### 方法区

线程共享，用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。

### 运行时常量池

运行时常量池是方法区的一部分。Class 文件中除了有类的版本、字段、方法、接口等描述信息外，还有常量池信息（用于存放编译期生成的各种字面量
和符号引用）。

JDK1.7及之后版本的 JVM 已经将运行时常量池从方法区中移了出来，在 Java 堆（Heap）中开辟了一块区域存放运行时常量池。

### 直接内存

线程共享，它并不是虚拟机运行时数据区的一部分，也不是虚拟机规范中定义的内存区域，但是这部分内存也被频繁地使用。而且也可能导致
OutOfMemoryError异常出现。

## 对象的创建


## 垃圾收集器与内存分配策略

### java垃圾收集器

使用过java开发语言的人都知道，我们不需要像C++那样，在不使用一个对象时，需要手动的取free该对象的内存，否则对象就会一直存留在内存
之中。在java中当我们不需要再继续使用一个对象时，无需进行任何操作，JVM会帮我们完成对应的内存回收操作，而发挥这个操作的机制就是JVM的垃
圾收集器。

了解JVM的垃圾收集器，我们需要知道它是如何“发现”哪些对象需要被回收？如何回收？


### 如何寻找需要被回收的对象？
    
- 引用计数法
    
- 可达性分析
    
    
### 垃圾收集算法
    
- 标记——清理算法
    
- 复制算法
    
- 标记-清除算法
    

### 常用的垃圾收集器

    
- 新生代垃圾收集器：
    
    - Seria
    
    - ParNew
    
    - Parallel Scaverage
    

- 老年代收集器：

    - Seria Old
        
    - CMS
    
    - Parallel Old
    

- G1收集器：可同时对新生代和老年代进行垃圾回收处理。
    
    
### JVM的内存分配规则





### GC日志阅读分析
    










## 分派思想

    参考代码： com.holmes.summary.jvm.dispatch

使用java的人都知道，封装、继承和多态是面向对象的三大特征。其中多态主要表现于对象方法的调用过程中：

编译期：根据对象的静态类型进行静态分派。

运行期：根据对象的实际类型进行动态分派。

要想了解java的分派思想，首先需要熟悉以下几个概念。

#### 静态类型与动态类型

**静态类型（Static Type）：又被称为明显类型（Apparent Type），指的是声明对象时所指定的类型。**

**动态类型（Dynamic Type）：又被称为实际类型（Actual Type），指的是初始化对象是所指定的类型。**

    Horse horse = new BlackHorse();

对象horse的静态类型为Horse，实际类型为BlackHorse.


#### 宗量、静态分派与动态分派

**宗量：一个方法所属的对象叫做方法的接收者，方法的接收者与方法的参数统称做方法的宗量。**

**静态分派：Java中通过静态类型来定位方法执行版本的分派动作称为静态分派，发生在编译期，具体体现在方法的重载上。**

**动态分派：Java中通过动态类型来定位方法执行版本的分派动作称为动态分派，发生在运行期，具体体现在方法的重写上。**

以墨子骑马为例：

    class Horse {
    
        public void eat(){
            System.out.println("马吃草");
        }
    }
    
    class BlackHorse extends Horse {
    
        @Override
        public void eat() {
            System.out.println("黑马吃草");
        }
    }
    
    class WhiteHorse extends Horse {
    
        @Override
        public void eat() {
            System.out.println("白马吃草");
        }
    }

    class Mozi {
        
        public void ride(Horse h){
            System.out.println("骑马");
        }
        
        public void ride(WhiteHorse wh){
            System.out.println("骑白马");
        }
        
        public void ride(BlackHorse bh){
            System.out.println("骑黑马");
        }
    }
    
    
    public class StaticDispatch {
    
        public static void main(String[] args) {
    
            Mozi mozi = new Mozi();
    
            Horse horse1 = new BlackHorse();
            Horse horse2 = new WhiteHorse();
    
            mozi.ride(horse1);
            mozi.ride(horse2);
        }
    }

StaticDispatch的main方法中，horse1和horse2的静态类型均为Horse，因此当调用Mozi中的几个重载的方法时，会通过静态类型Horse
来选择执行public void ride(Horse h)方法。

    public class DynamicDispatch {
    
        public static void main(String[] args) {
    
            // 动态类型为Horse
            Horse horse = new Horse();
            horse.eat();
    
            // 动态类型为BlackHorse
            horse = new BlackHorse();
            horse.eat();
        }
    }
    
StaticDispatch的main方法中，horse对象的实际类型为Horse，当执行horse.eat()方法时，会去执行Horse中的eat方法，
之后将horse重新初始化为BlackHorse类型的对象，此时的实际类型变成了BlackHorse，因此再次执行horse.eat()方法时，便会去执行
BlackHorse中的eat方法。



#### 单分派与多分派

根据分派基于多少种宗量，可以将分派划分为单分派和多分派。在编译期间，编译器需要根据方法的接收者的类型以及方法的所有参数的类型才能确定执行
的方法版本，因此Java的静态分派属于多分派。而在运行期，方法的名称和描述符已经是确定了的，但是在执行真正的方法调用时，只需要根据方法的接收
者的实际类型去决定执行的方法版本，因此Java的动态分派属于单分派。



## ClassLoader 类加载器

类加载器就是根据指定全限定名称将class文件加载到JVM内存，转为Class对象。

在JVM中，判断一个对象是否是某个类型时，如果该对象的实际类型与待比较的类型的类加载器不同，那么也会返回false。

- Bootstrap ClassLoader：启动类加载器

- Extension ClassLoader：扩展类加载器

- Application ClassLoader：应用程序类加载器

- User ClassLoader：自定义加载器

### 双亲委派模型

任何一个类加载器接收到类加载请求时，首先不会自己去加载该类，而是将这个请求交给父类去完成，如果父类无法加载，才会自己尝试去加载该类。

优点：
    保证每个类只被加载一次。
    每个加载器只能加载自己范围内的类。
    
#### 类加载过程

加载 --> 连接（验证 --> 准备 --> 解析） --> 初始化


##### 加载
















