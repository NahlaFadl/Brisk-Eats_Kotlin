package API

data class Filtration(
    val Code: Int,
    val Data: DataFiltration,
    val Message: String,
    val Status: Int
)

data class DataFiltration(
        val location: Location,
        val shops: List<Shop>
)

data class Shop(
        val busy: Int,
        val caver_path: String,
        val close: Int,
        val deliver_by_shop: Int,
        val distance: String,
        val district_id: Int,
        val offers: Int,
        val payment_method: List<PaymentMethod>,
        val profile_path: String,
        val rate_average: String,
        val rate_count: Int,
        val rate_price: Int,
        val shop_desc: String,
        val shop_id: Int,
        val shop_name: String,
        val shop_status: Int,
        val tags: List<Tag>,
        val time_delivery: String,
        val time_from: String,
        val time_to: String
)

data class Tag(
        val img_path: String,
        val is_featured: Int,
        val ordering: Int,
        val tag_id: Int,
        val tag_name: String
)
data class PaymentMethod(
        val path: Any,
        val payment_method_id: Int,
        val payment_method_name: String,
        val payment_type: String
)
class Location