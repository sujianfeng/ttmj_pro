package com.len.kindle.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author sujianfeng
 * @date 2017/3/29
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
