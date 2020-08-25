package com.li.gc;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-08-23 15:40
 **/
public class FinalizeEscapeGC {

    public String name;
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public FinalizeEscapeGC(String name) {
        this.name = name;
    }

    public void isAlive() {
        System.out.println("yes, i am still alive");
    }

    /**
     * Object基类的方法
     * 当一个对象重写了finalize()方法，jvm会把当前对象注册到 FinalizerThread对象的ReferenceQueue队列中
     * 在finalize()未执行的时候，当前对象被FinalizerThread对象引用，在发生GC的时候，jvm不会回收当期对象
     * 当finalize()方法执行完之后，FinalizerThread对象就会当前对象从队列中移除，jvm就会进行回收
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        System.out.println(this);
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC("leesf");
        System.out.println(SAVE_HOOK);
        // 对象第一次拯救自己
        SAVE_HOOK = null;
        System.out.println(SAVE_HOOK);
        System.gc();
        // 因为finalize方法优先级很低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead");
        }

        // 下面这段代码与上面的完全相同,但是这一次自救却失败了
        // 一个对象的finalize方法只会被调用一次
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级很低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead");
        }

    }

}
