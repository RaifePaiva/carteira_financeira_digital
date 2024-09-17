package br.com.istack.carteira_financeira_digital.domain

data class Income private constructor(
    val name: String,
    val description: String?,
    val value: Double
){

    companion object {

        fun of(name: String, description: String?, value: Double): Income {
            return Income(name, description, value)
        }

    }

}
