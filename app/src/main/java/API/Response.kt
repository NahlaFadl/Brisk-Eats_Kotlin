package API

//import API.Data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//register
data class ResponseRegister(var Status:Int, var Message:String, var Code:Int, var Data:RegisterData)
// terms
data class ResponseTerms(var Status:Int, var Message:String, var Code:Int, var Data:TermsData)
//product
// class ResponseProduct()
//{
//    @SerializedName("Status")
//    @Expose
//    var Status:Int? = null
//    @SerializedName("Message")
//    @Expose
//    var Message:String?=null
//    @SerializedName("Code")
//    @Expose
//    var Code:Int?=null
//    @SerializedName("Data")
//    @Expose
//    var Data: ArrayList<ProductData>? = ArrayList()
//}
//language
data class ResponseLanguage(var Status:Int, var Message:String, var Code:Int, var Data:LanguageData)

data class ResponseFiltration(var Status:Int, var Message:String, var Code:Int, var Data:filtrationData)