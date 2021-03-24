package tk.gabrielpaim.catimages.model

data class ApiResponseData(
    val id: String,
    val images: List<Image>?
)