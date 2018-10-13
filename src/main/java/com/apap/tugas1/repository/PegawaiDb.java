package com.apap.tugas1.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long>{
	List<PegawaiModel> findByNip(String nip);
	List<PegawaiModel> findByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir, String tahunMasuk);
	List<PegawaiModel> findByInstansi(InstansiModel instansi);
	List<PegawaiModel> findByJabatan(JabatanModel jabatan);
	List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansi);
	String deleteByNip(String nip);
}
