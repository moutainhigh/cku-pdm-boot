package com.ckunion.modules.series.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ckunion.modules.materials.entity.BasMaterialInfoColumn;
import com.ckunion.modules.materials.service.IBasMaterialInfoColumnService;
import com.ckunion.modules.series.entity.ProdSeries;
import com.ckunion.modules.series.entity.ProdSeriesField;
import com.ckunion.modules.series.entity.ProdSeriesMapping;
import com.ckunion.modules.series.entity.VwProductSeriesMapping;
import com.ckunion.modules.series.service.IProdSeriesFieldService;
import com.ckunion.modules.series.service.IProdSeriesMappingService;
import com.ckunion.modules.series.service.IProdSeriesService;
import com.ckunion.modules.series.service.IVwProdSeriesMappingService;
import com.ckunion.modules.series.vo.ProdSeriesMappingVo;
import com.ckunion.modules.series.vo.ProdSeriesPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
* @Description: prod_series
* @Author: jeecg-boot
* @Date:   2021-04-06
* @Version: V1.0
*/
@Api(tags="prod_series")
@RestController
@RequestMapping("/series/prodSeriesMapping")
@Slf4j
public class ProdSeriesMappingController {
    @Autowired
    private IProdSeriesMappingService prodSeriesMappingService;
    @Autowired
    private IVwProdSeriesMappingService vwProdSeriesMappingService;


    @ApiOperation("查询配置字段")
    @PostMapping(value = "/querySeriesColumn")
    public Result<List<VwProductSeriesMapping>> querySeriesColumn(HttpServletRequest request, @NotNull @RequestParam("type_model_id") String typeModelId){
        List<VwProductSeriesMapping> vwProductSeriesMappings = prodSeriesMappingService.querySeriesColumn(typeModelId);
        return  Result.OK(vwProductSeriesMappings);
    }


    @ApiOperation("保存型号配置系列")
    @PostMapping(value = "/saveSeriesColumn")
    public Result saveSeriesColumn(HttpServletRequest request, @RequestBody ProdSeriesMappingVo prodSeriesMappingVo){
        prodSeriesMappingService.saveSeriesColumn(prodSeriesMappingVo);
        return Result.OK();
    }


    @ApiOperation("获取对应产品型号的指标")
    @PostMapping(value = "/getSeriesMapping")
    public Result getSeriesMapping(HttpServletRequest request,  @NotNull @RequestParam("type_model_id") String typeModelId){

        List<VwProductSeriesMapping> list = vwProdSeriesMappingService.query().eq("type_Model_Id", typeModelId).list();

        return Result.OK(list);
    }


}
