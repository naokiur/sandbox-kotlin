package jp.ne.naokiur.domain.applications

import jp.ne.naokiur.domain.infra.user.UserRepositoryInterface
import jp.ne.naokiur.domain.models.users.*
import java.lang.Exception


//fun main(args: Array<String>) {
//    showUsers()
//
//    val taroUser = User(userId = UserName(), userName = UserName("taro"), fullName = FullName("Tanaka", "Taro"))
//    createUser(taroUser)
//
//    showUsers()
//}

class UserApplicationService(
        private val service: UserService,
        private val repository: UserRepositoryInterface,
        private val factory: UserFactoryInterface
) {

    fun createUser(userName: String , firstName: String, familyName: String) {
        val targetUser = factory.createUser(UserName(userName), FullName(firstName, familyName))


        if (service.isDuplicated(targetUser)) {
            throw Exception("重複しています。")
        }

        repository.save(targetUser)
    }

    fun changeUserInfo(user: User) {
        val target = repository.find(user.userName)

        // TODO remove null check
        if (target == null) {
            throw IllegalArgumentException("not found. target id: ${user.showId()}")
        }

        target.changeFullName(FullName(user.fullName.firstName, user.fullName.familyName))

        repository.save(target)
    }

    fun removeUser(id: String) {
        val targetId = UserId(id)
        val target = repository.find(targetId)

        if (target == null) {
            throw IllegalArgumentException("not found. target id: ${targetId.id}")
        }

        repository.remove(target)
    }

    fun getUserInfo(id: String): UserModel? {
        val userId = UserId(id)
        val target = repository.find(userId)

        if (target == null) {
            return null
        }

        return UserModel(target.userId.id, target.userName.name, target.fullName.name)
    }

    fun getUserList(): List<UserSummaryModel> {
        val users = repository.findAll()
        return users.map { UserSummaryModel(it.userId.id, it.fullName.firstName) }
    }

//    fun showUsers(): List<User>{
//        return repository.findAll()
//    }

    fun showUser(userName: UserName): User? {
        return repository.find(userName)
    }
}