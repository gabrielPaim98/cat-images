package tk.gabrielpaim.catimages.model


data class ApiResponse(
    val data: List<ApiResponseData>,
    val status: Int,
    val success: Boolean
)