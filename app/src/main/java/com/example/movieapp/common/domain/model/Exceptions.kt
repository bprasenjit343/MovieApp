package com.example.movieapp.common.domain.model

import java.io.IOException

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)

class NetworkException(message: String): Exception(message)
class NoMoviesAvailableException(message: String): Exception(message)