package com.example.demo.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.demo.model.CustomerEntity;


public class LoginUser extends User
{
    private CustomerEntity entity;

    public LoginUser(CustomerEntity entity)
    {
        super(entity.getEmail(), entity.getPassword(), makeAuthorities(entity.getRole()));
        // TODO 自動生成されたコンストラクター・スタブ
        this.entity = entity;
    }

    public static Collection<? extends GrantedAuthority> makeAuthorities(String auth)
    {
            return AuthorityUtils.createAuthorityList(auth);
    }

    public CustomerEntity getEntity()
    {
        return entity;
    }
}
