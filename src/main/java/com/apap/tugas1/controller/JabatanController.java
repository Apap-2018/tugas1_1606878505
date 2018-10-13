package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class JabatanController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	public String viewJabatan (@RequestParam(value = "idJabatan", required = true) Long idJabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(idJabatan).get();
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("headerTitle", "Lihat Jabatan");
		return "viewJabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	public String tambahJabatan (Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		model.addAttribute("headerTitle", "Tambah Jabatan");
		return "tambahJabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	public String tambahJabatan (Model model, @ModelAttribute JabatanModel jabatan) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("headerTitle", "Tambah Jabatan Sukses!!");
		return "tambahJabatanSukses";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	public String ubahJabatan (@RequestParam(value = "idJabatan", required = true) Long idJabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(idJabatan).get();
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("headerTitle", "Ubah Jabatan");
		return "ubahJabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	public String ubahJabatan (Model model, @ModelAttribute JabatanModel jabatan) {
		jabatanService.update(jabatan.getId(), jabatan);
		model.addAttribute("headerTitle", "Ubah Jabatan Sukses!!");
		return "ubahJabatanSukses";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	public String hapusJabatan (Model model, @RequestParam(value = "idJabatan", required = true) Long idJabatan) {
		try {
			jabatanService.deleteById(idJabatan);
			model.addAttribute("headerTitle", "Hapus Jabatan Sukses!!");
			return "deleteJabatanSukses";
		}
		catch (Exception ex) {
			return "deleteJabatanGagal";
		}
		
	}
	
	@RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	public String ubahJabatan (Model model) {
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("headerTitle", "Viewall Jabatan");
		return "viewallJabatan";
	}
}
