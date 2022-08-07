package cn.smartrick.metaverse.common.constant;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;


public enum MarketModel {
    MODEL_SECOND(1, "二级市场"),
    MODEL_OUT_GIVE(2, "场外转赠送"),
    MODEL_TRADE(3, "交易市场"),
    ;

    private int code;
    private String name;

    MarketModel(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<String> marketModelNameList() {
        return Arrays.stream(MarketModel.values()).map(MarketModel::getName).collect(Collectors.toList());
    }

    public static List<Map<String, Object>> list() {
        LinkedList<Map<String, Object>> list = Lists.newLinkedList();
        Arrays.stream(MarketModel.values()).forEach(item -> {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", item.name);
            hashMap.put("code", item.code);
            list.add(hashMap);
        });
        return list;
    }
}
