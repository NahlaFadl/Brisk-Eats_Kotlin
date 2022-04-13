package API

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ApiInterface {

    //register
    @POST("register")
    @FormUrlEncoded
    @Headers("Accept: Application/json")
    fun register(
        @Field("frist_name") frist_name:String,
        @Field("country") country:String,
        @Field("phone_code") phone_code:Int,
        @Field("mobile_number") mobile_number:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("user_type") user_type:String
    ):Call<ResponseRegister>

    //login
    @POST("login")
    @FormUrlEncoded
    @Headers("Accept: Application/json")
    fun login(
            @Field("number") number:String,
            @Field("password") password:String,
            @Field("user_type") user_type:String,
            @Field("push_token") push_token:String,
            @Field("device_type") device_type:String
    ):Call<ResponseRegister>

    //logout
    @POST("logout")
    @FormUrlEncoded
    @Headers("Accept: Application/json")
    fun logOut(

        @Field("token_mobile_push") token_mobile_push: String?,
        @Field("device_type") device_type:String
    ):Call<ResponseBody>

    //about us
    @GET("pages/about-us")
    fun getTerms():Call<ResponseTerms>

    //product
    @GET("shop/{shop_id}/products")
    fun getProduct(
            @Path("shop_id")id:Int
    ):Call<Products>

    @GET("shops?cat_id=7")
    fun getFiltration(
            @Query("input") text:String,
    ):Call<Filtration>

//    @GET("user")
//    fun getCode():Call<Response>

    //profile
    @FormUrlEncoded
    @POST("profile")
    fun updateProfile(
            @Field("profile") profile:String,
            @Field("old_password") old_password:String,
            @Field("first_name") first_name:String,
            @Field("last_name") last_name:String,
            @Field("mobile_number") mobile_number:String,
            @Field("email") email:String
    ):Call<ResponseRegister>

    //language
    @GET("language")
    fun getLanguage():Callback<ResponseLanguage>

    @FormUrlEncoded
    @POST("favorite/product")
    fun postFavorite(
            @Field("product_id") product_id:Int
    ):Call<PostFavorite>

}