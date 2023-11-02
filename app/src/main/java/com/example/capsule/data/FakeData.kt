package com.example.capsule.data


val FAKE_NOTES = listOf(
    "Carries oxygen, water, and nutrients to all the tissues in your body",
    "Collects waste products from your tissues and takes them to be removed",
    "Carries cells and proteins that help defend your body from foreign substances"
)



data class MCQ(
    val id: Int,
    val question: String = "",
    val options: List<String> = listOf(),
    val answer: String
)


val MCQQuiz = listOf(
    MCQ(
        id = 1,
        question = "Which of the following blood cells play an important role in blood clotting?",
        options = listOf(
            "Thrombocytes",
            "Neutrophils",
            "Leucocytes",
            "Erythrocytes",
        ),
        answer = "Thrombocytes"
    ),
    MCQ(
        id = 2,
        question = "Serum differs from blood as it lacks",
        options = listOf(
            "antibodies",
            "clotting factors",
            "albumins",
            "globulins"
        ),
        answer = "clotting factors"
    ),
    MCQ(
        id = 3,
        question = "Which of the following is correct?",
        options = listOf(
            "Serum contains blood and fibrinogen",
            "Plasma is blood without lymphocytes",
            "Blood comprises plasma, RBC, WBC and platelets",
            "Lymph is plasma with RBC and WBC",
        ),
        answer = "Blood comprises plasma, RBC, WBC and platelets"
    ),
    MCQ(
        id = 3,
        question = "Which of the following is correct?",
        options = listOf(
            "Serum contains blood and fibrinogen",
            "Plasma is blood without lymphocytes",
            "Blood comprises plasma, RBC, WBC and platelets",
            "Lymph is plasma with RBC and WBC",
        ),
        answer = "Blood comprises plasma, RBC, WBC and platelets"
    ),

    MCQ(
        id = 4,
        question = "DNA is not present in _____",
        options = listOf(
            "an enucleated ovum",
            "hair root",
            "a mature spermatozoa",
            "mature RBCs"
        ),
        answer = "mature RBCs"
    ),
    MCQ(
        id = 1,
        question = "Which of the following blood cells play an important role in blood clotting?",
        options = listOf(
            "Thrombocytes",
            "Neutrophils",
            "Leucocytes",
            "Erythrocytes",
        ),
        answer = "Thrombocytes"
    ),MCQ(
        id = 1,
        question = "Which of the following blood cells play an important role in blood clotting?",
        options = listOf(
            "Thrombocytes",
            "Neutrophils",
            "Leucocytes",
            "Erythrocytes",
        ),
        answer = "Thrombocytes"
    ),
    MCQ(
        id = 2,
        question = "Serum differs from blood as it lacks",
        options = listOf(
            "antibodies",
            "clotting factors",
            "albumins",
            "globulins"
        ),
        answer = "clotting factors"
    ),
    MCQ(
        id = 3,
        question = "Which of the following is correct?",
        options = listOf(
            "Serum contains blood and fibrinogen",
            "Plasma is blood without lymphocytes",
            "Blood comprises plasma, RBC, WBC and platelets",
            "Lymph is plasma with RBC and WBC",
        ),
        answer = "Blood comprises plasma, RBC, WBC and platelets"
    ),

    MCQ(
        id = 4,
        question = "DNA is not present in _____",
        options = listOf(
            "an enucleated ovum",
            "hair root",
            "a mature spermatozoa",
            "mature RBCs"
        ),
        answer = "mature RBCs"
    ),
)


