package com.apollo.uploadapps.credit.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "Mst_Credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "AsalRek")
    private String AsalRek;

    @Column(name = "NamaAsalRek")
    private String namaAsalRek;

    @Column(name = "RekTujuan")
    private String rekTujuan;

    @Column(name = "NamaRekTujuan")
    private String namaRekTujuan;

    @Column(name = "Jumlah")
    private int jumlah;

    @Column(name = "Keterangan")
    private String keterangan;

    @Column(name = "Status")
    private String status;


    @Column(name = "Alasan")
    private String alasan;

    @Column(name = "TglEfektif")
    private String tglEfektif;

    @Column(name ="CreatedDate" , nullable = false)
    private Date createdDate = new Date();

    @Column(name = "CreatedBy", nullable = false)
    private Integer createdBy = 1;

    @Column(name = "ModifiedDate")
    private Date modifiedDate;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "IsDelete", nullable = false)
    private Byte isDelete = 1;

}
