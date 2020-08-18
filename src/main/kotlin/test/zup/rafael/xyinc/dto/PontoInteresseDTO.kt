package test.zup.rafael.xyinc.dto

import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

class PontoInteresseDTO(
        var id: Int? = null,
        @NotBlank
        val nome: String,
        @PositiveOrZero(message = "Coordenada X não pode ser negativa!")
        val coordX: Int,
        @PositiveOrZero(message = "Coordenada Y não pode ser negativa!")
        val coordY: Int
) {
}