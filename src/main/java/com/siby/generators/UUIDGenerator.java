package com.siby.generators;

import java.util.UUID;

public class UUIDGenerator implements Generator<UUID> {


    public UUID next() {
        return UUID.randomUUID();
    }
}
