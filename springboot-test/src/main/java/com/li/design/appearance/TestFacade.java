package com.li.design.appearance;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-07-31 13:43
 **/
public class TestFacade {

    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.start();
        computerFacade.shutDown();
    }
}
