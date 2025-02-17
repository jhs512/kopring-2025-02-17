package com.ll.domain.post.genFile.entity

import com.ll.domain.base.genFile.genFile.entity.GenFile
import com.ll.domain.post.post.entity.Post
import jakarta.persistence.*
import lombok.NoArgsConstructor

@Entity
@NoArgsConstructor
class PostGenFile(
    @ManyToOne(fetch = FetchType.LAZY)
    var post: Post,

    @Enumerated(EnumType.STRING)
    var typeCode: TypeCode,

    fileNo: Int
) : GenFile(fileNo) {

    enum class TypeCode {
        attachment,
        thumbnail
    }

    override fun getOwnerModelId(): Long {
        return post.id
    }

    override fun getTypeCodeAsStr(): String {
        return typeCode.name
    }
}