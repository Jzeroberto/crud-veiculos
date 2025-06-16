package com.lojaveiculos.crud_veiculos.repository;

import com.lojaveiculos.crud_veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // Aqui você pode colocar consultas customizadas se quiser,
    // mas por enquanto o básico já é suficiente.
    @Query("SELECT v FROM Veiculo v WHERE " +
            "LOWER(v.marca) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(v.modelo) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "CAST(v.ano AS string) LIKE CONCAT('%', :filtro, '%')")
    List<Veiculo> buscarPorFiltro(@Param("filtro") String filtro);

}
