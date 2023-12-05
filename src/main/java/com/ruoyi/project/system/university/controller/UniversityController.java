package com.ruoyi.project.system.university.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.university.domain.University;
import com.ruoyi.project.system.university.service.IUniversityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 院校查询Controller
 * 
 * @author ruoyi
 * @date 2022-03-27
 */
@Controller
@RequestMapping("/system/university")
public class UniversityController extends BaseController
{
    private String prefix = "system/university";

    @Autowired
    private IUniversityService universityService;

    @RequiresPermissions("system:university:view")
    @GetMapping()
    public String university()
    {
        return prefix + "/university";
    }

    /**
     * 查询院校查询列表
     */
    @RequiresPermissions("system:university:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(University university)
    {
        startPage();
        List<University> list = universityService.selectUniversityList(university);
        return getDataTable(list);
    }

    /**
     * 导出院校查询列表
     */
    @RequiresPermissions("system:university:export")
    @Log(title = "院校查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(University university)
    {
        List<University> list = universityService.selectUniversityList(university);
        ExcelUtil<University> util = new ExcelUtil<University>(University.class);
        return util.exportExcel(list, "院校查询数据");
    }

    /**
     * 新增院校查询
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存院校查询
     */
    @RequiresPermissions("system:university:add")
    @Log(title = "院校查询", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(University university)
    {
        return toAjax(universityService.insertUniversity(university));
    }

    /**
     * 修改院校查询
     */
    @RequiresPermissions("system:university:edit")
    @GetMapping("/edit/{uniName}")
    public String edit(@PathVariable("uniName") String uniName, ModelMap mmap)
    {
        University university = universityService.selectUniversityByUniName(uniName);
        mmap.put("university", university);
        return prefix + "/edit";
    }

    /**
     * 修改保存院校查询
     */
    @RequiresPermissions("system:university:edit")
    @Log(title = "院校查询", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(University university)
    {
        return toAjax(universityService.updateUniversity(university));
    }

    /**
     * 删除院校查询
     */
    @RequiresPermissions("system:university:remove")
    @Log(title = "院校查询", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(universityService.deleteUniversityByUniNames(ids));
    }
}
