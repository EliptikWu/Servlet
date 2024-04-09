package repository.SaleRepositoryImpl;

import dtos.SaleDto;
import mapping.SaleMapper;
import model.Sale;
import repository.Repository;

import java.util.List;

public class SaleRepositoryFilesImpl implements Repository<SaleDto> {
    private final Repository<Sale> Repository;

    public SaleRepositoryFilesImpl() {
        this.Repository = new SaleRepositoryDBImpl();
    }

    @Override
    public List<SaleDto> list() {
        System.out.println("listando desde archivos");
        return Repository.list().stream().map(SaleMapper::mapFrom).toList();
    }

    @Override
    public SaleDto byId(Long id) {
        Sale sale = Repository.byId(id);
        return SaleMapper.mapFrom(sale);
    }

    @Override
    public void save(SaleDto saleDto) {
        System.out.println("Estoy llamando implementacion de archivos");
    }

    @Override
    public void delete(Long id) {
        Repository.delete(id);
    }

    @Override
    public void update(SaleDto saleDto) {

    }
}
