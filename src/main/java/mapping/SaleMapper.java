package mapping;

import dtos.SaleDto;
import model.Sale;

import java.util.List;
import java.util.stream.Collectors;

public class SaleMapper {
    public static SaleDto mapFrom(Sale saleMapper){
        return new SaleDto(saleMapper.getIdSale(),
                saleMapper.getIdToy(),
                saleMapper.getIdClient(),
                saleMapper.getIdEmployee());

    }

    public static Sale mapFrom(SaleDto saleMapper){
        return new Sale(saleMapper.idSale(),
                saleMapper.idToy(),
                saleMapper.idClient(),
                saleMapper.idEmployee());

    }

    public static List<SaleDto> mapFrom(List<Sale> saleMapper){
        return saleMapper.stream().map(SaleMapper::mapFrom).collect(Collectors.toList());

    }
    public static List<Sale> mapFromDto(List<SaleDto> saleMapper){
        return saleMapper.stream().map(SaleMapper::mapFrom).collect(Collectors.toList());
    }
}
