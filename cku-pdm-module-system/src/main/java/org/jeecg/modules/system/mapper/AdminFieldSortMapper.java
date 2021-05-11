package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.AdminFieldSort;
import org.jeecg.modules.system.entity.AdminFieldSortVO;

import java.util.List;

/**
 * @Description: admin_field_sort
 * @Author: jeecg-boot
 * @Date:   2020-10-10
 * @Version: V1.0
 */
public interface AdminFieldSortMapper extends BaseMapper<AdminFieldSort> {

    public List<AdminFieldSortVO> queryListHead(@Param("menu_id") String menu_id, @Param("user_id") String user_id);

    public List<AdminFieldSortVO> queryAllListHead();

}
