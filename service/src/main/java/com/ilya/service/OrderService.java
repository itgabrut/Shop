package com.ilya.service;

import com.ilya.model.Client;
import com.ilya.model.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 09.09.2016.
 */
public interface OrderService {

    boolean addOrder(Map<Integer,Integer> m,Client current);

    List<Order> getOrdersByClientId(int id);
}
