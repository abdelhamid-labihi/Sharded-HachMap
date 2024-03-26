package net.abdelhamid.shardedhashmap.service;

import net.abdelhamid.shardedhashmap.model.KeyValuePair;
import net.abdelhamid.shardedhashmap.sharding.IShardingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HashMapService {
    private final IShardingStrategy shardingStrategy;
    private final List<HashMap<String, String>> shards;

    public HashMapService(IShardingStrategy shardingStrategy, @Value("${app.numberOfShards}") int numberOfShards) {
        this.shardingStrategy = shardingStrategy;
        this.shards = new ArrayList<>();
        for (int i = 0; i < numberOfShards; i++) {
            this.shards.add(new HashMap<>());
        }
    }

    public void add(KeyValuePair pair) {
        int shardIndex = shardingStrategy.getShardForKey(pair.getKey());
        shards.get(shardIndex).put(pair.getKey(), pair.getValue());
    }

    public String retrieve(String key) {
        int shardIndex = shardingStrategy.getShardForKey(key);
        return shards.get(shardIndex).get(key);
    }

    public void delete(String key) {
        int shardIndex = shardingStrategy.getShardForKey(key);
        shards.get(shardIndex).remove(key);
    }
}