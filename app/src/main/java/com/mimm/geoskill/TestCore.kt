package com.mimm.geoskill

class TestCore {
    var stage: TestStages = TestStages.Preparation
    public lateinit var view: TestFragment
    var answered = 0
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
                view.setQuest(view.ctsList[++answered], answered)
                return "Ответ"
            }

            TestStages.Preparation -> {
                stage = TestStages.Thinking
                TestFragment.countries?.shuffle()
                var cts = TestFragment.countries!!
                var c = cts.get(0)
                answered++
                view.setQuest(c, answered)
                return "Ответ"
            }
        }
    }

    constructor(fragment: TestFragment) {
        view = fragment
    }
}

enum class TestStages {
    Preparation,
    Thinking,
    Answered
}