/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Webservice.bananastore;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author GF63-9RCX-622ID
 */
@Entity
@Table(name = "pisangstore")
@NamedQueries({
    @NamedQuery(name = "Pisangstore.findAll", query = "SELECT p FROM Pisangstore p"),
    @NamedQuery(name = "Pisangstore.findById", query = "SELECT p FROM Pisangstore p WHERE p.id = :id"),
    @NamedQuery(name = "Pisangstore.findByNama", query = "SELECT p FROM Pisangstore p WHERE p.nama = :nama"),
    @NamedQuery(name = "Pisangstore.findByHarga", query = "SELECT p FROM Pisangstore p WHERE p.harga = :harga"),
    @NamedQuery(name = "Pisangstore.findByJumlahbarang", query = "SELECT p FROM Pisangstore p WHERE p.jumlahbarang = :jumlahbarang")})
public class Pisangstore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Nama")
    private String nama;
    @Column(name = "Harga")
    private Integer harga;
    @Column(name = "Jumlah_barang")
    private Integer jumlahbarang;

    public Pisangstore() {
    }

    public Pisangstore(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getJumlahbarang() {
        return jumlahbarang;
    }

    public void setJumlahbarang(Integer jumlahbarang) {
        this.jumlahbarang = jumlahbarang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pisangstore)) {
            return false;
        }
        Pisangstore other = (Pisangstore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Webservice.bananastore.Pisangstore[ id=" + id + " ]";
    }
    
}
