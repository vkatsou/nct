package com.nct.ws.mapper;

import com.nct.domain.item.Item;
import com.nct.ws.domain.ItemResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemResourceMapper {

    ItemResourceMapper MAPPER = Mappers.getMapper(ItemResourceMapper.class);

    ItemResource map(Item item);

    List<ItemResource> map(List<Item> item);

    Item map(ItemResource itemResource);

}