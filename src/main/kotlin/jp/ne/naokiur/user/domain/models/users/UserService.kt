package jp.ne.naokiur.user.domain.models.users

import jp.ne.naokiur.user.domain.infra.UserRepository

class UserService {
    private val userRepository = UserRepository()

//    fun isDuplicated(user: User): Boolean {
//        val existUser = userRepository.find(user)
//        return existUser
//    }

    fun register(user: User) {
        userRepository.save(user)
    }

    fun showUsers(): List<User> {
        return userRepository.findAll()
    }
}