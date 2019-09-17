package com.pro.warehouse.constant;

public enum Operation {
    APPLY_ENTER_BATCH("批量申请入库"),
    APPLY_ENTER("申请入库"),
    APPLY_OUT_BATCH("批量申请出库"),
    APPLY_OUT("申请出库"),
    ENSURE_ENTER("确认入库"),
    ENSURE_OUT("确认出库"),
    REFUSE_ENTER("拒绝入库"),
    DELETE_APPLY_ENTER("删除入库申请"),
    DELETE_APPLY_ENTER_HIS("删除入库历史纪录"),
    DELETE_APPLY_OUT("删除出库申请"),
    DELETE_APPLY_OUT_HIS("删除出库历史纪录"),
    DELETE_STORE("删除库存"),
    CHECK_STORE("检验库存产品"),
    AUTO_COMPUTE_NUMBER("系统统计数量"),
    REFUSE_OUT("拒绝出库"),
    SEND_EMAIL_REPORT("发送报表");

    private String operation;
    Operation(String op){
        this.operation = op;
    }
    public String getOperation(){
        return operation;
    }
}
