package API

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterData (var token:String, var user:user )
/**/
data class TermsData(var page_id:Int, var page_body:String, var page_title:String)


// class ProductData ()
//{
//
// @SerializedName("product_id")
// @Expose
// var product_id:Int?=null
// @SerializedName("shop_id")
// @Expose
// var shop_id:Int?=null
// @SerializedName("cat_id")
// @Expose
// var cat_id:Int?=null
// @SerializedName("product_name")
// @Expose
// var product_name:String?=null
// @SerializedName("product_desc")
// @Expose
// var product_desc:String?=null
// @SerializedName("profile_path")
// @Expose
// var profile_path:String?=null
// @SerializedName("product_prices")
// @Expose
// var product_prices:ArrayList<product_prices>?=null
//}

// class product_prices()
// {
//  @SerializedName("product_price_id")
//  @Expose
//  var product_price_id: Int?=null
//  @SerializedName("product_price_before")
//  @Expose
//  var product_price_before:String?=null
//  @SerializedName("discount")
//  @Expose
//  var discount:Int?=null
//  @SerializedName("product_price_after")
//  @Expose
//  var product_price_after:String?=null
//  @SerializedName("product_size")
//  @Expose
//  var product_size: String?=null
//  @SerializedName("currency")
//  @Expose
//  var currency:String?=null
// }
//language
data class LanguageData(var lang_id:Int, var lang_title:String, var lang_text:String,
                        var lang_img_id:Int, var lang_direction:String)

//{
// "lang_id": 2,
// "lang_title": "ar",
// "lang_text": "Arabic",
// "lang_img_id": 597,
// "lang_direction": ""
//}
/***************************************************/
data class filtrationData(var location:String, var shops:ArrayList<filtrationShopData>)

data class filtrationShopData(var shop_id:Int, var shop_name:String, var district_id:Int, var shop_desc:String,
                              var profile_path:String, var caver_path:String, var shop_status:Int,
                              var rate_price:Int, var time_delivery:String, var deliver_by_shop:Int,
                              var rate_average:String, var rate_count:Int, var distance:String)

        /*
        *  "shop_id": 46,
                "shop_name": "KFC Mushrif abu dhabi island",
                "district_id": 331,
                "shop_desc": "KFC Mushrif Mall",
                "profile_path": "http://50.116.98.202/~briskdrive/brisk_delivery/uploads/shop/profile/7e407d30e9ba7c71fa9a1eb0de7d34e2.jpg-500,300",
                "caver_path": "http://50.116.98.202/~briskdrive/brisk_delivery/uploads/shop/profile/2141124d9676904fd2175b24691ee301.jpg-500,300",
                "shop_status": 0,
                "rate_price": 1,
                "time_delivery": "40",
                "deliver_by_shop": 0,
                "rate_average": "4.50",
                "rate_count": 5,
                "distance": "0",*/