package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	@Autowired
	private JabatanDb jabatanDb;
	
	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
	}
	
	@Override
	public Optional<JabatanModel> getJabatanById(Long id) {
		return jabatanDb.findById(id);
	}
	
	@Override
	public List<JabatanModel> getAllJabatan() {
		return jabatanDb.findAll();
	}
	
	@Override
	public void update(Long jabatanId, JabatanModel newJabatan) {
		JabatanModel oldJabatan = jabatanDb.findById(jabatanId).get();
		oldJabatan.setNama(newJabatan.getNama());
		oldJabatan.setDeskripsi(newJabatan.getDeskripsi());
		oldJabatan.setGajiPokok(newJabatan.getGajiPokok());
	}
	
	@Override
	public void deleteById(Long jabatanId) {
		jabatanDb.deleteById(jabatanId);
	}

}
