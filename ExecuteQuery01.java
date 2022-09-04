package JDBC;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connectionBaglantisi = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "......");
        Statement st = connectionBaglantisi.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        //1. Yol OFFSET ve FETCH NEXT kullanarak
        String sql1 = "select company, number_of_employees from companies order by number_of_employees desc offset 1 row fetch next 1 row only";
        System.out.println(st.executeQuery(sql1) );   //st.executeQuery() bize bir set verecegi icin direkt yazdırma referans adresını verir
       ResultSet resultSetimiz= st.executeQuery(sql1);
       while(resultSetimiz.next()) {
           System.out.println(resultSetimiz.getString("company") + "   " + resultSetimiz.getString("number_of_employees"));
       }


        //2. Yol Subquery kullanarak

ResultSet resultSetimiz2= st.executeQuery("select company, number_of_employees from companies where number_of_employees =(select max(number_of_employees)from companies where number_of_employees<(select max(number_of_employees) from companies))");
        while(resultSetimiz2.next()) {
            System.out.println(resultSetimiz2.getString("company") + "   " + resultSetimiz2.getString("number_of_employees"));
        }
        connectionBaglantisi .close() ;
        st.close() ;
        resultSetimiz2 .close() ;
        resultSetimiz.close() ;
    }
}

