package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService{
	@Autowired
	private InstansiDb instansiDb;
	
	@Override
	public void addInstansi(InstansiModel instansi) {
		instansiDb.save(instansi);
	}
	
	@Override
	public Optional<InstansiModel> getInstansiById(Long id) {
		return instansiDb.findById(id);
	}
	
	@Override
	public List<InstansiModel> getAllInstansi() {
		return instansiDb.findAll();
	}
	
	@Override
	public List<InstansiModel> getInstansiFromProvinsi(ProvinsiModel provinsi) {
		return instansiDb.findByProvinsi(provinsi);
	}
}
