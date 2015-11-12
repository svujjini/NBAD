package pdata;

import java.sql.*;
import java.util.ArrayList;
import model.CategoryBean;
import model.ProductBean;


public class ProductDB {
    
    
    ArrayList<CategoryBean> catList = new ArrayList<>();
    
    

    public void LoadData() {
        
        CategoryBean cat1 = new CategoryBean();
        cat1.setCatID(10);
        cat1.setCatName("Paintings");
        catList.add(cat1);
        
        CategoryBean cat2 = new CategoryBean();
        cat2.setCatID(11);
        cat2.setCatName("PebbleArt");
        catList.add(cat2);
    }
    public Object getCategories(){
        return catList;
    }
    public static void addProduct( int productID, String productName,String category, float price, String description, String imgURL){
        ProductBean product = new ProductBean();
        product.setProductID(productID);
        product.setProductName(productName);
        product.setCategory(category);
        product.setPrice(price);
        product.setDescription(description);
        product.setImgURL(imgURL);
        addProduct(product);
    }
    public static int addProduct(ProductBean product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO Product (productID,productName,category,price,description,imgURL) "
                + "VALUES (?, ?, ?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            
            ps.setInt(1, product.getProductID());
            ps.setString(3, product.getProductName());
            ps.setString(4, product.getCategory());
            ps.setDouble(5, product.getPrice());
            ps.setString(6, product.getDescription());
            ps.setString(7, product.getImgURL());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static ArrayList<ProductBean> getAllProducts(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product ";
        try {
            ps = connection.prepareStatement(query);
            //ps.setInt(1, productID);
            rs = ps.executeQuery();
            ArrayList<ProductBean> productList = new ArrayList<>();
            if (rs.next()) {
                ProductBean product = new ProductBean();
                product.setProductID(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                product.setImgURL(rs.getString("imgURL"));
                productList.add(product);
            }
            return productList;
        }catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        
    }
    }
    /**
     *
     * @param productID
     * @return
     */
    public static ProductBean getProduct(int productID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Product "
                + "WHERE productID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            ProductBean product = null;
            if (rs.next()) {
                product = new ProductBean();
                 product.setProductID(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setImgURL(rs.getString("imgURL"));
            }
            return product;
        }catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }    
    public static int delete(ProductBean product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM Product "
                + "WHERE productID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, product.getProductID());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
public static int update(ProductBean product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE Product SET "
                + "productName = ?, "
                + "category = ? "
                + "price = ? "
                + "description = ? "
                + "imgURL = ? "
                + "WHERE productID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getCategory());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getDescription());
            ps.setString(6, product.getImgURL());
            

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
}
