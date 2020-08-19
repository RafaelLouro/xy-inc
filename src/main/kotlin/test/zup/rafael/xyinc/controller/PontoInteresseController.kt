package test.zup.rafael.xyinc.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import test.zup.rafael.xyinc.dto.PontoInteresseDTO
import test.zup.rafael.xyinc.filter.PontoInteresseFilter
import test.zup.rafael.xyinc.service.PontoInteresseService
import javax.validation.Valid

@RestController
@RequestMapping("/poi")
class PontoInteresseController(@Autowired private val service: PontoInteresseService) {

    /**
     * Salva um novo [PontoInteresseDTO].
     * @param dto para cadastro da entidade
     * @return dto preenchido com os dados da nova entidade cadastrada
     */
    @PostMapping
    fun salva(@Valid @RequestBody dto: PontoInteresseDTO): ResponseEntity<PontoInteresseDTO> {
        return ResponseEntity(service.salva(dto), HttpStatus.OK)
    }

    /**
     * Lista todos os [PontoInteresseDTO] do sistema, sem filtro.
     * @return lista de dtos
     */
    @GetMapping
    fun lista(): ResponseEntity<List<PontoInteresseDTO>> {
        val lista = service.lista();
        return ResponseEntity(lista, HttpStatus.OK)
    }

    /**
     * Lista os [PontoInteresseDTO] que atendem as exigencias do filtro, ou seja, a partir da referência (coordX e coordY), lista os que estão
     * dentro da distância especificada.
     * @param filter com os dados para fazer a consulta
     * @return lista de dtos
     */
    @GetMapping("/filter")
    fun listaProximosReferencia(@Valid filter: PontoInteresseFilter): ResponseEntity<List<PontoInteresseDTO>> {
        return ResponseEntity(service.listaProximosReferencia(filter), HttpStatus.OK)
    }

}