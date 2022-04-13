package API

data class Products(
    val Code: Int,
    val Data: List<DataProduct>,
    val Message: String,
    val Status: Int
)

data class DataProduct(
        val cat_id: Int,
        val product_desc: String,
        val product_id: Int,
        val product_name: String,
        val product_prices: List<ProductPrice>,
        val profile_path: String,
        val shop_id: Int
)


data class ProductPrice(
        val currency: String,
        val discount: Int,
        val product_price_after: String,
        val product_price_before: String,
        val product_price_id: Int,
        val product_size: String
)