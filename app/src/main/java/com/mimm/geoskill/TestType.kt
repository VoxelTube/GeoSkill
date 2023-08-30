package com.mimm.geoskill

enum class TestType constructor() {

    Capital {
        override val testPoint = "Столица"
    },

    Flag {
        override val testPoint = "Флаг"
    },
    Map {
        override val testPoint = "На карте"
    };

    abstract val testPoint: String

}