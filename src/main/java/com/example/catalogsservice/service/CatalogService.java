package com.example.catalogsservice.service;

import com.example.catalogsservice.dto.CatalogDto;
import com.example.catalogsservice.jpa.CatalogEntity;
import com.example.catalogsservice.jpa.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogService {

  private final CatalogRepository catalogRepository;

  public Iterable<CatalogEntity> getAllCatalogs() {
    return catalogRepository.findAll();
  }

  public CatalogDto getCatalogByProductId(String productId) {
    CatalogEntity catalogEntity = catalogRepository.findByProductId(productId);
    return new ModelMapper().map(catalogEntity, CatalogDto.class);
  }


}
