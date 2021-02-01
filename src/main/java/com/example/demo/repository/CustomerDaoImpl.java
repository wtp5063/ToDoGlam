package com.example.demo.repository;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.common.security.EncodeUtil;
import com.example.demo.model.CustomerEntity;

import lombok.RequiredArgsConstructor;

/**
 * customer(ユーザー情報)テーブルのDao実装クラス。
 *
 * @author tanakamasato
 * @since 2021/01/26
 */
@Repository
@RequiredArgsConstructor
public class CustomerDaoImpl implements CustomerDao
{
    /**
     * データベースアクセス処理を行う。
     */
    private final JdbcTemplate jdbc;

    /**
     * 受け取ったEntityから取り出したデータをテーブルに挿入する。
     *
     * @param entity customer(ユーザー情報)テーブルのEntity class。
     * @return 成功時：true、失敗時：false。
     */
    @Override
    public boolean insert(CustomerEntity entity) throws DataAccessException
    {
        String password = EncodeUtil.passwordEncoder().encode(entity.getPassword());
        int rowCount = jdbc.update("INSERT INTO customer (name, email, password, address, tel) VALUES(?, ?, ?, ?, ?)", entity.getName(), entity.getEmail(), password, entity.getAddress(), entity.getTel());
        return rowCount > 0;
    }

    /**
     * 受け取ったidと一致するレコードを取得し、Entityに格納して返す。
     *
     * @param id customer(ユーザー情報)テーブルのプライマリキー。
     * @return customer(ユーザー情報)テーブルのEntity class。
     */
    @Override
    public CustomerEntity findById(int id) throws DataAccessException
    {
        try
        {
            Map<String, Object> map = jdbc.queryForMap("SELECT * FROM customer WHERE id = ?", id);
            CustomerEntity entity = new CustomerEntity();
            entity.setId((int) map.get("id"));
            entity.setName((String) map.get("name"));
            entity.setEmail((String) map.get("email"));
            entity.setPassword((String) map.get("password"));
            entity.setAddress((String) map.get("address"));
            entity.setTel((String) map.get("tel"));
            entity.setRole((String) map.get("role"));
            return entity;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    /**
     * 受け取ったメールアドレスと一致するレコードを取得し、Entityに格納して返す。
     *
     * @param email メールアドレス。
     * @return customer(ユーザー情報)テーブルのEntity class。
     */
    @Override
    public CustomerEntity findByEmail(String email) throws DataAccessException
    {
        try
        {
            Map<String, Object> map = jdbc.queryForMap("SELECT * FROM customer WHERE email = ?", email);
            CustomerEntity entity = new CustomerEntity();
            entity.setId((int) map.get("id"));
            entity.setName((String) map.get("name"));
            entity.setEmail((String) map.get("email"));
            entity.setPassword((String) map.get("password"));
            entity.setAddress((String) map.get("address"));
            entity.setTel((String) map.get("tel"));
            entity.setRole((String) map.get("role"));
            return entity;
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    /**
     * 受け取ったEntityに格納されたidと一致するレコードを格納されたデータで上書きする。
     *
     * @param entity customer(ユーザー情報)テーブルのプライマリキー。
     * @return 成功時：true、失敗時：false。
     */
    @Override
    public boolean updateById(CustomerEntity entity) throws DataAccessException
    {
        String password = EncodeUtil.passwordEncoder().encode(entity.getPassword());
        int rowCount = jdbc.update("UPDATE customer SET name = ?, email = ?, password = ?, address = ?, tel = ?, role = ? WHERE id = ?", entity.getName(), entity.getEmail(), password, entity.getAddress(), entity.getTel(), "ROLE_USER",
                entity.getId());
        return rowCount > 0;
    }

    /**
     * 受け取ったプライマリキーと一致するレコードを削除する。
     *
     * @param id customer(ユーザー情報)テーブルのプライマリキー。
     * @return 成功時：true、失敗時：false。
     */
    @Override
    public boolean deleteById(int id) throws DataAccessException
    {
        int rowCount = jdbc.update("DELETE FROM customer WHERE id = ?", id);
        return rowCount > 0;
    }

}
