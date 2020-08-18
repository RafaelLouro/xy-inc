package test.zup.rafael.xyinc.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import test.zup.rafael.xyinc.model.PontoInteresse

@Repository
interface PontoInteresseRepository : JpaRepository<PontoInteresse, Int> {

    /**
     * Lista os [PontoInteresse] que atendem as exigencias do filtro, ou seja, retorna os objetos que estão dentro dos intervalos de
     * coordXMinimo/coordXMaximo e coordYMinimo/coordYMaximo simultaneamente.
     * @param coordXMinimo valor mínimo da coordenada X
     * @param coordXMaximo valor máximo da coordenada X
     * @param coordYMinimo valor mínimo da coordenada Y
     * @param coordYMaximo valor máximo da coordenada Y
     * @return lista de entidades
     */
    @Query("SELECT pi FROM PontoInteresse pi WHERE pi.coordX >= :xMin AND pi.coordX <= :xMax AND pi.coordY >= :yMin AND pi.coordY <= :yMax")
    fun listaProximosReferencia(@Param("xMin") coordXMinimo: Int,
                                @Param("xMax") coordXMaximo: Int,
                                @Param("yMin") coordYMinimo: Int,
                                @Param("yMax") coordYMaximo: Int): List<PontoInteresse>

}