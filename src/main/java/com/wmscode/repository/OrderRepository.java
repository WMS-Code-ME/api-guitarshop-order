package com.wmscode.repository;

import org.bson.types.ObjectId;

import com.wmscode.commons.exception.OrderNotFoundException;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class OrderRepository implements PanacheMongoRepositoryBase<OrderDocument, String>{

    public void deleteByIdOrder(String idOrder) {
        delete("idOrder", idOrder);
      }
    
      public OrderDocument findByIdOrder(String idOrder) {
        return find("_id = ?1", new ObjectId(idOrder))
          .stream()
          .findFirst()
          .orElseThrow(() -> new OrderNotFoundException("Pedido não encontrado"));
      }

    public Object findByName(String name) {
        return find("name = ?1", name).firstResult();
    }

}
