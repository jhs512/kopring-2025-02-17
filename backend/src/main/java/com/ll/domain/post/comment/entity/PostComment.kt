package com.ll.domain.post.comment.entity

import com.ll.domain.member.member.entity.Member
import com.ll.domain.post.post.entity.Post
import com.ll.global.exceptions.ServiceException
import com.ll.global.jpa.entity.BaseTime
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne

@Entity
class PostComment(
    @ManyToOne(fetch = FetchType.LAZY)
    var post: Post,

    @ManyToOne(fetch = FetchType.LAZY)
    var author: Member,

    @Column(columnDefinition = "TEXT")
    var content: String
) : BaseTime() {

    fun modify(content: String) {
        this.content = content
    }

    fun checkActorCanModify(actor: Member?) {
        actor ?: throw ServiceException("401-1", "로그인 후 이용해주세요.")

        if (actor == author) return

        throw ServiceException("403-2", "작성자만 댓글을 수정할 수 있습니다.")
    }

    fun checkActorCanDelete(actor: Member?) {
        actor ?: throw ServiceException("401-1", "로그인 후 이용해주세요.")

        if (actor.isAdmin || actor == author) return

        throw ServiceException("403-2", "작성자만 댓글을 삭제할 수 있습니다.")
    }
}