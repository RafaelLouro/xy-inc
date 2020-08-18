package test.zup.rafael.xyinc.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import test.zup.rafael.xyinc.dto.PontoInteresseDTO
import test.zup.rafael.xyinc.filter.PontoInteresseFilter
import test.zup.rafael.xyinc.model.PontoInteresse
import test.zup.rafael.xyinc.repository.PontoInteresseRepository
import java.util.*

@Service
@Transactional(readOnly = true)
class PontoInteresseService(@Autowired private val repository: PontoInteresseRepository) {

    /**
     * Salva um novo [PontoInteresseDTO].
     * @param dto para cadastro da entidade
     * @return dto preenchido com os dados da nova entidade cadastrada
     */
    @Transactional(readOnly = false)
    fun salva(dto: PontoInteresseDTO): PontoInteresseDTO {
        val salvo: PontoInteresse = repository.save(constroiEntidade(dto))
        return constroiDTO(salvo)
    }

    /**
     * Lista todos os [PontoInteresseDTO] do sistema, sem filtro.
     * @return lista de dtos
     */
    fun lista(): List<PontoInteresseDTO>? {
        val listaDTO: MutableList<PontoInteresseDTO> = ArrayList()
        for (poi in repository.findAll()) {
            listaDTO.add(constroiDTO(poi))
        }
        return listaDTO
    }

    /**
     * Lista os [PontoInteresseDTO] que atendem as exigencias do filtro, ou seja, a partir da referência (coordX e coordY), lista os que estão
     * dentro da distância especificada.
     * @param filter com os dados para fazer a consulta
     * @return lista de dtos
     */
    fun listaProximosReferencia(filter: PontoInteresseFilter): List<PontoInteresseDTO> {
        val listaEntidade = repository.listaProximosReferencia(filter.getCoordXMinimo(), filter.getCoordXMaximo(),
                filter.getCoordYMinimo(), filter.getCoordYMaxima())
        val listaDTO: MutableList<PontoInteresseDTO> = ArrayList()
        for (poi in listaEntidade) {
            listaDTO.add(constroiDTO(poi))
        }
        return listaDTO
    }

    /**
     * Constroi o DTO a partir da entidade.
     * @param pontoInteresse entidade
     * @return dto
     */
    private fun constroiDTO(pontoInteresse: PontoInteresse): PontoInteresseDTO {
        return PontoInteresseDTO(pontoInteresse.id, pontoInteresse.nome, pontoInteresse.coordX, pontoInteresse.coordY)
    }

    /**
     * Constroi a entidade a partir do DTO.
     * @param pontoInteresse dto
     * @return entidade
     */
    private fun constroiEntidade(pontoInteresse: PontoInteresseDTO): PontoInteresse {
        return PontoInteresse(pontoInteresse.id, pontoInteresse.nome, pontoInteresse.coordX, pontoInteresse.coordY)
    }
}