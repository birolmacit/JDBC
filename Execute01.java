package JDBC;

import java.sql.*;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.adım : driver a kaydolma
        Class.forName("org.postgresql.Driver");


        //2.adım : database baglama
        Connection connectionObjesi = DriverManager .getConnection("jdbc:postgresql://localhost:5432/techproed","postgres",".....");


        //3.adım : statement olustur
        Statement st = connectionObjesi .createStatement();



        //4.adım: Query calıstır



        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        String sq1="CREATE TABLE workers(worker_id varchar(50), worker_name varchar(50), worker_salary int)";  //bu sekılde stringe atamamızın tek nedeni temiz daha uygun olsun diye
        boolean result= st.execute(sq1) ;
        //st.execute("CREATE TABLE workers(worker_id varchar(50), worker_name varchar(50), worker_salary int)");     bu sekılde de calıstırabilir



       // st.execute("CREATE TABLE workers2(worker_id varchar(50), worker_name varchar(50), worker_salary int)"); //st.execute kodu sql e komut gondermek ,c,n kullanılıyor

        System.out.println(result);//False return yapar, çünkü data çağrılmadı.




        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        st.execute("alter table workers add worker_adress varchar(50)") ;





        //3.Örnek: Drop workers table
        st.execute("drop table workers");



        //5. Adım: Bağlantı ve Statement'ı kapat.
        connectionObjesi .close();
        st.close() ;


    }
}


