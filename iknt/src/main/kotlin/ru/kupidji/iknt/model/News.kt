package ru.kupidji.iknt.model

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDate

@Getter
@NoArgsConstructor
@Entity
@Table(name = "news")
data class News(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,

    @Column(name = "title", nullable = false, columnDefinition = "text")
    var title: String = "",

    @Column(name = "content", nullable = false, columnDefinition = "text")
    var content: String = "",

    @Column(name = "cover_url", columnDefinition = "text")
    var coverUrl: String? = null,

    @Column( name = "published_at", nullable = false, columnDefinition = "date")
    var publishedAt: LocalDate = LocalDate.now(),
) {
    constructor(dto: NewsDTO) : this(
        title = dto.title,
        content = dto.content,
        coverUrl = dto.coverUrl(),
        publishedAt = dto.publishedAt,
    )

    fun update(dto: NewsDTO) {
        this.title = dto.title
        this.content = dto.content
        this.coverUrl = dto.coverUrl()
        this.publishedAt = dto.publishedAt
    }

}
