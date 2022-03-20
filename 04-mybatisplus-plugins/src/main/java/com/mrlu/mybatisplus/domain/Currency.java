package com.mrlu.mybatisplus.domain;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 14:22
 */
public class Currency {
    /**
     * 类型: 人民币 RMB , 美元 USD
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Currency() {
    }

    public Currency(String type, Double amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * 金额
     */
    private Double amount;
}
