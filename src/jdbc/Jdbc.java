package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import charactor.Hero;

public class Jdbc {
	
//	public static Hero get(int id) {
//		Hero hero = null;
//		// TODO Auto-generated method stub
//		try {
//			 Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO: handle exception
//			 e.printStackTrace();
//		}  
//		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root", "admin");
//			Statement s = c.createStatement();) { 
//			
//			String sql="select * from hero where id ="+id;
//			
//			ResultSet rs = s.executeQuery(sql);
//			if(rs.next()) {
//				hero =new Hero();
//				hero.name = rs.getString(2);
//	            hero.hp = rs.getFloat(3);
//	            hero.damage = rs.getInt(4);
//	            hero.id = id;
//			}
//			
//		}catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return hero;
//	}
	
	public static void main(String[] args) { 
		HeroDAO heroDAO =new HeroDAO();
		Hero hero;
		Hero hero1 = new Hero();
		hero1.damage=10;
		hero1.hp=0;
		hero1.name="123云";
		heroDAO.delete(24);
        List<Hero> heros = new ArrayList<>();
        heroDAO.add(hero1);
        heros = heroDAO.list(0,5);
        for (Hero hero2 : heros) {
			System.out.println(hero2.name);
		}
    }
	
}

