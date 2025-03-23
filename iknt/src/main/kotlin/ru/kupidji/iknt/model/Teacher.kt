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
    private var id: Long = 0,

    @Column(name = "first_name", nullable = false)
    var firstName: String,

    @Column(name = "middle_name")
    var middleName: String?,

    @Column(name = "last_name", nullable = false)
    var lastName: String,

    @Column( name = "birth_date", columnDefinition = "date")
    var birthDate: LocalDate?,

    @Column(name = "department", nullable = false)
    var department: String,

    @Column(name = "experience", nullable = false)
    var experience: Int,

    @Column(name = "photo_url", columnDefinition = "text")
    var photoUrl: String?,

) {

    constructor(dto: TeachersDTO) : this(
        firstName = dto.firstName,
        middleName = dto.middleName,
        lastName = dto.lastName,
        birthDate = dto.birthDate,
        department = dto.department,
        experience = dto.experience,
        photoUrl = dto.photoUrl(),
    )

    companion object {
        val USER_FRIENDLY_FORMAT: DateTimeFormatter =
            DateTimeFormatter.ofPattern("d MMMM yyyy 'г'", Locale.forLanguageTag("ru-RU"))
    }

    val shortName: String
        get() = Stream.of(
            lastName,
            (firstName.substring(0, 1) + '.'),
            if (middleName != null && middleName!!.isNotEmpty()) (middleName!!.substring(0, 1) + '.') else null
        ).filter { obj: String? -> Objects.nonNull(obj) }.collect(Collectors.joining(" "))

    fun hasBirthDate(): Boolean {
        return age > 0
    }

    val userFriendlyBirthDate: String?
        get() {
            return if (birthDate != null) USER_FRIENDLY_FORMAT.format(birthDate) else null
        }

    val userFriendlyAge: String
        get() {
            val age: Int = age
            val ageStr: String = age.toString()
            val lastChar: Char = ageStr[ageStr.length - 1]

            return when (lastChar) {
                '1' -> "%d год".formatted(age)
                '2', '3', '4' -> "%d года".formatted(age)
                else -> "%d лет".formatted(age)
            }
        }

    val age: Int
        get() {
            return if (birthDate != null) max(Period.between(birthDate, LocalDate.now()).years.toDouble(), 0.0)
                .toInt() else 0
        }

    fun hasPhoto(): Boolean {
        return photoUrl != null && photoUrl!!.isNotEmpty()
    }

    fun update(dto: TeachersDTO) {
        this.firstName = dto.firstName
        this.middleName = dto.middleName
        this.lastName = dto.lastName
        this.birthDate = dto.birthDate
        this.department = dto.department
        this.photoUrl = dto.photoUrl()
    }

}