package de.winston.utils;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

public class ListEntry<K, V> implements Entry<K, V>, Serializable {
	K key;
	V value;

	public ListEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V oV = value;
		this.value = value;
		return oV;
	}

	@Override
	public int hashCode() {
		return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Map.Entry))
			return false;
		Map.Entry e1 = (Map.Entry) obj;
		Map.Entry e2 = this;
		return (e1.getKey() == null ? e2.getKey() == null : e1.getKey().equals(e2.getKey()))
				&& (e1.getValue() == null ? e2.getValue() == null : e1.getValue().equals(e2.getValue()));
	}
}