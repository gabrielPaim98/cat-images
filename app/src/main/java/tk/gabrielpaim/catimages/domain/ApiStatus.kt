package tk.gabrielpaim.catimages.domain

/**
 * ApiStatus represent the current status of our api.
 * LOADING indicates that some new data is being fetched
 * ERROR indicates that something went wrong while fetching data
 * DONE indicates that the data has been fetched successfully
 */
enum class ApiStatus { LOADING, DONE, ERROR }