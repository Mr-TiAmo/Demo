/*
 * Copyright 2020 tu.cn All right reserved. This software is the
 * confidential and proprietary information of tu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tu.cn
 */
package com.li.net.netty;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Administrator
 * @Date: 2020-01-15 18:08
 * @Version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class ResultDataVO {
    /**
     * 通道ID
     */
    @ApiModelProperty(value = "通道ID")
    private String gateId;
    //车辆停车信息
    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;
    /**
     * 停车类型
     */
    @ApiModelProperty(value = "停车类型")
    private String parkType;
    /**
     * 进场时间
     */
    @ApiModelProperty(value = "进场时间")
    private Date inTime;
    /**
     * 出场时间
     */
    @ApiModelProperty(value = "出场时间")
    private Date outTime;
    /**
     * 停车时长
     */
    @ApiModelProperty(value = "停车时长")
    private String parkTime;



    //费率详情
//    /**
//     * 车型ID
//     */
//    private String carModelId;
    /**
     * 车型名称
     */
    @ApiModelProperty(value = "车型名称")
    private String carModelName;
    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal amount;
    /**
     * 已付金额
     */
    @ApiModelProperty(value = "已付金额")
    private BigDecimal payAmount;
    /**
     * 应缴金额
     */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal payableAmount;

//    /**
//     * 费用详情
//     */
//    @ApiModelProperty(value = "费用详情")
//    private List<RateVo> rateVos;

    /**
     * 警告信息
     */
    @ApiModelProperty(value = "警告信息")
    private String warnMsg;

    /**
     * 进出方向（0：进，1：出）
     */
    @ApiModelProperty(value = "进出方向（0：进，1：出）")
    private Integer ioStatus;

    /**
     * 出场全景图
     */
    @ApiModelProperty(value = "出场全景图")
    private String outPanorama;
    /**
     * 出场特征图
     */
    @ApiModelProperty(value = "出场特征图")
    private String outFeatureImg;

    /**
     * 进场全景图
     */
    @ApiModelProperty(value = "进场全景图")
    private String inPanorama;
    /**
     * 进场特征图
     */
    @ApiModelProperty(value = "进场特征图")
    private String inFeatureImg;

    /**
     * 是否需要模糊查询：0不需要，1需要
     */
    @ApiModelProperty(value = "是否需要模糊查询：0不需要，1需要")
    private Integer isIllegibility;

    /**
     * 是否可取消放行 0不可以，1可以
     */
    @ApiModelProperty(value = "是否可取消放行 0不可以，1可以")
    private Integer isCancel;

    /**
     * 是否可特殊放行 0不可以，1 可以
     */
    @ApiModelProperty(value = "是否可特殊放行 0不可以，1 可以")
    private Integer isSpecialRelease;
    /**
     * 是否可收费放行 0不可以，1 可以
     */
    @ApiModelProperty(value = "是否可收费放行 0不可以，1 可以")
    private Integer isFeeRelease;

    @ApiModelProperty(value = "优免金额")
    private BigDecimal discountAmount = new BigDecimal(0);

    @ApiModelProperty(value = "电子支付金额")
    private BigDecimal onlineAmount = new BigDecimal(0);

}
