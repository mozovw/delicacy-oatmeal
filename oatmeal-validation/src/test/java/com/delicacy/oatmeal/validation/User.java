package com.delicacy.oatmeal.validation;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.List;

@Data
public class User {

    //其他参数 .......

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @Email
    private String email;

    /**
     * 所拥有的书籍列表
     */
    @NotEmpty
    @ListNotHasNull
    private List<Book> books;
    //getter setter 方法.......
}