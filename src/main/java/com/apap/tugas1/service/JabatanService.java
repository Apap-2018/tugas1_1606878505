package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
	
	void addJabatan (JabatanModel jabatan);
	
	Optional<JabatanModel> getJabatanById(Long id);
	
	List<JabatanModel> getAllJabatan();

	void update(Long jabatanId, JabatanModel newJabatan);

	void deleteById(Long jabatanId);

}
