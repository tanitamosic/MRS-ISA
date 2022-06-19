package com.Projekat.model;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @SequenceGenerator(name = "photoIdSeqGen", sequenceName = "photoIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photoIdSeqGen")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "asset_path")
    private String assetPath;

    public Photo() {}

    public Photo(int id, String assetPath) {
        this.id = id;
        this.assetPath = assetPath;
    }

    public int getId() {
        return id;
    }

    public String getAssetPath() {
        return assetPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAssetPath(String assetPath) {
        this.assetPath = assetPath;
    }
}
