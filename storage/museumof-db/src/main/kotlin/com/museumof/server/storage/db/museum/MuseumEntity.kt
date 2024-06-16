package com.museumof.server.storage.db.museum

import com.museumof.server.storage.db.user.UserEntity
import jakarta.persistence.*

@Entity
@Table(name = "museums")
class MuseumEntity(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column
    var museumName: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    var owner: UserEntity? = null,

    @Column
    var description: String? = null,

    @Column
    var hidden: Boolean? = null,

    @Column
    var active: Boolean? = null,
) {
}
