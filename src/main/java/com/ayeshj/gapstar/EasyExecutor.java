package com.ayeshj.gapstar;

import com.ayeshj.gapstar.dto.CartDTO;
import com.ayeshj.gapstar.dto.CustomerDTO;
import com.ayeshj.gapstar.dto.ProductDTO;
import com.ayeshj.gapstar.model.WeightIndexModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Slf4j
public class EasyExecutor {

    private List<CustomerDTO> customers;
    private HashMap<CustomerDTO, CartDTO> customerCart;
    private List<ProductDTO> products;
    private List<WeightIndexModel> weightIndexModelList;

    public static void main(String[] args) {

        EasyExecutor executor = new EasyExecutor();
        GapstarShopping gapstarShopping = new GapstarShopping();
        gapstarShopping.execute(executor);
    }



    public EasyExecutor(){

        products = new ArrayList<>();
        setupProducts(1, "Apple", "111", 50, 1000, 300, 10);
        setupProducts(2, "Grapes", "222", 15, 500, 650, 20);
        setupProducts(3, "Orange", "333", 40, 800, 800, 30);

        weightIndexModelList = new ArrayList<>();
        setupWeightIndexModels(1, 100, 0, 1000.000);
        setupWeightIndexModels(2, 500, 1000.001, 2000);
        setupWeightIndexModels(3, 1000, 2000.001, 3000);
        setupWeightIndexModels(4, 1000, 3000.001, 99999999);

        customers = new ArrayList<>();
        setupCustomers(1, "Ayesh", "Jay");
        setupCustomers(1, "Barrak", "Obama");

        customerCart = new HashMap<>();
    }

    private void setupWeightIndexModels(int id, double amount, double startBlock, double endBlock){
        WeightIndexModel weightIndexModel = new WeightIndexModel();
        weightIndexModel.setId(id);
        weightIndexModel.setAmount(BigDecimal.valueOf(amount));
        weightIndexModel.setBlockStart(BigDecimal.valueOf(startBlock));
        weightIndexModel.setBlockEnd(BigDecimal.valueOf(endBlock));

        log.info("SETTING WEIGHT INDEXES FROM {} TO {} AT RATE {}", startBlock, endBlock, amount);

        weightIndexModelList.add(weightIndexModel);
    }

    private void setupProducts(int id, String productName, String sku, int stock, double weight, double price,
                               double tax){

        ProductDTO product = new ProductDTO();
        product.setProductName(productName);
        product.setId(id);
        product.setSku(sku);
        product.setStock(stock);
        product.setWeight(BigDecimal.valueOf(weight));
        product.setPrice(BigDecimal.valueOf(price));
        product.setTaxPercentage(BigDecimal.valueOf(tax));

        log.info("SETTING UP PRODUCT : {}", productName);

        products.add(product);

    }

    private void setupCustomers(int id, String firstName, String lastName){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(firstName);
        customerDTO.setLastName(lastName);
        customerDTO.setId(id);

        log.info("SETTING UP CUSTOMER : {}", firstName);

        customers.add(customerDTO);
    }
}
