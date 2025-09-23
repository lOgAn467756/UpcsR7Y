// 代码生成时间: 2025-09-23 14:01:33
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;
import java.util.UUID;

/**
 * ShoppingCartService class, handles the business logic for shopping cart operations.
 */
public class ShoppingCartService {

    private SqlSessionFactory sqlSessionFactory;

    public ShoppingCartService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Adds an item to the shopping cart.
     *
     * @param userId the ID of the user owning the cart
     * @param productId the ID of the product to add
     * @return a unique identifier for the cart item
     * @throws Exception if any database operation fails
     */
    public String addItemToCart(String userId, String productId) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            String cartItemId = UUID.randomUUID().toString();
            session.insert("CartMapper.addItemToCart", new CartItem(userId, productId, cartItemId));
            session.commit();
            return cartItemId;
        } catch (Exception e) {
            throw new Exception("Failed to add item to cart", e);
        }
    }

    /**
     * Removes an item from the shopping cart.
     *
     * @param cartItemId the ID of the cart item to remove
     * @throws Exception if any database operation fails
     */
    public void removeItemFromCart(String cartItemId) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("CartMapper.removeItemFromCart", cartItemId);
            session.commit();
        } catch (Exception e) {
            throw new Exception("Failed to remove item from cart", e);
        }
    }

    /**
     * Retrieves all items in the shopping cart for a given user.
     *
     * @param userId the ID of the user
     * @return a list of cart items
     * @throws Exception if any database operation fails
     */
    public List<CartItem> getCartItems(String userId) throws Exception {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("CartMapper.getCartItems", userId);
        } catch (Exception e) {
            throw new Exception("Failed to retrieve cart items", e);
        }
    }
}

/**
 * CartItem class, represents an item in the shopping cart.
 */
class CartItem {
    private String userId;
    private String productId;
    private String cartItemId;

    public CartItem(String userId, String productId, String cartItemId) {
        this.userId = userId;
        this.productId = productId;
        this.cartItemId = cartItemId;
    }

    // Getters and setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getCartItemId() { return cartItemId; }
    public void setCartItemId(String cartItemId) { this.cartItemId = cartItemId; }
}
