package com.hussein.jetpackcompose

const val DETAIL_ARGUMENT_ID="id"
const val DETAIL_ARGUMENT_NAME="name"

sealed class Screen(val route:String) {
    object Home:Screen(route = "home_screen")

    /*object Detail:Screen(route = "detail_screen/{$DETAIL_ARGUMENT_ID}/{$DETAIL_ARGUMENT_NAME}"){
        fun passID(id:Int): String {
            //return "detail_screen/$id"
            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_ID}" , newValue = id.toString())
        }
        fun passIDName(id:Int,name:String): String {
            return "detail_screen/$id/$name"
        }
    }*/
    object Detail:Screen(route = "detail_screen?id={$DETAIL_ARGUMENT_ID}&name={$DETAIL_ARGUMENT_NAME}"){
        fun passID(id:Int = 0): String {
            return "detail_screen?id=$id"
        }
        fun passIDName(id:Int = 0 , name:String = "Hussein"): String {
            return "detail_screen?id=$id&name=$name"
        }
    }
}