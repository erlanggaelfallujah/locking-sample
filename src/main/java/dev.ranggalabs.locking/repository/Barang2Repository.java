package dev.ranggalabs.locking.repository;

/**
 * Created by erlangga on 09/12/18.
 */
public interface Barang2Repository {
    Integer isEligible(String name);
    boolean saveBarang2(String name);
    boolean saveTempBarang(String name);
    boolean saveBarangAndTemp(String name);
}
