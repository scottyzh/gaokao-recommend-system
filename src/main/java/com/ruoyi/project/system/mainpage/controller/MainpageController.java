package com.ruoyi.project.system.mainpage.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.mainpage.domain.Mainpage;
import com.ruoyi.project.system.mainpage.service.IMainpageService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 首页Controller
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
@Controller
@RequestMapping("/system/mainpage")
public class MainpageController extends BaseController
{
    private String prefix = "system/mainpage";

    @Autowired
    private IMainpageService mainpageService;

    @RequiresPermissions("system:mainpage:view")
    @GetMapping()
    public String mainpage()
    {
        return prefix + "/mainpage";
    }

    /**
     * 查询首页列表
     */
    @RequiresPermissions("system:mainpage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Mainpage mainpage)
    {
        startPage();
        List<Mainpage> list = mainpageService.selectMainpageList(mainpage);
        return getDataTable(list);
    }

    /**
     * 导出首页列表
     */
    @RequiresPermissions("system:mainpage:export")
    @Log(title = "首页", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Mainpage mainpage)
    {
        List<Mainpage> list = mainpageService.selectMainpageList(mainpage);
        ExcelUtil<Mainpage> util = new ExcelUtil<Mainpage>(Mainpage.class);
        return util.exportExcel(list, "首页数据");
    }

    /**
     * 新增首页
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存首页
     */
    @RequiresPermissions("system:mainpage:add")
    @Log(title = "首页", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Mainpage mainpage)
    {
        return toAjax(mainpageService.insertMainpage(mainpage));
    }

    /**
     * 修改首页
     */
    @RequiresPermissions("system:mainpage:edit")
    @GetMapping("/edit/{imageId}")
    public String edit(@PathVariable("imageId") Integer imageId, ModelMap mmap)
    {
        Mainpage mainpage = mainpageService.selectMainpageByImageId(imageId);
        mmap.put("mainpage", mainpage);
        return prefix + "/edit";
    }

    /**
     * 修改保存首页
     */
    @RequiresPermissions("system:mainpage:edit")
    @Log(title = "首页", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Mainpage mainpage)
    {
        return toAjax(mainpageService.updateMainpage(mainpage));
    }

    /**
     * 删除首页
     */
    @RequiresPermissions("system:mainpage:remove")
    @Log(title = "首页", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(mainpageService.deleteMainpageByImageIds(ids));
    }
}
