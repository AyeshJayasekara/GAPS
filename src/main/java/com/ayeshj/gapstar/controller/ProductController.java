package com.ayeshj.gapstar.controller;

import com.ayeshj.gapstar.service.CategoryService;
import com.ayeshj.gapstar.service.ProductService;
import com.ayeshj.gapstar.service.SubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
     * @return Return to the product page
     */
    @GetMapping("/products")
    public String customerPage(Model model) {


        if (model.getAttribute("categoryMap") == null) {
            model.addAttribute("categoryMap", categoryService.fetchActiveCategories());
        }

        if (model.getAttribute("subCategoryMap") == null) {
            model.addAttribute("subCategoryMap", subCategoryService.fetchActiveSubCategories());
        }

        model.addAttribute("productList", productService.fetchAllProducts());

        return "product";
    }
}
