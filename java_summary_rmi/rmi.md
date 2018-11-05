### Java RMI
Java RMI（Java Remote Method Invocation），即Java远程方法调用， RMI能让一个Java程序去调用另一台服务器上的对象的方法，调用产生的效果
就和本地调用一样。主要用于在分布式服务中，实现Java应用程序之间的远程通信功能。

#### 如何创建一个RMI服务

1 定义服务接口
*  远程对象的接口必须扩展自java.rmi.Remote
*  必须声明抛出RemoteException异常
*  入参和返回值必须序列化
 
        public interface Calculator extends Remote {
        
            public Output add(Input input) throws RemoteException;
        
            public Output sub(Input input) throws RemoteException;
        
            public Output mul(Input input) throws RemoteException;
        
            public Output div(Input input) throws RemoteException;
        }  
          
2 服务端提供接口的具体实现
* 需要继承UnicastRemoteObject，表明这个类是远程方法调用的目标
* 实现构造方法并声明抛出RemoteException异常

        public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
        
            public CalculatorImpl() throws RemoteException {
        
            }
        
            @Override
            public Output add(Input input) throws RemoteException{
                return new Output(input.getA() + input.getB());
            }
        
            @Override
            public Output sub(Input input) throws RemoteException{
                return new Output(input.getA() - input.getB());
            }
        
            @Override
            public Output mul(Input input) throws RemoteException{
                return new Output(input.getA() * input.getB());
            }
        
            @Override
            public Output div(Input input) throws RemoteException{
                return new Output(input.getA() / input.getB());
            }
        }
    

3 服务端发布服务
* 指定端口
* 绑定调用url以及服务
    
        public static void main(String[] args) {
    
            try {
                Calculator calculator = new CalculatorImpl();
                LocateRegistry.createRegistry(1999);
                Naming.bind("rmi://localhost:1999/calculator", calculator);
                System.out.println("rmi 服务启动成功。。。");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            }
        }
        
        
4 客户端进行远程调用
* 接口类路径必须和服务端相同

        public static void main(String[] args) {
        
                String url = "rmi://localhost:1999/calculator";
                try {
                    Calculator calculator = (Calculator) Naming.lookup(url);
                    System.out.println(calculator.add(new Input(1, 2)));
                } catch (NotBoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }