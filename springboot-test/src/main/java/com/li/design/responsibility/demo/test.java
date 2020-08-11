package com.li.design.responsibility.demo;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-16 16:53
 **/
public class test {

    public static void main(String[] args) {
        Handler project = new ProjectManager();
        Handler dept = new DeptManager();
        Handler general = new GeneralManager();
        general.setSuccessor(dept);
        dept.setSuccessor(project);

        System.out.println("测试 project" +">>>>>>>>>" + project.handleFeeRequest("李四", 300));
        System.out.println("测试 project" +">>>>>>>>>" + project.handleFeeRequest("张三", 300));
        System.out.println("测试 project" +">>>>>>>>>" + project.handleFeeRequest("张三", 600));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("测试 dept" +">>>>>>>>>" + dept.handleFeeRequest("李四", 300));
        System.out.println("测试 dept" +">>>>>>>>>" + dept.handleFeeRequest("李四", 600));
        System.out.println("测试 dept" +">>>>>>>>>" + dept.handleFeeRequest("张三", 300));
        System.out.println("测试 dept" +">>>>>>>>>" + dept.handleFeeRequest("张三", 600));
        System.out.println("测试 dept" +">>>>>>>>>" + dept.handleFeeRequest("张三", 900));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("测试 general" +">>>>>>>>>" + general.handleFeeRequest("李四", 300));
        System.out.println("测试 general" +">>>>>>>>>" + general.handleFeeRequest("李四", 1200));
        System.out.println("测试 general" +">>>>>>>>>" + general.handleFeeRequest("张三", 300));
        System.out.println("测试 general" +">>>>>>>>>" + general.handleFeeRequest("张三", 600));
        System.out.println("测试 general" +">>>>>>>>>" + general.handleFeeRequest("张三", 900));
        System.out.println("测试 general" +">>>>>>>>>" + general.handleFeeRequest("张三", 1200));
    }
}
