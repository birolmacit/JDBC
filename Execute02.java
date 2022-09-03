package JDBC;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Execute02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connectionBaglantisi = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", ".....");
        Statement st = connectionBaglantisi.createStatement();


        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        String sql1 = "SELECT country_name FROM countries WHERE region_id = 1";
        boolean r1 = st.execute(sql1);
        System.out.println(r1);//true yada false verir. çünkü data çağırma işlemi yaptık.




        //REcordları görmek için executeQuery() methodu kullanmalıyız.
        ResultSet resultsetimiz1=st.executeQuery(sql1);   //burada sorguyu bir set icine atıyor ve yeni olusturdugumuz setdeki elemanları cagırıcaz bunun icin de iterator kullanabiliriz ama sart degıl
        // for loop ile de cagırılabilirdi
        while (resultsetimiz1.next()){
            System.out.println(resultsetimiz1.getString("country_name"));

        }





        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2="select country_id, country_name from countries where region_id>2";
        ResultSet resultSetimiz2=st.executeQuery(sql2);
        while (resultSetimiz2.next()){
            System.out.println( resultSetimiz2.getString("country_id")+"...."+(resultSetimiz2.getString("country_name")));
        }



        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        ResultSet resultSetimiz3=st.executeQuery("select * from companies where number_of_employees=(select min(number_of_employees) from companies) ");
        while (resultSetimiz3.next()) {
            System.out.println(resultSetimiz3.getInt("company_id") + "...." + resultSetimiz3.getString("company") + "..." + resultSetimiz3.getInt("number_of_employees"));
            System.out.println(resultSetimiz3.getInt(1) + "...." + resultSetimiz3.getString(2) + "..." + resultSetimiz3.getInt(3));//bu sekılde sutun ındex ınden de cagırılabılır
        }
        connectionBaglantisi .close();
        st.close();

    }}