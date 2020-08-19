package test.zup.rafael.xyinc.filter

import javax.validation.constraints.PositiveOrZero

class PontoInteresseFilter(
        @field:PositiveOrZero(message = "Coordenada X não pode ser negativa!")
        val coordX: Int,
        @field:PositiveOrZero(message = "Coordenada Y não pode ser negativa!")
        val coordY: Int,
        @field:PositiveOrZero(message = "A distância não pode ser negativa!")
        val distancia: Int
) {

    fun getCoordXMaximo(): Int {
        return coordX + distancia
    }

    fun getCoordXMinimo(): Int {
        val soma = coordX - distancia
        return if (soma > 0) soma else 0;
    }

    fun getCoordYMaxima(): Int {
        return coordY + distancia
    }

    fun getCoordYMinimo(): Int {
        val soma = coordY - distancia
        return if (soma > 0) soma else 0
    }
}