//package com.li.jdk.agent.cglb;
//
///**
// * @program: demo
// * @description:
// * @author: li
// * @create: 2021-10-26 10:27
// **/
//import java.lang.reflect.Method;
//
//import com.li.design.proxy.White;
//import org.springframework.cglib.core.ReflectUtils;
//import org.springframework.cglib.core.Signature;
//import org.springframework.cglib.proxy.Callback;
//import org.springframework.cglib.proxy.Factory;
//import org.springframework.cglib.proxy.MethodInterceptor;
//import org.springframework.cglib.proxy.MethodProxy;
//
//public class White$$EnhancerByCGLIB$$3d3524ca extends White implements Factory {
//    private boolean CGLIB$BOUND;
//    public static Object CGLIB$FACTORY_DATA;
//    private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
//    private static final Callback[] CGLIB$STATIC_CALLBACKS;
//    private MethodInterceptor CGLIB$CALLBACK_0;
//    private static Object CGLIB$CALLBACK_FILTER;
//    private static final Method CGLIB$print$0$Method;
//    private static final MethodProxy CGLIB$print$0$Proxy;
//    private static final Object[] CGLIB$emptyArgs;
//    private static final Method CGLIB$test$1$Method;
//    private static final MethodProxy CGLIB$test$1$Proxy;
//    private static final Method CGLIB$equals$2$Method;
//    private static final MethodProxy CGLIB$equals$2$Proxy;
//    private static final Method CGLIB$toString$3$Method;
//    private static final MethodProxy CGLIB$toString$3$Proxy;
//    private static final Method CGLIB$hashCode$4$Method;
//    private static final MethodProxy CGLIB$hashCode$4$Proxy;
//    private static final Method CGLIB$clone$5$Method;
//    private static final MethodProxy CGLIB$clone$5$Proxy;
//
//    static void CGLIB$STATICHOOK1() {
//        CGLIB$THREAD_CALLBACKS = new ThreadLocal();
//        CGLIB$emptyArgs = new Object[0];
//        Class var0 = Class.forName("com.li.design.proxy.White$$EnhancerByCGLIB$$3d3524ca");
//        Class var1;
//        Method[] var10000 = ReflectUtils.findMethods(new String[]{"equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;"}, (var1 = Class.forName("java.lang.Object")).getDeclaredMethods());
//        CGLIB$equals$2$Method = var10000[0];
//        CGLIB$equals$2$Proxy = MethodProxy.create(var1, var0, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$2");
//        CGLIB$toString$3$Method = var10000[1];
//        CGLIB$toString$3$Proxy = MethodProxy.create(var1, var0, "()Ljava/lang/String;", "toString", "CGLIB$toString$3");
//        CGLIB$hashCode$4$Method = var10000[2];
//        CGLIB$hashCode$4$Proxy = MethodProxy.create(var1, var0, "()I", "hashCode", "CGLIB$hashCode$4");
//        CGLIB$clone$5$Method = var10000[3];
//        CGLIB$clone$5$Proxy = MethodProxy.create(var1, var0, "()Ljava/lang/Object;", "clone", "CGLIB$clone$5");
//        var10000 = ReflectUtils.findMethods(new String[]{"print", "()V", "test", "()Ljava/lang/String;"}, (var1 = Class.forName("com.li.design.proxy.White")).getDeclaredMethods());
//        CGLIB$print$0$Method = var10000[0];
//        CGLIB$print$0$Proxy = MethodProxy.create(var1, var0, "()V", "print", "CGLIB$print$0");
//        CGLIB$test$1$Method = var10000[1];
//        CGLIB$test$1$Proxy = MethodProxy.create(var1, var0, "()Ljava/lang/String;", "test", "CGLIB$test$1");
//    }
//
//    final void CGLIB$print$0() {
//        super.print();
//    }
//
//    public final void print() {
//        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
//        if (var10000 == null) {
//            CGLIB$BIND_CALLBACKS(this);
//            var10000 = this.CGLIB$CALLBACK_0;
//        }
//
//        if (var10000 != null) {
//            var10000.intercept(this, CGLIB$print$0$Method, CGLIB$emptyArgs, CGLIB$print$0$Proxy);
//        } else {
//            super.print();
//        }
//    }
//
//    final String CGLIB$test$1() {
//        return super.test();
//    }
//
//    public final String test() {
//        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
//        if (var10000 == null) {
//            CGLIB$BIND_CALLBACKS(this);
//            var10000 = this.CGLIB$CALLBACK_0;
//        }
//
//        return var10000 != null ? (String)var10000.intercept(this, CGLIB$test$1$Method, CGLIB$emptyArgs, CGLIB$test$1$Proxy) : super.test();
//    }
//
//    final boolean CGLIB$equals$2(Object var1) {
//        return super.equals(var1);
//    }
//
//    public final boolean equals(Object var1) {
//        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
//        if (var10000 == null) {
//            CGLIB$BIND_CALLBACKS(this);
//            var10000 = this.CGLIB$CALLBACK_0;
//        }
//
//        if (var10000 != null) {
//            Object var2 = var10000.intercept(this, CGLIB$equals$2$Method, new Object[]{var1}, CGLIB$equals$2$Proxy);
//            return var2 == null ? false : (Boolean)var2;
//        } else {
//            return super.equals(var1);
//        }
//    }
//
//    final String CGLIB$toString$3() {
//        return super.toString();
//    }
//
//    public final String toString() {
//        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
//        if (var10000 == null) {
//            CGLIB$BIND_CALLBACKS(this);
//            var10000 = this.CGLIB$CALLBACK_0;
//        }
//
//        return var10000 != null ? (String)var10000.intercept(this, CGLIB$toString$3$Method, CGLIB$emptyArgs, CGLIB$toString$3$Proxy) : super.toString();
//    }
//
//    final int CGLIB$hashCode$4() {
//        return super.hashCode();
//    }
//
//    public final int hashCode() {
//        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
//        if (var10000 == null) {
//            CGLIB$BIND_CALLBACKS(this);
//            var10000 = this.CGLIB$CALLBACK_0;
//        }
//
//        if (var10000 != null) {
//            Object var1 = var10000.intercept(this, CGLIB$hashCode$4$Method, CGLIB$emptyArgs, CGLIB$hashCode$4$Proxy);
//            return var1 == null ? 0 : ((Number)var1).intValue();
//        } else {
//            return super.hashCode();
//        }
//    }
//
//    final Object CGLIB$clone$5() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    protected final Object clone() throws CloneNotSupportedException {
//        MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
//        if (var10000 == null) {
//            CGLIB$BIND_CALLBACKS(this);
//            var10000 = this.CGLIB$CALLBACK_0;
//        }
//
//        return var10000 != null ? var10000.intercept(this, CGLIB$clone$5$Method, CGLIB$emptyArgs, CGLIB$clone$5$Proxy) : super.clone();
//    }
//
//    public static MethodProxy CGLIB$findMethodProxy(Signature var0) {
//        String var10000 = var0.toString();
//        switch(var10000.hashCode()) {
//            case -1166389848:
//                if (var10000.equals("print()V")) {
//                    return CGLIB$print$0$Proxy;
//                }
//                break;
//            case -508378822:
//                if (var10000.equals("clone()Ljava/lang/Object;")) {
//                    return CGLIB$clone$5$Proxy;
//                }
//                break;
//            case 225925469:
//                if (var10000.equals("test()Ljava/lang/String;")) {
//                    return CGLIB$test$1$Proxy;
//                }
//                break;
//            case 1826985398:
//                if (var10000.equals("equals(Ljava/lang/Object;)Z")) {
//                    return CGLIB$equals$2$Proxy;
//                }
//                break;
//            case 1913648695:
//                if (var10000.equals("toString()Ljava/lang/String;")) {
//                    return CGLIB$toString$3$Proxy;
//                }
//                break;
//            case 1984935277:
//                if (var10000.equals("hashCode()I")) {
//                    return CGLIB$hashCode$4$Proxy;
//                }
//        }
//
//        return null;
//    }
//
//    public White$$EnhancerByCGLIB$$3d3524ca() {
//        CGLIB$BIND_CALLBACKS(this);
//    }
//
//    public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] var0) {
//        CGLIB$THREAD_CALLBACKS.set(var0);
//    }
//
//    public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] var0) {
//        CGLIB$STATIC_CALLBACKS = var0;
//    }
//
//    private static final void CGLIB$BIND_CALLBACKS(Object var0) {
//        White$$EnhancerByCGLIB$$3d3524ca var1 = (White$$EnhancerByCGLIB$$3d3524ca)var0;
//        if (!var1.CGLIB$BOUND) {
//            var1.CGLIB$BOUND = true;
//            Object var10000 = CGLIB$THREAD_CALLBACKS.get();
//            if (var10000 == null) {
//                var10000 = CGLIB$STATIC_CALLBACKS;
//                if (var10000 == null) {
//                    return;
//                }
//            }
//
//            var1.CGLIB$CALLBACK_0 = (MethodInterceptor)((Callback[])var10000)[0];
//        }
//
//    }
//
//    public Object newInstance(Callback[] var1) {
//        CGLIB$SET_THREAD_CALLBACKS(var1);
//        White$$EnhancerByCGLIB$$3d3524ca var10000 = new White$$EnhancerByCGLIB$$3d3524ca();
//        CGLIB$SET_THREAD_CALLBACKS((Callback[])null);
//        return var10000;
//    }
//
//    public Object newInstance(Callback var1) {
//        CGLIB$SET_THREAD_CALLBACKS(new Callback[]{var1});
//        White$$EnhancerByCGLIB$$3d3524ca var10000 = new White$$EnhancerByCGLIB$$3d3524ca();
//        CGLIB$SET_THREAD_CALLBACKS((Callback[])null);
//        return var10000;
//    }
//
//    public Object newInstance(Class[] var1, Object[] var2, Callback[] var3) {
//        CGLIB$SET_THREAD_CALLBACKS(var3);
//        White$$EnhancerByCGLIB$$3d3524ca var10000 = new White$$EnhancerByCGLIB$$3d3524ca;
//        switch(var1.length) {
//            case 0:
//                var10000.<init>();
//                CGLIB$SET_THREAD_CALLBACKS((Callback[])null);
//                return var10000;
//            default:
//                throw new IllegalArgumentException("Constructor not found");
//        }
//    }
//
//    public Callback getCallback(int var1) {
//        CGLIB$BIND_CALLBACKS(this);
//        MethodInterceptor var10000;
//        switch(var1) {
//            case 0:
//                var10000 = this.CGLIB$CALLBACK_0;
//                break;
//            default:
//                var10000 = null;
//        }
//
//        return var10000;
//    }
//
//    public void setCallback(int var1, Callback var2) {
//        switch(var1) {
//            case 0:
//                this.CGLIB$CALLBACK_0 = (MethodInterceptor)var2;
//            default:
//        }
//    }
//
//    public Callback[] getCallbacks() {
//        CGLIB$BIND_CALLBACKS(this);
//        return new Callback[]{this.CGLIB$CALLBACK_0};
//    }
//
//    public void setCallbacks(Callback[] var1) {
//        this.CGLIB$CALLBACK_0 = (MethodInterceptor)var1[0];
//    }
//
//    static {
//        CGLIB$STATICHOOK1();
//    }
//}
