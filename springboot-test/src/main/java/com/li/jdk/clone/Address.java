package com.li.jdk.clone;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-11-05 14:12
 **/
@Data
public class Address implements Cloneable{
    private String address;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    @Override
    public Object clone(){
        Address address = null;
        try {
            address = (Address) super.clone();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
}
