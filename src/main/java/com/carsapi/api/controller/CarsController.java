package com.carsapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carsapi.api.dto.CarDTO;
import com.carsapi.api.model.Car;
import com.carsapi.api.repository.CarRepository;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/car")

public class CarsController {

  @Autowired
  private CarRepository repository;

  @GetMapping
  public List<Car> listAll() {
    return repository.findAll();
  }

  @PostMapping
  public void create(@RequestBody @Valid CarDTO req) {
    System.out.println("Modelo: " + req.modelo());
    System.out.println("Fabricante: " + req.fabricante());
    System.out.println("Data de fabricação: " + req.dataFabricacao());
    System.out.println("Valor: R$" + req.valor() + ",00");
    System.out.println("Ano do modelo: " + req.anoModelo());
    repository.save(new Car(req));
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @RequestBody @Valid CarDTO req) {
    repository.findById(id).map(car -> {
      car.setModelo(req.modelo());
      car.setAnoModelo(req.anoModelo());
      car.setDataFabricacao(req.dataFabricacao());
      car.setFabricante(req.fabricante());
      car.setValor(req.valor());
      return repository.save(car);
    });
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
