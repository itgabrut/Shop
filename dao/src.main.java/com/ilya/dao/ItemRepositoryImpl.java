package com.ilya.dao;

import com.ilya.model.Item;
import com.ilya.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by ilya on 28.08.2016.
 */
public class ItemRepositoryImpl implements ItemRepository {

    private EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    @Override
    public void saveWithoutFoto(Item item) {
        Item old = getItem(item.getId());
        item.setFoto(old.getFoto());
        item.setVersion(old.getVersion());
        save(item);
    }

    public Item getItem(int itemId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Item.class,itemId);
    }

    public boolean deleteItem(int itemId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Item item = entityManager.find(Item.class,itemId);
        entityManager.getTransaction().begin();
        entityManager.remove(item);
        entityManager.getTransaction().commit();
        if(entityManager.getTransaction().isActive()){
            entityManager.getTransaction().rollback();
            return false;
        }
        entityManager.close();
        return true;
    }

    public List<Item> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select i from Item i",Item.class).getResultList();
    }

    public boolean save(Item item) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        if(item.getId()==0) {
            long a = (long) entityManager.createQuery("select count(i.name) from Item i where i.name =:curr").setParameter("curr", item.getName()).getSingleResult();
            if (a < 1) {
                entityManager.persist(item);
            }
        }
        else entityManager.merge(item);
        entityManager.getTransaction().commit();
        if(entityManager.isOpen())return false;
        return true;
    }

    public List<String> getThemes(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
         return entityManager.createNamedQuery("Item.getThemes",String.class).getResultList();
    }

    public List<Item> getItemsByTheme(String theme){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Item> query = entityManager.createQuery("select i from Item i where i.theme = :nameOfTheme",Item.class);
        return query.setParameter("nameOfTheme",theme).getResultList();
    }
}
