/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

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

/**
 *
 * @author Ninad
 */

public class ProductDAOImpl implements ProductDAO
{
    DataSource ds;
    Connection conn;
    ResultSet rs;
    
    public ProductDAOImpl()
    {
        try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            ds = (DataSource)envCtx.lookup("jdbc/toy");
        }
        catch (NamingException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Product> getAll()
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT * FROM product WHERE approved = 1;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getNoOfProducts()
    {
        int ret = 0;
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT COUNT(*)"
                    + " as num FROM product;").executeQuery();
            if(this.rs.next())
            {
                ret = this.rs.getInt("num");
            }
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getProductFromID(String id)
    {
        Product ret = null;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getBestSelling()
    {
        Product ret = null;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT TOP 1 product.id,"
                    + " SUM(ordered_product.quantity) AS totquantity"
                    + " FROM product, ordered_product"
                    + " WHERE  product.id = ordered_product.product_id"
                    + " GROUP BY product.id ORDER BY totquantity DESC;");
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                String id = rs.getString("id");
                ret = getProductFromID(id);
            }
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public List<Product> getProductFromName(String name)
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.conn = ds.getConnection();
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
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getAllPending()
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE new = '0' AND approved= '0';").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getLatestPending()
    {
        Product ret = null;
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT TOP 1 * FROM product"
                    + " WHERE product.new = '0' AND approved = '0'"
                    + " ORDER BY last_update DESC;").executeQuery();
            if(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getLatest()
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT TOP 8 * FROM product"
                    + " WHERE product.new = '1'"
                    + " ORDER BY last_update DESC;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getLatestRecycled()
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT TOP 4 * FROM product"
                    + " WHERE product.new = '0' AND approved = '1'"
                    + " ORDER BY last_update DESC;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getByFilter(String name, String modelNum, int categoryId)
    {
        String query = getQueryFromFilter(name, modelNum, categoryId, false, "shop");
        
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));                
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
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getRecycledByFilter(String name, String model, int categoryId, String owner)
    {
        String query = getQueryFromFilter(name, model, categoryId, true, owner);
        
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public String getQueryFromFilter(String name, String model, int categoryId, boolean recycled, String owner)
    {
        List<String> query = new ArrayList<String>();
        query.add("SELECT * FROM product");
        query.add(" WHERE");
        
        if(!name.equalsIgnoreCase(""))
        {
            query.add(" name LIKE '%" + name + "%'");
        }
        if(!model.equalsIgnoreCase(""))
        {
            query.add(" model_num LIKE '%" + model + "%'");
        }
        if(categoryId != 0)
        {
            query.add(" category_id = '" + categoryId + "'");
        }
        if(!owner.equalsIgnoreCase(""))
        {
            query.add(" owner LIKE '%" + owner + "%'");
        }
        if(recycled)
        {
            query.add(" new = '0'");
        }
        else
        {
            query.add(" new = '1'");
        }
        
        query.add(";");
        
        String ret = "";
        
        ret += query.get(0) + query.get(1);
        
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

    public List<Product> getByFilter(List<String> brand, List<Integer> categoryId)
    {   
        List<Product> ret = new ArrayList<Product>();
        String query;
        try
        {
            this.conn = ds.getConnection();
            query = getQueryFromFilter(brand, categoryId);
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));                
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
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    private String getQueryFromFilter(List<String> brand, List<Integer> categoryId)
    {
        List<String> query = new ArrayList<String>();
        query.add("SELECT * FROM product");
        query.add(" WHERE approved = 1 AND (");
        for(String brandToCheck: brand)
        {
            if(!brandToCheck.equalsIgnoreCase(""))
            {
                query.add(" brand LIKE '%" + brandToCheck + "%'");
            }
        }
        for(int categoryIdToCheck: categoryId)
        {
            if(categoryIdToCheck != 0)
            {
                query.add(" category_id = '" + categoryIdToCheck + "'");
            }
        }
        query.add(");");
        
        String ret = "";
        
        ret += query.get(0) + query.get(1);
        
        for(int i = 2; i <= (query.size() - 2); i++)
        {
            ret += query.get(i);
            if(!(query.get(i + 1).equalsIgnoreCase(");")))
            {
                ret += " OR";
            }
        }
        
        ret += query.get(query.size() - 1);
        
        return ret;
    }
    
    public List<Product> getRecycledByOwner(String owner)
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product"
                        + " WHERE owner = ?;");
            ps.setString(1, owner);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAOImpl();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
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
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    //if recycled enter owner, else if new, owner input can be anything
    public boolean addProduct(Product product, String category)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO product "
                    + "(id, name, model_num, category_id, quantity, price, "
                    + "brand, description, add_info, last_update, new, approved, owner) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?) ;");
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getModelNum());
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            ps.setInt(4, categoryDAO.getCategoryIDFromName(category));
            ps.setInt(5, product.getQuantity());
            ps.setDouble(6, product.getPrice());
            ps.setString(7, product.getBrand());
            ps.setString(8, product.getDescription());
            ps.setString(9, product.getAddInfo());
            ps.setBoolean(10, product.isNew1());
            ps.setBoolean(11, product.isApproved());
            ps.setString(12, product.getOwner());
            
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean updateProduct(Product product)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET "
                    + "name = ?, "
                    + "model_num = ?, "
                    + "category_id = ?, "
                    + "quantity = ?, "
                    + "price = ?, "
                    + "brand = ?, "
                    + "description = ?, "
                    + "add_info = ?, "
                    + "last_update = CURRENT_TIMESTAMP, "
                    + "new = ?, "
                    + "approved = ?, "
                    + "owner = ? "
                    + "WHERE id = ?;");
            ps.setString(1, product.getName());
            ps.setString(2, product.getModelNum());
            ps.setInt(3, product.getCategoryId().getId());
            ps.setInt(4, product.getQuantity());
            ps.setDouble(5, product.getPrice());
            ps.setString(6, product.getBrand());
            ps.setString(7, product.getDescription());
            ps.setString(8, product.getAddInfo());
            ps.setBoolean(9, product.isNew1());
            ps.setBoolean(10, product.isApproved());
            ps.setString(11, product.getOwner());
            ps.setString(12, product.getId());
            
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean approveRecycled(String id)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET"
                    + " approved = ?, last_update = CURRENT_TIMESTAMP WHERE id = ?;");
            ps.setBoolean(1, true);
            ps.setString(2, id);
            
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public int getQuantityById(String id)
    {
        int ret = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT quantity FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                ret = this.rs.getInt("quantity");
            }
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public String getNameById(String id)
    {
        String ret = "";
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT name FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                ret = this.rs.getString("name");
            }
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public double getPriceById(String id)
    {
        double ret = 0.0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT price FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                ret = this.rs.getDouble("price");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public boolean updateQuantity(String productID, int quantity)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET"
                    + " quantity = ?, last_update = CURRENT_TIMESTAMP WHERE id = ?;");
            ps.setInt(1, quantity);
            ps.setString(2, productID);
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public String[] getRelated(String productId)
    {
        String query = "SELECT DISTINCT(ordered_product.product_id) FROM ordered_product WHERE ordered_product.order_id IN "
                    + "(SELECT order_history.id FROM order_history WHERE order_history.customer_id IN "
                    + "(SELECT order_history.customer_id FROM order_history WHERE order_history.id IN "
                    + "(SELECT ordered_product.order_id FROM ordered_product WHERE ordered_product.product_id = ?)));";
        String[] related = {"", "", ""};
        
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, productId);
            this.rs = ps.executeQuery();
            int i = 0;
            while(this.rs.next() && i < 3)
            {
                related[i] = this.rs.getString("product_id");
                i++;
            }
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return related;
    }
    
    public List<String> listAllBrands()
    {
        List<String> ret = new ArrayList<String>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT DISTINCT(brand) FROM product;").executeQuery();
            while(this.rs.next())
            {
                ret.add(this.rs.getString("brand"));
            }
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public boolean checkIfNew(String id)
    {
        boolean ret = true;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT new FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                ret = this.rs.getBoolean("new");
            }
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
