## Java IO

### Stream java字节流
    
#### InputStream 输入字节流
java中的输入字节流的继承关系如下：
 - InputStream
    - ByteArrayInputStream
    - FileInputStream
    - FilterInputStream
        - BufferedInputStream
        - DataInputStream
        - LineNumberInputStream
        - PushbackInputStream
    - ObjectInputStream
    - PipedInputStream
    - SequenceInputStream
    - StringBufferInputStream

1. InputStream是一个抽象类，是所有输入字节流的父类。
2. ByteArrayInputStream、FileInputStream、StringBufferInputStream表示三种最基本的介质流，操作对象分别为：byte数组、文件和字符。
3. PipedInputStream是一个管道输入流，作用是让多线程可以通过管道进行线程间的通讯。
4. FilterInputStream和ObjectInputStream以及它们的子类都是装饰流，使用的是装饰器模式。
5. StringBufferInputStream已经被标示为Deprecated，主要因为String应该属于字符流的范围。

#### OutputStream 输出字节流
java中输出字节流的继承关系如下：
 - OutputStream
     - ByteArrayOutputStream
     - FileOutputStream
     - FilterOutputStream
         - BufferedOutputStream
         - DataOutputStream
         - PrintStream
     - ObjectOutputStream
     - PipedOutputStream
     
1. OutputStream是一个抽象类，是所有输出字节流的父类。
2. ByteArrayOutputStream和FileOutputStream表示两种最基本的介质流，分别向byte数组和本地文件写入数据。
3. PipedOutputStream是一个管道输出流，作用是让多线程可以通过管道进行线程间的通讯。
4. FilterOutputStream和ObjectOutputStream以及它们的子类都是装饰流，使用的是装饰器模式。

### java字符流

#### Reader 输入字符流
java中的输入字符流的继承关系如下：
 - Reader
     - BufferedReader
     - LineNumberReader
     - CharArrayReader
     - FilterReader
         - PushbackReader
     - InputStreamReader
         - FileReader
     - PipedReader
     - StringReader
    
1. Reader是一个抽象类，是所有输入字符流的父类。
2. CharArrayReader和StringReader表示两种最基本的介质流，操作对象分别为char数组和String。
3. BufferedReader和FilterReader及其子类都是装饰器，用于对Reader对象进行装饰。
4. PipedReader用于从与其它线程共用的管道中读取数据。
5. InputStreamReader是一个连接字节流和字符流的桥梁，它将字节流转变为字符流。
     
#### Writer 输出字符流
java中的输出字符流的继承关系如下：
 - Writer
     - BufferedWriter
     - StringWriter
     - CharArrayWriter
     - FilterWriter
     - OutputStreamWriter
        - FileWriter
     - PipedWriter
     - PrintWriter
     
1. Writer是一个抽象类，是所有输出字符流的父类。
2. CharArrayWriter和StringWriter表示两种最基本的介质流，操作对象分别为char数组和String。
3. BufferedWriter和FilterWriter及其子类都是装饰器，用于对Reader对象进行装饰。
4. PipedWriter用于从与其它线程共用的管道中读取数据。
5. OutputStreamWriter是一个连接字节流和字符流的桥梁，它将字节流转变为字符流。

### 常用IO类的解析

#### FileInputStream 文件输入流 和 FileOutputStream 文件输出流

作用：从文件中读取字节流或向文件中写入字节。

#### ObjectInputStream 对象输入流 和 ObjectOutputStream 对象输出流

主要的作用是用于写入对象信息与读取对象信息。可用于实现对象的序列化和反序列化。

    注：1.对象必须实现Serializable接口。
        2.使用transient关键字修饰的字段不会进行序列化。

#### ByteArrayInputStream 字节数组输入流 和 ByteArrayOutputStream 字节数组输出流

当用户在程序中需要创建临时文件时，可以通过使用这两个类，以防止再次访问磁盘。
close()方法中不做任何操作。

#### BufferedInputStream 输入缓冲流 和 BufferedOutputStream 输出缓冲流

带缓冲的流，用来减少访问磁盘的次数。
当用BufferedInputStream来读取数据时，会一次性的读取多个字节放入缓冲区，这样不需要再每次read时都去访问磁盘。
当用BufferedOutputStream向磁盘中写入数据时，会先将字节放入缓冲区，等到缓冲区满了再写入文件中。

**注：由于使用了缓冲区，当写完数据后，必须调用flush()或close()方法将数据刷新至文件。**


#### DataInputStream 数据输入流 和 DataOutputStream 数据输出流
    
作用：允许应用程序以与机器无关方式从底层输入流中读取或向输出流中写入Java基本数据类型。
    
    以下为DataInputStream的部分方法：
        final int read(byte[] buffer, int offset, int length)
        final int read(byte[] buffer)
        final boolean readBoolean()
        final byte readByte()
        final char readChar()
        final double readDouble()
        final float readFloat()
        final void readFully(byte[] dst)
        final void readFully(byte[] dst, int offset, int byteCount)
        final int readInt()
        final String readLine()
        final long readLong()
        final short readShort()
        final static String readUTF(DataInput in)
        final String readUTF()
        final int readUnsignedByte()
        final int readUnsignedShort()
        final int skipBytes(int count)
        
    DataOutputStream存在对应的write方法。
    用户可以通过这些方法从输入流中来获取对应类型的数据。
    
#### 管道流 PipedInputStream PipedOutputStream

[See the blog](https://www.cnblogs.com/skywang12345/p/io_04.html)


#### StringReader 和 StringWriter

感觉没啥用？？


