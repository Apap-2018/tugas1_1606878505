package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.ProvinsiDb;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	@Autowired
	private ProvinsiDb provinsiDb;
	
	@Override
	public void addProvinsi(ProvinsiModel provinsi) {
		provinsiDb.save(provinsi);
	}
	
	@Override
	public Optional<ProvinsiModel> getProvinsiById(Long id) {
		return provinsiDb.findById(id);
	}
	
	@Override
	public List<ProvinsiModel> getAllProvinsi() {
		return provinsiDb.findAll();
	}
	
	@Override
	public ProvinsiModel getProvinsiByName(String nama) {
		return provinsiDb.findByNama(nama).get(0);
	}

}
