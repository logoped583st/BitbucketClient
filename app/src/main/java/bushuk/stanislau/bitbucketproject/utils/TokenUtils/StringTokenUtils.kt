package bushuk.stanislau.bitbucketproject.utils.TokenUtils

object StringTokenUtils {


    /**
     * Util for convert string for string that will be supported by base64 format
     *
     */


    fun convertToBase64(string: String):String{
        val stage1 = string.replace("-", "+1")
        val stage2 = stage1.replace("_", "+2")
        val stage3 = stage2.replace("%", "+3")
        return stage3
    }

    fun convertToNormal(string: String):String{
        val stage1 = string.replace("+1", "-")
        val stage2 = stage1.replace("+2", "_")
        val stage3 = stage2.replace("+3", "%")
        return stage3
    }
}