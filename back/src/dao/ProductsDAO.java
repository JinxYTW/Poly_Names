package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import database.PolyNameDatabase;
import models.Product;

public class ProductsDAO {
    public ProductsDAO(){

    }
    public ArrayList<Product> findAll(){
        ArrayList<Product> res=new ArrayList<>();
        try{
            PolyBayDatabase my_Database= new PolyBayDatabase();
            String request="SELECT * FROM product ORDER BY name";
            PreparedStatement prepStat=my_Database.prepareStatement(request);
            ResultSet results = prepStat.executeQuery();      
            while (results.next()){
                final int identifiant=results.getInt("id");
                final String name= results.getString("name");
                final String owner=results.getString("owner");
                final float bid=results.getFloat("bid");
                Product myRecord = new Product(identifiant,name,owner,bid);
                res.add(myRecord);
                }
                return res;
            }
        catch(Exception e){
            System.out.println(e.getMessage());
            return res;
        }
    }
}
