package net.abdelhamid.shardedhashmap.sharding;

public interface IShardingStrategy {
    int getShardForKey(String key);
}
