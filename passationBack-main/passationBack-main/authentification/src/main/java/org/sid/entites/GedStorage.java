package org.sid.entites;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "GedStorage")
public class GedStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rootPath;

    public GedStorage() {
    }

    public GedStorage(Integer id, String rootPath) {
        this.id = id;
        this.rootPath = rootPath;
    }

    public Integer getId() {
        return id;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GedStorage)) return false;
        GedStorage that = (GedStorage) o;
        return getId() == that.getId() && getRootPath().equals(that.getRootPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRootPath());
    }
}
