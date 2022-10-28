package ua.niurhechev.sportswear.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.niurhechev.sportswear.models.Manufacturer;
import ua.niurhechev.sportswear.models.Product;
import ua.niurhechev.sportswear.repositories.ManufacturerRepository;

import java.util.List;

@Service
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }
    public List<Manufacturer> getAll() {
        return manufacturerRepository.findAll();
    }
    public Manufacturer getById(int id) {
        return manufacturerRepository.findById(id).orElse(null);
    }
    public void save(Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
    }
    public void delete(int id) {
        manufacturerRepository.deleteById(id);
    }
}
