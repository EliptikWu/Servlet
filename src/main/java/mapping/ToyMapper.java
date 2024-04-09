package mapping;

import dtos.ToyDto;
import model.Toy;

import java.util.List;
import java.util.stream.Collectors;

public class ToyMapper {
    public static ToyDto mapFrom(Toy toyMapper){
        return new ToyDto(toyMapper.getId(),
                toyMapper.getName(),
                toyMapper.getPrice(),
                toyMapper.getAmount(),
                toyMapper.getCategory());
    }

    public static Toy mapFrom(ToyDto toyMapper){
        return new Toy(toyMapper.id(),
                toyMapper.name(),
                toyMapper.price(),
                toyMapper.amount(),
                toyMapper.category());
    }

    public static List<ToyDto> mapFrom(List<Toy> toyMapper){
        return toyMapper.stream().map(ToyMapper::mapFrom).collect(Collectors.toList());

    }
    public static List<Toy> mapFromDto(List<ToyDto> toyMapper){
        return toyMapper.stream().map(ToyMapper::mapFrom).collect(Collectors.toList());
    }


}
