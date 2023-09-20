package com.example.dummyapicall

data class SubjectListResponse(
    val metadata: Metadata,
    val record: List<Record>
)

data class Metadata(
    val createdAt: String,
    val id: String,
    val name: String,
    val `private`: Boolean
)

data class Record(
    val subjectImage: String,
    val subjectName: String,
    val subjectId: String
)