package com.li.design.appearance;

/**
 * @program: Demo
 * @description: 客户端可以调用这个角色的方法。此角色知晓相关的（一个或者多个）子系统的功能和责任。
 *  *            在正常情况下，本角色会将所有从客户端发来的请求委派到相应的子系统去
 * *             一个模块中定义的方法可以分成两部分，一部分是给子系统外部使用的，一部分是子系统内部模块之间相互调用时使用的。
 *  *            有了Facade类，那么用于子系统内部模块之间相互调用的方法就不用暴露给子系统外部了
 * @author: li
 * @create: 2020-07-31 13:40
 **/
public class ComputerFacade {
    /**
     * 每个子系统都可以被客户端直接调用，或者被门面角色调用。子系统并不知道门面的存在，对于子系统而言，门面仅仅是另外一个客户端而已
     * Facade类其实相当于A、B、C模块的外观界面，有了这个Facade类，那么客户端就不需要亲自调用子系统中的A、B、C模块了
     *
     * 客户端只需要跟Facade类交互就好了，从而更好地实现了客户端和子系统中A、B、C模块的解耦，让客户端更容易地使用系统
     * @param args
     */

    private Cpu cpu;
    private Disk disk;
    private Memory memory;

    public ComputerFacade() {
        this.cpu = new Cpu();
        this.disk = new Disk();
        this.memory = new Memory();
    }

    public void start () {
        System.out.println("Computer start ...");
        cpu.start();
        disk.start();
        memory.start();
    }

    public void shutDown () {
        System.out.println("Computer shutDown ...");
        cpu.shutDown();
        disk.shutDown();
        memory.shutDown();
    }
}
