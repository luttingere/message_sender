package com.reaxium.messenger.modules.push.model;

import java.util.Arrays;

public enum PushServiceType {
    FIREBASE, EXPO;

    public static PushServiceType findByName(String name) {
        if(name == null) {
            return FIREBASE;
        }
        return Arrays.stream(PushServiceType.values())
                .filter(pushServiceType -> pushServiceType.name().equals(name)).findFirst()
                .orElse(null);

    }

}
