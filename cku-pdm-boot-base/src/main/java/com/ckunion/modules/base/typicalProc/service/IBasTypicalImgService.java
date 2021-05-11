package com.ckunion.modules.base.typicalProc.service;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: bas_typical_img
 * @Author: jeecg-boot
 * @Date:   2021-04-12
 * @Version: V1.0
 */
public interface IBasTypicalImgService extends IService<BasTypicalImg> {
    public List<BasTypicalImg> selectByMainId(String mainId);
}
