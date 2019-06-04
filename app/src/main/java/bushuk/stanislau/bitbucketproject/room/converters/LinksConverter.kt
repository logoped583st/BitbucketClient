package bushuk.stanislau.bitbucketproject.room.converters

import androidx.room.TypeConverter
import bushuk.stanislau.bitbucketproject.room.Href

class LinksConverter {

    @TypeConverter
    fun hrefConverter(href: Href)=href.href

    @TypeConverter
    fun hrefSetConverter(string: String)= Href(string)

}