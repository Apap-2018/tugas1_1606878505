package com.apap.tugas1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="jabatan")
public class JabatanModel implements Serializable, Comparable<JabatanModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;

	@NotNull
	@Column(name = "gaji_pokok", nullable = false)
	private Double gajiPokok;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Double getGajiPokok() {
		return gajiPokok;
	}

	public void setGajiPokok(Double gajiPokok) {
		this.gajiPokok = gajiPokok;
	}
	
	@Override
	public int compareTo(JabatanModel other) {
	    if (this.id < other.getId()) {
	      return -1;
	    } else if (this.id > other.getId()) {
	      return 1;
	    } else {
	      return 0;
	    }
	 }
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JabatanModel)) return false;

        JabatanModel jabatan = (JabatanModel) o;

        if (this.id != jabatan.getId()) return false;

        return true;
    }
}
