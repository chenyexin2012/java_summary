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





