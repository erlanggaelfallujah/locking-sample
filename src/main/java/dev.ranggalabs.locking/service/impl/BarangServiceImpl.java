package dev.ranggalabs.locking.service.impl;

import dev.ranggalabs.locking.model.ResponseModel;
import dev.ranggalabs.locking.repository.BarangRepository;
import dev.ranggalabs.locking.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by erlangga on 09/12/18.
 */
@Service
public class BarangServiceImpl implements BarangService {

    @Autowired
    private BarangRepository barangRepository;

    @Override
    public ResponseModel save(String name) {
        if(barangRepository.save(name,1L)){
            return new ResponseModel("00");
        }
        return new ResponseModel("A1");
    }
}
