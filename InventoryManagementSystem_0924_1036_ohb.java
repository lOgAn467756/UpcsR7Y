// 代码生成时间: 2025-09-24 10:36:31
package com.example.inventory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import java.io.Reader;
import java.util.List;

/**
 * Inventory Management System using MyBatis framework.
 * This class provides methods to interact with the inventory database.
 */
public class InventoryManagementSystem {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * Constructor to initialize the SQL session factory.
     * @param resourcePath Path to the MyBatis configuration file.
     */
    public InventoryManagementSystem(String resourcePath) {
        try {
            Reader reader = Resources.getResourceAsReader(resourcePath);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing SQL Session Factory");
        }
    }

    /**
     * Method to get all items in the inventory.
     * @return List of inventory items.
     */
    public List<InventoryItem> getAllItems() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("InventoryMapper.selectAllItems");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving all items");
        }
    }

    /**
     * Method to add a new item to the inventory.
     * @param item The inventory item to be added.
     * @return The inserted item with generated ID.
     */
    public InventoryItem addItem(InventoryItem item) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("InventoryMapper.insertItem", item);
            session.commit();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding new item");
        }
    }

    /**
     * Method to update an existing item in the inventory.
     * @param item The inventory item with updated details.
     * @return The updated item.
     */
    public InventoryItem updateItem(InventoryItem item) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.update("InventoryMapper.updateItem", item);
            session.commit();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating item");
        }
    }

    /**
     * Method to delete an item from the inventory.
     * @param itemId The ID of the item to be deleted.
     * @return The deleted item.
     */
    public InventoryItem deleteItem(int itemId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            InventoryItem item = session.selectOne("InventoryMapper.selectItemById", itemId);
            session.delete("InventoryMapper.deleteItem", itemId);
            session.commit();
            return item;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting item");
        }
    }

    // Main method for testing the InventoryManagementSystem class
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem("mybatis-config.xml");
        // Add testing logic here
    }
}

// InventoryItem.java
package com.example.inventory;

public class InventoryItem {
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Getters and setters...
}