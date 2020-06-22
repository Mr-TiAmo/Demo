package com.li.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author:zhengq
 * @Date:2019/8/23
 * @Description:com.vinsuan.parking.platform.entity
 * @Version:1.0
 * @Desc
 */
@Data
@EqualsAndHashCode
@TableName("parking.base_company")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company extends Model<Company> implements Serializable {
    private Integer id;

    /**
     * 单位id
     */
    @ApiModelProperty(value="单位id",name="cpid")
    private String cpid;
    /**
     * 单位名称
     */
    @ApiModelProperty(value="单位名称",name="cpName")
    private String cpName;

    /**
     * 所属城市ID
     */
    @ApiModelProperty(value="所属城市ID",name="cityId")
    private Integer cityId;

    /**
     * 详细地址
     */
    @ApiModelProperty(value="详细地址",name="address")
    private String address;

    /**
     * 联系人
     */
    @ApiModelProperty(value="联系人",name="linkMan")
    private String linkMan;

    /**
     * 联系电话
     */
    @ApiModelProperty(value="联系电话",name="mobile")
    private String mobile;

    /**
     * 上级ID
     */
    @ApiModelProperty(value="上级ID",name="masterId")
    private String masterId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name = "createTime", hidden=true)
    private LocalDate createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间",name = "lastUpdateTime", hidden=true)
    private Date lastUpdateTime;

    /**
     * 操作员名称
     */
    @ApiModelProperty(value = "操作员名称",name = "operatorName", hidden=true)
    private String operatorName;

    /**
     * 是否修改
     */
    @ApiModelProperty(value = "是否修改",name = "haveUpdate", hidden=true)
    private Integer haveUpdate;

    /**
     * 记录状态(0正常，2删除)
     */
    @ApiModelProperty(value = "记录状态(0正常，2删除)",name = "dataStatus", hidden=true)
    @TableLogic
    private Integer dataStatus;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注",name="remark")
    private String remark;

    /**
     * secretkey
     */
    @ApiModelProperty(value="secretkey",name="secretkey", hidden=true)
    private String secretkey;

    private static final long serialVersionUID = 1L;
}
