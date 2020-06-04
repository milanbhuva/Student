package com.kiyansoftech.student.model.CourseTopics


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("course_id")
    var courseId: String? = null,
    @SerializedName("create_on")
    var createOn: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("isActive")
    var isActive: String? = null,
    @SerializedName("notes_count")
    var notesCount: String? = null,
    @SerializedName("quiz_count")
    var quizCount: String? = null,
    @SerializedName("topic_name")
    var topicName: String? = null,
    @SerializedName("videos_count")
    var videosCount: String? = null
)