package test.zup.rafael.xyinc.model

import javax.persistence.*

/**
 * Entidade que mapeia os registros da tabela PONTO_INTERESSE.
 */
@Entity
@Table(name = "PONTO_INTERESSE")
@SequenceGenerator(name = PontoInteresse.SEQUENCE_NAME, sequenceName = PontoInteresse.SEQUENCE_NAME, allocationSize = 1)
data class PontoInteresse(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
        @Column(name = "PI_ID")
        var id: Int? = null,

        @Column(name = "PI_NOME")
        val nome: String,

        @Column(name = "PI_COORD_X")
        val coordX: Int,

        @Column(name = "PI_COORD_Y")
        var coordY: Int
) {

    companion object {
        const val SEQUENCE_NAME = "PI_SEQ"
    }
}