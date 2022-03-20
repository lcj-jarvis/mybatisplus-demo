package com.mrlu.mybatisplus.domain;

import java.util.List;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 14:21
 */
public class Wallet {
    /**
     * 名称
     */
    private String name;

    public Wallet(String name, List<Currency> currencyList) {
        this.name = name;
        this.currencyList = currencyList;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "name='" + name + '\'' +
                ", currencyList=" + currencyList +
                '}';
    }

    public Wallet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    /**
     * 各种货币
     */
    private List<Currency> currencyList;
}
