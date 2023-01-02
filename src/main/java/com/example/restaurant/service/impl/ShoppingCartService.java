package com.example.restaurant.service.impl;

import com.example.restaurant.dao.IShoppingCartDao;
import com.example.restaurant.model.ShoppingCart;
import com.example.restaurant.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    IShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart createShoppingItem(int dishesId, int count, int customerId) {
        ShoppingCart shoppingCart = shoppingCartDao.findByDishesIdAndCustomerIdAndStatus(dishesId, customerId,0);
        if(shoppingCart == null){
            ShoppingCart shoppingItem = new ShoppingCart();
            shoppingItem.setDishesId(dishesId);
            shoppingItem.setCount(count);
            shoppingItem.setCustomerId(customerId);
            shoppingItem.setStatus(0);
            shoppingCartDao.save(shoppingItem);
            return shoppingItem;
        }else{
            shoppingCart.setCount(shoppingCart.getCount() + count);
            shoppingCartDao.save(shoppingCart);
            return shoppingCart;
        }
    }

    @Override
    public void changeShoppingItem(int dishesId,int customerId,int count) throws Exception{
        ShoppingCart shoppingItem = shoppingCartDao.findByDishesIdAndCustomerId(dishesId,customerId);
        if(shoppingItem == null){
            throw new Exception("该菜品并没有被选择");
        }
        if(count >= shoppingItem.getCount()){
            shoppingItem.setStatus(2);
        }else{
            shoppingItem.setCount(shoppingItem.getCount() - count);
        }
        shoppingCartDao.save(shoppingItem);
    }

    @Override
    public Page<ShoppingCart> getShoppingItemPage(int customerId, int status, Pageable pageable) {
        return shoppingCartDao.findByCustomerIdAndStatus(customerId,status,pageable);
    }

    @Override
    public void deleteShoppingItem(int dishesId,int customerId) {
        ShoppingCart shoppingCart = shoppingCartDao.findByDishesIdAndCustomerIdAndStatus(dishesId,customerId,0);
        shoppingCart.setStatus(1);
        shoppingCartDao.save(shoppingCart);
    }
}
