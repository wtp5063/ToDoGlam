package com.example.demo.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.common.validation.order.ValidGroup1;
import com.example.demo.common.validation.order.ValidGroup2;

import lombok.Data;

@Data
public class PostEntity
{
    /**
     * post(投稿)テーブルのプライマリキー。
     */
    private int id;

    /**
     * customer(ユーザー情報)テーブルのプライマリキー。
     */
    private int customer_id;

    /**
     * 投稿のタイトル。
     */
    @NotBlank(groups = ValidGroup1.class)
    @Size(min = 0, max = 255, groups = ValidGroup2.class)
    private  String title;

    /**
     * 最終更新日時。
     */
    private Timestamp datetime;

    /**
     * 投稿内容。
     */
    @NotBlank(groups = ValidGroup1.class)
    @Size(min = 0, max = 255, groups = ValidGroup2.class)
    private String contents;
}
