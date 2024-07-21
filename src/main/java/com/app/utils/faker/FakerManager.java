package com.app.utils.faker;


import com.app.annotations.LazyAutowired;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FakerManager {


    @Value("${faker.password.min_length}")
    private int passwordMinLength;

    @Value("${faker.password.max_length}")
    private int passwordMaxLength;

    @Value("${faker.password.upper_case}")
    private boolean passwordUpperCase;

    @Value("${faker.password.special_chars}")
    private boolean passwordSpecialChars;

    @Value("${faker.password.digits}")
    private boolean passwordDigits;

    @LazyAutowired
    private Faker faker;


    public String generateEmail(){
        return faker.internet().emailAddress();
    }

    public String generatePassword(){
        return faker.internet().password(passwordMinLength,passwordMaxLength,passwordUpperCase,passwordSpecialChars,passwordDigits);
    }

}
