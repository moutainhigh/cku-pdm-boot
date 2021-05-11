package com.ckunion.modules.series.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProdSeriesMappingVo {

    private List<String> uncheckIds;

    private List<String> checkIds;

    private String typeModelId;

}
