package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PostEntity;

import lombok.RequiredArgsConstructor;

/**
 * post(投稿)テーブルのDao実装クラス。
 * @author tanakamasato
 * @since 2021/02/01
 */
@Repository
@RequiredArgsConstructor
public class PostDaoImpl implements PostDao
{
    /**
     * データベースアクセス処理を行う。
     */
    private final JdbcTemplate jdbc;

    /**
     * 受け取ったEntityから取り出したデータをテーブルに挿入する。
     *
     * @param post post(投稿)テーブルのEntity class。
     * @return 成功時：true、失敗時：false。
     */
    @Override
    public boolean insert(PostEntity post) throws DataAccessException
    {
        int rowCount = jdbc.update("INSERT INTO post (title, contents) VALUES(?, ?)", post.getTitle(), post.getContents());
        return rowCount > 0;
    }

    /**
     * post(投稿)テーブルのデータを全件取得し、Entityに格納後Listにして返す。
     * @return 成功時：PostEntityのList、失敗時：null。
     */
    @Override
    public List<PostEntity> findAll() throws DataAccessException
    {
        try
        {
            List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM post ORDER BY datetime DESC");
            List<PostEntity> postList = new ArrayList<>();
            for (Map<String, Object> map : getList)
            {
                PostEntity post = new PostEntity();
                post.setId((int) map.get("id"));
                post.setCustomer_id((int) map.get("customer_id"));
                post.setTitle((String) map.get("title"));
                post.setDatetime((Timestamp) map.get("datetime"));
                post.setContents((String) map.get("contents"));
                postList.add(post);
            }
            return postList;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    /**
     * 受け取ったidと一致するレコードを全件取得し、Entityに格納後Listにして返す。
     *
     * @param id post(投稿)テーブルのプライマリキー。
     * @return 成功時：post(投稿)テーブルのEntity classのList、失敗時：null。
     */
    @Override
    public List<PostEntity> findById(int id) throws DataAccessException
    {
        try
        {
            List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM post WHERE id = ? ORDER BY datetime DESC", id);
            List<PostEntity> postList = new ArrayList<>();
            for (Map<String, Object> map : getList)
            {
                PostEntity post = new PostEntity();
                post.setId((int) map.get("id"));
                post.setCustomer_id((int) map.get("customer_id"));
                post.setTitle((String) map.get("title"));
                post.setDatetime((Timestamp) map.get("datetime"));
                post.setContents((String) map.get("contents"));
                postList.add(post);
            }
            return postList;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    /**
     * 受け取ったEntityに格納されたidと一致するレコードを格納されたデータと更新日時で上書きする。
     * @param post post(投稿)テーブルのEntity class。
     * @return 成功時：true、失敗時：false。
     */
    @Override
    public boolean updateById(PostEntity post) throws DataAccessException
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int rowCount = jdbc.update("UPDATE post SET title = ?, datetime = ?, contents = ?", post.getTitle(), timestamp, post.getContents());
        return rowCount > 0;
    }

    /**
     * 受け取ったidと一致するレコードを削除する。
     * @param id post(投稿)テーブルのプライマリキー。
     * @return 成功時：true、失敗時：false。
     */
    @Override
    public boolean deleteById(int id) throws DataAccessException
    {
        int rowCount = jdbc.update("DELETE FROM post WHERE id = ?", id);
        return rowCount > 0;
    }

}
