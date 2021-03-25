package Platzi.Marke.persistence.mapper;

import Platzi.Marke.domain.Purchase;
import Platzi.Marke.domain.PurchaseItem;
import Platzi.Marke.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto",target = "productId"),
            @Mapping(source = "cantidad" , target = "quantity"),
            @Mapping(source = "total",target = "total"), // Como se llaman igual se puede omitir
            @Mapping(source = "estado",target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto",ignore = true),
            @Mapping(target = "id.idCompra",ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem purchaseItem);
}
