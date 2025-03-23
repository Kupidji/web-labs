package ru.kupidji.iknt.model

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream
import kotlin.math.max

@Getter
@NoArgsConstructor
@Entity
@Table(name = "teachers")
class Teacher(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,

    @Column(name = "first_name", nullable = false)
    var firstName: String = "",

    @Column(name = "middle_name")
    var middleName: String? = null,

    @Column(name = "last_name", nullable = false)
    var lastName: String = "",

    @Column( name = "birth_date", columnDefinition = "date")
    var birthDate: LocalDate? = null,

    @Column(name = "department", nullable = false)
    var department: String = "",

    @Column(name = "experience", nullable = false)
    var experience: Int = 0,

    @Column(name = "photo_url", columnDefinition = "text")
    var photoUrl: String? = null,

) {

    constructor(dto: TeacherDTO) : this(
        firstName = dto.firstName,
        middleName = dto.middleName,
        lastName = dto.lastName,
        birthDate = dto.birthDate,
        department = dto.department,
        experience = dto.experience,
        photoUrl = dto.photoUrl(),
    )

    val shortName: String
        get() = Stream.of(
            lastName,
            (firstName.substring(0, 1) + '.'),
            if (middleName != null && middleName!!.isNotEmpty()) (middleName!!.substring(0, 1) + '.') else null
        ).filter { obj: String? -> Objects.nonNull(obj) }.collect(Collectors.joining(" "))

    fun hasPhoto(): Boolean {
        return photoUrl != null && photoUrl!!.isNotEmpty()
    }

    fun update(dto: TeacherDTO) {
        this.firstName = dto.firstName
        this.middleName = dto.middleName
        this.lastName = dto.lastName
        this.birthDate = dto.birthDate
        this.department = dto.department
        this.photoUrl = dto.photoUrl()
    }

}