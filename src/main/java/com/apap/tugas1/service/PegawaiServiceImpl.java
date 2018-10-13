package com.apap.tugas1.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDb.save(pegawai);
	}
	
	@Override
	public PegawaiModel getPegawaiByNip(String nip) {
		return pegawaiDb.findByNip(nip).get(0);
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir, String tahunMasuk){
		return pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasuk(instansi, tanggalLahir, tahunMasuk);		
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByProvinsi(Long provinsiId) {
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : pegawaiDb.findAll()) {
			if (pegawai.getInstansi().getProvinsi().getId() == provinsiId) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
		return pegawaiDb.findByInstansi(instansi);
	}
	
	@Override 
	public List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan) {
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : pegawaiDb.findByInstansi(instansi)) {
			if (pegawai.getJabatan().contains(jabatan)) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByProvinsiAndJabatan(Long provinsiId, JabatanModel jabatan){
		List<PegawaiModel> hasil = new ArrayList<>();
		
		for(PegawaiModel pegawai : this.getPegawaiByProvinsi(provinsiId)) {
			if (pegawai.getJabatan().contains(jabatan)) {
				hasil.add(pegawai);
			}
		}
		
		return hasil;
	}
	
	@Override
	public List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan){
		return pegawaiDb.findByJabatan(jabatan);
	}
	
	@Override
	public void deleteByNip(String nip) {
		pegawaiDb.deleteByNip(nip);
	}
	
	@Override
	public void update(String nip, PegawaiModel newPegawai) {
		PegawaiModel oldPegawai = pegawaiDb.findByNip(nip).get(0);
		oldPegawai.setNip(newPegawai.getNip());
		oldPegawai.setNama(newPegawai.getNama());
		oldPegawai.setTempatLahir(newPegawai.getTempatLahir());
		oldPegawai.setTanggalLahir(newPegawai.getTanggalLahir());
		oldPegawai.setTahunMasuk(newPegawai.getTahunMasuk());
		oldPegawai.setInstansi(newPegawai.getInstansi());
		oldPegawai.setJabatan(newPegawai.getJabatan());
	}
	
	@Override
	public List<PegawaiModel> getTuaMudaInstansi(InstansiModel instansi){
		return pegawaiDb.findByInstansiOrderByTanggalLahirAsc(instansi);
	}

}
