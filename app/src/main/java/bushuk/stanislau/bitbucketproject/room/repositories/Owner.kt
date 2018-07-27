package bushuk.stanislau.bitbucketproject.room.repositories

import bushuk.stanislau.bitbucketproject.room.user.Links

data class Owner(val username: String,
                 val display_name: String,
                 val links: Links,
                 val account_id: String)