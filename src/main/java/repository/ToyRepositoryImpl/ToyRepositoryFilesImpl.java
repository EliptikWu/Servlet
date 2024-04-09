package repository.ToyRepositoryImpl;

import dtos.ToyDto;
import mapping.ToyMapper;
import model.Toy;
import repository.Repository;

import java.util.List;

public class ToyRepositoryFilesImpl implements Repository<ToyDto> {
    private final Repository<Toy> Repository;

    public ToyRepositoryFilesImpl() {
        this.Repository = new ToyRepositoryDBImpl();
    }

    @Override
    public List<ToyDto> list() {
        System.out.println("listando desde archivos");
        return Repository.list().stream().map(ToyMapper::mapFrom).toList();
    }

    @Override
    public ToyDto byId(Long id) {
        Toy toy = Repository.byId(id);
        return ToyMapper.mapFrom(toy);
    }

    @Override
    public void save(ToyDto toyDto) {
        System.out.println("Estoy llamando implementacion de archivos");
    }

    @Override
    public void delete(Long id) {
        Repository.delete(id);
    }

    @Override
    public void update(ToyDto toyDto) {

    }
}
