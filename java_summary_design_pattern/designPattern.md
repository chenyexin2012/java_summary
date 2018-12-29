## java设计模式

### 什么设计模式

设计模式是一套被反复使用的、多数人知晓且经过分类编目的代码设计经验的总结。使用设计模式可以提高代码的重用性、可读性和可靠性。

### 六大原则

- **开闭原则（Open Close Principle）：**

Software entities should be open for extension, but closed for modification.

开闭原则就是对扩展开放，对修改关闭。保证在对代码进行扩展时，不去修改源代码，仅仅对代码进行扩展，从而实现一个热插拔的效果。

- **里氏代换原则（Liskov Substitution Principle）：**

Inheritance should ensure that any property proved about supertype objects also holds for subtype objects.

里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。LSP是继承复用的基石，只有当衍生类
可以替换掉基类，软件单位的功能不受到影响时，基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。

- **依赖倒转原则（Dependence Inversion Principle）：**

依赖倒转原则是开闭原则的基础。表示在编写代码使用到具体类时，不直接与具体类进行交互，而是与其上层接口进行交互。即面向接口编程，依赖于抽象而
不依赖于具体。

- **接口分离原则（Interface Segregation Principle）：**

通过使用多个隔离的接口，达到降低类与类之间的耦合度。

- **迪米特法则（Demeter Principle）：**

也被称为最少知道原则（Least Knowledge Principle），即一个实体应当尽量少的与其它实体之间发生相互作用，使得系统功能模块相对独立。

- **合成复用原则（Composite Reuse Principle）：**

尽量首先使用合成/聚合的方式，而不是使用继承。


### 设计模式的分类

- **创建型模式：**

创建型模式涉及对象的实例化，特点是不让用户代码依赖于对象的创建或排列方式，避免用户直接使用new创建对象。

**工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。**

- **结构型模式：**

结构型模式涉及如何组合类和对象以形成更大的结构，和类有关的结构型模式涉及如何合理使用继承机制；和对象有关的结构型模式涉及如何合理的使用
对象组合机制。

**适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。**


- **行为型模式：**

行为型模式涉及怎样合理的设计对象之间的交互通信，以及怎样合理为对象分配职责，让设计富有弹性，易维护，易复用。

**策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。**


### 二十三种设计模式

#### 策略模式 strategy



#### 观察者模式 observer



#### 装饰模式 decorator



#### 工厂模式 factory



#### 代理模式 proxy



#### 单例模式 singleton



#### 命令模式 command



#### 适配器模式 adapter



#### 外观模式 facade



#### 建造模式 builder



#### 迭代模式 iterator



#### 桥接模式 bridge



#### 原型模式 prototype



#### 责任链模式 chain of responsibility



#### 组合模式 composite



#### 享元模式 flyweight



#### 状态模式 state


#### 备忘录模式 memento