package com.jpabuddy.kotlinentities

import com.jpabuddy.kotlinentities.BaseEntity.Companion.NO_ID
import org.hibernate.Hibernate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

abstract class BaseEntity {

    open val id: Long = NO_ID
    final override  fun equals(other: Any?): Boolean =
        when {
        this === other ->  true
        other == null || Hibernate.getClass(this) != Hibernate.getClass(other) -> false
        else -> id != NO_ID && id == (other as? BaseEntity)?.id
    }

    final override  fun hashCode(): Int = 1756406093

    fun isNew(): Boolean = id == NO_ID

    companion object {
        const val NO_ID = 0L
    }
}


@Entity(name = "stocks")
data class Stock(
    @field: javax.persistence.Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long = NO_ID,
    val symbol: String,
    val price: Double,
)
{
}

@Entity
@Table(name = "users")
data class User(
    @field: javax.persistence.Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long = NO_ID,
    val email: String,)

@Entity
data class Portfolio(
    @field: javax.persistence.Id @field: GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = NO_ID,

    @JoinColumn
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val stocks: MutableList<Stock> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User

){

    companion object {
//        operator fun invoke(stocks:List<Stock>, user:User) = Portfolio(stocks = stocks.toMutableList()).apply {
//            this.user = user
//        }
//        operator fun invoke(user:User) = Portfolio().apply {
//            this.user = user
//        }


    }
}

@Repository
interface BlockingStockRepository : JpaRepository<Stock, Long> {

}



@Repository
interface BlockingUserRepository : JpaRepository<User, Long> {

    fun findByEmail(symbol: String): User?
}


@Repository
interface BlockingPortfolioRepository : JpaRepository<Portfolio, Long> {

}
