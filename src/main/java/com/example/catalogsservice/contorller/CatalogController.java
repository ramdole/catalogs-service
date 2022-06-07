package com.example.catalogsservice.contorller;

import com.example.catalogsservice.dto.CatalogDto;
import com.example.catalogsservice.jpa.CatalogEntity;
import com.example.catalogsservice.service.CatalogService;
import com.example.catalogsservice.vo.ResponseCatalog;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("catalog-service")
@RestController
@RequiredArgsConstructor
public class CatalogController {

  private final CatalogService catalogService;
  private final Environment env;

  @GetMapping("/heath_check")
  public String status() {
    return String.format("It's Working in Catalog Service on Port %s.",
        env.getProperty("local.server.port"));
  }

  @GetMapping("catalogs")
  public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
    Iterable<CatalogEntity> catalogs = catalogService.getAllCatalogs();
    List<ResponseCatalog> result = new ArrayList<>();
    catalogs.forEach(user-> result.add(new ModelMapper().map(user, ResponseCatalog.class)));
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping("catalogs/{productId}")
  public ResponseEntity<ResponseCatalog> getCatalog(@PathVariable String productId) {
    CatalogDto catalogDto = catalogService.getCatalogByProductId(productId);
    ResponseCatalog responseCatalog = new ModelMapper().map(catalogDto, ResponseCatalog.class);

    return ResponseEntity.status(HttpStatus.OK).body(responseCatalog);
  }

}
