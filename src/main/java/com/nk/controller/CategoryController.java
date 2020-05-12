package com.nk.controller;

import com.nk.model.Category;
import com.nk.model.User;
import com.nk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/c/add", method = RequestMethod.POST)
    public String addCategory(HttpSession session, Category category) {
        if (category.getName().trim().length() != 0) {
            User user = (User) session.getAttribute("user");
            category.setUserId(user.getId());
            int num = categoryService.insert(category);
        }
        return "redirect:/writer";
    }

    @RequestMapping("/writer/{categoryId}/delete")
    public String delete(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return "redirect:/writer";
    }
}
