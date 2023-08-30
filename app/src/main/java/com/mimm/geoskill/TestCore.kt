package com.mimm.geoskill

class TestCore {
    var stage: TestStages = TestStages.Preparation
    public lateinit var view: TestFragment
    public var answered = 0
    public fun next(): String {
        when (stage) {
            TestStages.Thinking -> {
                stage = TestStages.Answered
                view.open()
                return if (answered != view.config.count)
                    "Следующий этап"
                else {
                    answered = 0
                    stage = TestStages.Preparation
                    "Начать заново"
                }
            }

            TestStages.Answered -> {
                stage = TestStages.Thinking
                view.setQuest(view.ctsList[answered++], answered)
                return "Ответ"
            }

            TestStages.Preparation -> {
                stage = TestStages.Thinking
                TestFragment.countries?.shuffle()
                var cts = TestFragment.countries!!
                if (view.config.count > TestFragment.countries!!.size)
                    view.config.count = TestFragment.countries!!.size
                view.setQuest(view.ctsList[answered++], answered)
                return "Ответ"
            }
        }
    }

    constructor(fragment: TestFragment) {
        view = fragment
        view.gameHandler = this
    }
}

enum class TestStages {
    Preparation,
    Thinking,
    Answered
}