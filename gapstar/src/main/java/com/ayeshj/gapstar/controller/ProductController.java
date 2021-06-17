package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.dto.CustomerDTO;
import com.ayeshj.gapstar.service.CategoryService;
import com.ayeshj.gapstar.service.ProductService;
import com.ayeshj.gapstar.service.SubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ProductController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final ProductService productService;

    @Autowired
    public ProductController(CategoryService categoryService,
                             SubCategoryService subCategoryService,
                             ProductService productService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.productService = productService;
    }


    @GetMapping("/products")
    public String customerPage(Model model, HttpServletRequest req){


        if(req.getSession().getAttribute("categoryMap") == null){
            req.getSession().setAttribute("categoryMap", categoryService.fetchActiveCategories());
        }

        if(req.getSession().getAttribute("subCategoryMap") == null){
            req.getSession().setAttribute("subCategoryMap", subCategoryService.fetchActiveSubCategories());
        }

        model.addAttribute("productList", productService.fetchAllProducts());

        return "product";
    }
}
