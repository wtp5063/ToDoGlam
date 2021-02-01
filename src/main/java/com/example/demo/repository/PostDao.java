package com.example.demo.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.model.PostEntity;

/**
 * post(投稿)テーブルのDao interface。
 * @author tanakamasato
 * @since 2021/02/01
 */
public interface PostDao
{
    public boolean insert(PostEntity post) throws DataAccessException;
    public List<PostEntity> findAll() throws DataAccessException;
    public List<PostEntity> findById(int id) throws DataAccessException;
    public boolean updateById(PostEntity post) throws DataAccessException;
    public boolean deleteById(int id) throws DataAccessException;
}
