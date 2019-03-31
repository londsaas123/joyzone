package com.joyzone.platform.core.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = ShopDiscountModel.TABLE_NAME)
public class ShopDiscountModel extends BaseModel{

    protected static final String TABLE_NAME = "shop_discount";

    @Id
    private Long id;

    /**
     * 店家ID
     */
    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 店家名称
     */
    @Column(name = "shop_name")
    private String shopName;

    /**
     * 店家种类ID
     */
    @Column(name = "shop_type_id")
    private Long shopTypeId;

    /**
     * 店家种类名称
     */
    @Column(name = "shop_type_name")
    private String shopTypeName;

    /**
     * 人数限制
     */
    private Integer number;

    /**
     * 折扣  9折 = 0.9 
     */
    private Integer discount;

    /**
     * 状态：0 失效 1 正常
     */
    private Integer status;

    /**
     * 玩耍时间
     */
    @Column(name = "play_time")
    private Date playTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取店家ID
     *
     * @return shop_id - 店家ID
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * 设置店家ID
     *
     * @param shopId 店家ID
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取店家名称
     *
     * @return shop_name - 店家名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置店家名称
     *
     * @param shopName 店家名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取店家种类ID
     *
     * @return shop_type_id - 店家种类ID
     */
    public Long getShopTypeId() {
        return shopTypeId;
    }

    /**
     * 设置店家种类ID
     *
     * @param shopTypeId 店家种类ID
     */
    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    /**
     * 获取店家种类名称
     *
     * @return shop_type_name - 店家种类名称
     */
    public String getShopTypeName() {
        return shopTypeName;
    }

    /**
     * 设置店家种类名称
     *
     * @param shopTypeName 店家种类名称
     */
    public void setShopTypeName(String shopTypeName) {
        this.shopTypeName = shopTypeName;
    }

    /**
     * 获取折扣  9折 = 0.9 
     *
     * @return discount - 折扣  9折 = 0.9 
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * 设置折扣  9折 = 0.9 
     *
     * @param discount 折扣  9折 = 0.9 
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * 获取状态：0 失效 1 正常
     *
     * @return status - 状态：0 失效 1 正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：0 失效 1 正常
     *
     * @param status 状态：0 失效 1 正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取玩耍时间
     *
     * @return play_time - 玩耍时间
     */
    public Date getPlayTime() {
        return playTime;
    }

    /**
     * 设置玩耍时间
     *
     * @param playTime 玩耍时间
     */
    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}