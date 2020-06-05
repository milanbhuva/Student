package com.kiyansoftech.student.utils

import com.kiyansoftech.student.model.BuyClassrooms.BuyClassroom
import com.kiyansoftech.student.model.ChangePwd.ChangePassword
import com.kiyansoftech.student.model.CourseList.CourseList
import com.kiyansoftech.student.model.CourseTopics.CourseTopic
import com.kiyansoftech.student.model.ForgotPwd.ForgotPassword
import com.kiyansoftech.student.model.GetClassrooms.GetClassroom
import com.kiyansoftech.student.model.GetMyClassrooms.GetMyClassroom
import com.kiyansoftech.student.model.GetMyTutors.GetMyTutor
import com.kiyansoftech.student.model.Login.StudentLogin
import com.kiyansoftech.student.model.MyAssignments.MyAssignment
import com.kiyansoftech.student.model.MyClassmates.MyClassmate
import com.kiyansoftech.student.model.MyCourses.MyCourse
import com.kiyansoftech.student.model.Registration.CustomerRegister
import com.kiyansoftech.student.model.TopicVideos.TopicVideo
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface ApiInterface {

    @FormUrlEncoded
    @POST(ConstHelper.STUDENT_REGISTER)
    fun makeStudentRegistration(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") phone: String
    ): Call<CustomerRegister>

    @FormUrlEncoded
    @POST(ConstHelper.STUDENT_LOGIN)
    fun makeStudentLogin(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("fcm_token") fcm_token: String
    ): Call<StudentLogin>

    @FormUrlEncoded
    @POST(ConstHelper.FORGOTPASSWORD)
    fun forgotPassword(
        @Field("email") email: String
    ): Call<ForgotPassword>

    @FormUrlEncoded
    @POST(ConstHelper.CHANGEPASSWORD)
    fun changePassword(
        @Field("user_id") user_id: String,
        @Field("old_password") old_password: String,
        @Field("new_password") new_password: String
    ): Call<ChangePassword>

    @GET(ConstHelper.COURSELIST)
    fun getCourseList(
    ): Call<CourseList>

    @FormUrlEncoded
    @POST(ConstHelper.MYCOURSES)
    fun getMyCourses(
        @Field("classroom_id") classroom_id: String
    ): Call<MyCourse>

    @FormUrlEncoded
    @POST(ConstHelper.COURSETOPIC)
    fun getCourseTopics(
        @Field("course_id") course_id: String
    ): Call<CourseTopic>

    @FormUrlEncoded
    @POST(ConstHelper.TOPICVIDEOS)
    fun getTopicVideos(
        @Field("topic_id") topic_id: String
    ): Call<TopicVideo>

    @GET(ConstHelper.CLASSROOMS)
    fun getClassrooms(
    ): Call<GetClassroom>

    @FormUrlEncoded
    @POST(ConstHelper.MY_CLASSROOMS)
    fun getMyClassroom(
        @Field("user_id") user_id: String
    ): Call<GetMyClassroom>

    @FormUrlEncoded
    @POST(ConstHelper.MY_TUTORS)
    fun getMyTutors(
        @Field("user_id") user_id: String
    ): Call<GetMyTutor>

    @FormUrlEncoded
    @POST(ConstHelper.BUY_CLASSROOMS)
    fun buyClassrooms(
        @Field("user_id") user_id: String,
        @Field("classroom_id") classroom_id: String
    ): Call<BuyClassroom>

    @FormUrlEncoded
    @POST(ConstHelper.MY_CLASSMATES)
    fun getMyClassmates(
        @Field("user_id") user_id: String,
        @Field("classroom_id") classroom_id: String
    ): Call<MyClassmate>

    @FormUrlEncoded
    @POST(ConstHelper.MY_ASSIGNMENTS)
    fun getMyAssignments(
        @Field("classroom_id") classroom_id: String
    ): Call<MyAssignment>

    @Multipart
    @POST(ConstHelper.SUBMIT_CLASSROOM_ASSIGNMENT)
    fun submitAssignments(
        @Part("student_id") student_id: String,
        @Part("assignment_id") assignment_id: String,
        @Part("assigment") assigment: MultipartBody.Part
    ): Call<MyAssignment>

}