package test.zup.rafael.xyinc.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

class PontoInteresseDTO(
        var id: Int? = null,
        @field:NotBlank(message = "O nome não pode estar em branco!")
        val nome: String,
        @field:PositiveOrZero(message = "Coordenada X não pode ser negativa!")
        val coordX: Int,
        @field:PositiveOrZero(message = "Coordenada Y não pode ser negativa!")
        val coordY: Int
) {
}