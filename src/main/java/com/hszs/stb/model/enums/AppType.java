package com.hszs.stb.model.enums;

public enum AppType {

    //1-承包商； 2-师傅； 3-徒弟;
	contractor(1), senior(2), apprentice(3);

    public final byte value;

    AppType(int value) {
        this.value = (byte) value;
    }

    public static AppType of(int val) {
        for (AppType apptype : AppType.values()) {
            if (apptype.value == val) {
                return apptype;
            }
        }
        return null;
    }
}
