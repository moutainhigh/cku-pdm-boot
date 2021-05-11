package com.ckunion.modules.base.typicalProc.service.impl;

import com.ckunion.modules.base.typicalProc.entity.BasTypicalImg;
import com.ckunion.modules.base.typicalProc.mapper.BasTypicalImgMapper;
import com.ckunion.modules.base.typicalProc.service.IBasTypicalImgService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: bas_typical_img
 * @Author: jeecg-boot
 * @Date:   2021-04-12
 * @Version: V1.0
 */
@Service
public class BasTypicalImgServiceImpl extends ServiceImpl<BasTypicalImgMapper, BasTypicalImg> implements IBasTypicalImgService {
    @Autowired
    private BasTypicalImgMapper basTypicalImgMapper;

    @Override
    public List<BasTypicalImg> selectByMainId(String mainId) {
        return basTypicalImgMapper.selectByMainId(mainId);
    }
}
