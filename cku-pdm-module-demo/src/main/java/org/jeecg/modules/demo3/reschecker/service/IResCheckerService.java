package org.jeecg.modules.demo3.reschecker.service;

import org.jeecg.modules.demo3.reschecker.entity.ResChecker;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 检验员表
 * @Author: 唐骁
 * @Date:   2021-05-10
 * @Version: V1.0
 */
public interface IResCheckerService extends IService<ResChecker> {
    /**
     * 保存检验员
     * @param resChecker
     */
    void saveResChecker(ResChecker resChecker);
}
