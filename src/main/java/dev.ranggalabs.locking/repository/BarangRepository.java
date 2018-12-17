package dev.ranggalabs.locking.repository;

/**
 * Created by erlangga on 09/12/18.
 */
public interface BarangRepository {
    boolean save(String name, long value);
}
