package com.li.thread.demo;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-05-27 14:23
 **/
public class PersonA extends Thread{
    Bank bank;

    String mode;

    public PersonA(Bank bank, String mode) {
        this.mode = mode;
        this.bank = bank;
    }

    public void run (){
        while(bank.money >= 100){
            try {
                bank.getMoney(100, mode);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
