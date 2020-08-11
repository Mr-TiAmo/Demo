package com.li.design.decorator;

/**
 * @program: Demo
 * @description: Component（被装饰对象的基类）
 *                  通常是一个抽象类或者接口，定义了一系列方法，方法的实现由其子类或者自己实现
 *                  通常不会直接使用该类，而是通过继承该类或者实现该接口来实现特定的功能
 *                  允许向一个现有的对象添加新的功能，而不改变其结构
 *
*                主要解决：一般的，我们为了扩展一个类经常使用继承方式实现，由于继承为类引入静态特征，并且随着扩展功能的增多，子类会很膨胀
 *
 *               优点：装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。
 *               缺点：多层装饰比较复杂。
 * @author: li
 * @create: 2020-07-31 14:42
 **/
public abstract class TeaComponent {

    public abstract String desc();
    public abstract double price();
}
