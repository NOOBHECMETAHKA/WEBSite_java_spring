package com.example.springmodels.dao;

import com.example.springmodels.models.Product;
import com.example.springmodels.models.ProductMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {
    private List<ProductMemory> productMemoryList = new ArrayList<>();

    public List<ProductMemory> index(){ return productMemoryList; }

    public void insert(ProductMemory productMemory){ productMemoryList.add(productMemory); }

    public void insert(Product product) { productMemoryList.add(new ProductMemory(product)); }

    public void delete(Product product){
        for (int i = 0; i < productMemoryList.size(); i++) {
            if(product.getId() == productMemoryList.get(i).getId()){
                productMemoryList.remove(i);
                break;
            }
        }
    }

    public void delete(ProductMemory product){
        for (int i = 0; i < productMemoryList.size(); i++) {
            if(product.getId() == productMemoryList.get(i).getId()){
                productMemoryList.remove(i);
                break;
            }
        }
    }

    public void delete(int id){
        for (int i = 0; i < productMemoryList.size(); i++) {
            if(id == productMemoryList.get(i).getId()){
                productMemoryList.remove(i);
                break;
            }
        }
    }

    public int count(){return productMemoryList.size();}

    public void clear(){
        productMemoryList.clear();
    }
}
