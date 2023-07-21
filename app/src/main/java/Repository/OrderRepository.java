package Repository;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import DAOs.IOrderDAO;
import DAOs.IWishDAO;
import DAOs.OrderRoomDatabase;
import DAOs.WishRoomDatabase;
import models.Order;
import models.Product;
import models.Wish;

public class OrderRepository {
    OrderRoomDatabase orderRoomDatabase;
    IOrderDAO orderDAO;
    Application application;
    public OrderRepository(Application application){
        orderRoomDatabase = OrderRoomDatabase.getDatabase(application);
        orderDAO = orderRoomDatabase.orderDAO();
        this.application = application;
    }
    public long insertOrder(Order order){
        return orderDAO.insert(order);
    }

    public List<Order> GetOrdersByUserId(int userId)
    {
        return orderDAO.getOrderByUserId(userId);
    }

    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);

    public void removeAll() {
        orderDAO.removeAll();
    }
}
