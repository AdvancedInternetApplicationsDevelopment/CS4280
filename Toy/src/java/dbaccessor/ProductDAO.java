/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Ninad
 */

public class ProductDAO
{
    Connection conn;
    ResultSet rs;
    
    public ProductDAO()
    {
        try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/toy");
            this.conn = ds.getConnection();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NamingException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeDB()
    {
        try
        {
            conn.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Product> getAll()
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM product;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                categoryDAO.closeDB();
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                
                ret.add(product);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getNoOfProducts()
    {
        int ret = 0;
        try
        {
            this.rs = conn.prepareStatement("SELECT COUNT(*)"
                    + " as num FROM product;").executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getInt("num");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getProductFromID(String id)
    {
        Product ret = null;
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                categoryDAO.closeDB();
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                ret = product;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getBestSelling()
    {
        Product ret = null;
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT product.id,"
                    + " SUM(ordered_product.quantity) AS totquantity"
                    + " FROM product, ordered_product"
                    + " WHERE  product.id = ordered_product.product_id"
                    + " GROUP BY product.id ORDER BY totquantity DESC LIMIT 1");
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                String id = rs.getString("id");
                ret = getProductFromID(id);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public List<Product> getProductFromName(String name)
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE name LIKE ?;");
            ps.setString(1, "%" + name + "%");
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                categoryDAO.closeDB();
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                ret.add(product);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public ProductImage getImageByID(String id)
    {
        ProductImage ret = new ProductImage();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product"
                        + " WHERE id = ?;");
                ps.setString(1, id);
                this.rs = ps.executeQuery();
                while(this.rs.next())
                {
                    ProductImage product = new ProductImage();
                    product.setId(this.rs.getString("id"));
                    product.setImage(this.rs.getBlob("image"));
                    ret = product;
                }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getAllPending()
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE new = '0';").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                categoryDAO.closeDB();
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                
                ret.add(product);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getLatestPending()
    {
        Product ret = null;
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE product.new = '0'"
                    + " ORDER BY last_update DESC"
                    + " LIMIT 1;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                categoryDAO.closeDB();
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                ret = product;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getByFilter(String name, String modelNum, int categoryId)
    {
        String query = getQueryFromFilter(name, modelNum, categoryId, false);
        
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                categoryDAO.closeDB();
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                
                ret.add(product);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getRecycledByFilter(String name, String modelNum, int categoryId)
    {
        String query = getQueryFromFilter(name, modelNum, categoryId, true);
        
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                categoryDAO.closeDB();
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                
                ret.add(product);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    private String getQueryFromFilter(String name, String modelNum, int categoryId, boolean recycled)
    {
        List<String> query = new ArrayList<String>();
        query.add("SELECT * FROM product");
        query.add(" WHERE");
        
        if(!name.equalsIgnoreCase(""))
        {
            query.add(" name LIKE '%" + name + "%'");
        }
        if(!modelNum.equalsIgnoreCase(""))
        {
            query.add(" model_num LIKE '%" + modelNum + "%'");
        }
        if(categoryId  != 0)
        {
            query.add(" category_id = '" + categoryId + "'");
        }
        
        query.add(";");
        
        String ret = "";
        
        ret += query.get(0) + query.get(1);
        if(recycled)
        {
            ret += " new = '0'";
        }
        
        for(int i = 2; i <= (query.size() - 2); i++)
        {
            ret += query.get(i);
            if(!(query.get(i + 1).equalsIgnoreCase(";")))
            {
                ret += " AND";
            }
        }
        
        ret += query.get(query.size() - 1);
        
        return ret;
    }
    
    //if recycled enter owner, else if new, owner input can be anything
    public boolean addProduct(Product product, Blob image, String category, 
            boolean recycled, String owner)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO product "
                    + "(id, name, model_num, category_id, quantity, available, price, "
                    + "brand, description, add_info, image, last_update, new, approved, owner) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, product.getName());
            ps.setString(3, product.getModelNum());
            CategoryDAO categoryDAO = new CategoryDAO();
            if(categoryDAO.categoryExists(category))
            {
                ps.setInt(4, categoryDAO.getCategoryIDFromName(category));
            }
            else
            {
                categoryDAO.addCategory(category);
                ps.setInt(4, categoryDAO.getCategoryIDFromName(category));
            }
            categoryDAO.closeDB();
            ps.setInt(5, product.getQuantity());
            ps.setBoolean(6, product.isAvailable());
            ps.setDouble(7, product.getPrice());
            ps.setString(8, product.getBrand());
            ps.setString(9, product.getDescription());
            ps.setString(10, product.getAddInfo());
            ps.setBlob(11, image);
            ps.setTimestamp(12, product.getLastUpdate());
            if(recycled)
            {
                ps.setBoolean(13, false);
                ps.setBoolean(14, false);
                ps.setString(15, owner);
            }
            else
            {
                ps.setBoolean(13, true);
                ps.setBoolean(14, true);
                ps.setString(15, "shop");
            }
            
            rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean updateProduct(Product product)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET "
                    + "name = ?, "
                    + "model_num = ?, "
                    + "category_id = ?, "
                    + "quantity = ?, "
                    + "available = ?, "
                    + "price = ?, "
                    + "brand = ?, "
                    + "description = ?, "
                    + "add_info = ?, "
                    + "last_update = ?, "
                    + "new = ?, "
                    + "approved = ?, "
                    + "owner = ?"
                    + " WHERE id = ?;");
            ps.setString(1, product.getName());
            ps.setString(2, product.getModelNum());
            ps.setInt(3, product.getCategoryId().getId());
            ps.setInt(4, product.getQuantity());
            ps.setBoolean(5, product.isAvailable());
            ps.setDouble(6, product.getPrice());
            ps.setString(7, product.getBrand());
            ps.setString(8, product.getDescription());
            ps.setString(9, product.getAddInfo());
            ps.setTimestamp(10, product.getLastUpdate());
            ps.setBoolean(11, product.isNew1());
            ps.setBoolean(12, product.isApproved());
            ps.setString(13, product.getOwner());
            ps.setString(14, product.getId());
            
            rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean approveRecycled(String id)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET"
                        + " approved = ? WHERE id = ?;");
                ps.setBoolean(1, true);
                ps.setString(2, id);
                rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean updateImageByID(String id, Blob image)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET"
                        + " image = ? WHERE id = ?;");
                ps.setBlob(1, image);
                ps.setString(2, id);
                rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public int getQuantityById(String id)
    {
        int ret = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT quantity FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getInt("quantity");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public String getNameById(String id)
    {
        String ret = "";
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getString("name");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public double getPriceById(String id)
    {
        double ret = 0.0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT price FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getDouble("price");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    
    public boolean updateQuantity(String productID, int quantity)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET"
                        + " quantity = ? WHERE id = ?;");
                ps.setInt(1, quantity);
                ps.setString(2, productID);
                rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public String[] getRelated(String productId)
    {
        String query = "SELECT DISTINCT(product_id) FROM ordered_product WHERE order_id IN "
                    + "(SELECT order_id FROM order_history WHERE customer_id IN "
                    + "(SELECT customer_id FROM order_history WHERE order_id IN "
                    + "(SELECT order_id FROM ordered_product WHERE product_id = ?)))"
                    + "LIMIT 4;";
        String[] related = {"", "", "", ""};
        
        try
        {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, productId);
            this.rs = ps.executeQuery();
            int i = 0;
            while(this.rs.next())
            {
                related[i] = this.rs.getString("product_id");
                i++;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return related;
    }
    
    public List<String> listAllBrands()
    {
        List<String> ret = new ArrayList<String>();
        try
        {
            this.rs = conn.prepareStatement("SELECT DISTINCT(brand) FROM product;").executeQuery();
            while(this.rs.next())
            {
                ret.add(this.rs.getString("brand"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
