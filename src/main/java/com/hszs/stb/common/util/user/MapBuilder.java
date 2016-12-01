package com.hszs.stb.common.util.user;

import java.util.HashMap;

public final class MapBuilder<K, V> {
	private HashMap<K, V> map = new HashMap<K,V>();
	
	public MapBuilder<K, V> put(K key, V value){
		map.put(key, value);
		return this;
	}
	
	public HashMap<K, V> build(){
		return map;
	}
}
