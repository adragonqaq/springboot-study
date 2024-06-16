package com.lzl.tkmybatis.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
@Data
public class MymallRole extends BaseTkEntity {

    private int id;

    private String name;

    private String desc;

    private String enabled;


}
