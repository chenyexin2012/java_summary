### AOP的通知类型


| 类型 | 接口 | 执行点 |
|:-------|:-------------|:----------|
Before|MethodBeforeAdvice|在接合点之前执行通知
After Returning|AfterReturningAdvice|在接合点执行返回之后执行通知
After Throwing|ThrowsAdvice|接合点抛出异常时执行通知
After（finally）|N/A|接合执行完成，不管是否抛出异常，都会执行同时
Around|N/A|在接合点周围执行通知，即可在执行之前，亦可在执行之后

1 使用After Throwing通知时，需要实现ThrowsAdvice接口，但是在ThrowsAdvice接口中未定义任何方法，因为它仅仅是作为一个标记接口来使用的。
实现该接口时，应当在通知中实现下面几个方法中的其中一个，实现的方法将会被反射调用。

     public void afterThrowing(Exception ex)
     public void afterThrowing(RemoteException)
     public void afterThrowing(Method method, Object[] args, Object target, Exception ex)
     public void afterThrowing(Method method, Object[] args, Object target, ServletException ex)
     
当afterThrowing方法中出现异常时，该异常会覆盖目标方法的异常。
     
2 
     
     