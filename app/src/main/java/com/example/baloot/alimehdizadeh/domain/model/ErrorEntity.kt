package com.example.baloot.alimehdizadeh.domain.model

enum class ErrorEntity(val id: Int) {
    Network(400),NotFound(404),AccessDenied(403),ServiceUnavailable(500),Unknown(300)

}