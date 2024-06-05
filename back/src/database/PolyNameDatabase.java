
package database;

public class PolyNameDatabase extends MySQLDatabase{
    public PolyNameDatabase() throws Exception{
        super("localhost", 3306, "poly_names", "root", "");
    }
   
}
