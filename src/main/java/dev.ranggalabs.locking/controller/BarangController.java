package dev.ranggalabs.locking.controller;

import dev.ranggalabs.locking.dto.BarangRequestDto;
import dev.ranggalabs.locking.model.ResponseModel;
import dev.ranggalabs.locking.service.Barang2Service;
import dev.ranggalabs.locking.service.BarangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by erlangga on 09/12/18.
 */
@Controller
@RequestMapping("/api/barang")
public class BarangController {

    @Autowired
    private BarangService barangService;
    @Autowired
    private Barang2Service barang2Service;

    @RequestMapping(method = RequestMethod.POST, value = "/post1")
    @ResponseBody
    public ResponseModel post1(@RequestBody BarangRequestDto barangRequestDto) {
        long startTime = System.currentTimeMillis();
        ResponseModel responseModel = barangService.save(barangRequestDto.getName());
        long endTime = System.currentTimeMillis();
        System.out.println("Response Time "+(endTime - startTime) +" ms");
        return responseModel;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post2")
    @ResponseBody
    public ResponseModel post2(@RequestBody BarangRequestDto barangRequestDto) {
        long startTime = System.currentTimeMillis();
        ResponseModel responseModel = barang2Service.save(barangRequestDto.getName());
        long endTime = System.currentTimeMillis();
        System.out.println("Response Time "+(endTime - startTime) +" ms");
        return responseModel;
    }

}
