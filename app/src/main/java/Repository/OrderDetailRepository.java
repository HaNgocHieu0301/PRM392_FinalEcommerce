package Repository;

import android.app.Application;

import DAOs.IOrderDAO;
import DAOs.IOrderDetailDAO;
import DAOs.OrderDetailRoomDatabase;
import DAOs.OrderRoomDatabase;
import models.Order;
import models.OrderDetail;

public class OrderDetailRepository {
    OrderDetailRoomDatabase orderDetailRoomDatabase;
    IOrderDetailDAO orderDetailDAO;
    Application application;
    public OrderDetailRepository(Application application){
        orderDetailRoomDatabase = OrderDetailRoomDatabase.getDatabase(application);
        orderDetailDAO = orderDetailRoomDatabase.orderDetailDAO();
        this.application = application;
    }
    public void insertOrderDetail(OrderDetail orderDetail){
        orderDetailDAO.insert(orderDetail);
    }
}
