package com.joyzone.platform.module.admin.controller;

import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.ShopCouponModel;
import com.joyzone.platform.core.service.ShopCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/shiopCoupon")
@Api(value="后台店家体验券管理V",description="后台体验券管理",tags = "1.0")
public class ShopCouponController {

    @Autowired
    private ShopCouponService shopCouponService;

    /**
     * Mr.Gx
     */
    @PostMapping("saveShopCoupon")
    @ApiOperation("添加店家体验券信息")
    public R saveShopCoupon(ShopCouponModel shopCouponModel){
        if(shopCouponModel == null)
            return R.error(R.STATUS_FAIL,"参数不能为空.");
        if(StringUtils.isBlank(shopCouponModel.getName()))
            return R.error(R.STATUS_FAIL,"体验券名称不能为空.");
        if(StringUtils.isBlank(shopCouponModel.getContent()))
            return R.error(R.STATUS_FAIL,"体验券内容不能为空.");
        if(shopCouponModel.getNumber() == null)
            return R.error(R.STATUS_FAIL,"参加人数不能为空.");
        if(shopCouponModel.getNumber().intValue() < 1)
            return R.error(R.STATUS_FAIL,"参加人数不能低于一个人.");
        if(shopCouponModel.getPrice() == null)
            return R.error(R.STATUS_FAIL,"体验券价格不能为空.");
//        if(shopCouponModel.getStartTime() == null)
//            return R.error(R.STATUS_FAIL,"体验券开始时间不能为空.");
//        if(shopCouponModel.getEndTime() == null)
//            return R.error(R.STATUS_FAIL,"体验券结束时间不能为空.");

        Date date = new Date();
        shopCouponModel.setStatus(ShopCouponModel.CONPON_SUCCESS);
        shopCouponModel.setCreateTime(date);
        shopCouponModel.setStartTime(date);
        shopCouponModel.setEndTime(date);
        return shopCouponService.save(shopCouponModel) > 0 ? R.ok("添加成功.")
                : R.error(R.STATUS_FAIL,"添加失败.") ;
    }

    /**
     * Mr.Gx
     */
    @GetMapping("getShopCouponList")
    @ApiOperation("体验券清单")
    public R getShopCouponList(ShopCouponModel shopCouponModel){
        if(shopCouponModel == null)
            return R.error(R.STATUS_FAIL,"页数与条数不能为空.");
        if(shopCouponModel.getPageNum() == null)
            shopCouponModel.setPageNum(BaseModel.PAGE_NUM);
        if(shopCouponModel.getPageSize() == null)
            shopCouponModel.setPageSize(BaseModel.PAGE_SIZE);
        List<ShopCouponModel> list = shopCouponService.getShopCouponList(shopCouponModel);
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,new ArrayList<>());
    }

    /**
     * Mr.Gx
     */
    @GetMapping("selectById/{id}")
    @ApiOperation("体验券详情")
    public R selectById(@PathVariable Long id){
        if(id == null)
            return  R.error(R.STATUS_FAIL,"体验券ID不能为空");
        ShopCouponModel shopCouponModel = shopCouponService.selectByKey(id);
        return R.ok(shopCouponModel != null ? shopCouponModel : new ShopCouponModel());
    }

    /**
     * Mr.Gx
     */
    @PostMapping("updateCouponStatus")
    @ApiOperation("体验券更新状态")
    public R updateShopCouponStatus(Long id,Integer status){
        if(id == null)
            return R.error(R.STATUS_FAIL,"体验券ID不能为空");
        if(status == null)
            return R.error(R.STATUS_FAIL,"状态不能为空");
        return shopCouponService.updateShopCouponStatus(id,status);
    }

}
