package com.ckunion.modules.base.entity;

import lombok.Data;

import java.util.List;

;

@Data
public class Tree {
    public String pid;
    public String key;
    public String code;
    public String title;
    public List<Tree> children;
}
