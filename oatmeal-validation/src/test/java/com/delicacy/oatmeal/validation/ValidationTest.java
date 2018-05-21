package com.delicacy.oatmeal.validation;

import org.junit.Test;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * test
 *
 * @author zyt
 * @create 2018-05-21 17:00
 **/

public class ValidationTest {

    @Test
    public void test(){
        User user = new User();
        user = getUser(user);
        String check = Jsr303Util.check(user);
        System.out.println(check);
    }

    private User getUser(@Valid User user){
        List<Book> books = Arrays.asList(new Book());
        user.setBooks(books);
        return user;
    }

}
