## 注解

### java中定义的四个元注解

- @Documented ：表示是否将注解信息添加在java文档中。

- @Retention(RetentionPolicy.RUNTIME) ：定义注解的生命周期。
    
    - RetentionPolicy.SOURCE ：表示该注解只在java源文件中存在，编译成.class文件后就会被丢弃。
    
    - RetentionPolicy.CLASS ：当被该注解标识的类被类加载器加载至内存中，注解就会被丢弃。
    
    - RetentionPolicy.RUNTIME ：生命周期贯穿整个运行期，自定义注解一般使用这种方式。

- @Target(ElementType.METHOD) : 用来表示注解用于什么地方。

    - ElementType.TYPE ：用于类、接口或enum声明
    
    - ElementType.FIELD ：用于实例变量
    
    - ElementType.METHOD ：用于方法
    
    - ElementType.PARAMETER ：用于方法参数
    
    - ElementType.CONSTRUCTOR ：用于构造函数
    
    - ElementType.LOCAL_VARIABLE ：用于局部变量
    
    - ElementType.ANNOTATION_TYPE ：用于另一个注解
    
    - ElementType.PACKAGE ：用于记录java文件的package信息

- @Inherited ：表示允许子类继承父类中的注解。


### 自定义注解

1.注解内部的属性只支持基本类型，String类型、数组类型和枚举类型，注解内部的属性被定义为方法，且可以设置默认值，不指定默认值，则使用注解时必须赋值。

    public enum Status {
        READY, RUNNING, BLOCKING, FINISHED
    }

    String name();

    int level();

    Status status() default Status.READY;

2.注解内部只有一个属性，且属性名为value，则使用该注解时，无需指定属性名。

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @Inherited
    public @interface SingleAttributeAnnotation {
    
        String value();
    }
    
    @SingleAttributeAnnotation("value")
    public void method() {
    }














