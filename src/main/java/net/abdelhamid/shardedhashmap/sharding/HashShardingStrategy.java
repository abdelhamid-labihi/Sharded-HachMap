package net.abdelhamid.shardedhashmap.sharding;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HashShardingStrategy implements IShardingStrategy {

    private final int numberOfShards;

    public HashShardingStrategy(@Value("${app.numberOfShards}") int numberOfShards) {
        this.numberOfShards = numberOfShards;
    }

    @Override
    public int getShardForKey(String key) {
        return Math.abs(key.hashCode()) % numberOfShards;
    }
}