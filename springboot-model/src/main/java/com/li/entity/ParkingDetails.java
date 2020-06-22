package com.li.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 栗翱
 * @since 2020-02-14
 */
@Data
@TableName(value = "parking_details")
public class ParkingDetails extends Model<ParkingDetails> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车牌
     */
    private String plateNumber;

    /**
     * 车型
     */
    private String carModelId;

    /**
     * 车场id
     */
    private String pkid;

    /**
     * 区域id
     */
    private String areaId;

    /**
     * 岗亭id
     */
    private String boxId;

    /**
     * 进场通道id
     */
    private String inGateId;

    /**
     * 出场通道id
     */
//    private String outGateId;
    /**
     * 停车类型（0临停，1长租，2等级车）
     */
    private Integer parkType;

    /**
     * 进场记录ID
     */
    private String inRecordId;

    /**
     * 进场时间
     */
    private Date inTime;

    /**
     * 出场时间
     */
    private Date outTime;

    /**
     * 长租车信息uuid
     */
    private String longrentInfoId;

    /**
     *是否有长租车位(0 没有 1 有  默认值 1 只针对长租类型记录)
     */
    private Integer isLongSpace;


    /**
     * 删除标识(0.未删除，1.删除)
     */
    @TableLogic(value = "0",delval = "1")
    @JsonIgnore
    private Integer isDelete;

    /**
     * 当前区域是否出场（0未出场,1出场）
     */
    private Integer isExit;

    /**
     * 数据上传状态(0 已上传，1 未上传，2 上传失败)
     */
    private Integer haveUpload;

}
