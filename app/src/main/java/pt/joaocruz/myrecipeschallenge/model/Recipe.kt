package pt.joaocruz.myrecipeschallenge.model

/**
 * Created by jcruz on 13.07.17.
 */
class Recipe {
    var calories: String?=null
    var carbos: String?=null
    var country: String?=null
    var deliverable_ingredients: List<String>?=null
    var description: String?=null
    var difficulty: Float?=null
    var fats: String?=null
    var favorites: Int?=null
    var fibers: String?=null
    var headline: String?=null
    var highlighted: Boolean?=null
    var id: String?=null
    var image: String?=null
    //var incompatibilities: List<String>?=null // Need to know the type!
    var keywords: List<String>?=null
    var name: String?=null
    var products: List<String>?=null
    var proteins: String?=null
    var ratings: Float?=null
    var thumb: String?=null
    var time: String?=null
    var undeliverable_ingredients: List<String>?=null
    var user: User?=null
    var weeks: List<String>?=null
}