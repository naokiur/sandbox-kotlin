package jp.ne.naokiur.user.domain.models.users

import jp.ne.naokiur.user.domain.infra.InMemoryUserRepository


class InMemoryUserFactory : UserFactoryInterface {
    private val repository = InMemoryUserRepository()

    override fun createUser(userName: UserName, fullName: FullName): User {
        val newId = repository.nextIdentity()

        return User(newId, userName, fullName)
    }
}