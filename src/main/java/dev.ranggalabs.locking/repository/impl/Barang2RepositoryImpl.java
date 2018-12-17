package dev.ranggalabs.locking.repository.impl;

import dev.ranggalabs.locking.repository.Barang2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

/**
 * Created by erlangga on 09/12/18.
 */
@Repository
public class Barang2RepositoryImpl implements Barang2Repository {

    @Autowired
    private Sql2o sql2o;

    // select count(a.id),b.stock from barang2 b join temp_barang2 a on a.name=b.name group by b.name;
    // select if((count(a.id) < b.stock),true,false) AS result from barang2 b join temp_barang2 a on a.name=b.name group by b.name;

    @Override
    public Integer isEligible(String name) {
        String sql = "select if((count(a.id) < b.stock),true,false) AS result from barang2 b join temp_barang2 a on a.name=b.name where b.name=:name group by b.name";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("name", name)
                    .executeScalar(Integer.class);
        }
    }

    @Override
    public boolean saveBarang2(String name) {
        try (Connection conn = sql2o.beginTransaction()) {
            StringBuilder sqlInsert = new StringBuilder("INSERT INTO barang2 (");
            sqlInsert.append("name,stock)")
                    .append(" VALUES(");
            sqlInsert.append(":name,:stock)");

            Query queryInsert = conn.createQuery(sqlInsert.toString())
                    .addParameter("name", name)
                    .addParameter("stock",100);
            queryInsert.executeUpdate();
            conn.commit();
            return true;
        }
    }

    @Override
    public boolean saveTempBarang(String name) {
        try (Connection conn = sql2o.beginTransaction()) {
            StringBuilder sqlInsert = new StringBuilder("INSERT INTO temp_barang2 (");
            sqlInsert.append("name)")
                    .append(" VALUES(");
            sqlInsert.append(":name)");

            Query queryInsert = conn.createQuery(sqlInsert.toString())
                    .addParameter("name", name);
            queryInsert.executeUpdate();
            conn.commit();
            return true;
        }
    }

    @Override
    public boolean saveBarangAndTemp(String name) {
        try (Connection conn = sql2o.beginTransaction()) {
            StringBuilder sqlInsert = new StringBuilder("INSERT INTO barang2 (");
            sqlInsert.append("name,stock)")
                    .append(" VALUES(");
            sqlInsert.append(":name,:stock)");

            Query queryInsert = conn.createQuery(sqlInsert.toString())
                    .addParameter("name", name)
                    .addParameter("stock",100);
            queryInsert.executeUpdate();

            sqlInsert = new StringBuilder("INSERT INTO temp_barang2 (");
            sqlInsert.append("name)")
                    .append(" VALUES(");
            sqlInsert.append(":name)");

            queryInsert = conn.createQuery(sqlInsert.toString())
                    .addParameter("name", name);
            queryInsert.executeUpdate();

            conn.commit();
            return true;
        }
    }
}
