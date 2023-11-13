package kh.edu.rupp.ite.visitmekotlin.api.model

data class ApiData<T> (
    val status: Status,
    val data: T?
)

enum class Status {
    PROCESSING, SUCCESS, ERROR
}