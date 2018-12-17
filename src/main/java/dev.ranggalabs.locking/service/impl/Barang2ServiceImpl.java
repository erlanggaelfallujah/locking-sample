package dev.ranggalabs.locking.service.impl;

import dev.ranggalabs.locking.model.ResponseModel;
import dev.ranggalabs.locking.repository.Barang2Repository;
import dev.ranggalabs.locking.service.Barang2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by erlangga on 09/12/18.
 */
@Service
public class Barang2ServiceImpl implements Barang2Service {

    @Autowired
    private Barang2Repository barang2Repository;

    @Override
    public ResponseModel save(String name) {
        Integer isEligible = barang2Repository.isEligible(name);
        if(isEligible==null){
            if(barang2Repository.saveBarangAndTemp(name)){
                return new ResponseModel("00");
            }
            return new ResponseModel("A2");
        }else if(isEligible==1){
            if(barang2Repository.saveTempBarang(name)){
                return new ResponseModel("00");
            }
            return new ResponseModel("A2");
        }
        return new ResponseModel("A1");
    }
}
