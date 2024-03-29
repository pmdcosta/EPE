package Beans;

import DataBean.ItemBean;
import DataBean.OrderBean;
import EJBInterface.LogEJBInterface;
import EJBInterface.OrderEJBInterface;
import JPA.Entities.ClientEntity;
import JPA.Entities.OrderEntity;
import JPA.Entities.ProductEntity;
import JPA.Entities.UserEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Stateless
public class OrderEJB implements OrderEJBInterface {
    @PersistenceContext(name="jpaUnit")
    EntityManager entityManager;

    @EJB
    LogEJBInterface logBean;

    @Override
    public boolean addOrder(OrderBean order) {
        try {
            OrderEntity newOrder = new OrderEntity();
            List<ProductEntity> productEntityList = new LinkedList<>();

            for(ItemBean i : order.getItemList()){
                productEntityList.add(getItemEntity(i.getId()));
            }

            newOrder.toEntity(order);
            newOrder.setProductToOrder(productEntityList);
            newOrder.setClient(getClientEntity(order.getClient().getId()));
            newOrder.setUser(getUserEntity(order.getUser().getId()));

            entityManager.persist(newOrder);

            logBean.addLog(LogEJBInterface.LogType.ORDER, new Date() + " - Order Id: " + newOrder.getId()
                + " User: " + newOrder.getUser().getId() + " : " + newOrder.getUser().getUsername()
            );

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<OrderBean> getPendingList() {
        try {
            Query query = entityManager.createQuery("FROM OrderEntity o WHERE o.shipped = false");
            List<OrderEntity> resultList = (List<OrderEntity>)query.getResultList();

            List <OrderBean> finalList = new LinkedList<>();

            for(OrderEntity i : resultList){
                finalList.add(new OrderBean()
                        .setUser(i.getUser().toBean())
                        .setId(i.getId())
                        .setDateOrder(i.getDateOrdered())
                        .setClient(i.getClient().toBean())

                );
            }

            return finalList;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<OrderBean> getShippedList(){
        try {
            Query query = entityManager.createQuery("FROM OrderEntity o WHERE o.shipped = true");
            List<OrderEntity> resultList = (List<OrderEntity>)query.getResultList();

            List <OrderBean> finalList = new LinkedList<>();

            for(OrderEntity i : resultList){
                finalList.add(new OrderBean()
                                .setUser(i.getUser().toBean())
                                .setId(i.getId())
                                .setClient(i.getClient().toBean())
                                .setDateOrder(i.getDateOrdered())

                );
            }

            return finalList;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public OrderBean getOrder(int id){
        OrderEntity temp = getOrderEntity(id);

        if(temp != null)
                return temp.toBean();
        return null;


    }

    private OrderEntity getOrderEntity(int id) {
        try {
            Query query = entityManager.createQuery("FROM OrderEntity o WHERE o.id =:t ");
            query.setParameter("t", id);

            List<OrderEntity> result = (List<OrderEntity>)query.getResultList();

            if (result!=null && !result.isEmpty()){ return result.get(0);  }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean shipOrder(int id){
        try {
            OrderEntity itemToChange = getOrderEntity(id);

            if(itemToChange==null) return false;
            itemToChange
                    .setDateShipped(new Timestamp(new Date().getTime()))
                    .setShipped(true);

            entityManager.persist(itemToChange);
            logBean.addLog(LogEJBInterface.LogType.SHIPPING, new Date() + " - Order Id: " + itemToChange.getId());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ProductEntity getItemEntity(int id){
        try {
            Query query = entityManager.createQuery("FROM ProductEntity u WHERE u.id = :t") ;
            query.setParameter("t", id);

            List<ProductEntity> result = (List<ProductEntity>)query.getResultList();

            if (result!=null && !result.isEmpty()){ return result.get(0); }

        }catch (Exception e){ e.printStackTrace();}
        return null;
    }
    public ClientEntity getClientEntity(int id) {
        try {
            Query query = entityManager.createQuery("FROM ClientEntity u WHERE u.id = :t");
            query.setParameter("t", id);

            List<ClientEntity> result = (List<ClientEntity>) query.getResultList();

            if (result != null && !result.isEmpty()) {
                return result.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserEntity getUserEntity(int id) {
        try {
            Query query = entityManager.createQuery("FROM UserEntity u WHERE u.id = :t");
            query.setParameter("t", id);

            List<UserEntity> result = (List<UserEntity>) query.getResultList();

            if (result != null && !result.isEmpty()) {
                return result.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
