// 代码生成时间: 2025-09-23 01:21:48
 * Shopping Cart Service that interacts with the MyBatis mapper to manage cart operations.
 */

package com.example.shopping;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.example.shopping.mapper.CartMapper;
import com.example.shopping.model.CartItem;
import com.example.shopping.model.Cart;
import java.util.List;

public class ShoppingCartService {
    private final SqlSessionFactory sqlSessionFactory;

    public ShoppingCartService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Adds an item to the cart.
     * 
     * @param userId The ID of the user.
     * @param itemId The ID of the item to add.
     * @param quantity The quantity to add.
     * @return True if the item was added successfully, false otherwise.
     */
    public boolean addItemToCart(int userId, int itemId, int quantity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CartMapper mapper = session.getMapper(CartMapper.class);
            mapper.addItemToCart(userId, itemId, quantity);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes an item from the cart.
     * 
     * @param userId The ID of the user.
     * @param itemId The ID of the item to remove.
     * @return True if the item was removed successfully, false otherwise.
     */
    public boolean removeItemFromCart(int userId, int itemId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CartMapper mapper = session.getMapper(CartMapper.class);
            mapper.removeItemFromCart(userId, itemId);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the quantity of an item in the cart.
     * 
     * @param userId The ID of the user.
     * @param itemId The ID of the item to update.
     * @param newQuantity The new quantity for the item.
     * @return True if the quantity was updated successfully, false otherwise.
     */
    public boolean updateItemQuantity(int userId, int itemId, int newQuantity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CartMapper mapper = session.getMapper(CartMapper.class);
            mapper.updateItemQuantity(userId, itemId, newQuantity);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves the cart for a user.
     * 
     * @param userId The ID of the user.
     * @return The cart containing items for the user.
     */
    public Cart getCart(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CartMapper mapper = session.getMapper(CartMapper.class);
            return mapper.getCart(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Clears the cart for a user.
     * 
     * @param userId The ID of the user.
     * @return True if the cart was cleared successfully, false otherwise.
     */
    public boolean clearCart(int userId) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            CartMapper mapper = session.getMapper(CartMapper.class);
            mapper.clearCart(userId);
            session.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}