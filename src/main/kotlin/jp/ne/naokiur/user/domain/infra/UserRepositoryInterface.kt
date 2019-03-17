package jp.ne.naokiur.user.domain.infra

import jp.ne.naokiur.user.domain.models.users.User
import jp.ne.naokiur.user.domain.models.users.UserId
import jp.ne.naokiur.user.domain.models.users.UserName

interface UserRepositoryInterface {
    fun find(targetUserId: UserId): User?
    fun find(targetUserName: UserName): User?
    fun findAll(): List<User>
    fun save(targetUser: User)
    fun remove(targetUser: User)
}