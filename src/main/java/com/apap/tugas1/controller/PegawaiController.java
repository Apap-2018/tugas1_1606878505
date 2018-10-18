package com.apap.tugas1.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("headerTitle", "Home");
		return "index";
	}
	
	@RequestMapping("/pegawai")
	public String viewPegawai (@RequestParam String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
		model.addAttribute("pegawai", pegawai);
		
		InstansiModel instansiPegawai = pegawai.getInstansi();
		String namaInstansiPegawai = instansiPegawai.getNama() + " - " + instansiPegawai.getProvinsi().getNama();
		model.addAttribute("namaInstansiPegawai", namaInstansiPegawai);
		
		List<JabatanModel> setJabatan = pegawai.getJabatan();
		model.addAttribute("setJabatan", setJabatan);
		
		String gaji = "Rp" + Integer.toString(pegawai.getGaji());
		model.addAttribute("gaji", gaji);
		model.addAttribute("headerTitle", "Data Pegawai");
		return "viewPegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	public String tambahPegawai (Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setJabatan(new ArrayList<JabatanModel>());
		pegawai.getJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		model.addAttribute("tanggalLahir", dateFormat.format(date));
		model.addAttribute("headerTitle", "Tambah Pegawai");
		return "tambahPegawai";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, false));
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"addJabatan"})
	private String addRow(@ModelAttribute PegawaiModel pegawai, Model model) {
		model.addAttribute("headerTitle", "Tambah Pegawai");
		pegawai.getJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		return "tambahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"deleteJabatan"})
	private String deleteRow(@ModelAttribute PegawaiModel pegawai, Model model, HttpServletRequest req) {
		model.addAttribute("headerTitle", "Tambah Pegawai");
		Integer rowId =  Integer.valueOf(req.getParameter("deleteJabatan"));
		pegawai.getJabatan().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		return "tambahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	public String tambahPegawai (@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.addPegawai(pegawai);
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("headerTitle", "Tambah Pegawai Sukses");
		return "tambahPegawaiSukses";
		}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	public String cariPegawai (@RequestParam(value="provinsiId", required = false) Optional<Long> provinsiId, 
								@RequestParam(value="instansiId", required = false) Optional<Long> instansiId, 
								@RequestParam(value="jabatanId", required = false) Optional<Long> jabatanId, Model model) {
		
		ProvinsiModel provinsi = null;
		InstansiModel instansi = null;
		JabatanModel jabatan = null;
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<PegawaiModel> hasilPencarian = null;
		if (provinsiId.isPresent()) {
			provinsi = provinsiService.getProvinsiById(provinsiId.get()).get();
			if (instansiId.isPresent()) {
				instansi = instansiService.getInstansiById(instansiId.get()).get();	
				if (jabatanId.isPresent()) {
					jabatan = jabatanService.getJabatanById(jabatanId.get()).get();	
					hasilPencarian = pegawaiService.getPegawaiByInstansiAndJabatan(instansi, jabatan);
				}
				else {
					hasilPencarian = pegawaiService.getPegawaiByInstansi(instansi);
				}
			}
			else if (jabatanId.isPresent()) {
				jabatan = jabatanService.getJabatanById(jabatanId.get()).get();	
				hasilPencarian = pegawaiService.getPegawaiByProvinsiAndJabatan(provinsiId.get(), jabatan);
			}
			else {
				hasilPencarian = pegawaiService.getPegawaiByProvinsi(provinsiId.get());
			}
		}
		else {
			if (jabatanId.isPresent()) {
				jabatan = jabatanService.getJabatanById(jabatanId.get()).get();	
				hasilPencarian = pegawaiService.getPegawaiByJabatan(jabatan);
			}
		}
		model.addAttribute("provinsi", provinsi);
		model.addAttribute("instansi", instansi);
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("hasilPencarian", hasilPencarian);
		model.addAttribute("headerTitle", "Cari Pegawai");
		return "cariPegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	public String tambahPegawai (@RequestParam String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
		model.addAttribute("pegawai", pegawai);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("headerTitle", "Ubah Pegawai");
		return "ubahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST, params={"addJabatan"})
	private String addRowUbah(@ModelAttribute PegawaiModel pegawai, Model model) {
		model.addAttribute("headerTitle", "Tambah Pegawai");
		pegawai.getJabatan().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		return "ubahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST, params={"deleteJabatan"})
	private String deleteRowUbah(@ModelAttribute PegawaiModel pegawai, Model model, HttpServletRequest req) {
		model.addAttribute("headerTitle", "Tambah Pegawai");
		Integer rowId =  Integer.valueOf(req.getParameter("deleteJabatan"));
		pegawai.getJabatan().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tanggalLahir = simpleDateFormat.format(pegawai.getTanggalLahir());
		model.addAttribute("tanggalLahir", tanggalLahir);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiFromProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", new HashSet(listInstansi));
		
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", new HashSet(listJabatan));
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		return "ubahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	public String ubahPegawai (@ModelAttribute PegawaiModel pegawai, Model model) {
		String oldNip = pegawai.getNip();
		pegawaiService.update(oldNip, pegawai);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("headerTitle", "Tambah Pegawai Sukses");
		return "ubahPegawaiSukses";
		}

	@RequestMapping("/pegawai/termuda-tertua")
	public String viewPegawai (@RequestParam Long idInstansi, Model model) {
		List<PegawaiModel> listPegawai = pegawaiService.getTuaMudaInstansi(instansiService.getInstansiById(idInstansi).get());
		model.addAttribute("pegawaiTertua", listPegawai.get(0));
		model.addAttribute("pegawaiTermuda", listPegawai.get(listPegawai.size()-1));
		return "viewPegawaiTuaMuda";
	}
}
