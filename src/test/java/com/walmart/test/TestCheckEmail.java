package com.walmart.test;

import com.walmart.ticketservice.utils.CheckEmail;

public class TestCheckEmail {
    public static void main(String[] args) {

        System.out.println(CheckEmail.isEmail("ss@d.co"));
        System.out.println(CheckEmail.isEmail("ssd.com"));
        System.out.println(CheckEmail.isEmail("ssco"));
        System.out.println(CheckEmail.isEmail("ssco@.com"));
        System.out.println(CheckEmail.isEmail("sscom"));
        System.out.println(CheckEmail.isEmail("sscom@gmai.com"));
    }
}
