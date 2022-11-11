package com.example.graduationprojectandroid

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class GenericsAsTypeParameter<T> protected constructor() {
    val type: Type = (javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0]
}