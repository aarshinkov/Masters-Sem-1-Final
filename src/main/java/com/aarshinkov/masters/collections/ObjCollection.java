package com.aarshinkov.masters.collections;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public abstract class ObjCollection<T> {
    private List<T> collection = new ArrayList<>();
    private Long itemsCount;
}
