package repository.ClientRepositoryImpl;

import dtos.ClientDto;
import mapping.ClientMapper;
import model.Client;
import repository.Repository;

import java.util.List;

public class ClientRepositoryFilesImpl implements Repository<ClientDto>  {
    private final Repository<Client> Repository;

    public ClientRepositoryFilesImpl() {
        this.Repository = new ClientRepositoryDBImpl();
    }

    @Override
    public List<ClientDto> list() {
        System.out.println("listando desde archivos");
        return Repository.list().stream().map(ClientMapper::mapFrom).toList();
    }

    @Override
    public ClientDto byId(Long id) {
        Client client = Repository.byId(id);
        return ClientMapper.mapFrom(client);
    }

    @Override
    public void save(ClientDto clientDto) {
        System.out.println("Estoy llamando implementacion de archivos");
    }

    @Override
    public void delete(Long id) {
        Repository.delete(id);
    }

    @Override
    public void update(ClientDto clientDto) {

    }
}
