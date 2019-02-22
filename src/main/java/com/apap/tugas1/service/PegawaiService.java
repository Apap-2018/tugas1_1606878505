package com.apap.tugas1.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	void addPegawai(PegawaiModel pegawai);
	
	PegawaiModel getPegawaiByNip(String nip);

	List<PegawaiModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir,
			String tahunMasuk);

	List<PegawaiModel> getPegawaiByProvinsi(Long provinsiId);

	List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);

	List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan);

	void deleteByNip(String nip);

	void update(String nip, PegawaiModel newPegawai);

	List<PegawaiModel> getPegawaiByProvinsiAndJabatan(Long provinsiId, JabatanModel jabatan);

	List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan);

	List<PegawaiModel> getTuaMudaInstansi(InstansiModel instansi);

	List<PegawaiModel> getPegawaiByProvinsiAndTahunMasuk(long provinsiId, String tahunMasuk);
}
