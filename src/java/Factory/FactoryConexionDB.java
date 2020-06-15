
package Factory;


public class FactoryConexionDB {
    
    public static final int MySQL = 1;
    public static String [] configMySQL = {"bd_inventario", "root", ""}; 
    
    public static ConexionDB open (int tipoBD){
        switch(tipoBD){
            case FactoryConexionDB.MySQL:
              return new MySQLConexionFactory(configMySQL);
            default:
                return null;
        }
    }
}
