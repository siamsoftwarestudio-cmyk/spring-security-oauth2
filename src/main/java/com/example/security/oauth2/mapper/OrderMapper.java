package com.example.security.oauth2.mapper;

import com.example.security.oauth2.dto.OrderRequest;
import com.example.security.oauth2.dto.OrderResponse;
import com.example.security.oauth2.entity.Order;
import com.example.security.oauth2.entity.User;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "ownerUsername", source = "owner.username")
    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponseList(List<Order> orders);

    Order toEntity(OrderRequest request, User owner);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "owner", ignore = true)
    void update(@MappingTarget Order order, OrderRequest request);
}
