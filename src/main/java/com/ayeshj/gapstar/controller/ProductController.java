package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.service.CategoryService;
import com.ayeshj.gapstar.service.ProductService;
import com.ayeshj.gapstar.service.SubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for functions related to the Products
 *
 * @author Ayesh Jayasekara
 * @since V1
 */
@Controller
@Slf4j
public class ProductController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final ProductService productService;

    /**
     * Constructor for dependency injection
     *
     * @param categoryService    Category service {@link CategoryService}
     * @param subCategoryService Sub Category service {@link SubCategoryService}
     * @param productService     Product service {@link ProductService}
     */
    @Autowired
    public ProductController(CategoryService categoryService,
                             SubCategoryService subCategoryService,
                             ProductService productService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.productService = productService;
    }


    /**
     * Customer page controller
     *
     * @param model UI model
     * @param req   HTTP request to access user session
     * @return Return to the product page
     * @// TODO: 2021-06-20 Category map is being stored in user session, which isn't recommended. Need better approach.
     */
    @GetMapping("/products")
    public String customerPage(Model model, HttpServletRequest req) {


        if (req.getSession().getAttribute("categoryMap") == null) {
            req.getSession().setAttribute("categoryMap", categoryService.fetchActiveCategories());
        }

        if (req.getSession().getAttribute("subCategoryMap") == null) {
            req.getSession().setAttribute("subCategoryMap", subCategoryService.fetchActiveSubCategories());
        }

        model.addAttribute("productList", productService.fetchAllProducts());

        return "product";
    }
}
