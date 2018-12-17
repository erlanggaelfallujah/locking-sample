package dev.ranggalabs.locking.repository.impl;

import dev.ranggalabs.locking.repository.BarangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.text.SimpleDateFormat;

/**
 * Created by erlangga on 09/12/18.
 */
@Repository
public class BarangRepositoryImpl implements BarangRepository {

    @Autowired
    private Sql2o sql2o;

    private static final int STOCK = 100;

    @Override
    public boolean save(String name, long value) {
        StringBuilder sqlSelect = new StringBuilder("SELECT current FROM barang WHERE name = :name FOR UPDATE");
        try (Connection conn = sql2o.beginTransaction()) {
            Long current = conn.createQuery(sqlSelect.toString())
                    .addParameter("name", name)
                    .executeScalar(Long.class);

            if (current == null) {
                current = 1L;
                StringBuilder sqlInsert = new StringBuilder("INSERT INTO barang (");
                sqlInsert.append("name,")
                        .append("stock,")
                        .append("current)")
                        .append(" VALUES(");
                sqlInsert.append(":name,:stock,:current)");

                Query queryInsert = conn.createQuery(sqlInsert.toString())
                        .addParameter("name", name)
                        .addParameter("stock", STOCK)
                        .addParameter("current", current);
                queryInsert.executeUpdate();
                conn.commit();
                return true;
            }

            if(current>=STOCK){
                conn.commit();
                return false;
            }

            StringBuilder sqlUpdate = new StringBuilder("UPDATE barang SET current = :current " +
                    "WHERE name = :name");

            conn.createQuery(sqlUpdate.toString())
                    .addParameter("name", name)
                    .addParameter("current", current + value).executeUpdate();
            conn.commit();

            return true;
        }
    }
}
