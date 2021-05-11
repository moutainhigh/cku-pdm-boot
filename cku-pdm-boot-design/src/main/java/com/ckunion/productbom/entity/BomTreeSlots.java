package com.ckunion.productbom.entity;

import com.ckunion.constant.ProConstant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


@Data
public class BomTreeSlots {

    //图标
    public String icon;

    public BomTreeSlots(String seriesId, String partType){
        if(StringUtils.isNotEmpty(seriesId)){
            this.icon="bars";
        }else {

            if (ProConstant.BOM_PART_TYPE_PART.equals(partType)) {
                this.icon = "setting";
            } else if (ProConstant.BOM_PART_TYPE_MAT.equals(partType)) {
                this.icon = "api";
            } else if (ProConstant.BOM_PART_TYPE_DOC.equals(partType)) {
                this.icon = "file-text";
            } else {
                this.icon = "";
            }
        }

    }

}
