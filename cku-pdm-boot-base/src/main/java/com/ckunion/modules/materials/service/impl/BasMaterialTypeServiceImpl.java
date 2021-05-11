package com.ckunion.modules.materials.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ckunion.modules.materials.entity.BasMaterialType;
import com.ckunion.modules.materials.mapper.BasMaterialTypeMapper;
import com.ckunion.modules.materials.service.IBasMaterialTypeService;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: bas_material_type
 * @Author: jeecg-boot
 * @Date:   2020-10-15
 * @Version: V1.0
 */
@Service
public class BasMaterialTypeServiceImpl extends ServiceImpl<BasMaterialTypeMapper, BasMaterialType> implements IBasMaterialTypeService {

	@Override
	public void addBasMaterialType(BasMaterialType basMaterialType) {
		if(oConvertUtils.isEmpty(basMaterialType.getPid())){
			basMaterialType.setPid(IBasMaterialTypeService.ROOT_PID_VALUE);
		}else{
			//如果当前节点父ID不为空 则设置父节点的hasChildren 为1
			BasMaterialType parent = baseMapper.selectById(basMaterialType.getPid());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.insert(basMaterialType);
	}
	
	@Override
	public void updateBasMaterialType(BasMaterialType basMaterialType) {
		BasMaterialType entity = this.getById(basMaterialType.getId());
		if(entity==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		String old_pid = entity.getPid();
		String new_pid = basMaterialType.getPid();
		if(!old_pid.equals(new_pid)) {
			updateOldParentNode(old_pid);
			if(oConvertUtils.isEmpty(new_pid)){
				basMaterialType.setPid(IBasMaterialTypeService.ROOT_PID_VALUE);
			}
			if(!IBasMaterialTypeService.ROOT_PID_VALUE.equals(basMaterialType.getPid())) {
				baseMapper.updateTreeNodeStatus(basMaterialType.getPid(), IBasMaterialTypeService.HASCHILD);
			}
		}
		baseMapper.updateById(basMaterialType);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBasMaterialType(String id) throws JeecgBootException {
		//查询选中节点下所有子节点一并删除
        id = this.queryTreeChildIds(id);
        if(id.indexOf(",")>0) {
            StringBuffer sb = new StringBuffer();
            String[] idArr = id.split(",");
            for (String idVal : idArr) {
                if(idVal != null){
                    BasMaterialType basMaterialType = this.getById(idVal);
                    String pidVal = basMaterialType.getPid();
                    //查询此节点上一级是否还有其他子节点
                    List<BasMaterialType> dataList = baseMapper.selectList(new QueryWrapper<BasMaterialType>().eq("pid", pidVal).notIn("id",Arrays.asList(idArr)));
                    if((dataList == null || dataList.size()==0) && !Arrays.asList(idArr).contains(pidVal)
                            && !sb.toString().contains(pidVal)){
                        //如果当前节点原本有子节点 现在木有了，更新状态
                        sb.append(pidVal).append(",");
                    }
                }
            }
            //批量删除节点
            baseMapper.deleteBatchIds(Arrays.asList(idArr));
            //修改已无子节点的标识
            String[] pidArr = sb.toString().split(",");
            for(String pid : pidArr){
                this.updateOldParentNode(pid);
            }
        }else{
            BasMaterialType basMaterialType = this.getById(id);
            if(basMaterialType==null) {
                throw new JeecgBootException("未找到对应实体");
            }
            updateOldParentNode(basMaterialType.getPid());
            baseMapper.deleteById(id);
        }
	}
	
	@Override
    public List<BasMaterialType> queryTreeListNoPage(QueryWrapper<BasMaterialType> queryWrapper) {
        List<BasMaterialType> dataList = baseMapper.selectList(queryWrapper);
        List<BasMaterialType> mapList = new ArrayList<>();
        for(BasMaterialType data : dataList){
            String pidVal = data.getPid();
            //递归查询子节点的根节点
            if(pidVal != null && !"0".equals(pidVal)){
                BasMaterialType rootVal = this.getTreeRoot(pidVal);
                if(rootVal != null && !mapList.contains(rootVal)){
                    mapList.add(rootVal);
                }
            }else{
                if(!mapList.contains(data)){
                    mapList.add(data);
                }
            }
        }
        return mapList;
    }
	
	/**
	 * 根据所传pid查询旧的父级节点的子节点并修改相应状态值
	 * @param pid
	 */
	private void updateOldParentNode(String pid) {
		if(!IBasMaterialTypeService.ROOT_PID_VALUE.equals(pid)) {
			Integer count = baseMapper.selectCount(new QueryWrapper<BasMaterialType>().eq("pid", pid));
			if(count==null || count<=1) {
				baseMapper.updateTreeNodeStatus(pid, IBasMaterialTypeService.NOCHILD);
			}
		}
	}

	/**
     * 递归查询节点的根节点
     * @param pidVal
     * @return
     */
    private BasMaterialType getTreeRoot(String pidVal){
        BasMaterialType data =  baseMapper.selectById(pidVal);
        if(data != null && !"0".equals(data.getPid())){
            return this.getTreeRoot(data.getPid());
        }else{
            return data;
        }
    }

    /**
     * 根据id查询所有子节点id
     * @param ids
     * @return
     */
    private String queryTreeChildIds(String ids) {
        //获取id数组
        String[] idArr = ids.split(",");
        StringBuffer sb = new StringBuffer();
        for (String pidVal : idArr) {
            if(pidVal != null){
                if(!sb.toString().contains(pidVal)){
                    if(sb.toString().length() > 0){
                        sb.append(",");
                    }
                    sb.append(pidVal);
                    this.getTreeChildIds(pidVal,sb);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 递归查询所有子节点
     * @param pidVal
     * @param sb
     * @return
     */
    private StringBuffer getTreeChildIds(String pidVal,StringBuffer sb){
        List<BasMaterialType> dataList = baseMapper.selectList(new QueryWrapper<BasMaterialType>().eq("pid", pidVal));
        if(dataList != null && dataList.size()>0){
            for(BasMaterialType tree : dataList) {
                if(!sb.toString().contains(tree.getId())){
                    sb.append(",").append(tree.getId());
                }
                this.getTreeChildIds(tree.getId(),sb);
            }
        }
        return sb;
    }



    /**
     * 获取表格list 但是需要增加附加值
     * @param pid
     */
    private void ExtPage(String pid) {

    }


    /**
     * 查询兄弟节点信息
     * @return
     */
    public Map queryBroConfig(String pid){

        Map data = baseMapper.queryBroConfig(pid);
        return data;
    }


    @Override
    public List<String> getAllFloorId(String id) {
        BasMaterialType type = this.getById(id);
        List<BasMaterialType> list = this.query().likeRight("node_id", type.getNodeId()).isNull("has_child").list();
        ArrayList<String> ids = new ArrayList<>();
        String idStr = "";
        for (BasMaterialType basMaterialType : list) {
            idStr += "'"+basMaterialType.getId()+"',";
            ids.add(basMaterialType.getId());
        }
        return ids;
    }

}
