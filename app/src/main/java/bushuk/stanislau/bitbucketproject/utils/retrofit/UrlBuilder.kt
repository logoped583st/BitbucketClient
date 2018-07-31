package bushuk.stanislau.bitbucketproject.utils.retrofit

object UrlBuilder {

    fun queryFindBuilder(field:Int,search:String?):HashMap<String,String>{
        val query: HashMap<String, String> = HashMap()

//        when(field){
//
//        }
        if(!search.isNullOrEmpty()) {
            query["q"] = "name~%22$search%22"
        }
        return query
    }
}