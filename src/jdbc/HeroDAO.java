package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import charactor.Hero;

public class HeroDAO implements DAO<Hero> {
	public  HeroDAO() {
		// TODO Auto-generated constructor stub
		try {
			 Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}  
	}
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root",
		        "admin");
	}
	public int getTotal() {
		int total=0;
		try(Connection c = getConnection();Statement s = c.createStatement();) {           
			String sql = "select count(*) from hero";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
  
            System.out.println("total:" + total);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
		
	}
	
	
	@Override
	public void add(Hero t) {
		String sql = "insert into hero values(null,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
  
            ps.setString(1, t.name);
            ps.setFloat(2, t.hp);
            ps.setInt(3, t.damage);
  
            ps.execute();
  
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
               t.id = id;
            }
        } catch (SQLException e) {
  
            e.printStackTrace();
        }
	}

	@Override
	public void update(Hero t) {
		String sql = "update hero set name= ?, hp = ? , damage = ? where id = ?";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
			 ps.setString(1, t.name);
	            ps.setFloat(2, t.hp);
	            ps.setInt(3, t.damage);
	            ps.setInt(4, t.id);
	            ps.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try (Connection c = getConnection(); Statement s = c.createStatement();) {  
            String sql = "delete from hero where id = " + id;
            if(s.executeUpdate(sql) !=0 ) {
            	System.out.println("d");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public Hero get(int id) {
		Hero hero = null;
		try (Connection c = getConnection();Statement s = c.createStatement();) { 
				String sql="select * from hero where id ="+id;
				ResultSet rs = s.executeQuery(sql);
				if(rs.next()) {
					hero =new Hero();
					hero.name = rs.getString(2);
		            hero.hp = rs.getFloat(3);
		            hero.damage = rs.getInt(4);
		            hero.id = id;
				}	
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return hero;
	}

	@Override
	public List<Hero> list() {
		// TODO Auto-generated method stub
		return list(0, Short.MAX_VALUE);
	}

	@Override
	public List<Hero> list(int start, int count) {
		// TODO Auto-generated method stub
		 List<Hero> heros = new ArrayList<Hero>();
		  
	        String sql = "select * from hero order by id desc limit ?,? ";
	  
	        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
	  
	            ps.setInt(1, start);
	            ps.setInt(2, count);
	  
	            ResultSet rs = ps.executeQuery();
	  
	            while (rs.next()) {
	                Hero hero = new Hero();
	                int id = rs.getInt(1);
	                String name = rs.getString(2);
	                float hp = rs.getFloat("hp");
	                int damage = rs.getInt(4);
	                hero.id = id;
	                hero.name = name;
	                hero.hp = hp;
	                hero.damage = damage;
	                heros.add(hero);
	            }
	        } catch (SQLException e) {
	  
	            e.printStackTrace();
	        }
	        return heros;
	}


	
}
