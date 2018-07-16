package com.delicacy.oatmeal.validation;

import lombok.Data;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.List;

@Data
public class User {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @ValidEmail
    private String email;

    @Email
    @NotBlank
    private String email2;

    @ListNotHasNull
    private List<Book> books;

    @NotEmpty
    private List<String> nicknames;

}