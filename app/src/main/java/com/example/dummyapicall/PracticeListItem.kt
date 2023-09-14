package com.example.dummyapicall


data class PracticeListItem(
    val metadata: PracticeMetadata,
    val record: List<PracticeRecord>
)

data class PracticeMetadata(
    val createdAt: String,
    val id: String,
    val name: String,
    val `private`: Boolean
)

data class PracticeRecord(
    val chapterName: String,
    val levelColor: String,
    val levelName: String,
    val practiceLevelModels: List<PracticeLevelModel>,
    val subjectName: String
)

data class PracticeLevelModel(
    val cardType: String,
    val completedQuestions: Int,
    val isCompleted: Boolean,
    val progressPercent: Int,
    val title: String,
    val totalQuestions: Int
)