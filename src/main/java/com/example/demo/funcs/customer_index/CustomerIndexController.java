package com.example.demo.funcs.customer_index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー向けホーム画面用Controller class。
 * @author tanakamasato
 * @since 2021/02/01
 */
@Controller
@RequiredArgsConstructor
public class CustomerIndexController
{
    /**
     * 表示処理を行う。
     * @param model
     * @return thymeleafテンプレート。
     */
    @GetMapping("/customer_home")
    public String home(Model model) {
        model.addAttribute("title", "ToDoGlam");
        model.addAttribute("main", "customerHome::main");
        return "layout";
    }
}
